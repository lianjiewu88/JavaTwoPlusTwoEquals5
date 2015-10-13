package innerTest;

class OuterClass {
	  private String language = "en";
	  private String region="US";
	  public class InnerClass { 
	       public void printOuterClassPrivateFields() {
	          String fields = "language= " + language + ";region = " + region;
		  System.out.println(fields);
	       }
	  }// end of inner class
	  
	  public void setLanguage(String lan) {
		  this.language = lan;
	  }

	public static void main(String[]args) {
	  OuterClass outer = new OuterClass();
	  OuterClass.InnerClass inner = outer.new InnerClass();
	  outer.setLanguage("de");
	  inner.printOuterClassPrivateFields();
	 }
	} 