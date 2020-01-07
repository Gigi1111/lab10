package lab5_1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on 
 * screen. It then refers to the "CalcEngine" to do all the real work.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class UserInterfaceSet
extends UserInterface 
{
	JTextArea display;
	String operators="∪∩+-p";
   
    public UserInterfaceSet(CalcEngine engine)
    {
    	super(engine);
        calc = engine;
       makeFrame();
       frame.setVisible(true);
       
    }

    /**
     * Set the visibility of the interface.
     * @param visible true if the interface is to be made visible, false otherwise.
     */
    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }

    /**
     * Make the frame for the user interface.
     */
    private void makeFrame()
    {
        frame = new JFrame(calc.getTitle());
        
        contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        display = new JTextArea();
        contentPane.add(display, BorderLayout.NORTH);

        buttonPanel = new JPanel(new GridLayout(4, 6));
           
           
            
           
            addButton(buttonPanel, "1");
            addButton(buttonPanel, "2");
            addButton(buttonPanel, "3");
            addButton(buttonPanel, "4");
            addButton(buttonPanel, "5");
            buttonPanel.add(new JLabel(" "));
            
            addButton(buttonPanel, "6");
            addButton(buttonPanel, "7");
            addButton(buttonPanel, "8");
            addButton(buttonPanel, "9");
            addButton(buttonPanel, "0");
            buttonPanel.add(new JLabel(" "));
            
            addButton(buttonPanel, "+");
            addButton(buttonPanel, "-");
            addButton(buttonPanel, "∩");
            addButton(buttonPanel, "=");
            addButton(buttonPanel, "Cl");
            addButton(buttonPanel, "?");
            
            addButton(buttonPanel, "{");
            addButton(buttonPanel, "}");
            addButton(buttonPanel, ",");
            addButton(buttonPanel, "p");//power
            addButton(buttonPanel, "n");//how many element
            
              
             
            
             
            
       

            
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        status = new JLabel(calc.getAuthor());
        contentPane.add(status, BorderLayout.SOUTH);

        frame.pack();
    }

    /**
     * Add a button to the button panel.
     * @param panel The panel to receive the button.
     * @param buttonText The text for the button.
     */
    private void addButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);
    }

    /**
     * An interface action has been performed.
     * Find out what it was and handle it.
     * @param event The event that has occured.
     */
    public void actionPerformed(ActionEvent event)
    {
        String command = event.getActionCommand();
        
        
        if(command.equals("=")) {
            calc.equals();
        }
        else if(command.equals("n")) {
            calc.getNumberOfElement();
        }
        else if(command.equals("p")) {
            calc.getPowerSet();
        }
        else if(command.equals("Cl")) {
            calc.clear();
        }
        else if(command.equals("?")) {
            showInfo();
        }
//        else if(operators.contains(command)) {
//        	//before and after command
//        	calc.setOperator(command);
//        }
        else{
        	
        	calc.displayAppend(command);
        	if(operators.contains(command)
        			&&//it's }- or }+ instead of just a "-1" element
        		calc.getDisplay().indexOf("}")==calc.getDisplay().indexOf(command)-1) {
        			    		calc.setOperator(command);
            	}
        }
        // else unknown command.

        redisplay();
    }

    /**
     * Update the interface display to show the current value of the 
     * calculator.
     */
    protected void redisplay()
    {
    	 
    	display.setText(calc.getDisplay());
    	
//    	if( calc.getDisplayValue()%1>0)
//        display.setText("" + calc.getDisplayValue());
//    	else{
//    		display.setText("" + (int)calc.getDisplayValue());
//    	}
    }

   
}

