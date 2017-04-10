package innerTest;

public class LocalClassTest{
	
	public int add(int var1, int var2){
		return new lcl_local().add(var1, var2);
	}
	
	private class lcl_local { 
		public int add(int var1, int var2){
			return var1 + var2;
		}
	}
}
