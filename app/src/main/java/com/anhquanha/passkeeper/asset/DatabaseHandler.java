package com.anhquanha.passkeeper.asset;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhquanha.passkeeper.model.Account;
import com.anhquanha.passkeeper.model.User;

/**
 * Created by anhquan.ha on 3/7/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "passKeeperDb";

    // Contacts table name
    private static final String USER_TABLE = "User";
    private static final String ACCOUNT_TABLE = "Account";

    // User Table Columns names
    private static final String USER_ID_KEY = "id";
    private static final String USER_NAME_KEY = "username";
    private static final String USER_PASS_KEY = "password";

    // Account table columns names
    private static final String ACCOUNT_ID_KEY = "id";
    private static final String ACCOUNT_LOGINID_KEY = "loginId";
    private static final String ACCOUNT_PASS_KEY  = "password";
    private static final String ACCOUNT_CATEGORY_KEY = "category";
    private static final String ACCOUNT_OWNERID_KEY = "ownerId";
    private static final String ACCOUNT_CREATEAT_KEY = "createdAt";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "("
                + USER_ID_KEY + " TEXT PRIMARY KEY," + USER_NAME_KEY + " TEXT,"
                + USER_PASS_KEY + " TEXT" + ")";

        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + ACCOUNT_TABLE + "("
                + ACCOUNT_ID_KEY + " TEXT PRIMARY KEY,"
                + ACCOUNT_LOGINID_KEY + " TEXT,"
                + ACCOUNT_PASS_KEY + " TEXT,"
                + ACCOUNT_CATEGORY_KEY + " TEXT,"
                + ACCOUNT_OWNERID_KEY + " TEXT,"
                + ACCOUNT_CREATEAT_KEY + " TEXT" + ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ACCOUNT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE);

        // Create tables again
        onCreate(db);
    }

    public void createUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID_KEY, user.getId());
        values.put(USER_NAME_KEY, user.getUsername());
        values.put(USER_PASS_KEY, user.getPassword());

        //insert
        db.insert(USER_TABLE, null, values);
        db.close();
    }

    public void createAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ACCOUNT_ID_KEY, account.getId());
        values.put(ACCOUNT_LOGINID_KEY, account.getLoginId());
        values.put(ACCOUNT_PASS_KEY, account.getPassword());
        values.put(ACCOUNT_CATEGORY_KEY, account.getCategory());
        values.put(ACCOUNT_OWNERID_KEY, account.getOwnerId());
        values.put(ACCOUNT_CREATEAT_KEY, account.getCreatedAt());

        db.insert(ACCOUNT_TABLE, null, values);
        db.close();
    }


}
