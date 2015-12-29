package quiz;

public class Workout45 {
    public static void main(String[] args) {
        workHard();
        System.out.println("nap time");
    }
    private static void workHard() {
        try {
          System.out.println("in try: work hard");
          workHard();
        } finally {
          // I set a breakpoint here and it seems debugging is not possible.
          System.out.println("in finally, work hard");
          workHard();
        }
    }
}
