package com.UserApp1.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseBuilderDTO {

	private Integer ownerId;

	private Integer builderId;

	private Integer builderStatus;
	
	private Integer nextOrPrevious;
}

