package designPattern.builder;

public class JerryDirector {
	public void Construct(JerryBuilder builder){  
		builder.BuildPartA();  
		builder.BuildPartB();  
	}  
}
