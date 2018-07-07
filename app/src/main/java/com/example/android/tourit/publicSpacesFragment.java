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

public class publicSpacesFragment extends android.support.v4.app.Fragment {

    ListView publicSpacesList;
    int numberOfPublicSpaces;
    ArrayList<item> publicSpaces;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_public_spaces, viewgroup, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        /**
         * Set the ListView variable
         * We have to do this here because in onCreateView findViewById isn't available.
         */
        publicSpacesList = view.findViewById(R.id.public_spaces_list);
        /**
         * Allows us to show the selected song as highlighted.
         * URL: https://stackoverflow.com/questions/5925892/how-to-highlight-row-in-listview-in-android
         */
        publicSpacesList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        /**
         * Here is where we want to load the attractions into memory.
         */
        //Get number of attractions
        numberOfPublicSpaces = getResources().getInteger(R.integer.number_of_public_spaces);
        //Create the new ArrayList
        publicSpaces = new ArrayList<item>();
        //Load attractions into memory!
        loadAttractions();

        /**
         * Now we want to set the onItemClickListener
         * Now we grab the index of them item in the list that was clicked and
         * use that to look up the current item in the attractions array.
         */
        publicSpacesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int selected_item, long l) {
                //Here is where we will have code to open the details view that will show more information on the attraction, event, etc.
                Toast.makeText(getActivity().getApplicationContext(), publicSpaces.get(selected_item).itemTitle, Toast.LENGTH_SHORT).show();
                //This is where we will use an intent to switch to the details screen to show more item information!
                Intent i = new Intent(getActivity().getApplicationContext(), itemDetails.class);
                //Send data that we will need later...
                i.putExtra("items", publicSpaces);
                i.putExtra("selectedItems", selected_item);
                startActivity(i);
            }
        });

    }

    private void loadAttractions() {
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


        for (x = 1; x < (numberOfPublicSpaces + 1); x++) {
            /*Create new instance of the custom class item*/
            item i = new item();
            /*Grab resource IDs for each (x) item from the strings resource file*/
            eventTitleID = getResources().getIdentifier("public_space_" + (x) + "_title", "string", getActivity().getPackageName());
            eventAddressID = getResources().getIdentifier("public_space_" + (x) + "_address", "string", getActivity().getPackageName());
            eventCoordinatesID = getResources().getIdentifier("public_space_" + (x) + "_coordinates", "string", getActivity().getPackageName());
            eventDescriptionID = getResources().getIdentifier("public_space_" + (x) + "_description", "string", getActivity().getPackageName());
            eventTimeStartID = getResources().getIdentifier("public_space_" + (x) + "_time_start", "string", getActivity().getPackageName());
            eventTimeStopID = getResources().getIdentifier("public_space_" + (x) + "_time_end", "string", getActivity().getPackageName());
            eventImageID = getResources().getIdentifier("public_space_" + (x) + "_image", "drawable", getActivity().getPackageName());
            eventEmailID = getResources().getIdentifier("public_space_" + (x) + "_email", "string", getActivity().getPackageName());
            eventPhoneID = getResources().getIdentifier("public_space_" + (x) + "_phone", "string", getActivity().getPackageName());
            /**
             * Grabs the attractions data information from the strings resource file using the above
             * resource IDs and ads them to the custom class i, item.
             */
            i.setItem(getString(eventTitleID), getString(eventAddressID), getString(eventCoordinatesID), getString(eventDescriptionID), getString(eventTimeStartID), getString(eventTimeStopID), getString(eventEmailID), getString(eventPhoneID), (eventImageID));
            /*Add the custom class s to the ArrayList songs*/
            publicSpaces.add(i);
            Log.e("LOADING ARRAY", i.itemTitle);
        }
        Log.e("TOTAL", String.valueOf(publicSpaces.size()));

        /**
         * This creates a new custom adaptor sa, songAdaptor, using this context
         * and the ArrayList songs, then sets the adaptor to the ListView songList
         */
        itemAdapter sa = new itemAdapter(getActivity(), publicSpaces);
        publicSpacesList.setAdapter(sa);
    }


}
