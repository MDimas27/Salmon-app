package com.master.salmonapp.service;

import java.util.ArrayList;
import java.util.List;

import com.master.salmonapp.entity.InsentifView;
import com.master.salmonapp.model.InsentifViewModel;
import com.master.salmonapp.repository.InsentifViewRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class InsentifViewServiceImpl implements InsentifViewService {

    @Autowired
    private InsentifViewRepository insentifViewRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<InsentifViewModel> findAll(){
		List<InsentifViewModel> entities = new ArrayList<>();
		insentifViewRepository.findAll().forEach(data -> {
			InsentifViewModel entity = new InsentifViewModel();
			BeanUtils.copyProperties(data, entity);
			entities.add(entity);
		});
        return entities;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public InsentifViewModel saveOrUpdate(InsentifViewModel entity) {
		InsentifView insentifView;
		if (entity.getId() != null) {
			insentifView = insentifViewRepository.findById(entity.getId()).orElse(null);
			if (insentifView == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Insentif with id : " + entity.getId() + " not found");

			insentifView.setVInsentif(entity.getVInsentif());
			insentifView.setNewInsentif(entity.getNewInsentif());
			insentifView.setUnit(entity.getUnit());
			insentifView.setMonthInsentif(entity.getMonthInsentif());
			insentifView.setYearInsentif(entity.getYearInsentif());
			insentifView = insentifViewRepository.save(insentifView);
		} else {
			insentifView = new InsentifView();
			insentifView.setVInsentif(entity.getVInsentif());
			insentifView.setNewInsentif(entity.getNewInsentif());
			insentifView.setUnit(entity.getUnit());
			insentifView.setMonthInsentif(entity.getMonthInsentif());
			insentifView.setYearInsentif(entity.getYearInsentif());
			insentifView = insentifViewRepository.save(insentifView);
		}
		BeanUtils.copyProperties(insentifView, entity);
		return entity;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public InsentifViewModel findById(Integer id) {
		if (id != null) {
			InsentifViewModel entity = new InsentifViewModel();
			InsentifView insentifView = insentifViewRepository.findById(id).orElse(null);
			if (insentifView == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Insentif with id: " + id + " not found");

			BeanUtils.copyProperties(insentifView, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }
}
