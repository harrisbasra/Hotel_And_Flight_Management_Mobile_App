package com.sda.fastlogistics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


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

    }
    public String[] getCitiesWithAvailableHotels() {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT Hotelcity FROM HotelsDetails WHERE HotelStatus = 'Available'";

        Cursor cursor = db.rawQuery(query, null);

        String[] cities = new String[cursor.getCount()];
        int i = 0;

        while (cursor.moveToNext()) {
            String city = cursor.getString(0);
            cities[i++] = city;
        }

        return cities;
    }
    public String[] getAvailableHotelsInCity(String cityName) {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT HotelName FROM HotelsDetails WHERE HotelStatus = 'Available' AND Hotelcity = ?";

        Cursor cursor = db.rawQuery(query, new String[] { cityName });

        String[] hotels = new String[cursor.getCount()];
        int i = 0;

        while (cursor.moveToNext()) {
            String hotelName = cursor.getString(0);
            hotels[i++] = hotelName;
        }

        cursor.close();
        db.close();

        return hotels;
    }

    public String[] getAvailableRoomTypesInHotel(String hotelName, String cityName) {
        SQLiteDatabase db = getReadableDatabase();

        String hotelIdQuery = "SELECT HotelId FROM HotelsDetails WHERE HotelName = ? AND Hotelcity = ?";
        Cursor hotelIdCursor = db.rawQuery(hotelIdQuery, new String[] { hotelName, cityName });

        if (!hotelIdCursor.moveToFirst()) {
            // Hotel not found in database
            hotelIdCursor.close();
            db.close();
            return null;
        }

        int hotelId = hotelIdCursor.getInt(0);
        hotelIdCursor.close();

        String roomTypeQuery = "SELECT Type FROM RoomsDetails WHERE HotelId = ? AND Price > 0";
        Cursor roomTypeCursor = db.rawQuery(roomTypeQuery, new String[] { String.valueOf(hotelId) });

        String[] roomTypes = new String[roomTypeCursor.getCount()];
        int i = 0;

        while (roomTypeCursor.moveToNext()) {
            String roomType = roomTypeCursor.getString(0);
            roomTypes[i++] = roomType;
        }

        return roomTypes;
    }
    public int getCustomerIdFromName(String customerName) {
        int customerId = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CustomerId FROM CustomersDetails WHERE CustomerName = ?", new String[]{customerName});
        if (cursor.moveToFirst()) {
            customerId = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return customerId;
    }
    public int getHotelIdByName(String hotelName) {
        int hotelid = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT HotelID FROM HotelsDetails  WHERE HotelName = ?", new String[]{hotelName});
        if (cursor.moveToFirst()) {
            hotelid = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return hotelid;
    }
    public String getAvailableRooms(int hotelId, String roomType) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] availableRooms = null;

        String query = "SELECT RoomNo FROM RoomsDetails " +
                "WHERE HotelId = ? AND Type = ? " +
                "AND RoomNo NOT IN " +
                "(SELECT RoomNo FROM RoomBooking " +
                "WHERE HotelId = ? AND DateTo >= ? AND DateFrom <= ?)";

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(calendar.getTime());

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(hotelId), roomType, String.valueOf(hotelId), currentDate, currentDate});

        if (cursor.moveToFirst()) {
            availableRooms = new String[cursor.getCount()];
            int index = 0;
            do {
                availableRooms[index++] = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        if(availableRooms!=null)
        return availableRooms[0];
        return null;
    }

    public String[] getAvailableRooms2(int hotelId, String roomType) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] availableRooms = null;

        String query = "SELECT RoomNo FROM RoomsDetails " +
                "WHERE HotelId = ? AND Type = ? " +
                "AND RoomNo NOT IN " +
                "(SELECT RoomNo FROM RoomBooking " +
                "WHERE HotelId = ? AND DateTo >= ? AND DateFrom <= ?)";

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(calendar.getTime());

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(hotelId), roomType, String.valueOf(hotelId), currentDate, currentDate});

        if (cursor.moveToFirst()) {
            availableRooms = new String[cursor.getCount()];
            int index = 0;
            do {
                availableRooms[index++] = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        if(availableRooms!=null)
            return availableRooms;
        return null;
    }

    public void FinallybookRoom(int customerId, int hotelId, int roomNo, String dateTo) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Get the current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String dateFrom = dateFormat.format(currentDate);

        // Prepare the values to be inserted
        ContentValues values = new ContentValues();
        values.put("Cust_ID", customerId);
        values.put("HotelId", hotelId);
        values.put("RoomNo", roomNo);
        values.put("Datefrom", dateFrom);
        values.put("Dateto", dateTo);

        // Insert the row into the table
        db.insert("RoomBooking", null, values);
        String[] args = {Integer.toString(hotelId), Integer.toString(roomNo)};
        db.delete("RoomsDetails", "HotelId=? AND RoomNo=?", args);
        db.close();
    }
    /////////////////////////////////////////
    public String[] getAvailableDepartureCities() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {"DepartureCity"};
        String selection = "FlightStatus = ?";
        String[] selectionArgs = {"Available"};
        Cursor cursor = db.query(
                "FlightsDetail",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        List<String> departureCities = new ArrayList<>();
        while (cursor.moveToNext()) {
            String departureCity = cursor.getString(cursor.getColumnIndexOrThrow("DepartureCity"));
            departureCities.add(departureCity);
        }
        cursor.close();
        return departureCities.toArray(new String[0]);
    }

    public String[] getAvailableArrivalCities(String departureCity) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT DISTINCT ArrivalCity FROM FlightsDetail WHERE DepartureCity = ? AND FlightStatus = ?";
        String[] selectionArgs = {departureCity, "Available"};

        Cursor cursor = db.rawQuery(query, selectionArgs);
        List<String> arrivalCities = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                arrivalCities.add(cursor.getString(cursor.getColumnIndex("ArrivalCity")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return arrivalCities.toArray(new String[0]);
    }

    public String[] getAvailableDepartureTimes(String departureCity, String arrivalCity) {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT DepartureTime FROM FlightsDetail WHERE DepartureCity = ? AND ArrivalCity = ? AND FlightStatus = 'Available'";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{departureCity, arrivalCity});

        String[] departureTimes = new String[cursor.getCount()];
        int index = 0;

        if (cursor.moveToFirst()) {
            do {
                String departureTime = cursor.getString(cursor.getColumnIndex("DepartureTime"));
                departureTimes[index++] = departureTime;
            } while (cursor.moveToNext());
        }

        cursor.close();
        return departureTimes;
    }

    public String[] getAvailableFlightClasses(String departureCity, String arrivalCity, String departureTime) {
        ArrayList<String> flightClasses = new ArrayList<>();
        SQLiteDatabase mDatabase = getReadableDatabase();
        Cursor cursor = null;
        try {
            String query = "SELECT DISTINCT FlightClass FROM FlightSeats " +
                    "WHERE Flightno IN " +
                    "(SELECT Flightno FROM FlightsDetail WHERE DepartureCity = ? AND ArrivalCity = ? AND DepartureTime = ? AND FlightStatus = 'Available') " +
                    "AND SeatStatus = 'Unreserved'";
            cursor = mDatabase.rawQuery(query, new String[]{departureCity, arrivalCity, departureTime});
            while (cursor.moveToNext()) {
                String flightClass = cursor.getString(cursor.getColumnIndex("FlightClass"));
                flightClasses.add(flightClass);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return flightClasses.toArray(new String[flightClasses.size()]);
    }

    public String[] getAvailableSeatNumber(String departureCity, String arrivalCity, String departureTime, String flightClass) {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] result = new String[2];

        // Join tables to get the available seat
        String query = "SELECT FlightSeats.SeatNumber, FlightsDetail.Flightno " +
                "FROM FlightSeats " +
                "JOIN FlightsDetail ON FlightSeats.Flightno = FlightsDetail.Flightno AND FlightSeats.DepartureTime = FlightsDetail.DepartureTime " +
                "WHERE FlightsDetail.DepartureCity = ? AND FlightsDetail.ArrivalCity = ? AND FlightsDetail.DepartureTime = ? AND FlightSeats.SeatStatus = 'Unreserved' AND FlightSeats.FlightClass = ? " +
                "ORDER BY FlightSeats.SeatNumber ASC " +
                "LIMIT 1";

        Cursor cursor = db.rawQuery(query, new String[]{departureCity, arrivalCity, departureTime, flightClass});

        // Check if there is an available seat
        if (cursor.moveToFirst()) {
            result[0] = cursor.getString(0); // Get the seat number
            result[1] = cursor.getString(1); // Get the flight number
        } else {
            result[0] = "-1"; // Set to -1 if there are no available seats
            result[1] = "-1";
        }

        cursor.close();
        db.close();

        return result;
    }

    public void FinallyBookFlight(int customerId, int flightNo, String departureTime, int seatNo) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Insert the booking details into the FlightBookings table
        ContentValues bookingValues = new ContentValues();
        bookingValues.put("CustomerId", customerId);
        bookingValues.put("Flightno", flightNo);
        bookingValues.put("DepartureTime", departureTime);
        bookingValues.put("SeatNumber", seatNo);
        db.insert("FlightBookings", null, bookingValues);

        // Delete the corresponding row from the FlightSeats table
        String[] whereArgs = {String.valueOf(seatNo), String.valueOf(flightNo), departureTime};
        db.delete("FlightSeats", "SeatNumber=? AND Flightno=? AND DepartureTime=?", whereArgs);

        db.close();
    }
    public float getRoomPrice(int hotelId, int roomNo) {
        float price = -1.0f; // default value to return if no price found
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT Price FROM RoomsDetails WHERE HotelId = ? AND RoomNo = ?";
        String[] selectionArgs = {String.valueOf(hotelId), String.valueOf(roomNo)};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            price = cursor.getFloat(cursor.getColumnIndex("Price"));
        }
        cursor.close();
        db.close();
        return price;
    }
    public float getFlightPrice(int flightNo, String flightClass) {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT SeatPrice FROM FlightSeats WHERE Flightno = ? AND FlightClass = ?";
        String[] args = {Integer.toString(flightNo), flightClass};

        Cursor cursor = db.rawQuery(query, args);

        float price = -1;

        if (cursor.moveToFirst()) {
            price = cursor.getFloat(cursor.getColumnIndex("SeatPrice"));
        }

        cursor.close();
        db.close();

        return price;
    }


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
