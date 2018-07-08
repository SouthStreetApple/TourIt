package com.example.android.tourit;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class foodFragment extends android.support.v4.app.Fragment{

    ListView foodsList;
    int numberOfFoods;
    ArrayList<item> foods;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_food, viewgroup,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        /**
         * Set the ListView variable
         * We have to do this here because in onCreateView findViewById isn't available.
         */
        foodsList = view.findViewById(R.id.foods_list);
        /**
         * Allows us to show the selected item as highlighted.
         * URL: https://stackoverflow.com/questions/5925892/how-to-highlight-row-in-listview-in-android
         */
        foodsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        /**
         * Here is where we want to load the attractions into memory.
         */
        //Get number of attractions
        numberOfFoods = getResources().getInteger(R.integer.number_of_foods);
        //Create the new ArrayList
        foods = new ArrayList<item>();
        //Load attractions into memory!
        loadAttractions();

        /**
         * Now we want to set the onItemClickListener
         * Now we grab the index of them item in the list that was clicked and
         * use that to look up the current item in the attractions array.
         */
        foodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int selected_item, long l) {
                //Here is where we will have code to open the details view that will show more information on the attraction, event, etc.
                Toast.makeText(getActivity().getApplicationContext(),foods.get(selected_item).itemTitle, Toast.LENGTH_SHORT).show();
                //This is where we will use an intent to switch to the details screen to show more item information!
                Intent i = new Intent(getActivity().getApplicationContext(), itemDetails.class);
                //Send data that we will need later...
                i.putExtra("items", foods);
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


        for (x = 1; x < (numberOfFoods + 1); x++) {
            /*Create new instance of the custom class item*/
            item i = new item();
            /*Grab resource IDs for each (x) item from the strings resource file*/
            eventTitleID = getResources().getIdentifier("food_" + (x) + "_title", "string", getActivity().getPackageName());
            eventAddressID = getResources().getIdentifier("food_" + (x) + "_address", "string", getActivity().getPackageName());
            eventCoordinatesID = getResources().getIdentifier("food_" + (x) + "_coordinates", "string", getActivity().getPackageName());
            eventDescriptionID = getResources().getIdentifier("food_" + (x) + "_description", "string", getActivity().getPackageName());
            eventTimeStartID = getResources().getIdentifier("food_" + (x) + "_time_start", "string",getActivity().getPackageName());
            eventTimeStopID = getResources().getIdentifier("food_" + (x) + "_time_end", "string", getActivity().getPackageName());
            eventImageID = getResources().getIdentifier("food_" + (x) + "_image", "drawable", getActivity().getPackageName());
            eventEmailID = getResources().getIdentifier("food_" + (x) + "_email", "string", getActivity().getPackageName());
            eventPhoneID = getResources().getIdentifier("food_" + (x) + "_phone", "string", getActivity().getPackageName());
            /**
             * Grabs the attractions data information from the strings resource file using the above
             * resource IDs and ads them to the custom class i, item.
             */
            i.setItem(getString(eventTitleID), getString(eventAddressID), getString(eventCoordinatesID), getString(eventDescriptionID), getString(eventTimeStartID), getString(eventTimeStopID), getString(eventEmailID), getString(eventPhoneID), (eventImageID));
            /*Add the custom class s to the ArrayList songs*/
            foods.add(i);
            Log.e("LOADING ARRAY", i.itemTitle);
        }
        Log.e("TOTAL", String.valueOf(foods.size()));

        /**
         * This creates a new custom adaptor sa, songAdaptor, using this context
         * and the ArrayList songs, then sets the adaptor to the ListView songList
         */
        itemAdapter sa = new itemAdapter(getActivity(), foods);
        foodsList.setAdapter(sa);
    }


}
