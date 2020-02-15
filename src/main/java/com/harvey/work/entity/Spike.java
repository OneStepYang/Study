package com.harvey.work.entity;

import java.io.Serializable;
import java.util.Date;

public class Spike implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8409724272103366184L;
	private String userId;
    private String model;
    private String vin;
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
