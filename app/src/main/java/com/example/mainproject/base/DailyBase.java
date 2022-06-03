package com.example.mainproject.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mainproject.ui.dashboard.BarExpenses;

import java.util.ArrayList;

public class DailyBase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION3 = 6;
    public static final String DATABASE_NAME3 = "Third.db";

    public static final String TABLE_NAME3 = "daily_expenses";
    public static final String COLUMN_ID3 = "_id";
    public static final String COLUMN_EXPENSE = "expense";
    public static final String COLUMN_DATE = "date";

    public DailyBase(Context context) {
        super(context, DATABASE_NAME3, null, DATABASE_VERSION3);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME3 +
                " (" + COLUMN_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " text, " +
                COLUMN_EXPENSE + " INTEGER);";


        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(sqLiteDatabase);

    }

    public void dayExpenses(String date, int expenses) {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase dbread = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        Cursor cursor = dbread.rawQuery("select * from daily_expenses", null);
        cursor.moveToLast();
        if (cursor.getPosition() == -1) {
            cv.put(COLUMN_DATE, date);
            cv.put(COLUMN_EXPENSE, expenses);
            db.insert(TABLE_NAME3, null, cv);
        } else if (!date.equals(cursor.getString(1))) {
            cv.put(COLUMN_DATE, date);
            cv.put(COLUMN_EXPENSE, expenses);
            db.insert(TABLE_NAME3, null, cv);
        } else {
            cv.put(COLUMN_EXPENSE, cursor.getInt(2) + expenses);
            db.update(TABLE_NAME3, cv, "date = ?", new String[]{date});
        }
    }

    public ArrayList<BarExpenses> getDailyExpenses(){
        SQLiteDatabase SqlObj = getReadableDatabase();
        ArrayList<BarExpenses> entries = new ArrayList<>();
        Cursor expCurs = SqlObj.rawQuery("select * from daily_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()) {
                String date = expCurs.getString(1);
                int expenses = expCurs.getInt(2);
                entries.add(new BarExpenses(date, expenses));
            }

        }
        return entries;
    }
}
