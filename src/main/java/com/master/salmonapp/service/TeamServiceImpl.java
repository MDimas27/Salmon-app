package com.master.salmonapp.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import com.master.salmonapp.entity.Team;
import com.master.salmonapp.entity.User;
import com.master.salmonapp.entity.Persistence.Status;
import com.master.salmonapp.model.TeamModel;
import com.master.salmonapp.model.TeamRequestCreateModel;
import com.master.salmonapp.repository.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService{
    
    @Autowired
    private TeamRepository teamRepository;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TeamModel saveOrUpdate(TeamModel entity) {
        // // validate user
        // User user = userRepository.findById(request.getUserId()).orElse(null);
		// if (user == null)
		// 	throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User with id: " + request.getUserId() + " not found");
        // Team team = TeamRepository.findByUserId(user.getId());
        // Set<Target> target = new HashSet<>();
        Team team;
		if (entity.getId() != null) {
			team = teamRepository.findById(entity.getId()).orElse(null);
			if (team == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Team with id: " + entity.getId() + " not found");
			
			team = teamRepository.save(team);
		} else {
			team = new Team();
			team = teamRepository.save(team);
		}
		BeanUtils.copyProperties(team, entity);
		return entity;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TeamModel delete(TeamModel entity) {
		if (entity.getId() != null) {
			Team team = teamRepository.findById(entity.getId()).orElse(null);
			if (team == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Team with id: " + entity.getId() + " not found");

			if (team.getTargets() != null && team.getTargets().size() > 0)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book Category cannot be deleted because already used in Books");
			
			team.setStatus(Status.NOT_ACTIVE);
			team = teamRepository.save(team);
			BeanUtils.copyProperties(team, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TeamModel deleteById(String id) {
		if (id != null) {
			TeamModel entity = new TeamModel();
			Team team = teamRepository.findById(id).orElse(null);
			if (team == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Team with id: " + id + " not found");
			
			if (team.getTargets() != null && team.getTargets().size() > 0)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Team cannot be deleted because already used in Books");
			
			team.setStatus(Status.NOT_ACTIVE);
			team = teamRepository.save(team);
			BeanUtils.copyProperties(team, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TeamModel findById(String id) {
		if (id != null) {
			TeamModel entity = new TeamModel();
			Team team = teamRepository.findById(id).orElse(null);
			if (team == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Team with id: " + id + " not found");
			
			BeanUtils.copyProperties(team, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<TeamModel> findAll() {
		List<TeamModel> entities = new ArrayList<>();
		teamRepository.findAll().forEach(data -> {
			TeamModel entity = new TeamModel();
			BeanUtils.copyProperties(data, entity);
			entities.add(entity);
		});
		return entities;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countAll() {
		return teamRepository.count();

			
    }
        
}
