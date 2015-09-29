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

public class StaffParseListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<StaffListObject> stafflist = null;
    private ArrayList<StaffListObject> arraylist;


    @SuppressLint("Instantiatable")
    public StaffParseListViewAdapter(Context context, List<StaffListObject> stafflist) {


        this.context = context;
        this.stafflist = stafflist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<StaffListObject>();
        this.arraylist.addAll(stafflist);
        imageLoader = new ImageLoader(context);

    }

    public class ViewHolder {
        TextView name;
        TextView other;
        ImageView image;
    }

    @Override
    public int getCount() {
        return stafflist.size();
    }

    @Override
    public Object getItem(int position) {
        return stafflist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;


        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.stafflistrow, null);


            // Locate the TextViews in listview_item.xml


            holder.name = (TextView) view.findViewById(R.id.nameTxt);


            holder.other = (TextView) view.findViewById(R.id.otherTxt);





            // Locate the ImageView in listview_item.xml

            holder.image = (ImageView) view.findViewById(R.id.image);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(stafflist.get(position).getName());
        holder.other.setText(stafflist.get(position).getOther());



        // Set the results into ImageView
        imageLoader.DisplayImage(stafflist.get(position).getImage(),
                holder.image);




       /*


      	// Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("name",
                        (stafflist.get(position).getName()));
                // Pass all data country
                intent.putExtra("wherefrom",
                        (stafflist.get(position).getOther()));
                // Pass all data population

                intent.putExtra("image",
                     stafflist.get(position).getImage()));
                // Start SingleItemView Class
                context.startActivity(intent);
            }
        });

        */
        return view;


    }


}
