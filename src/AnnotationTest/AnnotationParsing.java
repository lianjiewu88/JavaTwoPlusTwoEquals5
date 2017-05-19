package AnnotationTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2015/3/26.
 */
public class AnnotationParsing {

    public static void main(String[] args) {
        for (Method method: AnnotationExample.class.getMethods()) {
            if (method.isAnnotationPresent(MethodInfo.class)) {
                for (Annotation annotation:method.getAnnotations()) {
                    System.out.println(annotation + " in method:"+ method);
                }

                System.out.println("\r\n");
                MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);

                if ("1.0".equals(methodInfo.version())) {
                    System.out.println("Method with revision no 1.0 = "
                            + method);
                }
            }
        }
    }
}