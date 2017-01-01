package comparable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

class ComparableObject implements Serializable, Comparable {
    private String name;
    private int count;
 
    public ComparableObject(String name, int count) {
        this.name = name;
        this.count = count;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public int getCount() {
        return count;
    }
 
    public void setCount(int count) {
        this.count = count;
    }
 
    /**
     * 重写 equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComparableObject)) return false;
 
        ComparableObject bean = (ComparableObject) o;
 
        if (getCount() != bean.getCount()) return false;
        return getName().equals(bean.getName());
 
    }
 
    /**
     * 重写 hashCode 的计算方法
     * 根据所有属性进行 迭代计算，避免重复
     * 计算 hashCode 时 计算因子 31 见得很多，是一个质数，不能再被除
     * @return
     */
    @Override
    public int hashCode() {
        //调用 String 的 hashCode(), 唯一表示一个字符串内容
        int result = getName().hashCode();
        //乘以 31, 再加上 count
        result = 31 * result + getCount();
        return result;
    }
 
    @Override
    public String toString() {
        return "ComparableObject{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
 
    /**
     * 当向 TreeSet 中添加 ComparableObject 时，会调用这个方法进行排序
     * @param another
     * @return
     */
    @Override
    public int compareTo(Object another) {
        if (another instanceof ComparableObject){
            ComparableObject anotherBook = (ComparableObject) another;
            int result;
 
            //比如这里按照书价排序
            result = getCount() - anotherBook.getCount();     
 
          //或者按照 String 的比较顺序
          //result = getName().compareTo(anotherBook.getName());
 
            if (result == 0){   //当书价一致时，再对比书名。 保证所有属性比较一遍
                result = getName().compareTo(anotherBook.getName());
            }
            return result;
        }
        // 一样就返回 0
        return 0;
    }
}

public class ComparableTest{
	public static void main(String[] arg){
		ArrayList<ComparableObject> treeSet = new ArrayList<>();
		treeSet.add(new ComparableObject("A",34));
		treeSet.add(new ComparableObject("S",1));
		treeSet.add( new ComparableObject("V",46));
		treeSet.add( new ComparableObject("Q",26));
		
		Collections.sort(treeSet);
		for( ComparableObject object : treeSet){
			System.out.println(object);
			
		}
	}
}
