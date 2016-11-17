package ru.bizapp.kuponandroid.base;

import rx.Subscription;

/**
 * Created by Majo on 17.11.2016.
 */
public abstract class BasePresenter <V extends BaseView>{

    private V mView;

    protected Subscription mSubscription;

    public void onDestroy(){
        if (mSubscription!=null)
            mSubscription.unsubscribe();
        if (isViewAttached())
            detachView();
    }

    public final void attachView(V view) {
        if (view == null) {
            throw new NullPointerException("View must not be null");
        }
        mView = view;
    }

    protected final void detachView() {
        mView = null;
    }

    protected final V getView() {
        return mView;
    }

    protected final boolean isViewAttached() {
        return mView != null;
    }

}
