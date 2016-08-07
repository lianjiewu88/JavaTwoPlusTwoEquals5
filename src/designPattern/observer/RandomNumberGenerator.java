package designPattern.observer;

import java.util.Random;

public class RandomNumberGenerator extends NumberGenerator{
	private Random random = new Random();//随机数产生器  
	  
	private int number;   //用于存放数字  
	  
	public void generate() {  
	  
		for(int i=0 ; i < 5; i++) {  
			number = random.nextInt(10);//产生10以内的随机数  
			notifyObservers();  //有新产生的数字，通知所有注册的观察者  
		}
	}
	
	public int getNumber() {  
		return number;  
	}
	
	
}
