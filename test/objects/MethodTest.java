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

}
