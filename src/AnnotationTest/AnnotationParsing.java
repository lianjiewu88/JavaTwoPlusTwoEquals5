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

                MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);

                if ("1.0".equals(methodInfo.version())) {
                    System.out.println("Method with revision no 1.0 = "
                            + method);
                }
                /* output: 
                 * @AnnotationTest.MethodInfo(version=1.0, author=xxx, date=2015/03/26, comment=override toString()) in method:public java.lang.String AnnotationTest.AnnotationExample.toString()
Method with revision no 1.0 = public java.lang.String AnnotationTest.AnnotationExample.toString()
@AnnotationTest.MethodInfo(version=1.0, author=Pankaj, comment=Main method, date=Nov 17 2012) in method:public static void AnnotationTest.AnnotationExample.genericsTest()
Method with revision no 1.0 = public static void AnnotationTest.AnnotationExample.genericsTest()
@java.lang.Deprecated() in method:public static void AnnotationTest.AnnotationExample.oldMethod()
@AnnotationTest.MethodInfo(version=1.0, author=hupeng, comment=deprecated method, date=2015/03/26) in method:public static void AnnotationTest.AnnotationExample.oldMethod()
Method with revision no 1.0 = public static void AnnotationTest.AnnotationExample.oldMethod()
                 */
            }
        }
    }
}