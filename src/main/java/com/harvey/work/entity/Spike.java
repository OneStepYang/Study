package com.harvey.work.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

public class Spike implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8409724272103366184L;
	@Min(value = 10000,message = "userId必须大于10000")
	private String userId;

    private String model;
	@NotNull(message = "vin号不能为空！")
    private String vin;
	@Past(message = "生成时间必须小于当前时间！")
    private Date createTime;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}
