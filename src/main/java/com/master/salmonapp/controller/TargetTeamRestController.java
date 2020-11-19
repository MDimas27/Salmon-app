package com.master.salmonapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.master.salmonapp.entity.TargetTeam;
import com.master.salmonapp.model.TargetTeamModel;
import com.master.salmonapp.model.TargetTeamRequestModel;
import com.master.salmonapp.repository.TargetTeamRepository;
import com.master.salmonapp.service.TargetTeamService;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/api/rest/target-team")
public class TargetTeamRestController {

    @Autowired
	private TargetTeamService targetTeamService;

	@Autowired
	private TargetTeamRepository targetTeamRepository;
	
    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_HR')")
	@PostMapping("/save")
	public TargetTeamModel save(@RequestBody @Valid TargetTeamRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		TargetTeamModel targetTeamModel = new TargetTeamModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return targetTeamModel;
		} else {
			BeanUtils.copyProperties(request, targetTeamModel);
			return targetTeamService.saveOrUpdate(targetTeamModel);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_HR')")
	@PostMapping("/update")
	public TargetTeamModel update(@RequestBody @Valid TargetTeamRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		TargetTeamModel targetTeamModel = new TargetTeamModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return targetTeamModel;
		} else {
			BeanUtils.copyProperties(request, targetTeamModel);
			return targetTeamService.saveOrUpdate(targetTeamModel);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_HR')")
	@DeleteMapping("/deleteById/{id}")
	public TargetTeamModel delete(@PathVariable("id") final String id) {
		return targetTeamService.deleteById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_HR')") 
    @DeleteMapping("/deleteDbById")
    public String deleteDbById(@RequestParam("id") String id){
        List<TargetTeam> listTargetTeam = targetTeamRepository.findAll();
        for (TargetTeam targetTeam:listTargetTeam){
            if (targetTeam.getId().equals(id)){
                targetTeamRepository.delete(targetTeam);
                return "Target Team berhasil dihapus";
            }
        }

        return "Target Team tidak ditemukan";

    }

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER', 'ROLE_HR')")
	@GetMapping("/findAll")
	public List<TargetTeamModel> findAll() {
		return targetTeamService.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER', 'ROLE_HR')")
	@GetMapping("/findById/{id}")
	public TargetTeamModel findById(@PathVariable("id") final String id) {
		return targetTeamService.findById(id);
	}
}
