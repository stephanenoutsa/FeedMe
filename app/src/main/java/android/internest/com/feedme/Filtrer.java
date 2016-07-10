package android.internest.com.feedme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Filtrer extends Fragment {

    public Filtrer() {
        // Required empty public constructor
    }

    public static Filtrer newInstance() {
        Filtrer fragment = new Filtrer();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_filtrer, container, false);

        return rootView;
    }
}
