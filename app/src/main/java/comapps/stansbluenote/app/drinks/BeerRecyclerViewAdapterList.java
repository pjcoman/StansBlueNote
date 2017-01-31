package comapps.stansbluenote.app.drinks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import comapps.stansbluenote.app.R;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by me on 5/18/2016.
 */
public class BeerRecyclerViewAdapterList extends RecyclerView.Adapter<BeerRecyclerViewAdapterList.ViewHolder> {

    private static final String EMPTY_STRING = "";
    private static final String TAG = "BEERRECYCLERVIEWVIEWADAPTER";
    private final List<BeerObject> beerList;

    private Context context;




    public BeerRecyclerViewAdapterList(List<BeerObject> beerList, Context context) {

        this.context = context;
        this.beerList = beerList;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.beerlistrowrecyclerviewlist, parent, false);


        return new ViewHolder(v);

    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {



        final BeerObject beerObject = beerList.get(position);
        holder.bind(beerObject);





    }




    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return beerList.size();
    }






    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView beername;
        TextView beerwherefrom;
        TextView beerabvlabel;
        TextView beerabv;
        TextView beertype;
        ImageView beerimage;



        ViewHolder(View view) {
            super(view);
            beername = (TextView) view.findViewById(R.id.nameTxt);
            beerwherefrom = (TextView) view.findViewById(R.id.wherefromTxt);
            beerabv = (TextView) view.findViewById(R.id.abvTxt);
            beertype = (TextView) view.findViewById(R.id.beertypeTxt);
            beerimage = (ImageView) view.findViewById(R.id.beerimage);
            beerabvlabel = (TextView) view.findViewById(R.id.abvTxtlabel);




        }

        public void bind(BeerObject beerObject) {

            beername.setText(beerObject.getName());
            beerwherefrom.setText(beerObject.getWherefrom());
            beerabv.setText(beerObject.getAbv());
            beertype.setText(beerObject.getType() + " from ");
            beerabvlabel.setText("ABV");



            if ( !beerObject.getImage().isEmpty() ) {

                final int radius = 20;
                final int margin = 20;
                final Transformation transformation = new RoundedCornersTransformation(radius, margin);


                Picasso.with(context).load(beerObject.getImage()).transform(transformation).resize(500,500).centerInside().into(beerimage);


            }


        }




    }




}

