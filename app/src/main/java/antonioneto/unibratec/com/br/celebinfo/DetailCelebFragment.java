package antonioneto.unibratec.com.br.celebinfo;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import antonioneto.unibratec.com.br.celebinfo.model.Celeb;
import antonioneto.unibratec.com.br.celebinfo.database.CelebDao;
import antonioneto.unibratec.com.br.celebinfo.database.DbEvent;

import static antonioneto.unibratec.com.br.celebinfo.R.id.age;
import static antonioneto.unibratec.com.br.celebinfo.R.id.birth_place;
import static antonioneto.unibratec.com.br.celebinfo.R.id.birth_sign;
import static antonioneto.unibratec.com.br.celebinfo.R.id.birth_year;
import static antonioneto.unibratec.com.br.celebinfo.R.id.birthday;
import static antonioneto.unibratec.com.br.celebinfo.R.id.occupation;


public class DetailCelebFragment extends Fragment {
    TextView txtName;
    TextView txtAge;
    TextView txtBirth_place;
    TextView txtBirth_sign;
    TextView txtBirth_year;
    TextView txtOccupation;
    TextView txtBirthday;
    ImageView imageView;
    Celeb celeb;
    FloatingActionButton floatingActionButton;
    CelebDao celebDao;
    Celeb celebTemp;
    CollapsingToolbarLayout appBarLayout;
    boolean isFavorite;

    public DetailCelebFragment() {

    }

    public static DetailCelebFragment newInstance(Celeb celeb) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("celeb", celeb);

        DetailCelebFragment dcf = new DetailCelebFragment();
        dcf.setArguments(bundle);
        return dcf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_celeb, container, false);


        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadComponents(view);

        celeb = (Celeb) getArguments().getSerializable("celeb");

        txtName.setText(celeb.getName());
        txtAge.setText(celeb.getAge());
        txtBirth_place.setText(celeb.getBirth_place());
        txtBirth_sign.setText(celeb.getBirth_sign());
        txtBirth_year.setText(celeb.getBirth_year());
        txtOccupation.setText(celeb.getOccupation());
        txtBirthday.setText(celeb.getBirthday());
        Picasso.with(getActivity()).load(celeb.getPhoto_url()).into(imageView);

        if (getResources().getBoolean(R.bool.phone)) {
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) view
                    .findViewById(R.id.toolbar_layout);
            appBarLayout.setTitle(celeb.getName());
        }

        celebDao = new CelebDao(getActivity());
        celebTemp = celebDao.getCeleb(celeb.getName());
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        if (celebTemp.getName() == null) {
            changeFloatingButton(false);
        } else {
            changeFloatingButton(true);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveOrRemoveFavorite();
            }
        });


        return view;
    }

    private void changeFloatingButton(boolean isFavorite) {
        int resource = isFavorite ? R.drawable.ic_favorite_black_24dp : R.drawable.ic_favorite_border_black_24dp;
        floatingActionButton.setImageResource(resource);
    }

    public void saveOrRemoveFavorite() {
        if (TextUtils.isEmpty(celebTemp.getName())) {
            celebDao.insertCeleb(celeb);
            changeFloatingButton(true);
        } else {
            celebDao.deleteCeleb(celeb.getName());
            changeFloatingButton(false);
        }

        EventBus.getDefault().post(new DbEvent());
    }

    /**
     * Carrega os componentes de tela.
     *
     * @param view Tela a ser carregada os componentes.
     */
    void loadComponents(View view) {
        txtName = (TextView) view.findViewById(R.id.name);
        txtAge = (TextView) view.findViewById(age);
        txtBirth_place = (TextView) view.findViewById(birth_place);
        txtBirth_sign = (TextView) view.findViewById(birth_sign);
        txtBirth_year = (TextView) view.findViewById(birth_year);
        txtOccupation = (TextView) view.findViewById(occupation);
        txtBirthday = (TextView) view.findViewById(birthday);
        imageView = (ImageView) view.findViewById(R.id.image_poster);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);

    }
}


