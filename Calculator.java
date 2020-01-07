package lab5_1;

public class Calculator
{
	
    private CalcEngine engine;
 
    private UserInterface gui;
    /**
     * Create a new calculator and show it.
     */
    public Calculator()
    {
        engine = new CalcEngine();
        gui = new UserInterfaceSet(engine);
    }

    /**
     * In case the window was closed, show it again.
     */
    
    
    public static void main(String[]args) {
    	Calculator calc = new Calculator();  
    }
    
    public void show()
    {
        gui.setVisible(true);
    }
}



