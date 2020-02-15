package com.harvey;

import java.util.HashMap;
import java.util.UUID;

public class ThreadStudy {
	public static void main(String[] args) {
		new ticketThread().start();
		new ticketThread().start();
	}

}

// 继成java.lang.Thread, 重写run()方法
class ticketThread extends Thread {
	HashMap<String, String> map = new HashMap<String, String>();
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			Customer customer = new Customer();
			customer.ticket(map);
			Saler saler = new Saler();
			saler.check(map);
		}
	}
}

class MyRunnable implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "\t" + Thread.currentThread().getId());
	}
}

class Customer{
	public void ticket(HashMap<String, String> map) {
		String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "").toLowerCase();
		System.out.println("buy:"+uuid);
		map.put("name",uuid);
	}
}
class Saler{
	public void check(HashMap<String, String> map) {
		String name = map.get("name");
		System.out.println("check:"+name);
	}
}
