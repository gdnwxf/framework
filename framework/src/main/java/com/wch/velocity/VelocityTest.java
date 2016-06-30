package com.wch.velocity;

import org.apache.velocity.app.VelocityEngine;

import com.wch.utils.BeanUtils;

public class VelocityTest {

	
	public static void main(String[] args) {
		
		
		   VelocityEngine ve = new VelocityEngine();  
		   BeanUtils.print_Max(ve);
	}
}
