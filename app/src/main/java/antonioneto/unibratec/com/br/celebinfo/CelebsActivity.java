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

public class CelebsActivity extends AppCompatActivity implements OnCelebClick
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebs);


    }


    @Override
    public void OnCelebClick(Celeb celeb) {
        if(getResources().getBoolean(R.bool.phone)){
        Intent it = new Intent(this, DetailCelebActivity.class);
        it.putExtra("name", celeb.name);
        it.putExtra("age", celeb.age);
        it.putExtra("birth_place", celeb.birth_place);
        it.putExtra("birth_sign", celeb.birth_sign);
        it.putExtra("birth_year", celeb.birth_year);
        it.putExtra("occupation", celeb.occupation);
        it.putExtra("birthday", celeb.birthday);
        it.putExtra("photo_url", celeb.photo_url);
        startActivity(it);
        }else {
            DetailCelebFragment dcf = DetailCelebFragment.newInstance(celeb.name,celeb.age,celeb.birth_place,celeb.birth_sign,celeb.birth_year,celeb.occupation,celeb.birthday,celeb.photo_url);

            /*String name        = getIntent().getStringExtra("name");
            String age         = getIntent().getStringExtra("age");
            String birth_place = getIntent().getStringExtra("birth_place");
            String birth_sign  = getIntent().getStringExtra("birth_sign");
            String birth_year  = getIntent().getStringExtra("birth_year");
            String occupation  = getIntent().getStringExtra("occupation");
            String birthday    = getIntent().getStringExtra("birthday");
            String photo_url   = getIntent().getStringExtra("photo_url");
            DetailCelebFragment dcf = DetailCelebFragment.newInstance(name,age,birth_place,birth_sign,birth_year,occupation,birthday,photo_url);*/
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.content_celeb_detail,dcf,"detail").
                    commit();

       /* Bundle bundle = new Bundle();
        bundle.putString("name", celeb.name);
        bundle.putString("age", celeb.age);
        bundle.putString("birth_place", celeb.birth_place);
        bundle.putString("birth_sign", celeb.birth_sign);
        bundle.putString("birth_year", celeb.birth_year);
        bundle.putString("occupation", celeb.occupation);
        bundle.putString("photo_url", celeb.photo_url);
        bundle.putString("birthday", celeb.birthday);
        DetailCelebFragment dcf = new DetailCelebFragment();
        dcf.setArguments(bundle);*/
        }
    }
}