package lab10;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
 
/**
 * This program demonstrates how to use JPanel in Swing.
 * @author www.codejava.net
 */
public class GraphGUI extends JFrame {
     
	static WeightedGraphFromList4 wg = new WeightedGraphFromList4();
	static  JComboBox<String> cb;
    static JComboBox<String> cb1;
    
    public GraphGUI() {
    	JTextArea tf=new JTextArea();
    	JLabel labelStart = new JLabel("Start: ");
        JLabel labelEnd = new JLabel("Destination: ");
        JButton buttonAllRoutes = new JButton("All Routes");
        JButton buttonLeastTransferRoutes = new JButton("Least Transfer Routes(shortest");
        JButton buttonQuickestRoutes = new JButton("Quickest Routes(least weight)");
        //
         JCheckBox checkNoToll = new JCheckBox("No Toll");         
         Set<String> s = wg.hmap.keySet();
         String[] choices = new String[s.size()];
         int i=0;
         for (String x : wg.hmap.keySet()) 
             choices[i++] = x; 
       //
         cb = new JComboBox<String>(choices);
        cb1 = new JComboBox<String>(choices);
        
        
         
        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());
         
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
         
        // add components to the panel
       // constraints.gridx = 0;
        constraints.gridy = 0;     
        newPanel.add(labelStart, constraints);
 
        //constraints.gridx = 1;
        constraints.gridy = 0;   
        constraints.gridwidth = 20;
        newPanel.add(cb, constraints);
        //newPanel.add(textUsername, constraints);
         
     //   constraints.gridx = 0;
        constraints.gridy = 1;     
        newPanel.add(labelEnd, constraints);
         
    //    constraints.gridx = 1;
        constraints.gridy = 1;  
        constraints.gridwidth = 20;
        newPanel.add(cb1, constraints);
        
        
     //   constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonAllRoutes, constraints);
      
         
     //   constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonLeastTransferRoutes, constraints);
        
      //  constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonQuickestRoutes, constraints);
        
        //add text area
        constraints.gridy = 5;
	    constraints.anchor = GridBagConstraints.CENTER;
	    newPanel.add(tf,constraints);
	        
        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Route Finder"));
         
        // add the panel to this frame
        add(newPanel);
        addButtonActions(buttonAllRoutes,buttonLeastTransferRoutes,buttonQuickestRoutes,newPanel,tf); 
        pack();
        setLocationRelativeTo(null);
    }
    private void addButtonActions(JButton buttonAllRoutes,JButton buttonLeastTransferRoutes,JButton buttonQuickestRoutes,JPanel newPanel,JTextArea tf) {
    	 buttonAllRoutes.addActionListener(new ActionListener() {
     	    @Override
     	    public void actionPerformed(ActionEvent e) {
     	    	tf.setText(wg.getAllRoutes((String)cb.getSelectedItem(),(String)cb.getSelectedItem(),(String)cb1.getSelectedItem(),0,0,0));  	
     	    }
     	});
    	 buttonLeastTransferRoutes.addActionListener(new ActionListener() {
      	    @Override
      	    public void actionPerformed(ActionEvent e) {
      	    	wg.getAllRoutes((String)cb.getSelectedItem(),(String)cb.getSelectedItem(),(String)cb1.getSelectedItem(),0,0,0);
      	    	tf.setText(wg.getLeastTransferRoutes());  	
      	    }
      	});
    	 buttonQuickestRoutes.addActionListener(new ActionListener() {
      	    @Override
      	    public void actionPerformed(ActionEvent e) {
      	    	wg.getAllRoutes((String)cb.getSelectedItem(),(String)cb.getSelectedItem(),(String)cb1.getSelectedItem(),0,0,0);
      	    	tf.setText(wg.getQuickestRoutes()); 
      	    	}
      	});
	}

	public static void main(String[] args) {
        // set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        wg.createGraph();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GraphGUI().setVisible(true);
            }
        });
        
    }
}