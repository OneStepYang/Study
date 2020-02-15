package com.harvey;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.harvey.work.util.RedisUtil;

public class Study {
	@Autowired
	RedisUtil redisUtil;
	private static String tillString = "";// 计划任务生成文件截至时间
	private static String beginString = "";// 计划任务生成文件开始时间

	public static void main(String[] args) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowdate = dateFormat.parse("2020-02-01 08:00:00");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowdate);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour < 7) {// 六点以前不执行生成文件的任务
			return;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat df_month = new SimpleDateFormat("yyyy-MM");
		beginString = df_month.format(nowdate) + "-01 00:00:00";
		tillString = df.format(nowdate);
		System.out.println(beginString);
		System.out.println(tillString);
	}
}
