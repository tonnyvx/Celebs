package antonioneto.unibratec.com.br.celebinfo;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailCelebFragment extends Fragment {
    TextView txtName        ;
    TextView txtAge         ;
    TextView txtBirth_place ;
    TextView txtBirth_sign  ;
    TextView txtBirth_year  ;
    TextView txtOccupation  ;
    TextView txtBirthday    ;
    ImageView imageView     ;

    public static DetailCelebFragment newInstance(String name, String age, String birth_place, String birth_sign, String birth_year, String occupation, String birthday, String image_poster){
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("age",age);
        bundle.putString("birth_place",birth_place);
        bundle.putString("birth_sign",birth_sign);
        bundle.putString("birth_year",birth_year);
        bundle.putString("occupation",occupation);
        bundle.putString("birthday",birthday);
        bundle.putString("image_poster",image_poster);
        DetailCelebFragment dcf = new DetailCelebFragment();
        dcf.setArguments(bundle);
        return dcf;
    }

    public DetailCelebFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_celeb, container, false);
        if(getResources().getBoolean(R.bool.phone)) {
            Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    saveOrRemoveFavorite();
                    /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/
                }
            });

        }
        txtName        = (TextView)view.findViewById(R.id.name);
        txtAge         = (TextView)view.findViewById(R.id.age);
        txtBirth_place = (TextView)view.findViewById(R.id.birth_place);
        txtBirth_sign  = (TextView)view.findViewById(R.id.birth_sign);
        txtBirth_year  = (TextView)view.findViewById(R.id.birth_year);
        txtOccupation  = (TextView)view.findViewById(R.id.occupation);
        txtBirthday    = (TextView)view.findViewById(R.id.birthday);
        imageView      =  (ImageView)view.findViewById(R.id.image_poster);


        String name        =  getArguments().getString("name");
        String age         =  getArguments().getString("age");
        String birth_place =  getArguments().getString("birth_place");
        String birth_sign  =  getArguments().getString("birth_sign");
        String birth_year  =  getArguments().getString("birth_year");
        String occupation  =  getArguments().getString("occupation");
        String birthday    =  getArguments().getString("birthday");
        String photo_url   =  getArguments().getString("image_poster");


        /*Intent intent = getArguments();
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String birth_place = intent.getStringExtra("birth_place");
        String birth_sign = intent.getStringExtra("birth_sign");
        String birth_year = intent.getStringExtra("birth_year");
        String occupation = intent.getStringExtra("occupation");
        String photo_url = intent.getStringExtra("photo_url");
        String birthday = intent.getStringExtra("birthday");
*/


        txtName.setText(name);
        txtAge.setText(age);
        txtBirth_place.setText(birth_place);
        txtBirth_sign.setText(birth_sign);
        txtBirth_year.setText(birth_year);
        txtOccupation.setText(occupation);
        txtBirthday.setText(birthday);
        Picasso.with(getActivity()).load(photo_url).into(imageView);

        if (getResources().getBoolean(R.bool.phone)) {
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) view
                    .findViewById(R.id.toolbar_layout);
            appBarLayout.setTitle(name);
        }


/*
        getView().findViewById(R.id.toolbar_layout);
        toolbar.setTitle(name);
*/

       /* if(getResources().getBoolean(R.bool.phone))
        getSupportActionBar().setTitle(name);
*/
        return view;
    }


}
