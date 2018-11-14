import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class holds the constructors to create an electronic patient file 
 * menu that allows a Doctor User - for the purposes of this prototype - 
 * to examine the information in a patient's file.
 * 
 * @author kenms003
 */
public class PatientFileMenuFrame extends JFrame implements ActionListener {
	private JFrame searchPatientFrame;
	private Color skyBlue = new Color(135, 206, 250);
	private Color steelBlue = new Color(70, 130, 180);
	private Font arialBold = new Font("Arial", Font.BOLD, 14);
	
	/**
	 * Takes the parameters, Point dim, that specifies the height and width
	 * of the PatientFileMenuFrame and JFrame searchPatientFrame, the JFrame 
	 * that uses the search details of the user and directs them to this menu, 
	 * and sets the position of the PatientFileMenuFrame.
	 * 
	 * @param dim
	 * @param searchPatientFrame
	 */
	public PatientFileMenuFrame (Point dim, JFrame searchPatientFrame) {
		this.searchPatientFrame = searchPatientFrame;
		
		this.setSize(new Dimension(dim.x, dim.y));
		this.setTitle("Patient File");
		this.setLayout(new BorderLayout());
		
		this.add(createReturnHeader(), BorderLayout.NORTH);
		this.add(createMenuDisplay(), BorderLayout.CENTER);
		this.add(createCopyRightArea(), BorderLayout.SOUTH);
		
		// Makes the frame appears in the center of the screen
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
	}
	
	/**
	 * Returns a JPanel menuDisplay that contains JButtons, summaryButton,
	 * fullPatientHistoryButton, referralHistoryButton, medicationHistoryButton,
	 * prescriptionHistoryButton and the corresponding JLabels and ImageIcons.
	 * Also contains a series of JLabels that identify the who the content of 
	 * the file is about.
	 * 
	 */
	private JPanel createMenuDisplay() {
		JPanel menuDisplay = new JPanel();
		menuDisplay.setLayout(new BorderLayout());
		
		JPanel fileIDPane = new JPanel();
		fileIDPane.setLayout(new BoxLayout(fileIDPane, BoxLayout.Y_AXIS));
		fileIDPane.setBackground(skyBlue);
		
		JLabel patientName = new JLabel("Patient Name: Mrs Prudence Stanley");
		JLabel patientFileNo = new JLabel("Patient Number: 00008692");
		JLabel patientVisit = new JLabel("Last Visit: 04/05/2017");
		
		// Keep the label color consistent
		patientName.setForeground(Color.WHITE);
		patientFileNo.setForeground(Color.WHITE);
		patientVisit.setForeground(Color.WHITE);
		
		// Center align labels and align labels' text to the same starting direction
		fileIDPane.add(Box.createRigidArea(new Dimension(300, 25)));
		fileIDPane.add(patientFileNo);
		fileIDPane.add(patientName);
		fileIDPane.add(patientVisit);
		fileIDPane.add(Box.createRigidArea(new Dimension(0, 25)));
		
		// Set up the function panel
		JPanel menuFunctions = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 5, 0, -50);
		menuFunctions.setLayout(gridLayout);
		menuFunctions.setBackground(skyBlue);
		
		// Set up the "Summary" function components
		ImageIcon summaryIcon = new ImageIcon(getClass().getResource("doctorIcons/Patient Summary.png"));
		JLabel summaryIconLabel = new JLabel(summaryIcon);
		JButton summaryButton = new JButton("Brief Summary");
		summaryButton.setBorderPainted(false);
		summaryButton.setForeground(Color.WHITE);
		summaryButton.setActionCommand("Summary");
		summaryButton.addActionListener(this);
		
		// Set up the "Full Patient History" function components
		ImageIcon fullPatientHistoryIcon = new ImageIcon(getClass().getResource("doctorIcons/full patient history.png"));
		JLabel fullPatientHistoryIconLabel = new JLabel(fullPatientHistoryIcon);
		JButton fullPatientHistoryButton = new JButton("Full Patient History");
		fullPatientHistoryButton.setBorderPainted(false);
		fullPatientHistoryButton.setForeground(Color.WHITE);
		fullPatientHistoryButton.setActionCommand("FullHistory");
		fullPatientHistoryButton.addActionListener(this);
		
		// Set up the "Referral History" function components
		ImageIcon referralHistoryIcon = new ImageIcon(getClass().getResource("doctorIcons/referral history.png"));
		JLabel referralHistoryIconLabel = new JLabel(referralHistoryIcon);
		JButton referralHistoryButton = new JButton("Referral History");
		referralHistoryButton.setBorderPainted(false);
		referralHistoryButton.setForeground(Color.WHITE);
		referralHistoryButton.setActionCommand("ReferralHistory");
		referralHistoryButton.addActionListener(this);
		
		// Set up the "Medication History" function components
		ImageIcon medicationHistoryIcon = new ImageIcon(getClass().getResource("doctorIcons/Medication Icon.png"));
		JLabel medicationHistoryIconLabel = new JLabel(medicationHistoryIcon);
		JButton medicationHistoryButton = new JButton("Medication History");
		medicationHistoryButton.setBorderPainted(false);
		medicationHistoryButton.setForeground(Color.WHITE);
		medicationHistoryButton.setActionCommand("MedicationHistory");
		medicationHistoryButton.addActionListener(this);
		
		// Set up the "Prescription History" function components
		ImageIcon prescriptionHistoryIcon = new ImageIcon(getClass().getResource("doctorIcons/Prescription Icon.png"));
		JLabel prescriptionHistoryIconLabel = new JLabel(prescriptionHistoryIcon);
		JButton prescriptionHistoryButton = new JButton("Prescription History");
		prescriptionHistoryButton.setBorderPainted(false);
		prescriptionHistoryButton.setForeground(Color.WHITE);
		prescriptionHistoryButton.setActionCommand("PresciptionHistory");
		prescriptionHistoryButton.addActionListener(this);
		
		// Adds the components to the function panel
		menuFunctions.add(summaryIconLabel);
		menuFunctions.add(fullPatientHistoryIconLabel);
		menuFunctions.add(referralHistoryIconLabel);
		menuFunctions.add(medicationHistoryIconLabel);
		menuFunctions.add(prescriptionHistoryIconLabel);
		menuFunctions.add(summaryButton);
		menuFunctions.add(fullPatientHistoryButton);
		menuFunctions.add(referralHistoryButton);
		menuFunctions.add(medicationHistoryButton);
		menuFunctions.add(prescriptionHistoryButton);
		
		// Add the fileIDPane & menuFunctions to the returning JPanel
		menuDisplay.add(fileIDPane, BorderLayout.NORTH);
		menuDisplay.add(menuFunctions, BorderLayout.CENTER);
		
		return menuDisplay;
	}
	
	/**
	 * Creates a panel which contains a JButton that directs user back to the main menu.
	 * 
	 * @return the panel containing the return button
	 */
	private JPanel createReturnHeader() {
		JPanel returnButtonArea = new JPanel();
		JButton returnButton = new JButton("Return to Search Patient ↩ ");
		
		// Styles the returning panel and the return button
		returnButtonArea.setBackground(steelBlue);
		returnButton.setBorderPainted(false);
		returnButton.setFont(arialBold);
		returnButton.setForeground(Color.WHITE);
		
		// Assign action command and action listener to the return button
		returnButton.setActionCommand("returnToMainMenu");
		returnButton.addActionListener(this);
		
		// Adds the return button to the returning panel
		returnButtonArea.add(returnButton);
		
		return returnButtonArea;
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
	 * ActionPerformed contains the ActionCommands for the JButtons, Search,  
	 * with a check to ensure that the correct input is present and returnToMainMenu.
	 *  
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "returnToMainMenu") {
			this.dispose();
			searchPatientFrame.setLocationRelativeTo(null);
			searchPatientFrame.setVisible(true);
		}
		else if (e.getActionCommand() == "Summary"){
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}
		else if (e.getActionCommand() == "FullHistory"){
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}
		else if (e.getActionCommand() == "ReferralHistory"){
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}
		else if (e.getActionCommand() == "MedicationHistory"){
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}
		else if (e.getActionCommand() == "PresciptionHistory"){
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}
	}
}