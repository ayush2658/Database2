package com.brillicaservices.database2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Databasehelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "student_db";


    private static final String TABLE_NAME = "student_record";
    private static final String STUDENT_NAME = "student_name";
    private static final String STUDENT_ID = "student_id";
    private static final String STUDENT_COLLEGE = "student_college";
    private static final String STUDENT_FEES = "student_fees";
    private static final String STUDENT_PHONE_NUMBER = "student_phone";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            STUDENT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " TEXT, " +
            STUDENT_COLLEGE + " TEXT, " + STUDENT_FEES
            + " TEXT, " + STUDENT_PHONE_NUMBER + " INTEGER ); ";

    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }


    public long addNewStudent(Studentmodel studentmodel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(STUDENT_NAME, studentmodel.name);
        contentValues.put(STUDENT_COLLEGE, studentmodel.collegeName);
        contentValues.put(STUDENT_FEES, studentmodel.fees);
        contentValues.put(STUDENT_PHONE_NUMBER, studentmodel.phoneNumber);

        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        sqLiteDatabase.close();

        return id;
    }

    public Studentmodel getSingleStudentDetails(long id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{STUDENT_ID, STUDENT_NAME, STUDENT_COLLEGE, STUDENT_FEES,
                        STUDENT_PHONE_NUMBER}, STUDENT_ID + "=?", new String[]{String.valueOf(id)}, null, null,
                null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Studentmodel studentModel = new Studentmodel(cursor.getInt(cursor.getColumnIndex(STUDENT_ID)),
                cursor.getString(cursor.getColumnIndex(STUDENT_NAME)), cursor.getString(cursor.getColumnIndex(STUDENT_COLLEGE)),
                cursor.getDouble(cursor.getColumnIndex(STUDENT_FEES)), cursor.getLong(cursor.getColumnIndex(STUDENT_PHONE_NUMBER)));

        cursor.close();

        return studentModel;
    }

    public List<Studentmodel> allStudentsDetails() {
        List<Studentmodel> studentsList = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + TABLE_NAME ;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Studentmodel studentmodel = new Studentmodel();
                studentmodel.setId(cursor.getInt(cursor.getColumnIndex(STUDENT_ID)));
                studentmodel.setName(cursor.getString(cursor.getColumnIndex(STUDENT_NAME)));
                studentmodel.setCollegeName(cursor.getString(cursor.getColumnIndex(STUDENT_COLLEGE)));
                studentmodel.setFees(cursor.getDouble(cursor.getColumnIndex(STUDENT_FEES)));
                studentmodel.setPhoneNumber(cursor.getLong(cursor.getColumnIndex(STUDENT_PHONE_NUMBER)));

                studentsList.add(studentmodel);
            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return  studentsList;
    }

    public int getStudentsCount() {

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        int totalStudentsCount = cursor.getCount();
        cursor.close();

        return totalStudentsCount;
    }

    public int updateIndividualStudentDetails(Studentmodel studentmodel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, studentmodel.getName());
        values.put(STUDENT_COLLEGE, studentmodel.getCollegeName());
        values.put(STUDENT_FEES, studentmodel.getFees());
        values.put(STUDENT_PHONE_NUMBER, studentmodel.getPhoneNumber());

        // updating row
        return sqLiteDatabase.update(TABLE_NAME, values, STUDENT_ID + " = ?",
                new String[]{String.valueOf(studentmodel.getId())});
    }

}
