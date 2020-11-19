package com.master.salmonapp.service;

import java.util.ArrayList;
import java.util.List;

import com.master.salmonapp.entity.MasterMobil;
import com.master.salmonapp.entity.Persistence.Status;
import com.master.salmonapp.model.MasterMobilModel;
import com.master.salmonapp.repository.MasterMobilRepo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class MasterMobilServiceImpl implements MasterMobilService{
    
    @Autowired
    private MasterMobilRepo masterMobilRepo;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<MasterMobilModel> findAll(){
		List<MasterMobilModel> entities = new ArrayList<>();
		masterMobilRepo.findAll().forEach(data -> {
			MasterMobilModel entity = new MasterMobilModel();
			BeanUtils.copyProperties(data, entity);
			entities.add(entity);
		});
        return entities;
    }
    
    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MasterMobilModel saveOrUpdate(MasterMobilModel entity) {
		MasterMobil masterMobil;
		if (entity.getId() != null) {
			masterMobil = masterMobilRepo.findById(entity.getId()).orElse(null);
			if (masterMobil == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Master Mobil with id : " + entity.getId() + " not found");

			masterMobil.setNamaMobil(entity.getNamaMobil());
            masterMobil.setType(entity.getType());
            masterMobil.setTahunProduksi(entity.getTahunProduksi());
            masterMobil.setHargaOtr(entity.getHargaOtr());
            masterMobil.setCreateDate(entity.getCreateDate());
            masterMobil.setActive(entity.getActive());
			masterMobil = masterMobilRepo.save(masterMobil);
		} else {
			masterMobil = new MasterMobil();
			masterMobil.setNamaMobil(entity.getNamaMobil());
            masterMobil.setType(entity.getType());
            masterMobil.setTahunProduksi(entity.getTahunProduksi());
            masterMobil.setHargaOtr(entity.getHargaOtr());
            masterMobil.setCreateDate(entity.getCreateDate());
            masterMobil.setActive(entity.getActive());
			masterMobil = masterMobilRepo.save(masterMobil);
		}
		BeanUtils.copyProperties(masterMobil, entity);
		return entity;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MasterMobilModel delete(MasterMobilModel entity) {
		if (entity.getId() != null) {
			MasterMobil masterMobil = masterMobilRepo.findById(entity.getId()).orElse(null);
			if (masterMobil == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Master Mobil with id: " + entity.getId() + " not found");
			
			masterMobil.setStatus(Status.NOT_ACTIVE);
			masterMobil = masterMobilRepo.save(masterMobil);
			BeanUtils.copyProperties(masterMobil, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MasterMobilModel deleteById(String id) {
		if (id != null) {
			MasterMobilModel entity = new MasterMobilModel();
			MasterMobil masterMobil = masterMobilRepo.findById(id).orElse(null);
			if (masterMobil == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Master Mobil with id: " + id + " not found");
		
			masterMobil.setStatus(Status.NOT_ACTIVE);
			masterMobil = masterMobilRepo.save(masterMobil);
			BeanUtils.copyProperties(masterMobilRepo, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MasterMobilModel findById(String id) {
		if (id != null) {
			MasterMobilModel entity = new MasterMobilModel();
			MasterMobil masterMobil = masterMobilRepo.findById(id).orElse(null);
			if (masterMobil == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Master mobil with id: " + id + " not found");

			BeanUtils.copyProperties(masterMobil, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }
    
    @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countAll() {
		return masterMobilRepo.count();
	}
    
}
