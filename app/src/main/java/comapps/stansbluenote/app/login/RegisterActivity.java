package comapps.stansbluenote.app.login;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import comapps.stansbluenote.app.R;

public class RegisterActivity extends Activity
{
  private final static java.text.SimpleDateFormat SIMPLE_DATE_FORMAT = new java.text.SimpleDateFormat( "yyyy/MM/dd" );

  private EditText deviceIDField;
  private EditText emailField;
  private EditText favoritesField;
  private EditText genderField;
  private EditText idField;
  private EditText leaderboardField;
  private EditText nameField;
  private EditText passwordField;

  private Button registerButton;

  private String deviceID;
  private String email;
  private String favorites;
  private String gender;
  private String id;
  private String leaderboard;
  private String name;
  private String password;

  private BackendlessUser user;

  public void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView(R.layout.register );

    initUI();
  }

  private void initUI()
  {deviceIDField = (EditText) findViewById( R.id.deviceIDField );emailField = (EditText) findViewById( R.id.emailField );favoritesField = (EditText) findViewById( R.id.favoritesField );genderField = (EditText) findViewById( R.id.genderField );idField = (EditText) findViewById( R.id.idField );leaderboardField = (EditText) findViewById( R.id.leaderboardField );nameField = (EditText) findViewById( R.id.nameField );passwordField = (EditText) findViewById( R.id.passwordField );

    registerButton = (Button) findViewById( R.id.registerButton );

    registerButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onRegisterButtonClicked();
      }
    } );
  }

  private void onRegisterButtonClicked()
  {
    String deviceIDText = deviceIDField.getText().toString().trim();
    String emailText = emailField.getText().toString().trim();
    String favoritesText = favoritesField.getText().toString().trim();
    String genderText = genderField.getText().toString().trim();
    String idText = idField.getText().toString().trim();
    String leaderboardText = leaderboardField.getText().toString().trim();
    String nameText = nameField.getText().toString().trim();
    String passwordText = passwordField.getText().toString().trim();

    if ( emailText.isEmpty() )
    {
      showToast( "Field 'email' cannot be empty." );
      return;
    }

    if ( passwordText.isEmpty() )
    {
      showToast( "Field 'password' cannot be empty." );
      return;
    }

    if( !deviceIDText.isEmpty() )
    {
      deviceID = deviceIDText;
    }

    if( !emailText.isEmpty() )
    {
      email = emailText;
    }

    if( !favoritesText.isEmpty() )
    {
      favorites = favoritesText;
    }

    if( !genderText.isEmpty() )
    {
      gender = genderText;
    }

    if( !idText.isEmpty() )
    {
      id = idText;
    }

    if( !leaderboardText.isEmpty() )
    {
      leaderboard = leaderboardText;
    }

    if( !nameText.isEmpty() )
    {
      name = nameText;
    }

    if( !passwordText.isEmpty() )
    {
      password = passwordText;
    }

    user = new BackendlessUser();

    if( deviceID != null )
    {
      user.setProperty( "deviceID", deviceID );
    }

    if( email != null )
    {
      user.setProperty( "email", email );
    }

    if( favorites != null )
    {
      user.setProperty( "favorites", favorites );
    }

    if( gender != null )
    {
      user.setProperty( "gender", gender );
    }

    if( id != null )
    {
      user.setProperty( "id", id );
    }

    if( leaderboard != null )
    {
      user.setProperty( "leaderboard", leaderboard );
    }

    if( name != null )
    {
      user.setProperty( "name", name );
    }

    if( password != null )
    {
      user.setProperty( "password", password );
    }

    Backendless.UserService.register( user, new DefaultCallback<BackendlessUser>( RegisterActivity.this )
    {
      @Override
      public void handleResponse( BackendlessUser response )
      {
        super.handleResponse( response );
        startActivity( new Intent( RegisterActivity.this, RegistrationSuccessActivity.class ) );
        finish();
      }
    } );
  }

  private void showToast( String msg )
  {
    Toast.makeText( this, msg, Toast.LENGTH_SHORT ).show();
  }
}