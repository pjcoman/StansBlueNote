package comapps.stansbluenote.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SpecialsListViewAdapter extends BaseAdapter {


    Context context;
    List<SpecialsListObject> specialsObject;


    public SpecialsListViewAdapter(Context context, List<SpecialsListObject> specialsObject) {

        this.context = context;
        this.specialsObject = specialsObject;

    }


    @Override
    public int getCount() {
        return specialsObject.size();
    }

    @Override
    public Object getItem(int position) {
        return specialsObject.get(position);
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

            holder = new ViewHolder();
            holder.day = (TextView) view.findViewById(R.id.dayofweekTxt);
            holder.specials = (TextView) view.findViewById(R.id.specialTxt);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // S
        //
        SpecialsListObject object = specialsObject.get(position);

        holder.day.setText(object.getDay());
        holder.specials.setText(object.getSpecial());

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Marker Felt.ttf");
        holder.day.setTypeface(font);
        holder.specials.setTypeface(font);


        return view;


    }


    public class ViewHolder {
        TextView day;
        TextView specials;

    }


}