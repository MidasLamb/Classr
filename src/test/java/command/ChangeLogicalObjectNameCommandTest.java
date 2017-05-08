package command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logicalobjects.LogicalClass;

public class ChangeLogicalObjectNameCommandTest {

	@Test
	public void changeNameTest1() {
		Controller controller = new Controller();
		LogicalClass classe = new LogicalClass();
		Command c = new ChangeLogicalObjectNameCommand(classe, "Nieuwe_naam");
		controller.executeCommand(c);
		assertEquals("Nieuwe_naam", classe.getName());
	}
	
	@Test
	public void changeNameTest2() {
		Controller controller = new Controller();
		LogicalClass classe = new LogicalClass();
		String oldName = classe.getName();
		Command c = new ChangeLogicalObjectNameCommand(classe, "Nieuwe_naam");
		controller.executeCommand(c);
		controller.undo();
		assertEquals(oldName, classe.getName());
	}
	
	@Test
	public void changeNameTest3() {
		Controller controller = new Controller();
		LogicalClass classe = new LogicalClass();
		Command c = new ChangeLogicalObjectNameCommand(classe, "Nieuwe_naam");
		controller.executeCommand(c);
		controller.undo();
		controller.redo();
		assertEquals("Nieuwe_naam", classe.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void changeNameTest4() {
		Controller controller = new Controller();
		LogicalClass classe = new LogicalClass();
		Command c = new ChangeLogicalObjectNameCommand(classe, "nieuwe_naam");
		controller.executeCommand(c);
	}
	
	@Test
	public void changeNameTest5() {
		Controller controller = new Controller();
		LogicalClass classe1 = new LogicalClass();
		Command c1 = new ChangeLogicalObjectNameCommand(classe1, "Nieuwe_naam1");
		LogicalClass classe2 = new LogicalClass();
		Command c2 = new ChangeLogicalObjectNameCommand(classe2, "Nieuwe_naam2");
		controller.executeCommand(c1);
		controller.executeCommand(c2);
		assertEquals("Nieuwe_naam2", classe2.getName());
	}

}
