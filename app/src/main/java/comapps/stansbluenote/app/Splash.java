package comapps.stansbluenote.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends Activity {
    private long splashDelay = 3000; //3 seconds

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Animation a = AnimationUtils.loadAnimation(this, R.anim.startanimation);
        a.reset();
        ImageView iv = (ImageView) findViewById(R.id.enter);
        iv.clearAnimation();
        iv.startAnimation(a);


        TimerTask startstansbn = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent mainIntent = new Intent().setClass(Splash.this, MainActivity.class);
                startActivity(mainIntent);
            }
        };

        Timer timer = new Timer();
        timer.schedule(startstansbn, splashDelay);

    }
}

		
		 
		
			