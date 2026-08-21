package com.UserApp1.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;


import com.UserApp1.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.UserApp1.domain.Builder;
import com.UserApp1.dto.BuilderDTO;
import com.UserApp1.dto.BuilderSearchDTO;
import com.UserApp1.dto.BuilderPageDTO;
import com.UserApp1.service.BuilderService;
import com.UserApp1.dto.common.RequestDTO;
import com.UserApp1.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/builder")
@RestController
public class BuilderController {

	private final static Logger logger = LoggerFactory.getLogger(BuilderController.class);

	@Autowired
	BuilderService builderService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Builder> getAll() {

		List<Builder> builders = builderService.findAll();
		
		return builders;	
	}

	@GetMapping(value = "/{builderId}")
	@ResponseBody
	public BuilderDTO getBuilder(@PathVariable Integer builderId) {
		
		return (builderService.getBuilderDTOById(builderId));
	}

 	@RequestMapping(value = "/addBuilder", method = RequestMethod.POST)
	public ResponseEntity<?> addBuilder(@RequestBody BuilderDTO builderDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = builderService.addBuilder(builderDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/builders")
	public ResponseEntity<BuilderPageDTO> getBuilders(BuilderSearchDTO builderSearchDTO) {
 
		return builderService.getBuilders(builderSearchDTO);
	}	

	@RequestMapping(value = "/updateBuilder", method = RequestMethod.POST)
	public ResponseEntity<?> updateBuilder(@RequestBody BuilderDTO builderDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = builderService.updateBuilder(builderDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
