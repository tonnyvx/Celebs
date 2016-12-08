package http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;
import java.util.List;

import Model.Celeb;

/**
 * Created by AntonioNeto on 03/12/2016.
 */
public class CelebsSearchTask extends AsyncTaskLoader<List<Celeb>> {


    List<Celeb> mCelebs;
    String mQuery;

    public CelebsSearchTask(Context context, String q) {
        super(context);
        mQuery = q;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(mQuery == null) return;

        if (mCelebs == null){
            forceLoad();
        } else{
            deliverResult(mCelebs);
        }
    }

    @Override
    public List<Celeb> loadInBackground() {
        try {
            mCelebs = CelebsParser.searchByTitle(mQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mCelebs;
    }
}
