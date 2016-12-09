package antonioneto.unibratec.com.br.celebinfo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import antonioneto.unibratec.com.br.celebinfo.model.Celeb;
import antonioneto.unibratec.com.br.celebinfo.database.CelebDao;
import antonioneto.unibratec.com.br.celebinfo.database.DbEvent;
import antonioneto.unibratec.com.br.celebinfo.adapter.CelebsAdapter;


public class FavoritesCelebsFragments extends Fragment {
ListView mListViewCelebs;
    List<Celeb> celebList;

    public FavoritesCelebsFragments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_celeb_list, container, false);

        mListViewCelebs = (ListView)view.findViewById(R.id.list_celebs);
        mListViewCelebs.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(getActivity() instanceof OnCelebClick){
                    Celeb celeb = (Celeb) mListViewCelebs.getItemAtPosition(i);
                    ((OnCelebClick)getActivity()).OnCelebClick(celeb);
            }



            }
        });
        updateList();

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void updateList(){
        celebList = CelebDao.getInstance(getActivity()
                .getApplication()
                .getApplicationContext()).getFavoriteCelebs();
        mListViewCelebs.setAdapter(new CelebsAdapter(getActivity(), celebList));
        Log.d("Quantos", "UpdateList: " + celebList.size());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DbEvent event){

        updateList();

    }

}
