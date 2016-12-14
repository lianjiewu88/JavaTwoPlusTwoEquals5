package Interview.Songjun;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class FileListView extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public FileListView() {		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new MyTableModel(
			new Object[][] {
			},
			new String[] {
				"Filename", "Folder"
			}
		));
		
		// Ϊ�������¼�����
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					int row = ((JTable)e.getSource()).rowAtPoint(e.getPoint());
					String filename = (String) table.getModel().getValueAt(row, 0);
					String folder = (String) table.getModel().getValueAt(row, 1);
					String fullname = folder.concat(new String("\\")).concat(filename);
					openFileWith(fullname);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	public TableModel getModel() {
		return table.getModel();
	}
	
	public void updateUI(){
		table.updateUI();
	}
	
	// ������Process����������һ������ʹ��Runtime
	private void openFileWith(String file){
		Thread thd = new Thread(new Runnable(){
			public void run(){
				try {
					String[] arrCmd={"notepad",file};
					Process p = Runtime.getRuntime().exec(arrCmd);
					p.waitFor();
					System.out.println("return code:"+p.exitValue());
				} catch (IOException e) {
					System.err.println("IO error: " + e);
				} catch (InterruptedException e1) {
			        System.err.println("Exception: " + e1.getMessage());
			    }
			}
		});
		thd.start();
	}
}

//JTable ��Ϊ���ɱ༭
class MyTableModel extends DefaultTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyTableModel(Object[][] data, Object[] columnNames) {
		super(data,columnNames);
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;	
	}	
	
}
