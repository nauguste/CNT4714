/*   Name:  Naseem Auguste
     Course: CNT 4714 – Fall 2022 
     Assignment title: Project 1 – Event-driven Enterprise Simulation 
     Date: Sunday September 11, 2022 
*/ 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class myFrame extends JFrame implements ActionListener, MouseListener
{
	
	static JButton button;
	static JButton button2;
	static JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JLabel label;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	static JTextField textField;
	static JTextField textField2;
	static JTextField textField3;
	static JTextField textField4;
	JPanel topPanel;
	JPanel P2;
	JPanel PCP;
	JPanel VFOP;
	JPanel P3;
	JPanel P4;
	JPanel botPanel;
	Font VCROSD;
	Font VCROSD2;
	static boolean Flag = true;
	static boolean flag1 = false;
	static boolean flag2 = false;
	static boolean flag3 = false;
	int itemnum = 1;
	double sum = 0;
	String Quantity;
	double price;
	static int x = 1;
	static int y = 1;
	String name;
	String ID;
	String stock;
	String priceS;
	String[] orders = new String[100];;
	Timestamp timestamp;
	Date date;
	double tax = 0.05;
	//Scanner scanner;
	
	
	
	myFrame() throws FileNotFoundException
	{
		try 
		{
			VCROSD = Font.createFont(Font.TRUETYPE_FONT, new File("VCROSD.ttf")).deriveFont(20f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("VCROSD.ttf"))); // loads in custom font
		}
		catch(IOException | FontFormatException e) 
		{
		
		}
	
		try 
		{
			VCROSD2 = Font.createFont(Font.TRUETYPE_FONT, new File("VCROSD.ttf")).deriveFont(36f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("VCROSD.ttf"))); // loads in custom font
		}
		catch(IOException | FontFormatException e) 
		{
		
		}
		
		
		
	ImageIcon image = new ImageIcon("UKLogo.png"); 	//frame icon
	

	
	//==============================Item ID Label ==============================
	
	label = new JLabel(); 	//creates a label
	label.setText("ENTER ITEM ID FOR ITEM #" + itemnum + ": "); 	// set label text
	label.setForeground(Color.RED); 	// font color	
	label.setFont(VCROSD); 	//set font, font style, and font size
	label.setBounds(140, 20, 480, 24); 	//set (x,y,...) and dimensions (...z,a)
	
	//==============================Quantity Label ==============================
	
	label2 = new JLabel(); 	//creates a label
	label2.setText("ENTER QUANTITY FOR ITEM #" + itemnum + ":"); 	// set label text
	label2.setForeground(Color.RED); 	// font color	
	label2.setFont(VCROSD); 	//set font, font style, and font size
	label2.setBounds(140, 20, 480, 24); 	//set (x,y,...) and dimensions (...z,a)
	
	//==============================Details Label ==============================
	
	label3 = new JLabel(); 	//creates a label
	label3.setText("ITEM #" + itemnum + " DETAIL"); 	// set label text
	label3.setForeground(Color.RED); 	// font color	
	label3.setFont(VCROSD); 	//set font, font style, and font size
	label3.setBounds(140, 20, 480, 24); 	//set (x,y,...) and dimensions (...z,a)
	
	//==============================Subtotal Label ==============================
	
	label4 = new JLabel(); 	//creates a label
	label4.setText("SUBTOTAL FOR 'X' ITEMS"); 	// set label text
	label4.setForeground(Color.RED); 	// font color	
	label4.setFont(VCROSD); 	//set font, font style, and font size
	label4.setBounds(140, 20, 480, 24); 	//set (x,y,...) and dimensions (...z,a)
	
	//==============================Top Panel==============================

	topPanel =  new JPanel();
	//Border border = new LineBorder(Color.black,5,true);  
	topPanel.setPreferredSize(new Dimension(1065, 40));
	topPanel.setBackground(new Color(0x000000));
	//topPanel.setBorder(border);
	topPanel.setLayout(new GridLayout());
	//topPanel.setBounds(0,0, 1065, 480); //x,y,width, height
	
	//==============================2nd Panel==============================

	P2 =  new JPanel();
	P2.setPreferredSize(new Dimension(1065, 40));
	P2.setBackground(new Color(0x000000));
	//P2.setBorder(border);
	P2.setLayout(new GridLayout());
	P2.setBounds(0,0, 1065, 120); //x,y,width, height
	
	//==============================Process/Confirm Button Panel==============================

	PCP =  new JPanel();
	PCP.setPreferredSize(new Dimension(1065, 40));
	PCP.setBackground(new Color(0x000000));
	//P4.setBorder(border);
	PCP.setLayout(new GridLayout(0,2));
	PCP.setBounds(0,0, 1065, 120); //x,y,width, height
	
	//==============================3rd Panel==============================

	P3 =  new JPanel();
	P3.setPreferredSize(new Dimension(1065, 80));
	P3.setBackground(new Color(0x000000));
	//P3.setBorder(border);
	P3.setLayout(new GridLayout(0,1));
	P3.setBounds(0,0, 1065, 120); //x,y,width, height
	
	
	//==============================4th Panel==============================

	P4 =  new JPanel();
	P4.setPreferredSize(new Dimension(1065, 80));
	P4.setBackground(new Color(0x000000));
	//P4.setBorder(border);
	P4.setLayout(new GridLayout(0,1));
	P4.setBounds(0,0, 1065, 120); //x,y,width, height
	
	//==============================Process/Confirm Button Panel==============================

	VFOP =  new JPanel();
	VFOP.setPreferredSize(new Dimension(1065, 40));
	VFOP.setBackground(new Color(0x000000));
	VFOP.setLayout(new GridLayout(0,2));
	VFOP.setBounds(0,0, 1065, 120); //x,y,width, height
	
	//==============================Bottom Label==============================
	
	botPanel =  new JPanel();
	botPanel.setPreferredSize(new Dimension(1065, 100));
	botPanel.setBackground(new Color(0x000000));
	//botPanel.setBorder(border);
	botPanel.setLayout(new GridLayout(0,1));
	//botPanel.setBounds(0,0, 1065, 240);
	
	//==============================Process Item Button==============================
	
	button = new JButton(); // 	creates button
	button.setBounds(200, 100, 150, 30); 	// sets location (x,y,..) and dimensions (...z,a)
	button.setFont(VCROSD);
	button.setText("PROCESS ITEM #1"); 	//button text
	button.setFocusable(false); 	//removes the dotted line highlight when selected
	button.setBackground(new Color(0x353535));
	button.setBorder(BorderFactory.createEtchedBorder());
	button.setContentAreaFilled(false); //removes highlight when clicking on the button (and bckgrnd)
	button.addMouseListener(this);
	button.addActionListener(this); 	//button response when pressed
	button.setForeground(Color.green);
	button.setEnabled(true); 	//enabled clicking of the button

	
	//==============================Confirm Item Button==============================
	
	button2 = new JButton(); // 	creates button
	button2.setBounds(200, 100, 150, 30); 	// sets location (x,y,..) and dimensions (...z,a)
	button2.setFont(VCROSD);
	button2.setText("CONFIRM ITEM #1"); 	//button text
	button2.setFocusable(false); 	//removes the dotted line highlight when selected
	button2.setBackground(new Color(0x353535));
	button2.setBorder(BorderFactory.createEtchedBorder());
	button2.setContentAreaFilled(false); //removes highlight when clicking on the button (and bckgrnd)
	button2.addMouseListener(this);
	button2.addActionListener(this); 	//button response when pressed
	button2.setForeground(Color.green);
	button2.setEnabled(false); 	//enabled clicking of the button

	
	//==============================View Order Button==============================
	
	button3 = new JButton(); // 	creates button
	button3.setBounds(200, 100, 150, 30); 	// sets location (x,y,..) and dimensions (...z,a)
	button3.setFont(VCROSD);
	button3.setText("VIEW ORDER"); 	//button text
	button3.setFocusable(false); 	//removes the dotted line highlight when selected
	button3.setBackground(new Color(0x353535));
	button3.setBorder(BorderFactory.createEtchedBorder());
	button3.setContentAreaFilled(false); //removes highlight when clicking on the button (and bckgrnd)
	button3.addMouseListener(this);
	button3.addActionListener(this); 	//button response when pressed
	button3.setForeground(Color.gray);
	button3.setEnabled(false); 	//enabled clicking of the button

	
	//==============================Finish Order Button==============================
	
	
	button4 = new JButton(); // 	creates button
	button4.setBounds(200, 100, 150, 30); 	// sets location (x,y,..) and dimensions (...z,a)
	button4.setFont(VCROSD);
	button4.setText("FINISH ORDER"); 	//button text
	button4.setFocusable(false); 	//removes the dotted line highlight when selected
	button4.setBackground(new Color(0x353535));
	button4.setBorder(BorderFactory.createEtchedBorder());
	button4.setContentAreaFilled(false); //removes highlight when clicking on the button (and bckgrnd)
	button4.addMouseListener(this);
	button4.addActionListener(this); 	//button response when pressed
	button4.setForeground(Color.gray);
	button4.setEnabled(false); 	//enabled clicking of the button

	
	//==============================New Order Button==============================
	
	button5 = new JButton(); // 	creates button
	button5.setBounds(200, 100, 150, 30); 	// sets location (x,y,..) and dimensions (...z,a)
	button5.addActionListener(this); 	//button response when pressed
	button5.setFont(VCROSD2);
	button5.setText("NEW ORDER"); 	//button text
	button5.setFocusable(false); 	//removes the dotted line highlight when selected
	button5.setForeground(Color.orange);
	button5.setBackground(new Color(0x353535));
	button5.setBorder(BorderFactory.createEtchedBorder());
	button5.setEnabled(true); 	//enabled clicking of the button
	button5.addMouseListener(this);
	button5.setContentAreaFilled(false); //removes highlight when clicking on the button (and bckgrnd)
	
	//==============================Exit Button==============================
	
	button6 = new JButton(); // 	creates button
	button.setPreferredSize(new Dimension(40, 40));
	button6.addActionListener(this); 	//button response when pressed
	button6.setFont(VCROSD2);
	button6.setText("EXIT"); 	//button text
	button6.setFocusable(false); 	//removes the dotted line highlight when selected
	button6.setForeground(Color.RED);
	button6.setBackground(new Color(0x353535));
	button6.setBorder(BorderFactory.createEtchedBorder());
	button6.setEnabled(true); 	//enabled clicking of the button
	button6.addMouseListener(this);
	button6.setContentAreaFilled(false); //removes highlight when clicking on the button (and bckgrnd)
	
	
	//==============================Item Number Textfield==============================
	
	textField = new JTextField();
	Border border2 = new LineBorder(Color.red,2,true);
	textField.setPreferredSize(new Dimension(250,40));
	textField.setForeground(new Color(0xFFFFFF));
	textField.setBackground(new Color(0x000000));
	textField.setBorder(border2);
	textField.setFont(VCROSD);
	textField.setText("Enter Item # here...");
	textField.setEditable(true);
	
	//==============================Item Quantity Textfield==============================
	
	textField2 = new JTextField();
	textField2.setPreferredSize(new Dimension(250,40));
	textField2.setForeground(new Color(0xFFFFFF));
	textField2.setBackground(new Color(0x000000));
	textField2.setBorder(border2);
	textField2.setFont(VCROSD);
	textField2.setText("Enter Item Quantity here...");
	textField2.setEditable(true);
	
	//==============================Item Details Textfield==============================
	
	textField3 = new JTextField();
	textField3.setPreferredSize(new Dimension(250,40));
	textField3.setForeground(new Color(0xFFFFFF));
	textField3.setBackground(new Color(0x000000));
	textField3.setBorder(border2);
	textField3.setFont(VCROSD);
	textField3.setText(" ");
	textField3.setEditable(false);
	
	//==============================Subtotal Textfield==============================
	
	textField4 = new JTextField();
	textField4.setPreferredSize(new Dimension(250,40));
	textField4.setForeground(new Color(0xFFFFFF));
	textField4.setBackground(new Color(0x000000));
	textField4.setBorder(border2);
	textField4.setFont(VCROSD);
	textField4.setText(" ");
	textField4.setEditable(false);
	
	//==============================My Frame==============================
	
	this.setTitle("Archeron"); 	//set title of this
	this.setSize(1080, 500); 	//set size (x,y) of this
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new FlowLayout());
	this.setIconImage(image.getImage()); 	//sets the icon to image
	this.getContentPane().setBackground(new Color(0x000000)); 	//background color
	this.add(topPanel);
	this.add(P2);
	this.add(PCP);
	this.add(P3);
	this.add(P4);
	this.add(VFOP);
	this.add(botPanel);
	topPanel.add(label);
	topPanel.add(textField);
	P2.add(label2);
	P2.add(textField2);
	PCP.add(button); 
	PCP.add(button2);
	P3.add(label3);
	P3.add(textField3);
	P4.add(label4);
	P4.add(textField4);
	VFOP.add(button3);
	VFOP.add(button4);
	botPanel.add(button5);
	botPanel.add(button6);
	

	
	this.setResizable(false); 	//prevent resizing
	//this.add(textField);
	//this.add(button);	//adds the button to frame
	//this.add(label); 	//adds the label to frame	
	//this.pack();	
	
	this.setVisible(true); 	//makes the this visible 
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//==============================Button 1==============================
		 if(e.getSource() == button && button.isEnabled())
		 {
			 String outputMessage, itemID;
			 int flag = 0;
			 
			 //File f = new File("inventory.txt");
			 //FileReader fr = null;
			 //BufferedReader br = null;
			 
			 
			 try
			 {
				 Scanner scanner = new Scanner(new File("inventory.txt"));scanner.useDelimiter(",");
				 
				 
				 itemID = myFrame.textField.getText();
				 System.out.println("You Entered: " + itemID);
				 
				 Quantity = myFrame.textField2.getText();

				 ID = scanner.next();
				 System.out.println("Scanned ID: " + ID);
				 
				 name = scanner.next();
				 System.out.println("Scanned Name: " + name);
				 
				 stock = scanner.next();
				 System.out.println("Scanned Status: " + stock);
				 
				 priceS = scanner.next();
				 price = Double.parseDouble(priceS);
				 System.out.println("Scanned Price: $" + price);
				 
				 while(scanner.hasNext())
				 {
					 if(itemID.equals(ID) && stock.equals("true"))
					 {
						 System.out.print("\n\n");
						 flag = 1;
						 break;
					 }
					 else if(itemID.equals(ID) && stock.equals("false"))
					 {
						 System.out.print("Sorry, that item is out of stock. Please try another item.\n");
						 flag = 2;
						 break;
					 }
					 else
					 {
						 System.out.print("That item does not exist.\n\n");
						 flag = 3;
					 }
					 System.out.println("You Entered: " + itemID);
					 
					 ID = scanner.next();
					 System.out.println("Scanned ID: " + ID);
					 
					 name = scanner.next();
					 System.out.println("Scanned Name: " + name);
					 
					 stock = scanner.next();
					 System.out.println("Scanned Status: " + stock);
					 
					 priceS = scanner.next();
					 price = Double.parseDouble(priceS);
					 System.out.println("Scanned Price: $" + price);
					 x++;
				 }
				 
					if(y < itemnum)
					{
						y++;
						label3.setText("ITEM #" + y + " DETAIL"); 	// set label text
					}
					else 
					{
						
					}
				 
			     boolean Qty = isNumeric(Quantity);
			     System.out.println("The quantity field is:" + Qty);
			     if(Qty == false)
			     {
			    	 flag = 4;
			     }
			     else
			     {
			    	 
			     }
			    	 
			     
			     if(flag == 4) 
			     {
			    	 outputMessage = "Please Enter an Item Quantity.";
					 JOptionPane.showMessageDialog(null, outputMessage, "Archeron Error",JOptionPane.ERROR_MESSAGE);
			     }
			     else if(flag == 3)
				 {
					 outputMessage = "Item ID '" + itemID + "' is not in file";
					 JOptionPane.showMessageDialog(null, outputMessage, "Archeron Error",JOptionPane.ERROR_MESSAGE);
				 }
				 else if(flag == 2)
				 {
					 outputMessage = "Sorry, that item is out of stock. Please try another item.";
					 JOptionPane.showMessageDialog(null, outputMessage, "Archeron Error",JOptionPane.ERROR_MESSAGE);
				 }
				 else if(flag == 1)
				 {
					 Double mult = Double.valueOf(Quantity);
					 Double disc = 0.00;
						if(mult <= 4)
							disc = 0.00;
						else if(mult > 4 && mult <10)
							disc = 0.1;
						else if(mult >= 10 && mult <15)
							disc = 0.15;
						else if(mult >= 15)
							disc= 0.2;
							else {
								
							}
					 textField3.setText("Item: " + name + "  Price: $" + price + "  Quantity: " + myFrame.textField2.getText() + "  " + disc*100 + "% OFF");

					 button2.setForeground(Color.green);
					 button2.setEnabled(true); 	//enabled clicking of the button
					 button.setForeground(Color.gray);
					 button.setEnabled(false); 	//disable clicking of the button

					 
				 }
				 else
				 {
					 
				 }
				 
			 }catch (FileNotFoundException fileNotFoundException)
			 {
				 //print filenotfound error
			 }
			 /*catch (IOException ioException)
			 {
				 //print reading from file error
			 }
			 */
			 catch(NumberFormatException numberFormatException)
			 {
				 outputMessage = "Invalid input for number of line items or quantity of item's";
			 }
			
		 }
		 
		//==============================Button 2 ==============================
		 
		if(e.getSource() == button2 && button2.isEnabled())
		{	
			Double mult = Double.valueOf(Quantity);
			Double total = 0.00;
			Double disc = 0.00;
			if(mult <= 4)
				disc = 0.00;
			else if(mult > 4 && mult <10)
				disc = 0.1;
			else if(mult >= 10 && mult <15)
				disc = 0.15;
			else if(mult >= 15)
				disc= 0.2;
				else {
					
				}

			String outputMessage = "Item #" + itemnum + " has been added to your cart.";
			JOptionPane.showMessageDialog(null, outputMessage, "Archeron Alert",JOptionPane.INFORMATION_MESSAGE);
			
			total = ((mult * price)-(mult * (price * disc)));
			orders[itemnum-1] = itemnum + ".) " + "ID #" + ID + " " + myFrame.textField2.getText() + " " + name + " for  $" + String.format("%.2f", total) + ". " + "You got a " + disc*100 + "% Discount!";
			System.out.println(orders[itemnum-1]);

			itemnum++;
			
			textField.setText("");
			textField2.setText("");
			button.setForeground(Color.green);
			button.setEnabled(true); 	//enabled clicking of the button
			button2.setForeground(Color.gray);
			button2.setEnabled(false); 	//disable clicking of the button
			button3.setForeground(Color.cyan);
			button3.setEnabled(true); 	//enabled clicking of the button
			button4.setForeground(Color.cyan);
			button4.setEnabled(true); 	//disable clicking of the button
			label.setText("ENTER ITEM ID FOR ITEM #" + itemnum + ": "); 	// set label text
			label2.setText("ENTER QUANTITY FOR ITEM #" + itemnum + ":"); 	// set label text
			button.setText("PROCESS ITEM #" + itemnum); 	//button text
			button2.setText("CONFIRM ITEM #" + itemnum); 	//button text	
			
			sum += total;
			textField4.setText("$" + String.format("%.2f", sum));
			
		}
		//==============================Button 3 ==============================
		
		if(e.getSource() == button3 && button3.isEnabled())
		{
				JOptionPane.showMessageDialog(null, orders, "Your Order",JOptionPane.INFORMATION_MESSAGE);
		}
		
		//==============================Button 4 ==============================
		
		if(e.getSource() == button4 && button4.isEnabled())
		{
			
			double invoice = sum + (sum * tax);
			String totalMessage;
	        String timeStamp = new SimpleDateFormat("MM/dd/YY hh:mm:ss a z").format(new java.util.Date());
	        totalMessage = timeStamp + "\n\n" + "Number of line Items: " + (itemnum-1) + 
	        		"\n\n" + "Item# / ID / Title / Price / Qty / Disc% / Subtotal" +
	        		"\n\n"; 
	        for(int i=0;i<itemnum;i++) 
	        {
	        	if(orders[i] != null)
	        	totalMessage += orders[i] + "\n";
	        }
	        totalMessage +="\n\n" + "Order Subtotal: " + ("$" + String.format("%.2f", sum)
	        + "\n\n" + "Tax rate: " + tax + "%" + "\n\n" + "Tax Amount: " + String.format("%.2f",(sum * tax))) 
	        + "\n\n" + "Order Total: " +  String.format("%.2f",(invoice)) + "\n\n" + "Thank You For Shopping At Archeron!";
	        
			JOptionPane.showMessageDialog(null, totalMessage, "Your Invoice",JOptionPane.INFORMATION_MESSAGE);
			try 
			{
			   File ofp = new File("transactions.txt");
			   if (ofp.createNewFile());
			   FileWriter writer = new FileWriter("transactions.txt");
			   String timeStampX = new SimpleDateFormat("ddMMYYYYhhmm").format(new java.util.Date());
			   
			   
			   for(int i=0;i<itemnum;i++) 
		        {
				    
		        	if(orders[i] != null)
		        	{
		        		writer.write(timeStampX);
		        		writer.append(" " + orders[i] + " " + timeStamp + "\n");
		        	}
		        	
		        }
			   writer.close();
			} 
			catch (IOException e1) 
			{
			   e1.printStackTrace();
			}
			System.exit(0);
		}
		
		//==============================Button 5 ==============================
		
		if(e.getSource() == button5 && button5.isEnabled())
		{
			
			try 
			{
				new myFrame();
			} 
			catch (FileNotFoundException e1) 
			{
				e1.printStackTrace();
			}
			this.dispose();
		}
		
		//==============================Button 6 ==============================
		if(e.getSource() == button6 && button6.isEnabled())
		{
			System.exit(0);
		}
	}
	
	private static boolean isNumeric(String quantity) 
	{
		return quantity != null && quantity.matches("[0-9.]+");
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getSource() == button)
			button.setBackground(new Color(0x555555));
		if(e.getSource() == button2)
			button2.setBackground(new Color(0x555555));
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		if(e.getSource() == button)
			button.setBackground(new Color(0xD72329));
		if(e.getSource() == button2)
			button2.setBackground(new Color(0xD72329));
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if(e.getSource() == button)
			button.setBackground(new Color(0x353535));
		if(e.getSource() == button2)
			button2.setBackground(new Color(0x353535));
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		if(e.getSource() == button)
			button.setBackground(new Color(0x353535));
		if(e.getSource() == button2)
			button2.setBackground(new Color(0x353535));
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		if(e.getSource() == button)
			button.setBackground(new Color(0x454545));
		if(e.getSource() == button2)
			button2.setBackground(new Color(0x454545));
	}
	
}
