package designPattern.builder;

public class ConcreteBuilder2 extends JerryBuilder {
	private JerryProduct product = new JerryProduct();

	@Override
	public void BuildPartA() {
		product.add("部件x");
	}

	@Override
	public void BuildPartB() {
		product.add("部件y");
	}

	@Override
	public JerryProduct getResult() {
		return product;
	}
}
