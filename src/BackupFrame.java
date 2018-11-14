import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

/**
 * 
 * @author Alexander James Bishop
 *
 */
public class BackupFrame extends JFrame implements ActionListener{
  
  // Enum for the types of database
  public enum DBid {
    USERDATA,
    SALES,
    PRODUCTS
  };
  
  // Closest thing to a struct I can get
  class db
  {
      public String dbname; 
      public String dbdate;  
      public int    dbbytes; 
  };
  
  // Making the database backups
  db db1[] = {new db(),new db(),new db(),new db(),new db()};
  db db2[] = {new db(),new db(),new db(),new db()};
  db db3[] = {new db(),new db(),new db(),new db(),new db(),new db()};
  
  db databases[][] = {db1,db2,db3};
  
  private Color bodyColor = new Color(135, 206, 250);
  private Color titleColor = new Color(70, 130, 180);
  private Color headerColor = new Color(22,22,22);
  private Font buttonFont = new Font("Arial", Font.BOLD, 22);
  private Font headerFont = new Font("Arial", Font.BOLD, 24);
  private Font fixedwidthFont = new Font("monospaced", Font.PLAIN, 20);
  private JPanel centerArea = new JPanel();
  private JPanel sidebar = new JPanel();
  private JPanel bottombar = new JPanel();
  private BorderLayout bl = new BorderLayout();
  private db currentUserDB = new db();
  private db currentSalesDB = new db();
  private db currentProductsDB = new db();
  private db currentDBs[] = {currentUserDB,currentSalesDB,currentProductsDB};
  private DBid currentDB;
  private int selectedDB;

  public void actionPerformed(ActionEvent e) {
    
    if(e.getActionCommand().equals("Logout")) {
      
      new UserLoginFrame(new Point(600, 510));
      this.dispose();
    }
    else if(e.getActionCommand().equals("Home")) {
      System.out.println("Home");
      generateMenu();
    }
    else if(e.getActionCommand().equals("Servers")) {
      generateServersPage();
    }
    else if(e.getActionCommand().equals("Mail")) {
      System.out.println("Mail");
    }
    else if(e.getActionCommand().equals("Users")) {
      generateUsersPage();
    }
    else if(e.getActionCommand().equals("Logs")) {
      generateLogsPage();
    }
    else if(e.getActionCommand().equals("Databases")) {
      generateDatabasesPage();
    }
    else if(e.getActionCommand().equals("Backups")) {
      System.out.println("Backups");
      generateBackupDBs();
    }
    else if(e.getActionCommand().equals("Cats")) {
      generateCatsPage();
    }
    else if(e.getActionCommand().equals("userdata")) {
      System.out.println("userdata");
      // Set current working database to the userdata database
      currentDB = DBid.USERDATA;
      generateBackupPage(DBid.USERDATA,0);
    }
    else if(e.getActionCommand().equals("sales")) {
      System.out.println("sales");
      // set the current working database to the sales database
      currentDB = DBid.SALES;
      generateBackupPage(DBid.SALES,0);
    }
    else if(e.getActionCommand().equals("products")) {
      System.out.println("products");
      // set the current working database to the products database
      currentDB = DBid.PRODUCTS;
      generateBackupPage(DBid.PRODUCTS,0);
    }
    else if(e.getActionCommand().contains("BackupClickUserData")) {
      // find the index of the backup that was clicked and send it to the backup page generator
      int index = Integer.parseInt(e.getActionCommand().substring(19,e.getActionCommand().length()));
      generateBackupPage(DBid.USERDATA,index+1);
    }
    else if(e.getActionCommand().contains("BackupClickSales")) {
      // find the index of the backup that was clicked and send it to the backup page generator
      int index = Integer.parseInt(e.getActionCommand().substring(16,e.getActionCommand().length()));
      generateBackupPage(DBid.SALES,index+1);
    }
    else if(e.getActionCommand().contains("BackupClickProducts")) {
      // find the index of the backup that was clicked and send it to the backup page generator
      int index = Integer.parseInt(e.getActionCommand().substring(19,e.getActionCommand().length()));
      generateBackupPage(DBid.PRODUCTS,index+1);
    }
    else if(e.getActionCommand().equals("Add Backup")) {
      // This will create a copy of the current working database and add it to the list of backups
      db newdb = new db();
      switch(currentDB){
        case USERDATA:
          newdb.dbname = currentUserDB.dbname;
          newdb.dbdate = currentUserDB.dbdate;
          newdb.dbbytes = currentUserDB.dbbytes;
          // expand backup array by one and add the new database object
          databases[DBid.USERDATA.ordinal()] = Arrays.copyOf(databases[DBid.USERDATA.ordinal()], databases[DBid.USERDATA.ordinal()].length+1);
          databases[DBid.USERDATA.ordinal()][databases[DBid.USERDATA.ordinal()].length-1] = newdb;
          generateBackupPage(DBid.USERDATA,0);
          break;
        case SALES:
          newdb.dbname = currentSalesDB.dbname;
          newdb.dbdate = currentSalesDB.dbdate;
          newdb.dbbytes = currentSalesDB.dbbytes;
       // expand backup array by one and add the new database object
          databases[DBid.SALES.ordinal()] = Arrays.copyOf(databases[DBid.SALES.ordinal()], databases[DBid.SALES.ordinal()].length+1);
          databases[DBid.SALES.ordinal()][databases[DBid.SALES.ordinal()].length-1] = newdb;
          generateBackupPage(DBid.SALES,0);
          break;
        case PRODUCTS:
          newdb.dbname = currentProductsDB.dbname;
          newdb.dbdate = currentProductsDB.dbdate;
          newdb.dbbytes = currentProductsDB.dbbytes;
       // expand backup array by one and add the new database object
          databases[DBid.PRODUCTS.ordinal()] = Arrays.copyOf(databases[DBid.PRODUCTS.ordinal()], databases[DBid.PRODUCTS.ordinal()].length+1);
          databases[DBid.PRODUCTS.ordinal()][databases[DBid.PRODUCTS.ordinal()].length-1] = newdb;
          generateBackupPage(DBid.PRODUCTS,0);
          break;
      }
    }
    else if(e.getActionCommand().equals("Restore Backup")) {
      // show a warning screen before doing anything
      if(!splashwarning("restore")){return;}
      switch(currentDB){
        case USERDATA:
          // Set the current working database's variables to the selected backup
          currentUserDB.dbname = databases[currentDB.ordinal()][selectedDB-1].dbname;
          currentUserDB.dbdate = databases[currentDB.ordinal()][selectedDB-1].dbdate;
          currentUserDB.dbbytes = databases[currentDB.ordinal()][selectedDB-1].dbbytes;
          generateBackupPage(DBid.USERDATA,0);
          break;
        case SALES:
          // Set the current working database's variables to the selected backup
          currentSalesDB.dbname = databases[currentDB.ordinal()][selectedDB-1].dbname;
          currentSalesDB.dbdate = databases[currentDB.ordinal()][selectedDB-1].dbdate;
          currentSalesDB.dbbytes = databases[currentDB.ordinal()][selectedDB-1].dbbytes;
          generateBackupPage(DBid.SALES,0);
          break;
        case PRODUCTS:
          // Set the current working database's variables to the selected backup
          currentProductsDB.dbname = databases[currentDB.ordinal()][selectedDB-1].dbname;
          currentProductsDB.dbdate = databases[currentDB.ordinal()][selectedDB-1].dbdate;
          currentProductsDB.dbbytes = databases[currentDB.ordinal()][selectedDB-1].dbbytes;
          generateBackupPage(DBid.PRODUCTS,0);
          break;
      }
      generateBackupPage(DBid.USERDATA,0);
    }
    else if(e.getActionCommand().equals("Delete Backup")) {
      // Show a warning screen before doing anything
      if(!splashwarning("delete")){return;}
      // Make sure a database has actually been selected first
      if(selectedDB == 0){ return;}
      // Create new array one element smaller than current array length
      int arrlen = databases[currentDB.ordinal()].length;
      db newdbs[] = new db[arrlen-1];
      // Copy over all databases into new array, minus database to be removed
      int ni = 0;
      for(int i=0;i<arrlen;i++){
        if(i == selectedDB-1){
          continue;
        }
        newdbs[ni] = databases[currentDB.ordinal()][i];
        ni += 1;
      }
      // put the new database array into the database table
      databases[currentDB.ordinal()] = newdbs;
      generateBackupPage(DBid.USERDATA,0);
    }
    else if(e.getActionCommand().equals("Copy Backup")) {
      db newdb = new db();
      switch(currentDB){
        case USERDATA:
          // Create a copy of the currently selected database
          newdb.dbname = databases[currentDB.ordinal()][selectedDB-1].dbname;
          newdb.dbdate = databases[currentDB.ordinal()][selectedDB-1].dbdate;
          newdb.dbbytes = databases[currentDB.ordinal()][selectedDB-1].dbbytes;
          // Expand backup array by one and add the new entry
          databases[DBid.USERDATA.ordinal()] = Arrays.copyOf(databases[DBid.USERDATA.ordinal()], databases[DBid.USERDATA.ordinal()].length+1);
          databases[DBid.USERDATA.ordinal()][databases[DBid.USERDATA.ordinal()].length-1] = newdb;
          generateBackupPage(DBid.USERDATA,0);
          break;
        case SALES:
          // Create a copy of the currently selected database
          newdb.dbname = currentSalesDB.dbname;
          newdb.dbdate = currentSalesDB.dbdate;
          newdb.dbbytes = currentSalesDB.dbbytes;
          // Expand backup array by one and add the new entry
          databases[DBid.SALES.ordinal()] = Arrays.copyOf(databases[DBid.SALES.ordinal()], databases[DBid.SALES.ordinal()].length+1);
          databases[DBid.SALES.ordinal()][databases[DBid.SALES.ordinal()].length-1] = newdb;
          generateBackupPage(DBid.SALES,0);
          break;
        case PRODUCTS:
          // Create a copy of the currently selected database
          newdb.dbname = currentProductsDB.dbname;
          newdb.dbdate = currentProductsDB.dbdate;
          newdb.dbbytes = currentProductsDB.dbbytes;
          // Expand backup array by one and add the new entry
          databases[DBid.PRODUCTS.ordinal()] = Arrays.copyOf(databases[DBid.PRODUCTS.ordinal()], databases[DBid.PRODUCTS.ordinal()].length+1);
          databases[DBid.PRODUCTS.ordinal()][databases[DBid.PRODUCTS.ordinal()].length-1] = newdb;
          generateBackupPage(DBid.PRODUCTS,0);
          break;
      }
    }
  }
  
  // convinience function to clear the swing components
  private void blank(){
    centerArea.removeAll();
    sidebar.removeAll();
    bottombar.removeAll();
  }
  
  // Entrypoint
  public BackupFrame(int x, int y){
    // Set size, title and visibility
    this.setSize(x, y);
    this.setTitle("Admin Control Panel");
    this.setVisible(true);
       
    // Generate database backup data
    for(int i=0;i<3;i++){
      String basedate = "Fri Jun %d, 2017";
      int baseday = 1;
      for(int j=0;j<databases[i].length;j++){
        // quick pseudorandom generator
        databases[i][j].dbbytes = Math.abs(((3-i)*10+(10-j)+1)*512+0xBADB17E5)%512000;
        if(databases[i][j].dbbytes < 256000) {databases[i][j].dbbytes *= 2; }
        databases[i][j].dbdate = String.format(basedate,baseday+j);
        if(i==0){databases[i][j].dbname = "UserData";} 
        if(i==1){databases[i][j].dbname = "Sales";}
        if(i==2){databases[i][j].dbname = "Products";}
      }
    }
    
    // Create some data for the current working databases
    currentUserDB.dbname = "Users";
    currentUserDB.dbdate = "Sat Jun 10, 2017";
    currentUserDB.dbbytes = Math.abs((31)*512+0xBADB17E5)%512000;
    currentSalesDB.dbname = "Sales";
    currentSalesDB.dbdate = "Sat Jun 10, 2017";
    currentSalesDB.dbbytes = Math.abs((35)*512+0xBADB17E5)%512000;
    currentProductsDB.dbname = "Products";
    currentProductsDB.dbdate = "Sat Jun 10, 2017";
    currentProductsDB.dbbytes = Math.abs((39)*512+0xBADB17E5)%512000;

    // Create window elements
    this.setLayout(bl);
    
    // Create topbar
    JPanel topbar = new JPanel();
    topbar.setLayout(new BoxLayout(topbar, BoxLayout.LINE_AXIS));
    
    // Populate topbar
    JButton topbarHomeButton = new JButton("Home");
    JButton topbarServersButton = new JButton("Servers");
    JButton topbarMailButton = new JButton("Mail");
    JButton[] leftButtons = {topbarHomeButton,topbarServersButton,topbarMailButton};
    for(int i=0;i<3;i++){
      topbar.add(leftButtons[i]);
      leftButtons[i].setBackground(titleColor);
      leftButtons[i].setOpaque(true);
      leftButtons[i].setBorderPainted(false);
      leftButtons[i].setFont(buttonFont);
      leftButtons[i].setForeground(Color.WHITE);
      leftButtons[i].setActionCommand(leftButtons[i].getText());
      leftButtons[i].addActionListener(this);
    }
    
    // Doing this makes elements left aligned
    topbar.add(Box.createHorizontalGlue());
    
    // Create logout button
    JButton topbarLogoutButton = new JButton("Logout");
    topbar.add(topbarLogoutButton);
    topbarLogoutButton.setBackground(titleColor);
    topbarLogoutButton.setOpaque(true);
    topbarLogoutButton.setBorderPainted(false);
    topbarLogoutButton.setFont(buttonFont);
    topbarLogoutButton.setForeground(Color.WHITE);
    topbarLogoutButton.setActionCommand("Logout");
    topbarLogoutButton.addActionListener(this);
    
    // Add topbar
    topbar.setBackground(titleColor);
    this.add(topbar, BorderLayout.NORTH);
    
    // Generate the main menu and let the control flow go where it wants
    generateMenu();
    
	// Makes the frame appears in the center of the screen
	this.setLocationRelativeTo(null);
    
    // Set the close operation so the window manager buttons work
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  // Simple splash message to warn the user about something
  private boolean splashwarning(String type){
    String warningTitle;
    String warningMessage;
    if(type == "restore"){
      // Warning message for restore a backup
      warningTitle = "Confirm Restore";
      warningMessage = "Are you sure you wish to restore this backup? The current working database will be overwritten!";
    }
    else if(type == "delete"){
      // Warning message for delete a backup
      warningTitle = "Confirm Delete";
      warningMessage = "Are you sure you wish to delete this backup? It will not be recoverable afterwards!";
    }
    else {
      // Unknown type, show generic warning message
      warningTitle = "Confirm Action";
      warningMessage = "Are you sure you wish to do this action?";
    }

    // Make a dialog box with some options
    String[] options = {"Cancel", "Confirm"};
    int r = JOptionPane.showOptionDialog(null, warningMessage,warningTitle,
        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    if(r > 0) {return true;}
    else {return false;}
  }
  
  private void generateMenu(){
    
    // clear screen
    blank();

    // Set up the layouts
    FlowLayout centerLayout = new FlowLayout();
    centerArea.setLayout(centerLayout);
    centerLayout.setHgap(32);
    centerLayout.setVgap(32);
    centerArea.setBackground(bodyColor);
    centerArea.setBorder(null);
    
    // Create panels for the main menu buttons to sit in
    JPanel UsersButtonPanel = new JPanel();
    UsersButtonPanel.setLayout(new BoxLayout(UsersButtonPanel, BoxLayout.Y_AXIS));
    JPanel LogsButtonPanel = new JPanel();
    LogsButtonPanel.setLayout(new BoxLayout(LogsButtonPanel, BoxLayout.Y_AXIS));
    JPanel BackupsButtonPanel = new JPanel();
    BackupsButtonPanel.setLayout(new BoxLayout(BackupsButtonPanel, BoxLayout.Y_AXIS));
    JPanel DBsButtonPanel = new JPanel();
    DBsButtonPanel.setLayout(new BoxLayout(DBsButtonPanel, BoxLayout.Y_AXIS));
    JPanel CatsButtonPanel = new JPanel();
    CatsButtonPanel.setLayout(new BoxLayout(CatsButtonPanel, BoxLayout.Y_AXIS));
    
    JPanel[] ButtonPanels = { UsersButtonPanel,LogsButtonPanel,
                              DBsButtonPanel,BackupsButtonPanel,
                              CatsButtonPanel};
    
    String[] ButtonNames = {"Users","Logs","Databases","Backups","Cats"};

    // Set image icons for the main menu buttons
    ImageIcon UsersIcon = new ImageIcon(getClass().getResource("adminIcons/users.png"));
    ImageIcon LogsIcon = new ImageIcon(getClass().getResource("adminIcons/logs.png"));
    ImageIcon BackupsIcon = new ImageIcon(getClass().getResource("adminIcons/backup.png"));
    ImageIcon DBsIcon = new ImageIcon(getClass().getResource("adminIcons/DBs.png"));
    ImageIcon catsIcon = new ImageIcon(getClass().getResource("adminIcons/pepper.png"));
 
    // Create the main menu buttons
    JButton centerUsersButton = new JButton(UsersIcon);
    JButton centerLogsButton = new JButton(LogsIcon);
    JButton centerDatabasesButton = new JButton(DBsIcon);
    JButton centerBackupsButton = new JButton(BackupsIcon);
    JButton centerCatsButton = new JButton(catsIcon);
    JButton[] centerButtons = { centerUsersButton,centerLogsButton,
                                centerDatabasesButton,centerBackupsButton,
                                centerCatsButton};
    
    // Iterate though the main menu buttons and apply common properties
    for(int i=0;i<5;i++){
      ButtonPanels[i].add(centerButtons[i]);
      JLabel buttonlabel = new JLabel(ButtonNames[i]);
      buttonlabel.setFont(buttonFont);
      buttonlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      ButtonPanels[i].add(buttonlabel);
      ButtonPanels[i].setBackground(bodyColor);
      centerButtons[i].setActionCommand(ButtonNames[i]);
      centerButtons[i].addActionListener(this);
      centerButtons[i].setBackground(titleColor);
      centerButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
      centerButtons[i].setOpaque(true);
      centerButtons[i].setBorderPainted(false);
      centerButtons[i].setFont(buttonFont);
      centerButtons[i].setForeground(Color.WHITE);
      centerButtons[i].setBorder(BorderFactory.createEmptyBorder());
      centerButtons[i].setContentAreaFilled(false);
      centerArea.add(ButtonPanels[i]);
    }
    // Add center area
    this.add(centerArea, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
    
  }
  
  // Called when the user navigates to the selection screen for what database to view the backups of
  private void generateBackupDBs(){
    
    // Clear screen
    blank();

    
    // standard setting up of layouts
    FlowLayout centerLayout = new FlowLayout();
    centerArea.setLayout(centerLayout);
    centerLayout.setHgap(32);
    centerLayout.setVgap(32);
    centerArea.setBackground(bodyColor);
    centerArea.setBorder(null);
  
    // Make some panels for the buttons to sit in
    JPanel Database1 = new JPanel();
    Database1.setLayout(new BoxLayout(Database1, BoxLayout.Y_AXIS));
    JPanel Database2 = new JPanel();
    Database2.setLayout(new BoxLayout(Database2, BoxLayout.Y_AXIS));
    JPanel Database3 = new JPanel();
    Database3.setLayout(new BoxLayout(Database3, BoxLayout.Y_AXIS));
    
    JPanel[] ButtonPanels = { Database1,Database2,Database3};
    
    String[] ButtonNames = {"userdata","sales","products"};
    
    // Set icon for the buttons
    ImageIcon DBsIcon = new ImageIcon(getClass().getResource("adminIcons/DBs.png")); 

    // Make the buttons
    JButton DatabaseButton1 = new JButton(DBsIcon);
    JButton DatabaseButton2 = new JButton(DBsIcon);
    JButton DatabaseButton3 = new JButton(DBsIcon);
    JButton[] centerButtons = { DatabaseButton1,DatabaseButton2,DatabaseButton3};
    
    // Iterate though the buttons and set common properties
    for(int i=0;i<3;i++){
      ButtonPanels[i].add(centerButtons[i]);
      JLabel buttonlabel = new JLabel(ButtonNames[i]);
      buttonlabel.setFont(buttonFont);
      buttonlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      ButtonPanels[i].add(buttonlabel);
      ButtonPanels[i].setBackground(bodyColor);
      centerButtons[i].setActionCommand(ButtonNames[i]);
      centerButtons[i].addActionListener(this);
      centerButtons[i].setBackground(titleColor);
      centerButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
      centerButtons[i].setOpaque(true);
      centerButtons[i].setBorderPainted(false);
      centerButtons[i].setFont(buttonFont);
      centerButtons[i].setForeground(Color.WHITE);
      centerButtons[i].setBorder(BorderFactory.createEmptyBorder());
      centerButtons[i].setContentAreaFilled(false);
      centerArea.add(ButtonPanels[i]);
    }
    
    // Add the buttons to the center area
    this.add(centerArea, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
    
  }
  
  // Called when the backup listing page is to be drawn
  // The two arguments specify what the current working database is and the
  // currently selected (if any) backup. These determine what backup icon
  // is highlighted and what info is displayed in the side pane
  private void generateBackupPage(DBid id, int selection){

    // Clear screen
    blank();
    
    // layout setup again
    FlowLayout centerLayout = new FlowLayout();
    centerArea.setLayout(centerLayout);
    centerLayout.setHgap(32);
    centerLayout.setVgap(32);
    centerArea.setBackground(bodyColor);
    centerArea.setBorder(null);
  
    // Create arrays for the backup buttons
    JPanel[] backups = new JPanel[databases[id.ordinal()].length];
    JButton[] BackupButtons = new JButton[databases[id.ordinal()].length];
    
    // Set backup icons
    ImageIcon DBsIcon = new ImageIcon(getClass().getResource("adminIcons/backupfile.png")); 

    // Iterate though the backups to be shown and create them/set common properties
    for(int i=0;i<databases[id.ordinal()].length;i++){
      backups[i] = new JPanel();
      backups[i].setLayout(new BoxLayout(backups[i], BoxLayout.Y_AXIS));
      BackupButtons[i] = new JButton(DBsIcon);
      backups[i].add(BackupButtons[i]);
      
      // Set up the labels
      JLabel buttonlabel = new JLabel(databases[id.ordinal()][i].dbdate);
      buttonlabel.setFont(buttonFont);
      buttonlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      backups[i].add(buttonlabel);
      backups[i].setBackground(bodyColor);
      // Set a unique action command for each button so that the clicks can be told apart
      BackupButtons[i].setActionCommand("BackupClick" + databases[id.ordinal()][i].dbname + i);
      BackupButtons[i].addActionListener(this);
      BackupButtons[i].setBackground(titleColor);
      BackupButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
      BackupButtons[i].setOpaque(true);
      BackupButtons[i].setBorderPainted(false);
      BackupButtons[i].setFont(buttonFont);
      BackupButtons[i].setForeground(Color.WHITE);
      BackupButtons[i].setBorder(BorderFactory.createEmptyBorder());
      BackupButtons[i].setContentAreaFilled(false);
      centerArea.add(backups[i]);
    }
    
    // Add the backup action buttons
    bottombar.setLayout(new BoxLayout(bottombar, BoxLayout.LINE_AXIS));
    
    // Populate bottombar with buttons
    JButton bottombarAddBackup = new JButton("Add Backup");
    JButton bottombarRestoreBackup = new JButton("Restore Backup");
    JButton bottombarDeleteBackup = new JButton("Delete Backup");
    JButton bottombarCopyBackup = new JButton("Copy Backup");
    JButton[] actionButtons = {bottombarAddBackup,bottombarRestoreBackup,bottombarDeleteBackup,bottombarCopyBackup};
    // Iterate through buttons and apply common properties
    for(int i=0;i<4;i++){
      bottombar.add(actionButtons[i]);
      actionButtons[i].setBackground(titleColor);
      actionButtons[i].setOpaque(true);
      actionButtons[i].setBorderPainted(false);
      actionButtons[i].setFont(buttonFont);
      actionButtons[i].setForeground(Color.WHITE);
      actionButtons[i].setActionCommand(actionButtons[i].getText());
      actionButtons[i].addActionListener(this);
    }
    // Add the bottom bar to the screen
    bottombar.add(Box.createHorizontalGlue());
    bottombar.setBackground(titleColor);
    this.add(bottombar, BorderLayout.SOUTH);
    
    // Generate sidebar
    sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.PAGE_AXIS));
    
    // Create labels for the database info headers
    JLabel currentDBTitle = new JLabel(" Current Database ");
    JLabel selectedDBTitle = new JLabel(" Selected Database ");
    JLabel[] titles = {currentDBTitle,selectedDBTitle};
    // This could have been done without a for loop, and it would have been faster too
    // But this way it will match with the code done many times before this point
    for(int i=0;i<2;i++){
      titles[i].setBackground(headerColor);
      titles[i].setFont(headerFont);
      titles[i].setForeground(Color.WHITE);
    }
    
    // Create the labels for the properties of the current working database
    JLabel spacer = new JLabel(" ");
    JLabel currentDBName = new JLabel(" Name: " + currentDBs[currentDB.ordinal()].dbname + " ");
    JLabel currentDBDate = new JLabel(" Date: " + currentDBs[currentDB.ordinal()].dbdate + " ");
    JLabel currentDBSize = new JLabel(" Size: " + Integer.toString(currentDBs[currentDB.ordinal()].dbbytes) + " ");
    JLabel currentDB[] = {currentDBName,currentDBDate,currentDBSize};
    // Iterate though and set some common properties
    for(int i=0;i<3;i++){
      currentDB[i].setBackground(titleColor);
      currentDB[i].setOpaque(true);
      currentDB[i].setFont(fixedwidthFont);
      currentDB[i].setForeground(Color.WHITE);
    }
    
    // Create labels for the selected database properties
    JLabel selectedDBName;
    JLabel selectedDBDate;
    JLabel selectedDBSize;
    
    // Check if this method was called from the selection of a backup icon
    if(selection != 0){
      // Method was called with a nonzero selection, meaning the user has selected a backup
      selectedDB = selection;
      // "highlight" the selected database
      centerArea.getComponent(selection-1).setBackground(new Color(205, 255, 255));
      
      // Generate database info for the current working database
      selectedDBName = new JLabel(" Name: " + databases[id.ordinal()][selection-1].dbname + " ");
      System.out.println(databases[id.ordinal()][selection-1].dbname);
      selectedDBDate = new JLabel(" Date: " + databases[id.ordinal()][selection-1].dbdate + " ");
      selectedDBSize = new JLabel(" Size: " + Integer.toString(databases[id.ordinal()][selection-1].dbbytes) + " ");
    } else {
      // User landed on this page without selecting a backup, no selected backup info to show
      selectedDBName = new JLabel(" Name: ");
      selectedDBDate = new JLabel(" Date: ");
      selectedDBSize = new JLabel(" Size: ");
    }
    
    JLabel selectedDB[] = {selectedDBName,selectedDBDate,selectedDBSize};
    // Set some common properties
    for(int i=0;i<3;i++){
      selectedDB[i].setBackground(titleColor);
      selectedDB[i].setOpaque(true);
      selectedDB[i].setFont(fixedwidthFont);
      selectedDB[i].setForeground(Color.WHITE);
    }

    // Add the UI elements to the sidebar
    sidebar.add(spacer);
    sidebar.add(currentDBTitle);
    sidebar.add(spacer);
    sidebar.add(Box.createHorizontalGlue());
    sidebar.add(currentDBName);
    sidebar.add(currentDBDate);
    sidebar.add(currentDBSize);
    sidebar.add(spacer);
    sidebar.add(selectedDBTitle);
    sidebar.add(spacer);
    sidebar.add(selectedDBName);
    sidebar.add(selectedDBDate);
    sidebar.add(selectedDBSize);
    sidebar.setBackground(titleColor);
    // Add the sidebar and center area to the screen
    this.add(sidebar, BorderLayout.EAST);    
    this.add(centerArea, BorderLayout.CENTER);
    // Display it
    this.revalidate();
    this.repaint();
  }
  
  private void generateMonitorPage(){
    JOptionPane.showMessageDialog(null,"Feature not implemented");
  }
  
  private void generateLogsPage(){
    JOptionPane.showMessageDialog(null,"Feature not implemented");
  }
  
  private void generateUsersPage(){
    JOptionPane.showMessageDialog(null,"Feature not implemented");
  }
  
  private void generateCatsPage(){
    JOptionPane.showMessageDialog(null,"Feature not implemented");
  }
  private void generateServersPage(){
    JOptionPane.showMessageDialog(null,"Feature not implemented");
  }
  private void generateDatabasesPage(){
    JOptionPane.showMessageDialog(null,"Feature not implemented (check out the backups button)");
  }
}