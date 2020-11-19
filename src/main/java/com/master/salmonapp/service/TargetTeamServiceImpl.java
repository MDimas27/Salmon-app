package com.master.salmonapp.service;

import java.util.ArrayList;
import java.util.List;

import com.master.salmonapp.entity.TargetTeam;
import com.master.salmonapp.entity.Persistence.Status;
import com.master.salmonapp.model.TargetTeamModel;
import com.master.salmonapp.repository.TargetTeamRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;


@Service
public class TargetTeamServiceImpl implements TargetTeamService {
    
    @Autowired
	private TargetTeamRepository targetTeamRepository;
    
    @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TargetTeamModel> findAll(){
		List<TargetTeamModel> entities = new ArrayList<>();
		targetTeamRepository.findAll().forEach(data -> {
			TargetTeamModel entity = new TargetTeamModel();
			BeanUtils.copyProperties(data, entity);
			entities.add(entity);
		});
        return entities;
    }
    
    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TargetTeamModel saveOrUpdate(TargetTeamModel entity) {
		TargetTeam targetTeam;
		if (entity.getId() != null) {
			targetTeam = targetTeamRepository.findById(entity.getId()).orElse(null);
			if (targetTeam == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Target Team with id : " + entity.getId() + " not found");

			targetTeam.setTahun(entity.getTahun());
			targetTeam.setCreateDate(entity.getCreateDate());
			targetTeam.setTargetTeam(entity.getTargetTeam());
			targetTeam.setCreateDate(entity.getCreateDate());
			targetTeam.setActive(entity.getActive());
			targetTeam = targetTeamRepository.save(targetTeam);
		} else {
			targetTeam = new TargetTeam();
			targetTeam.setTahun(entity.getTahun());
			targetTeam.setCreateDate(entity.getCreateDate());
			targetTeam.setTargetTeam(entity.getTargetTeam());
			targetTeam.setCreateDate(entity.getCreateDate());
			targetTeam.setActive(entity.getActive());
			targetTeam = targetTeamRepository.save(targetTeam);
		}
		BeanUtils.copyProperties(targetTeam, entity);
		return entity;
    }
    
    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TargetTeamModel delete(TargetTeamModel entity) {
		if (entity.getId() != null) {
			TargetTeam targetTeam = targetTeamRepository.findById(entity.getId()).orElse(null);
			if (targetTeam == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Target Team with id: " + entity.getId() + " not found");
			
			targetTeam.setStatus(Status.NOT_ACTIVE);
			targetTeam = targetTeamRepository.save(targetTeam);
			BeanUtils.copyProperties(targetTeam, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }
    
    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TargetTeamModel deleteById(String id) {
		if (id != null) {
			TargetTeamModel entity = new TargetTeamModel();
			TargetTeam targetTeam = targetTeamRepository.findById(id).orElse(null);
			if (targetTeam == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Target Team with id: " + id + " not found");
			
			targetTeam.setStatus(Status.NOT_ACTIVE);
			targetTeam = targetTeamRepository.save(targetTeam);
			BeanUtils.copyProperties(targetTeamRepository, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }
    
    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TargetTeamModel findById(String id) {
		if (id != null) {
			TargetTeamModel entity = new TargetTeamModel();
			TargetTeam targetTeam = targetTeamRepository.findById(id).orElse(null);
			if (targetTeam == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Target Team with id: " + id + " not found");

			BeanUtils.copyProperties(targetTeam, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }
    
    @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countAll() {
		return targetTeamRepository.count();
	}


}