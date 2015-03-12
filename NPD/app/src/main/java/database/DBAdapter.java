package database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	static final String DATABASE_NAME = ".db";
	static final int DATABASE_VERSION = 2;
	
	public static final String TABLE_LOGIN = "LOGIN";
	public static final String TABLE_VIDEOS = "VIDEOS";
	public static final String TABLE_IMAGES = "IMAGES";
	public static final String TABLE_MEMBERS = "MEMBERS";
	
	//Login table columns
    public static final String LOGIN_COLUMN_ID = "ID";
    public static final String LOGIN_COLUMN_USERNAME = "USERNAME";
    public static final String LOGIN_COLUMN_PASSWORD = "PASSWORD";
    public static final String LOGIN_COLUMN_EMAIL = "EMAIL";
    public static final String INFO_COLUMN_FIRSTNAME = "FIRSTNAME";
    public static final String INFO_COLUMN_LASTNAME = "LASTNAME";
    public static final String INFO_COLUMN_PIN = "PIN";
    public static final String INFO_COLUMN_FIRSTLOGIN = "FIRST_LOGIN";
    
    //VitalSigns table columns
    public static final String IMAGES_COLUMN_ID = "ID";
    public static final String IMAGES_COLUMN_IMAGEID = "IMAGEID";
    public static final String IMAGES_COLUMN_DATE = "DATE";
    
    //Medication table columns
    public static final String VIDEOS_COLUMN_ID = "ID";
    public static final String VIDEOS_COLUMN_VIDEOID = "VIDEOID";
    public static final String VIDEOS_COLUMN_DATE = "DATE";
    
    //Doctors table columns
    public static final String MEMBERS_COLUMN_ID = "ID";
    public static final String MEMBERS_COLUMN_MEMBERS_NAME = "DOCTOR_NAME";
    public static final String MEMBERS_COLUMN_MEMBERS_PHONE = "DOCTOR_PHONE";
    public static final String MEMBERS_COLUMN_MEMBERS_EMAIL = "DOCTOR_EMAIL";
    public static final String MEMBERS_COLUMN_MEMBERS_HOUSENUM = "DOCTOR_HOUSENUM";
    public static final String MEMBERS_COLUMN_LOGINID = "LOGIN_ID";
    

    public static String[] LOGIN_ALLCOLUMNS= {LOGIN_COLUMN_ID,LOGIN_COLUMN_USERNAME,LOGIN_COLUMN_PASSWORD,LOGIN_COLUMN_EMAIL,
    											INFO_COLUMN_FIRSTNAME,INFO_COLUMN_LASTNAME,INFO_COLUMN_PIN};
    
    public static String[] IMAGES_ALLCOLUMNS= {IMAGES_COLUMN_ID,IMAGES_COLUMN_IMAGEID,IMAGES_COLUMN_DATE};
    
    public static String[] VIDEOS_ALLCOLUMNS= {VIDEOS_COLUMN_ID,VIDEOS_COLUMN_VIDEOID, VIDEOS_COLUMN_DATE};
    
    public static String[] MEMBERS_ALLCOLUMNS= {MEMBERS_COLUMN_ID,MEMBERS_COLUMN_MEMBERS_NAME, MEMBERS_COLUMN_MEMBERS_PHONE, MEMBERS_COLUMN_MEMBERS_EMAIL,MEMBERS_COLUMN_MEMBERS_HOUSENUM, MEMBERS_COLUMN_LOGINID};
	
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String CREATE_TABLE_LOGIN = " create table " 
    		+ TABLE_LOGIN + "("
    		+ LOGIN_COLUMN_ID + " integer primary key autoincrement, " 
    		+ INFO_COLUMN_FIRSTNAME + " text, " 
			+ INFO_COLUMN_LASTNAME + " text, "
			+ INFO_COLUMN_FIRSTLOGIN + " numeric default 1, "
			+ INFO_COLUMN_PIN + " text, " 
    		+ LOGIN_COLUMN_USERNAME + " text, "
    		+ LOGIN_COLUMN_EMAIL + " text, "
    		+ LOGIN_COLUMN_PASSWORD + " text);";
    
    static final String CREATE_TABLE_IMAGES = " create table " 
    		+ TABLE_IMAGES + "("
    		+ IMAGES_COLUMN_ID + " integer primary key autoincrement, " 
			+ IMAGES_COLUMN_IMAGEID + " numeric default 1, "
    		+ IMAGES_COLUMN_DATE + " date );";
    
    static final String CREATE_TABLE_VIDEOS = " create table " 
    		+ TABLE_VIDEOS + "("
    		+ VIDEOS_COLUMN_ID + " integer primary key autoincrement, " 
    		+ VIDEOS_COLUMN_VIDEOID + " text, "
    		+ VIDEOS_COLUMN_DATE + " date );";
    
    static final String CREATE_TABLE_MEMBERS = " create table " 
    		+ TABLE_MEMBERS + "("
    		+ MEMBERS_COLUMN_ID + " integer primary key autoincrement, " 
    		+ MEMBERS_COLUMN_MEMBERS_NAME + " text, " 
			+ MEMBERS_COLUMN_MEMBERS_PHONE + " text, "
			+ MEMBERS_COLUMN_MEMBERS_EMAIL + " text, " 
			+ MEMBERS_COLUMN_MEMBERS_HOUSENUM + " text, "
    		+ MEMBERS_COLUMN_LOGINID + " integer);";
	
	// Variable to hold the database instance
	public SQLiteDatabase db;
	// Context of the application using the database.
	public final Context context;
	// Database open/upgrade helper
	private DataBaseHelper dbHelper;

	public DBAdapter(Context _context) {
		context = _context;
		dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);
	}

	public DBAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		db.close();
	}

	public SQLiteDatabase getDatabaseInstance() {
		
		return db;
	}
}
