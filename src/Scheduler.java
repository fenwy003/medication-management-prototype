import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

/**
 * The scheduler opens a weekly calendar which displays times to take medication and events 
 * such as doctor’s appointments. Clicking on a time on the calendar opens a window to create 
 * a new event or medication time.
 * 
 * @author Austin Long
 *
 */
public class Scheduler extends JFrame implements ActionListener {
	// Creating the borderlayout, JComponents and styles.
	BorderLayout bl;
	JButton back;
	JButton forward;
	JButton home;
	JLabel weekDate;
	JTextField enterDate;
	JButton today;
	JButton calendar;
	JButton day;
	JButton week;
	JButton month;
	JPanel buttonArea;
	JPanel contentArea;
	JPanel header;
	JPanel copyRightArea = new JPanel();
	JScrollPane contentScroll;
	MainMenu mainMenu;
	private Color skyBlue = new Color(135, 206, 250);
	private Color steelBlue = new Color(70, 130, 180);
	private Color aliceBlue = new Color(240, 248, 255);
	private Font arialBold = new Font("Arial", Font.BOLD, 14);
	private Font arialBold2 = new Font("Arial", Font.BOLD, 20);

	/**This is the constructor for the scheduler JFrame, it takes a dimension as an argument to set the size and the mainmenu to allow
	 * returning to the main menu.
	 */ 
	public Scheduler (Point dim, MainMenu mainMenu) {
		this.setSize(new Dimension(dim.x, dim.y));
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		this.setTitle("Scheduler");
		this.setVisible(true);

		// The borderlayout is instantiated as bl and set to the JFrame.
		bl = new BorderLayout();
		this.mainMenu = mainMenu;
		this.setLayout(bl);
		//Adds the content area to the center of the borderlayout. The button area is set to north of the borderlayout.
		this.add(createContentArea(), BorderLayout.CENTER);
		this.add(createButtonArea(), BorderLayout.NORTH);
		// The program is terminated when the JFrame is closed.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Adds the panel containing the copyright information to the south
		this.add(createCopyRightArea(), BorderLayout.SOUTH);
	}

	/**
	 * This is the method that creates the button area JPanel as a BoxLayout that is laid 
	 * out horizontally (left to right).
	 */
	private JPanel createButtonArea() {
		buttonArea = new JPanel();
		// BoxLayout is set on the buttonarea JPanel horizontally with X_AXIS
		buttonArea.setLayout(new BoxLayout(buttonArea, BoxLayout.X_AXIS));
		/* The back, forward and home buttons are created and the icons to be used 
		 * are read from the /src folder and set to the button
		 */
		back = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("icons/back.png"));
			back.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		forward = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("icons/forward.png"));
			forward.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		home = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("icons/HomeIcon.png"));
			home.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		// The home button itself is turned invisible so you only see the icon as the button to click. ActionListener is added.
		home.setBorderPainted(false);
		home.setContentAreaFilled(false);
		home.setActionCommand("returnToMainMenu");
		home.addActionListener(this);
		weekDate = new JLabel("Oct 16 - 22, 2017");
		enterDate = new JTextField();
		calendar = new JButton();
		calendar.setActionCommand("Calendar");
		// Calendar icon is read and added
		calendar.addActionListener(this);
		try {
			Image img = ImageIO.read(getClass().getResource("icons/Calendar.png"));
			calendar.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		/* More buttons with icons have their borders and content areas turned invisible so you 
		 * only see the image as the button. Actionlisteners and action commands are set to the 
		 * button so there is a string associated with clicking the button to be used later to 
		 * make the buttons perform actions.
		 */
		calendar.setContentAreaFilled(false);
		calendar.setBorderPainted(false);
		today = new JButton("Today");
		today.setActionCommand("Today");
		today.addActionListener(this);
		day = new JButton("Day");
		day.setActionCommand("Day");
		day.addActionListener(this);
		week = new JButton("Week");
		month = new JButton("Month");
		month.setActionCommand("Month");
		month.addActionListener(this);
		// Buttons are added. Rigid areas are added between buttons in the boxlayout to create spacing between certain buttons.
		buttonArea.add(back);
		buttonArea.add(forward);
		buttonArea.add(home);
		buttonArea.add(weekDate);
		buttonArea.add(Box.createRigidArea(new Dimension(450, 1)));
		buttonArea.add(enterDate);
		buttonArea.add(calendar);
		buttonArea.add(Box.createRigidArea(new Dimension(40, 1)));
		buttonArea.add(today);
		buttonArea.add(Box.createRigidArea(new Dimension(20, 1)));
		buttonArea.add(day);
		buttonArea.add(week);
		buttonArea.add(month);
		back.setBorderPainted(false);
		forward.setBorderPainted(false);

		// The fonts of the JLabels and JButtons are added.
		weekDate.setFont(arialBold2);
		today.setFont(arialBold);
		day.setFont(arialBold);
		week.setFont(arialBold);
		month.setFont(arialBold);
		buttonArea.setBackground(skyBlue);
		week.setBackground(Color.blue);

		return buttonArea;	
	}

	/**
	 * The content area is created with a gridlayout to fit the buttons that will be the calendar dates into. JScrollpanel is added
	 * to the content area but couldn't get it to work.
	 */
	public JPanel createContentArea() {
		contentArea = new JPanel(new GridLayout(25, 8));
		// There is supposed to be a scrollpane but couldn't get it to work.
		contentScroll = new JScrollPane(contentArea);
		this.add(contentScroll, BorderLayout.CENTER);
		/* The header and times are added to string arrays so they can be added to the gridlayout in a loop. 
		 * A tCount is added to loop through the time array.
		 */
		String[] header = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		String[] time = {"Time", "12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
		int tCount = 0;

		/* Tried to make the header and times JLabels instead of buttons but couldn't figure it out. 
		 * Made the JButton that is to be added through the loop.
		 */
		JLabel y;
		JButton z;
		// If we want to identify each individual JButton they should be added to a JButton array.
		JButton[] buttons = new JButton[300];

		/* Nested for loops to create the calendar. The outer loop goes vertically, at the start of each inner loop it adds the time.
		 * After 12 it changes to PM.
		 */
		for (int b = 0; b < 25; b++) {
			for (int n = 0; n < 8; n++) {
				if (n == 0) {
					if (tCount < 13) {
						z = new JButton(time[tCount] + " AM");
					}
					else {
						z = new JButton(time[tCount] + " PM");
					}
					tCount = tCount + 1;
				}
				else {
					z = new JButton("");

				}
				if (b == 0) {
					z = new JButton(header[n]);
				}
				/* The border of the buttons is created. Content area is erased/blank. The buttons are added to the 
				 * gridlayout andactionlisteners are added.
				 */
				z.setPreferredSize(new Dimension(300,500));
				z.setBorder(BorderFactory.createLineBorder(Color.black));
				z.setContentAreaFilled(false);
				contentArea.add(z);
				z.setActionCommand("calSelect");
				z.addActionListener(this);
			}
		}

		return contentArea;
	}

	/**
	 * Creates the copyright area JPanel with default borderlayout. 
	 * @return
	 */
	public JPanel createCopyRightArea() {
		JPanel copyRightArea = new JPanel();
		JLabel copyRightLabel = new JLabel("© 2017 Group 1: fenwy003 | lonay007 |"
				+ " leerp002 | kenms003 | bisaj005");

		// Styles the copyrightarea panel and change copyright label colour to white
		copyRightArea.setBackground(Color.GRAY);
		copyRightLabel.setForeground(Color.WHITE);

		// Horiztontally centers the copyright information
		copyRightLabel.setHorizontalAlignment(JLabel.CENTER);

		// Adds the copyright information to the copyright panel
		copyRightArea.add(copyRightLabel);

		return copyRightArea;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// When you click a JButton in the contentarea a popup JFrame shows up.
		if (e.getActionCommand().equals("calSelect")) {
			SchedulerPopup popup = new SchedulerPopup(new Point (300, 300));

		}
		// This is the error message that shows up when you press a button for a feature that hasn't been implemented.
		if (e.getActionCommand().equals("Month")) {
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}

		if (e.getActionCommand().equals("Day")) {
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}

		if (e.getActionCommand().equals("Calendar")) {
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}

		if (e.getActionCommand().equals("Today")) {
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}
		/* Returns you to the main menu by removin the visibility of the JFrame and making the 
		 * mainmenu visible again.
		 */
		if (e.getActionCommand().equals("returnToMainMenu")) {
			this.setVisible(false);
			mainMenu.setLocationRelativeTo(null);
			mainMenu.setVisible(true);
		}
	}
}
