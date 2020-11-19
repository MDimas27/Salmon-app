package com.master.salmonapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.master.salmonapp.entity.Insentif;
import com.master.salmonapp.model.InsentifModel;
import com.master.salmonapp.model.InsentifRequestModel;
import com.master.salmonapp.repository.InsentifRepository;
import com.master.salmonapp.service.InsentifService;

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
@RequestMapping("/api/rest/insentif")
public class InsentifRestController {

    @Autowired
	private InsentifService insentifService;
	
	@Autowired
	private InsentifRepository insentifRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_HR')")
	@PostMapping("/save")
	public InsentifModel save(@RequestBody @Valid InsentifRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		InsentifModel insentifModel = new InsentifModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return insentifModel;
		} else {
			BeanUtils.copyProperties(request, insentifModel);
			return insentifService.saveOrUpdate(insentifModel);
		}
    }

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_HR')")
	@PostMapping("/update")
	public InsentifModel update(@RequestBody @Valid InsentifRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		InsentifModel insentifModel = new InsentifModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return insentifModel;
		} else {
			BeanUtils.copyProperties(request, insentifModel);
			return insentifService.saveOrUpdate(insentifModel);
		}
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_HR')")
	@DeleteMapping("/deleteById/{id}")
	public InsentifModel delete(@PathVariable("id") final String id) {
		return insentifService.deleteById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_HR')") 
    @DeleteMapping("/deleteDbById")
    public String deleteDbById(@RequestParam("id") String id){
        List<Insentif> listInsentif = insentifRepository.findAll();
        for (Insentif insentif:listInsentif){
            if (insentif.getId().equals(id)){
                insentifRepository.delete(insentif);
                return "Team berhasil dihapus";
            }
        }

        return "Team tidak ditemukan";

    }

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER', 'ROLE_HR')")
	@GetMapping("/findAll")
	public List<InsentifModel> findAll() {
		return insentifService.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER', 'ROLE_HR')")
	@GetMapping("/findById/{id}")
	public InsentifModel findById(@PathVariable("id") final String id) {
		return insentifService.findById(id);
	}
    
}
