import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;

public class CreateMedTime extends JFrame implements ActionListener {
	JLabel timeAndDate;
	BorderLayout bl;
	JCheckBox onlyThisDate;
	JButton qMark;
	JButton qMark2;
	JButton qMark3;
	JLabel selectMedication;
	JComboBox selectMedBox;
	JLabel doseLabel;
	JSpinner selectDose;
	JLabel addMedLabel;
	JButton addMedication;
	JCheckBox setAlarmNotification;
	JButton alarmSettings;
	JLabel contactLabel;
	JTextField contactNumber;
	JButton addContactNumber;
	JLabel addContactLabel;
	JButton cancel;
	JButton confirm;
	JPanel contentArea;
	JPanel footerArea;
	JPanel content1;
	JPanel content2;
	JPanel content3;
	JPanel content4;
	JPanel content5;
	JPanel content6;
	JPanel content7;
	JPanel content8;
	String[] doses = {"5 mg", "10 mg", "15 mg", "20 mg", "25 mg", "30 mg", "40 mg", "50 mg", "60 mg", "65 mg", "70 mg", "75 mg", "80 mg"};
	private Color skyBlue = new Color(135, 206, 250);
	private Color steelBlue = new Color(70, 130, 180);
	private Color aliceBlue = new Color(240, 248, 255);
	private Font arialBold = new Font("Arial", Font.BOLD, 18);
	private Font bigButton = new Font("Arial", Font.BOLD, 30);
	private Font dateHeader = new Font("Arial", Font.BOLD, 24);
	

	public CreateMedTime (Point dim) {
		// Adds the panel containing the copyright information to the south
		//this.add(createCopyRightArea(), BorderLayout.SOUTH);
		this.setSize(new Dimension(dim.x, dim.y));
		this.setTitle("Create New Medication Time");
		content1 = new JPanel();
		content2 = new JPanel();
		content3 = new JPanel();
		content4 = new JPanel();
		content5 = new JPanel();
		content6 = new JPanel();
		content7 = new JPanel();
		content8 = new JPanel();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		bl = new BorderLayout();
		this.setLayout(bl);
		contentArea = new JPanel();
		contentArea.setLayout(new BoxLayout(contentArea, BoxLayout.Y_AXIS));
		this.add(contentArea);
		timeAndDate = new JLabel("7:00 PM - 18th October, 2017");
		content1.add(timeAndDate);
		contentArea.add(content1);
		onlyThisDate = new JCheckBox("Only for this date?");
		content2.add(onlyThisDate);
		qMark = new JButton("?");
		content2.add(qMark);
		contentArea.add(content2);
		qMark.setFont(bigButton);
		qMark.setBorderPainted(false);
		qMark.setContentAreaFilled(false);
		qMark.setToolTipText("Help info goes here");
		selectMedication = new JLabel("Select Medication:");
		content3.add(selectMedication);
		selectMedBox = new JComboBox();
		selectMedBox.addItem("");
		selectMedBox.addItem("Lasix");
		selectMedBox.addItem("Aspirin");
		content3.add(selectMedBox);
		doseLabel = new JLabel("Dose:");
		content3.add(doseLabel);
		SpinnerListModel doseModel = new SpinnerListModel(doses);
		JSpinner selectDose = new JSpinner(doseModel);
		content3.add(selectDose);
		contentArea.add(content3);
		addMedLabel = new JLabel("Add a Medication...");
		content4.add(addMedLabel);
		addMedication = new JButton("+");
		content4.add(addMedication);
		contentArea.add(content4);
		addMedication.setBorderPainted(false);
		addMedication.setContentAreaFilled(false);
		setAlarmNotification = new JCheckBox("Set Alarm Notification");
		content5.add(setAlarmNotification);
		alarmSettings = new JButton("Alarm Settings");
		alarmSettings.setForeground(Color.blue);
		alarmSettings.setBorderPainted(false);
		alarmSettings.setContentAreaFilled(false);
		HashMap<TextAttribute, Object> textAttrMap = new HashMap<TextAttribute, Object>();
		textAttrMap.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		textAttrMap.put(TextAttribute.FOREGROUND, Color.BLUE);
		alarmSettings.setFont(alarmSettings.getFont().deriveFont(textAttrMap));
		content5.add(alarmSettings);
		qMark2 = new JButton("?");
		content5.add(qMark2);
		contentArea.add(content5);
		qMark2.setToolTipText("Help info goes here");
		qMark2.setBorderPainted(false);
		qMark2.setContentAreaFilled(false);
		qMark2.setFont(bigButton);
		contactLabel = new JLabel("Contact Number (Optional):");
		content6.add(contactLabel);
		contactNumber = new JTextField();
		contactNumber.setColumns(10);
		content6.add(contactNumber);
		contentArea.add(content6);
		addContactLabel = new JLabel("Add A Contact Number...");
		content7.add(addContactLabel);
		addContactNumber = new JButton("+");
		content7.add(addContactNumber);
		contentArea.add(content7);
		addContactNumber.setBorderPainted(false);
		addContactNumber.setContentAreaFilled(false);
		content3.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
		content2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		content4.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
		content5.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
		content6.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
		content7.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));

		footerArea = new JPanel(new BorderLayout());
		this.add(footerArea, BorderLayout.SOUTH);
		cancel = new JButton("Cancel");
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(this);
		confirm = new JButton("Confirm");
		confirm.setActionCommand("Confirm");
		confirm.addActionListener(this);
		footerArea.add(cancel, BorderLayout.WEST);
		footerArea.add(confirm, BorderLayout.EAST);		
		footerArea.setBackground(skyBlue);
		
		timeAndDate.setFont(dateHeader);
		onlyThisDate.setFont(arialBold);
		addContactNumber.setFont(bigButton);
		addMedication.setFont(bigButton);
		selectMedication.setFont(arialBold);
		selectMedBox.setFont(arialBold);
		doseLabel.setFont(arialBold);
		selectDose.setFont(arialBold);
		addMedLabel.setFont(arialBold);
		setAlarmNotification.setFont(arialBold);
		contactLabel.setFont(arialBold);
		contactNumber.setFont(arialBold);
		addContactLabel.setFont(arialBold);
		cancel.setFont(arialBold);
		confirm.setFont(arialBold);
		contentArea.setBackground(skyBlue);
		content1.setBackground(skyBlue);
		content2.setBackground(skyBlue);
		content3.setBackground(skyBlue);
		content4.setBackground(skyBlue);
		content5.setBackground(skyBlue);
		content6.setBackground(skyBlue);
		content7.setBackground(skyBlue);
		content8.setBackground(skyBlue);

		//content8.setBackground(skyBlue);
		onlyThisDate.setBackground(skyBlue);
		setAlarmNotification.setBackground(skyBlue);
		//this.setResizable(false);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
		if (e.getActionCommand().equals("returnToMainMenu")) {
			JOptionPane.showMessageDialog(null, "Returning to Main Menu!"
					,"Return", JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
		}
		if (e.getActionCommand().equals("Cancel")) {
			this.dispose();
					}
		
		if (e.getActionCommand().equals("Confirm")) {
			JOptionPane.showMessageDialog(null, "This function is currently unavailable"
					,"Function Unavailable", JOptionPane.ERROR_MESSAGE);
				}
		}
	}