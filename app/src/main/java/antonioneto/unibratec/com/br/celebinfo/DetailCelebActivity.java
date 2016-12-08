package antonioneto.unibratec.com.br.celebinfo;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import Model.Celeb;

public class DetailCelebActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_celeb);


        String name        = getIntent().getStringExtra("name");
        String age         = getIntent().getStringExtra("age");
        String birth_place = getIntent().getStringExtra("birth_place");
        String birth_sign  = getIntent().getStringExtra("birth_sign");
        String birth_year  = getIntent().getStringExtra("birth_year");
        String occupation  = getIntent().getStringExtra("occupation");
        String photo_url   = getIntent().getStringExtra("photo_url");
        String birthday    = getIntent().getStringExtra("birthday");
        DetailCelebFragment dcf = DetailCelebFragment.newInstance(name,age,birth_place,birth_sign,birth_year,occupation,photo_url,birthday);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_celeb_detail,dcf,"detail").commit();




    }
}
