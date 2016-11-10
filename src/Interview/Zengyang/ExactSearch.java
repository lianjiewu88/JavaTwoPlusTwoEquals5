package Interview.Zengyang;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;


/**
 * @author Yang
 *
 */
public class ExactSearch {
	/*route of folder*/
	private String folderRoute;
	/*store files*/
	private Vector<File> fileSearchedList = null;
	/*total number of files*/
	private int totalNum = 0;
	/*is all time for create*/
	private boolean isCreateAllTime;
	private boolean isAccessAllTime;
	private boolean isModifyAllTime;
	
	private Vector<Date> date;
	
	public ExactSearch(String folderRoute, boolean isCreateAllTime, boolean isAccessAllTime, boolean isModifyAllTime, Vector<Date> datedata,JProgressBar progressbar, SearchResultScollPane scollPane)
	{
		this.folderRoute = folderRoute;
		this.isCreateAllTime = isCreateAllTime;
		this.isAccessAllTime = isAccessAllTime;
		this.isModifyAllTime = isModifyAllTime;
		this.fileSearchedList = new Vector<File>();
		date = new Vector<Date>(datedata);
		//fileList = new File(folderRoute).listFiles();
	
		File[] currentFolder = new File(this.folderRoute).listFiles();
		totalNum = currentFolder.length;
		doSearch(currentFolder, true, progressbar, scollPane);
	}
	
	private void doSearch(File[] folder, boolean isRootFolder, JProgressBar j, SearchResultScollPane scollPane)
	{
		int count = folder.length;
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
	
	private void addFileToResult(File file, SearchResultScollPane scollPane)
	{
		
		if(checkFile(file))
		{
			fileSearchedList.addElement(file);
			scollPane.addRowToTable(file.getName(), file.getParent());
		}
	}
	private boolean checkFile(File file)
	{
		if(checkTime(isCreateAllTime, date.get(0),date.get(1), getCreateDate(file)) && checkTime(isAccessAllTime, date.get(2),date.get(3), getAccessDate(file)) && checkTime(isModifyAllTime, date.get(4),date.get(5), getModifyDate(file)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private boolean checkTime(boolean isAllTime, Date startTime, Date endTime, Date currentTime)
	{
		if(isAllTime)
		{
			return true;
		}
		else
		{
			if(currentTime.before(endTime) && currentTime.after(startTime))
				return true;
			else
				return false;
		}
	}
	private Date getCreateDate(File file)
	{
		Pattern pattern = Pattern.compile(".*" + file.getName() + ".*");
		Matcher matcher;
		Date d = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dddd hh:mm");
		try {  
            Process p = Runtime.getRuntime().exec(  
                    "cmd /C dir " + file.getPath());  
            InputStream is = p.getInputStream();  
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  
            String str;  
          
            while ((str = br.readLine()) != null) 
            {   
            	matcher = pattern.matcher(str);
                if (matcher.matches()) 
                {  
                    try 
                    {
						d = sim.parse(str.substring(0, 17));
					} catch (ParseException e) 
                    {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    break;
                }
            }  
   
        } catch (java.io.IOException exc) {  
            exc.printStackTrace();  
        }
		return d;
	}
	private Date getAccessDate(File file)
	{
		Date d = new Date(file.lastModified());
		return d;
	}
	
	private Date getModifyDate(File file)
	{
		Date d = new Date(file.lastModified());
		return d;
	}
}
