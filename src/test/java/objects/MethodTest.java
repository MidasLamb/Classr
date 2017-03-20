package objects;

import static org.junit.Assert.*;

import org.junit.Test;

public class MethodTest {

	@Test
	public void getRealClass() {
		RealClass realclass = new RealClass();
		Method methode = realclass.addMethod();
		assertEquals(realclass, methode.getRealClass());
	}
	
	@Test
	public void deleteClass() {
		RealClass realclass = new RealClass();
		Method methode = realclass.addMethod();
		realclass.deleteChild(methode);
		assertEquals(realclass, methode.getRealClass());
	}

}
