package com.master.salmonapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.master.salmonapp.entity.Prospek;
import com.master.salmonapp.model.ProspekModel;
import com.master.salmonapp.model.ProspekRequestModel;
import com.master.salmonapp.repository.ProspekRepository;
import com.master.salmonapp.service.ProspekService;

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
@RequestMapping("/api/rest/prospek")
public class ProspekRestController {
    
    @Autowired
	private ProspekService prospekService;
	
	@Autowired
	private ProspekRepository prospekRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_SALESLEAD', 'ROLE_USER')")
	@PostMapping("/save")
	public ProspekModel save(@RequestBody @Valid ProspekRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		ProspekModel prospekModel = new ProspekModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return prospekModel;
		} else {
			BeanUtils.copyProperties(request, prospekModel);
			return prospekService.saveOrUpdate(prospekModel);
		}
    }

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_SALESLEAD', 'ROLE_USER')")
	@PostMapping("/update")
	public ProspekModel update(@RequestBody @Valid ProspekRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		ProspekModel prospekModel = new ProspekModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return prospekModel;
		} else {
			BeanUtils.copyProperties(request, prospekModel);
			return prospekService.saveOrUpdate(prospekModel);
		}
    }

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_SALESLEAD', 'ROLE_USER')")
	@DeleteMapping("/deleteById/{id}")
	public ProspekModel delete(@PathVariable("id") final String id) {
		return prospekService.deleteById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_SALESLEAD', 'ROLE_USER')") 
    @DeleteMapping("/deleteDbById")
    public String deleteDbById(@RequestParam("id") String id){
        List<Prospek> listProspek = prospekRepository.findAll();
        for (Prospek prospek:listProspek){
            if (prospek.getId().equals(id)){
                prospekRepository.delete(prospek);
                return "Prospek berhasil dihapus";
            }
        }

        return "Prospek tidak ditemukan";

    }

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER', 'ROLE_SALESLEAD')")
	@GetMapping("/findAll")
	public List<ProspekModel> findAll() {
		return prospekService.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER', 'ROLE_SALESLEAD')")
	@GetMapping("/findById/{id}")
	public ProspekModel findById(@PathVariable("id") final String id) {
		return prospekService.findById(id);
	}
	 
}
