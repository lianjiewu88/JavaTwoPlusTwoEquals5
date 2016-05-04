package Interview.zheming;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MyFrame extends JFrame {
	private JLabel baseFolderName;
	private JLabel fileContainsName;
	private JButton browseBotton;
	private JButton searchButton;
	private JTextField showPath;
	private JTextField showKeyWord;
	private JCheckBox caseSenseBox;
	private JTable rs;
	private JScrollPane rsContain;
	private boolean caseSense;
	private JProgressBar searchProcess;
	private int fileTotalCount = 0 , nowCount = 0;
	
	public MyFrame() {
		super();
		this.setSize(500, 500);
		this.getContentPane().setLayout(null);
		addComponent();
		this.setTitle("Search Options");// ���ô��ڱ���
	}
	
	private void addComponent() {
		this.add(this.getShowKeyWord(), null);
		this.add(this.getShowPath(), null);

		this.add(this.getBrowseButton(), null);
		this.add(this.getSearchButton(), null);

		this.add(this.getBaseFolderLabel(), null);
		this.add(this.getFileContainsLabel(), null);

		this.add(this.getCaseSense(), null);

		this.add(this.getSearchProcess(),null);
		
		this.add(this.getRsContain(), null);
	}

	private JLabel getBaseFolderLabel() {
		if (baseFolderName == null) {
			baseFolderName = new JLabel();
			baseFolderName.setBounds(0, 49, 170, 30);
			baseFolderName.setText("Base Folders :");
		}
		return baseFolderName;
	}

	private JLabel getFileContainsLabel() {
		if (fileContainsName == null) {
			fileContainsName = new JLabel();
			fileContainsName.setBounds(0, 100, 170, 30);
			fileContainsName.setText("File Contains :");
		}
		return fileContainsName;
	}

	private JButton getBrowseButton() {
		if (browseBotton == null) {
			browseBotton = new JButton();
			browseBotton.setBounds(300, 49, 100, 30);
			browseBotton.setText("Browse...");
			browseBotton.addActionListener(new GetBase());// ��Ӽ������࣬����Ҫ����Ӧ���ɼ�������ķ���ʵ��
		}
		return browseBotton;
	}

	private class GetBase implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jfc.setDialogTitle("��ѡ��Base Folde");
			jfc.showDialog(new JLabel(), "ȷ��");
			
			File file = jfc.getSelectedFile();
			if(file != null)
				showPath.setText(file.getAbsolutePath());
		}
	}
	
	private int getFileNums(File file){
		int rs = 0;
		File[] files = file.listFiles();
		if (files == null)
			return rs;
		for (File f : files) {
			if (f.isDirectory()){
				rs++;
				rs += getFileNums(f);
			}
			else {
				rs++;
			}
		}
		return rs;
	}

	private JButton getSearchButton() {
		if (searchButton == null) {
			searchButton = new JButton();
			searchButton.setBounds(96, 150, 100, 30);
			searchButton.setText("search");
			searchButton.addActionListener(new SearchFile());
		}
		return searchButton;
	}

	private class SearchFile implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			File dir = new File(showPath.getText());
			nowCount = 0;
			searchProcess.setValue(0);
			fileTotalCount = getFileNums(dir);
			searchProcess.setMaximum(fileTotalCount);
			
			String[] fileNum = dir.list();
			ArrayList<File> path = new ArrayList<File>();
			RunSearch run = new RunSearch(dir, caseSense, path);
			Thread searchThread = new Thread(run);
			searchThread.start();
		}

	private class RunSearch implements Runnable{
		private File dir;
		private boolean caseSense;
		private ArrayList<File> al;
		
		public RunSearch(File dir, boolean caseSense, ArrayList<File> al){
			this.dir = dir;
			this.caseSense = caseSense;
			this.al = al;
		}
		private void writeToArr(File dir, boolean caseSense, ArrayList<File> al){
			String keyWord = showKeyWord.getText();
			File[] files = dir.listFiles();
			if (files == null)
				return ;
			for (File f : files) {
				if (f.isDirectory()){
					nowCount++;
					searchProcess.setValue(nowCount);
					writeToArr(f, caseSense, al);
				}else {
					if (caseSense && f.getName().contains(keyWord))
						al.add(f);
					else if (!caseSense && f.getName().toUpperCase().contains(keyWord.toUpperCase()))
						al.add(f);
					nowCount++;
					searchProcess.setValue(nowCount);
				}
			}
			return;
		}
		@Override
		public void run() {
			writeToArr(dir, caseSense, al);
			addToTable(al);
			return;
		}
	}
		
	private void addToTable(ArrayList<File> path) {
		if (rs.getModel().getRowCount() == 0) {
			DefaultTableModel tableModel = (DefaultTableModel) rs.getModel();
			for (File f : path)
				tableModel.addRow(new Object[] {
						f.getName(),
						f.getAbsolutePath().substring(0,f.getAbsolutePath().length()
											- f.getName().length()) });
				rs.invalidate();
			} else {
				DefaultTableModel tableModel = (DefaultTableModel) rs.getModel();
				tableModel.setRowCount(0);
				// ׷��Ԫ��
				for (File f : path)
					tableModel.addRow(new Object[] {
							f.getName(),f.getAbsolutePath().substring(0,f.getAbsolutePath().length()
											- f.getName().length()) });
				rs.invalidate();
				}
		}
	}

	private JTextField getShowPath() {
		if (showPath == null) {
			showPath = new JTextField();
			showPath.setBounds(96, 49, 160, 20);
		}
		return showPath;
	}

	private JTextField getShowKeyWord() {
		if (showKeyWord == null) {
			showKeyWord = new JTextField();
			showKeyWord.setBounds(96, 100, 160, 20);
		}
		return showKeyWord;
	}

	private JCheckBox getCaseSense() {
		if (caseSenseBox == null) {
			caseSenseBox = new JCheckBox("Case Sensitive");
			caseSense = false;
			caseSenseBox.setBounds(300, 100, 150, 20);
			caseSenseBox.addActionListener(new InverseCase());
		}
		return caseSenseBox;
	}

	private JTable getRs() {
		if (rs == null) {
			String[] headers = { "FileName", "Folder" };
			Object[][] cellData = null;
			DefaultTableModel model = new DefaultTableModel(cellData, headers) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			rs = new JTable(model);
			rs.addMouseListener(new TableHandle());
		}
		return rs;
	}

	private class TableHandle implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {//˫��
				int selectRows = rs.getSelectedRows().length;// ȡ���û���ѡ�е�����
				DefaultTableModel tableModel = (DefaultTableModel) rs
						.getModel();
				if (selectRows == 1) {
					int selectedRowIndex = rs.getSelectedRow(); // ȡ���û���ѡ����
					String fileName = (String) tableModel.getValueAt(
							selectedRowIndex, 0);
					String filePath = (String) tableModel.getValueAt(
							selectedRowIndex, 1);
					System.out.println(fileName + filePath);
					try {
						Runtime.getRuntime().exec(
								"C:\\WINDOWS\\system32\\notepad.exe "
										+ filePath + fileName);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}
		}
		@Override
	public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
	public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
	public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
	public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private JScrollPane getRsContain() {
		if (rsContain == null) {
			rsContain = new JScrollPane(getRs());
			rsContain.setBounds(20, 190, 400, 200);
		}
		return rsContain;
	}

	private class InverseCase implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			caseSense = !caseSense;
			System.out.println(caseSense);
		}
	}

	private JProgressBar getSearchProcess() {
		if (searchProcess == null) {
			searchProcess  = new JProgressBar(JProgressBar.HORIZONTAL ); 
			 searchProcess.setBounds(20, 400, 160, 20);
			 searchProcess.setStringPainted(true);
			 searchProcess.setMinimum(0);
		}
		return searchProcess;
	}
	
}