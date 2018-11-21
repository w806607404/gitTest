/**
 * @Description:
 * @Package: com.scinan.controller.business 
 * @author: 吴广   
 * @date: 2018年7月11日 上午10:42:27 
 */
package com.scinan.controller.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scinan.controller.BaseController;
import com.scinan.iot.service.AreasService;
import com.scinan.iot.service.DeviceDistributionService;
import com.scinan.iot.yunwa.dao.domain.Areas;

/**
 * @Description: 设备分配记录
 * @author: 吴广
 * @date: 2018年7月11日 上午10:42:27 
 */
@Controller
@RequestMapping("/deviceBelong/")
public class DeviceBelongController extends BaseController{
	
	final static Logger logger = Logger.getLogger(DeviceBelongController.class);
	
	@Autowired
	private DeviceDistributionService deviceDistributionService;
	@Autowired
	private AreasService areasService;
	
	/**
	 * 设备分配记录初始化页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model,HttpServletRequest request) throws Exception {
		String jsp = "/order/devicebelong/list";
		List<Areas> provinceList =areasService.fetchProvinceList();
		model.addAttribute("systemResourcesBeans",fetchButtons());
		model.addAttribute("provinceList", provinceList);
		return jsp;
	}
	
	
	/**
	 * 设备分配记录分页
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="fetchDeviceBelongByPage", method=RequestMethod.POST)
	@ResponseBody
	public Object fetchDeviceBelongByPage(Model model,HttpServletRequest request) throws Exception {
		try {
			Map<String,String> params = getParamsMap();
			return deviceDistributionService.fetchByDeviceBelongPage(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fetchDeviceBelongByPage is error ",e);
		}
		return null;
	}
}
