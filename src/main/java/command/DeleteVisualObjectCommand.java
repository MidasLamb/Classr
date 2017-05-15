package command;

import java.util.ArrayList;

import visualobjects.VisualAssociation;
import visualobjects.VisualObject;

public class DeleteVisualObjectCommand extends Command {
	private final VisualObject visualObject;
	private ArrayList<VisualAssociation> deletedAssociations;
	
	public DeleteVisualObjectCommand(VisualObject object) {
		this.visualObject = object;
	}

	@Override
	void execute() {
		ArrayList<VisualAssociation> ass = new ArrayList<>();
		getVisualObject().getParent().removeChild(getVisualObject());
		getVisualObject().getParent().getChildren().stream()
		.filter(x -> x instanceof VisualAssociation).map(x -> (VisualAssociation) x)
		.filter(x -> x.getP1().equals(getVisualObject()) || x.getP2().equals(getVisualObject()))
		.forEach(x -> ass.add(x));
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
		getVisualObject().delete();
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
