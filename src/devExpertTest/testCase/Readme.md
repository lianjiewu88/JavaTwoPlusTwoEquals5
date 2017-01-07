# InvoicingTest_00

Initial code

# InvoicingTest_01

only difference:

* assertTrue("Invoice should have 1 item", false);
* fail("Invoice should have 1 item"); 

# InvoicingTest_02

method extraction: add a new method assertDocumentItemEquals

# InvoicingTest_03

add some cleanup logic in finally block

# InvoicingTest_04

method extraction: instance initialization is done in createXXX method

# InvoicingTest_05

move initialization logic to @before, and cleanup in @after

# InvoicingTest_06

add a new member attribute private List<Object> testObjects;
when instance is created, add instance to this list. So in @after, the instance could be 
cleaned up generically.

# InvoicingTest_07
test a new method: testRemoveItemQuantity

# InvoicingTest_08
new approach @Test(expected=java.lang.IndexOutOfBoundsException.class)
How does it work:
set breakpoint in class ExpectException, method @Override evaluate, line 24 fExpected.isAssignableFrom

# InvoicingTest_09

* all test methods are run in the same thread
* add new method to compare expected price
* more human readable format in junit display result, not pure method name now

When a class is annotated with RunWith, JUnit will invoke the class it references to run the
tests in that class instead of the runner built into JUnit.
 
Check the source code of class Parameterized:
public class Parameterized extends Suite
There is an annotation:
Annotation for a method which provides parameters to be injected into the **test class constructor** 
by **Parameterized**

```Java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public static @interface Parameters
```



