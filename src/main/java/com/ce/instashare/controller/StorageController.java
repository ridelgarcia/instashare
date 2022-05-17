package com.ce.instashare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ce.instashare.dto.common.response.GenericResponseDTO;
import com.ce.instashare.dto.storage.request.CreateFolderRequestDTO;
import com.ce.instashare.dto.storage.request.NavigateRequestDTO;
import com.ce.instashare.dto.storage.request.UploadFileRequestDTO;
import com.ce.instashare.dto.storage.response.NodeResponseDTO;
import com.ce.instashare.services.StorageService;

@CrossOrigin
@RestController
@RequestMapping("/storage")
public class StorageController {

	@Autowired
	StorageService storageSrv;
	
	
	@RequestMapping(value = "/createfolder",method = RequestMethod.PUT)
	public ResponseEntity<?> createFolder(@RequestBody CreateFolderRequestDTO createDto) {		
		try {
			GenericResponseDTO response = storageSrv.createFolder(createDto);
			return new ResponseEntity<GenericResponseDTO>(response,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		 
	}
	@RequestMapping(value = "/uploadfile",method = RequestMethod.PUT)
	public ResponseEntity<?> createFolder(@RequestBody UploadFileRequestDTO uploadDto) {		
		try {
			GenericResponseDTO response = storageSrv.uploadFile(uploadDto);
			return new ResponseEntity<GenericResponseDTO>(response,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		 
	}
	
	@RequestMapping(value = "/navigate",method = RequestMethod.POST)
	public ResponseEntity<?> navigate(@RequestBody NavigateRequestDTO navigateDTO) {		
		try {
			List<NodeResponseDTO> response = storageSrv.navigate(navigateDTO);
			return new ResponseEntity<List<NodeResponseDTO>>(response,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		 
	}
}
