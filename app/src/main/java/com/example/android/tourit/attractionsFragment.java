
/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Adapted for TourIt
 */

package com.example.android.tourit;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.android.tourit.R;

import java.util.ArrayList;
import java.util.List;


/**
 * This will inflate the fragment that will display Attractions
 */
public class attractionsFragment extends android.support.v4.app.Fragment{

    ListView attractionsList;
    int numberOfAttractions;
    ArrayList<item> attractions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_attractions, viewgroup,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        /**
         * Set the ListView variable
         * We have to do this here because in onCreateView findViewById isn't available.
         */
        attractionsList = view.findViewById(R.id.attractions_list);
        /**
         * Allows us to show the selected song as highlighted.
         * URL: https://stackoverflow.com/questions/5925892/how-to-highlight-row-in-listview-in-android
         */
        attractionsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        /**
         * Here is where we want to load the attractions into memory.
         */
        //Get number of attractions
        numberOfAttractions = getResources().getInteger(R.integer.number_of_attractions);
        //Create the new ArrayList
        attractions = new ArrayList<item>();
        //Load attractions into memory!
        loadAttractions();

        /**
         * Now we want to set the onItemClickListener
         * Now we grab the index of them item in the list that was clicked and
         * use that to look up the current item in the attractions array.
         */
        attractionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int selected_item, long l) {
                //Here is where we will have code to open the details view that will show more information on the attraction, event, etc.
                Toast.makeText(getActivity().getApplicationContext(),attractions.get(selected_item).itemTitle, Toast.LENGTH_SHORT).show();
                //This is where we will use an intent to switch to the details screen to show more item information!
                Intent i = new Intent(getActivity().getApplicationContext(), itemDetails.class);
                //Send data that we will need later...
                i.putExtra("items", attractions);
                i.putExtra("selectedItems", selected_item);
                startActivity(i);
            }
        });

    }

    private void loadAttractions(){
        /**
         * Special Thanks to Neural & BigMikeDog from the Grow with Google Scholarship Slack.
         * They were instrumental in helping me debug my array usage and getIdentifier issues.
         * This loads the attractions into an ArrayList which is then passed to Adaptor and then utilized
         * by a ListView to show the contents.
         */
        int x;

        int eventTitleID;
        int eventAddressID;
        int eventCoordinatesID;
        int eventDescriptionID;
        int eventTimeStartID;
        int eventTimeStopID;
        int eventImageID;
        int eventEmailID;
        int eventPhoneID;


        for (x = 1; x < (numberOfAttractions + 1); x++) {
            /*Create new instance of the custom class item*/
            item i = new item();
            /*Grab resource IDs for each (x) item from the strings resource file*/
            eventTitleID = getResources().getIdentifier("attraction_" + (x) + "_title", "string", getActivity().getPackageName());
            eventAddressID = getResources().getIdentifier("attraction_" + (x) + "_address", "string", getActivity().getPackageName());
            eventCoordinatesID = getResources().getIdentifier("attraction_" + (x) + "_coordinates", "string", getActivity().getPackageName());
            eventDescriptionID = getResources().getIdentifier("attraction_" + (x) + "_description", "string", getActivity().getPackageName());
            eventTimeStartID = getResources().getIdentifier("attraction_" + (x) + "_time_start", "string",getActivity().getPackageName());
            eventTimeStopID = getResources().getIdentifier("attraction_" + (x) + "_time_end", "string", getActivity().getPackageName());
            eventImageID = getResources().getIdentifier("attraction_" + (x) + "_image", "drawable", getActivity().getPackageName());
            eventEmailID = getResources().getIdentifier("attraction_" + (x) + "_email", "string", getActivity().getPackageName());
            eventPhoneID = getResources().getIdentifier("attraction_" + (x) + "_phone", "string", getActivity().getPackageName());
            /**
             * Grabs the attractions data information from the strings resource file using the above
             * resource IDs and ads them to the custom class i, item.
             */
            i.setItem(getString(eventTitleID), getString(eventAddressID), getString(eventCoordinatesID), getString(eventDescriptionID), getString(eventTimeStartID), getString(eventTimeStopID), getString(eventEmailID), getString(eventPhoneID), (eventImageID));
            /*Add the custom class s to the ArrayList songs*/
            attractions.add(i);
            Log.e("LOADING ARRAY", i.itemTitle);
        }
        Log.e("TOTAL", String.valueOf(attractions.size()));

        /**
         * This creates a new custom adaptor sa, songAdaptor, using this context
         * and the ArrayList songs, then sets the adaptor to the ListView songList
         */
        itemAdapter sa = new itemAdapter(getActivity(), attractions);
        attractionsList.setAdapter(sa);
    }


}
