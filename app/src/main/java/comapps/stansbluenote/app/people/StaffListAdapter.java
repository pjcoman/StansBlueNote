package comapps.stansbluenote.app.people;

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

public class StaffListAdapter extends BaseAdapter {


    public static final String TAG = "STAFFLISTADAPTER";

    private final Context context;
    private final List<StaffObject> staffList;


    public StaffListAdapter(Context context, List<StaffObject> staffList) {

        this.context = context;
        this.staffList = staffList;

    }


    @Override
    public int getCount() {
        return staffList.size();
    }

    @Override
    public Object getItem(int position) {
        return staffList.get(position);
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
            view.setEnabled(false);

            holder = new ViewHolder();


            holder.staffname = (TextView) view.findViewById(R.id.nameTxt);
            holder.staffother = (TextView) view.findViewById(R.id.otherTxt);

            holder.staffimage = (ImageView) view.findViewById(R.id.staffimage);




            //holder.beerimage = (ImageView) view.findViewById(R.id.beerimage);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }


        // Set the results into TextViews
        StaffObject object = staffList.get(position);





        holder.staffname.setText(object.getName());
        holder.staffother.setText(object.getOther());

        holder.staffname.setTypeface(getCustomFont());
        holder.staffother.setTypeface(getCustomFont());

        Random randomGenerator = new Random();

        int randomWidth = randomGenerator.nextInt(1000);
        int randomHeight = randomGenerator.nextInt(1000);
        Log.i(TAG, "Generated width,height: " + randomWidth + "," + randomHeight);

        if ( randomWidth < 700) {

            randomWidth = 700;
        }

        randomHeight = randomWidth;

        final int radius = 20;
        final int margin = 20;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);



        Picasso.with(context).load(object.getImage()).transform(transformation).resize(randomWidth, randomHeight).into(holder.staffimage);






        return view;


    }

    public Typeface getCustomFont() {
        Typeface font = Typeface.createFromAsset(context.getAssets(),"fonts/Marker Felt.ttf");
        return font;
    }




    private static class ViewHolder {
        TextView staffname;
        TextView staffother;

        ImageView staffimage;
    }





}