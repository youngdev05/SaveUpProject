package com.example.mainproject.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.mainproject.R;
import com.example.mainproject.ui.dashboard.BarExpenses;
import com.example.mainproject.ui.notifications.NotificationsFragment;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarEntry;

import java.util.ArrayList;

public class Base extends SQLiteOpenHelper {

    Context context;
    NotificationsFragment nf;
    int spinnerPosition;

    public static final int DATABASE_VERSION = 22;
    public static final String DATABASE_NAME = "FirstDB.db";

    public static final String TABLE_NAME1 = "my_expenses";
    public static final String COLUMN_ID1 = "_id";
    public static final String COLUMN_DATE1 = "date";
    public static final String COLUMN_CATEGORY1 = "category";
    public static final String COLUMN_EXP1 = "expenses";


    public Base(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME1 +
                " (" + COLUMN_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE1 + " text, " +
                COLUMN_CATEGORY1 + " text, " +
                COLUMN_EXP1 + " INTEGER);";


        db.execSQL(query);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);

    }

    public void addC(String date, String categories, int expenses) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE1, date);
        cv.put(COLUMN_CATEGORY1, categories);
        cv.put(COLUMN_EXP1, expenses);
        long res = db.insert(TABLE_NAME1, null, cv);
        if (res == -1) {
            Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
        }
    }



    public ArrayList<DBViewer> getAllData() {
        try {
            ArrayList<DBViewer> DBViewerArrayList = new ArrayList<>();
            SQLiteDatabase objSqliteDatabase = getReadableDatabase();
            if (objSqliteDatabase != null) {
                Cursor objCursor = objSqliteDatabase.rawQuery("select * from my_expenses", null);
                //Cursor buttonCursor = objSqliteDatabase.rawQuery("select answer from answers where ID_question =" + id, null)
                if (objCursor.getCount() != 0) {
                    while (objCursor.moveToNext()) {
                        int id = objCursor.getInt(0);
                        String date = objCursor.getString(1);
                        String category = objCursor.getString(2);
                        int expenses = objCursor.getInt(3);
                        DBViewerArrayList.add(new DBViewer(id, date, category, expenses));
                    }

                    return DBViewerArrayList;
                } else {
                    Toast.makeText(context, "No data is retrieved...", Toast.LENGTH_SHORT).show();
                    return null;
                }
            } else {
                Toast.makeText(context, "Data is null ;)", Toast.LENGTH_SHORT).show();
                return null;
            }
        } catch (Exception e) {
            Toast.makeText(context, "getAllData:-" + e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public ArrayList<PieEntry> getAllPieExpenses() {
        SQLiteDatabase SqlObj = getReadableDatabase();
        ArrayList<PieEntry> entries = new ArrayList<>();
        Cursor expCurs = SqlObj.rawQuery("select * from my_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()) {
                int expenses = expCurs.getInt(3);
                entries.add(new PieEntry(expenses));
            }
        }
        return entries;
    }

    public ArrayList<BarEntry> getAllBarExpenses() {
        SQLiteDatabase SqlObj = getReadableDatabase();
        int x = 0;
        ArrayList<BarEntry> entries = new ArrayList<>();
        Cursor expCurs = SqlObj.rawQuery("select * from my_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()) {
                int expenses = expCurs.getInt(3);
                entries.add(new BarEntry(x, expenses));
                x += 1;
            }
        }
        return entries;
    }



    public int getFoodExpenses(){
        SQLiteDatabase SqlObj = getReadableDatabase();
        int foodExp = 0;
        Cursor expCurs = SqlObj.rawQuery("select * from my_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()){
                int expenses = expCurs.getInt(3);
                String category = expCurs.getString(2);
                if (category.equals("Питание")){
                    foodExp += expenses;
                }
            }
        }
        return foodExp;
    }


    public int getTransExpenses(){
        SQLiteDatabase SqlObj = getReadableDatabase();
        int transExp = 0;
        Cursor expCurs = SqlObj.rawQuery("select * from my_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()){
                int expenses = expCurs.getInt(3);
                String category = expCurs.getString(2);
                if (category.equals("Транспорт")){
                    transExp += expenses;
                }
            }
        }
        return transExp;
    }

    public int getClothExpenses(){
        SQLiteDatabase SqlObj = getReadableDatabase();
        int foodExp = 0;
        Cursor expCurs = SqlObj.rawQuery("select * from my_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()){
                int expenses = expCurs.getInt(3);
                String category = expCurs.getString(2);
                if (category.equals("Одежда")){
                    foodExp += expenses;
                }
            }
        }
        return foodExp;
    }

    public int getEducExpenses(){
        SQLiteDatabase SqlObj = getReadableDatabase();
        int foodExp = 0;
        Cursor expCurs = SqlObj.rawQuery("select * from my_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()){
                int expenses = expCurs.getInt(3);
                String category = expCurs.getString(2);
                if (category.equals("Учеба")){
                    foodExp += expenses;
                }
            }
        }
        return foodExp;
    }

    public int getOthersExpenses(){
        SQLiteDatabase SqlObj = getReadableDatabase();
        int foodExp = 0;
        Cursor expCurs = SqlObj.rawQuery("select * from my_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()){
                int expenses = expCurs.getInt(3);
                String category = expCurs.getString(2);
                if (category.equals("Другое")){
                    foodExp += expenses;
                }
            }
        }
        return foodExp;
    }

    public int getPurchExpenses(){
        SQLiteDatabase SqlObj = getReadableDatabase();
        int foodExp = 0;
        Cursor expCurs = SqlObj.rawQuery("select * from my_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()){
                int expenses = expCurs.getInt(3);
                String category = expCurs.getString(2);
                if (category.equals("Непредвиденные расходы")){
                    foodExp += expenses;
                }
            }
        }
        return foodExp;
    }

    public int getHouseExpenses(){
        SQLiteDatabase SqlObj = getReadableDatabase();
        int foodExp = 0;
        Cursor expCurs = SqlObj.rawQuery("select * from my_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()){
                int expenses = expCurs.getInt(3);
                String category = expCurs.getString(2);
                if (category.equals("Дом")){
                    foodExp += expenses;
                }
            }
        }
        return foodExp;
    }

    public int getEnterExpenses(){
        SQLiteDatabase SqlObj = getReadableDatabase();
        int foodExp = 0;
        Cursor expCurs = SqlObj.rawQuery("select * from my_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()){
                int expenses = expCurs.getInt(3);
                String category = expCurs.getString(2);
                if (category.equals("Развлечения")){
                    foodExp += expenses;
                }
            }
        }
        return foodExp;
    }




    public ArrayList<Integer> getColors() {
        SQLiteDatabase SblObj = getReadableDatabase();
        ArrayList<Integer> colors = new ArrayList<>();
        Cursor expCurs = SblObj.rawQuery("select * from my_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()) {
                String category = expCurs.getString(2);
                switch (category) {
                    case "Питание":
                        colors.add(context.getResources().getColor(R.color.orange));
                        break;
                    case "Транспорт":
                        colors.add(context.getResources().getColor(R.color.green));
                        break;
                    case "Развлечения":
                        colors.add(context.getResources().getColor(R.color.pink));
                        break;
                    case "Одежда":
                        colors.add(context.getResources().getColor(R.color.blue));
                        break;
                    case "Дом":
                        colors.add(context.getResources().getColor(R.color.yellow));
                        break;
                    case "Непредвиденные расходы":
                        colors.add(context.getResources().getColor(R.color.red));
                        break;
                    case "Другое":
                        colors.add(context.getResources().getColor(R.color.violet));
                        break;
                    case "Образование":
                        colors.add(context.getResources().getColor(R.color.white));
                        break;
                }
            }
        }
        return colors;
    }

    public int sumExpenses(){
        SQLiteDatabase SqlObj = getReadableDatabase();
        int sum = 0;
        Cursor expCurs = SqlObj.rawQuery("select * from my_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()) {
                int expenses = expCurs.getInt(3);
                sum += expenses;
            }
        }
        return sum;
    }

//    public ArrayList<DBViewer> getNeededData(){
//        try {
//            ArrayList<DBViewer> DBViewerArrayList = new ArrayList<>();
//            SQLiteDatabase objSqliteDatabase = getReadableDatabase();
//            spinnerPosition = nf.getSpinnerPosition();
//            if (objSqliteDatabase != null) {
//                Cursor objCursor = objSqliteDatabase.rawQuery("select * from my_expenses", null);
//                if (objCursor.getCount() != 0) {
//                    while (objCursor.moveToNext()) {
//                        int id = objCursor.getInt(0);
//                        String date = objCursor.getString(1);
//                        String category = objCursor.getString(2);
//                        int expenses = objCursor.getInt(3);
//                        DBViewerArrayList.add(new DBViewer(id, date, category, expenses));
//                    }
//
//                    return DBViewerArrayList;
//                } else {
//                    Toast.makeText(context, "No data is retrieved...", Toast.LENGTH_SHORT).show();
//                    return null;
//                }
//            } else {
//                Toast.makeText(context, "Data is null ;)", Toast.LENGTH_SHORT).show();
//                return null;
//            }
//        } catch (Exception e) {
//            Toast.makeText(context, "getAllData:-" + e.getMessage(), Toast.LENGTH_SHORT).show();
//            return null;
//        }
//    }



    public ArrayList<RadarEntry> getAllRadarExpenses() {
        SQLiteDatabase SqlObj = getReadableDatabase();
        ArrayList<RadarEntry> entries = new ArrayList<>();
        Cursor expCurs = SqlObj.rawQuery("select * from my_expenses", null);
        if (expCurs.getCount() != 0) {
            while (expCurs.moveToNext()) {
                int expenses = expCurs.getInt(3);
                entries.add(new RadarEntry(expenses));
            }
        }
        return entries;
    }



}


