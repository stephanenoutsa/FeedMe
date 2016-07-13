package android.internest.com.feedme;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;


public class CustomAdapter extends ArrayAdapter<Resto> {

    public CustomAdapter(Context context, List<Resto> restos) {
        super(context, R.layout.custom_row, restos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        //Resto resto = getItem(position);

        //RelativeLayout relativeLayout = (RelativeLayout) customView.findViewById(R.id.relativeLayout);
        ImageView restoIcon = (ImageView) customView.findViewById(R.id.restoIcon);
        TextView restoName = (TextView) customView.findViewById(R.id.restoName);
        TextView foodName = (TextView) customView.findViewById(R.id.foodName);
        TextView foodPrice = (TextView) customView.findViewById(R.id.foodPrice);

        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Magnificent.ttf");
        restoName.setTypeface(font, Typeface.BOLD);
        foodName.setTypeface(font);
        foodPrice.setTypeface(font);

        //relativeLayout.setBackground();
        restoIcon.setImageResource(R.drawable.un);
        restoName.setText("Mami Eru");
        foodName.setText("Fufu and Eru");
        foodPrice.setText("1000 FCFA");

        return customView;
    }
}
