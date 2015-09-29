package comapps.stansbluenote.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ParseSpecialsListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;

    private List<SpecialsListObject> specialslist = null;
    private ArrayList<SpecialsListObject> arraylist;


    @SuppressLint("Instantiatable")
    public ParseSpecialsListViewAdapter(Context context, List<SpecialsListObject> specialslist) {


        this.context = context;
        this.specialslist = specialslist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<SpecialsListObject>();
        this.arraylist.addAll(specialslist);


    }

    public class ViewHolder {
        TextView day;
        TextView specials;

    }

    @Override
    public int getCount() {
        return specialslist.size();
    }

    @Override
    public Object getItem(int position) {
        return specialslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;


        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.specialslistrow, null);


            // Locate the TextViews in listview_item.xml


            holder.day = (TextView) view.findViewById(R.id.dayofweekTxt);


            holder.specials = (TextView) view.findViewById(R.id.specialTxt);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // Set the results into TextViews
        holder.day.setText(specialslist.get(position).getDay());
        holder.specials.setText(specialslist.get(position).getSpecial());


        return view;


    }


}