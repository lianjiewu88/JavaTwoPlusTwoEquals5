package Interview.zhigang;

import java.util.ArrayList;

public interface FileSearch {
	public void searchFile(String basePath, String keyWord, ArrayList<FileProperty> fileList);
	public boolean fileNameMatch(String keyWord, String fileName);
}
