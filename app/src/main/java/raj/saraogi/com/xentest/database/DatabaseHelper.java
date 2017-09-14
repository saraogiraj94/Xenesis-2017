package raj.saraogi.com.xentest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import raj.saraogi.com.xentest.holder.Event;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "xenesis2017.db";
    public String CREATE_EVENT = "create table " + DatabaseSchema.Events.TABLE_NEAME
            + "(" + DatabaseSchema.Events.EVENT_ID + " text, "
            + DatabaseSchema.Events.EVENT_CODE + " text, "
            + DatabaseSchema.Events.DEPARTMENT_ID + " text, "
            + DatabaseSchema.Events.EVENT_NAME + " text, "
            + DatabaseSchema.Events.EVENT_DESCRIPTION + " text, "
            + DatabaseSchema.Events.EVENT_PRICE + " text, "
            + DatabaseSchema.Events.EVENT_DATE + " text, "
            + DatabaseSchema.Events.EVENT_TIME + " text, "
            + DatabaseSchema.Events.IMAGE_NAME + " integer ,"
            + DatabaseSchema.Events.URL + " text " + ")";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist " + DatabaseSchema.Events.TABLE_NEAME);
        onCreate(db);
    }

    public boolean addEvent (String eventId, String eventCode, String deptId, String name, String description, String price, String date, String time, int imageName,String url){
        try{
            ContentValues values = new ContentValues();

            values.put(DatabaseSchema.Events.EVENT_ID, eventId);
            values.put(DatabaseSchema.Events.EVENT_CODE, eventCode);
            values.put(DatabaseSchema.Events.DEPARTMENT_ID, deptId);
            values.put(DatabaseSchema.Events.EVENT_NAME, name);
            values.put(DatabaseSchema.Events.EVENT_DESCRIPTION, description);
            values.put(DatabaseSchema.Events.EVENT_PRICE, price);
            values.put(DatabaseSchema.Events.EVENT_DATE, date);
            values.put(DatabaseSchema.Events.EVENT_TIME, time);
            values.put(DatabaseSchema.Events.IMAGE_NAME, imageName);
            values.put(DatabaseSchema.Events.URL,url);
            getWritableDatabase().insert(DatabaseSchema.Events.TABLE_NEAME, null, values);
            return true;
        } catch (SQLiteConstraintException e) { e.printStackTrace(); return false; }
    }

    public List<Event> events(List<String> constraints, String[] values) {
        try{
            List<Event> events = new ArrayList<>();
            String conString = constraints.get(0) + "=?";
            if(constraints.size()>1) {
                for(int i=0; i < constraints.size() ; i++){
                    conString = conString.concat(" and " + constraints.get(i) + "=?");
                }
            }
            Cursor cursor = getReadableDatabase().query(DatabaseSchema.Events.TABLE_NEAME, new String[]{"*"}, conString, values, null, null, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Event object = new Event(cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_CODE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.DEPARTMENT_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_PRICE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_DATE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_TIME)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseSchema.Events.IMAGE_NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.URL)));

                cursor.moveToNext();
                events.add(object);
            }
            cursor.close();
            return events;
        } catch (CursorIndexOutOfBoundsException e) { e.printStackTrace(); return null; }
    }

    public Event events(String eventId) {
        try{

            Cursor cursor = getReadableDatabase().rawQuery("select * from " + DatabaseSchema.Events.TABLE_NEAME + " where " + DatabaseSchema.Events.EVENT_ID + "=" + eventId, null);
            cursor.moveToFirst();

                Event object = new Event(cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_CODE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.DEPARTMENT_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_PRICE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_DATE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_TIME)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseSchema.Events.IMAGE_NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.URL)));

            cursor.close();
            return object;
        } catch (CursorIndexOutOfBoundsException e) { e.printStackTrace(); return null; }
    }

    public int checkData (){
        try{
            Cursor cursor = getReadableDatabase().rawQuery("select * from " + DatabaseSchema.Events.TABLE_NEAME, null);
            return cursor.getCount();
        } catch (CursorIndexOutOfBoundsException e) { e.printStackTrace(); return 0; }
    }
}
