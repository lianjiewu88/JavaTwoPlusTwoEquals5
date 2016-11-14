package Interview.Songjun;

import java.awt.BorderLayout; 

import java.awt.Color; 

import java.awt.Cursor; 

import java.awt.Dimension; 

import java.awt.FlowLayout; 

import java.awt.Font; 

import java.awt.Frame; 

import java.awt.GridLayout; 

import java.awt.Point; 


import java.awt.event.ActionEvent; 

import java.awt.event.ActionListener; 

import java.text.ParseException; 

import java.text.SimpleDateFormat; 

import java.util.Calendar; 

import java.util.Date; 



import javax.swing.JButton; 

import javax.swing.JDialog; 


import javax.swing.JLabel; 

import javax.swing.JPanel; 

import javax.swing.JSpinner; 

import javax.swing.SpinnerNumberModel; 

import javax.swing.SwingConstants; 

import javax.swing.SwingUtilities; 

import javax.swing.border.LineBorder; 

import javax.swing.event.ChangeEvent; 

import javax.swing.event.ChangeListener; 



/** 

 * 

YouAreStupid 收集网上靠谱的例子，修改后的Swing日期  
68  
69  * 时间选择器,因为修改时间匆忙，希望有时间的朋友继续改进。  
70  
71  * 例子原作者:zjw  
72  
73  * 修改/完善：YouAreStupid  
 

 */ 

public class DateChooserJButton extends JButton { 


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DateChooser dateChooser = null; 

	private String preLabel = ""; 

	private String originalText = null; 

	private SimpleDateFormat sdf = null; 



	public DateChooserJButton() { 

		this(getNowDate()); 

	} 



	public DateChooserJButton(String dateString) { 

		this(); 

		setText(getDefaultDateFormat(), dateString); 

		initOriginalText(dateString); 

	} 



	public DateChooserJButton(SimpleDateFormat df, String dateString) { 

		this(); 
		setText(df, dateString); 

		//���䵱ǰ�����ڸ�ʽ���� 
		this.sdf = df; 

		//����ԭʼ����ʱ�� 
		Date originalDate = null; 

		try { 
			originalDate = df.parse(dateString); 
		} catch (ParseException ex) { 
			originalDate = getNowDate(); 
		} 
		initOriginalText(originalDate); 
	} 



	public DateChooserJButton(Date date) { 
		this("", date); 
		initOriginalText(date); 
	} 

	public DateChooserJButton(String preLabel, Date date) { 
		if (preLabel != null) { 
			this.preLabel = preLabel; 
		} 

		setDate(date); 

		//����ԭʼ������ʱ�� 
		initOriginalText(date);

		setBorder(null); 
		setCursor(new Cursor(Cursor.HAND_CURSOR)); 

		super.addActionListener(new ActionListener() { 
			@Override 
			public void actionPerformed(ActionEvent e) { 

				if (dateChooser == null) { 
					dateChooser = new DateChooser(); 
				} 

				Point p = getLocationOnScreen(); 

				p.y = p.y + 30; 

				dateChooser.showDateChooser(p); 
			} 
		}); 
	} 

	private static Date getNowDate() { 

		return Calendar.getInstance().getTime(); 
	} 

	private static SimpleDateFormat getDefaultDateFormat() { 

		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

	} 



	/** 

	 * �õ���ǰʹ�õ����ڸ�ʽ���� 

	 * @return ���ڸ�ʽ���� 

	 */ 

	public SimpleDateFormat getCurrentSimpleDateFormat(){ 

		if(this.sdf != null){ 

			return sdf; 

		}else{ 

			return getDefaultDateFormat(); 
		} 
	} 



	//����ԭʼ������ʱ�� 

	private void initOriginalText(String dateString) { 

		this.originalText = dateString; 

	} 



	//����ԭʼ������ʱ�� 

	private void initOriginalText(Date date) { 

		this.originalText = preLabel + getDefaultDateFormat().format(date); 

	} 



	/** 

	 * �õ���ǰ�����ԭʼ����ʱ�� 

	 * @return ��ǰ�����ԭʼ����ʱ�䣨δ�޸�ǰ������ʱ�䣩 

	 */ 

	public String getOriginalText() { 

		return originalText; 

	} 



	// ���Ǹ���ķ��� 

	@Override 

	public void setText(String s) { 

		Date date; 

		try { 

			date = getDefaultDateFormat().parse(s); 

		} catch (ParseException e) { 

			date = getNowDate(); 

		} 

		setDate(date); 

	} 



	public void setText(SimpleDateFormat df, String s) { 

		Date date; 

		try { 

			date = df.parse(s); 

		} catch (ParseException e) { 

			date = getNowDate(); 

		} 

		setDate(date); 

	} 



	public void setDate(Date date) { 

		super.setText(preLabel + getDefaultDateFormat().format(date)); 

	} 



	public Date getDate() { 

		String dateString = getText().substring(preLabel.length()); 

		try { 

			SimpleDateFormat currentSdf = getCurrentSimpleDateFormat(); 

			return currentSdf.parse(dateString); 

		} catch (ParseException e) { 

			return getNowDate(); 

		} 

	} 



	/** 

	 * ���Ǹ���ķ���ʹ֮��Ч 

	 * @param listener ��Ӧ������ 

	 */ 

	@Override 

	public void addActionListener(ActionListener listener) { 

	} 



	/** 

	 * �ڲ��࣬��Ҫ�Ƕ���һ��JPanel��Ȼ���������ص������������뱾JPanel�� 

	 * Ȼ���ٴ���һ��JDialog���ѱ��ڲ��ඨ���JPanel����JDialog�������� 

	 */ 

	private class DateChooser extends JPanel implements ActionListener, ChangeListener { 



		int startYear = 1980; // Ĭ�ϡ���С����ʾ��� 

		int lastYear = 2050; // Ĭ�ϡ������ʾ��� 

		int width = 390; // ������ 

		int height = 210; // ����߶� 

		Color backGroundColor = Color.gray; // ��ɫ 

		// ���������ɫ----------------// 

		Color palletTableColor = Color.white; // �������ɫ 

		Color todayBackColor = Color.orange; // ���챳��ɫ 

		Color weekFontColor = Color.blue; // ��������ɫ 

		Color dateFontColor = Color.black; // ��������ɫ 

		Color weekendFontColor = Color.red; // ��ĩ����ɫ 

		// ��������ɫ------------------// 

		Color controlLineColor = Color.pink; // ��������ɫ 

		Color controlTextColor = Color.white; // ��������ǩ����ɫ 

		Color rbFontColor = Color.white; // RoundBox����ɫ 

		Color rbBorderColor = Color.red; // RoundBox�߿�ɫ 

		Color rbButtonColor = Color.pink; // RoundBox��ťɫ 

		Color rbBtFontColor = Color.red; // RoundBox��ť����ɫ 

		/** ���DateChooserButtonʱ�����ĶԻ�����������������Ի����� */ 

		JDialog dialog; 

		JSpinner yearSpin; 

		JSpinner monthSpin; 

		JSpinner daySpin; 

		JSpinner hourSpin; 

		JSpinner minuteSpin; 

		JSpinner secondSpin; 

		JButton[][] daysButton = new JButton[6][7]; 



		DateChooser() { 



			setLayout(new BorderLayout()); 

			setBorder(new LineBorder(backGroundColor, 2)); 

			setBackground(backGroundColor); 



			JPanel topYearAndMonth = createYearAndMonthPanal(); 

			add(topYearAndMonth, BorderLayout.NORTH); 

			JPanel centerWeekAndDay = createWeekAndDayPanal(); 

			add(centerWeekAndDay, BorderLayout.CENTER); 

			JPanel buttonBarPanel = createButtonBarPanel(); 

			this.add(buttonBarPanel, java.awt.BorderLayout.SOUTH); 

		} 



		private JPanel createYearAndMonthPanal() { 

			Calendar c = getCalendar(); 

			int currentYear = c.get(Calendar.YEAR); 

			int currentMonth = c.get(Calendar.MONTH) + 1; 

			int currentHour = c.get(Calendar.HOUR_OF_DAY); 

			int currentMinute = c.get(Calendar.MINUTE); 

			int currentSecond = c.get(Calendar.SECOND); 



			JPanel result = new JPanel(); 

			result.setLayout(new FlowLayout()); 

			result.setBackground(controlLineColor); 



			yearSpin = new JSpinner(new SpinnerNumberModel(currentYear, startYear, lastYear, 1)); 

			yearSpin.setPreferredSize(new Dimension(48, 20)); 

			yearSpin.setName("Year"); 

			yearSpin.setEditor(new JSpinner.NumberEditor(yearSpin, "####")); 

			yearSpin.addChangeListener(this); 

			result.add(yearSpin); 



			JLabel yearLabel = new JLabel("��"); 

			yearLabel.setForeground(controlTextColor); 

			result.add(yearLabel); 



			monthSpin = new JSpinner(new SpinnerNumberModel(currentMonth, 1, 12, 1)); 

			monthSpin.setPreferredSize(new Dimension(35, 20)); 

			monthSpin.setName("Month"); 

			monthSpin.addChangeListener(this); 

			result.add(monthSpin); 



			JLabel monthLabel = new JLabel("��"); 

			monthLabel.setForeground(controlTextColor); 

			result.add(monthLabel); 



			//�������Ҫ�ܹ�ѡ��,��Ҫ�жϺܶණ��,����ÿ���·ֱ��ɶ�����,�Լ���������.����,�͸ɴ��Enable��Ϊfalse 

			daySpin = new JSpinner(new SpinnerNumberModel(currentMonth, 1, 31, 1)); 

			daySpin.setPreferredSize(new Dimension(35, 20)); 

			daySpin.setName("Day"); 

			daySpin.addChangeListener(this); 

			daySpin.setEnabled(false); 

			daySpin.setToolTipText("�����������������н���ѡ����һ�죡"); 

			result.add(daySpin); 



			JLabel dayLabel = new JLabel("��"); 

			dayLabel.setForeground(controlTextColor); 

			result.add(dayLabel); 



			hourSpin = new JSpinner(new SpinnerNumberModel(currentHour, 0, 23, 1)); 

			hourSpin.setPreferredSize(new Dimension(35, 20)); 

			hourSpin.setName("Hour"); 

			hourSpin.addChangeListener(this); 

			result.add(hourSpin); 



			JLabel hourLabel = new JLabel("ʱ"); 

			hourLabel.setForeground(controlTextColor); 

			result.add(hourLabel); 



			minuteSpin = new JSpinner(new SpinnerNumberModel(currentMinute, 0, 59, 1)); 

			minuteSpin.setPreferredSize(new Dimension(35, 20)); 

			minuteSpin.setName("Minute"); 

			minuteSpin.addChangeListener(this); 

			result.add(minuteSpin); 



			JLabel minuteLabel = new JLabel("��"); 

			hourLabel.setForeground(controlTextColor); 

			result.add(minuteLabel); 



			secondSpin = new JSpinner(new SpinnerNumberModel(currentSecond, 0, 59, 1)); 

			secondSpin.setPreferredSize(new Dimension(35, 20)); 

			secondSpin.setName("Second"); 

			secondSpin.addChangeListener(this); 

			result.add(secondSpin); 



			JLabel secondLabel = new JLabel("��"); 

			hourLabel.setForeground(controlTextColor); 

			result.add(secondLabel); 



			return result; 

		} 



		private JPanel createWeekAndDayPanal() { 

			String colname[] = {"��", "һ", "��", "��", "��", "��", "��"}; 

			JPanel result = new JPanel(); 

			// ���ù̶����壬������û����ı�Ӱ��������� 

			result.setFont(new Font("����", Font.PLAIN, 12)); 

			result.setLayout(new GridLayout(7, 7)); 

			result.setBackground(Color.white); 

			JLabel cell; 



			for (int i = 0; i < 7; i++) { 

				cell = new JLabel(colname[i]); 

				cell.setHorizontalAlignment(JLabel.RIGHT); 

				if (i == 0 || i == 6) { 

					cell.setForeground(weekendFontColor); 

				} else { 

					cell.setForeground(weekFontColor); 

				} 

				result.add(cell); 

			} 



			int actionCommandId = 0; 

			for (int i = 0; i < 6; i++) { 

				for (int j = 0; j < 7; j++) { 

					JButton numberButton = new JButton(); 

					numberButton.setBorder(null); 

					numberButton.setHorizontalAlignment(SwingConstants.RIGHT); 

					numberButton.setActionCommand(String.valueOf(actionCommandId)); 

					numberButton.addActionListener(this); 

					numberButton.setBackground(palletTableColor); 

					numberButton.setForeground(dateFontColor); 

					if (j == 0 || j == 6) { 

						numberButton.setForeground(weekendFontColor); 

					} else { 

						numberButton.setForeground(dateFontColor); 

					} 

					daysButton[i][j] = numberButton; 

					result.add(numberButton); 

					actionCommandId++; 

				} 

			} 



			return result; 

		} 



		/** �õ�DateChooserButton�ĵ�ǰtext����������Ϊ��ť�¼�������׼���ġ� */ 

		public String getTextOfDateChooserButton() { 

			return getText(); 

		} 



		/** �ָ�DateChooserButton��ԭʼ����ʱ��text����������Ϊ��ť�¼�������׼���ġ� */ 

		public void restoreTheOriginalDate() { 

			String originalText = getOriginalText(); 

			setText(originalText); 

		} 



		private JPanel createButtonBarPanel() { 

			JPanel panel = new JPanel(); 

			panel.setLayout(new java.awt.GridLayout(1, 2)); 



			JButton ok = new JButton("ȷ��"); 

			ok.addActionListener(new ActionListener() { 



				@Override 

				public void actionPerformed(ActionEvent e) { 

					//����ԭʼ����ʱ�� 

					initOriginalText(getTextOfDateChooserButton()); 

					//���������Ի��� 

					dialog.setVisible(false); 

				} 

			}); 

			panel.add(ok); 



			JButton cancel = new JButton("ȡ��"); 

			cancel.addActionListener(new ActionListener() { 



				@Override 

				public void actionPerformed(ActionEvent e) { 

					//�ָ�ԭʼ������ʱ�� 

					restoreTheOriginalDate(); 

					//���������Ի��� 

					dialog.setVisible(false); 

				} 

			}); 



			panel.add(cancel); 

			return panel; 

		} 



		private JDialog createDialog(Frame owner) { 

			JDialog result = new JDialog(owner, "����ʱ��ѡ��", true); 

			result.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE); 

			result.getContentPane().add(this, BorderLayout.CENTER); 

			result.pack(); 

			result.setSize(width, height); 

			return result; 

		} 



		void showDateChooser(Point position) { 

			Frame owner = (Frame) SwingUtilities.getWindowAncestor(DateChooserJButton.this); 

			if (dialog == null || dialog.getOwner() != owner) { 

				dialog = createDialog(owner); 

			} 

			dialog.setLocation(getAppropriateLocation(owner, position)); 

			flushWeekAndDay(); 

			dialog.setVisible(true); 

		} 



		Point getAppropriateLocation(Frame owner, Point position) { 

			Point result = new Point(position); 

			Point p = owner.getLocation(); 

			int offsetX = (position.x + width) - (p.x + owner.getWidth()); 

			int offsetY = (position.y + height) - (p.y + owner.getHeight()); 



			if (offsetX > 0) { 

				result.x -= offsetX; 

			} 



			if (offsetY > 0) { 

				result.y -= offsetY; 

			} 



			return result; 

		} 



		private Calendar getCalendar() { 

			Calendar result = Calendar.getInstance(); 

			result.setTime(getDate()); 

			return result; 

		} 



		private int getSelectedYear() { 

			return ((Integer) yearSpin.getValue()).intValue(); 

		} 



		private int getSelectedMonth() { 

			return ((Integer) monthSpin.getValue()).intValue(); 

		} 



		private int getSelectedHour() { 

			return ((Integer) hourSpin.getValue()).intValue(); 

		} 



		private int getSelectedMinite() { 

			return ((Integer) minuteSpin.getValue()).intValue(); 

		} 



		private int getSelectedSecond() { 

			return ((Integer) secondSpin.getValue()).intValue(); 

		} 



		private void dayColorUpdate(boolean isOldDay) { 

			Calendar c = getCalendar(); 

			int day = c.get(Calendar.DAY_OF_MONTH); 

			c.set(Calendar.DAY_OF_MONTH, 1); 

			int actionCommandId = day - 2 + c.get(Calendar.DAY_OF_WEEK); 

			int i = actionCommandId / 7; 

			int j = actionCommandId % 7; 

			if (isOldDay) { 

				daysButton[i][j].setForeground(dateFontColor); 

			} else { 

				daysButton[i][j].setForeground(todayBackColor); 

			} 

		} 



		private void flushWeekAndDay() { 

			Calendar c = getCalendar(); 

			c.set(Calendar.DAY_OF_MONTH, 1); 

			int maxDayNo = c.getActualMaximum(Calendar.DAY_OF_MONTH); 

			int dayNo = 2 - c.get(Calendar.DAY_OF_WEEK); 

			for (int i = 0; i < 6; i++) { 

				for (int j = 0; j < 7; j++) { 

					String s = ""; 

					if (dayNo >= 1 && dayNo <= maxDayNo) { 

						s = String.valueOf(dayNo); 

					} 

					daysButton[i][j].setText(s); 

					dayNo++; 

				} 

			} 

			dayColorUpdate(false); 

		} 



		/** 

		 * ѡ������ʱ����Ӧ�¼� 

		 */ 

		@Override 

		public void stateChanged(ChangeEvent e) { 

			JSpinner source = (JSpinner) e.getSource(); 

			Calendar c = getCalendar(); 

			if (source.getName().equals("Hour")) { 

				c.set(Calendar.HOUR_OF_DAY, getSelectedHour()); 

				setDate(c.getTime()); 

				return; 

			} 

			if (source.getName().equals("Minute")) { 

				c.set(Calendar.MINUTE, getSelectedMinite()); 

				setDate(c.getTime()); 

				return; 

			} 

			if (source.getName().equals("Second")) { 

				c.set(Calendar.SECOND, getSelectedSecond()); 

				setDate(c.getTime()); 

				return; 

			} 



			dayColorUpdate(true); 



			if (source.getName().equals("Year")) { 

				c.set(Calendar.YEAR, getSelectedYear()); 

			} else if (source.getName().equals("Month")) { 

				c.set(Calendar.MONTH, getSelectedMonth() - 1); 

			} 

			setDate(c.getTime()); 

			flushWeekAndDay(); 

		} 



		/** 

		 * ѡ������ʱ����Ӧ�¼� 

		 */ 

		@Override 

		public void actionPerformed(ActionEvent e) { 

			JButton source = (JButton) e.getSource(); 

			if (source.getText().length() == 0) { 

				return; 

			} 

			dayColorUpdate(true); 

			source.setForeground(todayBackColor); 

			int newDay = Integer.parseInt(source.getText()); 

			Calendar c = getCalendar(); 

			c.set(Calendar.DAY_OF_MONTH, newDay); 

			setDate(c.getTime()); 

			//��daySpin�е�ֵҲ���� 

			daySpin.setValue(Integer.valueOf(newDay)); 

		} 

	} 

	/** 

	 * ���Է��� 

	 */ 

//	public static void main(String[] args) { 
//
//		JFrame mainFrame = new JFrame("����"); 
//
//		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//
//		mainFrame.setSize(300, 300); 
//
//		mainFrame.setLayout(new java.awt.BorderLayout()); 
//
//		mainFrame.add(new DateChooserJButton(), java.awt.BorderLayout.CENTER); 
//
//
//
//		Toolkit kit = Toolkit.getDefaultToolkit(); 
//
//		Dimension screenSize = kit.getScreenSize(); 
//
//		int width = (int) screenSize.getWidth(); 
//
//		int height = (int) screenSize.getHeight(); 
//
//		int w = mainFrame.getWidth(); 
//
//		int h = mainFrame.getHeight(); 
//
//		mainFrame.setLocation((width - w) / 2, (height - h) / 2); 
//
//
//
//		mainFrame.setVisible(true); 
//
//	} 

}
