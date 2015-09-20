package com.imakkan.app.SQLiteDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by khalid on 9/16/2015.
 */
public class DBHelper extends SQLiteOpenHelper
{
    private static final String TAG = "DBAdapter"; //used for logging database version changes
    private static final String DATABASE_NAME     = "Imakkan.db";
    private static final String GROUP_TABLE       = "Groups";
    private static final String CATEGORIES_TABLE  = "Categories";
    private static final String USERPROFILE_TABLE = "UserProfile";
    private static final String LOCATION_TABLE    = "Locations";
    private static final int    DATABASE_VERSION  = 2;

    //SQL statement to create database
    private static final String CREATE_LOCATION_TABLE_SQL = "CREATE TABLE IF NOT EXISTS Locations (LocationId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "UserId TEXT NOT NULL,LocLat REAL NOT NULL,LocLong REAL NOT NULL,LocCatId INTEGER NOT NULL,LocPicURI BLOB,IsSynched INTEGER NOT NULL DEFAULT 0," +
            " LocTel TEXT DEFAULT '011-000-0000',LocName TEXT, FOREIGN KEY(UserId) REFERENCES UserProfile ( UserId ), FOREIGN KEY(LocCatId) REFERENCES Categories ( CatId ));";
    private static final String CREATE_GROUP_TABLE_SQL = "CREATE TABLE Groups (GroupId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "GroupName TEXT NOT NULL,IsPublic INTEGER);";
    private static final String CREATE_USERPROFILE_TABLE_SQL = "CREATE TABLE UserProfile (UserId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "UserName TEXT NOT NULL, UserPassword TEXT NOT NULL, GroupId INTEGER, FavoriteLoc INTEGER, FOREIGN KEY(GroupId) REFERENCES Groups ( GroupId ));";
    private static final String CREATE_CATEGORIES_TABLE_SQL = "CREATE TABLE Categories (CatId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "CatName TEXT NOT NULL, IsActive INTEGER NOT NULL DEFAULT 1);";

    private static final String POPULATE_GROUP_TABLE_SQL1 = "INSERT INTO Groups VALUES (1,'العائلة فقط',0);";
    private static final String POPULATE_GROUP_TABLE_SQL2 = "INSERT INTO Groups VALUES (2,'الإصدقاء',0)";
    private static final String POPULATE_GROUP_TABLE_SQL3 = "INSERT INTO Groups VALUES (3,'الزملاء في العمل',0)";
    private static final String POPULATE_CAT_TABLE_SQL1   = "INSERT INTO Categories VALUES (1,'مصارف/بنوك',1);";
    private static final String POPULATE_CAT_TABLE_SQL2   = "INSERT INTO Categories VALUES (2,'محلات جوالات',1);";
    private static final String POPULATE_CAT_TABLE_SQL3   = "INSERT INTO Categories VALUES (3,'محلات مواد بناء',1);";
    private static final String POPULATE_CAT_TABLE_SQL4   = "INSERT INTO Categories VALUES (4,'محلات مواد كهرباء/سباكة',1);";
    private static final String POPULATE_CAT_TABLE_SQL5   = "INSERT INTO Categories VALUES (5,'محلات إلكترونيات/حاسب آلي',1);";
    private static final String POPULATE_CAT_TABLE_SQL6   = "INSERT INTO Categories VALUES (6,'محلات حلويات/شوكولاته',1);";
    private static final String POPULATE_CAT_TABLE_SQL7   = "INSERT INTO Categories VALUES (7,'إستراحات/مراكز ترفيه',1);";
    private static final String POPULATE_CAT_TABLE_SQL8   = "INSERT INTO Categories VALUES (8,'مكاتب سفر وسياحة',1);";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_GROUP_TABLE_SQL);
        db.execSQL(CREATE_CATEGORIES_TABLE_SQL);
        db.execSQL(CREATE_USERPROFILE_TABLE_SQL);
        db.execSQL(CREATE_LOCATION_TABLE_SQL);
        db.execSQL(POPULATE_GROUP_TABLE_SQL1);
        db.execSQL(POPULATE_GROUP_TABLE_SQL2);
        db.execSQL(POPULATE_GROUP_TABLE_SQL3);
        db.execSQL(POPULATE_CAT_TABLE_SQL1);
        db.execSQL(POPULATE_CAT_TABLE_SQL2);
        db.execSQL(POPULATE_CAT_TABLE_SQL3);
        db.execSQL(POPULATE_CAT_TABLE_SQL4);
        db.execSQL(POPULATE_CAT_TABLE_SQL5);
        db.execSQL(POPULATE_CAT_TABLE_SQL6);
        db.execSQL(POPULATE_CAT_TABLE_SQL7);
        db.execSQL(POPULATE_CAT_TABLE_SQL8);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
//        Log.w(TAG, "Upgrading application's database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data!");

        // Destroy old database:
        db.execSQL("DROP TABLE IF EXISTS " + LOCATION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USERPROFILE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + GROUP_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORIES_TABLE);
        // Recreate new database:
        onCreate(db);
    }
}