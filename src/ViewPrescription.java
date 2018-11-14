import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class generates a JFrame where users are able to view
 * their prescription and print off their prescription if
 * they wishes to.
 * 
 * @author JohnFeng
 *
 */
public class ViewPrescription extends JFrame implements ActionListener {
	private Color skyBlue = new Color(135, 206, 250);
	private Color steelBlue = new Color(70, 130, 180);
	private Color aliceBlue = new Color(240, 248, 255);
	private Color lavender = new Color(230, 230, 250);
	private Color darkSlateBlue = new Color(72, 61, 139);
	private Color deepBlue = new Color(0, 126, 189);
	
	private Font arialBold = new Font("Arial", Font.BOLD, 14);
	private Font arialTitleBold = new Font("Arial", Font.BOLD, 16);
	private Font arialNormal = new Font("Arial", Font.PLAIN, 12);
	private Font arialItalic = new Font("Arial", Font.ITALIC, 12);
	
	private String seletedPrescription = "- No Prescription Has Been Selected Yet -";
	private JLabel prescriptionTitleLabel = new JLabel(seletedPrescription, JLabel.CENTER);
	
	private ImageIcon blocker = new ImageIcon(getClass().getResource("prescriptionImages/" + "detailBlocker.png"));
	private JLabel prescription = new JLabel(blocker);
	private JScrollPane prescriptionScroll = new JScrollPane(prescription);
	private MainMenu mainMenu; // This instance variable is created so that the main menu can be made visible again
	private ImageIcon prescriptionArray[] = new ImageIcon[11];
	private int currentFrame = 0; // Index for the prescription blocker animation
	private int delay = 200; // Implement image change after 200 ms
	
	/**
	 * A constructor which accepts a Point variable and sets the window/frame dimension to 
	 * the value of the given Point.
	 * 
	 * @param dim a Point variable containing the dimension of the window/frame is to be set to
	 */
	public ViewPrescription (Point dim, MainMenu mainMenu) {
		// Sets the size/dimension, title and layout of the frame
		this.setSize(new Dimension(dim.x, dim.y));
		this.setTitle("Electronic Prescription");
		this.setLayout(new BorderLayout());
		this.setMainMenu(mainMenu);
		
		// Adds the panel containing the copyright information to the south
		this.add(createCopyRightArea(), BorderLayout.SOUTH);
	
		// Adds the panel containing the return button to the north
		this.add(createReturnHeader(), BorderLayout.NORTH);
		
		// Adds the panel containing electronic prescription components to the center
		this.add(createPrescriptionArea(), BorderLayout.CENTER);
		
		// Ensure the java application is terminated when user closes the User Login frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Makes the view prescription frame appears in the center of the screen
		this.setLocationRelativeTo(null);
		
		// Once everything is ready to go, make the frame visible
		this.setVisible(true);
	}
	
	
	/**
	 * Creates several JPanels where one panel acts as the parent panel of all other panels.
	 * Each panels contains different elements. The possible elements are: user's full name
	 * and photo, user's doctors and prescription received from the doctor in the form of 
	 * clickable dates, a title of the selected prescription, a print button and fit to screen
	 * button and the image of the selected prescription.
	 * 
	 * @return the parent JPanel containing all other JPanels
	 */
	public JPanel createPrescriptionArea() {
		JPanel mainPrescriptionArea = new JPanel(); // Main panel containing all components from all panels
		JPanel westDisplayArea = new JPanel(); // Parent panel of 1st & 2nd child panel
		JPanel userInformationArea = new JPanel(); // 1st child panel
		JPanel prescriptionInfoArea = new JPanel(); // 2nd child panel
		JPanel prescriptionDisplayArea = new JPanel(); // Parent panel of 3rd & 4th child panel
		JPanel prescriptionDisplayTitleArea = new JPanel(); // 3rd child panel
		JPanel prescriptionDisplayBodyArea = new JPanel(); // 4th child panel
		
		String unSpacedName = "Mrs. Mei-Hui Chen   ";
		
		JLabel userPhotoLabel = new JLabel(new ImageIcon(getClass().getResource("photos/userPhoto.png")));
		JLabel userNameLabel = new JLabel(String.format("%1$23s", unSpacedName));
		JLabel doctor1Label = new JLabel("✵ Dr. Mario Gates"); // User's 1st doctor
		JLabel doctor2Label = new JLabel("✵ Dr. Owen Brown"); // User's 2nd doctor
		JLabel doctor3Label = new JLabel("✵ Dr. Alice Castle"); // User's 3rd doctor
		
		JButton doctor1Pres1 = new JButton("✜ 04 Apr 2017"); // Most recent prescription from 1st doctor 
		JButton doctor1Pres2 = new JButton("✜ 27 Jan 2017"); // Previous prescription from 1st doctor 
		JButton doctor1Pres3 = new JButton("✜ 11 Aug 2016"); // Least recent prescription from 1st doctor 
		
		JButton doctor2Pres1 = new JButton("✜ 03 May 2017"); 
		JButton doctor2Pres2 = new JButton("✜ 03 Mar 2017"); 
		JButton doctor2Pres3 = new JButton("✜ 03 Jan 2017"); 
		JButton doctor2Pres4 = new JButton("✜ 03 Nov 2016"); 
		
		JButton doctor3Pres1 = new JButton("✜ 28 Apr 2016"); 
		
		JButton missingPrescriptionButton = new JButton("Prescription missing?");
		JButton printPrescriptionButton = new JButton("Print");
		
		// Style background colour of all panels accordingly
		mainPrescriptionArea.setBackground(lavender);
		userInformationArea.setBackground(skyBlue);
		westDisplayArea.setBackground(darkSlateBlue);
		prescriptionInfoArea.setBackground(darkSlateBlue);
		prescriptionDisplayTitleArea.setBackground(aliceBlue);
		prescriptionDisplayBodyArea.setBackground(deepBlue);
		
		// Apply appropriate layout to each of the panels
		mainPrescriptionArea.setLayout(new BorderLayout());
		westDisplayArea.setLayout(new BorderLayout());
		prescriptionDisplayArea.setLayout(new BorderLayout());
		userInformationArea.setLayout(new BoxLayout(userInformationArea, BoxLayout.Y_AXIS));
		prescriptionInfoArea.setLayout(new BoxLayout(prescriptionInfoArea, BoxLayout.Y_AXIS));
		prescriptionDisplayTitleArea.setLayout(new BorderLayout());
		prescriptionDisplayBodyArea.setLayout(new BoxLayout(prescriptionDisplayBodyArea, BoxLayout.Y_AXIS));
		
		// Disable scroll bar border
		prescriptionScroll.setBorder(null);
		
		// Center user photo label
		userPhotoLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		// Styles other JLabels and apply appropriate font and alignment
		userNameLabel.setForeground(Color.WHITE);
		userNameLabel.setFont(arialBold);
		userNameLabel.setAlignmentX(CENTER_ALIGNMENT);
		doctor1Label.setForeground(Color.WHITE);
		doctor1Label.setFont(arialBold);
		doctor1Label.setAlignmentX(CENTER_ALIGNMENT);
		doctor2Label.setForeground(Color.WHITE);
		doctor2Label.setFont(arialBold);
		doctor2Label.setAlignmentX(CENTER_ALIGNMENT);
		doctor3Label.setForeground(Color.WHITE);
		doctor3Label.setFont(arialBold);
		doctor3Label.setAlignmentX(CENTER_ALIGNMENT);
		prescriptionTitleLabel.setForeground(Color.BLACK);
		prescriptionTitleLabel.setFont(arialTitleBold);
		prescriptionTitleLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		// Styles and center all Dr.Mario's prescription buttons and apply the same font (arialNormal)
		doctor1Pres1.setForeground(Color.WHITE);
		doctor1Pres1.setFont(arialNormal);
		doctor1Pres1.setAlignmentX(CENTER_ALIGNMENT);
		doctor1Pres1.setBorderPainted(false);
		doctor1Pres2.setForeground(Color.WHITE);
		doctor1Pres2.setFont(arialNormal);
		doctor1Pres2.setAlignmentX(CENTER_ALIGNMENT);
		doctor1Pres2.setBorderPainted(false);
		doctor1Pres3.setForeground(Color.WHITE);
		doctor1Pres3.setFont(arialNormal);
		doctor1Pres3.setAlignmentX(CENTER_ALIGNMENT);
		doctor1Pres3.setBorderPainted(false);
		
		// Styles and center all Dr.Owen's prescription buttons and apply the same font (arialNormal)
		doctor2Pres1.setForeground(Color.WHITE);
		doctor2Pres1.setFont(arialNormal);
		doctor2Pres1.setAlignmentX(CENTER_ALIGNMENT);
		doctor2Pres1.setBorderPainted(false);
		doctor2Pres2.setForeground(Color.WHITE);
		doctor2Pres2.setFont(arialNormal);
		doctor2Pres2.setAlignmentX(CENTER_ALIGNMENT);
		doctor2Pres2.setBorderPainted(false);
		doctor2Pres3.setForeground(Color.WHITE);
		doctor2Pres3.setFont(arialNormal);
		doctor2Pres3.setAlignmentX(CENTER_ALIGNMENT);
		doctor2Pres3.setBorderPainted(false);
		doctor2Pres4.setForeground(Color.WHITE);
		doctor2Pres4.setFont(arialNormal);
		doctor2Pres4.setAlignmentX(CENTER_ALIGNMENT);
		doctor2Pres4.setBorderPainted(false);
		
		// Styles and Dr.May's prescription button and apply the same font as all the other prescription buttons
		doctor3Pres1.setForeground(Color.WHITE);
		doctor3Pres1.setFont(arialNormal);
		doctor3Pres1.setAlignmentX(CENTER_ALIGNMENT);
		doctor3Pres1.setBorderPainted(false);
		
		// Styles the missing prescription button
		missingPrescriptionButton.setForeground(Color.WHITE);
		missingPrescriptionButton.setFont(arialItalic);
		missingPrescriptionButton.setAlignmentX(CENTER_ALIGNMENT);
		missingPrescriptionButton.setBorderPainted(false);
		
		// Set the font of the print prescription button
		printPrescriptionButton.setFont(arialNormal);
		
		// Assign a action command and action listener to each of the prescription buttons
		doctor1Pres1.setActionCommand("Mario04Apr2017");
		doctor1Pres1.addActionListener(this);
		doctor1Pres2.setActionCommand("Mario27Jan2017");
		doctor1Pres2.addActionListener(this);
		doctor1Pres3.setActionCommand("Mario11Aug2016");
		doctor1Pres3.addActionListener(this);
		doctor2Pres1.setActionCommand("Owen03May2017");
		doctor2Pres1.addActionListener(this);
		doctor2Pres2.setActionCommand("Owen03Mar2017");
		doctor2Pres2.addActionListener(this);
		doctor2Pres3.setActionCommand("Owen03Jan2017");
		doctor2Pres3.addActionListener(this);
		doctor2Pres4.setActionCommand("Owen03Nov2016");
		doctor2Pres4.addActionListener(this);
		doctor3Pres1.setActionCommand("Alice28Apr2016");
		doctor3Pres1.addActionListener(this);
		missingPrescriptionButton.setActionCommand("missingPrescription");
		missingPrescriptionButton.addActionListener(this);
		printPrescriptionButton.setActionCommand("printPrescription");
		printPrescriptionButton.addActionListener(this);
		
		// Adds the components to the specified position with appropriate spacing to 1st child panel.
		userInformationArea.add(Box.createRigidArea(new Dimension(10, 25)));
		userInformationArea.add(userNameLabel);
		userInformationArea.add(Box.createRigidArea(new Dimension(0,10)));
		userInformationArea.add(userPhotoLabel);
		userInformationArea.add(Box.createRigidArea(new Dimension(0,20)));
		
		// Adds the components to the specified position with appropriate spacing to 2nd child panel.
		prescriptionInfoArea.add(Box.createRigidArea(new Dimension(0,10)));
		prescriptionInfoArea.add(doctor1Label);
		prescriptionInfoArea.add(doctor1Pres1);
		prescriptionInfoArea.add(doctor1Pres2);
		prescriptionInfoArea.add(doctor1Pres3);
		prescriptionInfoArea.add(Box.createRigidArea(new Dimension(0,10)));
		prescriptionInfoArea.add(doctor2Label);
		prescriptionInfoArea.add(doctor2Pres1);
		prescriptionInfoArea.add(doctor2Pres2);
		prescriptionInfoArea.add(doctor2Pres3);
		prescriptionInfoArea.add(doctor2Pres4);
		prescriptionInfoArea.add(Box.createRigidArea(new Dimension(0,10)));
		prescriptionInfoArea.add(doctor3Label);
		prescriptionInfoArea.add(doctor3Pres1);
		
		// Adds the 1st and 2nd child panel to their parent panel
		westDisplayArea.add(userInformationArea, BorderLayout.NORTH);
		westDisplayArea.add(prescriptionInfoArea, BorderLayout.CENTER);
		westDisplayArea.add(missingPrescriptionButton, BorderLayout.SOUTH);
		
		// Adds the components to the specified position with appropriate spacing to 3rd child panel.
		prescriptionDisplayTitleArea.add(prescriptionTitleLabel, BorderLayout.CENTER);
		prescriptionDisplayTitleArea.add(printPrescriptionButton, BorderLayout.EAST);
		
		// Adds the components to 4th child panel.
		prescriptionDisplayBodyArea.add(prescriptionScroll);

		// Adds the 3rd and 4th child panel to their parent panel
		prescriptionDisplayArea.add(prescriptionDisplayTitleArea, BorderLayout.NORTH);
		prescriptionDisplayArea.add(prescriptionDisplayBodyArea, BorderLayout.CENTER);
		
		// Adds parent panels to the main panel
		mainPrescriptionArea.add(westDisplayArea, BorderLayout.WEST);
		mainPrescriptionArea.add(prescriptionDisplayArea, BorderLayout.CENTER);
		
		return mainPrescriptionArea;
	}
	
	
	/**
	 * This setter is created so that the main menu can be made visible again.
	 * 
	 * @param mainMenu the main menu frame of the application
	 */
	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
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
	 * Initialises Dr.Alice's prescription loading animation's image playlist array
	 * 
	 */
	public void initialiseDrAlicePrescription() {
		// Adds the images into the array in order they should appear in the animation
		for (int i=0; i<prescriptionArray.length; i++) {
			prescriptionArray[i] = new ImageIcon(getClass().getResource("prescriptionImages/" + "DrAlice" + i + ".png"));
		}
	}
	
	/**
	 * Initialises Dr.Mario's prescription loading animation's image playlist array
	 * 
	 */
	public void initialiseDrMarioPrescription() {
		// Adds the images into the array in order they should appear in the animation
		for (int i=0; i<prescriptionArray.length; i++) {
			prescriptionArray[i] = new ImageIcon(getClass().getResource("prescriptionImages/" + "DrMarioGates" + i + ".png"));
		}
	}
	
	/**
	 * Initialises Dr.Mario's prescription loading animation's image playlist array
	 * 
	 */
	public void initialiseDrOwenPrescription() {
		// Adds the images into the array in order they should appear in the animation
		for (int i=0; i<prescriptionArray.length; i++) {
			prescriptionArray[i] = new ImageIcon(getClass().getResource("prescriptionImages/" + "DrOwenBrown" + i + ".png"));
		}
	}
	
	
	/**
	 * This methods listens to an event and respond accordingly to the listened
	 * event.
	 * 
	 * @param e the event to be listened
	 */
	public void actionPerformed(ActionEvent e) {
		// Change images every 200 ms
		Timer animator = new Timer(delay, this);
		animator.setActionCommand("PlayAnimation");
		
		// returnButton is being clicked so bring up main menu and hide view prescription frame
		if (e.getActionCommand().equals("returnToMainMenu")) {
			this.dispose();
			mainMenu.setLocationRelativeTo(null);
			mainMenu.setVisible(true);
		}
		// 1st prescription button of user's first doctor is clicked
		else if (e.getActionCommand().equals("Mario04Apr2017")) {
			JOptionPane.showMessageDialog(null, "For Dr.Mario only the 11 August 2016 prescription has"
					+ " an image and animation...", "Prescription Image Not Found", JOptionPane.ERROR_MESSAGE);
			seletedPrescription = "Dr. Mario Gates - 04 April 2017";
			prescriptionTitleLabel.setText(seletedPrescription);
			prescription.setIcon(null);
		}
		// 2nd prescription button of user's first doctor is clicked
		else if (e.getActionCommand().equals("Mario27Jan2017")) {
			JOptionPane.showMessageDialog(null, "For Dr.Mario only the 11 August 2016 prescription has"
					+ " an image and animation...", "Prescription Image Not Found", JOptionPane.ERROR_MESSAGE);
			seletedPrescription = "Dr. Mario Gates - 27 January 2017";
			prescriptionTitleLabel.setText(seletedPrescription);
			prescription.setIcon(null);
		}
		// 3rd prescription button of user's first doctor is clicked
		else if (e.getActionCommand().equals("Mario11Aug2016")) {
			JOptionPane.showMessageDialog(null, "Please wait while we find your prescription data!"
					,"Loading Prescription...", JOptionPane.INFORMATION_MESSAGE);
			seletedPrescription = "Dr. Mario Gates - 11 August 2016";
			prescriptionTitleLabel.setText(seletedPrescription);
			
			// Reset currentFrame counter
			currentFrame = 0;
			
			// Initialise animation image array/library
			initialiseDrMarioPrescription();
			
			// Starts the prescription loading animation by starting the animation timer
			animator.start();
		}
		
		//  1st prescription button of user's second doctor is clicked
		else if (e.getActionCommand().equals("Owen03May2017")) {
			JOptionPane.showMessageDialog(null, "Please wait while we find your prescription data!"
					, "Loading Prescription...", JOptionPane.INFORMATION_MESSAGE);
			seletedPrescription = "Dr. Owen Brown - 03 May 2017";
			prescriptionTitleLabel.setText(seletedPrescription);
			
			// Reset currentFrame counter
			currentFrame = 0;
			
			// Initialise animation image array/library
			initialiseDrOwenPrescription();
			
			// Starts the prescription loading animation by starting the animation timer
			animator.start();
		}
		//  2nd prescription button of user's second doctor is clicked
		else if (e.getActionCommand().equals("Owen03Mar2017")) {
			JOptionPane.showMessageDialog(null, "For Dr.Owen only the 03 May 2016 prescription has"
					+ " an image and animation...", "Prescription Image Not Found", JOptionPane.ERROR_MESSAGE);
			seletedPrescription = "Dr. Owen Brown - 03 March 2017";
			prescriptionTitleLabel.setText(seletedPrescription);
			prescription.setIcon(null);
		}
		// 3rd prescription button of user's second doctor is clicked
		else if (e.getActionCommand().equals("Owen03Jan2017")) {
			JOptionPane.showMessageDialog(null, "For Dr.Owen only the 03 May 2016 prescription has"
					+ " an image and animation...", "Prescription Image Not Found", JOptionPane.ERROR_MESSAGE);
			seletedPrescription = "Dr. Owen Brown - 03 January 2017";
			prescriptionTitleLabel.setText(seletedPrescription);
			prescription.setIcon(null);
		}
		// 4th prescription button of user's second doctor is clicked
		else if (e.getActionCommand().equals("Owen03Nov2016")) {
			JOptionPane.showMessageDialog(null, "For Dr.Owen only the 03 May 2016 prescription has"
					+ " an image and animation...", "Prescription Image Not Found", JOptionPane.ERROR_MESSAGE);
			seletedPrescription = "Dr. Owen Brown - 03 November 2016";
			prescriptionTitleLabel.setText(seletedPrescription);
			prescription.setIcon(null);
		}
		
		// 1st prescription button of user's third doctor is clicked
		else if (e.getActionCommand().equals("Alice28Apr2016")) {
			JOptionPane.showMessageDialog(null, "Please wait while we find your prescription data!"
					,"Loading Prescription...", JOptionPane.INFORMATION_MESSAGE);
			seletedPrescription = "Dr. Alice Castle - 28 April 2016";
			prescriptionTitleLabel.setText(seletedPrescription);
			
			//Reset currentFrame counter
			currentFrame = 0;
			
			// Initialise animation image array/library
			initialiseDrAlicePrescription();
			
			// Starts the prescription loading animation by starting the animation timer
			animator.start();
		}
		
		// "Prescription missing?" button is being clicked
		else if (e.getActionCommand().equals("missingPrescription")) {
			JOptionPane.showMessageDialog(null, "Please contact the doctor of the missing prescription!"
					,"Missing Prescription", JOptionPane.WARNING_MESSAGE);
		}
		
		// "Print" button is being clicked
		else if (e.getActionCommand().equals("printPrescription")) {
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}
		
		// Play the prescription loading animation
		else {
			if (currentFrame < prescriptionArray.length) {
				prescription.setIcon(prescriptionArray[currentFrame]);
				currentFrame ++;
			}
			// Stops the timer when the all images in prescriptionArray has being played
			else {
				animator.stop();
			}
		}
	}
}
