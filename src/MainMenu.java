import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class generates a JFrame which allows users
 * to access to various functions that are appropriate
 * to patient user type. 
 * 
 * @author JohnFeng
 *
 */
public class MainMenu extends JFrame implements ActionListener{
	private Color skyBlue = new Color(135, 206, 250);
	private Color steelBlue = new Color(70, 130, 180);
	private Color aliceBlue = new Color(240, 248, 255);
	private Font arialBold = new Font("Arial", Font.BOLD, 14);
	private JFrame loginMenu;
	
	/**
	 * A constructor which accepts a Point variable and sets the window/frame dimension to 
	 * the value of the given Point.
	 * 
	 * @param dim a Point variable containing the dimension of the window/frame is to be set to
	 * @param loginMenu a JFrame which allows for user logins
	 */
	public MainMenu (Point dim, JFrame loginMenu) {
		this.loginMenu = loginMenu;
		
		// Sets the size/dimension, title and layout of the frame
		this.setSize(new Dimension(dim.x, dim.y));
		this.setTitle("Main Menu");
		this.setLayout(new BorderLayout());
		
		// Adds the panel containing the copyright information to the south
		this.add(createCopyRightArea(), BorderLayout.SOUTH);
	
		// Adds the panel containing the header banner to the north
		this.add(createHeaderBanner(), BorderLayout.NORTH);
		
		// Adds the panel containing the function icons and and labels to the center
		this.add(createFunctionArea(), BorderLayout.CENTER);
		
		// Ensure the java application is terminated when user closes the User Login frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Makes the frame appears in the center of the screen
		this.setLocationRelativeTo(null);
		
		// Once everything is ready to go, make the frame visible
		this.setVisible(true);
	}
	
	
	/**
	 * Creates a panel which contains a header banner/message
	 * 
	 * @return the header banner panel containing the header message
	 */
	private JPanel createHeaderBanner() {
		JPanel headerBanner = new JPanel();
		JLabel headerMessage = new JLabel("Please click on one of the functions to get started!");
		JButton logoutButton = new JButton("Logout");
		
		// Set the layout manager of the header banner panel
		headerBanner.setLayout(new BorderLayout());
		
		// Styles the header banner panel and change header message colour to white
		headerBanner.setBackground(steelBlue);
		headerMessage.setForeground(Color.WHITE);
		
		// Set up the logout button
		logoutButton.setBorderPainted(false);
		logoutButton.setFont(arialBold);
		logoutButton.setForeground(Color.WHITE);
		logoutButton.addActionListener(this);
		logoutButton.setActionCommand("Logout");
		
		// Horiztontally centers the header message
		headerMessage.setHorizontalAlignment(JLabel.CENTER);
		
		// Adds the header message to the headerBanner panel
		headerBanner.add(headerMessage, BorderLayout.CENTER);
		headerBanner.add(logoutButton, BorderLayout.LINE_END);
		
		return headerBanner;
	}
	
	
	/**
	 * Creates six JPanels where one of the panels will be the main panel (parent) of which the remaining
	 * five panels (children) will be added to. Each child will have an associated function icon and 
	 * corresponding function button.
	 * 
	 * @return the panel containing all the sub function panels
	 */
	public JPanel createFunctionArea() {
		JPanel mainFunctionArea = new JPanel(); // The main parent panel
		JPanel viewPrescriptionArea = new JPanel(); // 1st sub child panel
		JPanel schedulerArea = new JPanel(); // 2nd sub child panel
		JPanel searchArea = new JPanel(); // 3rd sub child panel
		
		JLabel viewPrescriptionLogoLabel = new JLabel(new ImageIcon(getClass().getResource("logos/viewPrescriptionLogo.png")));
		JButton viewPrescriptionButton = new JButton("View Prescription");
		JLabel schedulerLogoLabel = new JLabel(new ImageIcon(getClass().getResource("logos/schedulerLogo.png")));
		JButton schedulerButton = new JButton("Scheduler");
		JLabel searchLogoLabel = new JLabel(new ImageIcon(getClass().getResource("logos/searchMedicationLogo.png")));
		JButton searchButton = new JButton("Search Medication");
		
		// Style background colour of all panels accordingly
		viewPrescriptionArea.setBackground(skyBlue);
		mainFunctionArea.setBackground(skyBlue);
		schedulerArea.setBackground(aliceBlue);
		searchArea.setBackground(skyBlue);
		
		// Apply appropriate layout to each of the panels
		mainFunctionArea.setLayout(new GridLayout(0,3));
		viewPrescriptionArea.setLayout(new BoxLayout(viewPrescriptionArea, BoxLayout.Y_AXIS));
		schedulerArea.setLayout(new BoxLayout(schedulerArea, BoxLayout.Y_AXIS));
		searchArea.setLayout(new BoxLayout(searchArea, BoxLayout.Y_AXIS));
		
		// Center all function logo labels and buttons
		viewPrescriptionLogoLabel.setAlignmentX(CENTER_ALIGNMENT);
		schedulerLogoLabel.setAlignmentX(CENTER_ALIGNMENT);
		searchLogoLabel.setAlignmentX(CENTER_ALIGNMENT);
		viewPrescriptionButton.setAlignmentX(CENTER_ALIGNMENT);
		schedulerButton.setAlignmentX(CENTER_ALIGNMENT);
		searchButton.setAlignmentX(CENTER_ALIGNMENT);
		
		// Styles all function buttons and apply the same font (arialBold)
		viewPrescriptionButton.setForeground(Color.WHITE);
		viewPrescriptionButton.setFont(arialBold);
		viewPrescriptionButton.setBorderPainted(false);
		schedulerButton.setForeground(Color.BLACK);
		schedulerButton.setFont(arialBold);
		schedulerButton.setBorderPainted(false);
		searchButton.setForeground(Color.WHITE);
		searchButton.setFont(arialBold);
		searchButton.setBorderPainted(false);
		
		// Assign a action command and action listener to each of the function buttons
		viewPrescriptionButton.setActionCommand("viewPrescription");
		viewPrescriptionButton.addActionListener(this);
		schedulerButton.setActionCommand("scheduler");
		schedulerButton.addActionListener(this);
		searchButton.setActionCommand("searchMedication");
		searchButton.addActionListener(this);
		
		// Adds the components to the specified position with appropriate spacing to 1st panel.
		viewPrescriptionArea.add(Box.createRigidArea(new Dimension(0,100)));
		viewPrescriptionArea.add(viewPrescriptionLogoLabel);
		viewPrescriptionArea.add(Box.createRigidArea(new Dimension(0,20)));
		viewPrescriptionArea.add(viewPrescriptionButton);
		
		// Adds the components to the specified position with appropriate spacing to 2nd panel.
		schedulerArea.add(Box.createRigidArea(new Dimension(0,100)));
		schedulerArea.add(schedulerLogoLabel);
		schedulerArea.add(Box.createRigidArea(new Dimension(0,24)));
		schedulerArea.add(schedulerButton);
		
		// Adds the components to the specified position with appropriate spacing to 3rd panel.
		searchArea.add(Box.createRigidArea(new Dimension(0,100)));
		searchArea.add(searchLogoLabel);
		searchArea.add(Box.createRigidArea(new Dimension(0,24)));
		searchArea.add(searchButton);

		// Adds the child panels to the parent panel
		mainFunctionArea.add(viewPrescriptionArea);
		mainFunctionArea.add(schedulerArea);
		mainFunctionArea.add(searchArea);
		
		return mainFunctionArea;
	}
	
	
	/**
	 * Creates a panel which contains copyright information
	 * 
	 * @return the panel containing copyright information
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
	
	/**
	 * This methods listens to an event and respond accordingly to the listened
	 * event.
	 * 
	 * @param e the event to be listened
	 */
	public void actionPerformed(ActionEvent e) {
		// View prescription button is being clicked
		if (e.getActionCommand().equals("viewPrescription")) {
			JOptionPane.showMessageDialog(null, "Loading Electronic Prescription Page..."
					,"Loading Page...", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			new ViewPrescription(new Point(840, 800), this);
		}
		// Scheduler button is being clicked
		else if (e.getActionCommand().equals("scheduler")){
				JOptionPane.showMessageDialog(null, "Loading Scheduler..."
						,"Loading Function...", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			new Scheduler(new Point(700,700), this);
		}
		// Search medication button is being clicked
		else if (e.getActionCommand().equals("searchMedication")) {
			JOptionPane.showMessageDialog(null, "Loading Medication Database..."
					,"Loading Medication Database...", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			new SearchMedication(new Point(500,500), this);
		}
		else if (e.getActionCommand() == "Logout"){
			JOptionPane.showMessageDialog(null, "You've been logged out!"
					,"Logging Out", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			loginMenu.setVisible(true);
		}
	}
}
