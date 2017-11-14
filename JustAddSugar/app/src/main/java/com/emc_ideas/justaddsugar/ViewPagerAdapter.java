package com.emc_ideas.justaddsugar;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

/**
 * Created by ecross on 11/13/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

   private Context mContext;
    //Tab titles
   // private String[] tabs = {"List of Recipes", "Group Info"};


    public ViewPagerAdapter(FragmentManager fm, Context context){
        super(fm);
        mContext = context;
   }


    public Fragment getItem(int index){
        Fragment f = new Fragment();
        switch (index){
            case 0:
                //list of recipes
                f = Cookbook_Recipes_frag.newInstance(mContext);
                break;
            case 1:
                //multi-user information
                f = Group_info_frag.newInstance(mContext);
                break;
            default:
                f = null;
        }
        return f;
    }

    @Override
    public int getCount(){
        //get item count --> equal to the number of tabs
        return 2;
    }

    /*
    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return  tabs[position];
    }
*/
}
