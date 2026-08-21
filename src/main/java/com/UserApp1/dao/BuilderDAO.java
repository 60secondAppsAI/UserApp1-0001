package com.UserApp1.dao;

import java.util.List;
import java.util.Date;

import com.UserApp1.dao.GenericDAO;
import com.UserApp1.domain.Builder;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface BuilderDAO extends GenericDAO<Builder, Integer> {
  
	List<Builder> findAll();
	


}

