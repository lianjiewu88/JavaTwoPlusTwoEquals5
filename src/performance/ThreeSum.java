package performance;


public class ThreeSum {

    // print distinct triples (i, j, k) such that a[i] + a[j] + a[k] = 0
    public static void printAll(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        System.out.println(a[i] + " " + a[j] + " " + a[k]);
                        System.out.println("a[" + i + "]" + " a[" +  j + "]" + " a[" + k + "]");
                    }
                }
            }
        }
    } 

    // return number of distinct triples (i, j, k) such that a[i] + a[j] + a[k] = 0
    public static int count(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    } 

    public static void main(String[] args)  { 
        /*int N = StdIn.readInt(); 
        int[] a = new int[N]; 
        for (int i = 0; i < N; i++) {
            a[i] = StdIn.readInt();
        }*/
    	int [] a = {1,2,3,-1,-2,-3,0,0,0,0,0,0,0,0};

        Stopwatch timer = new Stopwatch();
        int cnt = count(a);
        System.out.println("elapsed time = " + timer.elapsedTime());
        System.out.println(cnt);
        printAll(a);
        
        int x = 0;
        int N = 3;
        for (int i = 0; i < N; i++)
           for (int j = i + 1; j < N; j++)
              for (int k = j + 1; k < N; k++)
                 x++;
        System.out.println("final: " + x); // 1
        // 3 * 2 * 1 / 6 = 1 N (N-1) (N-2) / 6.

    } 
} 

