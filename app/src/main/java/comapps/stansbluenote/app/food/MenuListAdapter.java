package comapps.stansbluenote.app.food;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import comapps.stansbluenote.app.R;

public class MenuListAdapter extends BaseAdapter {


    public static final String TAG = "MENULISTADAPTER";

    private final Context context;
    private final List<MenuObject> menuList;


    public MenuListAdapter(Context context, List<MenuObject> menuList) {

        this.context = context;
        this.menuList = menuList;

    }


    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuList.get(position);
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
            view = inflater.inflate(R.layout.menulistrow, parent, false);
            view.setEnabled(false);

            holder = new ViewHolder();


            holder.menuitem = (TextView) view.findViewById(R.id.itemTxt);
            holder.itemprice = (TextView) view.findViewById(R.id.priceTxt);


            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }


        // Set the results into TextViews
        MenuObject object = menuList.get(position);

        Log.i(TAG, "menu item type is " + object.getType());

        holder.menuitem.setText(object.getItem());
        holder.itemprice.setText(object.getPrice());


        String group = object.getType();
        switch (group) {
            case ("cat"):
                holder.itemprice.setTextSize(50);
                holder.itemprice.setTextColor(Color.parseColor("#97B3EF"));
                holder.menuitem.setTextSize(30);
                break;

            default:
                holder.menuitem.setTextColor(Color.parseColor("#97B3EF"));
                holder.itemprice.setTextColor(Color.YELLOW);
                holder.menuitem.setTextSize(20);
                holder.itemprice.setTextSize(20);

        }

        holder.menuitem.setTypeface(getCustomFont());
        holder.itemprice.setTypeface(getCustomFont());



        return view;


    }

    public Typeface getCustomFont() {
        Typeface font = Typeface.createFromAsset(context.getAssets(),"fonts/Marker Felt.ttf");
        return font;
    }




    private static class ViewHolder {
        TextView menuitem;
        TextView itemprice;

    }





}