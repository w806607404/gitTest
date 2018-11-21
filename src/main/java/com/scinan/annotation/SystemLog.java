package com.scinan.annotation;  
  
import java.lang.annotation.*;  
  
/** 
 * 
 * @project datacenter
 * @class com.scinan.annotation.SystemLog
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月11日
 * @description
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public  @interface SystemLog {  
  
	String module()  default "";  //模块名称 系统管理-用户管理－列表页面
	String methods()  default "";  //新增用户
    String description()  default "";  //执行状态
  
  
}  
  
