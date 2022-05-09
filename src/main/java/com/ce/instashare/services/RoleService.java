package com.ce.instashare.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ce.instashare.dto.role.response.RoleResponseDTO;
import com.ce.instashare.repositories.RoleRepository;
import com.ce.instashare.model.Role;


@Service
public class RoleService {
	private static Logger logger = LogManager.getLogger(RoleService.class);
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	RoleRepository rep;
	
	public RoleResponseDTO getUserDefaultRole() {
		RoleResponseDTO response = null;
		try {
			List<Role> rolelist = rep.getByrolename("USER");
			if(!rolelist.isEmpty()) {
				response = modelMapper.map(rolelist.get(0), RoleResponseDTO.class);
				logger.debug("Returning Default Role :: "+response.getRolename());
			}
		}
		catch (Exception e) {
			throw e;
		}
		return response;
	}
}
