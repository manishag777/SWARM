package com.worksap.stm.SWARMS.service.spec;

import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.entity.UserAccountCreationEntity;
import com.worksap.stm.SWARMS.exception.ServiceException;

@Service
public interface UserService {
	
	void insert(UserAccountCreationEntity userAccountCreationEntity)
			throws ServiceException;

}
