package comapps.stansbluenote.app.specials;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import comapps.stansbluenote.app.R;

public class SpecialsListAdapter extends BaseAdapter {


    public static final String TAG = "SPECIALSLISTADAPTER";

    private final Context context;
    private final List<SpecialsObject> specialsList;


    public SpecialsListAdapter(Context context, List<SpecialsObject> specialsList) {

        this.context = context;
        this.specialsList = specialsList;

    }


    @Override
    public int getCount() {
        return specialsList.size();
    }

    @Override
    public Object getItem(int position) {
        return specialsList.get(position);
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
            view = inflater.inflate(R.layout.specialslistrow, parent, false);
            view.setEnabled(false);

            holder = new ViewHolder();


            holder.dayofweek = (TextView) view.findViewById(R.id.dayofweekTxt);
            holder.special = (TextView) view.findViewById(R.id.specialTxt);




            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }


        // Set the results into TextViews
        SpecialsObject object = specialsList.get(position);




        holder.dayofweek.setText(object.getDayofweek());
        holder.special.setText(object.getSpecial());

        holder.dayofweek.setTypeface(getCustomFont());
        holder.special.setTypeface(getCustomFont());







        return view;


    }

    public Typeface getCustomFont() {
        Typeface font = Typeface.createFromAsset(context.getAssets(),"fonts/Marker Felt.ttf");
        return font;
    }




    private static class ViewHolder {
        TextView dayofweek;
        TextView special;

    }





}