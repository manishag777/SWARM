package com.worksap.stm.SWARMS.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.worksap.stm.SWARMS.dao.UserAccountDao;
import com.worksap.stm.SWARMS.dto.UserAccountDto;
import com.worksap.stm.SWARMS.entity.UserAccountCreationEntity;
import com.worksap.stm.SWARMS.exception.ServiceException;
import com.worksap.stm.SWARMS.service.spec.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserAccountDao UserAccountDao;
	
	@Transactional
	public void insert(UserAccountCreationEntity userAccountCreationEntity)
			throws ServiceException {
		
		String userId = userAccountCreationEntity.getUsername();
		// Insert user account
		UserAccountDto userAccount = new UserAccountDto(userAccountCreationEntity);
		
		try {
			UserAccountDao.insert(userAccount);
		} catch (Exception e) {
			throw new ServiceException("Cannot add user account for userId: "
					+ userAccountCreationEntity.getUsername(), e);
		}

	}

}
