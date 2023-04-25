package com.sda.fastlogistics;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MyDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyDatabase.db";

    String CREATE_FLIGHTS_DETAIL_TABLE = "CREATE TABLE IF NOT EXISTS FlightsDetail ("
            + "Flightno INTEGER,"
            + "DepartureTime DATETIME,"
            + "ArrivalTime DATETIME,"
            + "DepartureCity VARCHAR(20),"
            + "ArrivalCity VARCHAR(20),"
            + "FlightStatus VARCHAR(20),"
            + "PRIMARY KEY (Flightno, DepartureTime),"
            + "CONSTRAINT fk_1 FOREIGN KEY (Flightno, DepartureTime) REFERENCES FlightsDetail (Flightno, DepartureTime)"
            + ");";

    String CREATE_HOTELS_DETAILS_TABLE = "CREATE TABLE IF NOT EXISTS HotelsDetails ("
            + "HotelId INTEGER,"
            + "HotelName VARCHAR(20),"
            + "Hotelcity VARCHAR(20),"
            + "HotelStatus VARCHAR(20),"
            + "PRIMARY KEY (HotelId),"
            + "CONSTRAINT fk_2 FOREIGN KEY (HotelId) REFERENCES HotelsDetails (HotelId)"
            + ");";

    String CREATE_EMPLOYEES_TABLE = "CREATE TABLE IF NOT EXISTS Employees ("
            + "EmployeeId INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "EmployeeName VARCHAR(20),"
            + "EmployeePassword VARCHAR(20)"
            + ");";

    String CREATE_CUSTOMERS_DETAILS_TABLE = "CREATE TABLE IF NOT EXISTS CustomersDetails ("
            + "CustomerId INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "CustomerName VARCHAR(30),"
            + "CustomerPassword VARCHAR(20),"
            + "CreditCardNumber VARCHAR(20)"
            + ");";

    String CREATE_FLIGHT_SEATS_TABLE = "CREATE TABLE IF NOT EXISTS FlightSeats ("
            + "SeatNumber INTEGER NOT NULL,"
            + "Flightno INTEGER,"
            + "DepartureTime DATETIME,"
            + "SeatStatus VARCHAR(20),"
            + "FlightClass VARCHAR(20),"
            + "SeatPrice FLOAT,"
            + "PRIMARY KEY (SeatNumber),"
            + "CONSTRAINT fk_3 FOREIGN KEY (Flightno, DepartureTime) REFERENCES FlightsDetail (Flightno, DepartureTime)"
            + ");";

    String CREATE_FLIGHT_BOOKINGS_TABLE = "CREATE TABLE IF NOT EXISTS FlightBookings ("
            + "CustomerId INTEGER REFERENCES CustomersDetails(CustomerId),"
            + "Flightno INTEGER,"
            + "DepartureTime DATETIME,"
            + "SeatNumber INTEGER,"
            + "PRIMARY KEY (CustomerId, Flightno, DepartureTime),"
            + "CONSTRAINT fk_4 FOREIGN KEY (Flightno, DepartureTime) REFERENCES FlightsDetail (Flightno, DepartureTime)"
            + ");";

    String CREATE_ROOMS_DETAILS_TABLE = "CREATE TABLE IF NOT EXISTS RoomsDetails ("
            + "HotelId INTEGER NOT NULL REFERENCES HotelsDetails(HotelId) ON UPDATE CASCADE ON DELETE CASCADE,"
            + "RoomNo INTEGER NOT NULL,"
            + "Type VARCHAR(20),"
            + "Price INTEGER,"
            + "PRIMARY KEY (HotelId, RoomNo)"
            + ");";

    String CREATE_ROOM_BOOKING_TABLE = "CREATE TABLE IF NOT EXISTS RoomBooking ("
            + "Cust_ID INTEGER REFERENCES CustomersDetails(CustomerId) ON UPDATE CASCADE ON DELETE CASCADE,"
            + "HotelId INTEGER,"
            + "RoomNo INTEGER,"
            + "Datefrom DATETIME,"
            + "Dateto DATETIME,"
            + "PRIMARY KEY (Cust_ID, HotelId, RoomNo),"
            + "CONSTRAINT fk_5 FOREIGN KEY (HotelId, RoomNo) REFERENCES RoomsDetails (HotelId, RoomNo)"
            + ");";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + "FlightsDetail");
        db.execSQL("DROP TABLE IF EXISTS " + "HotelsDetails");
        db.execSQL("DROP TABLE IF EXISTS " + "Employees");
        db.execSQL("DROP TABLE IF EXISTS " + "CustomersDetails");
        db.execSQL("DROP TABLE IF EXISTS " + "FlightSeats");
        db.execSQL("DROP TABLE IF EXISTS " + "FlightBookings");
        db.execSQL("DROP TABLE IF EXISTS " + "roomsdetails");
        db.execSQL("DROP TABLE IF EXISTS " + "Roombooking");
        // Create tables again
        onCreate(db);
    }
    public void CreateTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CREATE_FLIGHTS_DETAIL_TABLE);
        db.execSQL(CREATE_HOTELS_DETAILS_TABLE);
        db.execSQL(CREATE_EMPLOYEES_TABLE);
        db.execSQL(CREATE_CUSTOMERS_DETAILS_TABLE);
        db.execSQL(CREATE_FLIGHT_SEATS_TABLE);
        db.execSQL(CREATE_FLIGHT_BOOKINGS_TABLE);
        db.execSQL(CREATE_ROOMS_DETAILS_TABLE);
        db.execSQL(CREATE_ROOM_BOOKING_TABLE);
    }
    public void insertFlightsDetail(int flightNo, String departureTime, String arrivalTime, String departureCity, String arrivalCity, String flightStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Flightno", flightNo);
        values.put("DepartureTime", departureTime);
        values.put("ArrivalTime", arrivalTime);
        values.put("DepartureCity", departureCity);
        values.put("ArrivalCity", arrivalCity);
        values.put("FlightStatus", flightStatus);
        db.insert("FlightsDetail", null, values);
    }

    public void insertHotelsDetails(int hotelId, String hotelName, String hotelCity, String hotelStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HotelId", hotelId);
        values.put("HotelName", hotelName);
        values.put("Hotelcity", hotelCity);
        values.put("HotelStatus", hotelStatus);
        db.insert("HotelsDetails", null, values);
    }

    public void insertEmployees(String employeeName, String employeePassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("EmployeeName", employeeName);
        values.put("EmployeePassword", employeePassword);
        db.insert("Employees", null, values);
    }

    public void insertCustomersDetails(String customerName, String customerPassword, String creditCardNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CustomerName", customerName);
        values.put("CustomerPassword", customerPassword);
        values.put("CreditCardNumber", creditCardNumber);
        db.insert("CustomersDetails", null, values);
    }

    public void insertFlightSeats(int seatNumber, int flightNo, String departureTime, String seatStatus, String flightClass, double seatPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SeatNumber", seatNumber);
        values.put("Flightno", flightNo);
        values.put("DepartureTime", departureTime);
        values.put("SeatStatus", seatStatus);
        values.put("FlightClass", flightClass);
        values.put("SeatPrice", seatPrice);
        db.insert("FlightSeats", null, values);
    }

    public void insertflightbookings(int customerId, int flightNo, String departureTime, int seatNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CustomerId", customerId);
        values.put("Flightno", flightNo);
        values.put("DepartureTime", departureTime);
        values.put("SeatNumber", seatNumber);
        db.insert("FlightBookings", null, values);
    }

    public void insertroomsdetails(int hotelId, int roomNo, String type, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hotelid", hotelId);
        values.put("roomNo", roomNo);
        values.put("type", type);
        values.put("price", price);
        db.insert("roomsdetails", null, values);
    }

    public void insertroombooking(int custId, int hotelId, int roomNo, String dateFrom, String dateTo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cust_ID", custId);
        values.put("hotelid", hotelId);
        values.put("Roomno", roomNo);
        values.put("Datefrom", dateFrom);
        values.put("Dateto", dateTo);
        db.insert("Roombooking", null, values);
        db.close();
    }
    // Methods to return and insert data will go here

}



//    public List<Contact> getAllContacts() {
//        List<Contact> contactList = new ArrayList<Contact>();
//        String selectQuery = "SELECT * FROM contacts";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // Loop through all rows and add them to the contact list
//        if (cursor.moveToFirst()) {
//            do {
//                Contact contact = new Contact();
//                contact.setId(cursor.getInt(0));
//                contact.setName(cursor.getString(1));
//                contact.setPhoneNumber(cursor.getString(2));
//                contactList.add(contact);
//            } while (cursor.moveToNext());
//        }
//
//        // Close the cursor and the database
//        cursor.close();
//        db.close();
//
//        // Return the contact list
//        return contactList;
//    }
