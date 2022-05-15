package dev.decagon.godday.payoneerapp.network;

import dev.decagon.godday.payoneerapp.model.ListResult;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface PaymentOptionService {
    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    Call<ListResult> getPaymentOptions();

    static PaymentOptionService getInstance() {
        return new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build()
                .create(PaymentOptionService.class);
    }
}
