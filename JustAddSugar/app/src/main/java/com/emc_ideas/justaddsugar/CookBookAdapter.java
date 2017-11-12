package com.emc_ideas.justaddsugar;

import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ecross on 11/1/17.
 */

public class CookBookAdapter extends RecyclerView.Adapter<CookBookAdapter.ViewHolder> {
    private List<mCookbook> cookbookList;

    /*
    to get a ref to get item in the list of cookbooks
    call inner private class. use View Holder to see all
     */
    public static class ViewHolder extends RecyclerView.ViewHolder  {


        //grab items from the cardview
        CardView cv;
        TextView bookAuthor;
        TextView bookTitle;
        FloatingActionButton bookFab;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.bookHolder);
            bookAuthor = (TextView) itemView.findViewById(R.id.cardAuthor);
            bookTitle = (TextView) itemView.findViewById(R.id.cardTitle);
            //bookFab = (FloatingActionButton) itemView.findViewById(R.id.fabAddBookBtn);

        }//end of viewholder constructor


    }//end of inner class


    //1. add listener to adapter class
    private RecyclerViewClickListener mRCL;


    //constructor of adapter
    public CookBookAdapter(List<mCookbook> bookList) {
        updateList(bookList);
    }

    public void updateList(List<mCookbook> dataset){
        cookbookList.clear();
        cookbookList.addAll(dataset);
    }

    //create new view when inviked by the layout manager
    @Override
    public CookBookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create a new view
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View v = li.inflate(R.layout.cookbook_list, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    //replace the contents of a view that is invoked by the laoyout manager
    @Override
    public void onBindViewHolder(CookBookAdapter.ViewHolder holder, final int i) {
        //1. get the element from arraylist at this position
        //2. replace contents of the view with that element
        mCookbook book = cookbookList.get(i);
        holder.bookTitle.setText(book.getTitle());
        holder.bookAuthor.setText("By Author " + book.getAuthor());

        //set a click listener for list to remove item
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment add_recipe_frag = new AddRecipe_frag();
                activity.getFragmentManager().beginTransaction()
                        .replace(R.id.home_container, add_recipe_frag).addToBackStack(null).commit();

               /*
                if (cookbookList.size() > 0) {
                    cookbookList.remove(i);
                    //notify adapter that item preivously has been removed from the data set
                    notifyItemRemoved(i);

                    //notify any registered oberevers that itemCount itmes poistion has changed as well
                    notifyItemRangeChanged(i, cookbookList.size());
                } else { //create a label to tell user no more items in the list}

                }
                */
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView rv) {
        super.onAttachedToRecyclerView(rv);
    }

    @Override
    public int getItemCount() {
        return cookbookList.size();
    }

    public List<mCookbook> getCookBookList() {
        return cookbookList;
    }

}
