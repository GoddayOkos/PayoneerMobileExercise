package dev.decagon.godday.payoneerapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.decagon.godday.payoneerapp.model.ApplicableNetwork;
import dev.decagon.godday.payoneerapp.model.Networks;
import dev.decagon.godday.payoneerapp.network.PaymentServiceCallback;
import dev.decagon.godday.payoneerapp.repository.PaymentOptionServiceRepository;

public class PaymentOptionViewModel extends ViewModel {
    private final MutableLiveData<List<ApplicableNetwork>> paymentOptions = new MutableLiveData<>();
    private final MutableLiveData<Throwable> errors = new MutableLiveData<>();
    private final PaymentOptionServiceRepository repository;

    public PaymentOptionViewModel(PaymentOptionServiceRepository repository) {
        this.repository = repository;
        loadPaymentOptions();
    }

    public LiveData<List<ApplicableNetwork>> getPaymentOptions() {
        return paymentOptions;
    }

    public LiveData<Throwable> getErrors() {
        return errors;
    }

    public void loadPaymentOptions() {
        repository.getPaymentOptions(new PaymentServiceCallback() {
            @Override
            public void onSuccess(Networks networks) {
                paymentOptions.setValue(networks.getApplicable());
            }

            @Override
            public void onError(Throwable error) {
                errors.setValue(error);
            }
        });
    }
}
