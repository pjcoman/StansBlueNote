package comapps.stansbluenote.app.drinks;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import comapps.stansbluenote.app.Future;

public class BeerObject
{
  private java.util.Date updated;
  private String type;
  private String wherefrom;
  private String abv;
  private String objectId;
  private String name;
  private String image;
  private String ownerId;
  private java.util.Date created;
  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getType()
  {
    return type;
  }

  public void setType( String type )
  {
    this.type = type;
  }

  public String getWherefrom()
  {
    return wherefrom;
  }

  public void setWherefrom( String wherefrom )
  {
    this.wherefrom = wherefrom;
  }

  public String getAbv()
  {
    return abv;
  }

  public void setAbv( String abv )
  {
    this.abv = abv;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getImage()
  {
    return image;
  }

  public void setImage( String image )
  {
    this.image = image;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

                                                    
  public BeerObject save()
  {
    return Backendless.Data.of( BeerObject.class ).save( this );
  }

  public Future<BeerObject> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BeerObject> future = new Future<BeerObject>();
      Backendless.Data.of( BeerObject.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<BeerObject> callback )
  {
    Backendless.Data.of( BeerObject.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( BeerObject.class ).remove( this );
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
      Backendless.Data.of( BeerObject.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( BeerObject.class ).remove( this, callback );
  }

  public static BeerObject findById(String id )
  {
    return Backendless.Data.of( BeerObject.class ).findById( id );
  }

  public static Future<BeerObject> findByIdAsync(String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BeerObject> future = new Future<BeerObject>();
      Backendless.Data.of( BeerObject.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<BeerObject> callback )
  {
    Backendless.Data.of( BeerObject.class ).findById( id, callback );
  }

  public static BeerObject findFirst()
  {
    return Backendless.Data.of( BeerObject.class ).findFirst();
  }

  public static Future<BeerObject> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BeerObject> future = new Future<BeerObject>();
      Backendless.Data.of( BeerObject.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<BeerObject> callback )
  {
    Backendless.Data.of( BeerObject.class ).findFirst( callback );
  }

  public static BeerObject findLast()
  {
    return Backendless.Data.of( BeerObject.class ).findLast();
  }

  public static Future<BeerObject> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BeerObject> future = new Future<BeerObject>();
      Backendless.Data.of( BeerObject.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<BeerObject> callback )
  {
    Backendless.Data.of( BeerObject.class ).findLast( callback );
  }

  public static BackendlessCollection<BeerObject> find(BackendlessDataQuery query )
  {
    return Backendless.Data.of( BeerObject.class ).find( query );
  }

  public static Future<BackendlessCollection<BeerObject>> findAsync(BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<BeerObject>> future = new Future<BackendlessCollection<BeerObject>>();
      Backendless.Data.of( BeerObject.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<BeerObject>> callback )
  {
    Backendless.Data.of( BeerObject.class ).find( query, callback );
  }
}