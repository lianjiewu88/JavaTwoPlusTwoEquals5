/**
 * this class is the designed to create main user interface. And this class inherits from JPanel
 * it contains two labels, one textbox which used to input the route of root folder,
 * one select bar which used to choose the search way, one button which used to run search,
 * and one area to input parameters that depend on the search way.
 */
package Interview.Zengyang;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;

/**
 * @author Yang
 *
 */
public class OriginalMainUI extends JPanel implements UserInterface {

	private static final long serialVersionUID = 1L;
	
	/*WIDTH is the width of window*/
	private static final int WIDTH = 600;
	/*HEIGHT is the height of window*/
	private static final int HEIGHT = 300;
	/*The top container*/
	private JFrame mainFrame;
	/*store the folder route*/
	//private String folderRoute = new String();
	private JTextField keywordText;
	
	private ExactSearchPane exactSearchPane;
	
	@Override
	public void createUI() {
		// TODO Auto-generated method stub
		mainFrame = new JFrame("File Searching System");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		mainFrame.add(this, BorderLayout.NORTH);
		mainFrame.setSize(WIDTH, HEIGHT);
		//mainFrame.setContentPane(this,BorderLayout.NORTH);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		int width = screenSize.width;
		int height = screenSize.height;
		
		int x = (width - WIDTH) / 2;
		int y = (height - HEIGHT) / 2;
		
		mainFrame.setLocation(x, y);
		
		JButton btnSearch = new JButton("Search");
		JLabel route = new JLabel("Route:");
		JLabel searchMethod = new JLabel("Search Method:");
		final JTextField routeinput = new JTextField(15);
		//final JTextField conditions = new JTextField(15);
		JComboBox<String> conditions = new JComboBox<String>();
		conditions.addItem("Key Word");
		conditions.addItem("Exact Search");
		
		JPanel searchAreaPan = new JPanel();
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		
		constraints.weightx = 10;
		constraints.weighty = 10;
		
		
		
		add(route, constraints,0,0,1,1);
		add(searchMethod, constraints,0,1,1,1);
		constraints.anchor = GridBagConstraints.CENTER;
		add(btnSearch,constraints,0,4,2,1);
		buildKeyWordPane(searchAreaPan);
		//buildExactSearchPane(searchAreaPan1);
		add(searchAreaPan, constraints,0,3,2,1);
		//add(searchAreaPan1, constraints,0,4,2,1);
		constraints.anchor = GridBagConstraints.WEST;
		add(routeinput, constraints,1,0,1,1);
		add(conditions, constraints,1,1,1,1);
		mainFrame.setVisible(true);
		
		/*button click event*/
		btnSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				if(checkRouteFormat(routeinput.getText()))
				{
					if(conditions.getSelectedIndex() == 0)
					{
						String folderRoute = routeinput.getText(); 
						System.out.println("Jerry in button clicked thread:" + Thread.currentThread().getId());
						SearchUI sUI = new SearchUI(folderRoute, keywordText.getText());
						Thread t = new Thread(sUI);
						t.start();
					}
					else
					{
						String folderRoute = routeinput.getText();
						boolean create = checkAllTime(exactSearchPane.createTimeCombo.getSelectedItem().toString());
						boolean modify = checkAllTime(exactSearchPane.modifyTimeCombo.getSelectedItem().toString());
						boolean access = checkAllTime(exactSearchPane.accessTimeCombo.getSelectedItem().toString());
						Vector<Date> dates = generateDates(create, modify, access);
						SearchUI sUI = new SearchUI(folderRoute, create, modify, access, dates);
						Thread t = new Thread(sUI);
						t.start();
					}
				}
			}
		});
		
		/*Item changed event*/
		conditions.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					if(e.getItem().toString().equals("Key Word"))
					{
						searchAreaPan.removeAll();
						buildKeyWordPane(searchAreaPan);
						mainFrame.setVisible(true);
					}
					else
					{
						searchAreaPan.removeAll();
						buildExactSearchPane(searchAreaPan);
						mainFrame.setVisible(true);
					}
					//System.out.println(ItemEvent.SELECTED);
				}
			}
			
		});
	}
	/**
	 * this method is used to add component to the panel
	 * @param component is the component need to be added
	 * @param constraints is the layout style of grid bag
	 * @param x is the location of x-coordinate where this component should be
	 * @param y is the location of y-coordinate
	 * @param r means how many rows the component takes
	 * @param c means how many column
	 */
	private void add(Component component, GridBagConstraints constraints, int x, int y, int w, int h)
	{
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		add(component, constraints);
	}
	
	/**
	 * this method is used to build the panel for key work searching
	 * @param j is the parameter refer to pane area
	 */
	private void buildKeyWordPane(JPanel j)
	{
		keywordText = new JTextField(15);
		JLabel jl = new JLabel("Please input key word:");
		j.add(jl);
		j.add(keywordText);
		j.repaint();
	}
	
	/**
	 * this method is used to build the panel for exact searching
	 * @param j is the parameter refer to pane area
	 */
	private void buildExactSearchPane(JPanel j)
	{
		exactSearchPane = new ExactSearchPane();
		//JLabel jl = new JLabel("test");
		//j.add(jl);
		j.add(exactSearchPane);
		j.repaint();
		//j.remove(0);
		//j.removeAll();
		//j.repaint();
		//JLabel jl = new JLabel("Please input key word:");
		//j.add(jl);
		
	}
	
	private boolean checkAllTime(String str)
	{
		if(str.equals("All Time"))
			return true;
		else
			return false;
	}
	
	private Vector<Date> generateDates(boolean c, boolean m, boolean a)
	{
		Vector<Date> dates = new Vector<Date>();
		if(c)
		{
			Date tempdate = null;
			dates.add(tempdate);
			dates.add(tempdate);
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date tempdate = sdf.parse(exactSearchPane.jftofcreatestart.getText());
				dates.add(tempdate);
				tempdate = sdf.parse(exactSearchPane.jftofcreateend.getText());
				dates.add(tempdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dates.add(null);
				dates.add(null);
			}
		}
		
		if(m)
		{
			Date tempdate = null;
			dates.add(tempdate);
			dates.add(tempdate);
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date tempdate = sdf.parse(exactSearchPane.jftofmodifystart.getText());
				dates.add(tempdate);
				tempdate = sdf.parse(exactSearchPane.jftofmodifyend.getText());
				dates.add(tempdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dates.add(null);
				dates.add(null);
			}
		}
		if(a)
		{
			Date tempdate = null;
			dates.add(tempdate);
			dates.add(tempdate);
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date tempdate = sdf.parse(exactSearchPane.jftofaccessstart.getText());
				dates.add(tempdate);
				tempdate = sdf.parse(exactSearchPane.jftofaccessend.getText());
				dates.add(tempdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dates.add(null);
				dates.add(null);
			}
		}
		return dates;
	}
	
	private boolean checkRouteFormat(String route)
	{
		File tempfile = new File(route);
		if(tempfile.isDirectory())
		{
			return true;
		}
		else
		{
			JDialog jd = new JDialog();
			jd.getContentPane().add(new JLabel("Please input the right folder route."));
			
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			
			int width = screenSize.width;
			int height = screenSize.height;
			
			int x = (width - WIDTH) / 2;
			int y = (height - HEIGHT) / 2;
			jd.setLocation(x, y);
			jd.setSize(250, 100);
			jd.setVisible(true);
			return false;
		}
	}
}
