import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

/**
 * This is the popup JFrame when you click a calendar date. It asks you if you want to create an event 
 * or medication time. 
 * @author Austin Long
 *
 */
public class SchedulerPopup extends JFrame implements ActionListener {
	JPanel main;
	JButton newEvent;
	JButton newMedication;
	private Color skyBlue = new Color(135, 206, 250);
	private Color aliceBlue = new Color(240, 248, 255);
	private Font arialBold = new Font("Arial", Font.BOLD, 14);

	public SchedulerPopup(Point dim) {	
		main = new JPanel();
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		
		// Setting the size, title is blank.
		this.setSize(new Dimension(dim.x, dim.y));
		this.setTitle("");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		// Sets the layout of the JFrame and JPanels
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		top.setLayout(new BorderLayout());
		bottom.setLayout(new BorderLayout());
		
		// Set the color of top and bottom panel
		top.setBackground(skyBlue);
		bottom.setBackground(aliceBlue);
		
		// Instantiates the buttons
		newEvent = new JButton("Create New event");
		newMedication = new JButton("Create New Medication");
		
		// Set up buttons' style
		newEvent.setBorderPainted(false);
		newEvent.setForeground(Color.WHITE);
		newEvent.setFont(arialBold);
		newMedication.setBorderPainted(false);
		newMedication.setFont(arialBold);

		// Create action listeners and action commands for the two buttons.
		newEvent.setActionCommand("Create New Event");
		newEvent.addActionListener(this);
		newMedication.setActionCommand("Create New Medication");
		newMedication.addActionListener(this);

		// Adds the button and panels 
		top.add(newEvent, BorderLayout.CENTER);
		bottom.add(newMedication, BorderLayout.CENTER);
		main.add(top);
		main.add(bottom);
		this.add(main);
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Create New Event")) {
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
		}
		// Opens the create medication time JFrame with size of 600x600, sets visibility of the popup to false.
		if (e.getActionCommand().equals("Create New Medication")) {
			this.setVisible(false);
			CreateMedTime medTimeWindow = new CreateMedTime(new Point (600, 600));
		}

	}

}
