package com.aqif.tweetssearcher.searcher.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by aqifhamid on 2/9/16.
 */
public abstract class BaseRecycleRViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>
{

    protected ArrayList<T> mData;
    private AppCompatActivity mContext;

    private int mItemLayoutId;
    private int mItemVariableId;

    public BaseRecycleRViewAdapter(AppCompatActivity context, int itemLayoutId, int itemVariableId)
    {
        mContext = context;
        mItemLayoutId = itemLayoutId;
        mItemVariableId = itemVariableId;

        mData = new ArrayList<>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(mContext.getLayoutInflater(), mItemLayoutId, parent, false);
        return new BaseViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position)
    {
        holder.getViewDataBinding().setVariable(mItemVariableId, mData.get(position));
    }

    @Override
    public int getItemCount()
    {
            return mData.size();
    }


    @Override
    public long getItemId(int position)
    {
        return position;
    }

}
