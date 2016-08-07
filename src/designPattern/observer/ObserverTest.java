package designPattern.observer;

public class ObserverTest {

	public static void main(String[] args) {
		NumberGenerator generator = new RandomNumberGenerator();  
		  
		JerryObserver observer1 = new NumberJerryObserver();  
		  
		JerryObserver observer2 = new SymbolJerryObserver();  
		  
		//注册观察者  
		  
		generator.addObserver(observer1);  
		  
		generator.addObserver(observer2);  
		  
		generator.generate(); //产生数字  

	}

}
