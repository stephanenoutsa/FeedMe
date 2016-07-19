package android.internest.com.feedme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
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
    GridView gridView;

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

                gridView = (GridView) rootView.findViewById(R.id.gridview);
                gridView.setAdapter(listAdapter);

                gridView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
                                ImageView restoIcon = (ImageView) view.findViewById(R.id.restoIcon);
                                TextView restoName = (TextView) view.findViewById(R.id.restoName);
                                TextView foodName = (TextView) view.findViewById(R.id.foodName);
                                TextView foodPrice = (TextView) view.findViewById(R.id.foodPrice);

                                restoIcon.setVisibility(View.INVISIBLE);
                                Bitmap rlBackgroundBitmap = getBitmapFromView(relativeLayout);
                                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                                rlBackgroundBitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);

                                String rName = restoName.getText().toString();
                                String fName = foodName.getText().toString();
                                String fPrice = foodPrice.getText().toString();

                                // Get image from ImageView and pass to next activity
                                restoIcon.setVisibility(View.VISIBLE);
                                restoIcon.buildDrawingCache();
                                Bitmap restoIconBitmap = restoIcon.getDrawingCache();
                                ByteArrayOutputStream bs1 = new ByteArrayOutputStream();
                                restoIconBitmap.compress(Bitmap.CompressFormat.PNG, 50, bs1);

                                Intent intent = new Intent(getContext(), SingleItem.class);
                                //intent.putExtras(rl);
                                intent.putExtra("rlBackground", bs.toByteArray());
                                intent.putExtra("rIconBitmap", bs1.toByteArray());
                                intent.putExtra("restoName", rName);
                                intent.putExtra("foodName", fName);
                                intent.putExtra("foodPrice", fPrice);
                                startActivity(intent);
                            }
                        }
                );
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

    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }
}
