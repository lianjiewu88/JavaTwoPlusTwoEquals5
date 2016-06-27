package Interview.yuancao;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyFile extends File {

	private boolean isChecked;
	
	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public MyFile(String pathname) {
		super(pathname);
		isChecked = false;
		// TODO Auto-generated constructor stub
	}
	
	public List<MyFile> myListFiles(){
		List<MyFile> Result =new ArrayList<>();
		File[] list=super.listFiles();
		for(File f:list){
			Result.add(new MyFile(f.getPath()));
		}
		return Result;
	}
}
