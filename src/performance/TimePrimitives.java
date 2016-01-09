package performance;

public class TimePrimitives {

    public static int f(int i, int j) { return 0; }

    @SuppressWarnings("unused")
	public static void main(String[] args) {
        int N = 1000;//Integer.parseInt(args[0]);
        Stopwatch timer = new Stopwatch();
        double freq, elapsed;

        System.out.println("Nanoseconds per operation");
 
       /***************************************************************
        * empty loop
        ***************************************************************/
        timer = new Stopwatch();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                ;
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N;
        System.out.println("Empty loop:" + "\t" + freq);


       /***************************************************************
        * addition
        ***************************************************************/
        timer = new Stopwatch();
        for (int i = 1; i <= N; i++) {
            int k = -1;
            for (int j = 1; j <= N; j++) {
                k = i + j;
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N;
        System.out.println("Integer addition:" + "\t" + freq);

       /***************************************************************
        * integer subtraction
        ***************************************************************/
        timer = new Stopwatch();
        for (int i = 1; i <= N; i++) {
            int k = -1;
            for (int j = 1; j <= N; j++) {
                k = i - j;
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N;
        System.out.println("Integer subtraction:" + "\t" + freq);


       /***************************************************************
        * multiplication
        ***************************************************************/
        timer = new Stopwatch();
        for (int i = 1; i <= N; i++) {
            int k = -1;
            for (int j = 1; j <= N; j++) {
                k = i * j;
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N;
        System.out.println("Multiply:" + "\t" + freq);


       /***************************************************************
        * comparison
        ***************************************************************/
        timer = new Stopwatch();
        for (int i = 1; i <= N; i++) {
            boolean b;
            for (int j = 1; j <= N; j++) {
                b = (i < j);
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N;
        System.out.println("Comparison:" + "\t" + freq);


       /***************************************************************
        * remainder
        ***************************************************************/
        timer = new Stopwatch();
        for (int i = 1; i <= N; i++) {
            int k = -1;
            for (int j = 1; j <= N; j++) {
                k = i % j;
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N;
        System.out.println("Remainder:" + "\t" + freq);


       /***************************************************************
        * division
        ***************************************************************/
        timer = new Stopwatch();
        for (int i = 1; i <= N; i++) {
            int k = -1;
            for (int j = 1; j <= N; j++) {
                k = i / j;
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N;
        System.out.println("Division:" + "\t" + freq);


       /***************************************************************
        * floating point add
        ***************************************************************/
        timer = new Stopwatch();
        for (int i = 1; i <= N; i++) {
            double k;
            double fi = i;
            for (int j = 1; j <= N; j++) {
                k = fi + j;
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N;
        System.out.println("Float Add:" + "\t" + freq);


       /***************************************************************
        * floating point add
        ***************************************************************/
        timer = new Stopwatch();
        for (double i = 1; i <= N; i++) {
            double k;
            double fi = i;
            for (double j = 1; j <= N; j++) {
                k = fi - j;
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N;
        System.out.println("Float subtraction:" + "\t" + freq);


       /***************************************************************
        * floating point division
        ***************************************************************/
        timer = new Stopwatch();
        for (double i = 1; i <= N; i++) {
            double k;
            double fi = i;
            for (double j = 1; j <= N; j++) {
                k = fi / j;
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N;
        System.out.println("Float Division:" + "\t" + freq);


       /***************************************************************
        * floating point multiply
        ***************************************************************/
        timer = new Stopwatch();
        for (double i = 1; i <= N; i++) {
            double fi = i;
            double k;
            for (double j = 1; j <= N; j++) {
                k = fi * j;
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N;
        System.out.println("Float Multiply:" + "\t" + freq);


       /***************************************************************
        * Empty integer function call
        ***************************************************************/
        timer = new Stopwatch();
        for (int i = 1; i <= N; i++) {
            int k = -1;
            for (int j = 1; j <= N; j++) {
                k = f(i, j);
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N;
        System.out.println("Function call:" + "\t" + freq);


       /***************************************************************
        * Math.sin
        ***************************************************************/
        timer = new Stopwatch();
        for (double i = 1; i <= N/10.0; i++) {
            double k;
            for (double j = 1; j <= N; j++) {
                k = Math.sin(i + j);
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N * 10;
        System.out.println("Math.sin:" + "\t" + freq);

       /***************************************************************
        * Math.atan2
        ***************************************************************/
        timer = new Stopwatch();
        for (double i = 1; i <= N/10.0; i++) {
            double k;
            for (double j = 1; j <= N; j++) {
                k = Math.atan2(i, j);
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N * 10;
        System.out.println("Math.atan2:" + "\t" + freq);

       /***************************************************************
        * Math.random
        ***************************************************************/
        timer = new Stopwatch();
        for (double i = 1; i <= N/10.0; i++) {
            double k;
            for (double j = 1; j <= N; j++) {
                k = Math.random();
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N * 10;
        System.out.println("Math.random:" + "\t" + freq);

       /***************************************************************
        * addition
        ***************************************************************/
        timer = new Stopwatch();
        for (int i = 1; i <= N; i++) {
            int k = -1;
            for (int j = 1; j <= N; j++) {
                k = i + j;
            }
        }
        elapsed = timer.elapsedTime();
        freq = 1.0E9 * elapsed / N / N;
        System.out.println("Integer addition:" + "\t" + freq);
    }

}
