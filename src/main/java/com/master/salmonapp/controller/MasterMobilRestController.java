package com.master.salmonapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.master.salmonapp.entity.MasterMobil;
import com.master.salmonapp.model.MasterMobilModel;
import com.master.salmonapp.model.MasterMobilRequestModel;
import com.master.salmonapp.repository.MasterMobilRepo;
import com.master.salmonapp.service.MasterMobilService;

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
@RequestMapping("/api/rest/masterMobil")
public class MasterMobilRestController {

    @Autowired
    private MasterMobilService masterMobilService;

    @Autowired
    private MasterMobilRepo masterMobilRepo;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/save")
	public MasterMobilModel save(@RequestBody @Valid MasterMobilRequestModel request,
			BindingResult result,
			HttpServletResponse response) throws IOException {
		MasterMobilModel masterMobilModel = new MasterMobilModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return masterMobilModel;
		} else {
			BeanUtils.copyProperties(request, masterMobilModel);
			return masterMobilService.saveOrUpdate(masterMobilModel);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update")
    public MasterMobilModel update(@RequestBody @Valid MasterMobilRequestModel request,
            BindingResult result,
            HttpServletResponse response) throws IOException {
        MasterMobilModel masterMobilModel = new MasterMobilModel();
        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return masterMobilModel;
        } else {
            BeanUtils.copyProperties(request, masterMobilModel);
            return masterMobilService.saveOrUpdate(masterMobilModel);
        }
    }

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteById/{id}")
    public MasterMobilModel delete(@PathVariable("id") final String id) {
        return masterMobilService.deleteById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    @DeleteMapping("/deleteDbById")
    public String deleteDbById(@RequestParam("id") String id){
        List<MasterMobil> listMasterMobil = masterMobilRepo.findAll();
        for (MasterMobil masterMobil:listMasterMobil){
            if (masterMobil.getId().equals(id)){
                masterMobilRepo.delete(masterMobil);
                return "Mobil berhasil dihapus";
            }
        }

        return "Mobil tidak ditemukan";

    }

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
    @GetMapping("/findAll")
    public List<MasterMobilModel> findAll() {
        return masterMobilService.findAll();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
    @GetMapping("/findById/{id}")
    public MasterMobilModel findById(@PathVariable("id") final String id) {
		return masterMobilService.findById(id);
	}
}

