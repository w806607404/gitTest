package com.scinan.iot.s6000.dao.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * RepairMaintainInfo对象.
 * @author kim
 *
 */
public class RepairMaintainInfo implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3386784124943728388L;

	/**
     * 主键
     */
    private Long id;
    
    /**
     * 用户ID
     */
    private Long user_id;
    
    /**
     * 设备ID
     */
    private String device_id;
    
    
    /**
     * 设备类型
     */
    private String type;
    
    
    /**
     *厂商ID
     */
    private String company_id;
    
    /**
     * 设备名称
     */
    private String device_name;
    
    
    /**
     * 主题
     */
    private String title;
    
    
    /**
     * 联系人名称
     */
    private String contacts_name;
    
    
    /**
     * 联系人电话
     */
    private String contacts_phone;
    
    
    /**
     * 维修保养详情
     */
    private String repair_details;
    
    
    /**
     * 操作时间
     */
    private Date create_time;
    
    /**
     * 修改时间
     */
    private Date update_time;
    
    
    /**
     * 状态 1 待处理   2完成
     */
    private String status;
    
    
    /**
     * 处理时间
     */
    private Date handle_time;
    
    
    /**
     * 处理时间
     */
    private String handle_date;
    
    
   
    /**
     * 维修金额
     */
    private BigDecimal repair_money;
    
    /**
     * 故障处理描述
     */
    private String  handle_desc;
    
    /**
     * 处理人名称
     */
    private String handle_man;
    
    
    /**
     * 保养过程
     */
    private String maintain_process;
    
    /**
     * 经销商
     */
    private String dealer;
    
    /**
     * 经销商名称 
     */
    private String dealer_name;




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContacts_name() {
		return contacts_name;
	}

	public void setContacts_name(String contacts_name) {
		this.contacts_name = contacts_name;
	}

	public String getContacts_phone() {
		return contacts_phone;
	}

	public void setContacts_phone(String contacts_phone) {
		this.contacts_phone = contacts_phone;
	}

	public String getRepair_details() {
		return repair_details;
	}

	public void setRepair_details(String repair_details) {
		this.repair_details = repair_details;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getHandle_time() {
		return handle_time;
	}

	public void setHandle_time(Date handle_time) {
		this.handle_time = handle_time;
	}

	public BigDecimal getRepair_money() {
		return repair_money;
	}

	public void setRepair_money(BigDecimal repair_money) {
		this.repair_money = repair_money;
	}

	public String getHandle_desc() {
		return handle_desc;
	}

	public void setHandle_desc(String handle_desc) {
		this.handle_desc = handle_desc;
	}

	public String getHandle_man() {
		return handle_man;
	}

	public void setHandle_man(String handle_man) {
		this.handle_man = handle_man;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getHandle_date() {
		return handle_date;
	}

	public void setHandle_date(String handle_date) {
		this.handle_date = handle_date;
	}

	public String getMaintain_process() {
		return maintain_process;
	}

	public void setMaintain_process(String maintain_process) {
		this.maintain_process = maintain_process;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public String getDealer_name() {
		return dealer_name;
	}

	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
	}

   

}
