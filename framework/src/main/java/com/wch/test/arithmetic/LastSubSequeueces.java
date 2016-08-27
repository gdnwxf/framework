package com.wch.test.arithmetic;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 求最大的子串的最大的值
 * @author Administrator
 *
 */
public class LastSubSequeueces {

	
	public static void main(String[] args) {
		
		int [] data =  { -2, 11, -4, 13, -5, -2 ,3213};
	 
		//状态转移方程： sum[i]=max(sum[i-1]+a[i],a[i])
		
//		int i,sum = 0, max = 0;  
//		for (  i = 0; i < data.length ; i++) {
//		  
//		        sum += data[i];  
//		        if(sum > max)   
//		            max = sum;  
//		        if(sum < 0)  
//		            sum = 0;          
//		}
//		 
//		System.out.printf("%d",max);  
		
		int sum = 0 , max =0 ;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
			if(sum > max) {
				max = sum ; //将max 更新到最新的状态
			}
			
			if(sum < 0) {
				sum = 0; //恢复到初始的状态
			}
		}
		System.out.printf("%d", sum);
		
		Deque<Integer> left = new LinkedList<Integer>();
		Deque<Integer> right = new LinkedList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		Integer first = arr[0];
		Integer second = arr[1];
		System.out.println(getMinTime(left , right ,stack , first ,second));
	}
	
	
	public void getMaxSubSequeue(  )  {
		
	} 
	
	static int [] arr  = {1,2,5,10};  
		
	public static int  getMinTime(Queue<Integer> left ,Queue<Integer> right , Stack<Integer> stack ,Integer first ,Integer second) {
		 //opt[i] = min{opt[i-1] + a[1] + a[i] , opt[i-2] + a[1] + a[i] + 2*a[2] }
//		if(index == 1) {
//			return Math.max(arr[0], arr[1]);
//		}  
//			 
//		return  Math.min(2 * arr[1] + getMinTime(stack, arr, min, index - 2) + arr [index - 1] + arr[0] ,  getMinTime(stack, arr, min, index - 1 ) + arr[0] + arr[index -1] ); 
		
		
		right.add(first);
		
		return 0;
	}
}
