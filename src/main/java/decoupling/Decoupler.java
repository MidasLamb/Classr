package decoupling;

/**
 * An abstract class to create classes which decouple an object from the other
 * objects, to pretend it is deleted
 */
public abstract class Decoupler {
	private boolean isDecoupled;

	/**
	 * A function to decouple the object
	 */
	public abstract void decouple();

	/**
	 * A function to couple the object again
	 */
	public abstract void recouple();

	/**
	 * @return true if the current item is decoupled, false otherwise
	 */
	boolean isDecoupled() {
		return isDecoupled;
	}

	/**
	 * Sets the current decoupled state
	 * 
	 * @param isDecoupled
	 *            the new decoupled state
	 */
	void setDecoupled(boolean isDecoupled) {
		this.isDecoupled = isDecoupled;
	}

}
