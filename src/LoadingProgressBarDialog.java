import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class holds the constructors to create a JDialog which 
 * plays the progress bar animation.
 * 
 * @author kenms003
 *
 */
public class LoadingProgressBarDialog extends JDialog implements ActionListener{
	private JProgressBar progressBar = new JProgressBar(0, 100);
	private JButton readyButton = new JButton("Ready");
	private Timer animator = new Timer(50, this);
	private int progress = 0;
	
	/**
	 * Constructor that takes no parameter.
	 * 
	 */
	public LoadingProgressBarDialog(){
		// Disable user interaction while dialog is showing
		this.setModal(true);
		
		// Set up the dialog
		this.setSize(300, 170);
		this.setTitle("Loading Patient Data");
		
		// Add the progress bar panel to the dialog
		this.add(createProgressBar());
		
		// Start the timer
		animator.start();
		
		this.setLocationRelativeTo(null);
		
		// Finally, make the dialog visible to users
		this.setVisible(true);
	}
	
	/**
	 * Create and returns a JPanel which contains the progress bar
	 * animation components 
	 * 
	 * @return progress bar animation JPanel
	 */
	public JPanel createProgressBar() {
		JPanel progressBarPanel = new JPanel();
		JLabel loadingLabel = new JLabel("Loading Patient Data...");
		
		progressBarPanel.setLayout(new BoxLayout(progressBarPanel, BoxLayout.Y_AXIS));
		
		// Sets up the label
		loadingLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		// Sets up the progress bar
		progressBar.setAlignmentX(CENTER_ALIGNMENT);
		progressBar.setMaximumSize(new Dimension(200, 20));
		progressBar.setStringPainted(true);
		
		// Sets up the ready button
		readyButton.setVisible(false);
		readyButton.setActionCommand("Ready");
		readyButton.addActionListener(this);
		readyButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		// Sets up the timer
		animator.setActionCommand("ProgressBar");
		
		// Add the animation components with appropriate spacing
		progressBarPanel.add(Box.createRigidArea(new Dimension(0,20)));
		progressBarPanel.add(loadingLabel);
		progressBarPanel.add(Box.createRigidArea(new Dimension(0,20)));
		progressBarPanel.add(progressBar);
		progressBarPanel.add(Box.createRigidArea(new Dimension(0,20)));
		progressBarPanel.add(readyButton);
		
		return progressBarPanel;
	}
	
	/**
	 * ActionPerformed contains the ActionCommands for the JButton and Timer.
	 *  
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Close the about dialog when ready button is being pressed
		if (e.getActionCommand().equals("Ready")) {
			this.dispose();
		}
		// Increase progress bar value
		else {
			if (progress < 100) {
				progress++;
				progressBar.setValue(progress);
			}
			else {
				readyButton.setVisible(true);
				animator.stop();
			}
		}
	}
}
