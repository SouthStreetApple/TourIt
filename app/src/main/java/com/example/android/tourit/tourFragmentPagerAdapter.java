
package com.example.android.tourit;
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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */


public class tourFragmentPagerAdapter extends FragmentPagerAdapter {

    public tourFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new attractionsFragment();
        } else if (position == 1) {
            return new eventsFragment();
        } else if (position == 2) {
            return new foodFragment();
        } else {
            return new publicSpacesFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    //Enables us to set the titles for each pager fragment
    //URL: https://stackoverflow.com/questions/14082854/adding-titles-to-viewpager#14083077
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Attractions";
        } else if (position == 1) {
            return "Events";
        } else if (position == 2) {
            return "Food";
        } else {
            return "Public Spaces";
        }
    }

}
