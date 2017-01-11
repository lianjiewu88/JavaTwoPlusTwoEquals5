package singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonAttack {

	private static void test1() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> classType = JerrySingleton.class;  
		 
        Constructor<?> c = classType.getDeclaredConstructor(null);  
        c.setAccessible(true);  
        JerrySingleton e1 = (JerrySingleton)c.newInstance();  
        JerrySingleton e2 = JerrySingleton.getInstance();  
        System.out.println(e1 == e2);  
	}
	
	private static void test2() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> classType = JerrySingletonImproved.class;  
		 
        Constructor<?> c = classType.getDeclaredConstructor(null);  
        c.setAccessible(true);  
        JerrySingletonImproved e1 = (JerrySingletonImproved)c.newInstance();  
        JerrySingletonImproved e2 = JerrySingletonImproved.getInstance();  
        System.out.println(e1 == e2);  
	}
	
	private static void test3() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> classType = JerrySingletonAnotherApproach.class;  
		 
        Constructor<?> c = classType.getDeclaredConstructor(null);  
        c.setAccessible(true);  
        JerrySingletonAnotherApproach e1 = (JerrySingletonAnotherApproach)c.newInstance();  
        JerrySingletonAnotherApproach e2 = JerrySingletonAnotherApproach.INSTANCE;
        System.out.println(e1 == e2);  
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException  
    {  
        // test2();
        
		System.out.println("Name:" + JerrySingletonAnotherApproach.INSTANCE.getName());
        test3();
    }  

}
