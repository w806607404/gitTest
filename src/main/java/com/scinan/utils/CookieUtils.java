package com.scinan.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @project datacenter
 * @class com.scinan.utils.CookieUtils
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月10日
 * @description
 */
public final class CookieUtils {

    /**
     * 得到Cookie的值, 不编码
     * 
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * 得到Cookie的值,
     * 
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(cookieList[i].getValue(), "UTF-8");
                    } else {
                        retValue = cookieList[i].getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 得到Cookie的值,
     * 
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    retValue = URLDecoder.decode(cookieList[i].getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
        	 e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     * 设置Cookie的值 在指定时间内生效,但不编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }

    /**
     * 设置Cookie的值 不设置生效时间,但编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数(指定编码)
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxage, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, encodeString);
    }

    /**
     * 删除Cookie带cookie域名
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
            String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, false);
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     * 
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
            String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request) {// 设置域名的cookie
            	String domainName = getDomainName(request);
            	System.out.println(domainName);
                if (!"localhost".equals(domainName)) {
                	cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     * 
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
            String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request) {// 设置域名的cookie
            	String domainName = getDomainName(request);
            	System.out.println(domainName);
                if (!"localhost".equals(domainName)) {
                	cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }

    /**
     * 得到cookie的域名
     */
    private static final String getDomainName(HttpServletRequest request) {
        String domainName = null;

        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }
    
    
    /** 
     * 清空所有cookie 
     */  
    public static void clearCookie(HttpServletRequest request,HttpServletResponse response, String path) {  
      Cookie[] cookies = request.getCookies();  
      try  
      {  
           for(int i=0;i<cookies.length;i++)    
           {  
            //System.out.println(cookies[i].getName() + ":" + cookies[i].getValue());  
            Cookie cookie = new Cookie(cookies[i].getName(), null);  
            cookie.setMaxAge(0);  
            cookie.setPath(path);//根据你创建cookie的路径进行填写      
            response.addCookie(cookie);  
           }  
      }catch(Exception ex)  
      {  
           System.out.println("清空Cookies发生异常！");  
      }   
       
    }  

    
    /** 
     * 清空所有cookie 
     */  
    public static void clearCookieName(HttpServletRequest request,HttpServletResponse response, String path) {  
      Cookie[] cookies = request.getCookies();  
      try  
      {  
           for(int i=0;i<cookies.length;i++)    
           {  
            //System.out.println(cookies[i].getName() + ":" + cookies[i].getValue());  
            Cookie cookie = new Cookie(cookies[i].getName(), null);  
            cookie.setMaxAge(0);  
            cookie.setPath(path);//根据你创建cookie的路径进行填写      
            response.addCookie(cookie);  
           }  
      }catch(Exception ex)  
      {  
           System.out.println("清空Cookies发生异常！");  
      }   
    }  
    
    
    
    
    /** 
     * 清空所有cookie 
     */  
    public static void clearAllCookies(HttpServletRequest request,HttpServletResponse response) {  
      Cookie[] cookies = request.getCookies();  
      try  
      {  
    	  for(Cookie cookie:cookies){
	    	  cookie.setMaxAge(0);
	    	  cookie.setPath("/");
	    	  response.addCookie(cookie);
    	  }
      }catch(Exception ex)  
      {  
           System.out.println("清空Cookies发生异常！");  
      }   
    }  
    
    
    
    /** 
     * 清空当前cookie 
     */  
    public static void delCookies(HttpServletRequest request,HttpServletResponse response,String cookieName,String token) {  
      try  
      {  
    	  if (cookieName != null) {
    		   Cookie cookie = getCookie(request,response,cookieName);//getCookie()方法在下面
    		   if (cookie != null) {
    			   javax.servlet.http.Cookie cc=new javax.servlet.http.Cookie(cookieName,null);
    			   cc.setMaxAge(0);   //一天时间
    			   cc.setPath("/");
    			   response.addCookie(cc);
    		   }
    		}
    		
      }catch(Exception ex)  
      {  
           System.out.println("清空Cookies发生异常！");  
      }   
    }  
    
    
    private static Cookie getCookie(HttpServletRequest request,HttpServletResponse response,String cookieName) {
    	  Cookie[] cookies = request.getCookies();
    	  Cookie cookie = null;
    	  try {
    	   if (cookies != null && cookies.length > 0) {
    	    for (int i = 0; i < cookies.length; i++) {
    	     cookie = cookies[i];
    	     if (cookie.getName().equals(cookieName)) {
    	    	 javax.servlet.http.Cookie cc=new javax.servlet.http.Cookie(cookieName,null);
  			     cc.setMaxAge(-1);   //一天时间
  			     cc.setPath("/");
  			     response.addCookie(cc);
    	         return cookie;
    	     }
    	    }
    	   }
    	  } catch (Exception e) {
    	   e.printStackTrace();
    	  }
    	  return null;
     }
    
    
    
    public static String getUserName(HttpServletRequest request,String userName){  
        Cookie[] cookies = request.getCookies();  
        String username = "";  
        if(cookies!=null){  
            for (int i = 0; i < cookies.length; i++)   
            {  
               Cookie c = cookies[i];       
               if(c.getName().equalsIgnoreCase(userName))  
               {  
                   username = c.getValue();  
               }  
            }  
        }  
        return username;  
    }  
    
    
    
    
    
}
