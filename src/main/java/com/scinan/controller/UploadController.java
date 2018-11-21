package com.scinan.controller;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.scinan.base.config.ConfigureFile;
import com.scinan.bean.UploadBean;
import com.scinan.constants.Constants;
import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.spring.SpringUtil;
import com.scinan.utils.Common;
import com.scinan.utils.JsonUtils;
import com.scinan.utils.MD5HashUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;


/**
 * app版本更新
 * @author scinan 2015-12-09
 * @Email: scinan@scinan.com
 * @version 1.0v
 */
@Controller
@RequestMapping("/upload/")
public class UploadController extends BaseController {
	
	//private final static Properties prop = PropertiesUtils.getProperties();
	
	/**
	 * 
	 * @param type 1、应用文件apk ipa bin   2、公共图片 3、推送图片  4、设备分类中图片上传   5.html图标   6.跑马灯图片  7.菜谱图片  8.咨询图标
	 * @param response
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "file/{type}", method = RequestMethod.POST)
	public Object file(@PathVariable Integer type, HttpServletResponse response, HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
		try {
			UploadBean uploadBean = doProcess(type,response,file);
			response.getWriter().write(JsonUtils.objectToJson(uploadBean));
		} catch (Exception e) {
			try {
				response.getWriter().write(JsonUtils.objectToJson(new UploadBean(500,null, null, null)));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	private UploadBean doProcess(int type,HttpServletResponse response,MultipartFile file) throws Exception{
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String originalFilename = file.getOriginalFilename();
		byte[] bytes = file.getBytes();
		String path = "upload/";
		String uploadDir = Constants.BASE_FILE_UPLOAD_PATH + path;
		String url = null;
		String hashCode = null;
		Long length = null;
		switch (type) {
		case 1://应用文件apk ipa bin
			hashCode = MD5HashUtil.hashCode(bytes);		
			length = file.getSize();
			if (originalFilename.endsWith(".apk")) {
				uploadDir += "android/";
				path += "android/";
			} else if (originalFilename.endsWith(".bin")) {
				uploadDir += "bin/";
				path += "bin/";
			}
			break;
		case 2://公共图片
			uploadDir += "images/";
			path += "images/";
			break;
		case 3://推送图片
			uploadDir += "msg_push/images/";
			path += "msg_push/images/";
			break;
		case 4://设备分类中图片上传
			uploadDir += "app_device_type/images/";
			path += "app_device_type/images/";
			break;
		case 5://html 缩略图标
			uploadDir += "html/images/";
			path += "html/images/";
		case 6://跑马灯图标
			uploadDir += "horseracelamp/images/";
			path += "horseracelamp/images/";	
		case 7://跑马灯图标
			uploadDir += "food/images/";
			path += "food/images/";
		case 8://资讯图标
			uploadDir += "zixun/images/";
			path += "zixun/images/";
		default:
			uploadDir += "images/";
			path += "images/";
			type = 4;
			break;
		}
		
		File dirPath = new File(uploadDir);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		String[] fileNameArr = originalFilename.split("\\.");
		originalFilename = new StringBuffer(UUID.randomUUID().toString()).append(".").append(fileNameArr[fileNameArr.length-1]).toString();
		File uploadedFile = new File(uploadDir + originalFilename);
		FileCopyUtils.copy(bytes, uploadedFile);
		url = ConfigureFile.getInstance().getProperty(Constants.DOWNLOAD_URL) + "/" + path + originalFilename;
		
//		if(type == 2 || type == 3 ||type == 4 || type == 5 || type == 6 || type == 7){
//			return new UploadBean(200,url, null, null);
//		}else{
			return new UploadBean(200,url, length, hashCode);
//		}
	}
	
	public static String doProcessStaticHtml(HttpServletRequest request,String fileName){
		try {
			FreeMarkerConfigurer freeMarkerConfigurer = (FreeMarkerConfigurer)SpringUtil.getBean("freemarkerConfigurer");
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			String companyId = "";
			Map<String, String> params = Common.getRequestParameters(request);
			
			String uuid = null;
			String detail_url = params.get("detail_url");
			String content = params.get("content");
			String title = params.get("title");
			String thumb_url = params.get("thumb_url");
			if(StringUtils.isEmpty(content) || StringUtils.isEmpty(title)){
				return null;
			}
			
			Date timeDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String timedate = dateFormat.format(timeDate);
			
			if(StringUtils.isNotEmpty(detail_url)){
				
				uuid  = detail_url.substring(detail_url.lastIndexOf("/"));
				if(uuid.split("_").length != 2){
					return null;
				}
				uuid = uuid.split("_")[1];
				if(uuid.split("\\.").length != 2){
					return null;
				}
				uuid = uuid.split("\\.")[0];
			}else{
				uuid = UUID.randomUUID().toString();
				detail_url = ConfigureFile.getInstance().getProperty(Constants.DOWNLOAD_URL) + "/upload/html/" +  companyId + "/"+ (StringUtils.isNotEmpty(fileName)?fileName:"article")  +"/detail_"  + uuid +".html";
			}
			
			Configuration con = freeMarkerConfigurer.getConfiguration();
			Template template = con.getTemplate((StringUtils.isNotEmpty(fileName)?fileName:"article") + ".ftl");
			
			Map<String, Object>	root = new HashMap<String, Object>();
			root.put("title", StringUtils.isEmpty(title) ? "" : title);
			root.put("thumb_url", StringUtils.isEmpty(thumb_url) ? "" : thumb_url);
			root.put("timedate", StringUtils.isEmpty(timedate) ? "" : timedate);
			root.put("content", content);
			
			String prefix_url = Constants.BASE_FILE_UPLOAD_PATH + "upload/html/" + companyId + "/" + (StringUtils.isNotEmpty(fileName)?fileName:"article") + "/";
			
			// 附件存放服务器路径
			File f = new File(prefix_url);
			if(!f.exists()) {
				f.mkdirs();
			}
			
			Writer out = new FileWriter(prefix_url + "detail_"  + uuid + ".html");
			template.process(root, out);
			out.flush();
			out.close();
			
			return detail_url;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static String doProcessStaticYajingHtml(HttpServletRequest request,String fileName){
		try {
			FreeMarkerConfigurer freeMarkerConfigurer = (FreeMarkerConfigurer)SpringUtil.getBean("freemarkerConfigurer");
			AccountInfo accountInfoBean = Common.getAccountInfo(request);
			
			Map<String, String> params = Common.getRequestParameters(request);
			String uuid = null;
			String detail_url = params.get("detail_url");
			String content = params.get("content");
			String title = params.get("title");
			String thumb_url = params.get("icon_url");
			if(StringUtils.isEmpty(content) || StringUtils.isEmpty(title)){
				return null;
			}
			
			Date timeDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String timedate = dateFormat.format(timeDate);
			
			if(StringUtils.isNotEmpty(detail_url)){
				
				uuid  = detail_url.substring(detail_url.lastIndexOf("/"));
				if(uuid.split("_").length != 2){
					return null;
				}
				uuid = uuid.split("_")[1];
				if(uuid.split("\\.").length != 2){
					return null;
				}
				uuid = uuid.split("\\.")[0];
			}else{
				uuid = UUID.randomUUID().toString();
				//detail_url = ConfigureFile.getInstance().getProperty(Constants.DOWNLOAD_URL) + "/upload/html/" +  accountInfoBean.getOrganization_id() + "/"+ (StringUtils.isNotEmpty(fileName)?fileName:"article")  +"/detail_"  + uuid +".html";
			}
			
			Configuration con = freeMarkerConfigurer.getConfiguration();
			Template template = con.getTemplate((StringUtils.isNotEmpty(fileName)?fileName:"article") + ".ftl");
			
			Map<String, Object>	root = new HashMap<String, Object>();
			root.put("title", StringUtils.isEmpty(title) ? "" : title);
			root.put("thumb_url", StringUtils.isEmpty(thumb_url) ? "" : thumb_url);
			root.put("timedate", StringUtils.isEmpty(timedate) ? "" : timedate);
			root.put("content", content);
			
			String prefix_url = "";//Constants.BASE_FILE_UPLOAD_PATH + "upload/html/" + accountInfoBean.getOrganization_id() + "/" + (StringUtils.isNotEmpty(fileName)?fileName:"article") + "/";
			
			// 附件存放服务器路径
			File f = new File(prefix_url);
			if(!f.exists()) {
				f.mkdirs();
			}
			
			Writer out = new FileWriter(prefix_url + "detail_"  + uuid + ".html");
			template.process(root, out);
			out.flush();
			out.close();
			
			return detail_url;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static String doProcessStaticHtml(HttpServletRequest request){
		return doProcessStaticHtml(request,null);
	}
	
}