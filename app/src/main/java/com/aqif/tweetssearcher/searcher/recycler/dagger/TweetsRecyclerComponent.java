package com.aqif.tweetssearcher.searcher.recycler.dagger;

import com.aqif.tweetssearcher.searcher.recycler.viewmodel.ITweetsRecyclerViewModel;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.TweetsRecyclerViewModel;
import com.aqif.tweetssearcher.searcher.recycler.viewmodel.observer.ITweetsRecyclerViewModelObservable;

import dagger.Component;


/**
 * Created by aqifhamid on 2/6/17.
 */

@Component(modules = {TweetsRecyclerModule.class})
public interface TweetsRecyclerComponent
{
    void inject(TweetsRecyclerViewModel.InjectableTweetsRecyclerViewModelField field);

    ITweetsRecyclerViewModelObservable getTweetsRecyclerViewModelObservable();
    ITweetsRecyclerViewModel getTweetsRecyclerViewModel();
}
