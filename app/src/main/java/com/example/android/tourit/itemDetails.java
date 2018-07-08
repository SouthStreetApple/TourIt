package com.example.android.tourit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class itemDetails extends AppCompatActivity {


    ArrayList<item> items;
    int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        /**
         * Load the variables from the previous screen & sets the View(s) info.
         */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //Load the item array!
            items = extras.getParcelableArrayList("items");
            //Gets which item we are working with
            selectedItem = extras.getInt("selectedItem");
            //Sets the title of the item
            TextView itemName = (TextView) findViewById(R.id.item_name);
            itemName.setText(items.get(selectedItem).itemTitle);
            //Sets the image for the item detail
            //We use TRY CATCH here to determine id the drawable resource is valid.
            ImageView itemImage = (ImageView) findViewById(R.id.item_image);
            try {
                itemImage.setImageResource(items.get(selectedItem).itemImage);
            } catch (Exception e) {
                //Hide field
                itemImage.setVisibility(View.GONE);
            }
            //Sets item description
            TextView itemDescription = (TextView) findViewById(R.id.item_description);
            itemDescription.setText(items.get(selectedItem).itemDescription);
            //Sets item address (check to see if address is avail or if we need coordinates
            TextView itemAddress = (TextView) findViewById(R.id.item_address);
            if (items.get(selectedItem).itemAddress.equalsIgnoreCase("")) {
                if (items.get(selectedItem).itemCoordinates.equalsIgnoreCase("")) {
                    //Hide field
                    itemAddress.setVisibility(View.GONE);
                } else {
                    //We will use coordinates if address is blank.
                    itemAddress.setText(items.get(selectedItem).itemCoordinates);
                }
            } else {
                //We will use the address
                itemAddress.setText(items.get(selectedItem).itemAddress);
            }
            //Sets Time/Date Stuff
            TextView itemTime = (TextView) findViewById(R.id.item_time);
            if (items.get(selectedItem).itemTimeEnd.equalsIgnoreCase("")) {
                if (items.get(selectedItem).itemTimeStart.equalsIgnoreCase("")) {
                    //Hide Field
                    itemTime.setVisibility(View.GONE);
                } else {
                    itemTime.setText(items.get(selectedItem).itemTimeStart);
                }
            } else {
                itemTime.setText(items.get(selectedItem).itemTimeStart + " - " + items.get(selectedItem).itemTimeEnd);
            }
            //Set Phone
            if (items.get(selectedItem).itemPhone.equalsIgnoreCase("")) {
                //Hide field
                TextView itemPhone = (TextView) findViewById(R.id.item_phone);
                itemPhone.setVisibility(View.GONE);
            } else {
                TextView itemPhone = (TextView) findViewById(R.id.item_phone);
                itemPhone.setText(items.get(selectedItem).itemPhone);
            }
            //Set Email
            if (items.get(selectedItem).itemEmail.equalsIgnoreCase("")) {
                //Hide field
                TextView itemEmail = (TextView) findViewById(R.id.item_email);
                itemEmail.setVisibility(View.GONE);
            } else {
                TextView itemEmail = (TextView) findViewById(R.id.item_email);
                itemEmail.setText(items.get(selectedItem).itemEmail);
            }

        }

    }
}
