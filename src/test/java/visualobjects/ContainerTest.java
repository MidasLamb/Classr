package visualobjects;

import static gui.inputHandlers.keys.FunctionKey.FunctionKeyType.DELETE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import canvaswindow.MyCanvasWindow;
import command.AddClassCommand;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.FunctionKey;
import main.Constants;
import command.Command;
import command.Controller;

public class ContainerTest {

	@Test
	public void isInTest1() {
		Container container = new Container(0, 0, 100, 100, new MyCanvasWindow("test"));
		assertTrue(container.isIn(0, 0));
	}
	
	@Test
	public void isInTest2() {
		Container container = new Container(0, 0, 100, 100, new MyCanvasWindow("test"));
		assertTrue(container.isIn(container.getWidth(), container.getHeight()));
	}
	
	@Test
	public void isInTest3() {
		Container container = new Container(10, 10, 100, 100, new MyCanvasWindow("test"));
		assertTrue(container.isIn(container.getWidth()-10, container.getHeight()-10));
	}
	
	@Test
	public void isInTest4() {
		Container container = new Container(0, 0, 100, 200, new MyCanvasWindow("test"));
		assertTrue(container.isIn(70, 150));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deleteTest1() {
		Container container = new Container(10, 10, 100, 100, new MyCanvasWindow("test"));
		container.removeChild(container);
	}
	
	@Test
	public void deleteTest2() {
		Container container = new Container(10, 10, 100, 100, new MyCanvasWindow("test"));
		new VisualClass(0, 0, 0, container);
		VisualClass klasse2 = new VisualClass(1, 1, 1, container);
		container.removeChild(klasse2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deleteTest3() {
		Container container = new Container(10, 10, 100, 100, new MyCanvasWindow("test"));
		VisualClass klasse1 = new VisualClass(0, 0, 0, container);
		container.removeChild(klasse1);
		container.removeChild(klasse1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deleteTest4() {
		Container container = new Container(10, 10, 100, 100, new MyCanvasWindow("test"));
		VisualClass klasse1 = new VisualClass(0, 0, 0, container);
		new VisualClass(1, 1, 1, container);
		container.removeChild(klasse1);
		container.removeChild(klasse1);
	}
	
	@Test
	public void deleteTest5() {
		Container container = new Container(10, 10, 100, 100, new MyCanvasWindow("test"));
		VisualClass klasse1 = new VisualClass(0, 0, 0, container);
		VisualClass klasse2 = new VisualClass(1, 1, 1, container);
		container.removeChild(klasse1);
		container.removeChild(klasse2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deleteTest6() {
		Container container = new Container(10, 10, 100, 100, new MyCanvasWindow("test"));
		VisualClass klasse = new VisualClass(0, 0, 0, container);
		klasse.delete();
		container.removeChild(klasse);
	}
	
	@Test
	public void getParentTest() {
		Container container = new Container(10, 10, 100, 100, new MyCanvasWindow("test"));
		assertEquals(null,container.getParent());
	}
	
	@Test
	public void constructorTest1() {
		Container container = new Container(0, 0, 100, 200, new MyCanvasWindow("test"));
		assertEquals(200, container.getHeight());
	}
	
	@Test
	public void constructorTest2() {
		Container container = new Container(0, 0, 100, 200, new MyCanvasWindow("test"));
		assertEquals(100, container.getWidth());
	}
	
	@Test
	public void selectedTest1() {
		Container container = new Container(0, 0, 100, 200, new MyCanvasWindow("test"));
		SingleClick click = new SingleClick(10, 20);
		container.onClick(click);
		assertFalse(container.isSelected());
	}
	
	@Test
	public void selectedTest2() {
		Container container = new Container(0, 0, 100, 200, new MyCanvasWindow("test"));
		assertFalse(container.isSelected());
	}
	
	@Test
	public void selectedTest3() {
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick doubleClick = new DoubleClick(10, 20);
		container.onDoubleClick(doubleClick);
		assertFalse(container.isSelected());
	}
	
	@Test
	public void createClassTest(){
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(138,101);
		container.onDoubleClick(click1);
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualClass){
				count++;
			}
		}
		assertEquals(1, count);
	}
	
	@Test
	public void createClassTest2(){
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(138,101);
		container.onDoubleClick(click1);
		int x = 0, y = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualClass){
				x = ((VisualClass) child).getX();
				y = ((VisualClass) child).getY();
			}
		}
		assertTrue(x == 138 && y == 101);
	}
	
	@Test
	public void notCreateDoubleClassTest(){
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(138,101);
		SingleClick click2 = new SingleClick(1, 1);
		container.onDoubleClick(click1);
		container.onClick(click2);
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualClass){
				count++;
			}
		}
		assertEquals(1, count);
	}
	
	@Test
	public void clickOnClassTest1(){
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(138,101);
		SingleClick click2 = new SingleClick(1,1);
		SingleClick click3 = new SingleClick(138,101);
		container.onDoubleClick(click1);
		container.onClick(click2);
		container.onClick(click3);
		VisualClass klasse = null;
		VisualObject selected = container.getSelected();
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualClass){
				klasse = (VisualClass) child;
			}
		}
		assertTrue(klasse.equals(selected));
	}
	
	@Test
	public void clickOnClassTest2(){
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(138,101);
		SingleClick click2 = new SingleClick(1,1);
		SingleClick click3 = new SingleClick(138,101);
		container.onDoubleClick(click1);
		container.onClick(click2);
		container.onClick(click3);
		VisualClass klasse = null;
		VisualObject selectedClass = container.getSelected();
		container.onClick(click3);
		VisualObject text = container.getSelected();
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualClass){
				klasse = (VisualClass) child;
			}
		}
		assertTrue(klasse.equals(selectedClass));
		for (VisualObject v: selectedClass.getChildren()){
			if (v instanceof PaddingBox){
				assertTrue(v.getChildren().contains(text));
			}
		}
	}
	
	@Test
	public void createClassesTest(){
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(138,101);
		DoubleClick click2 = new DoubleClick(226,299);
		DoubleClick click3 = new DoubleClick(700,400);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onDoubleClick(click3);
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualClass){
				count++;
			}
		}
		assertEquals(3, count);
	}
	
	//@Test
	public void createAttributeTest(){
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		DoubleClick click1 = new DoubleClick(182,162);
		DoubleClick click2 = new DoubleClick(210,196);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof PaddingBox){
				count++;
			}
		}
		assertEquals(1, count);
	}
	
	//@Test
	public void createMethodeTest(){
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		DoubleClick click1 = new DoubleClick(149,118);
		DoubleClick click2 = new DoubleClick(201,164);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof PaddingBox){
				count++;
			}
		}
		assertEquals(1, count);
	}
	
	@Test
	public void createAssTest(){
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(138,101);
		DoubleClick click2 = new DoubleClick(226,299);
		Drag drag = new Drag(137,135, 225,333);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onDragEnd(drag);
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualAssociation){
				count++;
			}
		}
		assertEquals(1,count);
	}
	
	@Test
	public void createTwoAssTest(){
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(138,101);
		DoubleClick click2 = new DoubleClick(226,299);
		DoubleClick click3 = new DoubleClick(500,500);
		Drag drag1 = new Drag(137,135, 225,333);
		Drag drag2 = new Drag(500,534, 225,333);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onDoubleClick(click3);
		container.onDragEnd(drag1);
		container.onDragEnd(drag2);
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualAssociation){
				count++;
			}
		}
		assertEquals(2,count);
	}
	
	@Test
	public void selectContainerTest(){
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		SingleClick click = new SingleClick(1,1);
		container.onClick(click);
		VisualObject selected = container.getSelected();
		assertEquals(null, selected);
	}
	
	@Test
	public void selectAttributeTest(){
		MyCanvasWindow window = new MyCanvasWindow("Test");
		Container container = new Container(0, 0, 1000, 1000, window);
		DoubleClick click1 = new DoubleClick(182,162);
		DoubleClick click2 = new DoubleClick(210,196);
		SingleClick click3 = new SingleClick(1,1);
		SingleClick click4 = new SingleClick(210,196);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onClick(click3);
		container.onClick(click4);
		assertTrue(container.getSelected() instanceof TextWrapper);
	}
	
	@Test
	public void selectMethodeTest(){
		MyCanvasWindow window = new MyCanvasWindow("Test");
		Container container = new Container(0, 0, 1000, 1000, window);
		DoubleClick click1 = new DoubleClick(149,118);
		DoubleClick click2 = new DoubleClick(201,164);
		SingleClick click3 = new SingleClick(1,1);
		SingleClick click4 = new SingleClick(201,164);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onClick(click3);
		container.onClick(click4);
		assertTrue(container.getSelected() instanceof TextWrapper);
	}
	
	@Test
	public void selectAssTest(){
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(103,108);
		DoubleClick click2 = new DoubleClick(326,369);
		Drag drag = new Drag(104,142,325,401);
		SingleClick click3 = new SingleClick(1,1);
		SingleClick click4 = new SingleClick(243,256);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onDragEnd(drag);
		container.onClick(click3);
		container.onClick(click4);
		assertTrue(container.getSelected() instanceof VisualAssociation);
	}
	
	@Test
	public void deleteClassTest(){
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(303,326);
		SingleClick click2 = new SingleClick(389,459);
		SingleClick click3 = new SingleClick(329,343);
		container.onDoubleClick(click1);
		//klik op andere locatie
		container.onClick(click2);
		//klik op klasse
		container.onClick(click3);
		container.handleFunctionKey(getDeleteKey());
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualClass){
				count++;
			}
		}
		assertEquals(0,count);
	}
	
	@Test
	public void deleteAttributeTest(){
		MyCanvasWindow window = new MyCanvasWindow("Test");
		Container container = new Container(0, 0, 1000, 1000, window);
		DoubleClick click1 = new DoubleClick(182,162);
		DoubleClick click2 = new DoubleClick(210,196);
		SingleClick click3 = new SingleClick(1,1);
		SingleClick click4 = new SingleClick(210,196);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onClick(click3);
		container.onClick(click4);
		container.handleFunctionKey(getDeleteKey());
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof PaddingBox){
				count++;
			}
		}		
		assertEquals(0, count);
	}
	
	@Test
	public void deleteMethodeTest(){
		MyCanvasWindow window = new MyCanvasWindow("Test");
		Container container = new Container(0, 0, 1000, 1000, window);
		DoubleClick click1 = new DoubleClick(149,118);
		DoubleClick click2 = new DoubleClick(201,164);
		SingleClick click3 = new SingleClick(1,1);
		SingleClick click4 = new SingleClick(201,164);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onClick(click3);
		container.onClick(click4);
		container.handleFunctionKey(getDeleteKey());
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof PaddingBox){
				count++;
			}
		}		
		assertEquals(0, count);
	}
	
	@Test
	public void deleteAssTest1(){
		//verwijderen door klikken op ass zelf
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(103,108);
		DoubleClick click2 = new DoubleClick(326,369);
		Drag drag = new Drag(104,142,325,401);
		SingleClick click3 = new SingleClick(1,1);
		SingleClick click4 = new SingleClick(243,256);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onDragEnd(drag);
		container.onClick(click3);
		container.onClick(click4);
		container.handleFunctionKey(getDeleteKey());
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualAssociation){
				count++;
			}
		}
		assertEquals(0, count);
	}
	
	@Test
	public void deleteAssTest2(){
		//verwijderen door klikken op p1
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(103,108);
		DoubleClick click2 = new DoubleClick(326,369);
		Drag drag = new Drag(104,142,325,401);
		SingleClick click3 = new SingleClick(1,1);
		SingleClick click4 = new SingleClick(103,108);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onDragEnd(drag);
		container.onClick(click3);
		container.onClick(click4);
		container.handleFunctionKey(getDeleteKey());
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualAssociation){
				count++;
			}
		}
		assertEquals(0, count);
	}
	
	@Test
	public void deleteAssTest3(){
		//verwijderen door klikken op p2
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(103,108);
		DoubleClick click2 = new DoubleClick(326,369);
		Drag drag = new Drag(104,142,325,401);
		SingleClick click3 = new SingleClick(1,1);
		SingleClick click4 = new SingleClick(326,369);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onDragEnd(drag);
		container.onClick(click3);
		container.onClick(click4);
		container.handleFunctionKey(getDeleteKey());
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualAssociation){
				count++;
			}
		}
		assertEquals(0, count);
	}
	
	@Test
	public void deleteTwoAssTest(){
		//delete the ass by deleting the middle class that has a connection to both
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(129,94);
		DoubleClick click2 = new DoubleClick(198,285);
		DoubleClick click3 = new DoubleClick(462,259);
		SingleClick click4 = new SingleClick(217,300); //Really close so it works with short names
		Drag drag1 = new Drag(129,125, 198,318);
		Drag drag2 = new Drag(198,318, 461,292);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onDoubleClick(click3);
		container.onDragEnd(drag1);
		container.onDragEnd(drag2);
		container.onClick(click4);
		container.handleFunctionKey(getDeleteKey());
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualAssociation){
				count++;
			}
		}
		assertEquals(0,count);
	}
	
	@Test
	public void createNewClassTest1(){
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		VisualClass klasse = container.createNewClass();
		assertEquals(10, klasse.getX());
	}
	
	@Test
	public void createNewClassTest2(){
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		VisualClass klasse = container.createNewClass();
		assertEquals(10, klasse.getY());
	}
	
	@Test
	public void createNewClassTest3(){
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		container.createNewClass();
		VisualClass klasse = container.createNewClass();
		assertEquals(10+Constants.CLASS_WIDTH+1, klasse.getX());
	}
	
	@Test
	public void createNewClassTest4(){
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		container.createNewClass();
		VisualClass klasse = container.createNewClass();
		assertEquals(10, klasse.getY());
	}
	
	@Test
	public void AddClassCommandTest1(){
		Controller controller = new Controller();
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		Command c1 = new AddClassCommand(container);
		controller.executeCommand(c1);
		assertEquals(1, container.getChildren().size());
	}
	
	@Test
	public void AddClassCommandTest2(){
		Controller controller = new Controller();
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		Command c1 = new AddClassCommand(container);
		controller.executeCommand(c1);
		controller.undo();
		assertEquals(0, container.getChildren().size());
	}
	
	@Test
	public void AddClassCommandTest3(){
		Controller controller = new Controller();
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		Command c1 = new AddClassCommand(container);
		controller.executeCommand(c1);
		controller.undo();
		controller.redo();
		assertEquals(1, container.getChildren().size());
	}
	
	@Test
	public void AddClassCommandTest4(){
		Controller controller = new Controller();
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		Command c1 = new AddClassCommand(container);
		Command c2 = new AddClassCommand(container);
		controller.executeCommand(c1);
		controller.executeCommand(c2);
		assertEquals(2, container.getChildren().size());
	}
	
	@Test
	public void AddClassCommandTest5(){
		Controller controller = new Controller();
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		Command c1 = new AddClassCommand(container);
		Command c2 = new AddClassCommand(container);
		controller.executeCommand(c1);
		controller.executeCommand(c2);
		controller.undo();
		assertEquals(1, container.getChildren().size());
	}
	
	@Test
	public void AddClassCommandTest6(){
		Controller controller = new Controller();
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		Command c1 = new AddClassCommand(container);
		Command c2 = new AddClassCommand(container);
		controller.executeCommand(c1);
		controller.undo();
		controller.executeCommand(c2);
		controller.undo();
		assertEquals(0, container.getChildren().size());
	}
	
	@Test
	public void AddClassCommandTest7(){
		Controller controller = new Controller();
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		Command c1 = new AddClassCommand(container);
		Command c2 = new AddClassCommand(container);
		controller.executeCommand(c1);
		controller.executeCommand(c2);
		controller.undo();
		controller.undo();
		assertEquals(0, container.getChildren().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void AddClassCommandTest8(){
		Controller controller = new Controller();
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		Command c1 = new AddClassCommand(container);
		controller.executeCommand(c1);
		controller.executeCommand(c1);
	}
	
	private static FunctionKey getDeleteKey(){
		return new FunctionKey(DELETE);
	}
	
}
