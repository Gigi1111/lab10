package lab5_1;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;

import org.junit.jupiter.api.Test;


class UserInterfaceSetTest {
	private int id= 0;
	private ActionEvent event;
	private CalcEngine engine = new CalcEngine();
	private UserInterfaceSet uihex = new UserInterfaceSet(engine);
	private String str;
	
	@Test
	 public void testUnion() {
		String[] testCases = {"{1,4,3,2}+{5,2,6,1}="
				};
		String[] answer = {"{1,4,3,2}+{5,2,6,1}={1,4,3,2,5,6}"
				};
		for(int j=0;j<testCases.length;j++) {
			str=testCases[j];
			for(int i=0;i<str.length();i++) {
				event = new ActionEvent(this, id++, ""+str.charAt(i));
				uihex.actionPerformed(event);
			}
			System.out.println("1:"+engine.getDisplay());
			assertEquals(answer[j],engine.getDisplay());
			//clear the string after each calculation
			event = new ActionEvent(this, id++, "Cl");
			uihex.actionPerformed(event);
		}	
	}
	@Test
	 public void testMinus() {
		String[] testCases = {"{1,4,3,2}-{5,2,6,1}="
				};
		String[] answer = {"{1,4,3,2}-{5,2,6,1}={4,3}"
				};
		for(int j=0;j<testCases.length;j++) {
			str=testCases[j];
			for(int i=0;i<str.length();i++) {
				event = new ActionEvent(this, id++, ""+str.charAt(i));
				uihex.actionPerformed(event);
			}
			System.out.println(engine.getDisplay());
			assertEquals(answer[j],engine.getDisplay());
			//clear the string after each calculation
			event = new ActionEvent(this, id++, "Cl");
			uihex.actionPerformed(event);
		}	
	}
	@Test
	 public void testIntersect() {
		String[] testCases = {"{1,4,3,2}∩{5,2,6,1}="
				};
		String[] answer = {"{1,4,3,2}∩{5,2,6,1}={1,2}"
				};
		for(int j=0;j<testCases.length;j++) {
			str=testCases[j];
			for(int i=0;i<str.length();i++) {
				event = new ActionEvent(this, id++, ""+str.charAt(i));
				uihex.actionPerformed(event);
			}
			System.out.println(engine.getDisplay());
			assertEquals(answer[j],engine.getDisplay());
			//clear the string after each calculation
			event = new ActionEvent(this, id++, "Cl");
			uihex.actionPerformed(event);
		}	
	}


}