package com.dsige.belcen.ui.adapters;

import android.view.View;
import android.widget.TextView;

import com.dsige.belcen.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderViewHolder {

    @BindView(R.id.textViewName)
    public
    TextView textViewName;
    @BindView(R.id.textViewEmail)
    public
    TextView textViewEmail;

    public HeaderViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
