package com.harvey.work.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harvey.work.entity.Spike;
import com.harvey.work.mapper.UserMapper;
import com.harvey.work.service.UserService;
import com.harvey.work.util.RedisUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	UserMapper userMapper;

	public Object secondKill(String userId, String model) {
		Object value = redisUtil.lLeftPop(model);
		if (null != value) {
			Spike spike = new Spike();
			spike.setCreateTime(new Date());
			spike.setModel(model);
			spike.setVin(value.toString());
			spike.setUserId(userId);
			userMapper.insertRecord(spike);
			return true;
		}
		return false;
	}

	public boolean init() {
		redisUtil.del("B515", "C490", "CD391");
		for (int i = 0; i < 20; i++) {
			redisUtil.lSet("B515", "VIN"+UUID.randomUUID().toString().toUpperCase().replace("-", "").toLowerCase().substring(0,17));
		}
		for (int i = 0; i < 20; i++) {
			redisUtil.lSet("C490", "VIN"+UUID.randomUUID().toString().toUpperCase().replace("-", "").toLowerCase().substring(0,17));
		}
		for (int i = 0; i < 20; i++) {
			redisUtil.lSet("CD391", "VIN"+UUID.randomUUID().toString().toUpperCase().replace("-", "").toLowerCase().substring(0,17));
		}
		return true;
	}
	
	public Object sortSet(String value,int score) {
		try {
			boolean flag = redisUtil.sortSetAdd("rank", value, score);
			Object rank = redisUtil.sortSetGet("rank", 0L, 10L);
			return rank;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public void getId() {
		redisUtil.set("id","0");
		System.out.println(redisUtil.get("id"));
		for(int i=0;i<10;i++) {
			redisUtil.incr("id",1L);
			System.out.println(redisUtil.get("id"));
		}
	}
	
}
