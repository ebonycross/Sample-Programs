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
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ecross on 10/9/17.
 */
public class List_cookbooks_home_frag extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    public static String BOOK_FRAG_ID = "book_frag";
    FragmentManager fm;
    Context mContext;
    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager mLayoutMgr;
    private TextView status;
    private EditText title;
    private Intent i;
    private String username;
    private String userid;
    private static String email;
    private RecyclerTouchListener l;
    private static int count = 0;
    private List<mCookbook> cBooks = new ArrayList<mCookbook>();
    private CookBookAdapter bookAdapter;

    private Map<String, Object> cookbooks = new HashMap<>();
    private Map<String, Object> bookUpdates = new HashMap<>();
    private RelativeLayout coordinatorLayout;

    //Firebase objects
    //declare database objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private mUserID currentUser;

    // Write a message to the database
    private FirebaseDatabase database;
    private DatabaseReference userRef, bookRef;
    private DatabaseReference dbRef;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userid = user.getUid();




        if (user == null) {
            startActivity(new Intent(getActivity(), WelcomeActivity.class));
           // finish();
            return;
        }


        userInfo();


        cBooks.clear();
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

                /***** BUNDLE COOKBOOK OBJECT***/
                Bundle bundle = new Bundle();


                bundle.putParcelable(Constants.COOKBOOK_OBJ_KEY,cBooks.get(position));
                Boolean t = bundle.isEmpty();

                Log.i(Constants.COOKBOOK_OBJ_KEY, "bundle empty? " +t );

                //bundle.putString(Constants.COOKBOOK_ID_KEY, cBooks.get(position).getId());
                //notifyUser("sending cookbook " + cBooks.get(position).getTitle());
                Fragment recipe_list = new List_Pager_Recipes_frag();

                /***** BUNDLE COOKBOOK OBJECT***/

                Fragment add_recipe_frag = new ViewPagerStyler1Activity();
               add_recipe_frag.setArguments(bundle);
                FragmentManager fm = getActivity().getSupportFragmentManager();
              FragmentTransaction ft = fm.beginTransaction();
                //recipe_list.setArguments(bundle);
                 ft.replace(R.id.home_container, add_recipe_frag).addToBackStack(null).commit();


            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();

                /***** BUNDLE COOKBOOK OBJECT***/
                Bundle bundle = new Bundle();
                Fragment recipe_list = new List_Pager_Recipes_frag();
                bundle.putParcelable(Constants.COOKBOOK_OBJ_KEY,cBooks.get(position));
                notifyUser("sending cookbook " + cBooks.get(position).getTitle());
                //bundle.putString(Constants.COOKBOOK_ID_KEY, cBooks.get(position).getId());
                /***** BUNDLE COOKBOOK OBJECT***/

                //recipe_list.setArguments(bundle);

                Fragment add_recipe_frag = new ViewPagerStyler1Activity();

                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                add_recipe_frag.setArguments(bundle);
                ft.replace(R.id.home_container, add_recipe_frag).addToBackStack(null).commit();
            }
        }));

        //initialize adapter to list of books
       // bookAdapter = new CookBookAdapter(cBooks);

        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecycler.setAdapter(bookAdapter);
        //ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);





        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecycler);

        fm = ((FragmentActivity) mContext).getSupportFragmentManager();


        final FloatingActionButton cookBookBtn = (FloatingActionButton) view.findViewById(R.id.fabAddBookBtn);
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



                                    mCookbook c = new mCookbook(currentUser.getFname(),s);


                                    //Ref = database.getReference(Constants.FIREBASE_CHILD_COOKBOOKS);
                                    //pushes and makes key at the same time (also gets key)
                                    String bookID = bookRef.push().getKey();
                                    c.setId(bookID);
                                    cookbooks.put(bookID,c);
                                    bookRef.setValue(cookbooks);
                                    c.addMember(currentUser);
                                    cookbooks.put(bookID,c );
                                   DatabaseReference newChildRef = bookRef.getRef();
                                    //String memberKey = newChildRef.getKey();
                                  // cookbooks.get(bookID).
                                   //Map<String, Object> bookUpdates = new HashMap<>();
                                   bookUpdates.put(bookID+"/members/"+userid, currentUser);
                                   newChildRef.updateChildren(bookUpdates);

                                   // notifyUser("MEMBER: "+ c.getMembers(0).getLname());
                                   //newChildRef.setValue(currentUser);


                                    bookAdapter.insertItem(c);
                                    //notifyUser("after");
                                    int position = bookAdapter.getItemCount();


                                    mRecycler.scrollToPosition(position);

                                    notifyUser("after scroll. count= "+position);
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
        notifyUser("current array size is " +cBooks.size());
        bookRef = dbRef.child(Constants.FIREBASE_CHILD_COOKBOOKS);

        // Attach a listener to read the data at our cookbook reference
        bookRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cBooks.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                  mCookbook book = child.getValue(mCookbook.class);
                  //notifyUser("book: " + book.getTitle());
                  cBooks.add(book);
                  cookbooks.put(book.getId(),book);

                }

                notifyUser("current AFTER array size is " +cBooks.size());

                //initialize adapter to list of books

                bookAdapter = new CookBookAdapter(cBooks);

                mRecycler.setItemAnimator(new DefaultItemAnimator());
                mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

                mRecycler.setAdapter(bookAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


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

        //remove book from database
        cookbooks.put(deletedItem.getId(),null);
        bookRef.updateChildren(cookbooks);

        // showing snack bar with Undo option
        Snackbar snackbar = Snackbar
                .make(getView().findViewById(R.id.book_container), "DELETE " + name + "?", Snackbar.LENGTH_LONG);


        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // undo is selected, restore the deleted item
                bookAdapter.restoreItem(deletedItem, deletedIndex);
                cookbooks.put(deletedItem.getId(),deletedItem);
                bookRef.updateChildren(cookbooks);
            }
        });

        snackbar.setDuration(5000);
        snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
        snackbar.show();


        bookAdapter.notifyDataSetChanged();

    }

    private void notifyUser(String message) {
        Toast.makeText(getActivity(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void userInfo(){
        // Get a reference to our posts
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();
        userRef = dbRef.child(Constants.FIREBASE_CHILD_USERS);


        // Attach a listener to read the data at our cookbook reference
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentUser = dataSnapshot.child(userid).getValue(mUserID.class);

                ((homeActivity) getActivity()).getSupportActionBar().setTitle("Welcome, "+currentUser.getFname());
                notifyUser("NEW NAME: "+currentUser.getFname());
               email =currentUser.getEmail();


                if(count == 0) {
                    Snackbar snackbar = Snackbar
                            .make(getActivity().findViewById(R.id.reLayout), " Signed in as " + email, Snackbar.LENGTH_LONG);

                    snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
                    snackbar.show();
                    count++;
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }
    @Override
    public void onResume(){
        super.onResume();
        userInfo();
    }
    //remove listener
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener !=null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}


