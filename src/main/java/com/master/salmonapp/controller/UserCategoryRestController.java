package com.master.salmonapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.master.salmonapp.model.UserCategoryModel;
import com.master.salmonapp.model.UserCategoryRequestCreateModel;
import com.master.salmonapp.model.UserCategoryRequestUpdateModel;
import com.master.salmonapp.service.UserCategoryService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("api/rest/user-catergory")
public class UserCategoryRestController {

    @Autowired
    private UserCategoryService userCategoryService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public UserCategoryModel save(@RequestBody @Valid UserCategoryRequestCreateModel request,
            BindingResult result,
            HttpServletResponse response) throws IOException {
        UserCategoryModel userCategoryModel = new UserCategoryModel();
        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return userCategoryModel;
        } else {
            BeanUtils.copyProperties(request, userCategoryModel);
            return userCategoryService.saveOrUpdate(userCategoryModel);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update")
    public UserCategoryModel update(@RequestBody @Valid UserCategoryRequestUpdateModel request,
            BindingResult result,
            HttpServletResponse response) throws IOException {
        UserCategoryModel userCategoryModel = new UserCategoryModel();
        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return userCategoryModel;
        } else {
            BeanUtils.copyProperties(request, userCategoryModel);
            return userCategoryService.saveOrUpdate(userCategoryModel);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteById/{id}")
    public UserCategoryModel delete(@PathVariable("id") final String id) {
        return userCategoryService.deleteById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
    @GetMapping("/findAll")
    public List<UserCategoryModel> findAll() {
        return userCategoryService.findAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_USER')")
    @GetMapping("/findById/{id}")
    public UserCategoryModel findById(@PathVariable("id") final String id) {
		return userCategoryService.findById(id);
    }
}