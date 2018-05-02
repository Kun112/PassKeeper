package com.anhquanha.passkeeper.asset;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.anhquanha.passkeeper.constant.Const;
import com.anhquanha.passkeeper.model.Account;
import com.anhquanha.passkeeper.model.User;

import java.util.ArrayList;
import java.util.List;

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

    Context context;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "("
                + USER_ID_KEY + " TEXT PRIMARY KEY," + USER_NAME_KEY + " TEXT,"
                + USER_PASS_KEY + " TEXT" + ")";

        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + ACCOUNT_TABLE + "("
                + ACCOUNT_ID_KEY + " TEXT PRIMARY KEY AUTOINCREMENT,"
                + ACCOUNT_LOGINID_KEY + " TEXT,"
                + ACCOUNT_PASS_KEY + " TEXT,"
                + ACCOUNT_CATEGORY_KEY + " TEXT,"
                + ACCOUNT_OWNERID_KEY + " TEXT,"
                + ACCOUNT_CREATEAT_KEY + " TEXT" + ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ACCOUNT_TABLE);
        Log.d(Const.APP_NAME, "Create table successfully!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE);
        Log.d(Const.APP_NAME, "Drop table successfully!");
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

    public User getUser(String idUser){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(USER_TABLE, null, USER_ID_KEY+ " = ?", new String[] { String.valueOf(idUser) },null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }else{
            return null;
        }
        User student = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        db.close();
        return student;
    }


    public boolean checkExistUser(String idUser){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        String checkQuery = "SELECT " + USER_ID_KEY + " FROM " + USER_TABLE + " WHERE " + USER_ID_KEY + "= '"+ idUser + "'";
        cursor= db.rawQuery(checkQuery,null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return exists;
    }

    public void createAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(ACCOUNT_ID_KEY, account.getId());
        values.put(ACCOUNT_LOGINID_KEY, account.getLoginId());
        values.put(ACCOUNT_PASS_KEY, account.getPassword());
        values.put(ACCOUNT_CATEGORY_KEY, account.getCategory());
        values.put(ACCOUNT_OWNERID_KEY, account.getOwnerId());
        values.put(ACCOUNT_CREATEAT_KEY, account.getCreatedAt());

        db.insert(ACCOUNT_TABLE, null, values);
        db.close();
    }

    public List<Account> getAccountsDependOnOwner(String ownerId){
        List<Account> accountList = new ArrayList<>();
        String query = "SELECT * FROM " + ACCOUNT_TABLE + " WHERE " + ACCOUNT_OWNERID_KEY + "= '"+ ownerId + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Account account = new Account(cursor.getString(cursor.getColumnIndex(ACCOUNT_LOGINID_KEY)),
                    cursor.getString(cursor.getColumnIndex(ACCOUNT_PASS_KEY)),
                    cursor.getString(cursor.getColumnIndex(ACCOUNT_CATEGORY_KEY)),
                    cursor.getString(cursor.getColumnIndex(ACCOUNT_OWNERID_KEY)),
                    cursor.getString(cursor.getColumnIndex(ACCOUNT_CREATEAT_KEY)));
            //Account account = new Account(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4));
            accountList.add(account);
            cursor.moveToNext();
        }
        return accountList;
    }


    public User getSinlgeEntry(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String checkQuery = "SELECT " + "*" + " FROM " + USER_TABLE + " WHERE " + USER_ID_KEY + "= '"+ userId + "'";
        Cursor cursor = null;
        cursor= db.rawQuery(checkQuery,null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            return null;
        }else{
            cursor.moveToFirst();
            //String password = cursor.getString(cursor.getColumnIndex(USER_PASS_KEY));
            String password = cursor.getString(2);
            String userName = cursor.getString(1);
            cursor.close();
            return new User(userId, userName, password);

        }

    }

}
