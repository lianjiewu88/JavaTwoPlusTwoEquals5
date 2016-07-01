package Interview.zhigang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class SwingGui  extends JFrame{
	//获取搜索路径控件
	private JLabel baseLabel;
	private JTextField baseText;
	private JButton buttonBrowse;
	
	//获取关键词控件
	private JLabel keywordLabel ;
	private JTextField keywordText;
	private JCheckBox sensitiveCheckBox;
	
	//搜索控件
	private JButton searchButton;
	
	//进度条控件
	public JProgressBar searchProcessBar;
	
	//是否开启大小写敏感
	private boolean isCaseSensitive;
	  
	public SwingGui() {
		
		super("search options");
		
		//获取搜索路径控件
		baseLabel = new JLabel("Base Folder:");
		baseText = new JTextField();
		baseText.setEditable(false);
		buttonBrowse= new JButton("Browse");
		//为button添加文件夹选择事件处理
		buttonBrowse.addActionListener(new ButtonBrowseHandler());
		
		//获取关键词控件
		keywordLabel = new JLabel("File Contains:");
		keywordText = new JTextField();
		sensitiveCheckBox = new JCheckBox("Case Sensitive");
		sensitiveCheckBox.addActionListener(new sensitiveCheckBoxHandler());
		
		//搜索控件初始化
		searchButton = new JButton("Search");
		searchButton.setEnabled(false);
		searchButton.addActionListener(new searchButtonHandler());
		
		//进度条控件初始化
		searchProcessBar = new JProgressBar();
		searchProcessBar.setStringPainted(true);
		
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		
		//添加垂直方向
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(baseLabel).addComponent(baseText).addComponent(buttonBrowse));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(keywordLabel).addComponent(keywordText).addComponent(sensitiveCheckBox));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(searchButton));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(searchProcessBar));
		vGroup.addGap(5);
		layout.setVerticalGroup(vGroup);
		//添加水平方向
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGap(10);
		hGroup.addGroup(layout.createParallelGroup().addComponent(baseLabel).addComponent(keywordLabel));
		hGroup.addGap(10);
		hGroup.addGroup(layout.createParallelGroup().addComponent(baseText).addComponent(keywordText).addComponent(searchProcessBar));
		hGroup.addGap(10);
		hGroup.addGroup(layout.createParallelGroup().addComponent(buttonBrowse).addComponent(sensitiveCheckBox).addComponent(searchButton));
		hGroup.addGap(10);
		layout.setHorizontalGroup(hGroup);
		

	}
	
	//选择文件夹事件处理
	private class ButtonBrowseHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JFileChooser jfc=new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
			jfc.showDialog(new JLabel(), "选择");
			File file=jfc.getSelectedFile();
			if(file.isDirectory()){
				baseText.setText(file.getAbsolutePath());
				searchButton.setEnabled(true);
				System.out.println("Directory:"+file.getAbsolutePath());
				
			}else if(file.isFile()){
				System.out.println("文件:"+file.getAbsolutePath());
			}
			//System.out.println(jfc.getSelectedFile().getName());
			
		}
	}
	
	//搜索按钮事件处理
	private class searchButtonHandler implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String dirPath = baseText.getText().trim();
			String keyWords = keywordText.getText();
			fileSearcher searcher = new fileSearcher(dirPath,keyWords);
			searcher.execute();
			//System.out.println("search end!");
		}
	}
	
	
	// 大小写敏感选择处理
	private class sensitiveCheckBoxHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(sensitiveCheckBox.isSelected()) {
				isCaseSensitive = true;
			} else {
				isCaseSensitive = false;
			}
		}
	}

	// 文件搜索线程
	private class fileSearcher extends SwingWorker<Boolean, Integer> {
		private String basePath;
		private String keyWords;
		private ArrayList<FileProperty> resultList;

		public fileSearcher(String basePath, String keyWords) {
			this.basePath = basePath;
			this.keyWords = keyWords;
			this.resultList = new ArrayList<FileProperty>();
		}

		@Override
		protected Boolean doInBackground() throws Exception {
			// TODO Auto-generated method stub
			searchProcessBar.setValue(0);
			FileSearch fileSearch = null;
			if (isCaseSensitive) {
				fileSearch = new FileSearcherCaseSensitive();
			} else {
				fileSearch = new FileSearcherNoCaseSensitive();
			}
			ArrayList<FileProperty> fileList = new ArrayList<FileProperty>();
			fileSearch.searchFile(basePath, keyWords, fileList);
			// System.out.println("before remove:"+resultList.size());
			for (int i = 0; i < fileList.size(); i++) {				
				if (fileSearch.fileContentMatch(fileList.get(i).getFilePath(), keyWords)) {
					resultList.add(fileList.get(i));
				}
				double totalFiles = fileList.size();
				double proportion = ((double) (i+1) / totalFiles) * 100;
				Double tempDouble = new Double(proportion);

				publish(tempDouble.intValue());
			}
			return true;
			// return null;
		}
		
		//显示进度条
		protected void process(List<Integer> chunks) {
			Integer value = chunks.get(chunks.size() - 1);

			searchProcessBar.setValue(value);
		}

		@Override
		protected void done() {
			
			ListViewGui listView = new ListViewGui(resultList,SwingGui.this);
			listView.setVisible(true);
			//searchProcessBar.setValue(0);
		}
		
	}
	//主函数
	public static void main(String[] args) {
		
		final JFrame frame =new SwingGui();
		
		frame.setVisible(true);
		frame.setSize(500, 170);
		frame.setVisible(true);
		frame.setLocation(400, 200);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(false);
	}

}