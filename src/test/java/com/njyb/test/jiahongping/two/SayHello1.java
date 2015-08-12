package com.njyb.test.jiahongping.two;
public class SayHello1 {
	public ISay1 toI1() {  
        return new ISay1(){
			@Override
			public void sayHello() {
				// TODO Auto-generated method stub
				System.out.println("hello");
			}  
        	
        };  
    } 
	
	public static void main(String[] args) {
		SayHello1 hello=new SayHello1();
		ISay1 say=hello.toI1();
		say.sayHello();
	}
}