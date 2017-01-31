package comapps.stansbluenote.app.drinks;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import comapps.stansbluenote.app.R;

public class BeerListAdapter extends BaseAdapter {


    public static final String TAG = "BEERLISTADAPTER";
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private final Context context;
    private final List<BeerObject> beerList;


    public BeerListAdapter(Context context, List<BeerObject> beerList) {

        this.context = context;
        this.beerList = beerList;



    }


    @Override
    public int getCount() {
        return beerList.size();
    }

    @Override
    public Object getItem(int position) {
        return beerList.get(position);
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
     //       holder.beerimage = (ImageView) view.findViewById(R.id.beerimage);





            //holder.beerimage = (ImageView) view.findViewById(R.id.beerimage);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }


        // Set the results into TextViews
        BeerObject object = beerList.get(position);

        String tempName;
        String tempABV;
        String tempWherefrom;
        String tempType;



        tempName = object.getName();
        if ( tempName == null ) {
            tempName = "";
        } else {
            tempName = (object.getName());
        }

        tempABV = object.getAbv();

        if ( tempABV == null ) {
            tempABV = "?";
            Log.i(TAG, "tempABV is " + tempABV);
        }


        tempType = object.getType();
        if ( tempType == null ) {
            tempType = "";
        } else {
            tempType = (object.getType());
        }

      tempWherefrom = object.getWherefrom();
        if ( tempWherefrom == null ) {
            tempWherefrom = "";
        } else {
            tempWherefrom = (object.getWherefrom());
        }


        if ( tempName.equals("")) {
            holder.beername.setVisibility(View.GONE);
        } else {
            holder.beername.setVisibility(View.VISIBLE);
        }



        if ( tempABV.equals("")) {
            holder.beerabv.setVisibility(View.GONE);
        } else {
            holder.beerabv.setVisibility(View.VISIBLE);
        }









        holder.beername.setText(tempName);
        holder.beerwherefrom.setText(tempWherefrom);
        holder.beerabv.setText(tempABV);
        holder.beertype.setText(tempType);

        holder.beername.setTypeface(getCustomFont());
        holder.beerwherefrom.setTypeface(getCustomFont());
        holder.beerabv.setTypeface(getCustomFont());
        holder.beertype.setTypeface(getCustomFont());

        holder.beername.setTextSize(26);
        holder.beertype.setTextSize(26);
        holder.beerabv.setTextSize(22);   




/* if ( holder.beerimage != null ) {

    Random randomGenerator = new Random();

    int randomWidth = randomGenerator.nextInt(500);
    int randomHeight = randomGenerator.nextInt(400);
    Log.i(TAG, "Generated width,height: " + randomWidth + "," + randomHeight);

    if ( randomHeight < 300) {

        randomHeight = 300;
    }

    if ( randomWidth < 300) {

        randomWidth = 300;
    }

    final int radius = 20;
    final int margin = 20;
    final Transformation transformation = new RoundedCornersTransformation(radius, margin);



    Picasso
            .with(context).setIndicatorsEnabled(false);
    Picasso
            .with(context)
            .setLoggingEnabled(true);

    if ( !object.getImage().isEmpty() ) {

        Picasso.with(context).load(object.getImage()).transform(transformation).resize(randomWidth, randomHeight).centerInside().into(holder.beerimage);

    }*/





        return view;


    }


    public Typeface getCustomFont() {
        Typeface font = Typeface.createFromAsset(context.getAssets(),"fonts/Marker Felt.ttf");
        return font;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }



    private static class ViewHolder {
        TextView beername;
        TextView beerwherefrom;

        TextView beerabv;
        TextView beertype;
        ImageView beerimage;
    }





}