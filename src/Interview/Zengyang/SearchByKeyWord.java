/**
 * this class is used to search files by key word.
 * the constructor of the this class need two parameters
 * this first one is the folder route the other is the keyword need to be searched
 */
package Interview.Zengyang;

import java.io.*;
import java.util.*;
import java.util.regex.*;


/**
 * @author Yang
 *
 */
public class SearchByKeyWord {
	/*route of folder*/
	private String folderRoute;
	/*key word*/
	private String keyword;
	/*store files*/
	private Vector<File> fileSearchedList = null;
	
	public SearchByKeyWord(String folderRoute, String keyword)
	{
		this.folderRoute = folderRoute;
		this.keyword = keyword;
		this.fileSearchedList = new Vector<File>();
		//fileList = new File(folderRoute).listFiles();
		File[] currentFolder = new File(this.folderRoute).listFiles();
		doSearch(currentFolder);
	}
	
	/*search all the files in the folder*/
	private void doSearch(File[] folder)
	{
			int count = folder.length;
			for(int i = 0; i < count; i++)
			{
				if(folder[i].isDirectory())
				{
					doSearch(new File(folder[i].getPath()).listFiles());
				}
				else
				{
					addFileToResult(folder[i]);
				}
			}
	}
	/*check if the file match the key word then decided whether to add it to the results*/
	private void addFileToResult(File file)
	{
		
		Pattern pattern = Pattern.compile(".*" + keyword + ".*");
		Matcher matcher = pattern.matcher(file.getName());
		if(matcher.matches())
		{
			fileSearchedList.addElement(file);
		}
	}
	
	public Vector<File> getSearchResult()
	{
		return fileSearchedList;
	}
}
