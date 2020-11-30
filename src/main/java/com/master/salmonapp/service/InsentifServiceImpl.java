package com.master.salmonapp.service;

import java.util.ArrayList;
import java.util.List;

import com.master.salmonapp.entity.Insentif;
import com.master.salmonapp.entity.Persistence.Status;
import com.master.salmonapp.model.InsentifModel;
import com.master.salmonapp.repository.InsentifRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class InsentifServiceImpl implements InsentifService {

    @Autowired
    private InsentifRepository insentifRepository;

    @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<InsentifModel> findAll(){
		List<InsentifModel> entities = new ArrayList<>();
		insentifRepository.findAll().forEach(data -> {
			InsentifModel entity = new InsentifModel();
			BeanUtils.copyProperties(data, entity);
			entities.add(entity);
		});
        return entities;
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public InsentifModel saveOrUpdate(InsentifModel entity) {
		Insentif insentif;
		if (entity.getId() != null) {
			insentif = insentifRepository.findById(entity.getId()).orElse(null);
			if (insentif == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Insentif with id : " + entity.getId() + " not found");

			insentif.setInsentif(entity.getInsentif());
			insentif.setCreateDate(entity.getCreateDate());
			insentif.setActive(entity.getActive());
			insentif = insentifRepository.save(insentif);
		} else {
			insentif = new Insentif();
			insentif.setInsentif(entity.getInsentif());
			insentif.setCreateDate(entity.getCreateDate());
			insentif.setActive(entity.getActive());
			insentif = insentifRepository.save(insentif);
		}
		BeanUtils.copyProperties(insentif, entity);
		return entity;
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public InsentifModel delete(InsentifModel entity) {
		if (entity.getId() != null) {
			Insentif insentif = insentifRepository.findById(entity.getId()).orElse(null);
		    if (insentif == null)
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Insentif with id: " + entity.getId() + " not found");
                
            insentif.setStatus(Status.NOT_ACTIVE);
			insentif = insentifRepository.save(insentif);
			BeanUtils.copyProperties(insentif, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public InsentifModel deleteById(String id) {
		if (id != null) {
			InsentifModel entity = new InsentifModel();
			Insentif insentif = insentifRepository.findById(id).orElse(null);
			if (insentif == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Insentif with id: " + id + " not found");
        
			insentif.setStatus(Status.NOT_ACTIVE);
			insentif = insentifRepository.save(insentif);
			BeanUtils.copyProperties(insentifRepository, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }
    
    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public InsentifModel findById(String id) {
		if (id != null) {
			InsentifModel entity = new InsentifModel();
			Insentif insentif = insentifRepository.findById(id).orElse(null);
			if (insentif == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Insenitf with id: " + id + " not found");

			BeanUtils.copyProperties(insentif, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }

    @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countAll() {
		return insentifRepository.count();
	}
            
    
}
