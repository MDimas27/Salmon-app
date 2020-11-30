package com.master.salmonapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.master.salmonapp.model.TeamViewModel;
import com.master.salmonapp.model.TeamViewRequestModel;
import com.master.salmonapp.repository.TeamViewRepository;
import com.master.salmonapp.service.TeamViewService;
import com.master.salmonapp.service.ViewService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/api/rest/teamView")
public class TeamViewRestController {


    @Autowired
    private TeamViewService teamViewService;

    // @Autowired
    // private TeamViewRepository teamViewRepository;
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER', 'ROLE_SALESLEAD')")
	@GetMapping("/findAll")
	public List<TeamViewModel> findAll() {
		return teamViewService.findAll();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping("/save")
	public TeamViewModel save(@RequestBody @Valid TeamViewRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		TeamViewModel teamViewModel = new TeamViewModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return teamViewModel;
		} else {
			BeanUtils.copyProperties(request, teamViewModel);
			return teamViewService.saveOrUpdate(teamViewModel);
		}
    }

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping("/update")
	public TeamViewModel update(@RequestBody @Valid TeamViewRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		TeamViewModel teamViewModel = new TeamViewModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return teamViewModel;
		} else {
			BeanUtils.copyProperties(request, teamViewModel);
			return teamViewService.saveOrUpdate(teamViewModel);
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
	@GetMapping("/findById/{id}")
	public TeamViewModel findById(@PathVariable("id") final Integer id) {
		return teamViewService.findById(id);
	}

}
