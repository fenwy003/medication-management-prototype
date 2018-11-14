import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is the pop-up JFrame when the search database icon is clicked. 
 * It prompts the user to search for a medication. 
 * 
 * @author Rachel Lee
 * 
 */
public class SearchMedication extends JFrame implements ActionListener{
	private Color steelBlue = new Color(70, 130, 180);
	private Font arialBold = new Font("Arial", Font.BOLD, 14);
	private Color skyBlue = new Color(135, 206, 250);
	private JTextField medicationInput = new JTextField(20);
	private JFrame mainMenu;
	
	//creates the JFrame for the search medication page
	public SearchMedication(Point dim, JFrame mainMenu){
		JButton backButton = new JButton("Return to Main Menu ↩ ");
		JPanel returnArea = new JPanel();

		this.mainMenu = mainMenu;
		
		returnArea.setBackground(steelBlue);
		
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(steelBlue);
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		backButton.setActionCommand("goBackToMenu");
		backButton.addActionListener(this);

		returnArea.add(backButton);
		
		//set the size of the JFrame
		this.setSize(new Dimension(dim.x, dim.y));
		
		//set the title of the JFrame
		this.setTitle("Search Medication");
		
		//set the JFrame layout
		this.setLayout(new BorderLayout());
		
		//ensure the application is terminated when closed
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//add the fucntion of the search medication to the JFrame
		this.add(serachMedicationArea(),BorderLayout.CENTER);
		this.add(returnArea, BorderLayout.NORTH);
		this.add(createCopyRightArea(), BorderLayout.SOUTH);
		
		//set the JFrame to visible
		this.setVisible(true);
	}


	/**
	 * This function will create the display for the search database menu
	 * @return searchArea
	 */
	private JPanel serachMedicationArea(){
		JPanel searchArea = new JPanel();
		BoxLayout boxLay = new BoxLayout(searchArea, BoxLayout.Y_AXIS);

		JLabel searchMedicationLabel = new JLabel("Medication Name :", JLabel.CENTER);
		JButton searchMedicationButton = new JButton("Search");
		JLabel availableMedicationLabel = new JLabel(" Please only search for : insulin, sleeping pills or antibiotics.", JLabel.CENTER);

		//set the size of the input text box
		medicationInput.setMaximumSize(medicationInput.getPreferredSize());

		//set the styles for the labels
		searchMedicationLabel.setForeground(Color.BLACK);
		searchMedicationLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		searchMedicationLabel.setFont(arialBold);

		availableMedicationLabel.setForeground(Color.BLACK);
		availableMedicationLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		availableMedicationLabel.setFont(arialBold);

		//set the styles for the search button
		searchMedicationButton.setForeground(Color.BLACK);
		searchMedicationButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

		//set the action command of the search button
		searchMedicationButton.setActionCommand("searchMedicationDetails");
		searchMedicationButton.addActionListener(this);

		//set the background colour for the JPanel
		searchArea.setBackground(skyBlue);
		searchArea.setLayout(boxLay);

		//set the layout of the labels, input and button
		searchArea.add(Box.createRigidArea(new Dimension(0,50)));
		searchArea.add(searchMedicationLabel);
		searchArea.add(medicationInput);
		searchArea.add(Box.createRigidArea(new Dimension(0,30)));
		searchArea.add(searchMedicationButton);
		searchArea.add(Box.createRigidArea(new Dimension(0,100)));
		searchArea.add(availableMedicationLabel);

		//return the JPanel when this function is called
		return searchArea;
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
	 * This function sets the action of the search button is clicked.
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		
		//checks if the action "searchMedicationDetails" as been performed
		if (e.getActionCommand().equals("searchMedicationDetails")) {
			
			String inputMedication = medicationInput.getText();// gets the text being input

			//checks if the input text is equals to sleeping pills
			if(inputMedication.equals("sleeping pills")){
				//Set the current JFrame to invisible
				this.setVisible(false);
				
				// Call the animation dialog
				new LoadingMedicationDialog();
				
				//creates a new Sleeping Pill page and displays it on the screen
				new SleepingPillMedicationPage(new Point(500, 500), this);
			}

			//checks if the input text is equals to insulin
			else if(inputMedication.equals("insulin")){
				//Set the current JFrame to invisible
				this.setVisible(false);
				
				// Call the animation dialog
				new LoadingMedicationDialog();
				
				//creates a new insulin page and displays it on the screen
				new InsulinMedicationPage(new Point(500,500), this);
			}

			//checks if the input text is equals to antibiotics
			else if(inputMedication.equals("antibiotics")){
				//Set the current JFrame to invisible
				this.setVisible(false);
				
				// Call the animation dialog
				new LoadingMedicationDialog();
				
				//creates a new antibiotic page and displays it on screen
				new AntibioticsMedicationPage(new Point(500, 500), this);
			}

			//if the user did not input anything when the search button was clicked
			else{
				//an error message will pop up 
				JOptionPane.showMessageDialog(null, "Sorry this medication does not exist"
						,"Please try again", JOptionPane.ERROR_MESSAGE);
			}
		}

		//if the user clicks the back button it will take them back to the main menu
		else if(e.getActionCommand().equals("goBackToMenu")){
			this.dispose();
			mainMenu.setVisible(true);
		}
	}
}