package com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 *
 * This class is taken from below gist link.
 * https://gist.github.com/rogerhu/d041b6467536842aa986
 *
 * Class name has been changed from EndlessRecyclerViewScrollListener to RecyclerViewScrollToEndObserver
 * Class has been modified to add a listner in it.
 *
 */

public class RecyclerViewScrollToEndObserver extends RecyclerView.OnScrollListener
{
    // The minimum amount of items to have below your current scroll position
    // before mLoading more.
    private int visibleThreshold = 2;
    // The current offset index of data you have loaded
    private int currentPage = 0;
    // The total number of items in the dataset after the last load
    private int previousTotalItemCount = 0;
    // True if we are still waiting for the last set of data to load.
    private boolean mLoading = true;
    // Sets the starting page index
    private int startingPageIndex = 0;

    private IOnLoadMoreRecycleViewDataListner mOnLoadMoreRecycleViewDataListner;
    private RecyclerView.LayoutManager mLayoutManager;

    public RecyclerViewScrollToEndObserver(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    public RecyclerViewScrollToEndObserver(GridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
    }

    public RecyclerViewScrollToEndObserver(StaggeredGridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
    }

    public void setOnLoadMoreRecycleViewDataListner(IOnLoadMoreRecycleViewDataListner onLoadMoreRecycleViewDataListner)
    {
        mOnLoadMoreRecycleViewDataListner = onLoadMoreRecycleViewDataListner;
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            }
            else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int lastVisibleItemPosition = 0;
        int totalItemCount = mLayoutManager.getItemCount();

        if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(null);
            // get maximum element within the list
            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
        } else if (mLayoutManager instanceof GridLayoutManager) {
            lastVisibleItemPosition = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        } else if (mLayoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        } 


        // If it’s still mLoading, we check to see if the dataset count has
        // changed, if so we conclude it has finished mLoading and update the current page
        // number and total item count.
        if (mLoading && (totalItemCount > previousTotalItemCount)) {
            previousTotalItemCount = totalItemCount;
        }

        // If it isn’t currently mLoading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!mLoading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            if(mOnLoadMoreRecycleViewDataListner!=null && totalItemCount>2)
            {
                mOnLoadMoreRecycleViewDataListner.onLoadMoreRecycleViewDataListner(currentPage, totalItemCount, view);
            }
        }
    }

    public void setLoadingData(boolean loading)
    {
        mLoading = loading;
    }

    // Call this method whenever performing new searches
    public void resetState() {
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.mLoading = true;
    }


    public interface  IOnLoadMoreRecycleViewDataListner
    {
        void onLoadMoreRecycleViewDataListner(int page, int totalItemsCount, RecyclerView view);
    }



}