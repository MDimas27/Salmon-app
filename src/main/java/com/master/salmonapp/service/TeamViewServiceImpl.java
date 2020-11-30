package com.master.salmonapp.service;

import java.util.ArrayList;
import java.util.List;

import com.master.salmonapp.entity.TeamView;
import com.master.salmonapp.model.TeamViewModel;
import com.master.salmonapp.repository.TeamViewRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class TeamViewServiceImpl implements TeamViewService{

    @Autowired
	private TeamViewRepository teamViewRepository;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TeamViewModel saveOrUpdate(TeamViewModel entity) {
		TeamView teamView;
		if (entity.getId() != null) {
			teamView = teamViewRepository.findById(entity.getId()).orElse(null);
			if (teamView == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Team with id : " + entity.getId() + " not found");

			teamView.setActive(entity.getActive());
			teamView.setCreateDate(entity.getCreateDate());
			teamView = teamViewRepository.save(teamView);
		} else {
			teamView = new TeamView();
			teamView.setActive(entity.getActive());
			teamView.setCreateDate(entity.getCreateDate());
			teamView = teamViewRepository.save(teamView);
		}
		BeanUtils.copyProperties(teamView, entity);
		return entity;
	}


    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TeamViewModel> findAll(){
		List<TeamViewModel> entities = new ArrayList<>();
		teamViewRepository.findAll().forEach(data -> {
			TeamViewModel entity = new TeamViewModel();
			BeanUtils.copyProperties(data, entity);
			entities.add(entity);
		});
        return entities;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TeamViewModel findById(Integer id) {
		if (id != null) {
			TeamViewModel entity = new TeamViewModel();
			TeamView teamView = teamViewRepository.findById(id).orElse(null);
			if (teamView == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Team with id: " + id + " not found");

			BeanUtils.copyProperties(teamView, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }

    
}
