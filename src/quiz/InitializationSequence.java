package quiz;

public class InitializationSequence {

	private String newName = getNewName();

	private static String getNewName() {
		System.out.println("in getNewName"); // execute first
		return "Jerry";
	}

	public InitializationSequence(){
		System.out.println("In constructor"); // execute second
	}
	
	public static void main(String[] args) {
		// InitializationSequence t = new InitializationSequence();
		
		// create it via reflection
		try {
			// Jerry: need to specify full qualified name
				Object m = Class.forName("quiz.InitializationSequence").newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch( ClassNotFoundException ex) {
				ex.printStackTrace();
			}
	}
}
