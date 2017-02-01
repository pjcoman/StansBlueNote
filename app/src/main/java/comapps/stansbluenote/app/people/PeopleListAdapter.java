package comapps.stansbluenote.app.people;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ParseException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.backendless.BackendlessUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import comapps.stansbluenote.app.R;

class PeopleListAdapter extends BaseAdapter {


    private static final String TAG = "PEOPLELISTADAPTER";

    private final Context context;
    private final List<BackendlessUser> peopleList;




    public PeopleListAdapter(Context context, List<BackendlessUser> peopleList) {

        this.context = context;
        this.peopleList = peopleList;

    }


    @Override
    public int getCount() {
        return peopleList.size();
    }

    @Override
    public Object getItem(int position) {
        return peopleList.get(position);
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
            view = inflater.inflate(R.layout.peoplelistrow, parent, false);
            view.setEnabled(false);

            holder = new ViewHolder();


            holder.nameTV = (TextView) view.findViewById(R.id.nameTxt);
            holder.timeTV = (TextView) view.findViewById(R.id.timeTxt);
            holder.favsTV = (TextView) view.findViewById(R.id.textViewFavs);
            holder.leaderboardTV = (TextView) view.findViewById(R.id.textViewLeaderboard);



            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }


        // Set the results into TextViews
        BackendlessUser user = peopleList.get(position);

        String name = (String) user.getProperty("name");
        String favorites = (String) user.getProperty("favorites");
        String leaderBoardString = (String) user.getProperty("leaderboard");


        if ( leaderBoardString == null) {
            leaderBoardString = "0000000";


        }

        char[] leaderBoardChars = leaderBoardString.toCharArray();

        int score = 0;

        for ( char day: leaderBoardChars) {

            score = score - Character.getNumericValue(day);

            Log.i(TAG, "score " + score);

        }






        Log.i(TAG, user.toString() + "last updated at " + user.getProperty("updated"));
        Log.i(TAG, "name " + name);
        Log.i(TAG, "favs " + favorites);
        Log.i(TAG, "leaderboard " + leaderBoardString);



        Date dateUpdated = (Date) user.getProperty("updated");

        if ( dateUpdated == null ) {
            dateUpdated = new Date(System.currentTimeMillis());
        }

        Log.i(TAG, "dateUpdated " + dateUpdated.toString());


        Date currentDateMinus2Hours = new Date(System.currentTimeMillis() - 1000*60*60*2);

        Calendar updatedDay = DateToCalendar(dateUpdated);
        Calendar today = Calendar.getInstance();

        long diff = today.getTimeInMillis() - updatedDay.getTimeInMillis();
        int diffInDays = (int) (diff/(1000*60*60*24));

        Log.i(TAG, "user last update " + dateUpdated);
        Log.i(TAG, "current date " + currentDateMinus2Hours);
        //    holder.name.setTextSize(0);




        if ( dateUpdated.before(currentDateMinus2Hours))   {

            holder.nameTV.setTextColor(Color.WHITE);


            holder.nameTV.setTextSize(10);
            holder.nameTV.setText(name + "   " + diffInDays + " days since last update");
            holder.timeTV.setText("");
            holder.favsTV.setVisibility(View.GONE);
            holder.leaderboardTV.setVisibility(View.GONE);


            Log.i(TAG, "IF");

        } else {

            holder.favsTV.setVisibility(View.VISIBLE);
            holder.leaderboardTV.setVisibility(View.VISIBLE);

            Log.i(TAG, "ELSE");

            holder.nameTV.setText(name);

            holder.nameTV.setTextColor(Color.parseColor("#97B3EF"));
            holder.nameTV.setTextSize(20);
            holder.timeTV.setText(android.text.format.DateFormat.format("hh:mm:ss a", new java.util.Date()));

            if ( String.valueOf(score) == "0") {

                holder.leaderboardTV.setText("E");

            } else {

                holder.leaderboardTV.setText(String.valueOf(score) + " to bar");

            }


            if ( favorites != null ) {

                holder.favsTV.setText(favorites);

            } else {
                holder.favsTV.setText("select favorites from main menu");
            }




        }

        return view;

    }


    private static class ViewHolder {
        TextView nameTV;
        TextView timeTV;
        TextView favsTV;
        TextView leaderboardTV;



    }

    private char LastChar(String a){
        return a.charAt(a.length() - 1);
    }

    private char FirstChar(String a){
        return a.charAt(0);
    }

    public static Calendar DateToCalendar(Date date )
    {
        Calendar cal = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            try {
                date = formatter.parse(date.toString());
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            cal=Calendar.getInstance();
            cal.setTime(date);
        }
        catch (ParseException e)
        {
            System.out.println("Exception :"+e);
        }
        return cal;
    }


}