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

		Drag drag1 = new Drag(100, 555, 89, 505);
		container.onDragUpdate(drag1);
		container.onDragEnd(drag1);

		int width = 0;
		int vcCount = 0;

		for (VisualObject child : container.getChildren()) {
			if (child instanceof VisualClass) {
				vcCount++;
				if (width != 0) {
					assertNotEquals(width, child.getWidth());
				} else {
					width = child.getWidth();
				}
			}
		}
		
		assertEquals(2, vcCount);

	}

	public void correctPositiveWidthChangeTest(int w) {
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		DoubleClick click1 = new DoubleClick(100, 500);

		container.onDoubleClick(click1);

		VisualClass vc = null;
		for (VisualObject v : container.getChildren()) {
			if (v instanceof VisualClass) {
				vc = (VisualClass) v;
				break;
			}
		}
		int startWidth = vc.getWidth();
		
		//Drag on side but not on name, so move down
		Drag drag1 = new Drag(100, 551, 100 - w, 551);
		container.onDragUpdate(drag1);
		container.onDragEnd(drag1);


		assertEquals(startWidth + w, vc.getWidth());

		startWidth = vc.getWidth();
		Drag drag2 = new Drag(100 + CLASS_WIDTH, 502, 100 + CLASS_WIDTH + w, 502);
		container.onDragUpdate(drag2);
		container.onDragEnd(drag2);

		assertEquals(startWidth + w, vc.getWidth());

	}

	@Test
	public void testMultipleCorrectPositiveWidthChanges() {
		for (int i = 0; i < 20; i++) {
			correctPositiveWidthChangeTest(i);
		}

		for (int i = 20; i < 100; i += 9) {
			correctPositiveWidthChangeTest(i);
		}
	}

}
