package de.unikoblenz.west.lkastler;

import com.example.test.R;

import de.unikoblenz.west.lkastler.data.DataControl;
import de.unikoblenz.west.lkastler.data.DataModel;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;

/**
 * main activity of this app
 * @author lkastler
 *
 */
public class MainActivity extends FragmentActivity {

	public static final String TAG = "MAIN";
	
	DataControl control;
	DataModel model;
	
	private TimesliderDialog dialog = new TimesliderDialog();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		model = new DataModel();
		control = new DataControl(this, model);
		
		TestFragment fragment = (TestFragment)getSupportFragmentManager().findFragmentById(R.id.testfragment);
		model.addListener(fragment);
		
		fragment.timeslider.addListener(control);
		model.addListener(fragment.timeslider);
		
		Log.i(TAG, "created");
	}

	public void showDialog() {
		dialog.show(getSupportFragmentManager(), "datetimepick");
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
