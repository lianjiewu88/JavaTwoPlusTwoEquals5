package Interview.yuancao;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;
import javax.swing.table.AbstractTableModel;

import org.w3c.dom.Attr;

import java.awt.Window.Type;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

//import progressBarDemo.ProgressBarDemo.Task;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Time;
import java.text.DateFormatSymbols;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Desktop;

public class FileSearcher extends JFrame implements ActionListener, PropertyChangeListener, ListSelectionListener {
	// Define Variables
	private JTextField RootFolder;
	private JTextField Filecontain;

	private JProgressBar progressBar = new JProgressBar();
	private JButton btnSearch = new JButton("Search");
	private Task task;

	private JList resultList;
	private DefaultListModel listModel_name;
	private JScrollPane listScrollPane;
	private JPanel resultPane;
	private String[] TableHeader = { "File Name", "Folder" };
	private JTable table = new JTable(new MyTableModel());
	// private Object[][] Result = {};

	private JLabel lblBaseFolder = new JLabel("Base Folder:");
	private JLabel lblContains = new JLabel("File Contains:");
	private JCheckBox lblCreated = new JCheckBox("Created:");
	private JCheckBox lblModified = new JCheckBox("Modified:");
	private JCheckBox lblAccessed = new JCheckBox("Accessed:");
	private JCheckBox chckbxCaseSensitive = new JCheckBox("Case sensitive");
	private JButton btnNewButton = new JButton("...");

	private String folderpath, keywords;
	private boolean CaseSensitive;

	private static CopyOnWriteArrayList<MyFile> SearchResult = new CopyOnWriteArrayList<>();
	private static AtomicInteger FileSearched = new AtomicInteger(0);
	private int TotalFiles;
	private Helper helper = new Helper();
	
	private final JComboBox create_0 = new JComboBox();
	private final JComboBox create_1 = new JComboBox();
	private final JComboBox modified_0 = new JComboBox();
	private final JComboBox modified_1 = new JComboBox();
	private final JComboBox accessed_0 = new JComboBox();
	private final JComboBox accessed_1 = new JComboBox();

	

	private JSpinner spinner = new JSpinner();
	private JSpinner spinner_1 = new JSpinner();
	private JSpinner spinner_2 = new JSpinner();
	private JSpinner spinner_3 = new JSpinner();
	private JSpinner spinner_4 = new JSpinner();
	private JSpinner spinner_5 = new JSpinner();
	private final JLabel lblTo = new JLabel("To");
	private final JLabel lblTo_1 = new JLabel("To");
	private final JLabel lblTo_2 = new JLabel("To");
	
	private int searchMethod;
	
	public FileSearcher() {

		init();
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FileSearcher tool = new FileSearcher();
				tool.setVisible(true);
			}
		});
	}

	public void init() {
		
		searchMethod = 0;
		
		// File searcher
		this.setTitle("File Searcher 1.0");
		this.setSize(536, 449);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// Label: Base Folder
		lblBaseFolder.setBounds(36, 10, 92, 42);
		getContentPane().add(lblBaseFolder);

		// Label: WordsContains
		lblContains.setBounds(36, 47, 92, 42);
		getContentPane().add(lblContains);

		// TextField: RootFolder
		RootFolder = new JTextField();
		RootFolder.setEditable(false);
		RootFolder.setBounds(130, 21, 341, 21);
		getContentPane().add(RootFolder);
		RootFolder.setColumns(10);

		// TextField: Filecontain
		Filecontain = new JTextField();
		Filecontain.setBounds(130, 58, 265, 21);
		getContentPane().add(Filecontain);
		Filecontain.setColumns(10);

		// Checkbox for Case
		chckbxCaseSensitive.setBounds(401, 57, 109, 23);
		getContentPane().add(chckbxCaseSensitive);

		// ProgressBar

		progressBar.setBounds(10, 176, 410, 20);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		getContentPane().add(progressBar);

		// Search
		// Button-----------------------------------------------------------------------
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reset();
				actionSearch();
			}
		});
		btnSearch.setBounds(430, 174, 80, 20);
		btnSearch.addActionListener(this);
		getContentPane().add(btnSearch);

		// Button: Choose
		// Folder---------------------------------------------------------------
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser Fchooser = new JFileChooser();
				Fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				Fchooser.showDialog(new JLabel(), "ѡ��");
				File selected = Fchooser.getSelectedFile();
				RootFolder.setText(selected.getPath());
			}
		});
		btnNewButton.setBounds(481, 20, 29, 23);
		getContentPane().add(btnNewButton);

		// Result List initialize
		listModel_name = new DefaultListModel();
		// listModel_name.addElement(new JButton("FileName"));
		resultList = new JList(listModel_name);
		resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultList.setVisibleRowCount(10);
		resultList.addListSelectionListener(this);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					String Filepath = (String) table.getValueAt(table.getSelectedRow(), 1)
							+ "\\"+ (String)table.getValueAt(table.getSelectedRow(), 0);
					Desktop desktop = Desktop.getDesktop();
					try {
						desktop.open(new File(Filepath));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		// listScrollPane = new JScrollPane(resultList);
		listScrollPane = new JScrollPane(table);
		listScrollPane.setBounds(10, 209, 500, 191);
		getContentPane().add(listScrollPane);
		
		
		lblCreated.setBounds(36, 100, 80, 20);
		getContentPane().add(lblCreated);

		lblModified.setBounds(36, 120, 80, 20);
		getContentPane().add(lblModified);
		
		
		lblAccessed.setBounds(36, 140, 80, 20);
		getContentPane().add(lblAccessed);
		
		
		create_0.setBounds(130, 100, 80, 20);
		getContentPane().add(create_0);
		
		create_1.setBounds(340, 100, 80, 20);
		getContentPane().add(create_1);
		
		modified_0.setBounds(130, 120, 80, 20);
		getContentPane().add(modified_0);
		modified_1.setBounds(340, 120, 80, 20);
		getContentPane().add(modified_1);
		
		accessed_0.setBounds(130, 140, 80, 20);
		getContentPane().add(accessed_0);
		accessed_1.setBounds(340, 140, 80, 20);
		getContentPane().add(accessed_1);
		
		LocalDate LD = LocalDate.now();
		for(int i = 0;i>-30;i--){
			create_0.addItem(LD.plusDays(i));
			create_1.addItem(LD.plusDays(i));
			modified_0.addItem(LD.plusDays(i));
			modified_1.addItem(LD.plusDays(i));
			accessed_0.addItem(LD.plusDays(i));
			accessed_1.addItem(LD.plusDays(i));
		};
		
		
		List<LocalTime> LT = new ArrayList<>();
		
		for(LocalTime Localt = LocalTime.MIN;Localt.isBefore(LocalTime.MAX.plusMinutes(-30));Localt = Localt.plusMinutes(30)){
			//System.out.println(Localt.toString());
			LT.add(Localt);
		}
		
		SpinnerListModel LModel = new SpinnerListModel(LT);
		SpinnerListModel LModel1 = new SpinnerListModel(LT);
		SpinnerListModel LModel2 = new SpinnerListModel(LT);
		SpinnerListModel LModel3 = new SpinnerListModel(LT);
		SpinnerListModel LModel4 = new SpinnerListModel(LT);
		SpinnerListModel LModel5 = new SpinnerListModel(LT);
		spinner = new JSpinner(LModel);
		spinner_1 = new JSpinner(LModel1);
		spinner_2 = new JSpinner(LModel2);
		spinner_3 = new JSpinner(LModel3);
		spinner_4 = new JSpinner(LModel4);
		spinner_5 = new JSpinner(LModel5);
		
		spinner.setBounds(220, 100, 80, 20);
		getContentPane().add(spinner);
		
		spinner_1.setBounds(430, 100, 80, 20);
		getContentPane().add(spinner_1);
		
		spinner_2.setBounds(220, 120, 80, 20);
		getContentPane().add(spinner_2);
		
		spinner_3.setBounds(430, 120, 80, 20);
		getContentPane().add(spinner_3);
		
		spinner_4.setBounds(220, 140, 80, 20);
		getContentPane().add(spinner_4);
		
		spinner_5.setBounds(430, 140, 80, 20);
		getContentPane().add(spinner_5);
	

		
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setBounds(310, 100, 20, 20);
		getContentPane().add(lblTo);
		
		lblTo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo_1.setBounds(310, 120, 20, 20);
		getContentPane().add(lblTo_1);
		
		lblTo_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo_2.setBounds(310, 140, 20, 20);
		getContentPane().add(lblTo_2);
		
	}

	private void actionSearch() {
		keywords = Filecontain.getText();
		CaseSensitive = chckbxCaseSensitive.isSelected();
		TotalFiles = helper.FileCount(RootFolder.getText());
		ThreadS root = new ThreadS();
		new Thread(root).start();
		// do{
		// try {
		// Thread.sleep(10);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }while(Thread.activeCount()>3);

	}

	private void reset() {
		FileSearched.set(0);
		SearchResult.clear();
		table.removeAll();
		progressBar.setValue(0);
	}

	class Task extends SwingWorker<Void, Void> {
		/*
		 * Main task. Executed in background thread.
		 */
		@Override
		public Void doInBackground() {
			// Initialize progress property.
			setProgress(0);
			while (progressBar.getValue() < 100) {
				// Sleep
				try {
					Thread.sleep(100);
				} catch (InterruptedException ignore) {
				}

				// Update progress.
				progressBar.setValue(100 * FileSearched.get() / TotalFiles);
				setProgress(progressBar.getValue());
			}
			return null;
		}

		@Override
		public void done() {
			Toolkit.getDefaultToolkit().beep();
			btnSearch.setEnabled(true);
			setCursor(null); // turn off the wait cursor
		}
	}

	public void actionPerformed(ActionEvent evt) {
		btnSearch.setEnabled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// Instances of javax.swing.SwingWorker are not reusuable, so
		// we create new instances as needed.
		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();
	}

	/**
	 * Invoked when task's progress property changes.
	 */
	public void propertyChange(PropertyChangeEvent evt) {

		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
			MyTableModel model = (MyTableModel) table.getModel();
			for (int n = table.getRowCount(); n < SearchResult.size(); n++) {
				// listModel_name.addElement(SearchResult.get(n).getName());
				table.setValueAt(SearchResult.get(n).getName(), n, 0);
				table.setValueAt(SearchResult.get(n).getParent(), n, 1);
				model.fireTableDataChanged();
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}

	public class MyTableModel extends AbstractTableModel {
		private String[] columnNames = TableHeader;
		// private Object[][] data = new Object[][]{};
		private Vector<Object[]> data = new Vector<Object[]>();

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data.get(row)[col];
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		/*
		 * Don't need to implement this method unless your table's editable.
		 */
		public boolean isCellEditable(int row, int col) {
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			return false;
		}

		public void setValueAt(Object value, int row, int col) {
			if (data.size() > row) {
				data.get(row)[col] = value;

			} else {
				Object[] newRow = new Object[getColumnCount()];
				newRow[col] = value;
				data.add(newRow);
			}
			fireTableCellUpdated(row, col);
		}

	}
	
	public class ThreadS implements Runnable {
		public ThreadS(){

		}
		public void run(){
			if(searchMethod == 0){
				Random random = new Random();
				MyFile root = new MyFile(RootFolder.getText());
				List<MyFile> Content = root.myListFiles();
				String KeyWords = Filecontain.getText();
				for(MyFile file:Content){
					try {
						Thread.sleep(random.nextInt(10));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(file.isDirectory()){
						ThreadS nThread = new ThreadS();
						new Thread(nThread).start();
					}
					else{//Begin to searchfile
					
						String filename = file.getName();
						boolean S_result = true;
						FileSearched.addAndGet(1);
						if(CaseSensitive == false){
							filename = filename.toLowerCase();
							KeyWords = KeyWords.toLowerCase();
						}
						
						if(filename.contains(KeyWords)){
							
						}
						else{
							continue;
						}
						
						//Check 
						try {
							
							BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
							if(lblCreated.isSelected()){
								LocalDate date0 = (LocalDate) create_0.getSelectedItem();
								LocalTime time0 = (LocalTime) spinner.getValue();
								LocalDateTime TimeFrom = LocalDateTime.of(date0,time0);
								LocalDate date1 = (LocalDate)create_1.getSelectedItem();
								LocalTime time1 = (LocalTime)spinner_1.getValue();
								LocalDateTime TimeTo = LocalDateTime.of(date1,time1);
								
								FileTime DT_from = FileTime.from(TimeFrom.toInstant(ZoneOffset.ofHours(0)));
								FileTime DT_to = FileTime.from(TimeTo.toInstant(ZoneOffset.ofHours(0)));
								
								FileTime C_Time = attr.creationTime();
								if(C_Time.compareTo(DT_from)>=0 & C_Time.compareTo(DT_to)<=0){
								}							
								else{
									continue;
								}
							}

							if(lblModified.isSelected()){
								LocalDate date0 = (LocalDate)modified_0.getSelectedItem();
								LocalTime time0 = (LocalTime)spinner_2.getValue();
								LocalDateTime TimeFrom = LocalDateTime.of(date0,time0);
								LocalDate date1 = (LocalDate)modified_1.getSelectedItem();
								LocalTime time1 = (LocalTime)spinner_3.getValue();
								LocalDateTime TimeTo = LocalDateTime.of(date1,time1);
								
								FileTime DT_from = FileTime.from(TimeFrom.toInstant(ZoneOffset.ofHours(0)));
								FileTime DT_to = FileTime.from(TimeTo.toInstant(ZoneOffset.ofHours(0)));
								
								FileTime M_Time = attr.lastModifiedTime();
								if(M_Time.compareTo(DT_from)>=0 & M_Time.compareTo(DT_to)<=0){
									
								}							
								else{
									continue;
								}
							}
							
							if(lblAccessed.isSelected()){
								LocalDate date0 = (LocalDate)accessed_0.getSelectedItem();
								LocalTime time0 = (LocalTime)spinner_4.getValue();
								LocalDateTime TimeFrom = LocalDateTime.of(date0,time0);
								LocalDate date1 = (LocalDate)accessed_1.getSelectedItem();
								LocalTime time1 = (LocalTime)spinner_5.getValue();
								LocalDateTime TimeTo = LocalDateTime.of(date1,time1);
								
								FileTime DT_from = FileTime.from(TimeFrom.toInstant(ZoneOffset.ofHours(0)));
								FileTime DT_to = FileTime.from(TimeTo.toInstant(ZoneOffset.ofHours(0)));
								
								FileTime A_Time = attr.lastModifiedTime();
								if(A_Time.compareTo(DT_from)>=0 & A_Time.compareTo(DT_to)<=0){
									
								}							
								else{
									continue;
								}
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(S_result){
							SearchResult.add(file);
						}
					}
				}
			}
		}
	}
}
