package command;

import java.util.ArrayList;

import visualobjects.VisualAssociation;
import visualobjects.VisualObject;

public class DeleteVisualObjectCommand extends Command {
	private final VisualObject<?> visualObject;
	private ArrayList<VisualAssociation> deletedAssociations;
	
	public DeleteVisualObjectCommand(VisualObject<?> object) {
		this.visualObject = object;
	}

	@Override
	void execute() {
		ArrayList<VisualAssociation> ass = new ArrayList<>();
		getVisualObject().getParent().removeChild(getVisualObject());
		ArrayList<VisualObject<?>> children = getVisualObject().getParent().getChildren();
		for(VisualObject<?> child : children){
			if(child instanceof VisualAssociation){
				VisualAssociation vass = (VisualAssociation) child;
				if(vass.getP1().equals(getVisualObject()) || vass.getP2().equals(getVisualObject()))
					ass.add(vass);
			}
		}
		ass.forEach(x -> getVisualObject().getParent().removeChild(x));
		setDeletedAssociations(ass);
	}

	@Override
	void unexecute() {
		getVisualObject().getParent().addChild(getVisualObject());
		getDeletedAssociations().forEach(x -> getVisualObject().getParent().addChild(x));
		setDeletedAssociations(new ArrayList<>());
	}

	@Override
	void cleanup() {
		
	}

	/**
	 * Returns the visual object that will be deleted
	 * @return the visual object that will be deleted
	 */
	private VisualObject getVisualObject() {
		return visualObject;
	}

	/**
	 * Get the associations that need to be deleted
	 * @return the associations that need to be deleted
	 */
	private ArrayList<VisualAssociation> getDeletedAssociations() {
		return deletedAssociations;
	}

	
	/**
	 * Set the associations that need to be deleted
	 * @param	deletedAssociations
	 * 			the associations that need to be deleted
	 */
	private void setDeletedAssociations(ArrayList<VisualAssociation> deletedAssociations) {
		this.deletedAssociations = deletedAssociations;
	}

}
