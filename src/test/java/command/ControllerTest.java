package command;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
	
	@Test
	public void redoTest9(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(addB);
		controller.undo();
		controller.undo();
		controller.redo();
		controller.redo();
		controller.undo();
		assertEquals("a", text);	
	}
	
	@Test
	public void redoTest10(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(addB);
		controller.undo();
		controller.undo();
		controller.redo();
		controller.redo();
		controller.undo();
		controller.undo();
		assertEquals("", text);	
	}
	
	@Test
	public void undoCommandsTest1(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(addB);
		ArrayList<Command> unexecuteList = new ArrayList<>();
		unexecuteList.add(addA);
		controller.undoCommands(unexecuteList);
		assertEquals("b", text);
	}
	
	@Test
	public void undoCommandsTest2(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(addB);
		ArrayList<Command> unexecuteList = new ArrayList<>();
		unexecuteList.add(addA);
		unexecuteList.add(addB);
		controller.undoCommands(unexecuteList);
		assertEquals("", text);
	}
	
	@Test
	public void undoCommandsTest3(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(addB);
		ArrayList<Command> unexecuteList = new ArrayList<>();
		controller.undoCommands(unexecuteList);
		assertEquals("ab", text);
	}
	
	@Test
	public void undoCommandsTest4(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(new addACommand());
		controller.executeCommand(addB);
		ArrayList<Command> unexecuteList = new ArrayList<>();
		unexecuteList.add(addA);
		controller.undoCommands(unexecuteList);
		assertEquals("ab", text);
	}
	
	@Test
	public void undoCommandsTest5(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(new addACommand());
		controller.executeCommand(addB);
		ArrayList<Command> unexecuteList = new ArrayList<>();
		unexecuteList.add(addA);
		unexecuteList.add(addA);
		controller.undoCommands(unexecuteList);
		assertEquals("ab", text);
	}
	
	@Test
	public void undoCommandsTest6(){
		Controller controller = new Controller();
		Command addA = new addACommand();
		Command addB = new addBCommand();
		controller.executeCommand(addA);
		controller.executeCommand(new addACommand());
		controller.executeCommand(addB);
		ArrayList<Command> unexecuteList = new ArrayList<>();
		unexecuteList.add(new addACommand());
		controller.undoCommands(unexecuteList);
		assertEquals("aab", text);
	}

	private class addACommand extends Command{

		@Override
		void execute() {
			text += "a";
			
		}

		@Override
		void unexecute() {
			int index = text.lastIndexOf("a");
			if(index < 0) return;
			StringBuilder builder = new StringBuilder(text);
			builder.deleteCharAt(index);			
			text = builder.toString();
		}

		@Override
		void cleanup() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class addBCommand extends Command{

		@Override
		void execute() {
			text += "b";
			
		}

		@Override
		void unexecute() {
			int index = text.lastIndexOf("b");
			if(index < 0) return;
			StringBuilder builder = new StringBuilder(text);
			builder.deleteCharAt(index);			
			text = builder.toString();
		}

		@Override
		void cleanup() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Before
	public void setUp() throws Exception {
		text = "";
	}
	
	private String text = "";

}
