package constructor;

public class SubClass extends SuperClass {

    private int mSubX = 1; // this line is executed after subclass constructor

    public SubClass() {}

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
