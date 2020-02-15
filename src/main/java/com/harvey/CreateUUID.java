package com.harvey;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.UUID;

import com.sun.jmx.snmp.Timestamp;

public class CreateUUID {
	public static void main(String[] args) {
		try {
			Long str = 0L;
			for (int i = 0; i < 10; i++) {
				str = SnowFlakeIdWorker.getId();
				//str = UUID.randomUUID().toString().toUpperCase().replace("-", "").toLowerCase();
				System.out.println(str);
			}
			System.out.println("success!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void getMemInfo() {
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		MemoryUsage memoryUsageHeap = memoryMXBean.getHeapMemoryUsage(); // 椎内存使用情况
		System.out.println("堆已用：" + memoryUsageHeap.getUsed()); // 已使用的内存
	}
}
