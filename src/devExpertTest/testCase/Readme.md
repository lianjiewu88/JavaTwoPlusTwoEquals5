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

SPLIT initialization logic to @before, and cleanup in @after

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
* add new method to compare expected price ( injected by @Parameter ) and actual price 
calculated by docItemActual.getExtendedPrice() in line 78 
* more human readable format in junit display result thanks to @parameter, not pure method name now

# How does @RunWith in test case 9 work

When a class is annotated with RunWith, JUnit will invoke the class it references to run the
tests in that class instead of the runner built into JUnit.
 
Check the source code of class Parameterized defined on top of class InvoicingTest_09:
```Java
public class Parameterized extends Suite
```

There is a comment there for @interface Parameters

> Annotation for a method which provides parameters to be injected into the **test class constructor** 
by **Parameterized**

```Java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public static @interface Parameters
```

## Suite

Parameterized is a Suite. Suite is a Runner.

```Java
/**
 * Using Suite as a runner allows you to manually
 * build a suite containing tests from many classes. It is the JUnit 4 equivalent of the JUnit 3.8.x
 * static junit.framework.Test suite() method. To use it, annotate a class
 * with **@RunWith(Suite.class)** and **@SuiteClasses({TestClass1.class, ...})**.
 * When you run this class, it will run all the tests in all the suite classes.
 *
 * @since 4.0
 */
public class Suite extends ParentRunner<Runner> {
    /**
     * Returns an empty suite.
     */
    public static Runner emptySuite() {
        try {
            return new Suite((Class<?>) null, new Class<?>[0]);
        } catch (InitializationError e) {
            throw new RuntimeException("This shouldn't be possible");
        }
    }
}
```
debugging could start by setting breakpoint in constructor of Parameterized class ( line 279 ) and:

* allParameters ( line 282 )
* createRunnersForParameters
* @Override createTest

# ProposalTest 

```Java
	@Test
	@Category(CategoryAddData.class)
	public void testAddItemQuantity(){ 
```
1. just mark method with a tag

2. How to run specific categorized class directly in Eclipse?

* If integrated with Maven: mvn install -P SlowTests
* else create a suite class, and run that suite class for testing.


```Java

@RunWith(Categories.class)  
@IncludeCategory(CategoryAddData.class)  
@SuiteClasses( { ProposalTest.class}) 

public class AddDataSuite {

}
```

## How does @RunWith(Categories.class) work

check its source code ( Categories.class line 98 ) :
From a given set of test classes, runs only the classes and methods that are
 * annotated with either the category given with the @IncludeCategory
 * annotation, or a subtype of that category.
 
 public class Categories extends Suite
 
 debug this method:
 ```Java
 @Override
 public boolean shouldRun(Description description) 
 ```
 
 # JUnit best practice
 
 * Do not use the test-case constructor to set up a test case
 * Don't assume the order in which tests within a test case run.
 There is no guarantee in the JUnit API documentation as to the order your tests 
 will be called in, because JUnit employs an ArrayList to store tests. 
 
 > BlockJUnit4ClassRunner, line 365, getFilteredChildren
 
 * Avoid writing test cases with side effects - each test case should be isolated from others.
 
 * Avoid test smells: conditional branches in a single test method
 
 * Consider locale when writing tests
 
 DO NOT use:
 
 ```Java
 Date date = DateFormat.getInstance ().parse ("dd/mm/yyyy"); 
 ```
 
 Use:
 
 ```Java
 Calendar cal = Calendar.getInstance ();
 Cal.set (yyyy, mm-1, dd);
 Date date = Calendar.getTime ();
 ```

 