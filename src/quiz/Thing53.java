package quiz;


class Thing{
    public Thing(int i ){ }
}

// Alternate constructor invocation - Private Constructor Capture
public class Thing53 extends Thing{

	private final int arg;
	public Thing53(){
		// Cannot refer to an instance field arg while explicitly invoking a constructor
	  // super(arg = 1);
		this(1); // 如果用Thing53(1)就有编译错误。
	}
	
    private Thing53(int i){
    	super(i);
    	arg = i;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
