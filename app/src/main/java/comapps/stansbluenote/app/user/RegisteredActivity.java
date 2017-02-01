package comapps.stansbluenote.app.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import comapps.stansbluenote.app.R;


/**
 * Created by me on 1/31/2017.
 */

public class RegisteredActivity extends Activity
{
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.registered);
    }

    @Override
    public void onBackPressed()
    {
        setResult( RESULT_OK, new Intent() );
        finish();
    }
}