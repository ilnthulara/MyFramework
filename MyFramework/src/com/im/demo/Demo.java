package com.im.demo;

import com.im.myframework.MyFramework;

public class Demo {

	public static void main(String[] args) {
		Member obj = new Member();
		obj.id = 1;
		obj.name = "Nethmi Ilangamge";
		obj.email = "nethu@gmail.com";
		obj.pw = "123";
		
		MyFramework.save(obj);
	}

}
