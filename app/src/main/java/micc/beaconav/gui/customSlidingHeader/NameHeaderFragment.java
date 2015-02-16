package micc.beaconav.gui.customSlidingHeader;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import micc.beaconav.FragmentHelper;
import micc.beaconav.R;
import micc.beaconav.db.dbHelper.IArtRow;
import micc.beaconav.db.dbHelper.museum.MuseumRow;
import micc.beaconav.outdoorEngine.Map;

public class NameHeaderFragment extends Fragment {

    private TextView textViewName = null;
    private IArtRow artRow = null;
    private Button backBtn = null;

    public NameHeaderFragment() {
        // Required empty public constructor.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name_header, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textViewName = (TextView)getView().findViewById(R.id.name);
        if(artRow != null) {
            textViewName.setText(artRow.getName());
            textViewName.setSelected(true);
        }
        backBtn = (Button)getView().findViewById(R.id.back_button2);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FragmentHelper.instance().getActiveMainFragment() == FragmentHelper.MainFragment.OUTDOOR)
                {
                    switch(FragmentHelper.instance().getActiveSlidingFragment()){
                        case NAVIGATE:
                            MuseumRow selectedMuseumRow = Map.getIstance().getSelectedMuseumRow();
                            FragmentHelper.instance().simulateDeselectMuseumOnMapClick();
                            FragmentHelper.instance().simulateMuseumOnMapClick(selectedMuseumRow);
                            break;

                        case DETAILS:
                            FragmentHelper.instance().simulateDeselectMuseumOnMapClick();
                            FragmentHelper.instance().showMuseumListFragment();
                            break;

                        case LIST:
                            // void. Nothing to do!
                            break;

                    }

                }
                else if(FragmentHelper.instance().getActiveMainFragment() == FragmentHelper.MainFragment.INDOOR)
                {
                    switch(FragmentHelper.instance().getActiveSlidingFragment()){
                        case NAVIGATE:
                            break;

                        case DETAILS:
                            FragmentHelper.instance().showArtworkListFragment(FragmentHelper.instance().artworkList_museumRow);
                            break;

                        case LIST:
                            FragmentHelper.instance().showOutdoorFragment();
                            break;

                    }
                }

            }
        });

    }

    public void setArtRow(IArtRow row){
        this.artRow = row;
        if(textViewName != null) {
            textViewName.setText(artRow.getName());
        }
    }

    public Button getBackBtn() {
        return backBtn;
    }

}
