package com.ce.instashare.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ce.instashare.model.User;
import com.ce.instashare.dto.common.response.GenericResponseDTO;
import com.ce.instashare.dto.storage.request.CreateFolderRequestDTO;
import com.ce.instashare.dto.storage.request.NavigateRequestDTO;
import com.ce.instashare.dto.storage.request.UploadFileRequestDTO;
import com.ce.instashare.model.Node;
import com.ce.instashare.model.Block;
import com.ce.instashare.model.Node.NodeType;
import com.ce.instashare.model.Storage;
import com.ce.instashare.repositories.BlockRepository;
import com.ce.instashare.repositories.NodeRepository;
import com.ce.instashare.repositories.StorageRepository;
import com.ce.instashare.repositories.UserRepository;
import com.ce.instashare.dto.storage.response.NodeResponseDTO;
@Service
public class StorageService {
	
	private static Logger logger = LogManager.getLogger(StorageService.class);
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	NodeRepository nodeRep;	
	
	@Autowired
	StorageRepository storageRep;
	
	@Autowired
	UserRepository userRep;
	
	@Autowired
	BlockRepository blockRep;
	
	public void createStorageForUser(User user) throws Exception{
			try {
				if(storageRep.getByUser(user) == null) {
					Node rootNode = new Node();
					rootNode.setType(NodeType.FOLDER);
					nodeRep.save(rootNode);
					Storage storage = new Storage();
					storage.setUser(user);
					storage.setRootNode(rootNode);
					storageRep.save(storage);
					logger.debug("Creating Storage for User :: "+user.getEmail());
				}
				else
					throw new Exception("Storage for user :: "+user.getEmail()+ " already created");
			}
			catch (Exception e) {
				throw e;
			}		
	}
	
	public List<NodeResponseDTO> navigate(NavigateRequestDTO request) throws Exception{
		List<NodeResponseDTO> responseList = new ArrayList<NodeResponseDTO>();
		try {			
			Node rootNode = getRootNodeOfUser(request.getUserId());
			Node currentNode = navigate(rootNode, request.getPath());
			List<Node> pathChilds = nodeRep.getByParentId(currentNode.getId());
			for(int i = 0 ; i < pathChilds.size() ; i++) {
				responseList.add(modelMapper.map(pathChilds.get(i),NodeResponseDTO.class));
			}			
		}
		catch (Exception e) {
			throw e;
		}
		return responseList;
	}
	public GenericResponseDTO uploadFile(UploadFileRequestDTO request) throws Exception{
		GenericResponseDTO response = new GenericResponseDTO(0,"SUCCESS");
		try {
			Node rootNode= getRootNodeOfUser(request.getUserId());
			Node folder = navigate(rootNode, request.getPath());
			List<Block> blocks = new ArrayList<Block>();
			byte[][] blocksData = divideArray(request.getData().getBytes(), 16384);
			for(int i = 0 ; i < blocksData.length ;i++) {
				Block block = new Block();
				block.setData(blocksData[i]);
				blocks.add(block);
			}
			String nextBlockId = "";
			String firstBlockId = "";
			for(int i = blocks.size() - 1 ; i >= 0 ; i--) {
				Block block = blocks.get(i);
				if(i < (blocks.size() - 1))
					block.setNextId(nextBlockId);
				blockRep.save(block);
				nextBlockId = block.getId();
				if(i == 0)
					firstBlockId = block.getId();
				
			}
			if(firstBlockId != "") {
				Node fileNode = new Node();
				fileNode.setName(request.getName());
				fileNode.setType(Node.NodeType.FILE);
				fileNode.setParentId(folder.getId());
				fileNode.setFirstBlockId(firstBlockId);
				nodeRep.save(fileNode);
			}
		
		}
		catch (Exception e) {
			throw e;
		}
		return response;
	}
	public GenericResponseDTO createFolder(CreateFolderRequestDTO request) throws Exception{
		GenericResponseDTO response = new GenericResponseDTO(1, "Error creating folder "+request.getFolderName());
		try {
			Node rootNode = getRootNodeOfUser(request.getUserId());
			Node parentFolder = navigate(rootNode, request.getPath());
			List<Node> childs = nodeRep.getByParentId(parentFolder.getId());
			boolean exist = false;
			for(int i = 0 ; i< childs.size() ;i++) {
				if(childs.get(i).getName().compareTo(request.getFolderName()) == 0) {
					exist = true;
					break;
				}					
			}
			if(!exist) {
				Node folder = new Node();
				folder.setName(request.getFolderName());
				folder.setType(Node.NodeType.FOLDER);
				folder.setParentId(parentFolder.getId());
				nodeRep.save(folder);
				response.setErrorCode(0);
				response.setErrorDescription("SUCCESS");
			}
			else {
				response.setErrorCode(1);
				response.setErrorDescription("A folder with name "+request.getFolderName()+" already exist");
			}
				
		}
		catch (Exception e) {
			throw e;
		}
		return response;
	}
	private static byte[][] divideArray(byte[] source, int chunksize) {


        byte[][] ret = new byte[(int)Math.ceil(source.length / (double)chunksize)][chunksize];

        int start = 0;

        for(int i = 0; i < ret.length; i++) {
            ret[i] = Arrays.copyOfRange(source,start, start + chunksize);
            start += chunksize ;
        }

        return ret;
    }
	
	private Node getRootNodeOfUser(String userId) throws Exception {
		Node rootNode = null;
		try {
			User user = userRep.findById(userId).get();
			Storage storage = storageRep.getByUser(user);
			rootNode = storage.getRootNode();
		}
		catch (Exception e) {
			throw e;
		}
		
		return rootNode;
	}
	private Node navigate(Node rootNode,String path) throws Exception {
		Node folderNode = rootNode;
		if(path != "/") {
			try {
				if(isValidDirectoryPath(path)) {				
					String[] folders = path.split("/");
					Node currentNode = rootNode;
					int i = 0;
					for( ; i < folders.length ;i++) {
						List<Node> childs = nodeRep.getByNameAndParentId(folders[i],currentNode.getId());
						if(!childs.isEmpty() && childs.get(0).getType() == Node.NodeType.FOLDER)
							currentNode = childs.get(0);
						else if(i > 0)
							break;
						
					}				
					if(i == folders.length)
						folderNode = currentNode;
					else
						throw new Exception("Failed to get the especiefied Node");
					
				}
				else
					throw new Exception("Path is invalid");
			}
			catch (Exception e) {
				throw e;
			}
		}		
		return folderNode;
	}
	private boolean isValidDirectoryPath(String path) {
	    Pattern directoryPattern = Pattern.compile("^/|(/[a-zA-Z0-9_-]+)+$");
	     return path != null && !path.trim().isEmpty() && directoryPattern.matcher( path ).matches();
	}
}
