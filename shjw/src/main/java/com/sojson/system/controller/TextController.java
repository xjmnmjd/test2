package com.sojson.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sojson.common.model.Wt;
import com.sojson.system.eutil.ExcelOperate;
import com.sojson.system.service.WtService;

@Controller
@Scope(value = "prototype")
@RequestMapping("wt")
public class TextController {
	@Resource
	WtService wtService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());

	@Test
	public void add() {
		List<Wt> list = ExcelOperate.readExcel();
		for (Wt tt : list) {
			tt.setWt_insert_time(insert_time);
			System.out.println("tt=="+tt.toString());
			wtService.addWt(tt);
		}
	}

}
