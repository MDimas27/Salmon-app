package com.master.salmonapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.master.salmonapp.entity.TargetSales;
import com.master.salmonapp.model.TargetSalesModel;
import com.master.salmonapp.model.TargetSalesRequestModel;
import com.master.salmonapp.repository.TargetSalesRepository;
import com.master.salmonapp.service.TargetSalesService;

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
@RequestMapping("/api/rest/target-sales")
public class TargetSalesRestController {

    @Autowired
	private TargetSalesService targetSalesService;
	
	@Autowired
	private TargetSalesRepository targetSalesRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_HR')")
	@PostMapping("/save")
	public TargetSalesModel save(@RequestBody @Valid TargetSalesRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		TargetSalesModel targetSalesModel = new TargetSalesModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return targetSalesModel;
		} else {
			BeanUtils.copyProperties(request, targetSalesModel);
			return targetSalesService.saveOrUpdate(targetSalesModel);
		}
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_HR')")
	@PostMapping("/update")
	public TargetSalesModel update(@RequestBody @Valid TargetSalesRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		TargetSalesModel targetSalesModel = new TargetSalesModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return targetSalesModel;
		} else {
			BeanUtils.copyProperties(request, targetSalesModel);
			return targetSalesService.saveOrUpdate(targetSalesModel);
		}
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_HR')")
	@DeleteMapping("/deleteById/{id}")
	public TargetSalesModel delete(@PathVariable("id") final String id) {
		return targetSalesService.deleteById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_HR')") 
    @DeleteMapping("/deleteDbById")
    public String deleteDbById(@RequestParam("id") String id){
        List<TargetSales> listTargetSales = targetSalesRepository.findAll();
        for (TargetSales targetSales:listTargetSales){
            if (targetSales.getId().equals(id)){
                targetSalesRepository.delete(targetSales);
                return "Team berhasil dihapus";
            }
        }

        return "Team tidak ditemukan";

    }

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER', 'ROLE_HR')")
	@GetMapping("/findAll")
	public List<TargetSalesModel> findAll() {
		return targetSalesService.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER', 'ROLE_HR')")
	@GetMapping("/findById/{id}")
	public TargetSalesModel findById(@PathVariable("id") final String id) {
		return targetSalesService.findById(id);
	}
	 
}
