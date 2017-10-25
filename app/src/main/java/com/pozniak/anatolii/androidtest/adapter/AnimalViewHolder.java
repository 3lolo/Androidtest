package com.pozniak.anatolii.androidtest.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pozniak.anatolii.androidtest.R;
import com.pozniak.anatolii.androidtest.models.AnimalModel;
import com.squareup.picasso.Picasso;

/**
 * Created by Владелец on 24.10.2017.
 */

public class AnimalViewHolder extends AbsViewHolder<AnimalModel> {
    private ImageView ivPic;
    private TextView tvText;

    public AnimalViewHolder(View itemView, OnRecyclerItemClick<AnimalModel> click) {
        super(itemView, click);
        ivPic = itemView.findViewById(R.id.iv_pic);
        tvText = itemView.findViewById(R.id.tv_text);
    }


    @Override
    public void onBind(final AnimalModel model, final int position) {
        if (model != null) {
            Picasso.with(itemView.getContext()).load(model.getUrl()).into(ivPic);
            tvText.setText(model.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.click(model, position);
                }
            });
        }
    }

}
