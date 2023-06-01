/*  Name:  Naseem Auguste
     Course: CNT 4714 – Fall 2022 
     Assignment title: Project 3 – Two-Tier Client-Server Application Development With MySQL and JDBC
     Date: Wednesday November 2, 2022 
*/ 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.mysql.cj.jdbc.MysqlDataSource;

public class myFrame extends JFrame implements ActionListener, MouseListener
{

	JButton button, button2;
	static JButton button3;
	JButton button4;
	Font VCROSD,VCROSD2;
	
	String sqlquery, URL, User, Pass;
	
	static JComboBox<String> propertieslist;
	
	static JTextField username;
	static JPasswordField password;
	static JTextField status;
	
	static JTextArea sqlentry;
	
	private ResultSetTableModel tableModel = null;
	static JTable tableresults;
	
	static JScrollPane scrollPane;
	
	private Connection connection;
	private Statement statement;
	
	private boolean connectedToDatabase = false;
	
	myFrame() throws FileNotFoundException
	{
		try 
		{
			VCROSD = Font.createFont(Font.TRUETYPE_FONT, new File("VCROSD2.ttf")).deriveFont(22f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("VCROSD2.ttf"))); // loads in custom font
		}
		catch(IOException | FontFormatException e) 
		{
		
		}
	
		try 
		{
			VCROSD2 = Font.createFont(Font.TRUETYPE_FONT, new File("VCROSD2.ttf")).deriveFont(16f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("VCROSD2.ttf"))); // loads in custom font
		}
		catch(IOException | FontFormatException e) 
		{
		
		}
		
	ImageIcon image = new ImageIcon("UKLogo2.png"); 	//frame icon
	Border border = new LineBorder(Color.red,2,true);	//creates a border
	
	/*----------------------------------------------

	██╗░░░░░░█████╗░██████╗░███████╗██╗░░░░░░██████╗
	██║░░░░░██╔══██╗██╔══██╗██╔════╝██║░░░░░██╔════╝
	██║░░░░░███████║██████╦╝█████╗░░██║░░░░░╚█████╗░
	██║░░░░░██╔══██║██╔══██╗██╔══╝░░██║░░░░░░╚═══██╗
	███████╗██║░░██║██████╦╝███████╗███████╗██████╔╝
	╚══════╝╚═╝░░╚═╝╚═════╝░╚══════╝╚══════╝╚═════╝░
	
	----------------------------------------------*/
	
	//==============================Connection Details Label ==============================
	
	JLabel label = new JLabel(); 	//creates a label
	label.setText("Connection Details"); 	// set label text
	label.setForeground(Color.RED); 	// font color	
	label.setFont(VCROSD); 	//set font, font style, and font size
	label.setBounds(0, 0, 270, 40); 	//set (x,y,...) and dimensions (...z,a)
	
	//==============================Properties File Label ==============================
	
	JLabel label2 = new JLabel(); 	//creates a label
	label2.setText("Properties File"); 	// set label text
	label2.setForeground(Color.orange); 	// font color	
	label2.setFont(VCROSD); 	//set font, font style, and font size
	label2.setBounds(0, 10, 480, 24); 	//set (x,y,...) and dimensions (...z,a)
	
	//==============================Username Label ==============================
	
	JLabel label3 = new JLabel(); 	//creates a label
	label3.setText("Username"); 	// set label text
	label3.setForeground(Color.orange); 	// font color	
	label3.setFont(VCROSD); 	//set font, font style, and font size
	label3.setBounds(0, 0, 270, 40); 	//set (x,y,...) and dimensions (...z,a)
	
	//==============================Password Label ==============================
	
	JLabel label4 = new JLabel(); 	//creates a label
	label4.setText("Password"); 	// set label text
	label4.setForeground(Color.orange); 	// font color	
	label4.setFont(VCROSD); 	//set font, font style, and font size
	label4.setBounds(0, 0, 270, 40); 	//set (x,y,...) and dimensions (...z,a)
	
	//==============================SQL Entry Label ==============================
	
	JLabel label5 = new JLabel(); 	//creates a label
	label5.setText("Enter An SQL Command"); 	// set label text
	label5.setForeground(Color.RED); 	// font color	
	label5.setFont(VCROSD); 	//set font, font style, and font size
	label5.setBounds(0, 0, 270, 40); 	//set (x,y,...) and dimensions (...z,a)
	
	//==============================SQL Eexecution Window Label ==============================
	
	JLabel label6 = new JLabel(); 	//creates a label
	label6.setText("SQL Execution Results"); 	// set label text
	label6.setForeground(Color.RED); 	// font color	
	label6.setFont(VCROSD); 	//set font, font style, and font size
	label6.setBounds(0, 0, 540, 40); 	//set (x,y,...) and dimensions (...z,a)
	
	/*----------------------------------------------

	██╗░░░░░░█████╗░██████╗░███████╗██╗░░░░░░██████╗
	██║░░░░░██╔══██╗██╔══██╗██╔════╝██║░░░░░██╔════╝
	██║░░░░░███████║██████╦╝█████╗░░██║░░░░░╚█████╗░
	██║░░░░░██╔══██║██╔══██╗██╔══╝░░██║░░░░░░╚═══██╗
	███████╗██║░░██║██████╦╝███████╗███████╗██████╔╝
	╚══════╝╚═╝░░╚═╝╚═════╝░╚══════╝╚══════╝╚═════╝░
	
	----------------------------------------------*/
	
	/*--------------------------------------------------------- 
	  
	██████╗░██╗░░░██╗████████╗████████╗░█████╗░███╗░░██╗░██████╗
	██╔══██╗██║░░░██║╚══██╔══╝╚══██╔══╝██╔══██╗████╗░██║██╔════╝
	██████╦╝██║░░░██║░░░██║░░░░░░██║░░░██║░░██║██╔██╗██║╚█████╗░
	██╔══██╗██║░░░██║░░░██║░░░░░░██║░░░██║░░██║██║╚████║░╚═══██╗
	██████╦╝╚██████╔╝░░░██║░░░░░░██║░░░╚█████╔╝██║░╚███║██████╔╝
	╚═════╝░░╚═════╝░░░░╚═╝░░░░░░╚═╝░░░░╚════╝░╚═╝░░╚══╝╚═════╝░
	 
	 ---------------------------------------------------------*/
	
	//==============================Connect to Database Button==============================
	
	button = new JButton(); // 	creates button
	button.setBounds(135, 180, 270, 40); 	// sets location (x,y,..) and dimensions (...z,a)
	button.setFont(VCROSD);
	button.setText("Connect to Database"); 	//button text
	button.setFocusable(false); 	//removes the dotted line highlight when selected
	button.setBackground(new Color(0x353535));
	button.setBorder(BorderFactory.createEtchedBorder());
	button.setContentAreaFilled(false); //removes highlight when clicking on the button (and bckgrnd)
	button.addMouseListener(this);
	button.addActionListener(this); 	//button response when pressed
	button.setForeground(Color.green);
	button.setEnabled(true); 	//enabled clicking of the button
	
	//==============================Clear SQL Command Button ==============================
	
	button2 = new JButton(); // 	creates button
	button2.setBounds(0, 320, 270, 40); 	// sets location (x,y,..) and dimensions (...z,a)
	button2.setFont(VCROSD);
	button2.setText("Clear SQL Command"); 	//button text
	button2.setFocusable(false); 	//removes the dotted line highlight when selected
	button2.setBackground(new Color(0x353535));
	button2.setBorder(BorderFactory.createEtchedBorder());
	button2.setContentAreaFilled(false); //removes highlight when clicking on the button (and bckgrnd)
	button2.addMouseListener(this);
	button2.addActionListener(this); 	//button response when pressed
	button2.setForeground(new Color(0x4B4B4B));
	button2.setEnabled(false); 	//enabled clicking of the button
	
	//==============================Clear SQL Command Button ==============================
	
	button3 = new JButton(); // 	creates button
	button3.setBounds(270, 320, 270, 40); 	// sets location (x,y,..) and dimensions (...z,a)
	button3.setFont(VCROSD);
	button3.setText("Enter SQL Command"); 	//button text
	button3.setFocusable(false); 	//removes the dotted line highlight when selected
	button3.setBackground(new Color(0x353535));
	button3.setBorder(BorderFactory.createEtchedBorder());
	button3.setContentAreaFilled(false); //removes highlight when clicking on the button (and bckgrnd)
	button3.addMouseListener(this);
	button3.addActionListener(this); 	//button response when pressed
	button3.setForeground(new Color(0x4B4B4B));
	button3.setEnabled(false); 	//enabled clicking of the button
	
	//==============================Clear SQL Results Button ==============================
	
	button4 = new JButton(); // 	creates button
	button4.setBounds(270, 560, 540, 40); 	// sets location (x,y,..) and dimensions (...z,a)
	button4.setFont(VCROSD);
	button4.setText("Clear SQL Results"); 	//button text
	button4.setFocusable(false); 	//removes the dotted line highlight when selected
	button4.setBackground(new Color(0x353535));
	button4.setBorder(BorderFactory.createEtchedBorder());
	button4.setContentAreaFilled(false); //removes highlight when clicking on the button (and bckgrnd)
	button4.addMouseListener(this);
	button4.addActionListener(this); 	//button response when pressed
	button4.setForeground(new Color(0x4B4B4B));
	button4.setEnabled(false); 	//enabled clicking of the button
	
	/*---------------------------------------------------------
	  
	██████╗░██╗░░░██╗████████╗████████╗░█████╗░███╗░░██╗░██████╗
	██╔══██╗██║░░░██║╚══██╔══╝╚══██╔══╝██╔══██╗████╗░██║██╔════╝
	██████╦╝██║░░░██║░░░██║░░░░░░██║░░░██║░░██║██╔██╗██║╚█████╗░
	██╔══██╗██║░░░██║░░░██║░░░░░░██║░░░██║░░██║██║╚████║░╚═══██╗
	██████╦╝╚██████╔╝░░░██║░░░░░░██║░░░╚█████╔╝██║░╚███║██████╔╝
	╚═════╝░░╚═════╝░░░░╚═╝░░░░░░╚═╝░░░░╚════╝░╚═╝░░╚══╝╚═════╝░
	 
	 ---------------------------------------------------------*/
	
	/*-----------------------------------------------

	██████╗░░█████╗░███╗░░██╗███████╗██╗░░░░░░██████╗
	██╔══██╗██╔══██╗████╗░██║██╔════╝██║░░░░░██╔════╝
	██████╔╝███████║██╔██╗██║█████╗░░██║░░░░░╚█████╗░
	██╔═══╝░██╔══██║██║╚████║██╔══╝░░██║░░░░░░╚═══██╗
	██║░░░░░██║░░██║██║░╚███║███████╗███████╗██████╔╝
	╚═╝░░░░░╚═╝░░╚═╝╚═╝░░╚══╝╚══════╝╚══════╝╚═════╝░
	
	-----------------------------------------------*/
	
	//==============================Top Left Panel Bounds==============================

	JPanel TLPanel =  new JPanel();
	TLPanel.setLayout(null); 	//Allows Specific Positioning within the JFrame
	TLPanel.setBackground(new Color(0x000000));	
	TLPanel.setBounds(0, 0, 540, 240); //x,y,width, height
	
	//==============================Connection Details Row==============================
	
	JPanel subTLPanel = new JPanel();
	subTLPanel.setLayout(null); 	//Allows Specific Positioning within the JFrame
	subTLPanel.setBackground(new Color(0x000000));	
	subTLPanel.setBounds(0, 0, 540, 40); //x,y,width, height

	//==============================Properties File Row==============================
	
	JPanel subTLPanel1 = new JPanel();
	subTLPanel1.setLayout(null);
	subTLPanel1.setBackground(new Color(0x000000));	
	subTLPanel1.setBounds(0, 40, 540, 40); //x,y,width, height
	
	//==============================Username Row==============================
	
	JPanel subTLPanel2 = new JPanel();
	subTLPanel2.setLayout(null);
	subTLPanel2.setBackground(new Color(0x000000));	
	subTLPanel2.setBounds(0, 80, 540, 40); //x,y,width, height
	
	//==============================Password Row==============================
	
	JPanel subTLPanel3 = new JPanel();
	subTLPanel3.setLayout(null);
	subTLPanel3.setBackground(new Color(0x000000));	
	subTLPanel3.setBounds(0, 120, 540, 40); //x,y,width, height
	
	//==============================Top Right Panel Bounds==============================

	JPanel TRPanel =  new JPanel();
	TRPanel.setLayout(null); 	//Allows Specific Positioning within the JFrame
	TRPanel.setBackground(new Color(0x000000));	
	TRPanel.setBounds(540, 0, 540, 360); //x,y,width, height
	
	//==============================SQL Command Entry Row==============================
	
	JPanel subTRPanel = new JPanel();
	subTRPanel.setLayout(null); 	//Allows Specific Positioning within the JFrame
	subTRPanel.setBackground(new Color(0x000000));	
	subTRPanel.setBounds(0, 0, 540, 40); //x,y,width, height
	
	//==============================SQL Command Entry Window==============================
	
	JPanel subTRPanel1 = new JPanel();
	subTRPanel1.setLayout(null); 	//Allows Specific Positioning within the JFrame
	subTRPanel1.setBackground(new Color(0x000000));	
	subTRPanel1.setBounds(0, 40, 540, 280); //x,y,width, height
	
	//==============================Bottom Panel Bounds==============================

	JPanel botPanel =  new JPanel();
	botPanel.setLayout(null); 	//Allows Specific Positioning within the JFrame
	botPanel.setBackground(new Color(0x000000));	
	botPanel.setBounds(0, 360, 1080, 600); //x,y,width, height
	
	//==============================Connection Status Row==============================

	JPanel subbotPanel =  new JPanel();
	subbotPanel.setLayout(null); 	//Allows Specific Positioning within the JFrame
	subbotPanel.setBackground(new Color(0x000000));	
	subbotPanel.setBounds(0, 0, 1080, 40); //x,y,width, height
	
	//==============================SQL Output Row==============================

	JPanel subbotPanel1 =  new JPanel();
	subbotPanel1.setLayout(null); 	//Allows Specific Positioning within the JFrame
	subbotPanel1.setBackground(new Color(0x000000));	
	subbotPanel1.setBounds(0, 80, 1080, 480); //x,y,width, height
	
	//==============================Clear SQL Output Row==============================

	JPanel subbotPanel2 =  new JPanel();
	subbotPanel2.setLayout(null); 	//Allows Specific Positioning within the JFrame
	subbotPanel2.setBackground(new Color(0x000000));	
	subbotPanel2.setBounds(0, 40, 1080, 40); //x,y,width, height
	
	/*-----------------------------------------------

	██████╗░░█████╗░███╗░░██╗███████╗██╗░░░░░░██████╗
	██╔══██╗██╔══██╗████╗░██║██╔════╝██║░░░░░██╔════╝
	██████╔╝███████║██╔██╗██║█████╗░░██║░░░░░╚█████╗░
	██╔═══╝░██╔══██║██║╚████║██╔══╝░░██║░░░░░░╚═══██╗
	██║░░░░░██║░░██║██║░╚███║███████╗███████╗██████╔╝
	╚═╝░░░░░╚═╝░░╚═╝╚═╝░░╚══╝╚══════╝╚══════╝╚═════╝░
	
	------------------------------------------------*/
	
	/*--------------------------------------
	 
	░█████╗░████████╗██╗░░██╗███████╗██████╗░
	██╔══██╗╚══██╔══╝██║░░██║██╔════╝██╔══██╗
	██║░░██║░░░██║░░░███████║█████╗░░██████╔╝
	██║░░██║░░░██║░░░██╔══██║██╔══╝░░██╔══██╗
	╚█████╔╝░░░██║░░░██║░░██║███████╗██║░░██║
	░╚════╝░░░░╚═╝░░░╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝
	
	 --------------------------------------*/
	
	//==============================Properties Dropdown Menu==============================
	
	String[] propertiesfile = {"root.properties", "client.properties", "oplog.properties"};
	
	propertieslist = new JComboBox<String>(propertiesfile);
	propertieslist.setPreferredSize(new Dimension(250,40));
	propertieslist.setForeground(new Color(0xFFFFFF));
	propertieslist.setBackground(new Color(0x000000));
	propertieslist.setBorder(border);
	propertieslist.setFont(VCROSD);
	propertieslist.setFocusable(false);
	propertieslist.setEditable(false);
	propertieslist.setBounds(270, 0, 270, 40); //x,y,width, height
	
	//==============================Username Textfield==============================
	
	username = new JTextField();
	username.setPreferredSize(new Dimension(250,40));
	username.setForeground(new Color(0xFFFFFF));
	username.setBackground(new Color(0x000000));
	username.setBorder(border);
	username.setFont(VCROSD);
	username.setEditable(true);
	username.setBounds(270, 0, 270, 40); //x,y,width, height
	
	//==============================Password Textfield==============================
	
	password = new JPasswordField();
	password.setPreferredSize(new Dimension(250,40));
	password.setForeground(new Color(0xFFFFFF));
	password.setBackground(new Color(0x000000));
	password.setBorder(border);
	password.setFont(VCROSD);
	password.setEditable(true);
	password.setBounds(270, 0, 270, 40); //x,y,width, height
	
	//==============================Connection Status Textfield==============================
	
	status = new JTextField();
	//status.setPreferredSize(new Dimension(250,40));
	status.setForeground(new Color(0xFFFFFF));
	status.setBackground(new Color(0x000000));
	status.setBorder(border);
	status.setFont(VCROSD);
	status.setText("*** NO CONNECTION ***");
	status.setEditable(false);
	status.setBounds(0, 0, 1080, 40); //x,y,width, height
	
	//==============================SQL Entry Text Area==============================
	
	sqlentry = new JTextArea();
	//sqlentry.setPreferredSize(new Dimension(250,40));
	sqlentry.setForeground(new Color(0xFFFFFF));
	sqlentry.setBackground(new Color(0x000000));
	sqlentry.setBorder(border);
	sqlentry.setFont(VCROSD);
	sqlentry.setEditable(false);
	sqlentry.setBounds(0, 0, 540, 280); //x,y,width, height
	
	//scrollPane = new JScrollPane(sqlentry, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	//scrollPane.setBounds(10,60,780,500);
	//scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	
	//==============================SQL Entry Text Area==============================
	
	tableresults = new JTable();
	tableresults.setPreferredSize(new Dimension(250,40));
	tableresults.setForeground(new Color(0xFFFFFF));
	tableresults.setBackground(new Color(0x000000));
	tableresults.setGridColor(Color.WHITE);
	tableresults.setBorder(border);
	tableresults.setFont(VCROSD2);
	tableresults.setBounds(0, 0, 1080, 480); //x,y,width, height
	
	
	
	/*---------------------------------------
	 
	░█████╗░████████╗██╗░░██╗███████╗██████╗░
	██╔══██╗╚══██╔══╝██║░░██║██╔════╝██╔══██╗
	██║░░██║░░░██║░░░███████║█████╗░░██████╔╝
	██║░░██║░░░██║░░░██╔══██║██╔══╝░░██╔══██╗
	╚█████╔╝░░░██║░░░██║░░██║███████╗██║░░██║
	░╚════╝░░░░╚═╝░░░╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝
	
	 --------------------------------------*/
	
	/*-----------------------------------------
	 
	███████╗██████╗░░█████╗░███╗░░░███╗███████╗
	██╔════╝██╔══██╗██╔══██╗████╗░████║██╔════╝
	█████╗░░██████╔╝███████║██╔████╔██║█████╗░░
	██╔══╝░░██╔══██╗██╔══██║██║╚██╔╝██║██╔══╝░░
	██║░░░░░██║░░██║██║░░██║██║░╚═╝░██║███████╗
	╚═╝░░░░░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝
	
	 ----------------------------------------*/
	
	//==============================My Frame==============================
	
	this.setTitle("SQL Client App Version - NPA.CNT.4714.Fall.2022.Project.3"); 	//set title of this
	this.setSize(1096, 1000); 	//set size (x,y) of this
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(null);
	this.setIconImage(image.getImage()); 	//sets the icon to image
	this.getContentPane().setBackground(new Color(0x000000)); 	//background color
	this.setResizable(false); 	//prevent resizing
	this.setVisible(true); 	//makes the this visible 
	
	
	this.add(TLPanel);
	this.add(TRPanel);
	this.add(botPanel);
	//this.add(scrollPane);
	TLPanel.add(subTLPanel);
	TLPanel.add(subTLPanel1);
	TLPanel.add(subTLPanel2);
	TLPanel.add(subTLPanel3);
	TLPanel.add(button);
	subTLPanel.add(label);
	subTLPanel1.add(label2);
	subTLPanel1.add(propertieslist);
	subTLPanel2.add(label3);
	subTLPanel2.add(username);
	subTLPanel3.add(label4);
	subTLPanel3.add(password);
	TRPanel.add(subTRPanel);
	TRPanel.add(subTRPanel1);
	TRPanel.add(button2);
	TRPanel.add(button3);
	subTRPanel.add(label5);
	//subTRPanel1.add(scrollPane);
	subTRPanel1.add(sqlentry);
	botPanel.add(subbotPanel);
	botPanel.add(subbotPanel1);
	botPanel.add(subbotPanel2);
	botPanel.add(button4);
	subbotPanel.add(status);
	subbotPanel1.add(tableresults);
	subbotPanel2.add(label6);
	//tableresults.add(scrollPane);
	
	}
	
	/*----------------------------------------
 
	███████╗██████╗░░█████╗░███╗░░░███╗███████╗
	██╔════╝██╔══██╗██╔══██╗████╗░████║██╔════╝
	█████╗░░██████╔╝███████║██╔████╔██║█████╗░░
	██╔══╝░░██╔══██╗██╔══██║██║╚██╔╝██║██╔══╝░░
	██║░░░░░██║░░██║██║░░██║██║░╚═╝░██║███████╗
	╚═╝░░░░░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝
	
	 ----------------------------------------*/
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		boolean usernameOK = false;
		boolean passwordOK = false;
		
		
		//==============================Connect to Database Button==============================
		
		if(e.getSource() == button && button.isEnabled())
		{
			
			Properties properties = new Properties();
			FileInputStream filein = null;
			MysqlDataSource dataSource = null;
				   
			try 
			{
				filein = new FileInputStream((String) propertieslist.getSelectedItem());
				properties.load(filein);
					
				if(properties.getProperty("MYSQL_DB_USERNAME").equals(username.getText()))
				{
					usernameOK = true;
				}
					
				//System.out.println("Correct User: " + properties.getProperty("MYSQL_DB_USERNAME") + "\nYour Input: " + username.getText());
					
				if(properties.getProperty("MYSQL_DB_PASSWORD").equals(password.getText()))
				{
					passwordOK = true;
				}
					
				//System.out.println("Correct User: " + properties.getProperty("MYSQL_DB_PASSWORD") + "\nYour Input: " + password.getText());
				//System.out.print("\n");
					   
				if(usernameOK && passwordOK)
				{
					
					dataSource = new MysqlDataSource();
				    dataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
				    dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
				    dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD")); 
				    
					URL = (properties.getProperty("MYSQL_DB_URL"));
					User = (properties.getProperty("MYSQL_DB_USERNAME"));
					Pass = (properties.getProperty("MYSQL_DB_PASSWORD"));
 	
					connection = dataSource.getConnection();
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				      
				    System.out.println("Connected To: " + URL + " As " + User);

					connectedToDatabase = true;
					    	
					status.setText("Connected to " + properties.getProperty("MYSQL_DB_URL"));
					
					button2.setForeground(Color.CYAN);
					button2.setEnabled(true); 	//enabled clicking of the button
					
					button3.setForeground(Color.GREEN);
					button3.setEnabled(true); 	//enabled clicking of the button
					
					button4.setForeground(Color.CYAN);
					button4.setEnabled(true); 	//enabled clicking of the button
					
					sqlentry.setEditable(true);
				 }
				 else
				 {
					button2.setForeground(new Color(0x4B4B4B));
					button2.setEnabled(false); 	//enabled clicking of the button
					
					button3.setForeground(new Color(0x4B4B4B));
					button3.setEnabled(false); 	//enabled clicking of the button
					
					button4.setForeground(new Color(0x4B4B4B));
					button4.setEnabled(false); 	//enabled clicking of the button
					
					sqlentry.setEditable(true);
					
					status.setText("*** NO CONNECTION ***");
					
					
					connection.close();
					statement.close();
				 }
					
			 } 
			 catch (FileNotFoundException e1) 
			{
				e1.printStackTrace();
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}

		}
		
		//==============================Clear SQL Command Button ==============================
		
		if(e.getSource() == button2 && button2.isEnabled())
		{
			sqlentry.setText("");
		}
		
		//==============================Execute SQL Command Button ==============================
		
		if(e.getSource() == button3 && button3.isEnabled())
		{
			
			sqlquery = sqlentry.getText();
			System.out.println("Username: " + User + " Requested: " + sqlquery);
			
			try 
			{
				tableModel = new ResultSetTableModel(sqlquery, connection);
				tableresults.setModel(tableModel);
				
		        //JScrollPane scrollPane = new JScrollPane(tableresults, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
			} 
			catch (ClassNotFoundException e1) 
			{
				 tableresults.setModel(new DefaultTableModel());
				 tableModel = null;
				 JOptionPane.showMessageDialog( null, e1.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
				e1.printStackTrace();
			} 
			catch (SQLException e1) 
			{
				 tableresults.setModel(new DefaultTableModel());
				 tableModel = null;
				 JOptionPane.showMessageDialog( null, e1.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
				 e1.printStackTrace();
			}
			
			if(tableModel != null)
			{
				
				if(sqlquery.contains("select") || sqlquery.contains("SELECT"))
				{
					try 
					{
						tableModel.setQuery(sqlquery);
					} 
					catch (IllegalStateException e1) 
					{
						 JOptionPane.showMessageDialog( null, e1.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
						 e1.printStackTrace();
					} 
					catch (SQLException e1) 
					{
						 JOptionPane.showMessageDialog( null, e1.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
						 e1.printStackTrace();
					}
				}
				else
				{
					try 
					{
						tableModel.setUpdate(sqlquery);
						 tableresults.setModel(new DefaultTableModel());
						 tableModel = null;
					} 
					catch (IllegalStateException e1) 
					{
						 tableresults.setModel(new DefaultTableModel());
						 tableModel = null;
						 JOptionPane.showMessageDialog( null, e1.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
						 e1.printStackTrace();
					} 
					catch (SQLException e1) 
					{
						 tableresults.setModel(new DefaultTableModel());
						 tableModel = null;
						 JOptionPane.showMessageDialog( null, e1.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
						 e1.printStackTrace();
					}
					
				}
				
			}
			
		}
		
		//==============================Clear SQL Result Button ==============================
		
		if(e.getSource() == button4 && button4.isEnabled())
		{
			 tableresults.setModel(new DefaultTableModel());
			 tableModel = null;
		}
		

		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getSource() == button && button.isEnabled())
			button.setBackground(new Color(0x555555));
		
		if(e.getSource() == button2 && button2.isEnabled())
			button2.setBackground(new Color(0x555555));
		
		if(e.getSource() == button3 && button3.isEnabled())
			button3.setBackground(new Color(0x555555));
		
		if(e.getSource() == button4 && button4.isEnabled())
			button4.setBackground(new Color(0x555555));
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		if(e.getSource() == button && button.isEnabled())
			button.setBackground(new Color(0xD72329));
		
		if(e.getSource() == button2 && button2.isEnabled())
			button2.setBackground(new Color(0xD72329));
		
		if(e.getSource() == button3 && button3.isEnabled())
			button3.setBackground(new Color(0xD72329));
		
		if(e.getSource() == button4 && button4.isEnabled())
			button4.setBackground(new Color(0xD72329));
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if(e.getSource() == button && button.isEnabled())
			button.setBackground(new Color(0x353535));
		
		if(e.getSource() == button2 && button2.isEnabled())
			button2.setBackground(new Color(0x353535));
		
		if(e.getSource() == button3 && button3.isEnabled())
			button3.setBackground(new Color(0x353535));
		
		if(e.getSource() == button4 && button4.isEnabled())
			button4.setBackground(new Color(0x353535));
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		if(e.getSource() == button && button.isEnabled())
			button.setBackground(new Color(0x353535));
		
		if(e.getSource() == button2 && button2.isEnabled())
			button2.setBackground(new Color(0x353535));
		
		if(e.getSource() == button3 && button3.isEnabled())
			button3.setBackground(new Color(0x353535));
		
		if(e.getSource() == button4 && button4.isEnabled())
			button4.setBackground(new Color(0x353535));
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		if(e.getSource() == button && button.isEnabled())
			button.setBackground(new Color(0x454545));
		
		if(e.getSource() == button2 && button2.isEnabled())
			button2.setBackground(new Color(0x454545));
		
		if(e.getSource() == button3 && button3.isEnabled())
			button3.setBackground(new Color(0x454545));
		
		if(e.getSource() == button4 && button4.isEnabled())
			button4.setBackground(new Color(0x454545));
	}

}
