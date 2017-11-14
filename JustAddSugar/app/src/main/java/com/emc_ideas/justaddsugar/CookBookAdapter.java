package com.emc_ideas.justaddsugar;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        //grab items from the cardview
        CardView cv;
        TextView bookAuthor;
        TextView bookTitle;
        FloatingActionButton bookFab;
        public RelativeLayout viewBackground, viewForeground;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cv = (CardView) itemView.findViewById(R.id.bookHolder);
            bookAuthor = (TextView) itemView.findViewById(R.id.cardAuthor);
            bookTitle = (TextView) itemView.findViewById(R.id.cardTitle);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            //bookFab = (FloatingActionButton) itemView.findViewById(R.id.fabAddBookBtn);

        }//end of viewholder constructor

        @Override
        public void onClick(View v){}

    }//end of inner class





    //constructor of adapter
    public CookBookAdapter(List<mCookbook> bookList) {
       //updateList(bookList);

        cookbookList = bookList;
    }



    public void updateList(List<mCookbook> dataset){
        cookbookList.clear();
        cookbookList.addAll(dataset);
        notifyDataSetChanged();
    }


    //create new view when inviked by the layout manager
    @Override
    public CookBookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create a new view
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View v = li.inflate(R.layout.cookbook, parent, false);
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
        /*
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity) v.getContext();


                if (cookbookList.size() > 0) {
                    cookbookList.remove(i);
                    //notify adapter that item preivously has been removed from the data set
                    notifyItemRemoved(i);

                    //notify any registered oberevers that itemCount itmes poistion has changed as well
                    notifyItemRangeChanged(i, cookbookList.size());
                } else { //create a label to tell user no more items in the list}

                }

            }
        });
        */
    }

    //setting the arraylist
    public void setListContent(List<mCookbook> list_members){
        cookbookList = list_members;
        notifyItemRangeChanged(0, cookbookList.size());
    }

    public void removeAt(int i){
        if (cookbookList.size() > 0) {
            cookbookList.remove(i);
            //notify adapter that item preivously has been removed from the data set
            notifyItemRemoved(i);

            //notify any registered oberevers that itemCount itmes poistion has changed as well
            notifyItemRangeChanged(0, cookbookList.size());
        } else { //create a label to tell user no more items in the list}

        }
    }

    public void insertItem(mCookbook c){
        cookbookList.add(c);
        notifyItemInserted(getItemCount());
    }

    public void updateList(mCookbook c){
        insertItem(c);
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

    public void restoreItem(mCookbook item, int position) {
        cookbookList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }

}
