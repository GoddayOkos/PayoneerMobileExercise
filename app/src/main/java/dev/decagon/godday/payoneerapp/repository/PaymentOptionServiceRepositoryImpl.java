package dev.decagon.godday.payoneerapp.repository;

import dev.decagon.godday.payoneerapp.model.ListResult;
import dev.decagon.godday.payoneerapp.network.PaymentOptionService;
import dev.decagon.godday.payoneerapp.network.PaymentServiceCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentOptionServiceRepositoryImpl implements PaymentOptionServiceRepository {

    @Override
    public void getPaymentOptions(PaymentServiceCallback callback) {

        PaymentOptionService.getInstance().getPaymentOptions().enqueue(new Callback<ListResult>() {
            @Override
            public void onResponse(Call<ListResult> call, Response<ListResult> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    if (response.body() != null) {
                        callback.onSuccess(response.body().getNetworks());
                    }
                }
            }

            @Override
            public void onFailure(Call<ListResult> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
