package singleton;

public class JerrySingleton {
	
	private String name;
	
    private JerrySingleton(){  
    	name = "Jerry";
    }  
 
    private  static class SingletonHolder{  
        private static final JerrySingleton INSTANCE = new JerrySingleton();  
    }  
 
    public static JerrySingleton getInstance()  
    {  
        return SingletonHolder.INSTANCE;  
    }  
}
