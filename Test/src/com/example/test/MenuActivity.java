package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;

public class MenuActivity extends Activity {
	
	final int MENU_COLOR_RED = 1;
	final int MENU_COLOR_GREEN = 2;
	final int MENU_COLOR_BLUE = 3;

	final int MENU_SIZE_22 = 4;
	final int MENU_SIZE_26 = 5;
	final int MENU_SIZE_30 = 6; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
		// Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    textView.setText(message);

	    // Set the text view as the activity layout
	    setContentView(textView);
	    
//		registerForContextMenu(tvColor);
//        registerForContextMenu(tvSize);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
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
	public void onCreateContextMenu(ContextMenu menu, View v,
    	    ContextMenuInfo menuInfo) {
    	
      switch (v.getId()) {
  	  case R.id.tvColor:
  	    menu.add(0, MENU_COLOR_RED, 0, "Red");
  	    menu.add(0, MENU_COLOR_GREEN, 0, "Green");
  	    menu.add(0, MENU_COLOR_BLUE, 0, "Blue");
  	    break;
  	  case R.id.tvSize:
  	    menu.add(0, MENU_SIZE_22, 0, "22");
  	    menu.add(0, MENU_SIZE_26, 0, "26");
  	    menu.add(0, MENU_SIZE_30, 0, "30");
  	    break;
  	  }
    	
    }
	 @Override
	    public boolean onContextItemSelected(MenuItem item) {
	      // TODO Auto-generated method stub
//	      switch (item.getItemId()) {
//	      // menu items for tvColor
//	      case MENU_COLOR_RED:
//	        tvColor.setTextColor(Color.RED);
//	        tvColor.setText("Text color = red");
//	        break;
//	      case MENU_COLOR_GREEN:
//	        tvColor.setTextColor(Color.GREEN);
//	        tvColor.setText("Text color = green");
//	        break;
//	      case MENU_COLOR_BLUE:
//	        tvColor.setTextColor(Color.BLUE);
//	        tvColor.setText("Text color = blue");
//	        break;
//	      // menu items for tvSize
//	      case MENU_SIZE_22:
//	        tvSize.setTextSize(22);
//	        tvSize.setText("Text size = 22");
//	        break;
//	      case MENU_SIZE_26:
//	        tvSize.setTextSize(26);
//	        tvSize.setText("Text size = 26");
//	        break;
//	      case MENU_SIZE_30:
//	        tvSize.setTextSize(30);
//	        tvSize.setText("Text size = 30");
//	        break;
//	      }
	      return super.onContextItemSelected(item);
	    }
}
