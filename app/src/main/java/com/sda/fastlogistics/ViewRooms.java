package com.sda.fastlogistics;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.sda.fastlogistics.databinding.ActivityViewRoomsBinding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ViewRooms extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler(Looper.myLooper());

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {

        }
    };

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (AUTO_HIDE) {
                        delayedHide(AUTO_HIDE_DELAY_MILLIS);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    view.performClick();
                    break;
                default:
                    break;
            }
            return false;
        }
    };
    private ActivityViewRoomsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewRoomsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mVisible = true;

        MyDBHelper db = new MyDBHelper(this);
        String Hi[] = db.getCitiesWithAvailableHotels();
        ArrayAdapter<String> citites = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Hi);
        binding.spinnerA.setAdapter(citites);

        String Je[] = db.getAvailableHotelsInCity(binding.spinnerA.getSelectedItem().toString());
        ArrayAdapter<String> hotells = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Je);
        binding.spinnerB.setAdapter(hotells);

        String RoomType[] = db.getAvailableRoomTypesInHotel(binding.spinnerB.getSelectedItem().toString(), binding.spinnerA.getSelectedItem().toString());
        if(!(RoomType ==null)){
            ArrayAdapter<String> roomType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, RoomType);
            binding.spinner4.setAdapter(roomType);
        }

        binding.spinnerA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Je[] = db.getAvailableHotelsInCity(binding.spinnerA.getSelectedItem().toString());
                ArrayAdapter<String> hotells = new ArrayAdapter<String>(ViewRooms.this, android.R.layout.simple_spinner_dropdown_item, Je);
                binding.spinnerB.setAdapter(hotells);
                String City = binding.spinnerA.getSelectedItem().toString();
                int HotelID = db.getHotelIdByName(binding.spinnerB.getSelectedItem().toString());
                String RoomNum[] = db.getAvailableRooms2(HotelID, binding.spinner4.getSelectedItem().toString());
                if(RoomNum==null){
                    RoomNum = new String[]{};
                }
                ArrayAdapter<String> roomType = new ArrayAdapter<String>(ViewRooms.this, android.R.layout.simple_spinner_dropdown_item, RoomNum);
                binding.cypher.setAdapter(roomType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.spinnerB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String RoomType[] = db.getAvailableRoomTypesInHotel(binding.spinner4.getSelectedItem().toString(), binding.spinnerA.getSelectedItem().toString());
                if(!(RoomType ==null)){
                    ArrayAdapter<String> roomType = new ArrayAdapter<String>(ViewRooms.this, android.R.layout.simple_spinner_dropdown_item, RoomType);
                    binding.spinner4.setAdapter(roomType);
                    String City = binding.spinnerA.getSelectedItem().toString();
                    int HotelID = db.getHotelIdByName(binding.spinnerB.getSelectedItem().toString());
                    String RoomNum[] = db.getAvailableRooms2(HotelID, binding.spinner4.getSelectedItem().toString());
                    if(RoomNum==null){
                        RoomNum = new String[]{};
                    }
                    ArrayAdapter<String> roomTypeo = new ArrayAdapter<String>(ViewRooms.this, android.R.layout.simple_spinner_dropdown_item, RoomNum);
                    binding.cypher.setAdapter(roomTypeo);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        String City = binding.spinnerA.getSelectedItem().toString();
        int HotelID = db.getHotelIdByName(binding.spinnerB.getSelectedItem().toString());
        String RoomNum[] = db.getAvailableRooms2(HotelID, binding.spinner4.getSelectedItem().toString());

        ArrayAdapter<String> roomType = new ArrayAdapter<String>(ViewRooms.this, android.R.layout.simple_spinner_dropdown_item, RoomNum);
        binding.cypher.setAdapter(roomType);



        binding.spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String City = binding.spinnerA.getSelectedItem().toString();
                int HotelID = db.getHotelIdByName(binding.spinnerB.getSelectedItem().toString());
                String RoomNum[] = db.getAvailableRooms2(HotelID, binding.spinner4.getSelectedItem().toString());
                if(RoomNum==null){
                    RoomNum = new String[]{};
                }
                ArrayAdapter<String> roomType = new ArrayAdapter<String>(ViewRooms.this, android.R.layout.simple_spinner_dropdown_item, RoomNum);
                binding.cypher.setAdapter(roomType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private void show() {

        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}