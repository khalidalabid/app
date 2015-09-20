package com.imakkan.app.SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBAdapter
{

    // DataBase info:
    private static final String TAG = "DBAdapter"; //used for logging database version changes
	public static final String[] ALL_KEYS_LOCATION    = new String[] {"LocationId","UserId","LocLat","LocLong","LocCatId","LocPicURI","IsSynched","LocTel","LocName"};
    public static final String[] ALL_KEYS_CATEGORY    = new String[] {"CatId", "CatName", "IsActive"};
    public static final String[] ALL_KEYS_GROUP       = new String[] {"GroupId", "GroupName","IsPublic"};
    public static final String[] ALL_KEYS_USERPROFILE = new String[] {"UserId", "UserName", "UserPassword","GroupId","FavoriteLoc"};

    private static final String DATABASE_NAME         = "Imakkan.db";
    private static final String GROUP_TABLE           = "Groups";
    private static final String CATEGORIES_TABLE      = "Categories";
    private static final String USERPROFILE_TABLE     = "UserProfile";
    private static final String LOCATION_TABLE        = "Locations";
    private static final int    DATABASE_VERSION      = 2;
	// Database related objects.
	//private final Context context;
	private DBHelper myDBHelper;
	//private SQLiteDatabase db;
    private LocationInfo locInfo;
    private CategoryInfo catInfo;
    private GroupInfo grpInfo;


	public DBAdapter(Context ctx)
    {
		//this.context = ctx;
		myDBHelper = new DBHelper(ctx);
	}
	
	// Open the database connection.
//	public DBAdapter open()
//    {
//        SQLiteDatabase db = myDBHelper.getWritableDatabase();
//		return this;
//	}
	
	// Close the database connection.
//	public void close()
//    {
//		myDBHelper.close();
//	}
	
	// Add a new set of values to be inserted into the user profile  - Registering New User.
	public long insertRow(UserInfo uinfo, LocationInfo linfo, CategoryInfo cinfo)
    {
        long lret =0;
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
		ContentValues initialValues = new ContentValues();
        if (uinfo!=null)
        {
            initialValues.put(ALL_KEYS_USERPROFILE[1], uinfo.getUserName());
            initialValues.put(ALL_KEYS_USERPROFILE[2], uinfo.getUserPassword());
            lret = db.insert(USERPROFILE_TABLE, null, initialValues);
        }
        else if (linfo!=null)
        {
            initialValues.put( ALL_KEYS_LOCATION[1],linfo.getUserId());
            initialValues.put( ALL_KEYS_LOCATION[2],linfo.getLocLat());
            initialValues.put( ALL_KEYS_LOCATION[3],linfo.getLocLong());
            initialValues.put( ALL_KEYS_LOCATION[4],linfo.getLocCatId());
            //initialValues.put( ALL_KEYS_LOCATION[5],linfo.getLocPicURI());
            initialValues.put( ALL_KEYS_LOCATION[6],linfo.getIsSynched());
            initialValues.put( ALL_KEYS_LOCATION[7],linfo.getLocTel());
            initialValues.put( ALL_KEYS_LOCATION[8],linfo.getLocName());
            // Insert the data into the database.
            lret = db.insert(LOCATION_TABLE, null, initialValues);
        }
        else if (cinfo!=null)
        {
            initialValues.put( ALL_KEYS_CATEGORY[1],cinfo.getCategoryName());
            initialValues.put(ALL_KEYS_CATEGORY[2], cinfo.getIsActive());
            // Insert the data into the database.
            lret = db.insert(CATEGORIES_TABLE, null, initialValues);
        }
        db.close();
        return lret;
	}


	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(String aTABLE, String critera)
    {
        boolean lret=false;
        String where = null;
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        if (aTABLE==USERPROFILE_TABLE)	 where ="UserId     = " + critera;
        if (aTABLE==CATEGORIES_TABLE)	 where ="CatName    = " + critera;
        if (aTABLE==GROUP_TABLE)	     where ="GroupName  = " + critera;
        if (aTABLE==LOCATION_TABLE)	     where ="LocationId = " + critera;
        lret = db.delete(aTABLE, where, null) != 0;
        db.close();
        return lret;
	}
	
//	public void deleteAll(String TableName) {
//		Cursor c = getAllRows_LOCATIONS();
//		long rowId = 0; //c.getColumnIndexOrThrow(KEY_ROWID);
//		if (c.moveToFirst()) {
//			do {
//				deleteRow(TableName,c.getLong((int) rowId));
//			} while (c.moveToNext());
//		}
//		c.close();
//	}
	
	// Return all data in the LOCATIONS.
	public Cursor getAllRows(String aTable)
    {

        int i=0;
        Cursor c = null;
        int ftd =0;
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        if (aTable==USERPROFILE_TABLE)  ftd = 1;
        else if (aTable==CATEGORIES_TABLE)   ftd=2;
        else if (aTable==GROUP_TABLE)    ftd =3;
        else if (aTable==LOCATION_TABLE) ftd =4;
        //c = db.query(true, aTable, ALL_KEYS_USERPROFILE, null, null, null, null, null, null);

        if (ftd == 1)
        {
             c = 	db.query(true, aTable, ALL_KEYS_USERPROFILE, null, null, null, null, null, null);
            if (c != null) {
                c.moveToFirst();
            }
            db.close();
            //return c;
        }
        else if (ftd == 2)
        {
             c = 	db.query(true, aTable, ALL_KEYS_CATEGORY, null, null, null, null, null, null);
            if (c != null) {
                c.moveToFirst();
            }
            db.close();
            //return c;
        }
        else if (ftd == 3)
        {
             c = 	db.query(true, aTable, ALL_KEYS_GROUP, null, null, null, null, null, null);
            if (c != null) {
                c.moveToFirst();
            }
            db.close();
            //return c;
        }
        if (ftd == 4)
        {
             c = 	db.query(true, aTable, ALL_KEYS_LOCATION, null, null, null, null, null, null);
            if (c != null) {
                c.moveToFirst();
            }
            db.close();
           // return c;
        }
        return c;
	}

    // Return all data in the CATEGORIES.
//    public Cursor getAllRows_CATEGORIES() {
//        String where = null;
//        SQLiteDatabase db = myDBHelper.getReadableDatabase();;
//        //String[] FieldsToDisplay =new String[] {"CatName"};
//        Cursor c = 	db.query(true, "Categories", ALL_KEYS_CATEGORY, where, null, null, null, null, null);
//        if (c != null) {
//            c.moveToFirst();
//        }
//        db.close();
//        return c;
//    }
//	// Get a specific row (by rowId)
//	public Cursor getRow(String uname) {
//		String where = "UserName=" + uname;
//        String[] FieldsToDisplay =new String[] {"CatName"};
//		Cursor c = 	db.query(true, GROUP_TABLE, FieldsToDisplay,
//                where, null, null, null, null, null);
//		if (c != null) {
//			c.moveToFirst();
//		}
//		return c;
//	}
    // Check if a user exists
    public String UserLookup(String uname)
    {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        String selectQuery = "SELECT  UserName,UserPassword FROM " + USERPROFILE_TABLE +" WHERE UserName = '" + uname +"'";

        Cursor cursor = db.rawQuery(selectQuery,null);
        String u,p ;
        p ="not found";
        if (cursor.moveToFirst())
        {
            do
            {
                u = cursor.getString(0);
                if (u.equals(uname))
                {
                    p = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return p;
    }

    public String RegisterNewUser(UserInfo CurrentUserInfo)
    {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ALL_KEYS_USERPROFILE[1], CurrentUserInfo.getUserName());
        values.put(ALL_KEYS_USERPROFILE[2], CurrentUserInfo.getUserPassword());
        values.put(ALL_KEYS_USERPROFILE[3], CurrentUserInfo.getGroupId());
        if (db.insert("UserProfile", null, values)>0)
        {
            db.close();
            return "New";
        }
        else
        {
            db.close();
            return "Existing";
        }
    }
	// Change an existing row to be equal to new data.
//	public int updateRow(String TableName, long rowId, String task, String date)
//    {
//        SQLiteDatabase db = myDBHelper.getWritableDatabase();
//		String where = null; //KEY_ROWID + "=" + rowId;
//        int lret;
//		ContentValues newValues = new ContentValues();
//		//newValues.put(KEY_TASK, task);
//		//newValues.put(KEY_DATE, date);
//		// Insert it into the database.
//        lret = db.update(TableName, newValues, where, null);
//        return lret;
//	}

    //Retrieve all UserGroups records and populate into List<String>
    public List<String> getallUserGroups() {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                ALL_KEYS_GROUP[1] +
                " FROM " + GROUP_TABLE;

        List<String> GroupList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Integer i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GroupList.add(i,cursor.getString(cursor.getColumnIndex(ALL_KEYS_GROUP[1])));
                i+=1;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return GroupList;

    }
    //Retrieve all UserGroups records and populate into List<String>
    public List<String> getallLocationCategories() {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                ALL_KEYS_CATEGORY[1] +
                " FROM " + CATEGORIES_TABLE;

        List<String> CategoryList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Integer i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CategoryList.add(i,cursor.getString(cursor.getColumnIndex(ALL_KEYS_CATEGORY[1])));
                i+=1;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return CategoryList;

    }

}

