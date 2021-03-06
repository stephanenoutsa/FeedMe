package android.internest.com.feedme;

import android.graphics.Typeface;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

public class Acceuil extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fruitopia.ttf");
        toolbarTitle.setTypeface(font);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Set up the swiping TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorTextPrimary),
                getResources().getColor(R.color.colorSelectedTab));
        tabLayout.getTabAt(0).setIcon(R.drawable.acceuil);
        //tabLayout.getTabAt(1).setIcon(R.drawable.filtrer);
        tabLayout.getTabAt(1).setIcon(R.drawable.favoris);
        tabLayout.getTabAt(2).setIcon(R.drawable.lecons);

    }

    ////////////Intents for menu items////////////
    public void onClickBasket() {

    }

    public void onClickAbout() {

    }

    public void onClickContact() {

    }

    public void onClickRate() {

    }

    public void onClickAccount() {

    }
    //////////////End of intents//////////////////


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_acceuil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.search) {
            onSearchRequested();
            return true;
        }

        if(id == R.id.basket) {
            onClickBasket();
            return true;
        }

        if (id == R.id.go_to_about) {
            onClickAbout();
            return true;
        }

        if (id == R.id.go_to_contact) {
            onClickContact();
            return true;
        }

        if (id == R.id.menu_item_rate) {
            onClickRate();
            return true;
        }

        if (id == R.id.go_to_account) {
            onClickAccount();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return PlaceholderFragment.newInstance(position + 1);
                /*case 1:
                    return Filtrer.newInstance();*/
                case 1:
                    return Favoris.newInstance();
                case 2:
                    return Lecons.newInstance();
                default:
                    return PlaceholderFragment.newInstance(position + 1);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.acceuil_fragment_text);
                /*case 1:
                    return getString(R.string.filtrer_fragment_text);*/
                case 1:
                    return getString(R.string.favoris_fragment_text);
                case 2:
                    return getString(R.string.lecons_fragment_text);
            }
            return null;
        }
    }
}
