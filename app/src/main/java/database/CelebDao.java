package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.Celeb;

/**
 * Created by AntonioNeto on 08/12/2016.
 */
public class CelebDao {
    MySqlHelper mySqlHelper;
    private static CelebDao instance;
    //private Context ctx;

    private CelebDao(Context ctx){
        mySqlHelper  = MySqlHelper.getInstance(ctx);
    }

    public static synchronized CelebDao getInstance(Context ctx){
        if (instance == null){
            instance = new CelebDao(ctx);
        }
        return instance;
    }

    public long insertCeleb(Celeb celeb){
        long result = -1;
        if(celeb != null) {
            ContentValues values = new ContentValues();
            SQLiteDatabase db = mySqlHelper.getWritableDatabase();

            values.put(DatabaseConstants.NAME, celeb.name);
            values.put(DatabaseConstants.AGE, celeb.age);
            values.put(DatabaseConstants.BIRTHDAY, celeb.birthday);
            values.put(DatabaseConstants.BIRTH_YEAR, celeb.birth_year);
            values.put(DatabaseConstants.BIRTH_PLACE, celeb.birth_place);
            values.put(DatabaseConstants.BIRTH_SIGN, celeb.birth_sign);
            values.put(DatabaseConstants.OCCUPATION, celeb.occupation);
            values.put(DatabaseConstants.PHOTO_URL, celeb.photo_url);

            try {
                result = db.insert(DatabaseConstants.TBCELEBS, null, values);
            } finally {
                db.close();
            }

        }
        return result;
    }
    public long deleteCeleb(Celeb celeb){
        long result = -1;
        if (celeb != null){

            SQLiteDatabase db = mySqlHelper.getWritableDatabase();

            try {
                result = db.delete(DatabaseConstants.TBCELEBS, DatabaseConstants.NAME
                + " = ? ", new String[]{celeb.name});
            }finally {
                db.close();
            }
        }
        return result;
    }

    public Celeb getCeleb(Celeb celeb){
        Celeb result = null;
        if(celeb != null){

            SQLiteDatabase db = mySqlHelper.getWritableDatabase();

            Cursor c = db.rawQuery("select * from" + DatabaseConstants.TBCELEBS + " where " + DatabaseConstants.NAME + " = ?", new String[]{celeb.name});

            if(c.moveToFirst()){
                result = getCelebFromCursor(c);
            }
        }
        return result;
    }

    public List<Celeb> getFavoriteCelebs(){

        List<Celeb> celebList = new ArrayList<>();


        SQLiteDatabase db = mySqlHelper.getWritableDatabase();

        Cursor c = db.rawQuery("select * from " + DatabaseConstants.TBCELEBS, null);
        while (c.moveToNext()){
            celebList.add(getCelebFromCursor(c));
        }
        return celebList;
    }
public Celeb getCelebFromCursor(Cursor c){
    Celeb result = new Celeb();
    if (c != null){
        result.name = c.getString(c.getColumnIndex(DatabaseConstants.NAME));
        result.age = c.getString(c.getColumnIndex(DatabaseConstants.AGE));
        result.birthday = c.getString(c.getColumnIndex(DatabaseConstants.BIRTHDAY));
        result.birth_year = c.getString(c.getColumnIndex(DatabaseConstants.BIRTH_YEAR));
        result.birth_place = c.getString(c.getColumnIndex(DatabaseConstants.BIRTH_PLACE));
        result.birth_sign= c.getString(c.getColumnIndex(DatabaseConstants.BIRTH_SIGN));
        result.occupation= c.getString(c.getColumnIndex(DatabaseConstants.OCCUPATION));
        result.photo_url= c.getString(c.getColumnIndex(DatabaseConstants.PHOTO_URL));
    }return result;
}
}
