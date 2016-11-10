/**
 * this class is used to search files by key word.
 * the constructor of the this class need two parameters
 * this first one is the folder route the other is the keyword need to be searched
 */
package Interview.Zengyang;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.JProgressBar;



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
	/*total number of files*/
	private int totalNum = 0;
	
	public SearchByKeyWord(String folderRoute, String keyword)
	{
		this.folderRoute = folderRoute;
		this.keyword = keyword;
		this.fileSearchedList = new Vector<File>();
		//fileList = new File(folderRoute).listFiles();
		File[] currentFolder = new File(this.folderRoute).listFiles();
		totalNum = currentFolder.length;
		doSearch(currentFolder);
	}
	
	public SearchByKeyWord(String folderRoute, String keyword, JProgressBar progressbar, SearchResultScollPane scollPane)
	{
		this.folderRoute = folderRoute;
		this.keyword = keyword;
		this.fileSearchedList = new Vector<File>();
		//fileList = new File(folderRoute).listFiles();
		File[] currentFolder = new File(this.folderRoute).listFiles();
		totalNum = currentFolder.length;
		doSearch(currentFolder, true, progressbar, scollPane);
	}
	
	/*search all the files in the folder with progress bar*/
	private void doSearch(File[] folder, boolean isRootFolder, JProgressBar j, SearchResultScollPane scollPane)
	{
		int count;
		if(folder == null)
		{
			count = 0;
		}
		else
		{
			count = folder.length;
		}
		for(int i = 0; i < count; i++)
		{
			if(isRootFolder)
			{
				j.setValue( (i + 1) * 100 / totalNum );
			}
			if(folder[i].isDirectory())
			{
				doSearch(new File(folder[i].getPath()).listFiles(), false, j, scollPane);
			}
			else
			{
				addFileToResult(folder[i], scollPane);
			}
		}
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
	
	private void addFileToResult(File file, SearchResultScollPane scollPane)
	{
		
		Pattern pattern = Pattern.compile(".*" + keyword + ".*");
		Matcher matcher = pattern.matcher(file.getName());
		if(matcher.matches())
		{
			fileSearchedList.addElement(file);
			scollPane.addRowToTable(file.getName(), file.getParent());
		}
	}
	
	
	public Vector<File> getSearchResult()
	{
		return fileSearchedList;
	}

}

