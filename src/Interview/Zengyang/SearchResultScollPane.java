/**
 * 
 */
package Interview.Zengyang;

import javax.swing.table.*;

import java.awt.Color;
import java.io.File;
import java.util.Vector;

import javax.swing.*;
/**
 * @author Yang
 *
 */
public class SearchResultScollPane extends JScrollPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3992245628296264832L;
	/*the column names of search result, including "filename" and "folder"*/
	private static final Object[] COLUMNNAMES = {"Filename","Folder"};
	private DefaultTableModel tablemode;
	private String folderRoute;
	private String keyWord;
	private SearchByKeyWord searchbykeyword;
	private JTable filelistview;
	
	public SearchResultScollPane(String folderRoute, String keyWord)
	{
		this.folderRoute = folderRoute;
		this.keyWord = keyWord;
		tablemode = new DefaultTableModel(COLUMNNAMES,0){
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		this.filelistview = new JTable(tablemode);
		initialTableStyle(filelistview);
		filelistview.doLayout();
		//initialTable();
		this.setViewportView(filelistview);
		//this.add(filelistview);
	}
	
	/*add one record to table*/
	public void addRowToTable(String path, String name)
	{
		Object[] rowData = new Object[2];
		rowData[0] = path;
		rowData[1] = name;
		tablemode.addRow(rowData);
	}
	
	/*initial the data of table*/
	private void initialTable()
	{
		searchbykeyword = new SearchByKeyWord(folderRoute, keyWord);
		Vector<File> searchresults = searchbykeyword.getSearchResult();
		for(int i = 0; i < searchresults.size(); i++)
		{
			Object[] rowData = new Object[2];
			rowData[1] = searchresults.get(i).getPath();
			rowData[0] = searchresults.get(i).getName();
			tablemode.addRow(rowData);
		}
	}
	
	/*initial the table style*/
	private void initialTableStyle(JTable j)
	{
		j.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		j.setRowSelectionAllowed(true);
		j.setGridColor(Color.BLACK);
		j.setShowGrid(true);
		j.setShowHorizontalLines(true);
		j.setShowVerticalLines(true);
		j.setFillsViewportHeight(true);
		DefaultTableCellRenderer dc = new DefaultTableCellRenderer();
		dc.setHorizontalAlignment(JTextField.LEFT);
		j.setDefaultRenderer(Object.class, dc);
		//j.setColumnWidth(0, 50);
	}
}
