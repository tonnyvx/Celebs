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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String birth_place = intent.getStringExtra("birth_place");
        String birth_sign = intent.getStringExtra("birth_sign");
        String birth_year = intent.getStringExtra("birth_year");
        String occupation = intent.getStringExtra("occupation");
        String photo_url = intent.getStringExtra("photo_url");
        String birthday = intent.getStringExtra("birthday");

        TextView txtName = (TextView)findViewById(R.id.name);
        TextView txtAge = (TextView)findViewById(R.id.age);
        TextView txtBirth_place = (TextView)findViewById(R.id.birth_place);
        TextView txtBirth_sign = (TextView)findViewById(R.id.birth_sign);
        TextView txtBirth_year = (TextView)findViewById(R.id.birth_year);
        TextView txtOccupation = (TextView)findViewById(R.id.occupation);
        TextView txtBirthday = (TextView)findViewById(R.id.birthday);

        txtName.setText(name);
        txtAge.setText(age);
        txtBirth_place.setText(birth_place);
        txtBirth_sign.setText(birth_sign);
        txtBirth_year.setText(birth_year);
        txtOccupation.setText(occupation);
        txtBirthday.setText(birthday);

        ImageView imageView =(ImageView)findViewById(R.id.image_poster);
        Picasso.with(this).load(photo_url).into(imageView);

        getSupportActionBar().setTitle(name);
    }

}
