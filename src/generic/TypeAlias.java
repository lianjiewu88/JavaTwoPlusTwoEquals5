package generic;

import java.util.ArrayList;
import java.util.List;

class Test<I extends Integer> {
    <L extends Long> void x(I i, L l) {
        System.out.println(i.intValue() + ", " +  l.longValue()
        );
    }
    
    static <T extends Number> void PrintA(List<T> l) {
        for(Number n: l) {
            System.out.println(n);
        }
    }
    
}

public class TypeAlias {
	public static void main(String[] arg){
		Test<Integer> jerry = new Test<Integer>();
		// The method x(Integer, L) in the type Test<Integer> is not applicable for the arguments (int, int)
		// jerry.x(2, 4);
		jerry.x(3,2l); // ok - 无需显式定义类型，直接放一个变量进去，compiler自行handle
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		
		ArrayList<String> b = new ArrayList<String>();
		b.add("1");
		b.add("2");
	
		Test.PrintA(a);
		// The method PrintA(List<T>) in the type Test is not applicable for the arguments (ArrayList<String>)
		//Test.PrintA(b); Jerry 2016-02-04 15:42PM 我甚至不需要显式再定义 T了。
	}
}
