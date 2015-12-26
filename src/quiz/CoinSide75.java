package quiz;

import java.util.Random;

public class CoinSide75 {
	  private static Random rnd = new Random();
	  public static CoinSide75 flip() {
	  /* 在5.0版本之前，第二个和第三个操作数是引用类型时，条件操作符要求其中一个必须是另一个的子类型。
	   * 5.0之后，结果类型是这两种类型的最小公共超类，default为object。
	   */
	      return rnd.nextBoolean() ? (CoinSide75)Heads.INSTANCE: Tails.INSTANCE;
	  }

	  public static void main(String[] args ) {
	     System.out.println(flip());
	     Derived1 a = new Derived1();
	     a.f();
	  }
	}

	class Heads extends CoinSide75 {
	   private Heads() {}
	   public static final Heads INSTANCE = new Heads();
	   public String toString() {
	       return "heads";
	   }
	}

	class Tails extends CoinSide75 {
	   private Tails() {}
	   public static final Tails INSTANCE = new Tails();
	   public String toString() {
	       return "tails";
	   }
	}
	
	/* 一个域，静态方法或者成员类型可以分别隐藏在其超类中可以访问到的具有相同名字，（对方法而言就是具有相同方法签名）的所有域，静态方法或者成员类型。隐藏一个成员将阻止其被继承 */
	class Base1 {
	   public static void f() { System.out.println("base");}
	}

	class Derived1 extends Base1 {
	   public static void f() { System.out.println("derived");} // hides Base.f()
	}
	
