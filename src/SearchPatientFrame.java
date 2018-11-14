import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This class holds the constructors to create a search engine that allows
 * a Doctor User - for the purposes of this prototype - to enter a patient's
 * name and retrieve their patient file.
 * 
 * @author kenms003
 */
public class SearchPatientFrame extends JFrame implements ActionListener{
	private JTextField patientNameInput = new JTextField(20);
	private JFrame mainMenu;
	private Color skyBlue = new Color(135, 206, 250);
	private Color steelBlue = new Color(70, 130, 180);
	private Font arialBold = new Font("Arial", Font.BOLD, 14);

	/**
	 * Takes the parameters, Point dim, that specifies the height and width
	 * of the DoctorMenuFrame and JFrame mainMenu, the JFrame that uses the 
	 * login details of the user and directs them to this menu, and sets the 
	 * position of the DoctorMenuFrame.
	 * 
	 * @param dim
	 * @param mainMenu
	 */
	public SearchPatientFrame(Point dim, JFrame mainMenu) {
		this.mainMenu = mainMenu;
		
		this.setSize(new Dimension(dim.x, dim.y));
		this.setTitle("Search Bar");
		this.setLayout(new BorderLayout());
		
		// Makes the frame appears in the center of the screen
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(createReturnHeader(), BorderLayout.NORTH);
		this.add(createSearchPatientPanel(), BorderLayout.CENTER);
		this.add(createCopyRightArea(), BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	/**
	 * Returns a JPanel called searchPanel that contains the JButton searchButton
	 * and the JLabel searchLabel, that allows the user to enter a patient's name
	 * and opens their patient file.
	 * 
	 * @return searchPanel the panel containing the search bar and search button.
	 */
	private JPanel createSearchPatientPanel(){
		JPanel searchPanel = new JPanel();
		JLabel searchLabel = new JLabel("Patient Name:", JLabel.CENTER);
		JButton searchButton = new JButton("Search");
		
		// Setup the panel
		searchPanel.setBackground(skyBlue);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
		
		// Setup the label
		searchLabel.setForeground(Color.WHITE);
		searchLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		searchLabel.setFont(arialBold);
		
		// Setup the search button
		searchButton.setActionCommand("Search");
		searchButton.addActionListener(this);
		searchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		/* Since boxlayout is been used, the maximum size of the input field must have a constraint
		 * as otherwise it will expand to a unreasonable big size.
		 */
		patientNameInput.setMaximumSize(patientNameInput.getPreferredSize());
		
		// Add the components to the panel
		searchPanel.add(Box.createRigidArea(new Dimension(0,150)));
		searchPanel.add(searchLabel);
		searchPanel.add(patientNameInput);
		searchPanel.add(Box.createRigidArea(new Dimension(0,5)));
		searchPanel.add(searchButton); 
		
		return searchPanel;
	}
	
	/**
	 * Creates a panel which contains a JButton that directs user back to the main menu.
	 * 
	 * @return the panel containing the return button
	 */
	private JPanel createReturnHeader() {
		JPanel returnButtonArea = new JPanel();
		JButton returnButton = new JButton("Return to Main Menu ↩ ");
		
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
		if (e.getActionCommand() == "Search") {
			if (patientNameInput.getText().equals("Prudence Stanley")) {
				this.setVisible(false);
				new LoadingProgressBarDialog();
				new PatientFileMenuFrame(new Point(1000, 410), this);
			}
			else {
				JOptionPane.showMessageDialog(null, "Patient Record Not Found!"
						,"Patient Not Found", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getActionCommand() == "returnToMainMenu") {
			this.dispose();
			mainMenu.setLocationRelativeTo(null);
			mainMenu.setVisible(true);
		}
	}
}