package com.example.glenn.lathalanavigation;

/**
 * Created by Glenn on 3/13/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String SCHEMA = "user";

    public DatabaseOpenHelper(Context context) {
        super(context, SCHEMA, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create tables here
        // CREATE TABLE note (_id INTEGER PRIMARY KEY AUTOINCREMENT,
        //  title TEXT,

        String sql = "CREATE TABLE "
                + User.TABLE_NAME + " ( "
                + User.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + User.COLUMN_NAME + " TEXT,"
                + User.COLUMN_USERNAME + " TEXT,"
                + User.COLUMN_PASSWORD + " TEXT);";
        //+Note.COLUMN_URL+" TEXT);"

        db.execSQL(sql);


        String sql2 = "INSERT INTO " + User.TABLE_NAME + " ( " + User.COLUMN_ID + " , " +User.COLUMN_NAME + " , " +User.COLUMN_USERNAME + " , " + User.COLUMN_PASSWORD+ " ) VALUES (1, 'Teemo', 'username', 'password');" ;

        //String sql2 = "INSERT INTO user( _id, username, password) VALUES (1, username, password)" ;

        db.execSQL(sql2);
//        User defaultUser = new User("projectlathala", "mobapde123");
//        addUser(defaultUser);
    }

    //add
    public long addUser(User u) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(u.COLUMN_USERNAME, u.getUsername());
        cv.put(u.COLUMN_PASSWORD, u.getPassword());
        cv.put(u.COLUMN_NAME, u.getName());
        //cv.put(Note.COLUMN_URL, n.getURL());
        long id = db.insert(u.TABLE_NAME, null, cv);
        db.close();
        return id;
    }

    //get
    public User getUser(int id) {
        User u = new User();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(User.TABLE_NAME, null, " " + User.COLUMN_ID + "=? ", new String[]{String.valueOf(id)}, null, null, null);
        if (c.moveToFirst()) {
            u.setId(c.getInt(c.getColumnIndex(User.COLUMN_ID)));
            u.setUsername(c.getString(c.getColumnIndex(User.COLUMN_USERNAME)));
            u.setPassword(c.getString(c.getColumnIndex(User.COLUMN_PASSWORD)));
            //n.setURL(c.getString(c.getColumnIndex(Note.COLUMN_URL)));
        } else {
            u = null;
        }

        c.close();
        db.close();
        return u;
    }

    //getAllNotes
    public Cursor getAllUsers() {
        /*Note n = new Note();
        ArrayList<Note> notes = new ArrayList<>();*/
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(User.TABLE_NAME, null, null, null, null, null, null);
        /*if(c.moveToFirst()){
            n.setId(c.getInt(c.getColumnIndex(Note.COLUMN_ID)));
            n.setTitle(c.getString(c.getColumnIndex(Note.COLUMN_TITLE)));
            n.setNote(c.getString(c.getColumnIndex(Note.COLUMN_NOTE)));
        }
        else{
            n= null;
        }*//*

        c.close();
        db.close();*/
        return c;
        //don't close db and cursor
    }

    //check if User is in db
    public User checkifUserExists(String username) {
        User u = new User();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(User.TABLE_NAME, null, " " + User.COLUMN_USERNAME + "=? ", new String[]{username}, null, null, null);
        if (c.moveToFirst()) {
            u.setId(c.getInt(c.getColumnIndex(User.COLUMN_ID)));
            u.setUsername(c.getString(c.getColumnIndex(User.COLUMN_USERNAME)));
            u.setPassword(c.getString(c.getColumnIndex(User.COLUMN_PASSWORD)));
            //n.setURL(c.getString(c.getColumnIndex(Note.COLUMN_URL)));
        } else {
            u = null;
        }

        c.close();
        db.close();
        return u;
    }

    //editNote
    //I don't think we can do this without
    public int editUser(User u) {
        //Note n = new Note();
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(Expense.COLUMN_TITLE, e.getName());
        cv.put(User.COLUMN_PASSWORD, u.getPassword());
        //cv.put(Note.COLUMN_URL, n.getURL());
        int userID = db.update(u.TABLE_NAME, cv, " " + User.COLUMN_ID + "=? ", new String[]{String.valueOf(u.getId())});
        db.close();
        return userID;
    }

    //deleteNote
   /* public int deleteUser(Expense e){
        SQLiteDatabase db = getWritableDatabase();
        int rowDelete = db.delete(Expense.TABLE_NAME, " " + Expense.COLUMN_ID+"=? ", new String[]{String.valueOf(e.getID())});
        db.close();
        return rowDelete;
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
