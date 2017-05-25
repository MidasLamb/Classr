package decoupling;

import logicalobjects.Method;

/**
 * A class that decouples Methods
 */
public class MethodDecoupler extends Decoupler {
	private final Method method;

	public MethodDecoupler(Method method) {
		this.method = method;
	}

	@Override
	public void decouple() {
		if (!isDecoupled())
			getMethod().getRealClass().deleteChild(getMethod());
		setDecoupled(true);
	}

	@Override
	public void recouple() {
		if (isDecoupled())
			getMethod().getRealClass().addMethod(getMethod());
		setDecoupled(false);
	}

	/**
	 * Returns the method that needs to be decoupled
	 * 
	 * @return the method that needs to be decoupled
	 */
	private Method getMethod() {
		return method;
	}

}
