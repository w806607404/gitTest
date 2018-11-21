package com.scinan.bean;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 
 * @project datacenter
 * @class com.scinan.bean.DataCenterResult
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月6日
 * @description
 */
public class DataCenterResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static DataCenterResult build(Integer status, String msg, Object data) {
        return new DataCenterResult(status, msg, data);
    }

    public static DataCenterResult ok(Object data) {
        return new DataCenterResult(data);
    }

    public static DataCenterResult ok() {
        return new DataCenterResult(null);
    }
    
    public static DataCenterResult fail() {
        return DataCenterResult.build(500);
    }


    public DataCenterResult() {

    }
    
    public static DataCenterResult build(Integer status){
    	 return new DataCenterResult(status, null, null);
    }

    public static DataCenterResult build(Integer status, String msg) {
        return new DataCenterResult(status, msg, null);
    }

    public DataCenterResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public DataCenterResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     * 
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static DataCenterResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, DataCenterResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static DataCenterResult format(String json) {
        try {
            return MAPPER.readValue(json, DataCenterResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static DataCenterResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
