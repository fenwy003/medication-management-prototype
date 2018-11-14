import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class generates the loading animation JDialog
 * 
 * @author Rachel Lee
 *
 */
public class LoadingMedicationDialog extends JDialog implements ActionListener{
	private ImageIcon[] animationIcons = new ImageIcon[3];
	private JLabel animationImageLabel;
	private Timer animator = new Timer(500, this);
	private int currentImageIndex = 0;
	private int loopCounter = 0;
	
	/**
	 * This constrcutor calls a pop-up dialog which
	 * plays a loading animation when user searches
	 * an existing medication within the database.
	 */
	public LoadingMedicationDialog() {
		// Disable user interaction while dialog is showing
		this.setModal(true);
		
		// Set up the dialog
		this.setSize(300, 200);
		this.setTitle("Loading Medication Data");
		
		// Add the progress bar panel to the dialog
		this.add(createLoadingAnimation());
		
		// Start the timer
		animator.start();
		
		this.setLocationRelativeTo(null);
		
		// Finally, make the dialog visible to users
		this.setVisible(true);
	}
	
	/**
	 * Create and returns a JPanel containing the animation components
	 * 
	 * @return the animation JPanel
	 */
	private JPanel createLoadingAnimation() {
		JPanel animationPanel = new JPanel();
		JLabel loadingLabel = new JLabel("Loading Medication Data...");
		
		animationPanel.setLayout(new BoxLayout(animationPanel, BoxLayout.Y_AXIS));
		
		// Sets up the label
		loadingLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		// Initialise the animationIcons array
		animationIcons[0] = new ImageIcon(getClass().getResource("icons/load1.png"));
		animationIcons[1] = new ImageIcon(getClass().getResource("icons/load2.png"));
		animationIcons[2] = new ImageIcon(getClass().getResource("icons/load3.png"));
		
		// Initialise the animationImageLabel
		animationImageLabel = new JLabel(animationIcons[0]);
		animationImageLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		// Sets up the timer
		animator.setActionCommand("LoadMedication");
		
		// Adds in the components to the panel
		animationPanel.add(Box.createRigidArea(new Dimension(0,20)));
		animationPanel.add(loadingLabel);
		animationPanel.add(Box.createRigidArea(new Dimension(0,20)));
		animationPanel.add(animationImageLabel);
		
		return animationPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Repeat the animation several times
		if (loopCounter < 3) {
			// Reset the image index
			if (currentImageIndex == 3) {
				loopCounter++;
				currentImageIndex = 0;
			}
			animationImageLabel.setIcon(animationIcons[currentImageIndex]);
			currentImageIndex++;
		}
		else {
			animator.stop();
			this.dispose();
		}
	}
}
