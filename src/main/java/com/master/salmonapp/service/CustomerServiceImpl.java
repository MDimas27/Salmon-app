package com.master.salmonapp.service;

import java.util.ArrayList;
import java.util.List;

import com.master.salmonapp.entity.Customer;
import com.master.salmonapp.entity.Persistence.Status;
import com.master.salmonapp.model.CustomerModel;
import com.master.salmonapp.repository.CustomerRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CustomerModel> findAll(){
		List<CustomerModel> entities = new ArrayList<>();
		customerRepository.findAll().forEach(data -> {
			CustomerModel entity = new CustomerModel();
			BeanUtils.copyProperties(data, entity);
			entities.add(entity);
		});
        return entities;
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CustomerModel saveOrUpdate(CustomerModel entity) {
		Customer customer;
		if (entity.getId() != null) {
			customer = customerRepository.findById(entity.getId()).orElse(null);
			if (customer == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Customer with id : " + entity.getId() + " not found");

            customer.setNama(entity.getNama());
            customer.setPhone(entity.getPhone());
            customer.setEmail(entity.getEmail());
            customer.setJk(entity.getJk());
			customer.setAddress(entity.getAddress());
			customer.setCreateDate(entity.getCreateDate());
			customer.setActive(entity.getActive());
			customer = customerRepository.save(customer);
		} else {
			customer = new Customer();
            customer.setNama(entity.getNama());
            customer.setPhone(entity.getPhone());
            customer.setEmail(entity.getEmail());
            customer.setJk(entity.getJk());
			customer.setAddress(entity.getAddress());
			customer.setCreateDate(entity.getCreateDate());
			customer.setActive(entity.getActive());
			customer = customerRepository.save(customer);
		}
		BeanUtils.copyProperties(customer, entity);
        return entity;
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CustomerModel delete(CustomerModel entity) {
		if (entity.getId() != null) {
			Customer customer = customerRepository.findById(entity.getId()).orElse(null);
			if (customer == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Customer with id: " + entity.getId() + " not found");

			customer.setStatus(Status.NOT_ACTIVE);
			customer = customerRepository.save(customer);
			BeanUtils.copyProperties(customer, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CustomerModel deleteById(String id) {
		if (id != null) {
			CustomerModel entity = new CustomerModel();
			Customer customer = customerRepository.findById(id).orElse(null);
			if (customer == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Customer with id: " + id + " not found");
			
			customer.setStatus(Status.NOT_ACTIVE);
			customer = customerRepository.save(customer);
			BeanUtils.copyProperties(customerRepository, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }

    @Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CustomerModel findById(String id) {
		if (id != null) {
			CustomerModel entity = new CustomerModel();
			Customer customer = customerRepository.findById(id).orElse(null);
			if (customer == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Customer with id: " + id + " not found");

			BeanUtils.copyProperties(customer, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
    }
    
    @Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countAll() {
		return customerRepository.count();
	}

}
