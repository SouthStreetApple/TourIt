package com.example.android.tourit;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class itemAdapter  extends ArrayAdapter<item>{
    public itemAdapter(Activity context, ArrayList<item> objects){
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        //Get current item
        item currentItem = getItem(position);

        //Check to see if the current item view is null, if it is create it
        View listItemView = convertView;
        if(listItemView == null){
            //the view is currently null, create it
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        //Here is where we update the list view, this is where things get complicated!
        //We need to know when we are working with an event, an attraction, food, public spaces, etc.
        //If we don't have data for any fields, hide these fields, make them invisible on a future version

        //Set data for the item's name.
        TextView itemName = (TextView) listItemView.findViewById(R.id.item_name);
        itemName.setText(currentItem.itemTitle);

        //Set data for the item's description
        TextView itemDescription = (TextView) listItemView.findViewById(R.id.item_description);
        itemDescription.setText(currentItem.itemDescription);

        //Return the listViewItem!
        return listItemView;
    }
}
