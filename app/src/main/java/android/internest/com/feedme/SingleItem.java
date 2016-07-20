package android.internest.com.feedme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SingleItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fruitopia.ttf");
        toolbarTitle.setTypeface(font);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        ImageView restoIcon = (ImageView) findViewById(R.id.restoIcon);
        TextView restoName = (TextView) findViewById(R.id.restoName);
        TextView foodName = (TextView) findViewById(R.id.foodName);
        TextView foodPrice = (TextView) findViewById(R.id.foodPrice);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Magnificent.ttf");
        restoName.setTypeface(typeface, Typeface.BOLD);
        foodName.setTypeface(typeface);
        foodPrice.setTypeface(typeface);

        Bitmap rlBackgroundBitmap = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("rlBackground"),
                0, getIntent().getByteArrayExtra("rlBackground").length);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(rlBackgroundBitmap);
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            relativeLayout.setBackgroundDrawable(bitmapDrawable);
        } else {
            relativeLayout.setBackground(bitmapDrawable);
        }

        Bitmap restoIconBitmap = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("rIconBitmap"),
                0, getIntent().getByteArrayExtra("rIconBitmap").length);
        restoIcon.setImageBitmap(restoIconBitmap);

        String rName = getIntent().getExtras().getString("restoName");
        restoName.setText(rName);

        String fName = getIntent().getExtras().getString("foodName");
        foodName.setText(fName);

        String fPrice = getIntent().getExtras().getString("foodPrice");
        foodPrice.setText(fPrice);
    }

}
