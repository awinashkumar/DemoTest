package com.example.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTime extends Activity implements OnClickListener {
	EditText etName;
	  Button btnOK;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time);
		
		 etName = (EditText) findViewById(R.id.etName);
		 btnOK = (Button) findViewById(R.id.btnOK);
		 btnOK.setOnClickListener(this);
		
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//	    String time = sdf.format(new Date(System.currentTimeMillis()));
//	    
//	    TextView tvTime = (TextView) findViewById(R.id.tvTime);
//	    tvTime.setText(time);
	}

	public static void testPerk() {
		System.out.println("testning");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_time, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onClick(View view) {
		Intent intent = new Intent();
		intent.putExtra("name", etName.getText().toString());
		setResult(RESULT_OK, intent);
		finish();
	}
}


