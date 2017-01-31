package comapps.stansbluenote.app.food;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import comapps.stansbluenote.app.Future;

public class MenuObject
{
  private String item;
  private String price;
  private String type;
  private String objectId;
  private java.util.Date updated;
  private String x;
  private String ownerId;
  private java.util.Date created;
  public String getItem()
  {
    return item;
  }

  public void setItem( String item )
  {
    this.item = item;
  }

  public String getPrice()
  {
    return price;
  }

  public void setPrice( String price )
  {
    this.price = price;
  }

  public String getType()
  {
    return type;
  }

  public void setType( String type )
  {
    this.type = type;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getX()
  {
    return x;
  }

  public void setX( String x )
  {
    this.x = x;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

                                                    
  public MenuObject save()
  {
    return Backendless.Data.of( MenuObject.class ).save( this );
  }

  public Future<MenuObject> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<MenuObject> future = new Future<MenuObject>();
      Backendless.Data.of( MenuObject.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<MenuObject> callback )
  {
    Backendless.Data.of( MenuObject.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( MenuObject.class ).remove( this );
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
      Backendless.Data.of( MenuObject.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( MenuObject.class ).remove( this, callback );
  }

  public static MenuObject findById( String id )
  {
    return Backendless.Data.of( MenuObject.class ).findById( id );
  }

  public static Future<MenuObject> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<MenuObject> future = new Future<MenuObject>();
      Backendless.Data.of( MenuObject.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<MenuObject> callback )
  {
    Backendless.Data.of( MenuObject.class ).findById( id, callback );
  }

  public static MenuObject findFirst()
  {
    return Backendless.Data.of( MenuObject.class ).findFirst();
  }

  public static Future<MenuObject> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<MenuObject> future = new Future<MenuObject>();
      Backendless.Data.of( MenuObject.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<MenuObject> callback )
  {
    Backendless.Data.of( MenuObject.class ).findFirst( callback );
  }

  public static MenuObject findLast()
  {
    return Backendless.Data.of( MenuObject.class ).findLast();
  }

  public static Future<MenuObject> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<MenuObject> future = new Future<MenuObject>();
      Backendless.Data.of( MenuObject.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<MenuObject> callback )
  {
    Backendless.Data.of( MenuObject.class ).findLast( callback );
  }

  public static BackendlessCollection<MenuObject> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( MenuObject.class ).find( query );
  }

  public static Future<BackendlessCollection<MenuObject>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<MenuObject>> future = new Future<BackendlessCollection<MenuObject>>();
      Backendless.Data.of( MenuObject.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<MenuObject>> callback )
  {
    Backendless.Data.of( MenuObject.class ).find( query, callback );
  }
}