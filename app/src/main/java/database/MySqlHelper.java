package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by AntonioNeto on 08/12/2016.
 */
public class MySqlHelper  extends SQLiteOpenHelper {
    private static final String DB_NAME ="celebsdb.db";
    private static final int    DB_VERSION = 1;

    private static MySqlHelper  instance;
    private Context ctx;

    private MySqlHelper (Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
        this.ctx = ctx;
    }

    public static synchronized MySqlHelper  getInstance(Context ctx){
        if (instance == null){
            instance = new MySqlHelper (ctx);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseConstants.CREATE_TBCELEBS.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
