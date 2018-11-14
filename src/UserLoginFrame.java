import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class generates a JFrame which allows user to log in their
 * account and access their personal medical data and various functions.
 * 
 * @author JohnFeng
 *
 */
public class UserLoginFrame extends JFrame implements ActionListener{
	private Color skyBlue = new Color(135, 206, 250);
	private Color steelBlue = new Color(70, 130, 180);
	private Font arialBold = new Font("Arial", Font.BOLD, 14);
	private JTextField usernameInput = new JTextField(20); // The input field for user to input username
	private JPasswordField passwordInput = new JPasswordField(20); // The input field for user to input password
	
	/**
	 * A constructor which accepts a Point variable and sets the window/frame dimension to 
	 * the value of the given Point.
	 * 
	 * @param dim a Point variable containing the dimension of the window/frame is to be set to
	 */
	public UserLoginFrame (Point dim) {
		// Sets the size/dimension, title and layout of the frame
		this.setSize(new Dimension(dim.x, dim.y));
		this.setTitle("User Login");
		this.setLayout(new BorderLayout());

		// Adds the panel containing the button to the south
		this.add(createLoginButtonArea(), BorderLayout.SOUTH);

		// Adds the panel containing the welcome banner to the north
		this.add(createWelcomeBanner(), BorderLayout.NORTH);

		// Adds the panel containing the login instruction and detail to the center
		this.add(createLoginDetailArea(), BorderLayout.CENTER);

		// Ensure the java application is terminated when user closes the User Login frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Makes the log in frame appears in the center of the screen
		this.setLocationRelativeTo(null);

		// Once everything is ready to go, make the frame visible
		this.setVisible(true);
	}


	/**
	 * Creates a panel which contains a welcome banner/message
	 * 
	 * @return the welcome banner panel containing the welcome message
	 */
	private JPanel createWelcomeBanner() {
		JPanel welcomeBanner = new JPanel();
		JLabel welcomeMessage = new JLabel("Welcome to Medication Management Application!");

		// Styles the welcome banner panel and change message colour to white
		welcomeBanner.setBackground(steelBlue);
		welcomeMessage.setForeground(Color.WHITE);

		// Horiztontally centers the welcome message
		welcomeMessage.setHorizontalAlignment(JLabel.CENTER);

		// Adds the welcome message to the welcomeBanner panel
		welcomeBanner.add(welcomeMessage);

		return welcomeBanner;
	}


	/**
	 * Creates a styled container with a login (submit) button.
	 *  
	 * @return the styled container with the login button
	 */
	private JPanel createLoginButtonArea() {
		JPanel loginButtonArea = new JPanel(); // The container where the login button will be added to
		JButton loginButton = new JButton("Login"); // The login button that submit username and password

		/* Sets the background color, transparency, border outline, font and foreground color 
		 * of the login button.
		 */
		loginButton.setBackground(Color.GRAY);
		loginButton.setOpaque(true);
		loginButton.setBorderPainted(false);
		loginButton.setForeground(Color.WHITE);

		/* Sets the layout of the frame then adds and places the login button into the panel
		 * at the specified position.
		 */
		loginButtonArea.setLayout(new BorderLayout());
		loginButtonArea.add(loginButton, BorderLayout.SOUTH);

		/* Set the specified action command to loginButton so the button can be recognized when it is being
		 * clicked. Then assign the method that will be called when loginButton is being clicked.
		 */
		loginButton.setActionCommand("submitLoginDetail");
		loginButton.addActionListener(this);

		return loginButtonArea;
	}


	/**
	 * Creates a panel which contains two descriptive labels that explain what information
	 * should go inside the input field below it.
	 * 
	 * @return the panel containing the descriptive labels and corresponding input fields
	 */
	private JPanel createLoginDetailArea() {
		JPanel instructionArea = new JPanel();
		ImageIcon logo = new ImageIcon(getClass().getResource("logos/applicationLogo.png"));
		JLabel logoLabel = new JLabel("", logo, JLabel.CENTER);
		BoxLayout boxLay = new BoxLayout(instructionArea, BoxLayout.Y_AXIS); // So label and input field can be added from top to bottom
		JLabel usernameLabel = new JLabel("Username", JLabel.CENTER); // The label to display string "Username"
		JLabel passwordLabel = new JLabel("Password", JLabel.CENTER); // The label to display string "Password"
		JButton forgotDetailButton = new JButton("Forget your username/password?"); //

		/* Styles the username label
		 */
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		usernameLabel.setFont(arialBold);

		/* Styles the password label
		 */
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		passwordLabel.setFont(arialBold);

		// Center the logo label
		logoLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		/* Since boxlayout is been used, the maximum size of the two input fields must have a constraint
		 * as otherwise they will expand to a unreasonable big size.
		 */
		usernameInput.setMaximumSize(usernameInput.getPreferredSize());
		passwordInput.setMaximumSize(passwordInput.getPreferredSize());

		/* Styles the forgot detail button
		 */
		forgotDetailButton.setForeground(Color.WHITE);
		forgotDetailButton.setBorderPainted(false);
		forgotDetailButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

		/* Set the specified action command to forgotDetailButton so the button can be recognized when it is being
		 * clicked. Then assign the method that will be called when forgotDetailButton is being clicked.
		 */
		forgotDetailButton.setActionCommand("retrieveForgottenLoginDetail");
		forgotDetailButton.addActionListener(this);

		/* Styles and sets the layout of the panel
		 */
		instructionArea.setBackground(skyBlue);
		instructionArea.setLayout(boxLay);

		/* Adds the components to the specified position with appropriate spacing.
		 */
		instructionArea.add(Box.createRigidArea(new Dimension(0,50)));
		instructionArea.add(logoLabel);
		instructionArea.add(Box.createRigidArea(new Dimension(0,50)));
		instructionArea.add(usernameLabel);
		instructionArea.add(usernameInput);
		instructionArea.add(Box.createRigidArea(new Dimension(0,30)));
		instructionArea.add(passwordLabel);
		instructionArea.add(passwordInput);
		instructionArea.add(Box.createRigidArea(new Dimension(0,10)));
		instructionArea.add(forgotDetailButton);

		return instructionArea;
	}


	/**
	 * This methods listens to an event and respond accordingly to the listened
	 * event.
	 * 
	 * @param e the event to be listened
	 */
	public void actionPerformed(ActionEvent e) {

		// The forgotLoginDetailButton is being clicked
		if (e.getActionCommand().equals("retrieveForgottenLoginDetail")) {
			JOptionPane.showMessageDialog(null, "Patient: Username is 'patient'"
					+ " | Password is 'meiHui'" +"\n Admin: Username is 'admin'"
					+ " | Password is 'alpine'"
					+ "\nDoctor: Username is 'doctor' | "
					+ " Password is 'harper'","Hint", JOptionPane.ERROR_MESSAGE);
		}
		// The login button is being clicked
		else if (e.getActionCommand().equals("submitLoginDetail")){
			String inputUsername = usernameInput.getText();
			String inputPassword = new String(passwordInput.getPassword());
			// Login credentials matches patient's login credentials 
			if (inputUsername.equals("patient") && inputPassword.equals("meiHui")) {
				JOptionPane.showMessageDialog(null, "Welcome Back Mrs Mei-Hui Chen!"
						,"Welcome Back", JOptionPane.INFORMATION_MESSAGE);
				resetInputFields();
				this.setVisible(false);
				new MainMenu(new Point(900, 410), this);
			}
			// Login credentials matches administrator's login credentials 
			else if (inputUsername.equals("admin") && inputPassword.equals("alpine")){
				JOptionPane.showMessageDialog(null, "Logging in as admin"
						,"Administrator Control", JOptionPane.INFORMATION_MESSAGE);
				resetInputFields();
				this.setVisible(false);
				new BackupFrame(800,500);
			}
			// Login credentials matches doctor's login credentials
			else if (inputUsername.equals("doctor") && inputPassword.equals("harper")){
				JOptionPane.showMessageDialog(null, "Welcome Back Dr.Harper!"
						,"Welcome Back", JOptionPane.INFORMATION_MESSAGE);
				resetInputFields();
				this.setVisible(false);
				new DoctorMenuFrame(new Point(1100,410), this);
			}
			// No matching login credentials
			else {
				JOptionPane.showMessageDialog(null, "Incorrect login details!!!"
						,"Access Denied!!!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * This function clears out all the text/values within the two input fields.
	 * 
	 */
	private void resetInputFields() {
		usernameInput.setText("");
		passwordInput.setText("");
	}
	
	/**
	 * Initalises the application by creating and displaying the login frame.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		System.out.println("Starting Medication Management application...");
		// Creates the PostItNoteFrame window application
		UserLoginFrame loginWindow = new UserLoginFrame(new Point(600, 510));
	}
}
