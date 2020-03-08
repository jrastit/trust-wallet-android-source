package com.aitivity.enterprise.wallet.service;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public final class CustomObserver<T> implements Observer<Response<T>>, Disposable {

    // The downstream's Observer that will receive the onXXX events
    final Observer<Response<T>> downstream;

    // The connection to the upstream source that will call this class' onXXX methods
    Disposable upstream;

    // The constructor takes the downstream subscriber and usually any other parameters
    public CustomObserver(Observer<? super T> downstream) {
        this.downstream = (Observer<Response<T>>) downstream;
    }

    // In the subscription phase, the upstream sends a Disposable to this class
    // and subsequently this class has to send a Disposable to the downstream.
    // Note that relaying the upstream's Disposable directly is not allowed in RxJava
    @Override
    public void onSubscribe(Disposable d) {
        if (upstream != null) {
            d.dispose();
        } else {
            upstream = d;
            downstream.onSubscribe(this);
        }
    }

    // The upstream calls this with the next item and the implementation's
    // responsibility is to emit an item to the downstream based on the intended
    // business logic, or if it can't do so for the particular item,
    // request more from the upstream
    @Override
    public void onNext(Response<T> item) {
        downstream.onNext(item);
        // Observable doesn't support backpressure, therefore, there is no
        // need or opportunity to call upstream.request(1) if an item
        // is not produced to the downstream
    }

    // Some operators may handle the upstream's error while others
    // could just forward it to the downstream.
    @Override
    public void onError(Throwable throwable) {
        downstream.onError(throwable);
    }

    // When the upstream completes, usually the downstream should complete as well.
    @Override
    public void onComplete() {
        downstream.onComplete();
    }

    // Some operators may use their own resources which should be cleaned up if
    // the downstream disposes the flow before it completed. Operators without
    // resources can simply forward the dispose to the upstream.
    // In some cases, a disposed flag may be set by this method so that other parts
    // of this class may detect the dispose and stop sending events
    // to the downstream.
    @Override
    public void dispose() {
        upstream.dispose();
    }

    // Some operators may simply forward the call to the upstream while others
    // can return the disposed flag set in dispose().
    @Override
    public boolean isDisposed() {
        return upstream.isDisposed();
    }
}
