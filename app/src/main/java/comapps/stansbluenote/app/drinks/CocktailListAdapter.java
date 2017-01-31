package comapps.stansbluenote.app.drinks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;
import java.util.Random;

import comapps.stansbluenote.app.R;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class CocktailListAdapter extends BaseAdapter {


    public static final String TAG = "COCKTAILLISTADAPTER";

    private final Context context;
    private final List<CocktailObject> cocktailList;


    public CocktailListAdapter(Context context, List<CocktailObject> cocktailList) {

        this.context = context;
        this.cocktailList = cocktailList;

    }


    @Override
    public int getCount() {
        return cocktailList.size();
    }

    @Override
    public Object getItem(int position) {
        return cocktailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;


        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cocktaillistrow, parent, false);
            view.setEnabled(false);

            holder = new ViewHolder();


            holder.cocktailname = (TextView) view.findViewById(R.id.nameTxt);
            holder.cocktailIngredients = (TextView) view.findViewById(R.id.ingredients);
            holder.cocktailprice = (TextView) view.findViewById(R.id.price);

            holder.cocktailimage = (ImageView) view.findViewById(R.id.cocktailimage);




            //holder.beerimage = (ImageView) view.findViewById(R.id.beerimage);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }


        // Set the results into TextViews
        CocktailObject object = cocktailList.get(position);

        String tempName;
        String tempABV;
        String tempWherefrom;
        String tempType;




        holder.cocktailname.setText(object.getName());
        holder.cocktailIngredients.setText(object.getIngredients());
        holder.cocktailprice.setText(object.getPrice());

        holder.cocktailname.setTypeface(getCustomFont());
        holder.cocktailIngredients.setTypeface(getCustomFont());
        holder.cocktailprice.setTypeface(getCustomFont());






          if ( holder.cocktailimage != null ) {


              Random randomGenerator = new Random();

              int randomWidth = randomGenerator.nextInt(600);
              int randomHeight = randomGenerator.nextInt(600);
              Log.i(TAG, "Generated width,height: " + randomWidth + "," + randomHeight);

              if ( randomHeight < 400) {

                  randomHeight = 400;
              }

              if ( randomWidth < 400) {

                  randomWidth = 400;
              }

              final int radius = 20;
              final int margin = 20;
              final Transformation transformation = new RoundedCornersTransformation(radius, margin);



              Picasso.with(context).load(object.getImage()).transform(transformation).resize(400, 400).into(holder.cocktailimage);

          }


        return view;


    }

    public Typeface getCustomFont() {
        Typeface font = Typeface.createFromAsset(context.getAssets(),"fonts/Marker Felt.ttf");
        return font;
    }




    private static class ViewHolder {
        TextView cocktailname;
        TextView cocktailIngredients;
        TextView cocktailprice;
        ImageView cocktailimage;
    }





}