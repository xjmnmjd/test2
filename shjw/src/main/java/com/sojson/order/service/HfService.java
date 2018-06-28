package com.sojson.order.service;

import java.util.List;

import com.sojson.common.model.Hf;

public interface HfService {

	void add_hf(Hf hf);
    
	List<Hf> find_hf(int parseInt);

}
