package Interview.zhigang;

import java.io.File;
import java.util.ArrayList;

public class FileSearcherCaseSensitive implements FileSearch {

	@Override
	public void searchFile(String basePath, String keyWord, ArrayList<FileProperty> fileList) {
		// TODO Auto-generated method stub
		String tempName = null; 
		File baseDir = new File(basePath);
		if (!baseDir.exists() || !baseDir.isDirectory()){  
			System.out.println("�ļ�����ʧ�ܣ�" + basePath + "����һ��Ŀ¼��");  
	    } else {
	    	String[] filelist = baseDir.list();
	    	for (int i = 0; i < filelist.length; i++) {  
                File readfile = new File(basePath + "\\" + filelist[i]);  
                if(!readfile.isDirectory()) {  
                    tempName =  readfile.getName();   
                   // if (fileNameMatch(keyWord, tempName)) {  
                        //ƥ��ɹ������ļ�����ӵ������  
                    	String FilePath = readfile.getAbsolutePath();
                    	String FileName = readfile.getName();
                    	FileProperty targetFile = new FileProperty(FileName,FilePath);
                        fileList.add(targetFile);   
                    //}  
                } else if(readfile.isDirectory()){  
                	searchFile(basePath + "\\" + filelist[i],keyWord,fileList);  
                }  
            }  
	    	
	    }
		
	}
	
	public boolean fileNameMatch(String keyWord, String tempName) {
		if(tempName.indexOf(keyWord) != -1) {
			return true;
		} else {
			
			return false;
		}
		
	}

}
