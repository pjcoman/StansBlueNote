package comapps.stansbluenote.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CocktailListViewAdapter extends BaseAdapter {


    Context context;
    List<CocktailListObject> cocktailObject;


    public CocktailListViewAdapter(Context context, List<CocktailListObject> cocktailObject) {

        this.context = context;
        this.cocktailObject = cocktailObject;

    }


    @Override
    public int getCount() {
        return cocktailObject.size();
    }

    @Override
    public Object getItem(int position) {
        return cocktailObject.get(position);
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

            holder = new ViewHolder();
            holder.cocktailname = (TextView) view.findViewById(R.id.nameTxt);
            holder.cocktailingredients = (TextView) view.findViewById(R.id.ingredients);
            holder.cocktailprice = (TextView) view.findViewById(R.id.price);
            // Locate the ImageView in listview_item.xml

            holder.cocktailimage = (ImageView) view.findViewById(R.id.cocktailimage);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // S
        //
        CocktailListObject object = cocktailObject.get(position);

        holder.cocktailname.setText(object.getCocktailName());
        holder.cocktailingredients.setText(object.getCocktailIngredients());
        holder.cocktailprice.setText(object.getCocktailPrice());

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Marker Felt.ttf");
        holder.cocktailname.setTypeface(font);
        holder.cocktailingredients.setTypeface(font);
        holder.cocktailprice.setTypeface(font);


        Picasso.with(context).load(object.getCocktailImage()).resize(200, 400).into(holder.cocktailimage);

        return view;


    }


    public static class ViewHolder {
        TextView cocktailname;
        TextView cocktailingredients;
        TextView cocktailprice;
        ImageView cocktailimage;
    }


}