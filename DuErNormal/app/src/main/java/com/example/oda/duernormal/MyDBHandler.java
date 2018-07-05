package com.example.oda.duernormal;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import java.sql.Statement;

public class MyDBHandler extends SQLiteOpenHelper{
       //information of database
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "statementsDB.db";
        public static final String TABLE_NAME = "Thoughts";
        public static final String COLUMN_ID = "ThoughtID";
        public static final String COLUMN_NAME = "Thought";
        //initialize the database
        public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
         String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_ID +
                 "INTEGER PRIMARYKEY," + COLUMN_NAME + "TEXT )";
         db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

        public String loadHandler() {
             String result = "";
             String query = "Select*FROM" + TABLE_NAME;
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
         values.put(COLUMN_ID, thought.getID());
         values.put(COLUMN_NAME, thought.gettoughttext());
         SQLiteDatabase db = this.getWritableDatabase();
         db.insert(TABLE_NAME, null, values);
         db.close();
        }

        public Thought findHandler(String thoughtText) {

         String query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_NAME + " = " + "'" + thoughtText + "'";
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

        public boolean deleteHandler(int ID) {
         boolean result = false;
         String query = "Select*FROM" + TABLE_NAME + "WHERE" + COLUMN_ID + "= '" + String.valueOf(ID) + "'";
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(query, null);
         Thought thought = new Thought();
         if (cursor.moveToFirst()) {
          thought.setID(Integer.parseInt(cursor.getString(0)));
          db.delete(TABLE_NAME, COLUMN_ID + "=?",
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
         args.put(COLUMN_ID, ID);
         args.put(COLUMN_NAME, name);
         return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
        }

 }
