Java1.7和1.8都对java泛型有所增强，特别是1.8增强了泛型的推断能力。开始的时候以为是这个原因，但是在用javac -target 1.7 -source 1.7 ExceptionForQuiz.java编译之后还是能通过，说明不是范性增强的原因。后面发现SQLException的父类是Exception而不是RuntimeException。所以做了如下修改就可以工作了
```Java
public class ExceptionForQuiz<T extends Exception> {
    private void pleaseThrow(final Exception t) throws T {
        throw (T) t;
    }

    public static void main(final String[] args) {
        try {
            new ExceptionForQuiz<RuntimeException>()
                .pleaseThrow(new ChildRuntimeException());
        } catch (final ChildRuntimeException ex) {
            System.out.println("Jerry print, the exception class: " + ex.getClass().getSimpleName());
            ex.printStackTrace();
        }
    }


    static public class ChildRuntimeException extends RuntimeException {

    }

}

```

另外发一个[java1.7和1.8泛型增加的参考链接](https://my.oschina.net/benhaile/blog/184390)

Best Regards,
Joe Zhong
