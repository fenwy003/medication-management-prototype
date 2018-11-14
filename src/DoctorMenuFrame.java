import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class holds the constructors to create a main interface
 * specific to a Doctor User - for the purposes of this prototype
 * the persona, Dr Jennifer Harper, created in the Design Document
 * is used.
 * 
 * @author  kenms003
 *
 */
public class DoctorMenuFrame extends JFrame implements ActionListener{
	private Color skyBlue = new Color(135, 206, 250);
	private Color steelBlue = new Color(70, 130, 180);
	private Color aliceBlue = new Color(240, 248, 255);
	public Font defaultFontHeader = new Font("Arial", Font.BOLD, 20);
	public Font defaultFontText = new Font("Arial", Font.PLAIN, 14);
	private Font arialBold = new Font("Arial", Font.BOLD, 14);
	private JFrame loginMenu;

	public DoctorMenuFrame(Point dim, JFrame loginMenu) {
		BorderLayout bl = new BorderLayout();
		
		this.loginMenu = loginMenu;
		
		this.setSize(new Dimension(dim.x, dim.y));
		this.setTitle("Doctor Menu");
		this.setLayout(bl);
		
		this.add(createUserIdentifier(), BorderLayout.NORTH);
		this.add(createMenuArea(), BorderLayout.CENTER);
		this.add(createCopyRightArea(), BorderLayout.SOUTH);
		
		// Makes the frame appears in the center of the screen
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	/**
	 * Returns a JPanel called welcomePanel that contains the JButton logoutButton
	 * and the JLabel userWelcomeMesage.
	 * 
	 * @return welcomePanel the panel containing the welcome user idenification.
	 */
	private JPanel createUserIdentifier(){
		JPanel userIdentifier = new JPanel();
		JLabel userWelcomeMessage = new JLabel (" Welcome, Jennifer Harper");
		JButton logoutButton = new JButton("Logout");
		
		userIdentifier.setLayout(new BorderLayout());
		userIdentifier.setBackground(steelBlue);

		userWelcomeMessage.setFont(defaultFontHeader);
		userWelcomeMessage.setForeground(Color.WHITE);
		
		logoutButton.setBorderPainted(false);
		logoutButton.setFont(arialBold);
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setActionCommand("Logout");
		logoutButton.addActionListener(this);
		
		userIdentifier.add(userWelcomeMessage, BorderLayout.WEST);
		userIdentifier.add(logoutButton, BorderLayout.LINE_END);
		
		return userIdentifier;	
	}
	
	/**
	 * Returns a JPanel called funtionPanel that contains JButtons, settingsButton,
	 * createPrescriptionButton, searchMedDButton, searchPatientButton and 
	 * chemicalRectButton, the JLabels and Image Icons that correspond with the 
	 * appropriate functions.
	 * 
	 * @return functionPanel the panel containing the menu information
	 */
	private JPanel createMenuArea() {
		JPanel functionPanel = new JPanel(); // Main JPanel that contains the five function icon panels
		// The five functions' icon panel
		JPanel settingsPanel = new JPanel();
		JPanel createPresPanel = new JPanel();
		JPanel searchMedDbPanel = new JPanel();
		JPanel searchPatientPanel = new JPanel();
		JPanel chemicalRectPanel = new JPanel();
		
		// Creates the necessary imageicons JLables
		ImageIcon settingsIcon = new ImageIcon(getClass().getResource("doctorIcons/Settings Icon.png"));
		JLabel settingIconLabel = new JLabel(settingsIcon);
		ImageIcon createPrescriptionIcon = new ImageIcon(getClass().getResource("doctorIcons/CreatePrescriptionIcon.png"));
		JLabel createPrescriptionIconLabel = new JLabel(createPrescriptionIcon);
		ImageIcon searchMedDbIcon = new ImageIcon(getClass().getResource("doctorIcons/SearchIcon.png"));
		JLabel searchMedDbIconLabel = new JLabel(searchMedDbIcon);
		ImageIcon searchPatientIcon = new ImageIcon(getClass().getResource("doctorIcons/Search File Icon.png"));
		JLabel searchPatientIconLabel = new JLabel(searchPatientIcon);
		ImageIcon chemicalRectIcon = new ImageIcon(getClass().getResource("doctorIcons/CompareMedicationIcons.png"));
		JLabel chemicalRectIconLabel = new JLabel(chemicalRectIcon);
		
		// The five functions' JButtons
		JButton settingsButton = new JButton("System Settings");
		JButton createPrescriptionButton = new JButton("Create Prescription");
		JButton searchMedDbButton = new JButton("Search Medical Database");
		JButton searchPatientButton = new JButton("Search Patient");
		JButton chemicalRectButton = new JButton("Chemical Reaction Check");
		
		// Centers all JLabels and JButtons
		settingIconLabel.setAlignmentX(CENTER_ALIGNMENT);
		createPrescriptionIconLabel.setAlignmentX(CENTER_ALIGNMENT);
		searchMedDbIconLabel.setAlignmentX(CENTER_ALIGNMENT);
		searchPatientIconLabel.setAlignmentX(CENTER_ALIGNMENT);
		chemicalRectIconLabel.setAlignmentX(CENTER_ALIGNMENT);
		settingsButton.setAlignmentX(CENTER_ALIGNMENT);
		createPrescriptionButton.setAlignmentX(CENTER_ALIGNMENT);
		searchMedDbButton.setAlignmentX(CENTER_ALIGNMENT);
		searchPatientButton.setAlignmentX(CENTER_ALIGNMENT);
		chemicalRectButton.setAlignmentX(CENTER_ALIGNMENT);
		
		// Setup buttons' action command and listener
		settingsButton.setActionCommand("Settings");
		createPrescriptionButton.setActionCommand("CreatePrescription");
		searchMedDbButton.setActionCommand("SearchMedication");
		searchPatientButton.setActionCommand("SearchPatient");
		chemicalRectButton.setActionCommand("CheckChemicalReaction");
		settingsButton.addActionListener(this);
		createPrescriptionButton.addActionListener(this);
		searchMedDbButton.addActionListener(this);
		searchPatientButton.addActionListener(this);
		chemicalRectButton.addActionListener(this);
		
		// Sets the layout of each panel
		functionPanel.setLayout(new GridLayout(0, 5));
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
		createPresPanel.setLayout(new BoxLayout(createPresPanel, BoxLayout.Y_AXIS));
		searchMedDbPanel.setLayout(new BoxLayout(searchMedDbPanel, BoxLayout.Y_AXIS));
		searchPatientPanel.setLayout(new BoxLayout(searchPatientPanel, BoxLayout.Y_AXIS));
		chemicalRectPanel.setLayout(new BoxLayout(chemicalRectPanel, BoxLayout.Y_AXIS));
		
		// Style background colour of all panels accordingly
		settingsPanel.setBackground(skyBlue);
		createPresPanel.setBackground(aliceBlue);
		searchMedDbPanel.setBackground(skyBlue);
		searchPatientPanel.setBackground(aliceBlue);
		chemicalRectPanel.setBackground(skyBlue);
		
		// Adds the settingsPanel's components with appropriate settings
		settingsPanel.add(Box.createRigidArea(new Dimension(70,100)));
		settingsPanel.add(settingIconLabel);
		settingsPanel.add(Box.createRigidArea(new Dimension(0,20)));
		settingsPanel.add(settingsButton);
		settingsButton.setBorderPainted(false);
		settingsButton.setForeground(Color.WHITE);
		settingsButton.setFont(arialBold);

		// Adds the createPresPanel's components with appropriate settings
		createPresPanel.add(Box.createRigidArea(new Dimension(80,100)));
		createPresPanel.add(createPrescriptionIconLabel);
		createPresPanel.add(Box.createRigidArea(new Dimension(-30,20)));
		createPresPanel.add(createPrescriptionButton);
		createPrescriptionButton.setBorderPainted(false);
		createPrescriptionButton.setFont(arialBold);
		
		// Adds the searchMedDbPanel's components with appropriate settings
		searchMedDbPanel.add(Box.createRigidArea(new Dimension(70,100)));
		searchMedDbPanel.add(searchMedDbIconLabel);
		searchMedDbPanel.add(Box.createRigidArea(new Dimension(0,20)));
		searchMedDbPanel.add(searchMedDbButton);
		searchMedDbButton.setBorderPainted(false);
		searchMedDbButton.setForeground(Color.WHITE);
		searchMedDbButton.setFont(arialBold);
		
		// Adds the searchPatientPanel's components with appropriate settings
		searchPatientPanel.add(Box.createRigidArea(new Dimension(70,100)));
		searchPatientPanel.add(searchPatientIconLabel);
		searchPatientPanel.add(Box.createRigidArea(new Dimension(0,20)));
		searchPatientPanel.add(searchPatientButton);
		searchPatientButton.setBorderPainted(false);
		searchPatientButton.setFont(arialBold);
		
		// Adds the chemicalRectPanel's components with appropriate settings
		chemicalRectPanel.add(Box.createRigidArea(new Dimension(70,100)));
		chemicalRectPanel.add(chemicalRectIconLabel);
		chemicalRectPanel.add(Box.createRigidArea(new Dimension(0,20)));
		chemicalRectPanel.add(chemicalRectButton);
		chemicalRectButton.setBorderPainted(false);
		chemicalRectButton.setForeground(Color.WHITE);
		chemicalRectButton.setFont(arialBold);
		
		// Adds the function panels to the main panel
		functionPanel.add(settingsPanel);
		functionPanel.add(createPresPanel);
		functionPanel.add(searchMedDbPanel);
		functionPanel.add(searchPatientPanel);
		functionPanel.add(chemicalRectPanel);
		
		return functionPanel;	
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
	 * ActionPerformed contains the Action Commands for the JButtons,
	 * Logout, Settings, Create Prescription, SearchMedication, SearchPatient
	 * and CheckChemicalReaction.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Logout"){
			JOptionPane.showMessageDialog(null, "You've been logged out!"
					,"Logging Out", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			loginMenu.setVisible(true);
		}
		else if (e.getActionCommand() == "Settings"){
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}
		else if (e.getActionCommand() == "CreatePrescription"){
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}
		else if (e.getActionCommand() == "SearchMedication"){
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}
		else if (e.getActionCommand() == "SearchPatient"){
			JOptionPane.showMessageDialog(null, "Function loading..."
					,"Function Loading", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			new SearchPatientFrame(new Point(600, 510), this);
		}
		else if (e.getActionCommand() == "CheckChemicalReaction"){
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}
	}
}
