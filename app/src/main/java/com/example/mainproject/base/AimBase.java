package com.example.mainproject.base;

import static com.example.mainproject.base.Base.DATABASE_NAME;
import static com.example.mainproject.base.Base.DATABASE_VERSION;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class AimBase extends SQLiteOpenHelper {

    Context context;

    public static final int DATABASE_VERSION1 = 6;
    public static final String DATABASE_NAME1 = "SecondDB.db";

    public static final String TABLE_NAME2 = "my_aims";
    public static final String COLUMN_ID2 = "aim_id";
    public static final String COLUMN_AIM2 = "aim";
    public static final String COLUMN_NEEDED_MONEY2 = "needed_money";
    public static final String COLUMN_CURRENT_MONEY2 = "current_money";

    public AimBase(Context context) {
        super(context, DATABASE_NAME1, null, DATABASE_VERSION1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME2 +
                " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_AIM2 + " text, " +
                COLUMN_NEEDED_MONEY2 + " INTEGER, " +
                COLUMN_CURRENT_MONEY2 + " INTEGER);";


        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(sqLiteDatabase);
    }


    public ArrayList<AimDbViewer> getAllAimData() {
        try {
            ArrayList<AimDbViewer> AimDBViewerArrayList = new ArrayList<>();
            SQLiteDatabase objSqliteDatabase = getReadableDatabase();
            if (objSqliteDatabase != null) {
                Cursor objCursor = objSqliteDatabase.rawQuery("select * from my_aims", null);
                //Cursor buttonCursor = objSqliteDatabase.rawQuery("select answer from answers where ID_question =" + id, null)
                if (objCursor.getCount() != 0) {
                    while (objCursor.moveToNext()) {
                        int id = objCursor.getInt(0);
                        String aim = objCursor.getString(1);
                        int neededMoney = objCursor.getInt(2);
                        int currentMoney = objCursor.getInt(3);
                        AimDBViewerArrayList.add(new AimDbViewer(id, aim, neededMoney, currentMoney));
                    }

                    return AimDBViewerArrayList;
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

    public void addAimToBase(String aim, int neededMoney, int currentMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_AIM2, aim);
        cv.put(COLUMN_NEEDED_MONEY2, neededMoney);
        cv.put(COLUMN_CURRENT_MONEY2, currentMoney);
        long res = db.insert(TABLE_NAME2, null, cv);
        if (res == -1) {
            Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateAimBase(String id, int currentMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

//        cv.put(COLUMN_AIM2, aim);
//        cv.put(COLUMN_NEEDED_MONEY2, neededMoney);
        cv.put(COLUMN_CURRENT_MONEY2, currentMoney);
        String selection = "aim_id = ?";
        String[] selectionArgs = { "" + id};

        db.update(TABLE_NAME2, cv, selection, new String[] {id});

    }

    public void deleteFromAimBase(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = "aim_id = ?";
        String[] selectionArgs = { "" + id};

        db.delete(TABLE_NAME2, selection, new String[] {id});
    }
}
