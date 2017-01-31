package comapps.stansbluenote.app.specials;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import comapps.stansbluenote.app.Future;

public class   SpecialsObject
{
  private java.util.Date updated;
  private String dayofweek;
  private String x;
  private String objectId;
  private String special;
  private String ownerId;
  private java.util.Date created;
  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getDayofweek()
  {
    return dayofweek;
  }

  public void setDayofweek( String dayofweek )
  {
    this.dayofweek = dayofweek;
  }

  public String getX()
  {
    return x;
  }

  public void setX( String x )
  {
    this.x = x;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getSpecial()
  {
    return special;
  }

  public void setSpecial( String special )
  {
    this.special = special;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

                                                    
  public SpecialsObject save()
  {
    return Backendless.Data.of( SpecialsObject.class ).save( this );
  }

  public Future<SpecialsObject> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<SpecialsObject> future = new Future<SpecialsObject>();
      Backendless.Data.of( SpecialsObject.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<SpecialsObject> callback )
  {
    Backendless.Data.of( SpecialsObject.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( SpecialsObject.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( SpecialsObject.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( SpecialsObject.class ).remove( this, callback );
  }

  public static SpecialsObject findById( String id )
  {
    return Backendless.Data.of( SpecialsObject.class ).findById( id );
  }

  public static Future<SpecialsObject> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<SpecialsObject> future = new Future<SpecialsObject>();
      Backendless.Data.of( SpecialsObject.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<SpecialsObject> callback )
  {
    Backendless.Data.of( SpecialsObject.class ).findById( id, callback );
  }

  public static SpecialsObject findFirst()
  {
    return Backendless.Data.of( SpecialsObject.class ).findFirst();
  }

  public static Future<SpecialsObject> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<SpecialsObject> future = new Future<SpecialsObject>();
      Backendless.Data.of( SpecialsObject.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<SpecialsObject> callback )
  {
    Backendless.Data.of( SpecialsObject.class ).findFirst( callback );
  }

  public static SpecialsObject findLast()
  {
    return Backendless.Data.of( SpecialsObject.class ).findLast();
  }

  public static Future<SpecialsObject> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<SpecialsObject> future = new Future<SpecialsObject>();
      Backendless.Data.of( SpecialsObject.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<SpecialsObject> callback )
  {
    Backendless.Data.of( SpecialsObject.class ).findLast( callback );
  }

  public static BackendlessCollection<SpecialsObject> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( SpecialsObject.class ).find( query );
  }

  public static Future<BackendlessCollection<SpecialsObject>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<SpecialsObject>> future = new Future<BackendlessCollection<SpecialsObject>>();
      Backendless.Data.of( SpecialsObject.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<SpecialsObject>> callback )
  {
    Backendless.Data.of( SpecialsObject.class ).find( query, callback );
  }
}