package quiz;

/*这道题，如果看过Mark Allen Weiss写的数据结构与算法分析一书，可以发现是第二章为了介绍算法的魅力，
 * 逐步从三次方的时间复杂度一直优化到线性时间复杂度的一个例子。有一点小区别，就是书中给的例子简化了，
 * 如果全为负数，则认为最大子序列和为0，所以有一点点出入，不过基本思路是完全一样的。

现在对线性时间解法做一下解释，属于一种DP问题。已知了前k个元素的最大子序列和为maxSub（已经被记录下来了），
以及一个临时和sum，如果添加了第k+1这个元素，由于是连续子序列这个限制，所以如果k+1这个元素之前的和是小于0的，
那么对于增大k+1这个元素从而去组成最大子序列是没有贡献的，所以可以把sum 置0。举个例子，-1， -2 ，4， -5， 7
这里假定7为第k+1个元素，那么很明显可以看出，之前的sum = -5 + 4 =-1，那么这样对于7来说只会减少它，
所以直接置sum = 0， 0 + 7才能得到正确的答案。再拓展这个数组， -1， -2， 4， -5， 7， 1 
这里1之前的sum = 7 > 0，对于后面的1来组成最大子序列是有贡献的，所以sum = 7 + 1 =8。
再注意一点，只要sum不减到负数，中间出现小于0的元素是没关系的，sum仍然可以继续累加。

http://blog.csdn.net/joylnwang/article/details/6859677

假设以 i 结尾的数组 nums[0 ,i] 的最大子数组为 f(i)，那么：
f(i) = max(f(i-1)+nums[i], nums[i])

*/
public class MaxSubArray {

	public static int maxSubArray(int[] A) {
        if (null==A)
            throw new IllegalArgumentException();
         
        int max = Integer.MIN_VALUE;
        int sum = 0;
        // think about every maximum subarray ended with i
        for (int i : A) {
            sum += i;
             
            // keep it
            if(sum > max)
                max = sum;
             
            // deprecate it, the worst case: every member in A are negative, then the result is 0
            if(sum<0)
                sum = 0;
        }
         
        return max;
    }
	
	public static void main(String[] args) {
		int[] input = { -2,1,-3,4,-1,2,1,-5,4}; // 6
		int result = maxSubArray(input);
		System.out.println("result: " + result);

	}

}
