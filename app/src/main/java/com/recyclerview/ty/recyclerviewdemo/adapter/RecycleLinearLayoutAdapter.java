package com.recyclerview.ty.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recyclerview.ty.recyclerviewdemo.R;

import java.util.List;

/**
 * Created by LJX on 2016/6/3.
 */
public class RecycleLinearLayoutAdapter extends RecyclerView.Adapter<RecycleLinearLayoutAdapter.MyViewHolder> {

    private Context mContext;
    List<String> mDatas;
    private LayoutInflater inflater;

    public RecycleLinearLayoutAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recycle_linearlayout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textview.setText(mDatas.get(position));
//        holder.imageview
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textview;
//        public ImageView imageview;

        public MyViewHolder(View rootView) {
            super(rootView);
            this.textview = (TextView) rootView.findViewById(R.id.textview);
//            this.imageview = (ImageView) rootView.findViewById(R.id.imageview);
        }

    }
}
