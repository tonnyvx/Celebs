package antonioneto.unibratec.com.br.celebinfo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import Model.Celeb;
import http.CelebsSearchTask;
import ui.adapter.CelebsAdapter;


public class CelebListFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<List<Celeb>>, SearchView.OnQueryTextListener  {

    ListView mListCelebs;
    LoaderManager mLoaderManager;

    public CelebListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_celeb_list, container, false);
        mListCelebs = (ListView)view.findViewById(R.id.list_celebs);
        mListCelebs.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (getActivity() instanceof OnCelebClick){

                    Celeb celeb = (Celeb) mListCelebs.getItemAtPosition(position);
                    ((OnCelebClick)getActivity()).OnCelebClick(celeb);
                }

                            }
        });



        mLoaderManager = getLoaderManager();
        mLoaderManager.initLoader(0,null,this);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search,menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public Loader<List<Celeb>> onCreateLoader(int id, Bundle args) {
        String q = args != null ? args.getString("q"): null;
        return new CelebsSearchTask(getActivity(), q);
    }

    @Override
    public void onLoadFinished(Loader<List<Celeb>> loader, List<Celeb> celebs) {
        if (celebs != null){
            mListCelebs.setAdapter(new CelebsAdapter(getActivity(),celebs));

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
