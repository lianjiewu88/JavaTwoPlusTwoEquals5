package designPattern.observer;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class NumberGenerator {
	private ArrayList observers = new ArrayList();
	public void addObserver(JerryObserver observer) {  
		observers.add(observer);  
	}  
	
	public void delObserver(JerryObserver observer) {  
		observers.remove(observer);    
	}
	
	public void notifyObservers() {  
		Iterator it = observers.iterator();  
		while(it.hasNext()) {  
			JerryObserver o =(JerryObserver) it.next();  
			o.update(this);//this相当于上面提到的邮局名  
		}  
	}
	
	public abstract int getNumber();//获取数字  
	  
	public abstract void generate();//产生数字  
}
