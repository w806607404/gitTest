package com.scinan.controller.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scinan.controller.BaseController;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.s6000.dao.domain.DeviceInfo;
import com.scinan.iot.service.DeviceDistributionService;
import com.scinan.iot.service.DeviceInfoService;
import com.scinan.utils.Common;
import com.scinan.utils.RedisUtil;
import com.scinan.utils.StringUtil;

/**
 * 设备批量分配功能
 * 
 * @author kim
 * @date 2015-12-30
 */
@Controller
@RequestMapping("/distribution/devicebatch")
public class DeviceBatchDistributionController extends BaseController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DeviceInfoService deviceInfoService;
	@Autowired
	private DeviceDistributionService deviceDistributionService;
	

	// 线程池
	ExecutorService executorService = Executors.newCachedThreadPool();

	/**
	 * 功能说明：批量分配初使化
	 * 
	 * @param model
	 * @param f_file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("init")
	public String device_of_import(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		//不是指定的用户,不允许执行设备导入操作
		try{
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("parent_user_id", accountInfoBean.getId());
			params.put("ids_arr", String.valueOf(request.getParameter("ids_arr")));
			params.put("roleType", accountInfoBean.getRole_type().intValue());
			deviceDistributionService.setModuleInfo(model, params);
			model.addAttribute("companyId", accountInfoBean.getCompany_id());
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("device_of_import is error " + ex.getMessage());
		}
		
		return "/batchImport/deviceImport";
	}

	
	/**
	 * <一句话功能简述>设备信息导入操作 
	 * <功能详细描述>
	 * @param file
	 * @param request
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "importFile")
	@ResponseBody
	public Object importFile(MultipartFile f_file, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		String user_id = request.getParameter("user_id");
		FileReader file = null;
		BufferedReader bufRead = null;
		String sumCount = "0"; // 总数据条数
		String nowPath = "";
		List<DeviceInfo> deviceList = new ArrayList<DeviceInfo>();
		String lineTxt = null;
		
		try {
			if(accountInfoBean.getRole_type().intValue()<=0){   //如果不是厂商或经销商，不允许执行批量分配操作
				return "-8";
			}
			
			// 文件不为空
			if (!f_file.isEmpty()) {
				Date now = new Date();
				// 生成存放文档的路径
				String path = "/upload/device_import/"+ DateFormatUtils.format(now, "yyyyMMdd");
				String name = f_file.getOriginalFilename();
				// 获得当前文件的路径
				nowPath = /* prop.getProperty(Constants.DOWNLOAD_URL); */request.getRealPath("/");
				String realPath = nowPath + path;
				// 文件上传并保存到upload目录下
				FileUtils.copyInputStreamToFile(f_file.getInputStream(),new File(realPath, name));
				file = new FileReader(new File(realPath, name)); // path 指定文件的路径
				// 从读取流中读取文件信息
				bufRead = new BufferedReader(file);
				// 循环读取文件中的内容
				while ((lineTxt = bufRead.readLine()) != null) {
					String[] s = lineTxt.split(",");
					String deviceId = null;
					if (null != s && !"".equals(StringUtil.isValidateStr(s[0]))) {
						for (int i = 0; i < s.length; i++) {
							switch (i) {
							case 0:
								deviceId = s[0];
								break;
							default:
								break;
							}
						}
						
						/****************封装对应的设备信息 start**************/
						DeviceInfo deviceInfo = new DeviceInfo();
						deviceInfo.setId(deviceId);
						deviceInfo.setUser_digit(Long.parseLong(user_id));
						deviceList.add(deviceInfo);
						/****************封装对应的设备信息 end**************/
					}
				}

				// 在redis里边取得对应的设备信息
				RedisUtil.set("datacenter_file_import_with_device_url","/home/moosefsdata");
				// 导入之前,清空相关数据信息
				RedisUtil.set(accountInfoBean.getUser_name()+"_datacenter_file_import_with_device_message", "");
				//线程异步处理
				sumCount = "0";
			} else {
				System.out.println("找不到指定的文件");
				sumCount = "-3";
			}
		} catch (Exception e) {
			sumCount = "-4";
			logger.error("DeviceBatchDistributionController is upFileImport operation DataBase " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (null != bufRead) {
					bufRead.close();
				}
			} catch (IOException e) {
				logger.error("DeviceBatchDistributionController is upFileImport is Close bufRead " + e.getMessage());
				sumCount = "-4";
			} catch (Exception ex) {
				logger.error("DeviceBatchDistributionController is upFileImport is error "+ ex.getMessage());
				sumCount = "-4";
			}
		}
		return sumCount;
	}
	
	
	/**
	 * 功能说明：查看导入设备状态
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("deviceofstatus")
	public String deviceofstatus(Model model, HttpServletRequest request)
			throws Exception {
		AccountInfo accountInfoBean = Common.getAccountInfo(request);
		String resultData = (String) RedisUtil.get(accountInfoBean.getUser_name()+"_distribution_file_import_with_device_message");
		return resultData;
	}
	
	
	/**
	 * 功能说明:加载所有设备
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*@ResponseBody
	@RequestMapping("loadDeviceInfo")
	@Transactional(readOnly = false)
	public Object loadDeviceInfo(Model model, HttpServletRequest request)
			throws Exception {
		AccountInfoBean accountInfoBean = Common.getAccountInfo(request);
		try {
			// 线程异步处理
			RedisUtil.set(accountInfoBean.getUser_id()+"_load_all_device_num", "0");
			executorService.execute(new LoadAllOfDeviceInfoThread(deviceInfoService,accountInfoBean.getUser_id()));
			return DataCenterResult.ok();

		} catch (Exception e) {
			logger.error("", e);
			return DataCenterResult.build(500);
		}
	}*/
	
	
	/**
	 * 功能说明:查看所有加载设备总量
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*@ResponseBody
	@RequestMapping("lookDeviceInfo")
	@Transactional(readOnly = false)
	public String lookDeviceInfo(Model model, HttpServletRequest request)
			throws Exception {
		AccountInfoBean accountInfoBean = Common.getAccountInfo(request);
		try {
			int loadNum = 0;
			//设备总数
			int allNum = deviceInfoService.countDeviceInfo(null);
			//当前已加载的设备总数
			String loadCount = RedisUtil.get(accountInfoBean.getUser_id()+"_load_all_device_num");
			if(!StringUtils.isEmpty(loadCount)){
				loadNum = Integer.parseInt(loadCount);
			}
			return allNum+";"+loadNum;
		} catch (Exception e) {
			logger.error("", e);
			return "error";
		}
	}*/
	
	
	
	

	
	
	
	
	
	
	
	

}