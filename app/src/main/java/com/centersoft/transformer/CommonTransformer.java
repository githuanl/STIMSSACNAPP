package com.centersoft.transformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CommonTransformer<T> implements ObservableTransformer<T, T> {

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
//                        .doOnSubscribe(new Consumer<Disposable>() {
//                            @Override
//                            public void accept(Disposable disposable) throws Exception {
//                                if (!NetworkUtils.isConnected()) {
//                                    disposable.dispose();
//                                }
////                                else{
////                                    dialog.show();
////                                }
//                            }
//                        })
                .observeOn(AndroidSchedulers.mainThread());
//                .compose(ErrorTransformer.<T>getInstance());
    }
}



