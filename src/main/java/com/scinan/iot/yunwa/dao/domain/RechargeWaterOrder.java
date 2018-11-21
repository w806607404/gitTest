package com.scinan.iot.yunwa.dao.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * RechargeWaterOrder对象.
 * @author yangkun
 *
 */
public class RechargeWaterOrder implements Serializable{
	private static final long serialVersionUID = 6552299123880269918L;

	private String card_no; //卡片， NFC
    
    public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	/**
     * .
     */
    private Long id;
    
    /**
     * .
     */
    private Long app_key;
    
    /**
     * 厂商ID.
     */
    private String company_id;
    
    /**
     * 充水记录号.
     */
    private String order_id;
    
    /**
     * 用户ID.
     */
    private Long user_id;
    
    /**
     * 用户token.
     */
    private String user_token;
    
    /**
     * 设备ID.
     */
    private String device_id;
    
    /**
     * 产品编号(机器号).
     */
    private String product_id;
    
    /**
     * 充水量.
     */
    private Integer volume;
    
    /**
     * 充水总金额.
     */
    private BigDecimal amount;
    
    /**
     * 消费支付金额.
     */
    private BigDecimal expense_pay;
    
    /**
     * 消费赠送金额.
     */
    private BigDecimal expense_present;
    
    /**
     * 消费红包ID.
     */
    private Long red_envelope_id;
    
    /**
     * 消费红包金额.
     */
    private BigDecimal red_envelope_amount;
    
    /**
     * 充水时间.
     */
    private Date create_time;
    
    /**
     * 更新时间.
     */
    private Date update_time;
    
    /**
     * 状态 0:未完成, 1:充水成功, 2:充水失败
     */
    public static int STATUS_SUCCESS = 1;
    public static int STATUS_FAIL = 2;
    private Integer status;
    
    /**
     * 数据推送到雅旌平台的状态 0:未推送.
     */
    private Integer send_status;
    
    /**
     * 数据推送到雅旌平台的时间.
     */
    private Date send_time;

    //订单类型：1用户充水；2平台充水；3扫码用水；4NFC出水
    private Integer type;
    public static int TYPE3_USE_WATER = 3;
    public static int TYPE4_NFC_WATER = 4;
    //容量单位：1升；2天；3小时；4桶；5百毫升
    private Integer volume_type;
    public static int VOLUME_UNIT1_LITRE = 1;
    public static int VOLUME_UNIT2_DAY = 2;
    public static int VOLUME_UNIT3_HOUR = 3;
    public static int VOLUME_UNIT4_TONG = 4;
    public static int VOLUME_UNIT5_100ML = 5;
    
    
    private String start_time;
    
    private String end_time;
    
    
    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getVolume_type() {
		return volume_type;
	}

	public void setVolume_type(Integer volume_type) {
		this.volume_type = volume_type;
	}

	public Long getId() {
        return this.id;
    }	
  
    public void setId(Long id) {
        this.id = id;
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
    
    public String getOrder_id() {
        return this.order_id;
    }	
  
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    
    public Long getUser_id() {
        return this.user_id;
    }	
  
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    
    public String getUser_token() {
        return this.user_token;
    }	
  
    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }
    
    public String getDevice_id() {
        return this.device_id;
    }	
  
    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
    
    public String getProduct_id() {
        return this.product_id;
    }	
  
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    
    public Integer getVolume() {
        return this.volume;
    }	
  
    public void setVolume(Integer volume) {
        this.volume = volume;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }	
  
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public BigDecimal getExpense_pay() {
        return this.expense_pay;
    }	
  
    public void setExpense_pay(BigDecimal expense_pay) {
        this.expense_pay = expense_pay;
    }
    
    public BigDecimal getExpense_present() {
        return this.expense_present;
    }	
  
    public void setExpense_present(BigDecimal expense_present) {
        this.expense_present = expense_present;
    }
    
    public Long getRed_envelope_id() {
        return this.red_envelope_id;
    }	
  
    public void setRed_envelope_id(Long red_envelope_id) {
        this.red_envelope_id = red_envelope_id;
    }
    
    public BigDecimal getRed_envelope_amount() {
        return this.red_envelope_amount;
    }	
  
    public void setRed_envelope_amount(BigDecimal red_envelope_amount) {
        this.red_envelope_amount = red_envelope_amount;
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
    
    public Integer getStatus() {
        return this.status;
    }	
  
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getSend_status() {
        return this.send_status;
    }	
  
    public void setSend_status(Integer send_status) {
        this.send_status = send_status;
    }
    
    public Date getSend_time() {
        return this.send_time;
    }	
  
    public void setSend_time(Date send_time) {
        this.send_time = send_time;
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

}
