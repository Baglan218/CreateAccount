package com.example.registration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoreDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "registration.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "user_store";

    public static final String COLUMN_INFO = "user_full_name";
    public static final String COLUMN_EMAIL = "user_email";
    public static final String COLUMN_PASSWORD = "user_password";
    public static final String COLUMN_GROUP = "user_group";
    public static final String COLUMN_PHONE = "user_phone";

    Context context;
    public StoreDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_USER+"("+
                COLUMN_INFO+"TEXT," +
                COLUMN_EMAIL +"TEXT,"+
                COLUMN_PASSWORD+"TEXT,"+
                COLUMN_GROUP+"TEXT,"+
                COLUMN_PHONE+"TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        onCreate(db);
    }




}