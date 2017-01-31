package comapps.stansbluenote.app.drinks;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import comapps.stansbluenote.app.Future;

public class CocktailObject
{
  private String name;
  private String ingredients;
  private java.util.Date updated;
  private String ownerId;
  private String price;
  private String image;
  private String objectId;
  private java.util.Date created;
  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getIngredients()
  {
    return ingredients;
  }

  public void setIngredients( String ingredients )
  {
    this.ingredients = ingredients;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getPrice()
  {
    return price;
  }

  public void setPrice( String price )
  {
    this.price = price;
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

  public java.util.Date getCreated()
  {
    return created;
  }

                                                    
  public CocktailObject save()
  {
    return Backendless.Data.of( CocktailObject.class ).save( this );
  }

  public Future<CocktailObject> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<CocktailObject> future = new Future<CocktailObject>();
      Backendless.Data.of( CocktailObject.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<CocktailObject> callback )
  {
    Backendless.Data.of( CocktailObject.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( CocktailObject.class ).remove( this );
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
      Backendless.Data.of( CocktailObject.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( CocktailObject.class ).remove( this, callback );
  }

  public static CocktailObject findById( String id )
  {
    return Backendless.Data.of( CocktailObject.class ).findById( id );
  }

  public static Future<CocktailObject> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<CocktailObject> future = new Future<CocktailObject>();
      Backendless.Data.of( CocktailObject.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<CocktailObject> callback )
  {
    Backendless.Data.of( CocktailObject.class ).findById( id, callback );
  }

  public static CocktailObject findFirst()
  {
    return Backendless.Data.of( CocktailObject.class ).findFirst();
  }

  public static Future<CocktailObject> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<CocktailObject> future = new Future<CocktailObject>();
      Backendless.Data.of( CocktailObject.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<CocktailObject> callback )
  {
    Backendless.Data.of( CocktailObject.class ).findFirst( callback );
  }

  public static CocktailObject findLast()
  {
    return Backendless.Data.of( CocktailObject.class ).findLast();
  }

  public static Future<CocktailObject> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<CocktailObject> future = new Future<CocktailObject>();
      Backendless.Data.of( CocktailObject.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<CocktailObject> callback )
  {
    Backendless.Data.of( CocktailObject.class ).findLast( callback );
  }

  public static BackendlessCollection<CocktailObject> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( CocktailObject.class ).find( query );
  }

  public static Future<BackendlessCollection<CocktailObject>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<CocktailObject>> future = new Future<BackendlessCollection<CocktailObject>>();
      Backendless.Data.of( CocktailObject.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<CocktailObject>> callback )
  {
    Backendless.Data.of( CocktailObject.class ).find( query, callback );
  }
}