package antonioneto.unibratec.com.br.celebinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import antonioneto.unibratec.com.br.celebinfo.model.Celeb;
import antonioneto.unibratec.com.br.celebinfo.R;

/**
 * Created by AntonioNeto on 03/12/2016.
 */
public class CelebsAdapter extends ArrayAdapter<Celeb>{




    public CelebsAdapter(Context context, List<Celeb> celebs) {
        super(context, 0, celebs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Celeb celeb = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_celebs, parent, false);
        }
        ImageView imgPoster = (ImageView) convertView.findViewById(R.id.item_celeb_poster);
        TextView  txtName = (TextView) convertView.findViewById(R.id.item_celeb_name);
        TextView  txtOcuppation = (TextView) convertView.findViewById(R.id.item_celeb_occupation);

        Picasso.with(getContext()).load(celeb.getPhoto_url())
                .into(imgPoster);
        txtName.setText(celeb.getName());
        txtOcuppation.setText(celeb.getOccupation());
        return convertView;

    }
}
