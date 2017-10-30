package csitmnr.newsproject2.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.NfcEvent;
import android.util.Log;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import csitmnr.newsproject2.NewsPojo;

import static android.R.attr.id;
import static android.R.attr.theme;

/**
 * Created by Manoj Budha Ayer on 10/23/2017.exist
 */

public class DatabaseNews extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "newsandevent.db";
    private static final String TABLE_NAME = "newsandevent";

    public DatabaseNews(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY,TITLE TEXT,INTRO_TXT TEXT,CREATED_BY_ID INTEGER,FEATURED_IMAGE BLOB,DETAIL TEXT,CREATED_AT TEXT,UPDATED_AT TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean putData(String title, String intro_text, String detail, String created_at, String updated_at) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("TITLE", title);
        cv.put("INTRO_TXT", intro_text);
        cv.put("DETAIL", detail);
        cv.put("CREATED_AT", created_at);
        cv.put("UPDATED_AT", updated_at);
        long result = db.insertOrThrow(TABLE_NAME, null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }




    public List<NewsPojo> getAlldata(){
        List<NewsPojo> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from "+TABLE_NAME+";",null);

//        String[] columns = {"TITLE","INTRO_TXT","DETAIL","CREATED_AT","UPDATED_AT"};
//        Cursor cursor = db.query(TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer = new StringBuffer();
        NewsPojo listofdata = null;
        if (cursor.moveToNext()){
             listofdata = new NewsPojo();

            int index1 = cursor.getColumnIndex("TITLE");
            int index2 = cursor.getColumnIndex("INTRO_TXT");
            int index3 = cursor.getColumnIndex("DETAIL");
            int index4 = cursor.getColumnIndex("CREATED_AT");
            int index5 = cursor.getColumnIndex("UPDATED_AT");

            String  title = cursor.getString(index1);
            String  intro_text = cursor.getString(index2);
            String  detail = cursor.getString(index3);
            String  created_at = cursor.getString(index4);
            String  updated_at = cursor.getString(index5);

            listofdata.setTitle(title);
            listofdata.setIntroText(intro_text);
            listofdata.setDetail(detail);
            listofdata.setCreatedAt(created_at);
            listofdata.setUpdatedAt(updated_at);

//            listofdata = new NewsPojo(title,intro_text,detail,created_at,updated_at);

            buffer.append(listofdata);
            list.add(listofdata);
        }
        for (NewsPojo ls:list){
            Log.i("Hi",""+ls.getCreatedAt());
        }

        return list;

    }
}
