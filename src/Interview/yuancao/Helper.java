package Interview.yuancao;

import java.io.File;

import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class Helper {
	
	private static int count = 0;
	//Count Files
	public int FileCount(String Path){

		File root = new File(Path);
		File[] Content = root.listFiles();
		
		for(File file:Content){
			if(file.isDirectory()){
				FileCount(file.getPath());
			}
			else{
				count = count + 1;			
			}
		}
		return count;
	}
	public JSplitPane addContent(String FileName, String Path){
		JSplitPane NewSplitPane = new JSplitPane();
		JLabel TxtL = new JLabel(FileName);
		JLabel TxtR = new JLabel(Path);
		//NewSplitPane.setDividerLocation(NewSplitPane.getWidth()/3);
		NewSplitPane.setLeftComponent(TxtL);
		NewSplitPane.setRightComponent(TxtR);
		return NewSplitPane;
	}
}
