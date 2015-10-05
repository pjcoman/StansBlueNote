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

/**
 * Created by me on 9/29/2015.
 */
public class StaffListViewAdapter extends BaseAdapter {


    Context context;
    List<StaffListObject> staffObject;


    public StaffListViewAdapter(Context context, List<StaffListObject> staffObject) {

        this.context = context;
        this.staffObject = staffObject;

    }


    @Override
    public int getCount() {
        return staffObject.size();
    }

    @Override
    public Object getItem(int position) {
        return staffObject.get(position);
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
            view = inflater.inflate(R.layout.stafflistrow, parent, false);

            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.nameTxt);
            holder.other = (TextView) view.findViewById(R.id.otherTxt);


            holder.image = (ImageView) view.findViewById(R.id.image);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // S
        //
        StaffListObject object = staffObject.get(position);

        holder.name.setText(object.getName());
        holder.other.setText(object.getOther());

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Marker Felt.ttf");
        holder.name.setTypeface(font);
        holder.other.setTypeface(font);


        Picasso.with(context).load(object.getImage()).resize(200, 400).into(holder.image);

        return view;


    }


    public static class ViewHolder {
        TextView name;
        TextView other;
        ImageView image;
    }


}