package comapps.stansbluenote.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CocktailParseListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<CocktailListObject> cocktaillist = null;
    private ArrayList<CocktailListObject> arraylist;


    @SuppressLint("Instantiatable")
    public CocktailParseListViewAdapter(Context context, List<CocktailListObject> cocktaillist) {


        this.context = context;
        this.cocktaillist = cocktaillist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<CocktailListObject>();
        this.arraylist.addAll(cocktaillist);
        imageLoader = new ImageLoader(context);

    }

    public class ViewHolder {
        TextView cocktailname;
        TextView cocktailingredients;
        TextView cocktailprice;
        ImageView cocktailimage;
    }

    @Override
    public int getCount() {
        return cocktaillist.size();
    }

    @Override
    public Object getItem(int position) {
        return cocktaillist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;


        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.cocktaillistrow, null);


            // Locate the TextViews in listview_item.xml


            holder.cocktailname = (TextView) view.findViewById(R.id.nameTxt);


            holder.cocktailingredients = (TextView) view.findViewById(R.id.ingredients);


            holder.cocktailprice = (TextView) view.findViewById(R.id.price);


            // Locate the ImageView in listview_item.xml

            holder.cocktailimage = (ImageView) view.findViewById(R.id.cocktailimage);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.cocktailname.setText(cocktaillist.get(position).getCocktailName());
        holder.cocktailingredients.setText(cocktaillist.get(position).getCocktailIngredients());
        holder.cocktailprice.setText(cocktaillist.get(position).getCocktailPrice());


        // Set the results into ImageView
        imageLoader.DisplayImage(cocktaillist.get(position).getCocktailImage(),
                holder.cocktailimage);
        
        
        
       
       /* 
        
        
      	// Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("name",
                        (cocktaillist.get(position).getCocktailName()));
                // Pass all data country
                intent.putExtra("wherefrom",
                        (cocktaillist.get(position).getCocktailIngredients()));
                // Pass all data population
                intent.putExtra("abv",
                        (cocktaillist.get(position).getCocktailPrice()));
                // Pass all data flag
                intent.putExtra("image",
                     cocktaillist.get(position).getCocktailImage()));
                // Start SingleItemView Class
                context.startActivity(intent);
            }
        });
        
        */
        return view;


    }


}