package Interview.zhigang;

import java.io.File;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileSearcherCaseSensitive implements FileSearch {

	@Override
	public void searchFile(String basePath, String keyWord, ArrayList<FileProperty> fileList) {
		// TODO Auto-generated method stub
		String tempName = null; 
		File baseDir = new File(basePath);
		if (!baseDir.exists() || !baseDir.isDirectory()){  
			System.out.println("文件查找失败：" + basePath + "不是一个目录！");  
	    } else {
	    	String[] filelist = baseDir.list();
	    	for (int i = 0; i < filelist.length; i++) {  
                File readfile = new File(basePath + "\\" + filelist[i]);  
                if(!readfile.isDirectory()) {  
                    tempName =  readfile.getName();   
                   // if (fileNameMatch(keyWord, tempName)) {  
                        //匹配成功，将文件名添加到结果集  
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
	
	//文件名匹配
	public boolean fileNameMatch(String keyWord, String tempName) {
		if(tempName.indexOf(keyWord) != -1) {
			return true;
		} else {
			
			return false;
		}
		
	}
	
	
	//文件内容搜索，按行搜索
	public boolean fileContentMatch(String filePath,String keyWord) {
		boolean result = false;
		File f = new File(filePath);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();
			while (s != null) {
				int num = s.indexOf(keyWord);// 获得s所在的下标，
				if(num != -1) {
					result = true;
					break;
				}
				s = br.readLine();
			}

			br.close();// 关闭缓冲读入流及文件读入流的连接.
		} catch (FileNotFoundException e1) {
			System.err.println("File not found: " + filePath);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}

}