package command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest {
	
	@Test
	public void executeCommandTest1(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		controller.executeCommand(addA);
		assertEquals("a", text);		
	}
	
	@Test
	public void executeCommandTest2(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(addB);
		assertEquals("ab", text);		
	}
	
	@Test
	public void undoTest1(){
		Controller controller = new Controller();
		controller.undo();
		assertEquals("", text);
	}
	
	@Test
	public void undoTest2(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		controller.executeCommand(addA);
		controller.undo();
		assertEquals("", text);		
	}
	
	@Test
	public void undoTest3(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(addB);
		controller.undo();
		assertEquals("a", text);		
	}
	
	@Test
	public void undoTest4(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.undo();
		controller.executeCommand(addB);
		assertEquals("b", text);		
	}
	
	@Test
	public void undoTest5(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(addB);
		controller.undo();
		controller.undo();
		assertEquals("", text);		
	}
	
	@Test
	public void redoTest1(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		controller.executeCommand(addA);
		controller.redo();
		assertEquals("a", text);	
	}
	
	@Test
	public void redoTest2(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		controller.executeCommand(addA);
		controller.undo();
		controller.redo();
		assertEquals("a", text);	
	}
	
	@Test
	public void redoTest3(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(addB);
		controller.undo();
		controller.redo();
		assertEquals("ab", text);	
	}
	
	@Test
	public void redoTest4(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(addB);
		controller.undo();
		controller.undo();
		controller.redo();
		assertEquals("a", text);	
	}
	
	@Test
	public void redoTest5(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.undo();
		controller.executeCommand(addB);
		controller.redo();
		assertEquals("b", text);	
	}
	
	@Test
	public void redoTest6(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.undo();
		controller.executeCommand(addB);
		controller.redo();
		assertEquals("b", text);	
	}
	
	@Test
	public void redoTest7(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.undo();
		controller.executeCommand(addB);
		controller.redo();
		controller.redo();
		assertEquals("b", text);	
	}
	
	@Test
	public void redoTest8(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(addB);
		controller.undo();
		controller.undo();
		controller.redo();
		controller.redo();
		assertEquals("ab", text);	
	}

	private class addACommand extends Command{

		@Override
		void execute() {
			text += "a";
			
		}

		@Override
		void unexecute() {
			text =  text.substring(0, text.length()-1);
		}
		
	}
	
	private class addBCommand extends Command{

		@Override
		void execute() {
			text += "b";
			
		}

		@Override
		void unexecute() {
			text =  text.substring(0, text.length()-1);
		}
		
	}
	
	@Before
	public void setUp() throws Exception {
		text = "";
	}
	
	private String text = "";

}
