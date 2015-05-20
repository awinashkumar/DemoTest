package com.example.test;



import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {  
	
	// Within which the entire activity is enclosed
		private DrawerLayout mDrawerLayout;

		// ListView represents Navigation Drawer
		private ListView mDrawerList;

		// ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar
		private ActionBarDrawerToggle mDrawerToggle;

		// Title of the action bar
		private String mTitle = "";
		TextView tvName;
		
		EditText _monthAndYear;
		
		 private int years, changedYear, maxYear, minYear;
		    private int month, maxMonth, minMonth;
			    private int day, maxDay, minDay;
			    static final int DATE_PICKER_ID = 1111; 
			    
			    Calendar maxDate, minDate;
	public final static String EXTRA_MESSAGE = "SUCCESS";
	
	MyTooltipWindow tipWindow;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);      
        
        tipWindow = new MyTooltipWindow(MainActivity.this);
      
        final Calendar c = Calendar.getInstance();
        years  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);   
        changedYear = years - 5 ; 
        
        maxYear = years ;
        maxMonth = month;
        maxDay = day;
        
        minYear = years - 5 ;
        minMonth = month;
        minDay = day;
       
        maxDate = Calendar.getInstance();
       // maxDate.Set(DateTime.Now.Year, DateTime.Now.Month - 1, DateTime.Now.Day);
        maxDate.set(maxDate.get(Calendar.YEAR), maxDate.get(Calendar.MONTH), maxDate.get(Calendar.DAY_OF_MONTH));
        
        minDate = Calendar.getInstance();
        minDate.set((maxDate.get(Calendar.YEAR)) - 5 , maxDate.get(Calendar.MONTH), maxDate.get(Calendar.DAY_OF_MONTH));
        
    	//_monthAndYear = (EditText) findViewById(R.id.et_monthAndYear);
        
//    	_monthAndYear.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				//createDialogWithoutDateField().show();
//			}
//		});
    	
//    	_monthAndYear.setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				InputMethodManager imm = (InputMethodManager) MainActivity.this
//						.getSystemService(Context.INPUT_METHOD_SERVICE);
//				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//				//showDialog(DATE_PICKER_ID);
//				createDialogWithoutDateField().show();
//				System.out.println("_monthAndYear 111111");
//				return true;
//			}
//		});
     

     		
        //mTitle = "PERK MENU";
        mTitle = (String) getTitle();	
		//getActionBar().setTitle(mTitle);

        // Getting reference to the DrawerLayout
 		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_p);
 		
 		
 		mDrawerList = (ListView) findViewById(R.id.drawer_list);
 		
 		// Getting reference to the ActionBarDrawerToggle
 		mDrawerToggle = new ActionBarDrawerToggle(	this, 
 													mDrawerLayout, 
 													R.drawable.ic_drawer, 
 													R.string.drawer_open,
 													R.string.drawer_close){
 			
 			/** Called when drawer is closed */
             public void onDrawerClosed(View view) {
             	getActionBar().setTitle(mTitle);
             	invalidateOptionsMenu();
                 
             }

             /** Called when a drawer is opened */
             public void onDrawerOpened(View drawerView) {
                 getActionBar().setTitle("Select Menu");
                 invalidateOptionsMenu();
             }
 			
 		};
 		
 		// Setting DrawerToggle on DrawerLayout
 		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// Creating an ArrayAdapter to add items to the listview mDrawerList
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							getBaseContext(), 
							R.layout.drawer_list_item  , 
							getResources().getStringArray(R.array.PerkMenu) 
						);

		// Setting the adapter on mDrawerList
		mDrawerList.setAdapter(adapter);

		// Enabling Home button
		getActionBar().setHomeButtonEnabled(true);

		// Enabling Up navigation
		getActionBar().setDisplayHomeAsUpEnabled(true); 
		
		Button btnTime = (Button) findViewById(R.id.btnTime);
        Button btnDate = (Button) findViewById(R.id.btnDate);
        tvName = (TextView) findViewById(R.id.nameLable);
        btnTime.setOnClickListener(this);
        btnDate.setOnClickListener(this);
        
             
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_PICKER_ID:
             
            // open datepicker dialog. 
            // set date picker for current date 
            // add pickerListener listner to date picker
          //  return new DatePickerDialog(this, pickerListener, year, month,day);
            DatePickerDialog datePickerDialog = this.createDialogWithoutDateField();
            return datePickerDialog;
        }
        return null;
    }
    
    private DatePickerDialog createDialogWithoutDateField(){

	    //DatePickerDialog dpd1 = new DatePickerDialog(this, null,years, month, day);
	    DatePickerDialog dpd = new DatePickerDialog(this, datePickerListener,
	    		years, month,day );
	    try{
		    java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
		    for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) { 
		        if (datePickerDialogField.getName().equals("mDatePicker")) {
		            datePickerDialogField.setAccessible(true);
		            DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
		            java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
		            for (java.lang.reflect.Field datePickerField : datePickerFields) {
		                Log.i("test", datePickerField.getName());
		               if ("mDaySpinner".equals(datePickerField.getName())) {
		                  datePickerField.setAccessible(true);
		                  Object dayPicker = new Object();
		                  dayPicker = datePickerField.get(datePicker);
		                  ((View) dayPicker).setVisibility(View.GONE);
		               }
		            }
		         }
	
		      }		   
		    dpd.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
		    dpd.getDatePicker().setMinDate(minDate.getTimeInMillis());
		    
		 //   dpd.getDatePicker().setMaxDate(maxDate);
		 //   dpd.getDatePicker().init(changedYear, month, day, null);
	    } catch(Exception ex){
	    	
	    }
	    
	    return dpd;

     }
    
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {


		// when dialog box is closed, below method will be called.
		@Override
		public void onDateSet(DatePicker datepickerView, int selectedYear,
		    int selectedMonth, int selectedDay) {
		
		
			try {
				
				if (selectedYear > maxYear ||selectedMonth > maxMonth && selectedYear == maxYear||
						selectedDay > maxDay && selectedYear == maxYear && selectedMonth == maxMonth){

					datepickerView.updateDate(maxYear, maxMonth, maxDay);
		         //   updateDisplay(maxYear, maxMonth, maxDay);

		        }
		        else if (selectedYear < minYear ||selectedMonth < minMonth && selectedYear == minYear||
		        		selectedDay < minDay && selectedYear == minYear && selectedMonth == minMonth) {

		        	datepickerView.updateDate(minYear, minMonth, minDay);
		          //  updateDisplay(minYear, minMonth, minDay);
		        }
		        else {

		        	datepickerView.updateDate(selectedYear, selectedMonth, selectedDay);
		         //   updateDisplay(year, monthOfYear, dayOfMonth);
		        } 
				
				 _monthAndYear.setText(new StringBuilder().append(selectedMonth + 1).append("/").append("/").append(selectedYear));  
				
			} catch (Exception e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}		
		
		}
};

//private void initDatePickerCompat(final Calendar minCalendar, final Calendar maxCalendar) {
//    if (null == mDatePicker) {
//        // Exit early if DatePicker view was not initialized
//        return;
//    }
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//        // (mDatePicker is a DatePicker)
//        mDatePicker.setMinDate(minCalendar.getTimeInMillis());
//        mDatePicker.setMaxDate(maxCalendar.getTimeInMillis());
//    } else {
//        final int minYear = minCalendar.get(Calendar.YEAR);
//        final int minMonth = minCalendar.get(Calendar.MONTH);
//        final int minDay = minCalendar.get(Calendar.DAY_OF_MONTH);
//
//        final int maxYear = maxCalendar.get(Calendar.YEAR);
//        final int maxMonth = maxCalendar.get(Calendar.MONTH);
//        final int maxDay = maxCalendar.get(Calendar.DAY_OF_MONTH);
//
//        mDatePicker.init(minYear, minMonth, minDay, new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Calendar newCalendar = Calendar.getInstance();
//                newCalendar.set(year, monthOfYear, dayOfMonth);
//
//                if (newCalendar.after(maxCalendar)) {
//                    // MAX
//                    view.updateDate(maxYear, maxMonth, maxDay);
//                } else if (newCalendar.before(minCalendar)) {
//                    // MIN
//                    view.updateDate(minYear, minMonth, minDay);
//                } else {
//                    // BETWEEN
//                    view.updateDate(year, monthOfYear, dayOfMonth);
//                }
//            }
//        });
//    }
//}
    
    // menu creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//    	menu.add(0,1,0,"add");
//    	menu.add(0,2,0,"edit");
//    	menu.add(0,3,3,"delete");
//    	menu.add(1,4,1,"copy");
//    	menu.add(1,5,2,"paste");
//    	menu.add(1,6,4,"exit");
    	
    	
    	
    	
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    // menu update
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu){
//    	menu.setGroupVisible(1, chv.isChecked());
//    	return super.onPrepareOptionsMenu(menu);
//    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	
//    	StringBuilder sb = new StringBuilder();
//    	sb.append("Item Menu");
//    	sb.append("\r\n groupId: "+ String.valueOf(item.getGroupId()));
//    	sb.append("\r\n itemId: "+ String.valueOf(item.getItemId()));
//    	sb.append("\r\n order: "+ String.valueOf(item.getOrder()));
//    	sb.append("\r\n title: "+ String.valueOf(item.getTitle()));
//    	txv.setText(sb.toString());
    	
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }    	
    	
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
          }
          
        return super.onOptionsItemSelected(item);
    }
    
    @Override
	 protected void onPostCreate(Bundle savedInstanceState) {
		 super.onPostCreate(savedInstanceState);	     
	     mDrawerToggle.syncState();	
	 }
   
    public void loginButtonClick(View view){
    	
    	Intent intent = new Intent(this,MenuActivity.class);
    	EditText emailText = (EditText) findViewById(R.id.email);
    	EditText passwordText = (EditText) findViewById(R.id.password);
    	String message = "Email:  " + emailText.getText().toString();
    	message = message +"\r\nPassword:  " +  passwordText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
 
   // @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
//		Intent intent = new Intent(this, ActivityTime.class) ;
//		startActivityForResult(intent, 1);
//		
		 // showCheatSheet(v, "perk wallet" );
		  
		  if(!tipWindow.isTooltipShown())
			  tipWindow.showToolTip(v);
		
//		switch (v.getId()) {
//		case R.id.btnTime:
//			intent = new Intent("com.example.test.action.showtime");
//		    startActivity(intent);
//		    break;
//		case R.id.btnDate:
//		//	email.setText("Button cancle click");
//			break;
//		default:
//			break;
		}
	@Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (data == null) {return;}
	    String name = data.getStringExtra("name");
	    tvName.setText("Your name is " + name);
	  }
	private static final int ESTIMATED_TOAST_HEIGHT_DIPS = 48;
	/**
     * Internal helper method to show the cheat sheet toast.
     */
    private static boolean showCheatSheet(View view, CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return false;
        }
        System.out.println("inside showCheatSheet");
        final int[] screenPos = new int[2]; // origin is device display
        final Rect displayFrame = new Rect(); // includes decorations (e.g. status bar)
        view.getLocationOnScreen(screenPos);
        view.getWindowVisibleDisplayFrame(displayFrame);
 
        final Context context = view.getContext();
        final int viewWidth = view.getWidth();
        final int viewHeight = view.getHeight();
        final int viewCenterX = screenPos[0] + viewWidth / 2;
        final int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        final int estimatedToastHeight = (int) (ESTIMATED_TOAST_HEIGHT_DIPS
                * context.getResources().getDisplayMetrics().density);
 
        Toast cheatSheet = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        boolean showBelow = screenPos[1] < estimatedToastHeight;
        if (showBelow) {
            // Show below
            // Offsets are after decorations (e.g. status bar) are factored in
            cheatSheet.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL,
                    viewCenterX - screenWidth / 2,
                    screenPos[1] - displayFrame.top + viewHeight);
        } else {
            // Show above
            // Offsets are after decorations (e.g. status bar) are factored in
            // NOTE: We can't use Gravity.BOTTOM because when the keyboard is up
            // its height isn't factored in.
            cheatSheet.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL,
                    viewCenterX - screenWidth / 2,
                    screenPos[1] - displayFrame.top - estimatedToastHeight);
        }
 
        cheatSheet.show();
        return true;
    }
    
    @Override
    protected void onDestroy() {
    // TODO Auto-generated method stub
	    if(tipWindow != null && tipWindow.isTooltipShown())
	    tipWindow.dismissTooltip();
	    
	    super.onDestroy();
    }
    
}
