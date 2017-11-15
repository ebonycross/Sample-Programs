package com.emc_ideas.justaddsugar;


import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ecross on 10/9/17.
 */
public class List_cookbooks_home_frag extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    FragmentManager fm;
    Context mContext;
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager mLayoutMgr;
    private TextView status;
    private EditText title;
    private Intent i;
    private RecyclerTouchListener l;

    private List<mCookbook> cBooks;
    private CookBookAdapter bookAdapter;

    private RelativeLayout coordinatorLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.content_home_cook_frag, parent, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceSate) {
        super.onViewCreated(view, savedInstanceSate);
        mContext = getActivity();


        status = (TextView) view.findViewById(R.id.dialog_status);
        mRecycler = (RecyclerView) view.findViewById(R.id.rv);

        coordinatorLayout = (RelativeLayout) view.findViewById(R.id.book_container);

        //good for if content layout size doesnt really change
        if (mRecycler != null) {
            mRecycler.setHasFixedSize(true);
        }

        //linearLayoutManager is user here, this will layout elems in like fashion of
        //listView would do element. The RecyclerView.LayoutManager defines how elements
        //are laid out
        mLayoutMgr = new LinearLayoutManager(mContext);

        mRecycler.setLayoutManager(mLayoutMgr);



        mRecycler.addOnItemTouchListener(new RecyclerTouchListener(mContext,
                mRecycler, new RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Values are passing to activity & to fragment as well
                Toast.makeText(getActivity(), "Single Click on position   :" + position,
                        Toast.LENGTH_SHORT).show();



                Fragment add_recipe_frag = new ViewPagerStyler1Activity();
                getFragmentManager().beginTransaction()
                        .replace(R.id.home_container, add_recipe_frag).addToBackStack(null).commit();


            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();
            }
        }));

        //initialize adapter to list of books
        bookAdapter = new CookBookAdapter(cBooks);

        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mRecycler.setAdapter(bookAdapter);

        //ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);





        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecycler);

        fm = ((FragmentActivity) mContext).getSupportFragmentManager();


        FloatingActionButton cookBookBtn = (FloatingActionButton) view.findViewById(R.id.fabAddBookBtn);
        cookBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(mContext);
                final View promptsView = li.inflate(R.layout.activity_fragment_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext, R.style.DialogStyle);

                //set dialog layout .xml
                alertDialogBuilder.setView(promptsView);


                alertDialogBuilder.setCancelable(false)
                        .setTitle("Name of Cookbook")


                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                //status.setText("");

                                try {
                                    title = (EditText) promptsView.findViewById(R.id.dialog_userInput);
                                    String s = title.getText().toString();
                                    Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();

                                    mCookbook c = new mCookbook(s, "");
                                    bookAdapter.insertItem(c);
                                    int position = bookAdapter.getItemCount();

                                    mRecycler.scrollToPosition(position);

                                } catch (ArrayIndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                }
                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();


                //show it
                alertDialog.show();
            }

        });

    }


    private void initDataset() {
        cBooks = new ArrayList<mCookbook>();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle b = data.getExtras();
                    String t = b.getString("");
                }
        }
    }


    /*INNER CLASS*/
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private RecyclerViewClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final RecyclerViewClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }//END of INNER CLASS






    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * callback when recycler view is swiped
     * item will be removed on swiped
     * undo option will be provided in snackbar to restore the item
     */
    /*
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

            // get the removed item name to display it in snack bar

            String name = cBooks.get(viewHolder.getAdapterPosition()).getTitle();

            // backup of removed item for undo purpose
            final mCookbook deletedItem = cBooks.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();


            // remove the item from recycler view
           // bookAdapter.removeAt(viewHolder.getAdapterPosition());


            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    bookAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();

    }

*/
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Toast.makeText(getActivity(), "on Move", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir, int position) {
        //Toast.makeText(getActivity(), "on Swiped ", Toast.LENGTH_SHORT).show();
        //Remove swiped item from list and notify the RecyclerView
        position = viewHolder.getAdapterPosition();
        // get the removed item name to display it in snack bar

        String name = cBooks.get(position).getTitle();


        // backup of removed item for undo purpose
        final mCookbook deletedItem = cBooks.get(position);
        final int deletedIndex = position;


        // remove the item from recycler view
        bookAdapter.removeAt(position);


        // showing snack bar with Undo option
        Snackbar snackbar = Snackbar
                .make(getView().findViewById(R.id.book_container), "DELETE " + name + "?", Snackbar.LENGTH_LONG);


        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // undo is selected, restore the deleted item
                bookAdapter.restoreItem(deletedItem, deletedIndex);
            }
        });

        snackbar.setDuration(5000);
        snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
        snackbar.show();


        bookAdapter.notifyDataSetChanged();

    }
}


