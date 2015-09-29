package comapps.stansbluenote.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by me on 6/4/2015.
 */
public class ParseMenuListViewExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private HashMap<String, List<String>> Menu_Category;
    private List<String> Menu_List;




    public ParseMenuListViewExpandableAdapter(Context context, HashMap<String, List<String>> Menu_Category, List<String> Menu_List) {

        this.context = context;
        this.Menu_Category = Menu_Category;
        this.Menu_List = Menu_List;

    }




    @Override
    public int getGroupCount() {
        return Menu_List.size();
    }

    @Override
    public int getChildrenCount(int arg0) {
        return Menu_Category.get(Menu_List.get(arg0)).size();
    }

    @Override
    public Object getGroup(int arg0) {
        return Menu_List.get(arg0);
    }

    @Override
    public Object getChild(int parent, int child) {
        return Menu_Category.get(Menu_List.get(parent)).get(child);
    }

    @Override
    public long getGroupId(int arg0) {
        return arg0;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentview) {
        String group_title = (String) getGroup(parent);
        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menulist_parent, parentview, false);
        }

        TextView parent_textview = (TextView) convertView.findViewById(R.id.menuListParent_txt);
     // parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);

        return convertView;

    }

    @Override
    public View getChildView(int parent, int child, boolean lastChild, View convertView, ViewGroup parentview) {

        String child_title = (String) getChild(parent, child);
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menulist_child, parentview, false);


        }



        TextView child_textview = (TextView) convertView.findViewById(R.id.menuListChild_txt);
        child_textview.setText(child_title);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
