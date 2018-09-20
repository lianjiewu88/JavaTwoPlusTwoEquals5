package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EnginnerProxy implements InvocationHandler
{
    Object obj;
    
    public Object bind(Object obj)
    {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
                .getClass().getInterfaces(), this);
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable
    {
        System.out.println("Enginner writes document");
        String methodName = method.getName();
        switch( methodName){
             case "write": 
            	 System.out.println("write method is called");
            	 break;
             case "read":
            	 System.out.println("read method is called");
            	 break;
             default:
            	 System.out.println("Invalid method! valid methods are 'write' or 'read'!");
            	 return null;
        }
        Object res = method.invoke(obj, args);
        return res;
    }
}
