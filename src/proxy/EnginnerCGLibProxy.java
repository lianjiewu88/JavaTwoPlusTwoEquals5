package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;
public class EnginnerCGLibProxy {
	Object obj;
	 
    public Object bind(final Object target)
    {
        this.obj = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args,
                    MethodProxy proxy) throws Throwable
            {
                System.out.println("Enginner 2 writes document");
                Object res = method.invoke(target, args);
                return res;
            }
        });
        return enhancer.create();
    }
}
