package de.unikoblenz.west.lkastler.data;

/**
 * handles changes of DataModel objects
 * @author lkastler
 *
 */
public interface DataModelListener {

	/**
	 * call function for handling DataModel changes of the specified sender
	 * @param sender - DataModel that called the method.
	 */
	public void notify(DataModel sender);
}
