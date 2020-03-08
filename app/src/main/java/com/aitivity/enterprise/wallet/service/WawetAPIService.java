package com.aitivity.enterprise.wallet.service;

import android.util.Log;

import com.aitivity.enterprise.wallet.entity.WawetCommand;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public class WawetAPIService implements WawetCommandService {
    private static final String TAG = "WawetAPIService";
    private static final String WAWET_API_URL = "https://api.wawet.com/deversifi/";
    private final OkHttpClient httpClient;
    private final Gson gson;
    private WawetApiClient apiClient;

    public WawetAPIService(
            OkHttpClient httpClient,
            Gson gson) {
        this.httpClient = httpClient;
        this.gson = gson;
        buildApiClient(WAWET_API_URL);
    }

    private void buildApiClient(String baseUrl) {
        apiClient = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(WawetApiClient.class);
    }

    @Override
    public Observable<WawetCommand[]> fetchCommand(String address) {
        return apiClient
                .fetchCommand(address)
                .map(r -> r.response.toArray(new WawetCommand[r.response.size()]))
                .subscribeOn(Schedulers.io());
    }

    private static @NonNull
    <T> ApiErrorOperator<T> apiError() {
        ApiErrorOperator apiErrorOperator = new ApiErrorOperator<>();
        return apiErrorOperator;
    }

    public interface WawetApiClient {
        @FormUrlEncoded
        @POST("fetchcommand.php")
        Observable<WawetCommandResponse> fetchCommand(@Field("key") String address);
    }

    private static class WawetCommandResponse{
        List<WawetCommand> response;
    }



    private final static class ApiErrorOperator <T> implements ObservableOperator<T, Response<T>> {

        @Override
        public Observer<? super Response<T>> apply(Observer<? super T> downstream) throws Exception {

            return new DisposableObserver<Response<T>>() {

                @Override
                public void onNext(Response<T> response) {
                    downstream.onNext(response.body());
                    downstream.onComplete();
                }


                @Override
                public void onError(Throwable e) {
                    downstream.onError(e);
                }

                @Override
                public void onComplete() {
                    downstream.onComplete();
                }



            };


        }
    }
}
