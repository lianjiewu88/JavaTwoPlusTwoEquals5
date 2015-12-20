package quiz;

import java.util.ArrayList;
import java.util.List;

/*1,动态规划。到第i层的第k个顶点的最小路径长度表示为f(i,k)，则f(i, k) = min{f(i-1,k),  f(i-1,k-1)} + d(i, k); 
 * 其中d(i, k)表示原来三角形数组里的第i行第k列的元素。则可以求得从第一行到最终到第length-1行第k个元素的最小路径长度，
 * 最后再比较第length-1行中所有元素的路径长度大小，求得最小值。

2,本题主要关心的是空间复杂度不要超过n。
3,注意边界条件——每一行中的第一和最后一个元素在上一行中只有一个邻居。
而其他中间的元素在上一行中都有两个相邻元素。
*/
public class Triangle {

	public List<List<Integer>> generate(){
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		ArrayList<Integer> list4 = new ArrayList<Integer>();
		
		list1.add(2);
		list2.add(3); list2.add(4);
		list3.add(6); list3.add(5);list3.add(7);
		//list4.add(4); list4.add(1);list4.add(8);list4.add(7);
		
		list.add(list1); list.add(list2);list.add(list3);
		//list.add(list4);
		
		return list;
	}
	
	public int minimumTotal2(List<List<Integer>> triangle) {
        List<Integer> mins = new ArrayList<>();
        int minTotal = Integer.MAX_VALUE;
        for (int index=0; index<triangle.size(); index++) {
            List<Integer> list = triangle.get(index);
            List<Integer> newMins = new ArrayList<>();
            for (int i=0; i<list.size(); i++) {
                int left = Integer.MAX_VALUE;
                if (i!=0)
                    left = mins.get(i-1) + list.get(i);
                 
                int right = Integer.MAX_VALUE;
                if (i!=list.size()-1)
                    right = mins.get(i) + list.get(i);
                 
                int min;
                if (list.size()==1)
                    min = list.get(0);
                else
                    min = left<right? left : right;
                if (index==triangle.size()-1) {
                    if (minTotal>min)
                        minTotal = min;
                }
                else {
                    newMins.add(min);
                }
            }
             
            mins = newMins;
        }
         
        return minTotal;
    }
	
	public int minimumTotal(List<List<Integer>> triangle) {
	    //We record each minimum sum value for each index in the triangle.
	    //We compute these values from top to bottom.
	    List<List<Integer>> ixSum = new ArrayList<>(triangle.size()); 
	    for (int i = 0; i < triangle.size(); ++i) {
	      List<Integer> lines = triangle.get(i);
	      ixSum.add(new ArrayList<Integer>(lines.size()));
	      if (i == 0) {
	        ixSum.get(0).add(lines.get(0)); //Notice that, we use 'add' method rather than 'set' method.
	      } else {
	        for (int j = 0; j < lines.size(); ++j) {
	          if (j == 0) {
	            ixSum.get(i).add((ixSum.get(i - 1).get(0) + lines.get(0)));
	          } else if (j == (lines.size() - 1)) {
	            // Notice that： ixSum.get(i).get(j-1).
	            ixSum.get(i).add((ixSum.get(i - 1).get(j - 1) + lines.get(j)));
	          } else {
	            if (ixSum.get(i - 1).get(j) > ixSum.get(i - 1).get(j - 1)) {
	              ixSum.get(i).add(ixSum.get(i - 1).get(j - 1) + lines.get(j));
	            } else {
	              ixSum.get(i).add(ixSum.get(i - 1).get(j) + lines.get(j));
	            }
	          }
	        }
	      }
	    }
	    int min = Integer.MAX_VALUE;
	    for (int e : ixSum.get(ixSum.size()-1)) {
	      if (e < min) {
	        min = e;
	      }
	    }
	    ixSum = null;
	    return min;
	  }
	
	public static void main(String[] args) {
		Triangle tool = new Triangle();
		List<List<Integer>> input = tool.generate();
		int result = tool.minimumTotal2(input);
		System.out.println("result: " + result); // 2 + 3 + 5 + 1 = 11)
	}

}
