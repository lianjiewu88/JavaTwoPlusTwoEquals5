package quiz;

import java.util.Calendar;

/* 
 * 类的初始化顺序中的循环：类初始化由虚拟机对其main方法调用而触发，首先。静态field被设置为缺

省值，其中INSTANCE为null，CURRENT_YEAR为0，接下来，静态域初始器按照其出现的顺序执行，第一

个静态域是INSTANCE, 它的值是通过调用Elvis（）构造器计算出来。这个构造器会初始化beltSize。

通常，读取一个静态field是会引起一个类被初始化的事件之一，但是我们已经在初始化Elvis类了，递

归的初始化尝试会直接被忽略掉。因此，CURRENT_YEAR仍然是0.

根源：在final类型的静态域被初始化之前，存在着读取它的值的可能，而此时静态域包含的还只是其

所属类型的缺省值。有点与直觉相违背，因为我们通常会将final类型的域看做是常量。final类型的

field只有在其初始化表达式是常量表达式时才是常量。
静态域在被初始化之前就调用构造器，静态field，甚至是final类型的静态域，可能会在它们被初始化

之前，就被读走缺省值。

 */
public class Elvis49 {
	// public static final Elvis49 INSTANCE = new Elvis49();
	private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    public static final Elvis49 INSTANCE = new Elvis49();
    private final int beltSize;
   
    private Elvis49(){
      beltSize = CURRENT_YEAR - 1930;
    }
    public int beltSize() {
      return beltSize;
    }
    public static void main(String[] args){
    
       System.out.println("Elvis wears a size " + INSTANCE.beltSize() + " belt.");
    }
}
