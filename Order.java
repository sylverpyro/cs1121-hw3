//-----------------------------------------------------
//
// Michael Benson 
// CS1121
// Fall 2004
// April 1, 2005
// Recitation Section 1
// Lab Section 10
//
// Homework Program 3
//
//-----------------------------------------------------

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Order extends JApplet { 

    public void init() {

	// set up the applet window
	
	Container window = getContentPane();
	window.setLayout(new GridLayout(5,3));

	// create the interactive buttons
	
	JButton main1 = new JButton("main1");
	JButton main2 = new JButton("main2");
	JButton main3 = new JButton("main3");
	JButton side1 = new JButton("side1");
	JButton side2 = new JButton("side2");
	JButton side3 = new JButton("side3");
	JButton desrt1 = new JButton("desrt1");
	JButton desrt2 = new JButton("desrt2");
	JButton desrt3 = new JButton("desrt3");
	JButton order = new JButton("Place Order");
	JButton reset = new JButton("Reset Order");
	
	// create the text field and headers
	
	JLabel crntOrder = new JLabel("Ready to Order");
	JLabel mainHead = new JLabel("Main Dishes");
	JLabel sideHead = new JLabel("Side Dishes");
	JLabel desrtHead = new JLabel("Deserts");

	// add the headers to the window
	
	window.add(mainHead);
	window.add(sideHead);
	window.add(desrtHead);

	// add the buttons to the window
	
	window.add(main1);
	window.add(side1);
	window.add(desrt1);
	window.add(main2);
	window.add(side2);
	window.add(desrt2);
	window.add(main3);
	window.add(side3);
	window.add(desrt3);
	window.add(order);
	window.add(crntOrder);
	window.add(reset);

	// create tracker for the order window
	
	Tracker orderTr = new Tracker(crntOrder);

	// create listeners for the order buttons
	
	Listener main1L = new Listener(1,orderTr,main1);
	Listener main2L = new Listener(2,orderTr,main2);
	Listener main3L = new Listener(3,orderTr,main3);
	Listener side1L = new Listener(4,orderTr,side1);
	Listener side2L = new Listener(5,orderTr,side2);
	Listener side3L = new Listener(6,orderTr,side3);
	Listener desrt1L = new Listener(7,orderTr,desrt1);
	Listener desrt2L = new Listener(8,orderTr,desrt2);
	Listener desrt3L = new Listener(9,orderTr,desrt3);
	Listener orderL = new Listener(10,orderTr,order);
	Listener resetL = new Listener(11,orderTr,reset);

	// add listeners to the buttons
	
	main1.addActionListener(main1L);
	main2.addActionListener(main2L);
	main3.addActionListener(main3L);
	
	side1.addActionListener(side1L);
	side2.addActionListener(side2L);
	side3.addActionListener(side3L);
	
	desrt1.addActionListener(desrt1L);
	desrt2.addActionListener(desrt2L);
	desrt3.addActionListener(desrt3L);
	
	order.addActionListener(orderL);
	reset.addActionListener(resetL);

	} // end init method

    } // end of Order class

class Listener implements ActionListener {

    // properties
    
    private final int buttonIDNum;
    private final Tracker crntOrder;
    private final JButton orderName;

    public Listener (int w, Tracker t, JButton b) {
	buttonIDNum = w;
	crntOrder = t;
	orderName = b;

	} // end of constructor

    public void actionPerformed (ActionEvent event) {
	
	crntOrder.buttonPushed(buttonIDNum, orderName);

	} // end of actionPerformed method

    } // end of listener class

class Tracker {

    // properties
    
    private final JLabel orderName = null;
    private final JLabel orderMsg;
    private int i;
    private int [] supply = {0,5,5,5,5,5,5,5,5,5};
    private int [] crnt_order = {0,0,0,0,0,0,0,0,0,0};
    private String prnt;
    private String [] products = {null,"main1","main2","main3","side1","side2","side3","dsrt1","dsrt2","dsrt3"};
    private String [] html = {"<html>", "<br>", "</html>"};
    private boolean mainOrdered = false;
    
	public Tracker(JLabel o) {

	    orderMsg = o;
	
	} // end of constructor

    public void buttonPushed (int buttonNum, JButton ordered) {

    if (buttonNum == 1 || buttonNum == 2 || buttonNum == 3) {
	
	    mainOrdered = true;
	
	} // end if statement
	
    if (buttonNum == 11) {

	orderMsg.setText("<html>Order reset.<br>Ready to order.");
	prnt = null;
	
	    for (i = 0 ; i <= supply.length-1 ; i=i+1) {
		    crnt_order [i] = 0;
		    mainOrdered = false;
	        } // end of for loop
	
	} // end of if statement
    
    else if (buttonNum == 10 && mainOrdered == true) {
	
	orderMsg.setText("<html>Thank you for your order.<br>Ready for next order.</html>");
	prnt = null;
	mainOrdered = false;
	
	for (i = 0; i < supply.length - 1 ; i = i+1) {
		    supply [i] = supply [i] - crnt_order [i];
		    crnt_order [i] = 0;
	        } // end of for loop
	
	} // end of else-if statement
    
    else if (buttonNum == 10 && mainOrdered == false) {
	orderMsg.setText("<html>Please make sure to order a main dish</html>");
	} // end else-if statement
    
    else {
	
	crnt_order [buttonNum] = crnt_order [buttonNum] + 1;
	
	if (supply [buttonNum] - crnt_order [buttonNum] <= 0) {
	    orderMsg.setText("<html>Sorry, but we are out of that item.<br>Please select another item.</html></br> ");
	    crnt_order [buttonNum] = crnt_order [buttonNum] - 1;
	    } // end of embeded if statement
	
	else {
	    
	    if (prnt == null) {
		prnt = "<html>" + products [buttonNum];
		orderMsg.setText(prnt);
		} // end embeded if statement
	    
	    else {
		prnt = prnt + "<br>" + products [buttonNum];
		orderMsg.setText(prnt);
		} // end embeded else statement
	    
	    } // end of embeded else statement
	
	} // end of else statement
    
    } // end buttonPushed method
    
} // end tracker class
