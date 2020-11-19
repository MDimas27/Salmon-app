package com.master.salmonapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.master.salmonapp.entity.Customer;
import com.master.salmonapp.model.CustomerModel;
import com.master.salmonapp.model.CustomerRequestModel;
import com.master.salmonapp.repository.CustomerRepository;
import com.master.salmonapp.service.CustomerService;

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
@RequestMapping("/api/rest/customer")
public class CustomerRestController {
    
    @Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/save")
	public CustomerModel save(@RequestBody @Valid CustomerRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		CustomerModel customerModel = new CustomerModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return customerModel;
		} else {
			BeanUtils.copyProperties(request, customerModel);
			return customerService.saveOrUpdate(customerModel);
		}
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/update")
	public CustomerModel update(@RequestBody @Valid CustomerRequestModel request, 
			BindingResult result,
			HttpServletResponse response) throws IOException {
		CustomerModel customerModel = new CustomerModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return customerModel;
		} else {
			BeanUtils.copyProperties(request, customerModel);
			return customerService.saveOrUpdate(customerModel);
		}
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/deleteById/{id}")
	public CustomerModel delete(@PathVariable("id") final String id) {
		return customerService.deleteById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')") 
    @DeleteMapping("/deleteDbById")
    public String deleteDbById(@RequestParam("id") String id){
        List<Customer> listCustomer = customerRepository.findAll();
        for (Customer customer:listCustomer){
            if (customer.getId().equals(id)){
                customerRepository.delete(customer);
                return "Customer berhasil dihapus";
            }
        }

        return "Customer tidak ditemukan";

    }

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
	@GetMapping("/findAll")
	public List<CustomerModel> findAll() {
		return customerService.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
	@GetMapping("/findById/{id}")
	public CustomerModel findById(@PathVariable("id") final String id) {
		return customerService.findById(id);
	}
}
