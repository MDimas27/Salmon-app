package com.master.salmonapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.master.salmonapp.model.TeamModel;
import com.master.salmonapp.model.TeamRequestCreateModel;
import com.master.salmonapp.model.TeamRequestUpdateModel;
import com.master.salmonapp.service.TeamService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/api/rest/team")
public class TeamRestController {
    
    @Autowired
    private TeamService teamService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/save")
	public TeamModel save(@RequestBody @Valid TeamRequestCreateModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		TeamModel teamModel = new TeamModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return teamModel;
		} else {
			BeanUtils.copyProperties(request, teamModel);
			return teamService.saveOrUpdate(teamModel);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/update")
	public TeamModel update(@RequestBody @Valid TeamRequestUpdateModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		TeamModel teamModel = new TeamModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return teamModel;
		} else {
			BeanUtils.copyProperties(request, teamModel);
			return teamService.saveOrUpdate(teamModel);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/deleteById/{id}")
	public TeamModel delete(@PathVariable("id") final String id) {
		return teamService.deleteById(id);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
	@GetMapping("/findAll")
	public List<TeamModel> findAll() {
		return teamService.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
	@GetMapping("/findById/{id}")
	public TeamModel findById(@PathVariable("id") final String id) {
		return teamService.findById(id);
	}
}

