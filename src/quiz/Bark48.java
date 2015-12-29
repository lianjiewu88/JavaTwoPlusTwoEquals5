package quiz;

class Dog48 {
    public static void bark() {
       System.out.println("Woof");
    }
    
    public void w(){
    	System.out.println("Dog w");
    }
}

 class Basenji extends Dog48 {
       public static void bark() { }
       
       public void w(){
    	   System.out.println("...");
       }
 }

/* 当程序调用了静态方法时，被调用的方法都是在编译时刻被选定，基于修饰符的编译器类型做出的，修

饰符的编译期类型就是我们给出的方法调用表达式中圆点左边部分的名字。在本例中，两个方法调用的

修饰符分别是变量woofer和nipper，都被声明为Dog类型，因此具有相同的编译器类型，所以编译器使

得他们调用的是相同的方法:Dog.bark. 尽管nipper运行期类型是Basenji，但编译器只考虑其编译期类

型。 
静态方法通常都是通过一个类来调用，如Dog.bark(). 千万不要用表达式来标识一个静态方法调用。
静态方法不能被覆写，只能被隐藏。

*/
public class Bark48{
    public static void main(String[] args) {
       Dog48 woofer = new Dog48();
       Dog48 nipper = new Basenji();
       woofer.bark(); // static method should be accessed via a static way
       nipper.bark();
       
       nipper.w();
    }
}