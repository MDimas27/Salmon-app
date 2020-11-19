package com.master.salmonapp.service;

import java.util.ArrayList;
import java.util.List;

import com.master.salmonapp.entity.Deal;
import com.master.salmonapp.entity.Persistence.Status;
import com.master.salmonapp.model.DealModel;
import com.master.salmonapp.repository.DealRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class DealServiceImpl implements DealService {
    
    @Autowired
    private DealRepository dealRepository;

    @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<DealModel> findAll(){
		List<DealModel> entities = new ArrayList<>();
		dealRepository.findAll().forEach(data -> {
			DealModel entity = new DealModel();
			BeanUtils.copyProperties(data, entity);
			entities.add(entity);
		});
        return entities;
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DealModel saveOrUpdate(DealModel entity) {
		Deal deal;
		if (entity.getId() != null) {
			deal = dealRepository.findById(entity.getId()).orElse(null);
			if (deal == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Deal with id : " + entity.getId() + " not found");

            deal.setKeterangan(entity.getKeterangan());
            deal.setStage(entity.getStage());
			deal.setRevenue(entity.getRevenue());
			deal.setCreateDate(entity.getCreateDate());
			deal.setActive(entity.getActive());
			deal = dealRepository.save(deal);
		} else {
			deal = new Deal();
            deal.setKeterangan(entity.getKeterangan());
            deal.setStage(entity.getStage());
			deal.setRevenue(entity.getRevenue());
			deal.setCreateDate(entity.getCreateDate());
			deal.setActive(entity.getActive());
			deal = dealRepository.save(deal);
		}
		BeanUtils.copyProperties(deal, entity);
		return entity;
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DealModel delete(DealModel entity) {
		if (entity.getId() != null) {
			Deal deal = dealRepository.findById(entity.getId()).orElse(null);
			if (deal == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Deal with id: " + entity.getId() + " not found");
  
			deal.setStatus(Status.NOT_ACTIVE);
			deal = dealRepository.save(deal);
			BeanUtils.copyProperties(deal, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DealModel deleteById(String id) {
		if (id != null) {
			DealModel entity = new DealModel();
			Deal deal = dealRepository.findById(id).orElse(null);
			if (deal == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Deal with id: " + id + " not found");
            
			deal.setStatus(Status.NOT_ACTIVE);
			deal = dealRepository.save(deal);
			BeanUtils.copyProperties(dealRepository, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }
    
    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DealModel findById(String id) {
		if (id != null) {
			DealModel entity = new DealModel();
			Deal deal = dealRepository.findById(id).orElse(null);
			if (deal == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Prospek with id: " + id + " not found");

			BeanUtils.copyProperties(deal, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }

    @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countAll() {
		return dealRepository.count();
	}


}
