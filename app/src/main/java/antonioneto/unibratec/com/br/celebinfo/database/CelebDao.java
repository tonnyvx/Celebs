package antonioneto.unibratec.com.br.celebinfo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import antonioneto.unibratec.com.br.celebinfo.model.Celeb;

/**
 * Created by AntonioNeto on 08/12/2016.
 */
public class CelebDao {
    /*MySqlHelper mySqlHelper;*/
    private static CelebDao instance;
    private SQLiteDatabase db;
    private Context context;
    MySqlHelper mySqlHelper;


    public CelebDao(Context context) {
        this.context = context;
        mySqlHelper = new MySqlHelper(context);

    }

    public static synchronized CelebDao getInstance(Context ctx) {
        if (instance == null) {
            instance = new CelebDao(ctx);
        }
        return instance;
    }

    public long insertCeleb(Celeb celeb) {
        long result = -1;
        if (celeb != null) {
            //ContentValues Armazenar informaçoes do "resultSet"
            ContentValues values = new ContentValues();
            SQLiteDatabase db = mySqlHelper.getWritableDatabase();

            values.put(DatabaseConstants.NAME, celeb.getName());
            values.put(DatabaseConstants.AGE, celeb.getAge());
            values.put(DatabaseConstants.BIRTHDAY, celeb.getBirthday());
            values.put(DatabaseConstants.BIRTH_YEAR, celeb.getBirth_year());
            values.put(DatabaseConstants.BIRTH_PLACE, celeb.getBirth_place());
            values.put(DatabaseConstants.BIRTH_SIGN, celeb.getBirth_sign());
            values.put(DatabaseConstants.OCCUPATION, celeb.getOccupation());
            values.put(DatabaseConstants.PHOTO_URL, celeb.getPhoto_url());

            try {
                result = db.insert(DatabaseConstants.TBCELEBS, null, values);
            } finally {
                db.close();
            }

        }
        return result;
    }

    public void deleteCeleb(String name) {


        SQLiteDatabase db = mySqlHelper.getWritableDatabase();

        try {
            db.delete(DatabaseConstants.TBCELEBS, DatabaseConstants.NAME
                    + " = ? ", new String[]{name});
        } finally {
            db.close();
        }
    }

    public Celeb getCeleb(String nome) {
        Celeb celeb = new Celeb();

        SQLiteDatabase db = mySqlHelper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + DatabaseConstants.TBCELEBS + " where " + DatabaseConstants.NAME + " = ?", new String[]{nome});

        if (c.moveToFirst()) {
            celeb = getCelebFromCursor(c);
        }
        db.close();
        return celeb;
    }

    public List<Celeb> getFavoriteCelebs() {

        List<Celeb> celebList = new ArrayList<>();

        //A classe SQLiteDatabase, contém métodos que permitem manusear instruções de banco de dados.



        SQLiteDatabase db = mySqlHelper.getWritableDatabase();

        //Cursor é uma interface que permite acesso de leitura e escrita no resultSet de um retorno de uma query
        Cursor c = db.rawQuery("select * from " + DatabaseConstants.TBCELEBS, null);
        while (c.moveToNext()) {
            celebList.add(getCelebFromCursor(c));
        }
        return celebList;
    }




    public Celeb getCelebFromCursor(Cursor c) {
        Celeb result = new Celeb();
        if (c != null) {
            result.setName(c.getString(c.getColumnIndex(DatabaseConstants.NAME)));
            result.setAge(c.getString(c.getColumnIndex(DatabaseConstants.AGE)));
            result.setBirthday(c.getString(c.getColumnIndex(DatabaseConstants.BIRTHDAY)));
            result.setBirth_year(c.getString(c.getColumnIndex(DatabaseConstants.BIRTH_YEAR)));
            result.setBirth_place(c.getString(c.getColumnIndex(DatabaseConstants.BIRTH_PLACE)));
            result.setBirth_sign(c.getString(c.getColumnIndex(DatabaseConstants.BIRTH_SIGN)));
            result.setOccupation(c.getString(c.getColumnIndex(DatabaseConstants.OCCUPATION)));
            result.setPhoto_url(c.getString(c.getColumnIndex(DatabaseConstants.PHOTO_URL)));
        }
        return result;
    }
}
