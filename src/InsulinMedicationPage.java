import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This function creates the JFrame for the Insulin medication 
 * 
 * @author Rachel Lee
 *
 */
public class InsulinMedicationPage extends JFrame implements ActionListener{

	private Font arialBold = new Font("Arial", Font.BOLD, 14);
	private Color skyBlue = new Color(135, 206, 250);
	private JButton backSearchButton = new JButton("Back to search");
	private JFrame searchMedicationFrame;

	//This function creates the JFrame of the current medication page
	public InsulinMedicationPage(Point dim, JFrame searchMedicationFrame){
		BorderLayout bl = new BorderLayout();

		//set the size of the JFrame
		this.setSize(new Dimension(dim.x, dim.y));

		//set the title of the JFrame
		this.setTitle("Insulin Medication");

		//set the layout of the current JFrame
		this.setLayout(bl);

		this.searchMedicationFrame = searchMedicationFrame;

		//set the style of the back button
		backSearchButton.setForeground(Color.GRAY);
		backSearchButton.setBorderPainted(false);
		backSearchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

		//set the action command of the back button
		backSearchButton.setActionCommand("goBackToSearch");
		backSearchButton.addActionListener(this);

		//add a scroll pane to the JFrame. this enable the page to be scrolled
		this.add(scrollPane(), BorderLayout.CENTER);
		this.add(backSearchButton, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		//set the JFrame to visible
		this.setVisible(true);
	}

	/**create the JScroll pane to display the information
	 * @return scrollArea
	 * */
	private JScrollPane scrollPane(){

		// get all the information and put it in a JPanel
		JPanel getInfo = informationArea();

		//set the JPanel onto the JScroll
		JScrollPane scrollArea = new JScrollPane(getInfo);
		return scrollArea ;

	}


	/**create the JPanel that will store the information of the medication
	 * @return informationArea
	 * */
	private JPanel informationArea(){
		
		//intialise the JPanel
		JPanel informationArea = new JPanel();
		
		//Intilaise the layout for the JPanel
		BoxLayout boxLay = new BoxLayout(informationArea, BoxLayout.Y_AXIS);
		
		//intiliase th eloading gif. not actual animation
		ImageIcon pic = new ImageIcon(getClass().getResource("medications/insulin.png"));
		
		//intialise all the JLabels
		JLabel medicationPic = new JLabel(pic,JLabel.CENTER);
		JLabel ingredientsText = new JLabel(" Medication Ingredients : 16 mg glycerin, 1.88 mg dibasic sodium phosphate ", JLabel.CENTER);
		JLabel safeDosage = new JLabel("Approved safe dosage: 500 units/ml",JLabel.CENTER);
		JLabel mostUse = new JLabel("Mostly used for: Diabetes", JLabel.CENTER);
		JLabel sideEffects = new JLabel("Affected by these medication: ",JLabel.CENTER);
		
		//initlaise the button to display the medication side efefcts with other medication
		JButton affectedDrugs = new JButton ("Sleeping pills");

		//set the styles for the label
		ingredientsText.setForeground(Color.BLACK);
		ingredientsText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		ingredientsText.setFont(arialBold);

		//set the alignment of the image of the medication
		medicationPic.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		//set the styles for the label
		safeDosage.setForeground(Color.BLACK);
		safeDosage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		safeDosage.setFont(arialBold);

		//set the styles for the label
		mostUse.setForeground(Color.BLACK);
		mostUse.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		mostUse.setFont(arialBold);

		//set the styles for the label
		sideEffects.setForeground(Color.BLACK);
		sideEffects.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		sideEffects.setFont(arialBold);

		//set the styles for the button
		affectedDrugs.setForeground(Color.BLACK);
		affectedDrugs.setBorderPainted(false);
		affectedDrugs.setAlignmentX(JButton.CENTER_ALIGNMENT);

		//set the action command of the button
		affectedDrugs.setActionCommand("affectedDrugs");
		affectedDrugs.addActionListener(this);
		
		//set the style of the box layout
		informationArea.setBackground(skyBlue);
		informationArea.setLayout(boxLay);

		//add the JLabels, Image and the JButton onto the JPanel
		informationArea.add(medicationPic);
		informationArea.add(Box.createRigidArea(new Dimension(0,50)));
		informationArea.add(ingredientsText);
		informationArea.add(Box.createRigidArea(new Dimension(0,50)));
		informationArea.add(safeDosage);
		informationArea.add(Box.createRigidArea(new Dimension(0,50)));
		informationArea.add(mostUse);
		informationArea.add(Box.createRigidArea(new Dimension(0,50)));
		informationArea.add(sideEffects);
		informationArea.add(Box.createRigidArea(new Dimension(0,30)));
		informationArea.add(affectedDrugs);

		//return the JPanel
		return informationArea;

	}

	/**
	 * This function sets the action of the search button is clicked.
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		
		//this checks if the back button is clicked
		if(e.getActionCommand().equals("goBackToSearch")){
			
			//the current page will be closed
			this.setVisible(false);
			
			//the main menu will be displayed
			searchMedicationFrame.setVisible(true);
		}
		
		//this will check if the button that display the side effects of each medication when it comes in contact with the current medication is clicked
		else if (e.getActionCommand().equals("affectedDrugs")){
			
			//displays a pop-up box stating the side effects of the medication
			JOptionPane.showMessageDialog(null, "Possible side effects: low blood sugar during slumber."
					,"Side Effects", JOptionPane.INFORMATION_MESSAGE);
		}

	}
}