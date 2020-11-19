package com.master.salmonapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import com.master.salmonapp.entity.UserCategory;
import com.master.salmonapp.entity.Persistence.Status;
import com.master.salmonapp.model.UserCategoryModel;
import com.master.salmonapp.repository.UserCategoryRepository;

@Service
public class UserCategoryServiceImpl implements UserCategoryService {

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserCategoryModel saveOrUpdate(UserCategoryModel entity) {
        UserCategory userCategory;
        if (entity.getId() != null) {
            userCategory = userCategoryRepository.findById(entity.getId()).orElse(null);
            if (userCategory == null)
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User category with id: " + entity.getId() + " not found");

            userCategory.setCode(entity.getCode());
            userCategory.setName(entity.getName());
            userCategory = userCategoryRepository.save(userCategory);
        } else {
            userCategory = new UserCategory();
            userCategory.setCode(entity.getCode());
            userCategory.setName(entity.getName());
            userCategory = userCategoryRepository.save(userCategory);
        }
        BeanUtils.copyProperties(userCategory, entity);
        return entity;
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserCategoryModel delete(UserCategoryModel entity) {
		if (entity.getId() != null) {
			UserCategory userCategory = userCategoryRepository.findById(entity.getId()).orElse(null);
			if (userCategory == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User Category with id: " + entity.getId() + " not foud");

			if (userCategory.getUsers() != null && userCategory.getUsers().size() > 0)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User Category cannot be deleted because already used in Used");

			userCategory.setStatus(Status.NOT_ACTIVE);
			userCategory = userCategoryRepository.save(userCategory);
			BeanUtils.copyProperties(userCategory, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserCategoryModel deleteById(String id) {
		if (id != null) {
			UserCategoryModel entity = new UserCategoryModel();
			UserCategory userCategory = userCategoryRepository.findById(id).orElse(null);
			if (userCategory == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User Category with id: " + id + " not found");
			
			if (userCategory.getUsers() != null && userCategory.getUsers().size() > 0)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User Category cannot be deleted because already used in Users");
			
			userCategory.setStatus(Status.NOT_ACTIVE);
			userCategory = userCategoryRepository.save(userCategory);
			BeanUtils.copyProperties(userCategoryRepository, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserCategoryModel findById(String id) {
		if (id != null) {
			UserCategoryModel entity = new UserCategoryModel();
			UserCategory userCategory = userCategoryRepository.findById(id).orElse(null);
			if (userCategory == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User Category with id: " + id + " not found");
			
			BeanUtils.copyProperties(userCategory, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<UserCategoryModel> findAll() {
		List<UserCategoryModel> entities = new ArrayList<>();
		userCategoryRepository.findAll().forEach(data -> {
			UserCategoryModel entity = new UserCategoryModel();
			BeanUtils.copyProperties(data, entity);
			entities.add(entity);
		});
        return entities;
    }

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countAll() {
		return userCategoryRepository.count();
	}
}