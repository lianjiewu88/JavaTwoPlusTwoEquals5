package listSort;

public class FunctionDemo {

    @FunctionalInterface
    public interface FunctionIFTest{
        public void print(String arg);
    }

    public static void process(FunctionIFTest functionQuote){
        String str = "https://www.sap.com";
        
        System.out.println("Simple Name: " + functionQuote.getClass().getSimpleName());
        System.out.println("Canonical Name: " + functionQuote.getClass().getCanonicalName());
        System.out.println("Type name:" + functionQuote.getClass().getTypeName());
        functionQuote.print(str);
    }

    public static void main(String[] args) {
        process((String s) -> System.out.println(s));
        process(System.out::println);
    }
}
