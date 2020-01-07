package lab5_1;
public class CalcEngine
{
    // The calculator's state is maintained in three fields:
    //     buildingDisplayValue, haveLeftOperand, and lastOperator.
    
    // Are we already building a value in the display, or will the
    // next digit be the first of a new one?
    private boolean buildingDisplayValue;
    // Has a left operand already been entered (or calculated)?
    private boolean haveLeftOperand;
    // The most recent operator that was entered.
    private char lastOperator;

    // The current value (to be) shown in the display.
    private int displayValue;
    private String display="";
    // The value of an existing left operand.
    private int leftOperand;
    private int base=10;

    private String operator="";
    MySetAsList<String> s1,s2;
    
    
    /**
     * Create a CalcEngine.
     */
    public CalcEngine()
    {
        clear();
    }

    /**
     * @return The value that should currently be displayed
     * on the calculator display.
     */
    public float getDisplayValue()
    {
        return displayValue;
    }
    public String getDisplay()
    {
        return display;
    }

    /**
     * A number button was pressed.
     * Either start a new operand, or incorporate this number as
     * the least significant digit of an existing one.
     * @param number The number pressed on the calculator.
     */
    public void numberPressed(int number,int base)
    {
    	
        if(buildingDisplayValue) {
           
            displayValue = displayValue*base + number;
        }
        
        else {
            // Start building a new number.
            displayValue = number;
            buildingDisplayValue = true;
        }
    }

    /**
     * The 'plus' button was pressed. 
     */
    public void plus()
    {
        applyOperator('+');
    }
    public void union()
    {
        applyOperator('∪');
    }
    
    /**
     * The 'minus' button was pressed.
     */
    public void minus()
    {
        applyOperator('-');
    }
    
    public void multi()
    {
        applyOperator('*');
    }
    public void div()
    {
        applyOperator('/');
    }
    
    /**
     * The '=' button was pressed.
     */
    public void equals()
    {
    	//get two sets
    	getTwoSets();
    	//if valid
    	if(validExpression()) {
    		//apply operator
    		calculateResult();
    	}   	
    	    	
    }
    public void getTwoSets() {
    	int end=display.indexOf("}");
    	s1=getSet(display.substring(0,end+1));
    	s2=getSet(display.substring(end+2));
       	System.out.println("s1:"+s1.toString());
    	System.out.println("s2:"+s2.toString());
    }
    
    private MySetAsList<String> getSet(String s) {//{1,1}+{
		int i=s.indexOf("{");
		int j=s.indexOf("}");
		System.out.println("s in getset:"+s+","+i+","+j);
		 MySetAsList<String> set=new MySetAsList<String>(); 
		if(i>-1 && j>-1 && i<j ) {
			String str1 = s.substring(i+1,j);
			String[] elements = str1.split(",");
			for(String ele : elements) {
				set.add(ele);
			}
			System.out.println(set.toString());
			return set;
		}
		System.out.println("Sets should look like this {1,2,3...}, try again!");
		return null;
		
		
	}
    
    public boolean validExpression() {
    	//check if it's a valid expression
    	//1. if there are two sets
    	//2. if there's an operator
    	//3. if the operator is between }and{
    	return s1!=null && s2!= null && operator!="";
    }
    public void setOperator(String op) {
    	if(operator=="")operator=op;
    	else {
    		System.out.println("More than one operator Error!");
    	}
    }
    
    public void getNumberOfElement(){
    	//! display =""+MySetAsList.size();
    	MySetAsList s=getSet(display);
    	if(s==null) display+=" has no element";
    	else
    		display+=" has "+s.getSize()+" element(s)";
    }
    
    
    public void getPowerSet(){
    	//! display =""+MySetAsList.size();
    	MySetAsList s=getSet(display);
    	if(s==null) display+=" has no power sets";
    	else {
    		display+=" has "+Math.pow(2, s.getSize())+" power sets:";
    		display+=s.powerSet();
    	}
    }

	public int getBase() {
    	return base;
    }
    /**
     * The 'C' (clear) button was pressed.
     * Reset everything to a starting state.
     */
    public void clear()
    {
    	operator="";
        display="";
    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "Java Calculator";
    }

    /**
     * @return The author of this engine.
     */
    public String getAuthor()
    {
        return "David J. Barnes and Michael Kolling";
    }

    /**
     * @return The version number of this engine.
     */
    public String getVersion()
    {
       return "Version 1.0";
    }

    /**
     * Combine leftOperand, lastOperator, and the
     * current display value.
     * The result becomes both the leftOperand and
     * the new display value.
     */
    private void calculateResult()
    {
    	
        switch(operator.charAt(0)) {
            case '+':
                display+="="+ s1.union(s2).listElements();
                break;
            case '-':
            	display+="="+ s1.subtract(s2).listElements();
                break;
            case '∩':
            	display+="="+ s1.intersection(s2).listElements();
                break;
           
          default:
                keySequenceError();
                break;
        }
    }
    
    /**
     * Apply an operator.
     * @param operator The operator to apply.
     */
    private void applyOperator(char operator)
    {
        // If we are not in the process of building a new operand
        // then it is an error, unless we have just calculated a
        // result using '='.
        if(!buildingDisplayValue &&
                    !(haveLeftOperand && lastOperator == '?')) {
            keySequenceError();
            return;
        }

        if(lastOperator != '?') {
            // First apply the previous operator.
            calculateResult();
        }
        else {
            // The displayValue now becomes the left operand of this
            // new operator.
            haveLeftOperand = true;
            leftOperand = displayValue;
        }
        lastOperator = operator;
        buildingDisplayValue = false;
    }
    public void displayAppend(String command) {
		
		display +=command;
	}
    /**
     * Report an error in the sequence of keys that was pressed.
     */
    private void keySequenceError()
    {
        System.out.println("A key sequence error has occurred.");
        // Reset everything.
        clear();
    }

	

	
}

