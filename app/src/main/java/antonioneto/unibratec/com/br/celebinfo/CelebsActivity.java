package antonioneto.unibratec.com.br.celebinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import antonioneto.unibratec.com.br.celebinfo.model.Celeb;

public class CelebsActivity extends AppCompatActivity implements OnCelebClick {

    Toolbar toolbar;
    CelebListFragment celebListFragment;
    FavoritesCelebsFragments favoritesCelebsFragments;
    ViewPager mViewPager;
    SelectorPageAdapter selectorPageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebs);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buildViewPager();

    }

    private void buildViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.pager);
        selectorPageAdapter = new SelectorPageAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(selectorPageAdapter);

        TabLayout tab = (TabLayout) findViewById(R.id.tabs);
        tab.setupWithViewPager(mViewPager);
    }


    @Override
    public void OnCelebClick(Celeb celeb) {
        if (getResources().getBoolean(R.bool.phone)) {
            Intent it = new Intent(this, DetailCelebActivity.class);
            it.putExtra("celeb", celeb);
            startActivity(it);
        } else {
            //Carregando o Fragment em tempo de execução
            DetailCelebFragment dcf = DetailCelebFragment.newInstance(celeb);
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.detail_fragment_frame, dcf, "celeb")
                    .commit();
        }
    }

    public class SelectorPageAdapter extends FragmentPagerAdapter {
        public SelectorPageAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (celebListFragment == null) {
                        celebListFragment = new CelebListFragment();
                    }
                    return celebListFragment;
                case 1:
                default:
                    if (favoritesCelebsFragments == null) {
                        favoritesCelebsFragments = new FavoritesCelebsFragments();

                    }
                    return favoritesCelebsFragments;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Lista";
                case 1:
                default:
                    return "Favoritos";
            }
        }
    }
}