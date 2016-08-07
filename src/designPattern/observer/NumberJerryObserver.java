package designPattern.observer;

public class NumberJerryObserver implements JerryObserver {
	
	public void update(NumberGenerator generator) {  
		  
		System.out.println("NumberObserver:"+ generator.getNumber());  
		  
		try {  
			Thread.sleep(1000 * 3); //为了能清楚的看到输出，休眠3秒钟。  
		}catch(InterruptedException e) {  
			e.printStackTrace();  
		}  
	}
}
