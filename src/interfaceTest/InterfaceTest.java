package interfaceTest;

interface IF_Java{
	public int getVersion();
}

class CL_Java implements IF_Java{
	static public IF_Java CreateVersionAPI(){
		return new CL_Java();
	}

	@Override
	public int getVersion() {
		return 1;
	}
}
public class InterfaceTest {

	public static void main(String[] args) {
		IF_Java result = CL_Java.CreateVersionAPI();
		System.out.println(result.getVersion());

	}

}
