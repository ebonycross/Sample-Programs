package com.emc_ideas.justaddsugar;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by ecross on 11/13/17.
 */

public class ViewPagerStyler1Activity extends Fragment {
    private ViewPager _mViewPager;
    private ViewPagerAdapter _adapter;
    private Button _btn1,_btn2;


    /** Called when the activity is first created. */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpView();
        setTab();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.cookbook_recipe_list, parent, false);
        return v;
    }

    private void setUpView(){
        _mViewPager = (ViewPager) getView().findViewById(R.id.pager);
        this._adapter = new ViewPagerAdapter(getChildFragmentManager(),getActivity());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
        initButton();
    }
    private void setTab(){
        _mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrollStateChanged(int position) {}
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                btnAction(position);
                _mViewPager.setCurrentItem(position);
                _adapter.notifyDataSetChanged();
            }

        });

    }
    private void btnAction(int action){
        switch(action){
            case 0:
                setButton(_btn1,200,200); setButton(_btn2,20,20);
                 _btn1.setBackground(getResources().getDrawable(R.drawable.rounded_cell2));
                _btn2.setBackground(getResources().getDrawable(R.drawable.rounded_cell));
            break;

            case 1:
                setButton(_btn2,200,200); setButton(_btn1,20,20);
                _btn2.setBackground(getResources().getDrawable(R.drawable.rounded_cell2));
                _btn1.setBackground(getResources().getDrawable(R.drawable.rounded_cell));
                //_btn2.setBackgroundColor(getResources().getColor(R.color.colorLightOrangeSal));
            break;
        }
    }
    private void initButton(){
        _btn1=(Button)getView().findViewById(R.id.btn1);
        _btn2=(Button)getView().findViewById(R.id.btn2);
        setButton(_btn1,40,40);
        setButton(_btn2,20,20);
        _btn1.setBackground(getResources().getDrawable(R.drawable.rounded_cell2));
    }
    private void setButton(Button btn,int w, int h ){
       // btn.setWidth(w);
        //btn.setHeight(h);
        /*
        int width= (int)getResources().getDimension(R.dimen.button_width);
        int heigth= (int)getResources().getDimension(R.dimen.button_height);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(w, h);
        btn.setLayoutParams(layoutParams);
        */
    }
}

