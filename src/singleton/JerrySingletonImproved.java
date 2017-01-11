package singleton;

public class JerrySingletonImproved  
{  
    private static boolean flag = false;  
 
    private JerrySingletonImproved(){  
        synchronized(JerrySingletonImproved.class)  
        {  
            if(flag == false)  
            {  
                flag = !flag;  
            }  
            else 
            {  
                throw new RuntimeException("Singleton violated");  
            }  
        }  
    }  
 
    private  static class SingletonHolder{  
        private static final JerrySingletonImproved INSTANCE = new JerrySingletonImproved();  
    }  
 
    public static JerrySingletonImproved getInstance()  
    {  
        return SingletonHolder.INSTANCE;  
    }  
}