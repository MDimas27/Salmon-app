package com.master.salmonapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.master.salmonapp.entity.Deal;
import com.master.salmonapp.model.DealModel;
import com.master.salmonapp.model.DealRequestModel;
import com.master.salmonapp.repository.DealRepository;
import com.master.salmonapp.service.DealService;

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
@RequestMapping("/api/rest/deal")
public class DealRestController {

    @Autowired
	private DealService dealService;
	
	@Autowired
	private DealRepository dealRepository;


    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping("/save")
	public DealModel save(@RequestBody @Valid DealRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		DealModel dealModel = new DealModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return dealModel;
		} else {
			BeanUtils.copyProperties(request, dealModel);
			return dealService.saveOrUpdate(dealModel);
		}
    }

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping("/update")
	public DealModel update(@RequestBody @Valid DealRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		DealModel dealModel = new DealModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return dealModel;
		} else {
			BeanUtils.copyProperties(request, dealModel);
			return dealService.saveOrUpdate(dealModel);
		}
    }

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_USER')")
	@DeleteMapping("/deleteById/{id}")
	public DealModel delete(@PathVariable("id") final String id) {
		return dealService.deleteById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_USER')") 
    @DeleteMapping("/deleteDbById")
    public String deleteDbById(@RequestParam("id") String id){
        List<Deal> listDeal = dealRepository.findAll();
        for (Deal deal:listDeal){
            if (deal.getId().equals(id)){
                dealRepository.delete(deal);
                return "Deal berhasil dihapus";
            }
        }

        return "Deal tidak ditemukan";

    }

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
	@GetMapping("/findAll")
	public List<DealModel> findAll() {
		return dealService.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
	@GetMapping("/findById/{id}")
	public DealModel findById(@PathVariable("id") final String id) {
		return dealService.findById(id);
	}
    
}
