package AnnotationTest;

public class AnnotationExample {
    @Override
    @MethodInfo(author = "xxx",version = "1.0",date = "2015/03/26",comment = "override toString()")
    public String toString() {
        return "AnnotationExample{}";
    }

    @Deprecated
    @MethodInfo(comment = "deprecated method", date = "2015/03/26")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    @MethodInfo(author = "Pankaj", comment = "Main method", date = "Nov 17 2012", version = "1.0")
    public static void genericsTest() {
        oldMethod();
    }
}