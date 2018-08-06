package com.example.oda.duernormal;

import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.view.View;
import android.util.Log;
import java.io.File;

import org.w3c.dom.Text;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper{
       //information of database
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "Thoughts_DataBase_Test_new.db";
        public static final String TABLE_NAME = "Thought_DataBase_Test";
        public static final String ID_pri = "ID_primary";
        public static final String ID_cat = "ID_category";
        public static final String COLUMN_1 = "ID_nrincategory";
        public static final String COLUMN_2 = "Stat_Damer";
        public static final String COLUMN_3 = "Stat_Menn";
        public static final String COLUMN_4 = "Thought";

        //initialize the database
        public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
            SQLiteDatabase db = getWritableDatabase();
        }
        public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = getWritableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            File f = new File("/data/data/com.example.oda.duernormal/databases/thoughts_database.db");
            if (f.exists() && !f.isDirectory()) {
                Log.d("Exists", "Table exist already");
            } else {
                String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + ID_pri + " INTEGER PRIMARY KEY, " + ID_cat + " INTEGER, " + COLUMN_1 + " TEXT, " + COLUMN_2 + " FLOAT, " + COLUMN_3 + " FLOAT, " + COLUMN_4 + " TEXT ) ";
                //String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( ThoughtID INTEGER PRIMARY KEY, Thought TEXT) ";
                Log.d("Error?", "In oncreate");
                db.execSQL(CREATE_TABLE);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
         onCreate(db);
         Log.d("Onupgrade", "OnUpgrade Ran");
        }

        public String loadHandler() {
             String result = "";
             String query = "Select*FROM " + TABLE_NAME;
             SQLiteDatabase db = this.getWritableDatabase();
             Cursor cursor = db.rawQuery(query, null);
             while (cursor.moveToNext()){
              int result_0 = cursor.getInt(0);
              String result_1 = cursor.getString(1);
              result += String.valueOf(result_0) + " " + result_1 +
                      System.getProperty("line.separator");
             }
             cursor.close();
             db.close();
             return result;
        }

        public void addHandler(Thought thought) {
         ContentValues values = new ContentValues();
         SQLiteDatabase db = this.getWritableDatabase();;
         values.put(COLUMN_4, thought.gettoughttext());
         values.put(ID_cat, thought.getID_category());
         values.put(COLUMN_1, thought.getID_nrincategory());
         values.put(COLUMN_2, thought.getStatDamer());
         values.put(COLUMN_3, thought.getStatMenn());
         Log.d("Error2", "Print4 in DBHandler");
         db.insert(TABLE_NAME, null, values);
         Log.d("Error2", "ADDED NEW!");
         db.close();
        }

        public Thought findHandler(String thoughtText) {

         String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_4 + " = " + "'" + thoughtText + "'";
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(query, null);
         Thought thought = new Thought();
         if (cursor.moveToFirst()) {
          cursor.moveToFirst();
          thought.setID(Integer.parseInt(cursor.getString(0)));
          thought.setThoughtText(cursor.getString(1));
          cursor.close();
         } else {
          thought = null;
         }
         db.close();
         return thought;
        }
         public String findByID(int ID) {

         String catByID;
         String query = "Select * FROM " + TABLE_NAME + " WHERE " + ID_pri + " = " + "'" + ID + "'";
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(query, null);
         if (cursor.moveToFirst()) {
         cursor.moveToFirst();

         catByID = (cursor.getString(1));
         cursor.close();
         } else {
             catByID = "";
         }
         db.close();
         return catByID;
         }

        public String findStatByCategory(String ID_cat) {

        String statByCat;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + this.ID_cat + " = " + "'" + ID_cat + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();

            statByCat = (cursor.getString(1));
            Log.d("tag in findBy", statByCat);
            cursor.close();
        } else {
            statByCat = "";
        }
        db.close();
        return statByCat;
    }
        Integer row = 0;

    public int find_nr_of_rows_in_database() {
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        boolean isOk = cursor.moveToFirst();
        int count = cursor.getCount();
        db.close();
        cursor.close();

        return count;
    }

    public int find_nr_of_rows_in_database_by_cat(String Category) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + this.ID_cat + " = " + "'" + Category + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        boolean isOk = cursor.moveToFirst();
        int count = cursor.getCount();
        db.close();
        cursor.close();

        return count;
    }

    public int find_nr_of_rows_in_database_by_cat2(String Category) {
        String query = "Select count(*) FROM " + TABLE_NAME + " WHERE " + this.ID_cat + " = " + "'" + Category + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        boolean isOk = cursor.moveToFirst();
        int count = cursor.getInt(0);
        db.close();
        cursor.close();

        return count;
    }

    public String findThoughtByCategory_test(String ID_cat, Integer row_nr) {

        String thoughtByCat;
        Log.isLoggable("ROW NR", row_nr);
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + this.ID_cat + " = " + "'" + ID_cat + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        //if( cursor != null && cursor.moveToFirst() ){
        //}
        //else {Log.d("eic","error in cursor");}
        cursor.moveToPosition(row_nr);
        thoughtByCat = cursor.getString(5);
        cursor.close();
        db.close();
        return thoughtByCat;
    }

    public String findStatWomen(String ID_cat, Integer row_nr) {

        String statWomen = "";
        Log.isLoggable("ROW COUNT", row_nr);
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + this.ID_cat + " = " + "'" + ID_cat + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if( cursor != null && cursor.moveToFirst() ){
            cursor.moveToPosition(row_nr);
        }
        statWomen = cursor.getString(3);
        cursor.close();
        db.close();
        return statWomen;
    }

    public String findStatMen(String ID_cat, Integer row_nr) {

        String statMen = "";
        Log.isLoggable("ROW COUNT", row_nr);
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + this.ID_cat + " = " + "'" + ID_cat + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToPosition(row_nr);
        statMen = cursor.getString(4);
        cursor.close();
        db.close();
        return statMen;
    }
/*
    public String findThoughtByCategory_test(String ID_cat, Integer row_nr) {

        String thoughtByCat;
        Integer rownr = row_nr;
        Log.isLoggable("ROW COUNT", row_nr);
        Log.d("h", "HALLA I ROWZ");
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + this.ID_cat + " = " + "'" + ID_cat + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (rownr==0) {
            cursor.moveToFirst();
            thoughtByCat = (cursor.getString(5));
            cursor.close();
        }
        else if(rownr!=0){
            cursor.moveToNext();
            thoughtByCat = (cursor.getString(5));
            cursor.close();
            Log.d("move", "ARE WE HERE EVER");
        }
        else {
            thoughtByCat = "";
        }
        db.close();
        return thoughtByCat;
        */

    // Most likely my error was caused by this function being a never ending loop
   /* public String findThoughtByCategory(String ID_cat) {

        String thoughttest;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + this.ID_cat + " = " + "'" + ID_cat + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String name = cursor.getString(cursor.getColumnIndex(countyname));
                    //list.add(name);
                    cursor.moveToNext();
                }
            }
*/

    public String findThoughtByCategory(String ID_cat) {

        String thoughttest = "bo";
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + this.ID_cat + " = " + "'" + ID_cat + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                thoughttest = (cursor.getString(5));
                }
            cursor.moveToNext();
            }
            Log.d("tag in findBy", thoughttest);
            cursor.close();
        db.close();
        return thoughttest;
    }

        public boolean deleteHandler(int ID) {
         boolean result = false;
         String query = "Select*FROM" + TABLE_NAME + "WHERE" + ID_pri + "= '" + String.valueOf(ID) + "'";
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(query, null);
         Thought thought = new Thought();
         if (cursor.moveToFirst()) {
          thought.setID(Integer.parseInt(cursor.getString(0)));
          db.delete(TABLE_NAME, ID_pri + "=?",
                  new String[] {
           String.valueOf(thought.getID())
          });
          cursor.close();
          result = true;
         }
         db.close();
         return result;
        }

        public boolean updateHandler(int ID, String name) {
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues args = new ContentValues();
         args.put(ID_pri, ID);
         args.put(COLUMN_4, name);
         return db.update(TABLE_NAME, args, ID_pri + "=" + ID, null) > 0;
        }
        private static boolean doesDatabaseExist(Context context, String dbName) {
            File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
 }
