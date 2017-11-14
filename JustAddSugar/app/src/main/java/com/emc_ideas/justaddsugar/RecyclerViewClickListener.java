package com.emc_ideas.justaddsugar;

import android.view.View;

/**
 * Created by ecross on 11/12/17.
 */

public interface RecyclerViewClickListener {

    public void onClick(View v, int position);
    public void onLongClick(View view, int position);
}
