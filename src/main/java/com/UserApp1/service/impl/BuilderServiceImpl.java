package com.UserApp1.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.UserApp1.dao.GenericDAO;
import com.UserApp1.service.GenericService;
import com.UserApp1.service.impl.GenericServiceImpl;
import com.UserApp1.dao.BuilderDAO;
import com.UserApp1.domain.Builder;
import com.UserApp1.dto.BuilderDTO;
import com.UserApp1.dto.BuilderSearchDTO;
import com.UserApp1.dto.BuilderPageDTO;
import com.UserApp1.dto.BuilderConvertCriteriaDTO;
import com.UserApp1.dto.common.RequestDTO;
import com.UserApp1.dto.common.ResultDTO;
import com.UserApp1.service.BuilderService;
import com.UserApp1.util.ControllerUtils;


@Service
public class BuilderServiceImpl extends GenericServiceImpl<Builder, Integer> implements BuilderService {

    private final static Logger logger = LoggerFactory.getLogger(BuilderServiceImpl.class);

	@Autowired
	BuilderDAO builderDao;

	

	@Override
	public GenericDAO<Builder, Integer> getDAO() {
		return (GenericDAO<Builder, Integer>) builderDao;
	}
	
	public List<Builder> findAll () {
		List<Builder> builders = builderDao.findAll();
		
		return builders;	
		
	}

	public ResultDTO addBuilder(BuilderDTO builderDTO, RequestDTO requestDTO) {

		Builder builder = new Builder();

		builder.setBuilderId(builderDTO.getBuilderId());

		builder.setName(builderDTO.getName());

		builder.setDescription(builderDTO.getDescription());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		builder = builderDao.save(builder);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Builder> getAllBuilders(Pageable pageable) {
		return builderDao.findAll(pageable);
	}

	public Page<Builder> getAllBuilders(Specification<Builder> spec, Pageable pageable) {
		return builderDao.findAll(spec, pageable);
	}

	public ResponseEntity<BuilderPageDTO> getBuilders(BuilderSearchDTO builderSearchDTO) {
	
			Integer builderId = builderSearchDTO.getBuilderId(); 
 			String name = builderSearchDTO.getName(); 
 			String description = builderSearchDTO.getDescription(); 
 			String sortBy = builderSearchDTO.getSortBy();
			String sortOrder = builderSearchDTO.getSortOrder();
			String searchQuery = builderSearchDTO.getSearchQuery();
			Integer page = builderSearchDTO.getPage();
			Integer size = builderSearchDTO.getSize();

	        Specification<Builder> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, builderId, "builderId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Builder> builders = this.getAllBuilders(spec, pageable);
		
		//System.out.println(String.valueOf(builders.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(builders.getTotalPages()));
		
		List<Builder> buildersList = builders.getContent();
		
		BuilderConvertCriteriaDTO convertCriteria = new BuilderConvertCriteriaDTO();
		List<BuilderDTO> builderDTOs = this.convertBuildersToBuilderDTOs(buildersList,convertCriteria);
		
		BuilderPageDTO builderPageDTO = new BuilderPageDTO();
		builderPageDTO.setBuilders(builderDTOs);
		builderPageDTO.setTotalElements(builders.getTotalElements());
		return ResponseEntity.ok(builderPageDTO);
	}

	public List<BuilderDTO> convertBuildersToBuilderDTOs(List<Builder> builders, BuilderConvertCriteriaDTO convertCriteria) {
		
		List<BuilderDTO> builderDTOs = new ArrayList<BuilderDTO>();
		
		for (Builder builder : builders) {
			builderDTOs.add(convertBuilderToBuilderDTO(builder,convertCriteria));
		}
		
		return builderDTOs;

	}
	
	public BuilderDTO convertBuilderToBuilderDTO(Builder builder, BuilderConvertCriteriaDTO convertCriteria) {
		
		BuilderDTO builderDTO = new BuilderDTO();

		builderDTO.setBuilderId(builder.getBuilderId());

		builderDTO.setName(builder.getName());

		builderDTO.setDescription(builder.getDescription());
		
		return builderDTO;
	}

	public ResultDTO updateBuilder(BuilderDTO builderDTO, RequestDTO requestDTO) {
		
		Builder builder = builderDao.getById(builderDTO.getBuilderId());
		
		builder.setBuilderId(ControllerUtils.setValue(builder.getBuilderId(), builderDTO.getBuilderId()));
		
		builder.setName(ControllerUtils.setValue(builder.getName(), builderDTO.getName()));
		
		builder.setDescription(ControllerUtils.setValue(builder.getDescription(), builderDTO.getDescription()));

        builder = builderDao.save(builder);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BuilderDTO getBuilderDTOById(Integer builderId) {
	
		Builder builder = builderDao.getById(builderId);
		
		BuilderConvertCriteriaDTO convertCriteria = new BuilderConvertCriteriaDTO();
		return(this.convertBuilderToBuilderDTO(builder,convertCriteria));
	}

}
