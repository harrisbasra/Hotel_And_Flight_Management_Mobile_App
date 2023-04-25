package com.sda.fastlogistics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class DBTESTER extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtester);
        MyDBHelper DBB = new MyDBHelper(this);
        DBB.CreateTables();


        DBB.insertFlightsDetail  (1001,"2023-03-23 12:00:00","2023-03-23 13:00:00","Lahore","Karchi","Available");
        DBB.insertFlightsDetail  (1001,"2023-03-23 16:00:00","2023-03-23 17:00:00","Lahore","Karchi","Available");
        DBB.insertFlightsDetail  (1002,"2023-03-24 12:00:00","2023-03-23 13:12:54","Lahore","Multan","Available");
        DBB.insertFlightsDetail  (1002,"2023-03-24 16:00:00","2023-03-23 17:12:54","Multan","Lahore","Available");
        DBB.insertFlightsDetail  (1003,"2023-03-26 01:00:00","2023-03-26 02:30:00","Lahore","Peshawar","Unvailable");
        DBB.insertFlightsDetail (1003,"2023-03-26 03:00:00","2023-03-23 4:30:00","Peshawar","Lahore","Unavailable");
        DBB.insertFlightsDetail  (1004,"2023-03-25 03:00:00","2023-03-23 04:00:00","Islamabad","Lahore","Available");
        DBB.insertFlightsDetail  (1004,"2023-03-25 05:00:00","2023-03-23 06:00:00","Lahore","Islamabad","Available");



        DBB.insertHotelsDetails (5001,"Awari","Lahore","Available");
        DBB.insertHotelsDetails (5002,"PC","Peshawar","Unavailable");
        DBB.insertHotelsDetails (5003,"Grand Swiss","Lahore","Available");
        DBB.insertHotelsDetails (5004,"Luxus Grand","Islamabad","Available");
        DBB.insertHotelsDetails (5005,"Nishat","Karachi","Available");


        DBB.insertEmployees("Javaria","ted24");
        DBB.insertEmployees("Harris","lahore");
        DBB.insertEmployees("Rohma","passcode");
        DBB.insertEmployees("Noor Fatima","123fast");


        DBB.insertCustomersDetails("Zain Ali","pakistan","2345167813451234");
        DBB.insertCustomersDetails("Hadia Ahmed","xyz123","2345167813345678");
        DBB.insertCustomersDetails("John Mathew","europe","2345167813451666");
        DBB.insertCustomersDetails("Ali Malik","punjab","7777167813451234");
        DBB.insertCustomersDetails("Ayesha Arif","Nutella","2678167813451234");


        DBB.insertFlightSeats(1,1001,"2023-03-23 12:00:00","Reserved","Economy", 20.5);

        DBB.insertFlightSeats(2,1001,"2023-03-23 12:00:00","Reserved","Economy",20.5);
        DBB.insertFlightSeats(3,1001,"2023-03-23 12:00:00","Unreserved","Regular",20.5);
        DBB.insertFlightSeats(4,1001,"2023-03-23 12:00:00","Unreserved","Economy",20.5);
        DBB.insertFlightSeats(5,1001,"2023-03-23 12:00:00","Reserved","Economy",20.5);
        DBB.insertFlightSeats(1,1003,"2023-03-26 03:00:00","Reserved","Economy",20.5);
        DBB.insertFlightSeats(2,1003,"2023-03-26 03:00:00","Unreserved","Economy",20.5);
        DBB.insertFlightSeats(3,1003,"2023-03-26 03:00:00","Reserved","Regular",20.5);
        DBB.insertFlightSeats(4,1003,"2023-03-26 03:00:00","Reserved","Economy",20.5);
        DBB.insertFlightSeats(5,1003,"2023-03-26 03:00:00","Reserved","Regular",20.5);
        DBB.insertFlightSeats(6,1003,"2023-03-26 03:00:00","Unreserved","Economy",20.5);
        DBB.insertFlightSeats(1,1002,"2023-03-24 12:00:00","Reserved","Economy",20.5);
        DBB.insertFlightSeats(2,1002,"2023-03-24 12:00:00","Reserved","Regular",20.5);
        DBB.insertFlightSeats(3,1002,"2023-03-24 12:00:00","Reserved","Economy",20.5);
        DBB.insertFlightSeats(4,1002,"2023-03-24 12:00:00","Unrserved","Economy",20.5);
        DBB.insertFlightSeats(5,1002,"2023-03-24 12:00:00","Unreserved","Regular",20.5);
        DBB.insertFlightSeats(6,1002,"2023-03-24 12:00:00","Unreserved","Economy",20.5);
        DBB.insertFlightSeats(1,1001,"2023-03-23 16:00:00","Unreserved","Economy",20.5);
        DBB.insertFlightSeats(2,1001,"2023-03-23 16:00:00","Reserved","Economy",20.5);
        DBB.insertFlightSeats(3,1001,"2023-03-23 16:00:00","Reserved","Economy",20.5);
        DBB.insertFlightSeats(4,1001,"2023-03-23 16:00:00","Reserved","Economy",20.5);

        DBB.insertflightbookings (100,1001,"2023-03-23 12:00:00",1);
        DBB.insertflightbookings (104,1001,"2023-03-23 12:00:00",2);
        DBB.insertflightbookings (102,1001,"2023-03-23 12:00:00",5);
        DBB.insertflightbookings (101,1003,"2023-03-26 03:00:00",1);
        DBB.insertflightbookings (101,1003,"2023-03-26 03:00:00",3);
        DBB.insertflightbookings (104,1003,"2023-03-26 03:00:00",4);
        DBB.insertflightbookings (101,1003,"2023-03-26 03:00:00",5);
        DBB.insertflightbookings (101,1002,"2023-03-24 12:00:00",1);
        DBB.insertflightbookings (103,1002,"2023-03-24 12:00:00",2);
        DBB.insertflightbookings (102,1002,"2023-03-24 12:00:00",3);
        DBB.insertflightbookings (101,1002,"2023-03-23 16:00:00",2);
        DBB.insertflightbookings (104,1002,"2023-03-23 16:00:00",3);
        DBB.insertflightbookings (102,1002,"2023-03-23 16:00:00",4);


        DBB.insertroomsdetails(5004,2,"Single",35000);
        DBB.insertroomsdetails(5004,71,"Family",40000);
        DBB.insertroomsdetails(5001,23,"Double",40000);
        DBB.insertroomsdetails(5001,2,"Double",20000);
        DBB.insertroomsdetails(5001,1,"Single",15000);
        DBB.insertroomsdetails(5001,4,"Double",25000);
        DBB.insertroomsdetails(5002,10,"Single",9000);
        DBB.insertroomsdetails(5002,6,"Family",50000);
        DBB.insertroomsdetails(5005,3,"Single",24000);
        DBB.insertroomsdetails(5005,4,"Suite",42000);
        DBB.insertroomsdetails(5002,5,"Suite",10000);



        DBB.insertroombooking(104,5002,6,"9/12/2023","9/20/2023");
        DBB.insertroombooking(102,5001,2,"4/1/2023","5/1/2023");
        DBB.insertroombooking(101,5004,71,"3/23/2023","3/31/2023");
        DBB.insertroombooking(104,5002,10,"2/2/2023","2/28/2023");
        DBB.insertroombooking(103,5002,5,"1/25/2023","2/12/2023");

        // Fetch the data from the database
//        List<Contact> contactList = DBB.getAllContacts();
//        String Arriy[] = {contactList.get(0).getName(),contactList.get(1).getName() };
//        Toast.makeText(this, Arriy[1], Toast.LENGTH_SHORT).show();
        // Create an adapter to display the data in the list view
//        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(DBTESTER.this,, Arriy);
//        ListView listView = findViewById(R.id.listview);
//        listView.setAdapter(adapter);
    }

}