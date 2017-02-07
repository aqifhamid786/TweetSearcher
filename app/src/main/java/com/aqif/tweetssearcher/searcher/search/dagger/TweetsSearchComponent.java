package com.aqif.tweetssearcher.searcher.search.dagger;

import com.aqif.tweetssearcher.restapi.twitter.manager.dagger.TwitterApiManagerComponent;
import com.aqif.tweetssearcher.searcher.search.model.ITweetsSearchModel;
import com.aqif.tweetssearcher.searcher.search.viewmodel.ITweetsSearchViewModel;
import com.aqif.tweetssearcher.searcher.search.viewmodel.TweetsSearchViewModel.InjectableTweetsSearchViewModelField;
import com.aqif.tweetssearcher.searcher.search.viewmodel.observer.ITweetsSearchViewModelObservable;

import dagger.Component;

/**
 * Created by aqifhamid on 2/7/17.
 */

@Component(modules = {TweetsSearchModule.class}, dependencies = {TwitterApiManagerComponent.class})
public interface TweetsSearchComponent
{

    void inject(InjectableTweetsSearchViewModelField fields);

    ITweetsSearchModel getTweetsSearchModel();
    ITweetsSearchViewModelObservable getTweetsSearchViewModelObservable();
    ITweetsSearchViewModel getTweetsSearchViewModel();
        
}
