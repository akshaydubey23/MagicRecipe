package com.magicrecipe.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.magicrecipe.Helper.CircleImageView;
import com.magicrecipe.R;
import com.magicrecipe.Model.SubDetails;

import java.util.ArrayList;

/**
 * Created by HP on 04/03/18.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<SubDetails> mAndroidList;
    private Context context;
    public DataAdapter(Context context,ArrayList<SubDetails> androidList) {
        mAndroidList = androidList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTvName.setText(mAndroidList.get(position).getTitle());
        //holder.mTvhref.setText(mAndroidList.get(position).getIngredients());
        Glide.with(context).load(mAndroidList.get(position).getThumbnail()).asBitmap()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mIvthumb);
        holder.mIvthumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog;
                dialog = new Dialog(v.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.sub_details);
                TextView item_name = dialog.findViewById(R.id.item_name);
                ImageView item_image = dialog.findViewById(R.id.fullimage);
                TextView item_description = dialog.findViewById(R.id.itemdescription);
                item_description.setText(mAndroidList.get(position).getIngredients());
                item_name.setText(mAndroidList.get(position).getTitle());
                Glide.with(context).load(mAndroidList.get(position).getThumbnail()).asBitmap()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(item_image);

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAndroidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvName,mTvhref,mTvingre;
        private CircleImageView mIvthumb;
        public ViewHolder(View view) {
            super(view);

            mTvName = (TextView)view.findViewById(R.id.tv_name);
           // mTvhref = (TextView)view.findViewById(R.id.tv_comment);
            mIvthumb=(CircleImageView)view.findViewById(R.id.itemimage);
        }
    }
}