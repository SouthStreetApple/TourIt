package com.example.android.tourit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class itemDetails extends AppCompatActivity {


    ArrayList<item> attractions;
    int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        /**
         * Load the variables from the previous screen
         */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //Load the item array!
            attractions = extras.getParcelableArrayList("items");
            //Gets which item we are working with
            selectedItem = extras.getInt("selectedItem");
            //Sets the title of the item
            TextView itemName = (TextView) findViewById(R.id.item_name);
            itemName.setText(attractions.get(selectedItem).itemTitle);
            //Sets the image for the item detail
            ImageView itemImage = (ImageView) findViewById(R.id.item_image);
            itemImage.setImageResource(attractions.get(selectedItem).itemImage);
            //Sets item description
            TextView itemDescription = (TextView) findViewById(R.id.item_description);
            itemDescription.setText(attractions.get(selectedItem).itemDescription);
            //Sets item address (check to see if address is avail or if we need coordinates
            TextView itemAddress = (TextView) findViewById(R.id.item_address);
            if (attractions.get(selectedItem).itemAddress.equalsIgnoreCase("") ){
                //We not will use coordinates if address is blank.
                itemAddress.setText(attractions.get(selectedItem).itemCoordinates);
            } else {
                //We will use the address
                itemAddress.setText(attractions.get(selectedItem).itemAddress);
            }
            //Sets Time/Date Stuff
            TextView itemTime = (TextView) findViewById(R.id.item_time);
            if (attractions.get(selectedItem).itemTimeEnd.equalsIgnoreCase("")){
                itemTime.setText(attractions.get(selectedItem).itemTimeStart);
            } else {
                itemTime.setText(attractions.get(selectedItem).itemTimeStart + " - " + attractions.get(selectedItem).itemTimeEnd);
            }
            //Set email
            TextView itemPhone = (TextView) findViewById(R.id.item_phone);
            itemPhone.setText(attractions.get(selectedItem).itemPhone);
            //Set Phone
            TextView itemEmail = (TextView) findViewById(R.id.item_email);
            itemEmail.setText(attractions.get(selectedItem).itemEmail);


        }

    }
}
