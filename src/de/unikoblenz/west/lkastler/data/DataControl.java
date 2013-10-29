package de.unikoblenz.west.lkastler.data;

import android.util.Log;

import de.unikoblenz.west.lkastler.MainActivity;
import de.unikoblenz.west.lkastler.timeslider.FastForwardTimesliderEvent;
import de.unikoblenz.west.lkastler.timeslider.LongPressTimesliderEvent;
import de.unikoblenz.west.lkastler.timeslider.ScrollTimesliderEvent;
import de.unikoblenz.west.lkastler.timeslider.TimeFrameChangeTimesliderEvent;
import de.unikoblenz.west.lkastler.timeslider.TimesliderEvent;
import de.unikoblenz.west.lkastler.timeslider.TimesliderListener;
import de.unikoblenz.west.lkastler.timeslider.TimesliderView;
import de.unikoblenz.west.lkastler.timeslider.UpTimesliderEvent;

/**
 * handles all input from the TimesliderView and adds changes to the DataModel.
 * 
 * @author lkastler
 */
public class DataControl implements TimesliderListener {

	public static final String TAG = "DATA CONTROL";

	DataModel model;

	ScrollTimesliderEvent buffer;
	MainActivity activity;

	/**
	 * constructor
	 * @param model - model for this DataControl 
	 */
	public DataControl(MainActivity activity, DataModel model) {
		this.activity = activity;
		this.model = model;
	}

	@Override
	public void notify(TimesliderView sender, TimesliderEvent event) {
		Log.i(TAG, event.getInfo());
		
		if (event instanceof UpTimesliderEvent) {
			if (buffer != null) {
				buffer = null;
			}
		} 
		else if (event instanceof ScrollTimesliderEvent) {
			ScrollTimesliderEvent scrollEvent = (ScrollTimesliderEvent)event;
			model.setPointInTime(model.getPointInTime() + (long)(scrollEvent.getDistance() * (model.getFrameSize() / scrollEvent.getSliderSize())));
		}
		else if (event instanceof FastForwardTimesliderEvent) {
			FastForwardTimesliderEvent ff = (FastForwardTimesliderEvent) event;

			model.setPointInTime(model.getPointInTime() 
					+ (ff.isForward() ? model.getFrameSize() : -model.getFrameSize()));
		}
		else if (event instanceof TimeFrameChangeTimesliderEvent) {
			TimeFrameChangeTimesliderEvent id = (TimeFrameChangeTimesliderEvent)event; 
			model.setCurrentTimeFrame(id.getTimeFrameId());
		}
		else if (event instanceof LongPressTimesliderEvent) {
			activity.showDialog();
		}
	}

}
