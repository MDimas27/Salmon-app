package com.master.salmonapp.service;

import java.util.ArrayList;
import java.util.List;

import com.master.salmonapp.entity.Prospek;
import com.master.salmonapp.entity.Persistence.Status;
import com.master.salmonapp.model.ProspekModel;
import com.master.salmonapp.repository.ProspekRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class ProspekServiceImpl implements ProspekService {
    
    @Autowired
    private ProspekRepository prospekRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<ProspekModel> findAll(){
		List<ProspekModel> entities = new ArrayList<>();
		prospekRepository.findAll().forEach(data -> {
			ProspekModel entity = new ProspekModel();
			BeanUtils.copyProperties(data, entity);
			entities.add(entity);
		});
        return entities;
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProspekModel saveOrUpdate(ProspekModel entity) {
		Prospek prospek;
		if (entity.getId() != null) {
			prospek = prospekRepository.findById(entity.getId()).orElse(null);
			if (prospek == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Prospek with id : " + entity.getId() + " not found");

            prospek.setKeterangan(entity.getKeterangan());
            prospek.setStage(entity.getStage());
            prospek.setClosingStatus(entity.getClosingStatus());
			prospek.setProspekRevenue(entity.getProspekRevenue());
			prospek.setCreateDate(entity.getCreateDate());
			prospek.setActive(entity.getActive());
			prospek = prospekRepository.save(prospek);
		} else {
			prospek = new Prospek();
            prospek.setKeterangan(entity.getKeterangan());
            prospek.setStage(entity.getStage());
            prospek.setClosingStatus(entity.getClosingStatus());
			prospek.setProspekRevenue(entity.getProspekRevenue());
			prospek.setCreateDate(entity.getCreateDate());
			prospek.setActive(entity.getActive());
			prospek = prospekRepository.save(prospek);
		}
		BeanUtils.copyProperties(prospek, entity);
        return entity;
    }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProspekModel delete(ProspekModel entity) {
		if (entity.getId() != null) {
			Prospek prospek = prospekRepository.findById(entity.getId()).orElse(null);
			if (prospek == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Prospek with id: " + entity.getId() + " not found");

			prospek = prospekRepository.save(prospek);
			BeanUtils.copyProperties(prospek, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProspekModel deleteById(String id) {
		if (id != null) {
			ProspekModel entity = new ProspekModel();
			Prospek prospek = prospekRepository.findById(id).orElse(null);
			if (prospek == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Prospek with id: " + id + " not found");
		
			prospek.setStatus(Status.NOT_ACTIVE);
			prospek = prospekRepository.save(prospek);
			BeanUtils.copyProperties(prospekRepository, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProspekModel findById(String id) {
		if (id != null) {
			ProspekModel entity = new ProspekModel();
			Prospek prospek = prospekRepository.findById(id).orElse(null);
			if (prospek == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Prospek with id: " + id + " not found");

			BeanUtils.copyProperties(prospek, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }
    
    @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countAll() {
		return prospekRepository.count();
	}
    


}
