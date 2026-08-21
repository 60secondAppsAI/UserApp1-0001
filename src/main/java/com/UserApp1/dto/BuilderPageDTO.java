package com.UserApp1.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BuilderPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<BuilderDTO> builders;
}





