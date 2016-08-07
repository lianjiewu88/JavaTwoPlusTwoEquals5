package designPattern.builder;

public class ConcreteBuilder1 extends JerryBuilder {

	private JerryProduct product = new JerryProduct();

	@Override
	public void BuildPartA() {
		product.add("部件A");
	}

	@Override
	public void BuildPartB() {
		product.add("部件B");
	}

	@Override
	public JerryProduct getResult() {
		return product;
	}
}
