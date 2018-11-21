package com.scinan.iot.s8000log.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class DeviceStartTimeLog implements Serializable {


    private static final long serialVersionUID = 8335735867524142714L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 设备id
     */
    private String device_id;

    /**
     * 开启时间
     */
    private Long start_time;

    /**
     * 关闭时间
     */
    private Long close_time;

    /**
     * 单次开启时长
     */
    private Long online_time;

    /**
     * 厂商id
     */
    private String company_id;

    /**
     * 厂商类型
     */
    private String type;

    /**
     * 创建时间
     */
    private Date create_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getOnline_time() {
        return online_time;
    }

    public void setOnline_time(Long online_time) {
        this.online_time = online_time;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getClose_time() {
        return close_time;
    }

    public void setClose_time(Long close_time) {
        this.close_time = close_time;
    }

    @Override
    public String toString() {
        return "DeviceStartTimeLog{" +
                "id=" + id +
                ", device_id='" + device_id + '\'' +
                ", start_time=" + start_time +
                ", close_time=" + close_time +
                ", online_time=" + online_time +
                ", company_id='" + company_id + '\'' +
                ", type='" + type + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}
