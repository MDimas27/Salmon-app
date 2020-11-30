package com.master.salmonapp.service;

import com.master.salmonapp.model.InsentifViewModel;

public interface InsentifViewService extends ViewService<InsentifViewModel, Integer>{

	InsentifViewModel findById(Integer id);
    
}
