package android.internest.com.feedme;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

public class FlashScreen extends Activity {

    // Duration of wait
    private final long DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        /* New Handler to start the Acceuil Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Acceuil Activity. */
                Intent i = new Intent(FlashScreen.this, Acceuil.class);
                FlashScreen.this.startActivity(i);
                FlashScreen.this.finish();
            }
        }, DELAY);
    }
}
