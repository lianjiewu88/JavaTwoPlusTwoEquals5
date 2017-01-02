链接：blog.csdn.net/u013256816/article/details/50394923


Java程序经常也会遇到进程挂掉的情况，一些状态没有正确的保存下来，这时候就需要在JVM关掉的时候执行一些清理现场的代码。JAVA中的ShutdownHook提供了比较好的方案。

JDK提供了Java.Runtime.addShutdownHook(Thread hook)方法，可以注册一个JVM关闭的钩子，这个钩子可以在一下几种场景中被调用：

  1. 程序正常退出
  2. 使用System.exit()
  3. 终端使用Ctrl+C触发的中断
  4. 系统关闭
  5. OutOfMemory宕机
  6. 使用Kill pid命令干掉进程（注：在使用kill -9 pid时，是不会被调用的）

下面是JDK1.7中关于钩子的定义：
>

    public void addShutdownHook(Thread hook)
参数：
    hook - An initialized but unstarted Thread object 
抛出： 
    IllegalArgumentException - If the specified hook has already been registered, or if it can be determined that the hook is already running or has already been run 
    IllegalStateException - If the virtual machine is already in the process of shutting down 
    SecurityException - If a security manager is present and it denies RuntimePermission("shutdownHooks")
从以下版本开始： 
    1.3
另请参见：
    removeShutdownHook(java.lang.Thread), halt(int), exit(int)


首先来测试第一种，程序正常退出的情况：
```Java
package com.hook;  
 
import java.util.concurrent.TimeUnit;  
 
public class HookTest  
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
        new HookTest().start();  
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
```

运行结果：

The Application is doing something  
Execute Hook.....

如上可以看到，当main线程运行结束之后就会调用关闭钩子。

下面再来测试第五种情况（顺序有点乱，表在意这些细节）：
```Java
package com.hook;  
 
import java.util.concurrent.TimeUnit;  
 
public class HookTest2  
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
        new HookTest().start();  
        System.out.println("The Application is doing something");  
        byte[] b = new byte[500*1024*1024];  
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
```

运行参数设置为：-Xmx20M  这样可以保证会有OutOfMemoryError的发生。

运行结果：

The Application is doing something  
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space  
    at com.hook.HookTest2.main(HookTest2.java:22)  
Execute Hook.....

可以看到程序遇到内存溢出错误后调用关闭钩子，与第一种情况中，程序等待5000ms运行结束之后推出调用关闭钩子不同。

接下来再来测试第三种情况：
```Java

package com.hook;  
 
import java.util.concurrent.TimeUnit;  
 
public class HookTest3  
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
        new HookTest3().start();  
        Thread thread = new Thread(new Runnable(){  
 
            @Override 
            public void run()  
            {  
                while(true)  
                {  
                    System.out.println("thread is running....");  
                    try 
                    {  
                        TimeUnit.MILLISECONDS.sleep(100);  
                    }  
                    catch (InterruptedException e)  
                    {  
                        e.printStackTrace();  
                    }  
                }  
            }  
 
        });  
        thread.start();  
    }  
 
}
```

在命令行中编译：javac com/hook/HookTest3.java

在命令行中运行：Java com.hook.HookTest3  （之后按下Ctrl+C）

效果如预期。

