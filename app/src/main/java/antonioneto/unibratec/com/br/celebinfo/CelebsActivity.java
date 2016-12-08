package antonioneto.unibratec.com.br.celebinfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import Model.Celeb;
import http.CelebsParser;
import http.CelebsSearchTask;
import ui.adapter.CelebsAdapter;

public class CelebsActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Celeb>>, SearchView.OnQueryTextListener {

    ListView mListCelebs;
    LoaderManager mLoaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebs);

        mListCelebs = (ListView)findViewById(R.id.list_celebs);
        mListCelebs.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Celeb celeb = (Celeb) mListCelebs.getItemAtPosition(position);
                Intent it = new Intent(CelebsActivity.this, DetailCelebActivity.class);
                it.putExtra("name", celeb.name);
                it.putExtra("age", celeb.age);
                it.putExtra("birth_place", celeb.birth_place);
                it.putExtra("birth_sign", celeb.birth_sign);
                it.putExtra("birth_year", celeb.birth_year);
                it.putExtra("occupation", celeb.occupation);
                it.putExtra("photo_url", celeb.photo_url);
                it.putExtra("birthday", celeb.birthday);
                startActivity(it);
            }
        });



        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(0,null,this);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_search,menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public Loader<List<Celeb>> onCreateLoader(int id, Bundle args) {
        String q = args != null ? args.getString("q"): null;
        return new CelebsSearchTask(this, q);
    }

    @Override
    public void onLoadFinished(Loader<List<Celeb>> loader, List<Celeb> celebs) {
        if (celebs != null){
            mListCelebs.setAdapter(new CelebsAdapter(CelebsActivity.this,celebs));

        }
  }

    @Override
    public void onLoaderReset(Loader<List<Celeb>> loader) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Bundle bundle = new Bundle();
        bundle.putString("q",query);
        mLoaderManager.restartLoader(0,bundle,this);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
