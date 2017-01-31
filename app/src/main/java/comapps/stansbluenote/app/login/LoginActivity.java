package comapps.stansbluenote.app.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comapps.stansbluenote.app.MainActivity;
import comapps.stansbluenote.app.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    private Button facebookButton;
    private Button twitterButton;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Marker Felt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setContentView( R.layout.login );

        initUI();

        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( this, Defaults.APPLICATION_ID, Defaults.SECRET_KEY, Defaults.VERSION );

        Backendless.UserService.isValidLogin( new DefaultCallback<Boolean>( this )
        {
            @Override
            public void handleResponse( Boolean isValidLogin )
            {
                if( isValidLogin && Backendless.UserService.CurrentUser() == null )
                {
                    String currentUserId = Backendless.UserService.loggedInUser();

                    if( !currentUserId.equals( "" ) )
                    {
                        Backendless.UserService.findById( currentUserId, new DefaultCallback<BackendlessUser>( LoginActivity.this, "Logging in..." )
                        {
                            @Override
                            public void handleResponse( BackendlessUser currentUser )
                            {
                                super.handleResponse( currentUser );
                                Backendless.UserService.setCurrentUser( currentUser );
                                startActivity( new Intent( getBaseContext(), LoginSuccessActivity.class ) );
                                finish();
                            }
                        } );
                    }
                }

                super.handleResponse( isValidLogin );
            }
        });
    }

    private void initUI()
    {
        facebookButton = (Button) findViewById( R.id.loginFacebookButton );
        twitterButton = (Button) findViewById( R.id.loginTwitterButton );

        facebookButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                onLoginWithFacebookButtonClicked();
            }
        } );

        twitterButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                onLoginWithTwitterButtonClicked();
            }
        } );
    }

    public void onLoginWithFacebookButtonClicked()
    {
        Map<String, String> facebookFieldsMapping = new HashMap<>();
        facebookFieldsMapping.put( "name", "name" );
        facebookFieldsMapping.put( "gender", "gender" );
        facebookFieldsMapping.put( "email", "email" );

        List<String> facebookPermissions = new ArrayList<>();
        facebookPermissions.add( "email" );

        Backendless.UserService.loginWithFacebook( LoginActivity.this, null, facebookFieldsMapping, facebookPermissions, new SocialCallback<BackendlessUser>( LoginActivity.this )
        {
            @Override
            public void handleResponse( BackendlessUser backendlessUser )
            {
                startActivity( new Intent( getBaseContext(), MainActivity.class ) );
                finish();
            }
        }, true );
    }

    public void onLoginWithTwitterButtonClicked()
    {
        Map<String, String> twitterFieldsMapping = new HashMap<>();
        twitterFieldsMapping.put( "name", "name" );

        Backendless.UserService.loginWithTwitter( LoginActivity.this, null, twitterFieldsMapping, new SocialCallback<BackendlessUser>( LoginActivity.this )
        {
            @Override
            public void handleResponse( BackendlessUser backendlessUser )
            {
                startActivity( new Intent( getBaseContext(), MainActivity.class ) );
                finish();
            }
        }, true );
    }

    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }
}
