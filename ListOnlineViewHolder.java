package com.example.easytransportation.easyriderapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by arjunpatidar on 23/12/17.
 */

public class ListOnlineViewHolder extends RecyclerView.ViewHolder {
    public TextView txtEmail;

    public ListOnlineViewHolder(View itemView) {
        super(itemView);
        txtEmail = (TextView)itemView.findViewById(R.id.txt_email);

    }
}
