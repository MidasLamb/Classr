package visualobjects;

import static org.junit.Assert.*;
import static main.Constants.*;
import org.junit.Test;

import canvaswindow.CanvasWindow;
import canvaswindow.MyCanvasWindow;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;

public class ResizableAndMovableVisualObjectTest {

	@Test
	public void twoClassesChangeWidthTest() {
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(100, 100);
		DoubleClick click2 = new DoubleClick(100, 500);

		container.onDoubleClick(click1);
		container.onDoubleClick(click2);

		Drag drag1 = new Drag(99, 505, 89, 505);
		container.onDragUpdate(drag1);
		container.onDragEnd(drag1);

		int width = 0;

		for (VisualObject child : container.getChildren()) {
			if (child instanceof VisualClass) {
				if (width != 0) {
					assertNotEquals(width, child.getWidth());
				} else {
					width = child.getWidth();
				}
			}
		}

	}

	public void correctPositiveWidthChangeTest(int w) {
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(100, 500);

		container.onDoubleClick(click1);

		VisualClass vc = (VisualClass) container.getChildren().get(0);
		int startWidth = vc.getWidth();

		Drag drag1 = new Drag(99, 505, 99-w, 505);
		container.onDragUpdate(drag1);
		container.onDragEnd(drag1);

		// + 1 because the absolute change and we start dragging 1 pixel more to the left.
		assertEquals(startWidth + w + 1, vc.getWidth());
		
		startWidth = vc.getWidth();
		Drag drag2 = new Drag(100 + CLASS_WIDTH, 502, 100 + CLASS_WIDTH + w, 502);
		container.onDragUpdate(drag2);
		container.onDragEnd(drag2);

		assertEquals(startWidth + w, vc.getWidth());

	}

	@Test
	public void testMultipleCorrectPositiveWidthChanges() {
		for (int i = 0; i < 20; i++){
			correctPositiveWidthChangeTest(i);
		}
		
		for (int i = 20; i < 100; i+= 9){
			correctPositiveWidthChangeTest(i);
		}
	}

}
