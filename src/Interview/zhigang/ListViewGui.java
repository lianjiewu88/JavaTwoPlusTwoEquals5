package Interview.zhigang;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class ListViewGui extends JFrame {

	private ArrayList<FileProperty> fileItemList;

	public ListViewGui(ArrayList<FileProperty> fileItemList) {
		super("File List");
		this.fileItemList = fileItemList;
		setBounds(100, 100, 500, 400);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponent();
	}

	private void initComponent() {
		String[] columnNames = { "File Name", "File Path" };
		Object[][] cellData = null;
		DefaultTableModel tableModel = new myDefaultTableModel(cellData, columnNames);
		JTable table = new JTable(tableModel);

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // �����λ��
					int col = ((JTable) e.getSource()).columnAtPoint(e.getPoint()); // �����λ��
																					// String
					String pathVal = (String) (tableModel.getValueAt(row, 1));

					String path = "notepad.exe " + pathVal ;
					try {

						Runtime.getRuntime().exec(path);
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}

				}
			}
		});

		for (int i = 0; i < fileItemList.size(); i++) {
			String fileName = fileItemList.get(i).getFileName();
			String filePath = fileItemList.get(i).getFilePath();
			String[] rowValues = { fileName, filePath };
			tableModel.addRow(rowValues);
		}
		JScrollPane scrollPane = new JScrollPane(table); // ֧�ֹ���
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

	private class myDefaultTableModel extends DefaultTableModel {
		public myDefaultTableModel(Object[][] obj, String[] header) {
			super(obj, header);
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		};

	}

}
