package de.unikoblenz.west.lkastler;

import com.example.test.R;

import de.unikoblenz.west.lkastler.data.DataControl;
import de.unikoblenz.west.lkastler.data.DataModel;
import de.unikoblenz.west.lkastler.data.DataModelListener;
import de.unikoblenz.west.lkastler.data.TimeConverter;
import de.unikoblenz.west.lkastler.timeslider.LongPressTimesliderEvent;
import de.unikoblenz.west.lkastler.timeslider.TimesliderEvent;
import de.unikoblenz.west.lkastler.timeslider.TimesliderListener;
import de.unikoblenz.west.lkastler.timeslider.TimesliderView;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class TestFragment extends Fragment implements TimesliderListener, DataModelListener {

	public static final String TAG = "TIMESLIDER FRAGMENT";
	
	TimesliderView timeslider;
	
	TextView info;
	TextView fieldB;
	TextView fieldA;
	RelativeLayout layout;
	
	DataControl control = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.test,  container, false);

		timeslider =(TimesliderView)v.findViewById(R.id.TestView);
		timeslider.addListener(this);
		
		info = (TextView)v.findViewById(R.id.info);
		fieldA = (TextView)v.findViewById(R.id.fieldA);
		fieldB = (TextView)v.findViewById(R.id.fieldB);
		
		return v;
	}

	@Override
	public void notify(TimesliderView sender, TimesliderEvent event) {
		info.setText(event.getInfo());
		
		if(event instanceof LongPressTimesliderEvent) {
			FragmentManager fm = ((FragmentActivity)this.getActivity()).getSupportFragmentManager();
			TimesliderDialog d = new TimesliderDialog();
			d.show(fm, "dialog");
		}
	}

	@Override
	public void notify(DataModel sender) {
		fieldA.setText(TimeConverter.timeLongToString(sender.getPointInTime()) + " : " + TimeConverter.timeLongToString(sender.getPointInTime() + sender.getFrameSize()));
		fieldB.setText(TimeConverter.timeLongToString(sender.getFrameSize()));
	}
	
	
}
