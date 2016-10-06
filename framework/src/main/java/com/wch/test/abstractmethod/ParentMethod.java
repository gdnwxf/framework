package com.wch.test.abstractmethod;

public class ParentMethod {

	public static void main(String[] args) {
		Child child = new Child();
		child.work();
	}
}

class  Child extends Parent {

	@Override
	protected void Pmethod() {
		System.out.println("具体实现父类中的方法!!!");
	}
	
}

abstract class Parent{
	
	public void work() {
		System.out.println("调用双亲的方法开始!!");
		Pmethod();
	}
 	
	protected abstract void Pmethod(); 
	
}
