package shutdownhook;

import java.util.concurrent.TimeUnit;  

public class JVMHookTest  
{  
    public void start()  
    {  
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {  
            @Override 
            public void run()  
            {  
                System.out.println("Execute Hook.....");  
            }  
        }));  
    }  
 
    public static void main(String[] args)  
    {  
        new JVMHookTest().start();  
        System.out.println("The Application is doing something");  
 
        try 
        {  
            TimeUnit.MILLISECONDS.sleep(5000);  
        }  
        catch (InterruptedException e)  
        {  
            e.printStackTrace();  
        }  
    }  
}
