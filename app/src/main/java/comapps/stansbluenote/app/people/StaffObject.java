package comapps.stansbluenote.app.people;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import comapps.stansbluenote.app.Future;

public class StaffObject
{
  private String name;
  private java.util.Date created;
  private String image;
  private String objectId;
  private String other;
  private java.util.Date updated;
  private String ownerId;
  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getImage()
  {
    return image;
  }

  public void setImage( String image )
  {
    this.image = image;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getOther()
  {
    return other;
  }

  public void setOther( String other )
  {
    this.other = other;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

                                                    
  public StaffObject save()
  {
    return Backendless.Data.of( StaffObject.class ).save( this );
  }

  public Future<StaffObject> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<StaffObject> future = new Future<StaffObject>();
      Backendless.Data.of( StaffObject.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<StaffObject> callback )
  {
    Backendless.Data.of( StaffObject.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( StaffObject.class ).remove( this );
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
      Backendless.Data.of( StaffObject.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( StaffObject.class ).remove( this, callback );
  }

  public static StaffObject findById( String id )
  {
    return Backendless.Data.of( StaffObject.class ).findById( id );
  }

  public static Future<StaffObject> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<StaffObject> future = new Future<StaffObject>();
      Backendless.Data.of( StaffObject.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<StaffObject> callback )
  {
    Backendless.Data.of( StaffObject.class ).findById( id, callback );
  }

  public static StaffObject findFirst()
  {
    return Backendless.Data.of( StaffObject.class ).findFirst();
  }

  public static Future<StaffObject> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<StaffObject> future = new Future<StaffObject>();
      Backendless.Data.of( StaffObject.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<StaffObject> callback )
  {
    Backendless.Data.of( StaffObject.class ).findFirst( callback );
  }

  public static StaffObject findLast()
  {
    return Backendless.Data.of( StaffObject.class ).findLast();
  }

  public static Future<StaffObject> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<StaffObject> future = new Future<StaffObject>();
      Backendless.Data.of( StaffObject.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<StaffObject> callback )
  {
    Backendless.Data.of( StaffObject.class ).findLast( callback );
  }

  public static BackendlessCollection<StaffObject> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( StaffObject.class ).find( query );
  }

  public static Future<BackendlessCollection<StaffObject>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<StaffObject>> future = new Future<BackendlessCollection<StaffObject>>();
      Backendless.Data.of( StaffObject.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<StaffObject>> callback )
  {
    Backendless.Data.of( StaffObject.class ).find( query, callback );
  }
}