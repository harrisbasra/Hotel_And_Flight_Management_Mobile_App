package com.sda.fastlogistics;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import com.sda.fastlogistics.databinding.ActivityBookFlightsBinding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BookFlights extends AppCompatActivity {
    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
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
    private ActivityBookFlightsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBookFlightsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mVisible = true;


        binding.button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookFlights.this, PaymentModule.class));
            }
        });



        MyDBHelper db = new MyDBHelper(this);
        String Hi[] = db.getAvailableDepartureCities();
        ArrayAdapter<String> citites = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Hi);
        binding.spinnerA.setAdapter(citites);

        String Je[] = db.getAvailableArrivalCities(binding.spinnerA.getSelectedItem().toString());
        ArrayAdapter<String> hotells = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Je);
        binding.spinnerA2.setAdapter(hotells);

        binding.spinnerA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Je[] = db.getAvailableArrivalCities(binding.spinnerA.getSelectedItem().toString());
                ArrayAdapter<String> hotells = new ArrayAdapter<String>(BookFlights.this, android.R.layout.simple_spinner_dropdown_item, Je);
                binding.spinnerA2.setAdapter(hotells);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String RoomType[] = db.getAvailableDepartureTimes(binding.spinnerA.getSelectedItem().toString(), binding.spinnerA2.getSelectedItem().toString());
        if(!(RoomType ==null)){
            ArrayAdapter<String> roomType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, RoomType);
            binding.spinnerA3.setAdapter(roomType);
        }
        else{
            String Empty[] = {};
            ArrayAdapter<String> roomType = new ArrayAdapter<String>(BookFlights.this, android.R.layout.simple_spinner_dropdown_item, Empty);
            binding.spinnerA3.setAdapter(roomType);
        }

        binding.spinnerA2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String RoomType[] = db.getAvailableDepartureTimes(binding.spinnerA.getSelectedItem().toString(), binding.spinnerA2.getSelectedItem().toString());
                if(!(RoomType ==null)){
                    ArrayAdapter<String> roomType = new ArrayAdapter<String>(BookFlights.this, android.R.layout.simple_spinner_dropdown_item, RoomType);
                    binding.spinnerA3.setAdapter(roomType);
                }
                else{
                    String Empty[] = {};
                    ArrayAdapter<String> roomType = new ArrayAdapter<String>(BookFlights.this, android.R.layout.simple_spinner_dropdown_item, Empty);
                    binding.spinnerA3.setAdapter(roomType);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        String Classtypes[] = db.getAvailableFlightClasses(binding.spinnerA.getSelectedItem().toString(), binding.spinnerA2.getSelectedItem().toString(), binding.spinnerA3.getSelectedItem().toString());
        if(!(Classtypes ==null)){
            ArrayAdapter<String> roomType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Classtypes);
            binding.cypher2.setAdapter(roomType);
        }
        else{
            String Empty[] = {};
            ArrayAdapter<String> roomType = new ArrayAdapter<String>(BookFlights.this, android.R.layout.simple_spinner_dropdown_item, Empty);
            binding.cypher2.setAdapter(roomType);
        }

        binding.spinnerA3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Classtypes[] = db.getAvailableFlightClasses(binding.spinnerA.getSelectedItem().toString(), binding.spinnerA2.getSelectedItem().toString(), binding.spinnerA3.getSelectedItem().toString());
                if(!(Classtypes ==null)){
                    ArrayAdapter<String> roomType = new ArrayAdapter<String>(BookFlights.this, android.R.layout.simple_spinner_dropdown_item, Classtypes);
                    binding.cypher2.setAdapter(roomType);
                }
                else{
                    String Empty[] = {};
                    ArrayAdapter<String> roomType = new ArrayAdapter<String>(BookFlights.this, android.R.layout.simple_spinner_dropdown_item, Empty);
                    binding.cypher2.setAdapter(roomType);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        binding.button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(binding.cypher4.getText().toString().equals(""))){
                    String ID = String.valueOf(db.getCustomerIdFromName(binding.cypher4.getText().toString()));
                    String DepartureCity = binding.spinnerA.getSelectedItem().toString();
                    String ArrivalCity = binding.spinnerA2.getSelectedItem().toString();
                    String Date = binding.spinnerA3.getSelectedItem().toString();
                    String Class = binding.cypher2.getSelectedItem().toString();

                    int FlightSeat = Integer.valueOf(db.getAvailableSeatNumber(DepartureCity, ArrivalCity, Date, Class)[0]);
                    int FlightNumber = Integer.valueOf(db.getAvailableSeatNumber(DepartureCity, ArrivalCity, Date, Class)[1]);

                    String Price = "0";
                    Toast.makeText(BookFlights.this, FlightSeat+"'"+FlightNumber, Toast.LENGTH_SHORT).show();
                    if(!ID.equals("-1")){
                        Price = String.valueOf(db.getFlightPrice(FlightNumber, Class));
                        ////////////////////////////////////////////////////////////////
                        String Money1= "0";
                        try {
                            FileInputStream fin = openFileInput("flightmoney.txt");
                            int a;
                            StringBuilder temp = new StringBuilder();
                            while ((a = fin.read()) != -1) {
                                temp.append((char)a);
                            }
                            Money1 = temp.toString();
                            fin.close();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                        FileOutputStream fos = null;
                        Money1 = String.valueOf(Float.valueOf(Money1) +Float.valueOf(Price));
                        try {
                            fos = openFileOutput("flightmoney.txt", Context.MODE_PRIVATE);
                            fos.write(Money1.getBytes());
                            fos.flush();
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        db.FinallyBookFlight(Integer.valueOf(ID), FlightNumber, Date, FlightSeat);
                        startActivity(new Intent(BookFlights.this, MainMenuuu.class));
                    }
                    else{
                        Toast.makeText(BookFlights.this, "No Such Customers Exist", Toast.LENGTH_SHORT).show();
                    }
                }
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
        // Show the system bar

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