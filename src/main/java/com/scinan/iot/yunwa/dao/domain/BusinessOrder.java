package com.scinan.iot.yunwa.dao.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * BusinessOrder对象.
 * @author yangkun
 *
 */
public class BusinessOrder implements Serializable{
	private static final long serialVersionUID = 8251601003168206855L;


	/**
     * 业务订单号.
     */
    private String order_id;
    
    private String device_id;
    
    /**
     * .
     */
    private Long app_key;
    
    /**
     * 厂商ID.
     */
    private String company_id;
    
    /**
     * 用户ID.
     */
    private Long user_id;
    
    /**
     * 业务类型：1预约安装；2现金充值.
     */
    private Integer order_type;
    
    /**
     * 预留业务订单状态.
     */
    private Integer order_status;
    
    /**
     * 订单备注.
     */
    private String order_note;
    
    /**
     * 订单金额.
     */
    private String order_amount;
    
    /**
     * 充值套餐ID.
     */
    private Integer recharge_package_id;
    
    /**
     * 支付方式 1:支付宝.
     */
    private Integer pay_type;
    
    /**
     * 支付状态 1.待支付
     */
    private Integer pay_status;
    
    /**
     * 订单支付时间.
     */
    private Date pay_time;
    
    /**
     * 分享红包（整包）ID.
     */
    private Long red_envelope_group_id;
    
    /**
     * 红包分享状态 0:未分享.
     */
    private Integer red_envelope_group_status;
    
    /**
     * 订单生成时间.
     */
    private Date create_time;
    
    /**
     * 订单更新时间.
     */
    private Date update_time;
    
    /**
     * 分享红包失效时间.
     */
    private Date share_overdue_time;
    
    /**
     * 异常状态 0.
     */
    private Integer exception_status;
    /**
     * 赠送金额.
     */
    private Long order_present;
    
    private String user_name;
    
    private Long order_amounts;
    
    private Long order_presents;
    
    private String user_mobile;
    
    private Date usercreate_time;
    
    private String address;
    
    private String order_amounts_str;
    
    private String start_time;
    
    private String end_time;
    
    private String user_path;
    
    private String dealer_name;
    

    public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
    
    public String getOrder_id() {
        return this.order_id;
    }	
  
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    
    public Long getApp_key() {
        return this.app_key;
    }	
  
    public void setApp_key(Long app_key) {
        this.app_key = app_key;
    }
    
    public String getCompany_id() {
        return this.company_id;
    }	
  
    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
    
    public Long getUser_id() {
        return this.user_id;
    }	
  
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    
    public Integer getOrder_type() {
        return this.order_type;
    }	
  
    public void setOrder_type(Integer order_type) {
        this.order_type = order_type;
    }
    
    public Integer getOrder_status() {
        return this.order_status;
    }	
  
    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }
    
    public String getOrder_note() {
        return this.order_note;
    }	
  
    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }
    
    public String getOrder_amount() {
        return this.order_amount;
    }	
  
    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }
    
    public Integer getRecharge_package_id() {
        return this.recharge_package_id;
    }	
  
    public void setRecharge_package_id(Integer recharge_package_id) {
        this.recharge_package_id = recharge_package_id;
    }
    
    public Integer getPay_type() {
        return this.pay_type;
    }	
  
    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }
    
    public Integer getPay_status() {
        return this.pay_status;
    }	
  
    public void setPay_status(Integer pay_status) {
        this.pay_status = pay_status;
    }
    
    public Date getPay_time() {
        return this.pay_time;
    }	
  
    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }
    
    public Long getRed_envelope_group_id() {
        return this.red_envelope_group_id;
    }	
  
    public void setRed_envelope_group_id(Long red_envelope_group_id) {
        this.red_envelope_group_id = red_envelope_group_id;
    }
    
    public Integer getRed_envelope_group_status() {
        return this.red_envelope_group_status;
    }	
  
    public void setRed_envelope_group_status(Integer red_envelope_group_status) {
        this.red_envelope_group_status = red_envelope_group_status;
    }
    
    public Date getCreate_time() {
        return this.create_time;
    }	
  
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    
    public Date getUpdate_time() {
        return this.update_time;
    }	
  
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    
    public Date getShare_overdue_time() {
        return this.share_overdue_time;
    }	
  
    public void setShare_overdue_time(Date share_overdue_time) {
        this.share_overdue_time = share_overdue_time;
    }
    
    public Integer getException_status() {
        return this.exception_status;
    }	
  
    public void setException_status(Integer exception_status) {
        this.exception_status = exception_status;
    }

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Long getOrder_present() {
		return order_present;
	}

	public void setOrder_present(Long order_present) {
		this.order_present = order_present;
	}

	public Long getOrder_amounts() {
		return order_amounts;
	}

	public void setOrder_amounts(Long order_amounts) {
		this.order_amounts = order_amounts;
	}

	public Long getOrder_presents() {
		return order_presents;
	}

	public void setOrder_presents(Long order_presents) {
		this.order_presents = order_presents;
	}

	public String getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}

	public Date getUsercreate_time() {
		return usercreate_time;
	}

	public void setUsercreate_time(Date usercreate_time) {
		this.usercreate_time = usercreate_time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrder_amounts_str() {
		return order_amounts_str;
	}

	public void setOrder_amounts_str(String order_amounts_str) {
		this.order_amounts_str = order_amounts_str;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getUser_path() {
		return user_path;
	}

	public void setUser_path(String user_path) {
		this.user_path = user_path;
	}

	public String getDealer_name() {
		return dealer_name;
	}

	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
	}

	

}
