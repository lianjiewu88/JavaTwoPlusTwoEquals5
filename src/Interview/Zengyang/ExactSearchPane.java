/**
 * 
 */
package Interview.Zengyang;

import javax.swing.BorderFactory;

//import org.jdesktop.swingx.JXDatePicker;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.*;


/**
 * @author Yang
 *
 */
public class ExactSearchPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8911365557968335057L;
	private static final String ALLTIME = "All Time";
	private static final String TIMERANGE = "range";
	
	private JLabel createTimeTitle = new JLabel("Created:");
	private JLabel modifyTimeTitle = new JLabel("Modified:");
	private JLabel accessTimeTitle = new JLabel("Accessed:");
	public JComboBox<String> createTimeCombo = new JComboBox<String>();
	public JComboBox<String> modifyTimeCombo = new JComboBox<String>();
	public JComboBox<String> accessTimeCombo = new JComboBox<String>();
	
	public JFormattedTextField jftofmodifystart = new JFormattedTextField(new Date());
	public JFormattedTextField jftofmodifyend = new JFormattedTextField(new Date());
	private JButton btnModifyDayStart = new JButton("...");
	private JButton btnModifyDayEnd = new JButton("...");
	
	public JFormattedTextField jftofaccessstart = new JFormattedTextField(new Date());
	public JFormattedTextField jftofaccessend = new JFormattedTextField(new Date());
	private JButton btnAccessDayStart = new JButton("...");
	private JButton btnAccessDayEnd = new JButton("...");
	
	public JFormattedTextField jftofcreatestart = new JFormattedTextField(new Date());
	public JFormattedTextField jftofcreateend = new JFormattedTextField(new Date());
	private JButton btnCreateDayStart = new JButton("...");
	private JButton btnCreateDayEnd = new JButton("...");
	
	public ExactSearchPane(){
		
		this.setBorder(BorderFactory.createTitledBorder("File Time"));
		Box verBox = Box.createVerticalBox();
		Box createTimeBox = Box.createHorizontalBox();
		Box modifyTimeBox = Box.createHorizontalBox();
		Box accessTimeBox = Box.createHorizontalBox();
		
		createTimeCombo.addItem(ALLTIME);
		createTimeCombo.addItem(TIMERANGE);
		modifyTimeCombo.addItem(ALLTIME);
		modifyTimeCombo.addItem(TIMERANGE);
		accessTimeCombo.addItem(ALLTIME);
		accessTimeCombo.addItem(TIMERANGE);
	//	JXDatePicker dpofstarttime = new JXDatePicker();
	//	JXDatePicker dpofendtime = new JXDatePicker();
		
		
		createTimeBox.add(createTimeTitle);
		createTimeBox.add(Box.createHorizontalStrut(20));
		createTimeBox.add(createTimeCombo);
		createTimeBox.add(Box.createHorizontalStrut(5));
		createTimeBox.add(jftofcreatestart);
		createTimeBox.add(btnCreateDayStart);
		createTimeBox.add(Box.createHorizontalStrut(5));
		createTimeBox.add(jftofcreateend);
		createTimeBox.add(btnCreateDayEnd);
		
		modifyTimeBox.add(modifyTimeTitle);
		modifyTimeBox.add(Box.createHorizontalStrut(17));
		modifyTimeBox.add(modifyTimeCombo);
		modifyTimeBox.add(Box.createHorizontalStrut(5));
		modifyTimeBox.add(jftofmodifystart);
		modifyTimeBox.add(btnModifyDayStart);
		modifyTimeBox.add(Box.createHorizontalStrut(5));
		modifyTimeBox.add(jftofmodifyend);
		modifyTimeBox.add(btnModifyDayEnd);
		
		accessTimeBox.add(accessTimeTitle);
		accessTimeBox.add(Box.createHorizontalStrut(8));
		accessTimeBox.add(accessTimeCombo);
		accessTimeBox.add(Box.createHorizontalStrut(5));
		accessTimeBox.add(jftofaccessstart);
		accessTimeBox.add(btnAccessDayStart);
		accessTimeBox.add(Box.createHorizontalStrut(5));
		accessTimeBox.add(jftofaccessend);
		accessTimeBox.add(btnAccessDayEnd);
		
		createTimeRangeItemListener createItemListener = new createTimeRangeItemListener();
		createTimeCombo.addItemListener(createItemListener);
		modifyTimeRangeItemListener modifyItemListener = new modifyTimeRangeItemListener();
		modifyTimeCombo.addItemListener(modifyItemListener);
		accessTimeRangeItemListener accessItemListener = new accessTimeRangeItemListener();
		accessTimeCombo.addItemListener(accessItemListener);
		
		verBox.add(createTimeBox);
		verBox.add(modifyTimeBox);
		verBox.add(accessTimeBox);
		this.add(verBox);
		
		initialStyle();
	}
	
	private void initialStyle()
	{
		jftofcreateend.setEditable(false);
		jftofcreatestart.setEditable(false);
		btnCreateDayEnd.setEnabled(false);
		btnCreateDayStart.setEnabled(false);
		jftofmodifyend.setEditable(false);
		jftofmodifystart.setEditable(false);
		btnModifyDayEnd.setEnabled(false);
		btnModifyDayStart.setEnabled(false);
		jftofaccessend.setEditable(false);
		jftofaccessstart.setEditable(false);
		btnAccessDayEnd.setEnabled(false);
		btnAccessDayStart.setEnabled(false);
	}
	
	

	
	private class createTimeRangeItemListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				if(e.getItem().toString().equals(ALLTIME))
				{
					jftofcreatestart.setEditable(false);
					jftofcreateend.setEditable(false);
					btnCreateDayEnd.setEnabled(false);
					btnCreateDayStart.setEnabled(false);
				}
				else
				{
					jftofcreateend.setEditable(true);
					jftofcreatestart.setEditable(true);
					btnCreateDayEnd.setEnabled(true);
					btnCreateDayStart.setEnabled(true);
				}
			}
		}
	}
	
	private class modifyTimeRangeItemListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				if(e.getItem().toString().equals(ALLTIME))
				{
					
					jftofmodifyend.setEditable(false);
					jftofmodifystart.setEditable(false);
					btnModifyDayEnd.setEnabled(false);
					btnModifyDayStart.setEnabled(false);
				
				}
				else
				{
				
					jftofmodifyend.setEditable(true);
					jftofmodifystart.setEditable(true);
					btnModifyDayEnd.setEnabled(true);
					btnModifyDayStart.setEnabled(true);
				
				}
			}
		}
	}
	
	private class accessTimeRangeItemListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				if(e.getItem().toString().equals(ALLTIME))
				{
					
					jftofaccessend.setEditable(false);
					jftofaccessstart.setEditable(false);
					btnAccessDayEnd.setEnabled(false);
					btnAccessDayStart.setEnabled(false);
				}
				else
				{
					jftofaccessend.setEditable(true);
					jftofaccessstart.setEditable(true);
					btnAccessDayEnd.setEnabled(true);
					btnAccessDayStart.setEnabled(true);
				}
			}
		}
	}
}
