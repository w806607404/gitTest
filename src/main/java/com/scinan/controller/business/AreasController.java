package com.scinan.controller.business;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scinan.controller.BaseController;

/**
 * 区域选择
 *@project Distribution
 *@author vinson
 * 
 */
 
@Controller
@RequestMapping("/area/")
public class AreasController extends BaseController {

	final static Logger logger = Logger.getLogger(AreasController.class);
	
	@RequestMapping("List")
	public String list(){
		return "";
	}
	
	
	
}
