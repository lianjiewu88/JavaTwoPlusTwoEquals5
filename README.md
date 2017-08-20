# 2017-01-14
Java养成什么样的编程习惯可以有利于GC? - From zhihu

# 2017-01-21

Mockito是一个针对Java的mocking框架。它与EasyMock和jMock很相似，但是通过在执行后校验什么已经被调用，它消除了对期望行为（expectations）的需要。其它的mocking库需要你在执行前记录期望行为（expectations），而这导致了丑陋的初始化代码。

Stub和Mock

相同点：Stub和Mock对象都是用来模拟外部依赖，使我们能控制。

不同点：而stub完全是模拟一个外部依赖，用来提供测试时所需要的测试数据。而mock对象用来判断测试是否能通过，
也就是用来验证测试中依赖对象间的交互能否达到预期。在mocking框架中mock对象可以同时作为stub和mock对象
使用，两者并没有严格区别。 更多信息：http://martinfowler.com/articles/mocksArentStubs.html·

# 2017-02-18 9:33PM

https://github.com/HalfStackDeveloper/ClassReader 

# 2017-03-28 11：20PM
no commit yesterday

# 2017-07-01 8:07AM - Xun's house

## Created by Jerry Wang, last modified on Sep 08, 2015

* Eclipse local workspace: C:\MyProgram\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core

* [Technical details of serve modules without publishing in Eclipse WTP and Tomcat?
](https://stackoverflow.com/questions/15575302/technical-details-of-serve-modules-without-publishing-in-eclipse-wtp-and-tomcat)

# 2017-08-20 10:10AM

* Java中传参都是值传递，如果是基本类型，就是对值的拷贝，如果是对象，就是对引用地址的拷贝。

* 基本类型的传参，对传参进行修改，不影响原本参数的值。

* 基本类型的传参，在方法内部是值拷贝，有一个新的局部变量得到这个值，对这个局部变量的修改不影响原来的参数。对象类型的传参，传递的是堆上的地址，在方法内部是有一个新的局部变量得到引用地址的拷贝，对该局部变量的操作，影响的是同一块地址，因此原本的参数也会受影响，反之，若修改局部变量的引用地址，则不会对原本的参数产生任何可能的影响。