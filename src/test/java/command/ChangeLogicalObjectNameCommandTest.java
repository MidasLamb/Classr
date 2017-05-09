package command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logicalobjects.Association;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;
import logicalobjects.Method;

public class ChangeLogicalObjectNameCommandTest {

	@Test
	public void changeClassNameTest1() {
		Controller controller = new Controller();
		LogicalClass classe = new LogicalClass();
		Command c = new ChangeLogicalObjectNameCommand(classe, "Nieuwe_naam");
		controller.executeCommand(c);
		assertEquals("Nieuwe_naam", classe.getName());
	}
	
	@Test
	public void changeClassNameTest2() {
		Controller controller = new Controller();
		LogicalClass classe = new LogicalClass();
		String oldName = classe.getName();
		Command c = new ChangeLogicalObjectNameCommand(classe, "Nieuwe_naam");
		controller.executeCommand(c);
		controller.undo();
		assertEquals(oldName, classe.getName());
	}
	
	@Test
	public void changeClassNameTest3() {
		Controller controller = new Controller();
		LogicalClass classe = new LogicalClass();
		Command c = new ChangeLogicalObjectNameCommand(classe, "Nieuwe_naam");
		controller.executeCommand(c);
		controller.undo();
		controller.redo();
		assertEquals("Nieuwe_naam", classe.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void changeClassNameTest4() {
		Controller controller = new Controller();
		LogicalClass classe = new LogicalClass();
		Command c = new ChangeLogicalObjectNameCommand(classe, "nieuwe_naam");
		controller.executeCommand(c);
	}
	
	@Test
	public void changeClassNameTest5() {
		Controller controller = new Controller();
		LogicalClass classe1 = new LogicalClass();
		Command c1 = new ChangeLogicalObjectNameCommand(classe1, "Nieuwe_naam1");
		LogicalClass classe2 = new LogicalClass();
		Command c2 = new ChangeLogicalObjectNameCommand(classe2, "Nieuwe_naam2");
		controller.executeCommand(c1);
		controller.executeCommand(c2);
		assertEquals("Nieuwe_naam2", classe2.getName());
	}
	
	@Test
	public void changeMethodeNameTest1() {
		Controller controller = new Controller();
		LogicalClass classe1 = new LogicalClass();
		Method method = new Method(classe1);
		Command c = new ChangeLogicalObjectNameCommand(method, "nieuwe_naam");
		controller.executeCommand(c);
		assertEquals("nieuwe_naam", method.getName());
	}
	
	@Test
	public void changeMethodeNameTest2() {
		Controller controller = new Controller();
		LogicalClass classe1 = new LogicalClass();
		Method method = new Method(classe1);
		String oldName = method.getName();
		Command c = new ChangeLogicalObjectNameCommand(method, "nieuwe_naam");
		controller.executeCommand(c);
		controller.undo();
		assertEquals(oldName, method.getName());
	}
	
	@Test
	public void changeAttributeNameTest1() {
		Controller controller = new Controller();
		LogicalClass classe1 = new LogicalClass();
		Attribute attr = new Attribute(classe1);
		Command c = new ChangeLogicalObjectNameCommand(attr, "nieuwe_naam");
		controller.executeCommand(c);
		assertEquals("nieuwe_naam", attr.getName());
	}
	
	@Test
	public void changeAttributeNameTest2() {
		Controller controller = new Controller();
		LogicalClass classe1 = new LogicalClass();
		Attribute attr = new Attribute(classe1);
		String oldName = attr.getName();
		Command c = new ChangeLogicalObjectNameCommand(attr, "nieuwe_naam");
		controller.executeCommand(c);
		controller.undo();
		assertEquals(oldName, attr.getName());
	}
	
	@Test
	public void changeAssociationTest1() {
		Controller controller = new Controller();
		Association ass = new Association(new LogicalClass(), new LogicalClass());
		Command c = new ChangeLogicalObjectNameCommand(ass, "nieuwe_naam");
		controller.executeCommand(c);
		assertEquals("nieuwe_naam", ass.getName());
	}
	
	@Test
	public void changeAssociationTest2() {
		Controller controller = new Controller();
		Association ass = new Association(new LogicalClass(), new LogicalClass());
		String oldName = ass.getName();
		Command c = new ChangeLogicalObjectNameCommand(ass, "nieuwe_naam");
		controller.executeCommand(c);
		controller.undo();
		assertEquals(oldName, ass.getName());
	}

}
