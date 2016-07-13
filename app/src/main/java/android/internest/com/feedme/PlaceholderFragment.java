package android.internest.com.feedme;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    List<Resto> restoList = new ArrayList<>();
    ListView listView;

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_acceuil, container, false);

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Resto resto1 = new Resto();
                restoList.add(resto1);
                Resto resto2 = new Resto();
                restoList.add(resto2);
                Resto resto3 = new Resto();
                restoList.add(resto3);
                Resto resto4 = new Resto();
                restoList.add(resto4);
                Resto resto5 = new Resto();
                restoList.add(resto5);

                ListAdapter listAdapter = new CustomAdapter(getActivity(), restoList);

                listView = (ListView) rootView.findViewById(R.id.restoList);
                listView.setAdapter(listAdapter);
            }
        };

        Runnable r = new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        Thread thread = new Thread(r);
        thread.start();

        return rootView;
    }
}
