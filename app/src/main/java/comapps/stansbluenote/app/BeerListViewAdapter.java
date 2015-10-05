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

public class BeerListViewAdapter extends BaseAdapter {


    Context context;
    List<BeerListObject> beerObject;


    public BeerListViewAdapter(Context context, List<BeerListObject> beerObject) {

        this.context = context;
        this.beerObject = beerObject;

    }


    @Override
    public int getCount() {
        return beerObject.size();
    }

    @Override
    public Object getItem(int position) {
        return beerObject.get(position);
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
            view = inflater.inflate(R.layout.beerlistrow, parent, false);

            holder = new ViewHolder();

            holder.beername = (TextView) view.findViewById(R.id.nameTxt);
            holder.beerwherefrom = (TextView) view.findViewById(R.id.wherefromTxt);
            holder.beerabv = (TextView) view.findViewById(R.id.abvTxt);
            holder.beertype = (TextView) view.findViewById(R.id.beertypeTxt);
            holder.beerimage = (ImageView) view.findViewById(R.id.beerimage);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }
        // Set the results into TextViews
        BeerListObject object = beerObject.get(position);

        holder.beername.setText(object.getBeerName());
        holder.beerwherefrom.setText(object.getBeerWhereFrom());
        holder.beerabv.setText(object.getBeerAbv());
        holder.beertype.setText(object.getBeerType());

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Marker Felt.ttf");
        holder.beername.setTypeface(font);
        holder.beerwherefrom.setTypeface(font);
        holder.beerabv.setTypeface(font);
        holder.beertype.setTypeface(font);

        Picasso.with(context).load(object.getBeerImage()).resize(200, 400).into(holder.beerimage);

        return view;


    }


    private static class ViewHolder {
        TextView beername;
        TextView beerwherefrom;
        TextView beerabv;
        TextView beertype;
        ImageView beerimage;
    }


}