package quiz;

public class UniquePath {

	 public int uniquePaths(int m, int n) {
	        if ( m <= 0 || n <= 0)
	            return 0;
	 
	        Integer[][] visited = new Integer[m][n];
	        return traverse(0, 0, m, n, visited);
	    }
	     
	    // f(i,j) x, y, x', y' 从(x,y)到(x', y')的方法
	 	
	    // f(i) = f(i-1, j) + f(i, j-1)
	    private int traverse(int i, int j, int m, int n, Integer[][] visited) {
	        if (i == m-1 && j == n-1) // 只差一步的情况
	            return 1;
	 
	        int count = 0;
	         
	        if (i < m-1) { // i和m不相邻
	            Integer val = visited[i+1][j];
	            if (val == null) {
	                val = traverse(i+1, j, m, n, visited);
	                visited[i+1][j] = val;
	            }
	            count += val;
	        }
	         
	        if (j<n-1) {
	            Integer val = visited[i][j+1];
	            if (val==null) {
	                val = traverse(i, j+1, m, n, visited);
	                visited[i][j+1] = val;
	            }
	            count += val;
	        }
	 
	        return count;
	    }
	    
	public static void main(String[] args) {
		UniquePath tool = new UniquePath();
		int result = tool.uniquePaths(2, 2);
		System.out.println(result);

	}

}
