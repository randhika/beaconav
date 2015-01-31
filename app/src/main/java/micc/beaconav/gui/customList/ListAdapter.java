package micc.beaconav.gui.customList;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.FragmentManager;

import micc.beaconav.R;
import micc.beaconav.db.dbJSONManager.schema.TableRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr_Holmes on 21/01/15.
 */

//A questo ListAdapter non interessa di che stiamo parlando (musei o opere),
//basta dargli dati che corrispondano al ViewHolder (si può fare in comune fra opere e musei in una ViewHolder grossa
// poi si gestisce quali visualizzare o no)
//a passargli questi dati, impacchettati in un ArtListItem, ci pensa ArtListFragment.

public class ListAdapter extends BaseAdapter {

    private Context context;
    private List<ArtListItem> list; //lista di oggetti base della lista (sono questi che si possono modificare a piacimento)
    private MuseumDescrFragment museumDescrFragment;
    //private ArtDescrFragment artDescrFragment;
    private String description;

    private class ViewHolder
    {
        ImageView _navButton;
        TextView _artPieceName;
        // Altri attributi posso essere aggiunti qui
        // ma devono essere anche messi nell'xml
        // e vanno sempre inizializzati (null al limite)
    }

    public ListAdapter(Context context, List<ArtListItem> list) {
        this.context = context;
        this.list = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.art_list_item, null);
            holder = new ViewHolder();
            // qui aggancio i vari componenti dell'oggetto base
            // della lista nell'xml
            holder._artPieceName = (TextView) convertView.findViewById(R.id.artPieceName);
            holder._navButton = (ImageView) convertView.findViewById(R.id.navButton);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        ArtListItem artListItem = (ArtListItem) getItem(position);

        holder._artPieceName.setText(artListItem.getName());
        holder._navButton.setImageResource(artListItem.getImageId());
        this.description = artListItem.getDescription();

        holder._navButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View btn) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_list_container, museumDescrFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        holder._artPieceName.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View txt) {


            }
        });

        return convertView;

    }

    //TODO: implementare il meccanismo dei click per ogni componente dell'oggetto
    //TODO: base della lista, probabilmente da usare il metodo getItem().


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


}