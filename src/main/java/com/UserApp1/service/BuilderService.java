package com.UserApp1.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.UserApp1.domain.Builder;
import com.UserApp1.dto.BuilderDTO;
import com.UserApp1.dto.BuilderSearchDTO;
import com.UserApp1.dto.BuilderPageDTO;
import com.UserApp1.dto.BuilderConvertCriteriaDTO;
import com.UserApp1.service.GenericService;
import com.UserApp1.dto.common.RequestDTO;
import com.UserApp1.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BuilderService extends GenericService<Builder, Integer> {

	List<Builder> findAll();

	ResultDTO addBuilder(BuilderDTO builderDTO, RequestDTO requestDTO);

	ResultDTO updateBuilder(BuilderDTO builderDTO, RequestDTO requestDTO);

    Page<Builder> getAllBuilders(Pageable pageable);

    Page<Builder> getAllBuilders(Specification<Builder> spec, Pageable pageable);

	ResponseEntity<BuilderPageDTO> getBuilders(BuilderSearchDTO builderSearchDTO);
	
	List<BuilderDTO> convertBuildersToBuilderDTOs(List<Builder> builders, BuilderConvertCriteriaDTO convertCriteria);

	BuilderDTO getBuilderDTOById(Integer builderId);



	
}
