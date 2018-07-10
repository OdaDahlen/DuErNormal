package com.example.oda.duernormal;

import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;
import android.util.Log;
import java.io.File;

import org.w3c.dom.Text;

import java.sql.Statement;

public class MyDBHandler extends SQLiteOpenHelper{
       //information of database
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "statementsDB.db";
        public static final String TABLE_NAME = "Thoughts_table";
        public static final String ID_pri = "ThoughtID";
        public static final String COLUMN_NAME = "Thought_Text";

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
            File f = new File("/data/data/com.example.oda.duernormal/databases/thoughs_database.db");
            if (f.exists() && !f.isDirectory()) {
                Log.d("Exists", "Table exist already");
            } else {
                String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + ID_pri + " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT) ";
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
         values.put(COLUMN_NAME, thought.gettoughttext());
         Log.d("Error2", "Print4 in DBHandler");
         db.insert(TABLE_NAME, null, values);
         Log.d("Error2", "ADDED NEW!");
         db.close();
        }

        public Thought findHandler(String thoughtText) {

         String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + "'" + thoughtText + "'";
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

         String thoughttest;
         String query = "Select * FROM " + TABLE_NAME + " WHERE " + ID_pri + " = " + "'" + ID + "'";
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(query, null);
         if (cursor.moveToFirst()) {
         cursor.moveToFirst();

         thoughttest = (cursor.getString(1));
         cursor.close();
         } else {
             thoughttest = "";
         }
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
         args.put(COLUMN_NAME, name);
         return db.update(TABLE_NAME, args, ID_pri + "=" + ID, null) > 0;
        }
        private static boolean doesDatabaseExist(Context context, String dbName) {
            File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
 }
