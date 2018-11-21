package com.scinan.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.scinan.iot.ddeddo.dao.domain.AccountInfo;
import com.scinan.iot.s6000.dao.domain.DeviceTypeBean;
import com.scinan.iot.s9000.dao.domain.CityBean;
import com.scinan.iot.s9000.dao.domain.CountryBean;
import com.scinan.iot.s9000.dao.domain.ProvinceBean;
import com.scinan.iot.service.AccountInfoService;
import com.scinan.iot.service.DeviceTypeService;
import com.scinan.spring.SpringUtil;

import net.sf.json.JSONObject;

public class Common {
	
	private static final Logger logger = Logger.getLogger(Common.class);
	public static final String REDIS_KEY_DEVICE_TYPE = "_TYPE";
	/**
	 * 获取用户信息对象
	 * @param request
	 * @return
	 */
	public static AccountInfo getAccountInfo(HttpServletRequest request){
		String token = CookieUtils.getUserName(request, "DISTRIBUTION_DC_TOKEN");
		AccountInfoService accountInfoService = (AccountInfoService) SpringUtil.getBean("accountInfoService");
		if(StringUtil.isNull(token))return null;
		String json = (String)RedisUtil.get("REDIS_USER_SESSION_" + token);
		if(StringUtil.isNull(json))return null;
		AccountInfo accountInfoBean = JsonUtils.jsonToPojo(json, AccountInfo.class);
		if(accountInfoBean == null){
			return null;
		}
		if(accountInfoBean.getRole_type() == null || accountInfoBean.getRole_type() == 1 || accountInfoBean.getRole_type() == 0 ){
			accountInfoBean.setAccount_type(AccountInfo.ACCOUNT_TYPE_ADMIN);
		}else if(accountInfoBean.getRole_type() == 2){
			accountInfoBean.setAccount_type(AccountInfo.ACCOUNT_TYPE_COMMON);
		}else{
			accountInfoBean.setAccount_type(AccountInfo.ACCOUNT_TYPE_OTHER);
		}
		return accountInfoBean;
	}
	
	
	
	/**
	 * 判断是否登录
	 * @param request
	 * @return
	 */
	public static boolean isLogin(HttpServletRequest request){
		String token = CookieUtils.getCookieValue(request, "DISTRIBUTION_DC_TOKEN");
		String json = (String)RedisUtil.getRedis().get("REDIS_USER_SESSION_" + token);
		return StringUtils.isNotEmpty(json)? true : false;
	}
	
	/**
	 * 获取前端提交参数Map
	 * @param request
	 * @return
	 */
	public static Map<String, String> getRequestParameters(HttpServletRequest request){
		Map<String, String> parameterMap = new HashMap<String, String>();
		Map<String, String[]> tempMap = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = tempMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String[]> tempEntry = (Map.Entry<String, String[]>) it.next();
			parameterMap.put(tempEntry.getKey(), tempEntry.getValue()[0]);
		}
		return parameterMap;
	}
	
	/**
	 * 字符串转list
	 * @param str
	 * @param split
	 * @return
	 */
	public static List<String> strSplitToList(String str,String split){
		List<String> list = new ArrayList<String>();
		String[] strArr = str.split(split);
		for(int i = 0;i<strArr.length;i++){
			list.add(strArr[i]);
		}
		return list;
		
	}
	
	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 将Map形式的键值对中的值转换为泛型形参给出的类中的属性值 t一般代表pojo类
	 * 
	 * @descript
	 * @param t
	 * @param params
	 * @author scinan
	 * @date 2015年3月29日
	 * @version 1.0
	 */
	public static <T extends Object> T flushObject(T t, Map<String, Object> params) {
		if (params == null || t == null)
			return t;

		Class<?> clazz = t.getClass();
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				Field[] fields = clazz.getDeclaredFields();

				for (int i = 0; i < fields.length; i++) {
					String name = fields[i].getName(); // 获取属性的名字
					Object value = params.get(name);
					if (value != null && !"".equals(value)) {
						// 注意下面这句，不设置true的话，不能修改private类型变量的值
						fields[i].setAccessible(true);
						fields[i].set(t, value);
					}
				}
			} catch (Exception e) {
			}

		}
		return t;
	}
	
	
	 /**
     * <一句话功能简述>通过基站获取经纬度
     * <功能详细描述>
     * @param lac 位置区码
     * @param cid 小区识别码
     * @return strReturn
     * @see [类、类#方法、类#成员]
     */
    public static String getCarCoordinateInfo(int lac,int cid)
    {
        String jsonInfo = getCoordinateInfo(lac,cid);
        try{
            if(!isEmpty(jsonInfo)){
                String[] val = jsonInfo.split(",");
                return validateIsEmpty(val[1])+","+validateIsEmpty(val[2])+","+validateIsEmpty(val[4]);
           }
        }catch(Exception ex){
        	logger.error("CarCoordinateOfAddressQuartzClock getCarCoordinateAddressInfo :" + ex.getMessage());
        }
        return null;
    }
    
    
    
  
    
    /**
     * <一句话功能简述>根据坐标获取对应的详细地址
     * <功能详细描述>
     * @param coordinate 坐标
     * @return 
     * @see [类、类#方法、类#成员]
     */
      public static String getCoordinateInfo(int lac,int cid) {
          //String url = "http://api.cellid.cn/cellid.php?lac="+lac+"&cell_id="+cid+"&token=95dba0a4fc7b2196248fe32566682d76";
          String url = "http://www.cellocation.com/cell/?coord=bd09&output=csv&mcc=460&mnc=0&lac="+lac+"&ci="+cid;
          HttpClient httpClient = new HttpClient();
          PostMethod post = new PostMethod(url.toString());
          try {
               int result = httpClient.executeMethod(post);
               if(result == HttpStatus.SC_OK) {
                   String str = post.getResponseBodyAsString();
                   return str;
               }
           } catch (HttpException e) {
               logger.error("com.scinan.iot GetAddressUtil" + e.getMessage() );
           } catch (IOException e) {
               logger.error("com.scinan.iot GetAddressUtil" + e.getMessage());
           }
         return null;
      }
   
    
      
      
      /**
       * <一句话功能简述>通过经纬度获取对应的详细地址
       * <功能详细描述>
       * @param coordinate 经纬度信息
       * @return strReturn
       * @see [类、类#方法、类#成员]
       */
      public static String getCarCoordinateAddressInfo(String coordinate)
      {
          JSONObject jsonInfo = getCoordinateOfAddressInfo(coordinate);
          try{
              JSONObject bbn = JSONObject.fromObject(jsonInfo.getString("result"));
              
              if(null!=bbn){
                  return bbn.getString("formatted_address");
              }
          }catch(Exception ex){
              logger.error("CarCoordinateOfAddressQuartzClock getCarCoordinateAddressInfo :  " + ex.getMessage());
          }
          return null;
      }
      
      
      /**
       * <一句话功能简述>根据坐标获取对应的详细地址
       * <功能详细描述>
       * @param coordinate 坐标
       * @return 
       * @see [类、类#方法、类#成员]
       */
        public static JSONObject getCoordinateOfAddressInfo(String coordinate) {
            String url = "http://api.map.baidu.com/geocoder/v2/?ak=RjNCBUhXKrCrA26RG3XySysC&callback=renderReverse&output=json&pois=1&location="+coordinate;
            String strReturn = "";
            HttpClient httpClient = new HttpClient();
            PostMethod post = new PostMethod(url.toString());
            try {
                 int result = httpClient.executeMethod(post);
                 if(result == HttpStatus.SC_OK) {
                     String str = post.getResponseBodyAsString();
                     int startNum = str.indexOf("{");
                     int endNum = str.lastIndexOf(")");
                     strReturn = str.substring(startNum, endNum);
                     JSONObject obj = JSONObject.fromObject(strReturn);
                     return obj;
                 }
             } catch (HttpException e) {
                 logger.error("com.scinan.lingdong.util GetAddressUtil" + e.getMessage());
             } catch (IOException e) {
                 logger.error("com.scinan.lingdong.util GetAddressUtil" + e.getMessage());
             }
           return null;
        }
        
        public static String validateIsEmpty(String value){
            if(null == value || "".equals(value) || "null".equals(value))
                return "";
            else 
                return value;
        }
	
        
        public static Long generate_user_digit() {
    		long userDigitNo = RedisUtil.incr("user_incr_key", 1);
    		return userDigitNo;
    	}
        
        
        public static String getDoubleValue(double f) {  
        	DecimalFormat df = new DecimalFormat("0.00");  
            BigDecimal bg = new BigDecimal(f);  
            double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
            return String.valueOf(df.format(f1));  
        } 
        
        
        static  Map<String, String> country = new HashMap<String, String>();
        public static void putCountry(List<CountryBean> list){
      	  for(CountryBean b :list ){
      		  country.put(b.getCountry_code(), b.getName_cn());
      	  }
        }
        
        /**
         * 功能说明:获取国家名称信息
         * @param country_code
         * @return country_name
         */
        public static String getCountryName(String code){
      	  return country.get(code);
        }
        
        
        /**
         * 功能说明:获取省份名称信息
         * @param province_code
         * @return province_name
         */
        /*public static Map<String,String> getProvinceMap(){
      	  String json = (String)RedisUtil.getRedis().get("COMMON_PROVINCE_LIST");
      	  Map<String,String> map = new HashMap<String,String>();
      	  if(!StringUtil.isNull(json)){
      		  net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(json);
      		  if(null!=jsonArray){
      			  for(int i=0;i<jsonArray.size();i++){
      				  net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject)jsonArray.get(i);
      				  map.put(jsonObject.getString("province_code"), jsonObject.getString("name_cn"));
      			  }
      		  }
      	  }
      	  return map;
        }*/
        
        
        
        /**
         * 功能说明:获取省份名称信息
         * @param province_code
         * @return province_name
         */
        static  Map<String, String> province = new HashMap<String, String>();
        public static void putProvince(List<ProvinceBean> list){
      	  for(ProvinceBean b :list ){
      		  province.put(b.getProvince_code(), b.getName_cn());
      	  }
        }
        
        /**
         * 功能说明:获取省份名称信息
         * @param province_code
         * @return province_name
         */
        public static String getProvinceName(String code){
      	  return province.get(code);
        }
        
        
        /**
         * 功能说明:获取地市名称信息
         * @param city_code
         * @return city_name
         */
        /*public static Map<String,String> getCityMap(){
      	  String json = (String)RedisUtil.getRedis().get("COMMON_CITY_LIST");
      	  Map<String,String> map = new HashMap<String,String>();
      	  if(!StringUtil.isNull(json)){
      		  net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(json);
      		  if(null!=jsonArray){
      			  for(int i=0;i<jsonArray.size();i++){
      				  net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject)jsonArray.get(i);
      				  map.put(jsonObject.getString("city_code"), jsonObject.getString("name_cn"));
      			  }
      		  }
      	  }
      	  return map;
        }*/
        
        
        /**
         * 功能说明:获取地市名称信息
         * @param city_code
         * @return city_name
         */
        static  Map<String, String> city = new HashMap<String, String>();
        public static void putCity(List<CityBean> list){
      	  for(CityBean b :list ){
      		  city.put(b.getCity_code(), b.getName_cn());
      	  }
        }
        
        /**
         * 功能说明:获取地市名称信息
         * @param city_code
         * @return city_name
         */
        public static String getCityName(String code){
      	  return city.get(code);
        }
        
        public static String getDeviceType(String device_id) {
    		String type = "1";
    		String key = device_id + REDIS_KEY_DEVICE_TYPE;
    		String value = RedisUtil.get(key);
    		if(value != null && !"".equals(value)) {
    			try {
    				String[] tmp = value.split(",");
    				type = tmp[1];
    			} catch (Exception e) {
    				logger.error("device type data error : " + value);
    			}
    		} 
    		return type;
    	}
        
        public static String getDeviceName(String device_id) {
        	String type = getDeviceType(device_id);
        	DeviceTypeService deviceTypeService = (DeviceTypeService)SpringUtil.getBean("deviceTypeService");
        	Map<String, Object> map =  new HashMap<String, Object>();
        	map.put("type", type);
        	map.put("company_id", "10D5");
        	DeviceTypeBean bean = deviceTypeService.fetchByTypeAndCompanyId(map);
        	if(bean != null){
        		return bean.getDevice_name();
        	}
        	return "";
        }
        
        public static DeviceTypeBean getDeviceTypeBean(String device_id) {
        	String type = getDeviceType(device_id);
        	DeviceTypeService deviceTypeService = (DeviceTypeService)SpringUtil.getBean("deviceTypeService");
        	Map<String, Object> map =  new HashMap<String, Object>();
        	map.put("type", type);
        	map.put("company_id", "10D5");
        	return deviceTypeService.fetchByTypeAndCompanyId(map);
        }
        
}
