package com.master.salmonapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.master.salmonapp.model.InsentifViewModel;
import com.master.salmonapp.model.InsentifViewRequestModel;
import com.master.salmonapp.service.InsentifViewService;
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
@RequestMapping("/api/rest/insentifView")
public class InsentifViewRestController {

    @Autowired
    private InsentifViewService insentifViewService;
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER', 'ROLE_SALESLEAD')")
	@GetMapping("/findAll")
	public List<InsentifViewModel> findAll() {
		return insentifViewService.findAll();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping("/save")
	public InsentifViewModel save(@RequestBody @Valid InsentifViewRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		InsentifViewModel insentifViewModel = new InsentifViewModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return insentifViewModel;
		} else {
			BeanUtils.copyProperties(request, insentifViewModel);
			return insentifViewService.saveOrUpdate(insentifViewModel);
		}
    }

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping("/update")
	public InsentifViewModel update(@RequestBody @Valid InsentifViewRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		InsentifViewModel insentifViewModel = new InsentifViewModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return insentifViewModel;
		} else {
			BeanUtils.copyProperties(request, insentifViewModel);
			return insentifViewService.saveOrUpdate(insentifViewModel);
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
	@GetMapping("/findById/{id}")
	public InsentifViewModel findById(@PathVariable("id") final Integer id) {
		return insentifViewService.findById(id);
	}

}
