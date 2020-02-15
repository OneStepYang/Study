package com.harvey.work.mapper;


import org.apache.ibatis.annotations.Insert;

import com.harvey.work.entity.Spike;

public interface UserMapper {
	@Insert("insert into tm_Spike_record(user_Id,Model,vin,Create_time) values(#{userId},#{model},#{vin},#{createTime, jdbcType=TIMESTAMP})")
	void insertRecord(Spike spike);
}
