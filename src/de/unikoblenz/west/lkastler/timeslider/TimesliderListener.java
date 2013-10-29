package de.unikoblenz.west.lkastler.timeslider;

/**
 * implemented by classes that listen on the TimesliderView.
 * @author lkastler
 *
 */
public interface TimesliderListener {

	/**
	 * triggered if the user does input identified as event on the TimesliderView identified as sender
	 * @param sender - triggering TimesliderView.
	 * @param event - triggered TimesliderEvent.
	 */
	public void notify(TimesliderView sender, TimesliderEvent event);
}
