/**
 * This class is used to create the search UI
 */
package Interview.Zengyang;

import java.awt.Color;
import java.io.File;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;


/**
 * @author Yang
 *
 */
public class SearchUI extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -913373114766594L;
	
	/*WIDTH is the width of window*/
	private static final int WIDTH = 800;
	/*HEIGHT is the height of window*/
	private static final int HEIGHT = 600;
	
	private String folderRoute;
	private String keyWord;
	private SearchByKeyWord searchbykeyword;
	private ExactSearch exactSearch;
	
	private boolean isCreateAllTime;
	private boolean isAccessAllTime;
	private boolean isModifyAllTime;
	private Vector<Date> dates;
	private boolean isKeySearch;
	
	public SearchUI(String folderRoute, String keyWord)
	{
		this.folderRoute = folderRoute;
		this.keyWord = keyWord;
		this.isKeySearch = true;
		System.out.println("Jerry search keyword: " + keyWord + " on folder: " + folderRoute);
	}
	
	public SearchUI(String folderRoute, boolean isCreateAllTime, boolean isModifyAllTime, boolean isAccessAllTime, Vector<Date> dates)
	{
		this.folderRoute = folderRoute;
		this.isCreateAllTime = isCreateAllTime;
		this.isAccessAllTime = isAccessAllTime;
		this.isModifyAllTime = isModifyAllTime;
		this.dates = new Vector<Date>(dates);
		this.isKeySearch = false;
		this.keyWord = "";
	}
	
	private void createUI()
	{	
		System.out.println("Jerry in runnable createUI thread:" + Thread.currentThread().getId());
		SearchResultScollPane scollPane = new SearchResultScollPane(folderRoute, keyWord);
		ProgressBar progressBar = new ProgressBar();
		JPanel contentPane = new JPanel();
		

		//contentPane.setSize(this.getSize());
		
		Box verBox = Box.createVerticalBox();
		//BoxLayout boxlayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
		verBox.add(scollPane);
		verBox.add(progressBar);
		contentPane.add(verBox);
		//contentPane.add(progressBar);
		this.setContentPane(contentPane);
		//this.setContentPane(progressbarPane);
		
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		
		searchbykeyword = new SearchByKeyWord(folderRoute, keyWord, progressBar, scollPane);
	}
	
	private void createUI1()
	{	
		SearchResultScollPane scollPane = new SearchResultScollPane(folderRoute, keyWord);
		ProgressBar progressBar = new ProgressBar();
		JPanel contentPane = new JPanel();
		

		//contentPane.setSize(this.getSize());
		
		Box verBox = Box.createVerticalBox();
		//BoxLayout boxlayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
		verBox.add(scollPane);
		verBox.add(progressBar);
		contentPane.add(verBox);
		//contentPane.add(progressBar);
		this.setContentPane(contentPane);
		//this.setContentPane(progressbarPane);
		
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		
		exactSearch = new ExactSearch(folderRoute, isCreateAllTime, isAccessAllTime, isModifyAllTime, dates, progressBar, scollPane);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(isKeySearch)
		{
			createUI();
		}
		else
		{
			createUI1();
		}
	}
}
