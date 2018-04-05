package models;
/**
 * "Any object that wants to hear about changes in a shape model should implement ModelListener"
 */
public interface ModelListener {
	/**
	 * tells whena  model has been changed
	 * @param model the pointer to the model that has changed
	 */
	public void modelChanged(DShapeModel model);
}
