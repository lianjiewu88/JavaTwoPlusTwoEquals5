package constructor;

public class SubClass extends SuperClass {

    private int mSubX = 1; // this line is executed after subclass constructor

    public SubClass() {}
    /*
     * (non-Javadoc)
     * @see constructor.SuperClass#setX(int)
     *  0: aload_0       ----------------------------------------
        1: invokespecial #1                  // Method bugme/SuperClass."<init>":()V
       ......
这里面并没有方法叫<init>, 是因为javap为了方便我们阅读, 
直接把它改成类名bugme.SubClass, 顺便一提, bugme是包名.
<init>方法并非通常意义上的构造方法, 这是Java帮我们合成的一个方法, 
里面的指令会帮我们按顺序进行普通成员变量初始化, 也包括初始化块里的代码, 
注意是按顺序执行, 这些都执行完了之后才轮到构造方法里代码生成的指令执行. 
这里aload_0将局部变量表中下标为0的元素入栈, 其实就是Java中的this, 
结合invokespecial #1, 是在调用父类的构造函数, 也就是我们常见的super().
     */

    @Override
    public void setX(int x) {
        super.setX(x);
        mSubX = x;
        System.out.println("SubX is assigned " + x);
    }

    public void printX() {
        System.out.println("SubX = " + mSubX);
    }
    
    public static void main(String[] args) {
        SubClass sc = new SubClass();
        sc.printX();
    }
    
}
