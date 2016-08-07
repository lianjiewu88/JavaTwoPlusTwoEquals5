package designPattern.builder;

/*
 * 通过上面的代码，我们给出了经典创建者模式的核心代码形式，那么针对上面无疑有以下的几个缺点：
1、Ibuilder接口必须定义完整的组装流程，一旦定义就不能随意的动态修改。
2、Builder与具体的对象之间有一定的依赖关系，当然这里可以通过接口来解耦来实现灵活性。
3、Builder必须知道具体的流程。
那么针对上面的几个问题，我们如何来解决呢？我想前面的创建型模式已经给我了足够的经验，还是通过配置文件或者其他的形式来提供灵活性。
 */
public class TestBuilder {

	public static void main(String[] args) {

		JerryDirector director = new JerryDirector();

		JerryBuilder b1 = new ConcreteBuilder1();

		JerryBuilder b2 = new ConcreteBuilder2();

		director.Construct(b1);

		JerryProduct p1 = b1.getResult();

		p1.show();

		director.Construct(b2);

		JerryProduct p2 = b2.getResult();

		p2.show();
	}
}
