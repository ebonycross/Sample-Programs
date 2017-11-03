package com.emc_ideas.justaddsugar;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ecross on 10/9/17.
 */
public class home_screen_cookbook_frag extends Fragment{
    FragmentManager fm;
    Context mContext;
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager mLayoutMgr;

    private List<mCookbook> cBooks;
    private CookBookAdapter bookAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initDataset();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.content_home_cook_frag, parent, false);

        mContext = getActivity();




        mRecycler = (RecyclerView) v.findViewById(R.id.rv);

        //good for if content layout size doesnt really change
        if(mRecycler !=null){
            mRecycler.setHasFixedSize(true);
        }

        //linearLayoutManager is user here, this will layout elems in like fashion of
        //listView would do element. The RecyclerView.LayoutManager defines how elements
        //are laid out
        mLayoutMgr = new LinearLayoutManager(mContext);

        mRecycler.setLayoutManager(mLayoutMgr);

        mCookbook cook = new mCookbook();
        cook.setAuthor("Ebony");
        cook.setTitle("Cross Family Cookbook");
        cBooks.add(cook);
        //initialize adapter to list of books
        bookAdapter = new CookBookAdapter(cBooks);

        //set CookBookAdapter as the adapter for RecyclerView
        mRecycler.setAdapter(bookAdapter);
        //mRecycler.setItemAnimator(new DefaultItemAnimator());

        bookAdapter.notifyDataSetChanged();


        fm = ((FragmentActivity) mContext).getSupportFragmentManager();


        FloatingActionButton cookBookBtn = (FloatingActionButton) v.findViewById(R.id.fabAddBookBtn);
        cookBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(mContext);
                View promptsView = li.inflate(R.layout.activity_fragment_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext, R.style.DialogStyle);

                //set dialog layout .xml
                alertDialogBuilder.setView(promptsView);

                alertDialogBuilder.setCancelable(false)
                        .setTitle("Alert Frag")


                        .setPositiveButton("OKay Dokey", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })

                        .setNegativeButton("Naw", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();

                //show it
                alertDialog.show();

                // DialogFragment alertDFragment = new AlertDFragment();

                //alertDFragment.show(fm, "Alert Dia");
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();


                int position = bookAdapter.getItemCount();
                try {
                    mCookbook c = new mCookbook("Title", "The Author");
                    cBooks.add(c);
                    bookAdapter.notifyItemInserted(position);
                    mRecycler.scrollToPosition(position);
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

            }
        });

        return v;
    }

    private void initDataset(){
        cBooks = new ArrayList<mCookbook>();
    }
}
