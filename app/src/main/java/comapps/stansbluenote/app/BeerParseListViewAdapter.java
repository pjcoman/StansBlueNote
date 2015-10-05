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

public class BeerParseListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<BeerListObject> beerlist = null;
    private ArrayList<BeerListObject> arraylist;


    @SuppressLint("Instantiatable")
    public BeerParseListViewAdapter(Context context, List<BeerListObject> beerlist) {


        this.context = context;
        this.beerlist = beerlist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<BeerListObject>();
        this.arraylist.addAll(beerlist);
        imageLoader = new ImageLoader(context);

    }


    public class ViewHolder {
        TextView beername;
        TextView beerwherefrom;
        TextView beerabv;
        TextView beertype;
        ImageView beerimage;
    }

    @Override
    public int getCount() {
        return beerlist.size();
    }

    @Override
    public Object getItem(int position) {
        return beerlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;


        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.beerlistrow, null);


            holder.beername = (TextView) view.findViewById(R.id.nameTxt);


            holder.beerwherefrom = (TextView) view.findViewById(R.id.wherefromTxt);


            holder.beerabv = (TextView) view.findViewById(R.id.abvTxt);

            holder.beertype = (TextView) view.findViewById(R.id.beertypeTxt);
            // Locate the ImageView in listview_item.xml

            holder.beerimage = (ImageView) view.findViewById(R.id.beerimage);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.beername.setText(beerlist.get(position).getBeerName());
        holder.beerwherefrom.setText(beerlist.get(position).getBeerWhereFrom());
        holder.beerabv.setText(beerlist.get(position).getBeerAbv());
        holder.beertype.setText(beerlist.get(position).getBeerType());


        // Set the results into ImageView
        imageLoader.DisplayImage(beerlist.get(position).getBeerImage(),
                holder.beerimage);


        return view;


    }


}