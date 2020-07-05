package com.harvey.work.mapper;


import org.apache.ibatis.annotations.*;

import com.harvey.work.entity.Spike;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
	@Insert("insert into tm_Spike_record(user_Id,Model,vin,Create_time) values(#{userId},#{model},#{vin},#{createTime, jdbcType=TIMESTAMP})")
	void insertRecord(Spike spike);
	@Select("select * from tm_Spike_record")
	@Results(id = "spike",value = {
			@Result(property = "id",column = "id",id = true)
	})
	List selectSpike();
}
