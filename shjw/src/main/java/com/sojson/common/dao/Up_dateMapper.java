package com.sojson.common.dao;

import com.sojson.common.model.Up_date;


public interface Up_dateMapper {

	Up_date findUp_date(String orderno);

	void addUp_date(Up_date up_date);

	void update_up_date(Up_date up_date);


}
