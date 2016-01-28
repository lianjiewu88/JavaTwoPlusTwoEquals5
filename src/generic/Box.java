package generic;
// https://docs.oracle.com/javase/tutorial/java/generics/types.html
/*
 * A Simple Box Class

Begin by examining a non-generic Box class that operates on objects of any type. 
It needs only to provide two methods: set, which adds an object to the box, and get, which retrieves it:

public class Box {
    private Object object;

    public void set(Object object) { this.object = object; }
    public Object get() { return object; }
}
Since its methods accept or return an Object, you are free to pass in whatever you want, 
provided that it is not one of the primitive types. There is no way to verify, 
at compile time, how the class is used. One part of the code may place an Integer in the box and 
expect to get Integers out of it, while another part of the code may mistakenly pass in a String,
 resulting in a runtime error.
 */
public class Box<T> {
	// T stands for "Type"
    private T t;

    public void set(T t) { 
    	this.t = t; 
    }
    public T get() { 
    	return t; 
    }
    /*
     * Box<Integer> integerBox;
     * Like any other variable declaration, this code does not actually create a new Box object. It simply declares that integerBox will 
     * hold a reference to a "Box of Integer", which is
     *  how Box<Integer> is read.
     */
    static public void main(String[] arg){
    	Box<Integer> integerBox = new Box<Integer>();
    	integerBox.set(1);
    }
}
