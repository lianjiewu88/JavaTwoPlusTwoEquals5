package quiz;

class Grandpa {
    static {
        System.out.println("Grandpa was initialized.");
    }
}

class Parent extends Grandpa{
     static String language = "Chinese";
     static{
         System.out.println("Parent was initialized.");
     }
 }

class Cindy extends Parent{
    static{
        System.out.println("Child was initialized.");
    }
}

public class PassiveAccess {
	
	/* 主动使用会导致类的初始化，其超类均将在该类的初始化之前被初始化，但通过子类访问父类的静态字段或方法时，对于子类(或子接口、接口的实现类)来说，
	 * 这种访问就是被动访问，或者说访问了该类(接口)中的不在该类(接口)中声明的静态成员。 
	 * Grandpa was initialized.
	Parent was initialized.
	Chinese
	 */
	public static void main(String[] args) {
		System.out.println(Cindy.language);
	}
}
