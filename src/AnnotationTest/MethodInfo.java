package AnnotationTest;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
    String author() default "hupeng";
    String version() default "1.0";
    String date();
    String comment();

}

