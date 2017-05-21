package decoupling;

import visualobjects.EditableTextWrapper;

public class EditableTextWrapperDecoupler extends Decoupler {
	
	private final EditableTextWrapper<?> textWrapper;

	public EditableTextWrapperDecoupler(EditableTextWrapper<?> textWrapper) {
		this.textWrapper = textWrapper;
	}

	@Override
	public void decouple() {
		if(!isDecoupled()){
			setDecoupled(true);
			decoupleVisual();
			decoupleLogical();
		}
	}

	@Override
	public void recouple() {
		if(isDecoupled()){
			setDecoupled(false);
			recoupleVisual();
			recoupleLogical();
		}
		
	}
	
	private void decoupleVisual(){
		getTextWrapper().getParent().removeChild(getTextWrapper());
	}
	
	private void recoupleVisual(){
		getTextWrapper().getParent().addChild(getTextWrapper());
	}
	
	private void decoupleLogical(){
	}
	
	private void recoupleLogical(){
		
	}

	private EditableTextWrapper<?> getTextWrapper() {
		return textWrapper;
	}

}
