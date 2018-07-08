package com.example.android.tourit;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class itemAdapter extends ArrayAdapter<item> {
    public itemAdapter(Activity context, ArrayList<item> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        /**
         * This is where we're going to setup the listItemView
         */
        //Get current item
        item currentItem = getItem(position);

        //Check to see if the current item view is null, if it is create it
        View listItemView = convertView;
        if (listItemView == null) {
            //the view is currently null, create it
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        /**
         * Here is where we update the list view.
         */
        //Set data for the item's name.
        TextView itemName = (TextView) listItemView.findViewById(R.id.item_name);
        itemName.setText(currentItem.itemTitle);

        //Set data for the item's description
        TextView itemDescription = (TextView) listItemView.findViewById(R.id.item_description);
        itemDescription.setText(currentItem.itemDescription);

        //TRY to Set the image for the listItemView IF the item has an image. If error, do nothing.
        //I intended to use IF ELSE statements for this but there is not a direct way to check
        //if a drawable resource is EMPTY.  Even an empty resource will return a resource ID.
        try {
            //Use the image that the item has for the list image!
            ImageView itemImage = (ImageView) listItemView.findViewById(R.id.item_image);
            itemImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            itemImage.setImageResource(currentItem.itemImage);
        } catch (Exception e) {
            //Do nothing as there is already an image set in the layout.
        }

        //Return the listViewItem!
        return listItemView;
    }
}
