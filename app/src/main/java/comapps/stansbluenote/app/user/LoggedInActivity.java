package comapps.stansbluenote.app.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.backendless.Backendless;

import comapps.stansbluenote.app.MainActivity;
import comapps.stansbluenote.app.R;

/**
 * Created by me on 1/31/2017.
 */

public class LoggedInActivity extends Activity
{
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.loggedin );

        findViewById( R.id.logoutButton ).setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                Backendless.UserService.logout(new DefaultCallback<Void>( LoggedInActivity.this )
                {
                    @Override
                    public void handleResponse( Void response )
                    {
                        super.handleResponse( response );
                        startActivity( new Intent( getBaseContext(), MainActivity.class ) );
                        finish();
                    }
                } );
            }
        } );
    }
}