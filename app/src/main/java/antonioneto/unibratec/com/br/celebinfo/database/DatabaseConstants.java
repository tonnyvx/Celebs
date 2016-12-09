package antonioneto.unibratec.com.br.celebinfo.database;

import android.provider.BaseColumns;

/**
 * Created by AntonioNeto on 08/12/2016.
 */
public interface DatabaseConstants extends BaseColumns {

    String TBCELEBS    = "tbcelebs";
    String NAME        = "name";
    String AGE         = "age";
    String BIRTHDAY    = "birthday";
    String BIRTH_YEAR  = "birth_year";
    String BIRTH_PLACE = "birth_place";
    String BIRTH_SIGN  = "birth_sign";
    String OCCUPATION  = "occupation";
    String PHOTO_URL   = "photo_url";

    StringBuilder CREATE_TBCELEBS =
            new StringBuilder("create table " + TBCELEBS)
                    .append(" ("+ _ID + " integer primary key autoincrement, ")
                    .append(NAME + " text, ")
                    .append(AGE + " text, ")
                    .append(BIRTHDAY + " text, ")
                    .append(BIRTH_YEAR + " text, ")
                    .append(BIRTH_PLACE + " text, ")
                    .append(BIRTH_SIGN + " text, ")
                    .append(OCCUPATION + " text, ")
                    .append(PHOTO_URL + " text); ");
}