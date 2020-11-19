package com.master.salmonapp.service;

import java.util.ArrayList;
import java.util.List;

import com.master.salmonapp.entity.TargetSales;
import com.master.salmonapp.entity.Persistence.Status;
import com.master.salmonapp.model.TargetSalesModel;
import com.master.salmonapp.repository.TargetSalesRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class TargetSalesServiceImpl implements TargetSalesService {
    
    @Autowired
    private TargetSalesRepository targetSalesRepository;

    @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TargetSalesModel> findAll(){
		List<TargetSalesModel> entities = new ArrayList<>();
		targetSalesRepository.findAll().forEach(data -> {
			TargetSalesModel entity = new TargetSalesModel();
			BeanUtils.copyProperties(data, entity);
			entities.add(entity);
		});
        return entities;
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TargetSalesModel saveOrUpdate(TargetSalesModel entity) {
		TargetSales targetSales;
		if (entity.getId() != null) {
			targetSales = targetSalesRepository.findById(entity.getId()).orElse(null);
			if (targetSales == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Target Sales with id : " + entity.getId() + " not found");

            targetSales.setTahun(entity.getTahun());
			targetSales.setTargetSales(entity.getTargetSales());
			targetSales.setCreateDate(entity.getCreateDate());
			targetSales.setActive(entity.getActive());
			targetSales = targetSalesRepository.save(targetSales);
		} else {
			targetSales = new TargetSales();
            targetSales.setTahun(entity.getTahun());
			targetSales.setTargetSales(entity.getTargetSales());
			targetSales.setCreateDate(entity.getCreateDate());
			targetSales.setActive(entity.getActive());
			targetSales = targetSalesRepository.save(targetSales);
		}
		BeanUtils.copyProperties(targetSales, entity);
		return entity;
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TargetSalesModel delete(TargetSalesModel entity) {
		if (entity.getId() != null) {
			TargetSales targetSales = targetSalesRepository.findById(entity.getId()).orElse(null);
			if (targetSales == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Target Sales with id: " + entity.getId() + " not found");

			targetSales.setStatus(Status.NOT_ACTIVE);
			targetSales = targetSalesRepository.save(targetSales);
			BeanUtils.copyProperties(targetSales, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TargetSalesModel deleteById(String id) {
		if (id != null) {
			TargetSalesModel entity = new TargetSalesModel();
			TargetSales targetSales = targetSalesRepository.findById(id).orElse(null);
			if (targetSales == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Target Sales with id: " + id + " not found");
		
			targetSales.setStatus(Status.NOT_ACTIVE);
			targetSales = targetSalesRepository.save(targetSales);
			BeanUtils.copyProperties(targetSales, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TargetSalesModel findById(String id) {
		if (id != null) {
			TargetSalesModel entity = new TargetSalesModel();
			TargetSales targetSales = targetSalesRepository.findById(id).orElse(null);
			if (targetSales == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Target Sales with id: " + id + " not found");

			BeanUtils.copyProperties(targetSales, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }
    
    @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countAll() {
		return targetSalesRepository.count();
	}
    

}
