package decoupling;

import logicalobjects.Attribute;

public class AttributeDecoupler extends Decoupler {
	private final Attribute attribute;

	public AttributeDecoupler(Attribute attribute) {
		this.attribute = attribute;
	}

	@Override
	public void decouple() {
		if(!isDecoupled())
			getAttribute().getRealClass().deleteChild(getAttribute());
		setDecoupled(true);
	}

	@Override
	public void recouple() {
		if(isDecoupled())
			getAttribute().getRealClass().addAttribute(getAttribute());
		setDecoupled(false);
	}

	/**
	 * Returns the attribute that needs to be decoupled
	 * @return the attribute that needs to be decoupled
	 */
	private Attribute getAttribute() {
		return attribute;
	}

}
