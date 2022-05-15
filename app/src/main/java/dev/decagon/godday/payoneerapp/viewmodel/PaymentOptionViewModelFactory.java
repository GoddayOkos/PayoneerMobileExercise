package dev.decagon.godday.payoneerapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dev.decagon.godday.payoneerapp.repository.PaymentOptionServiceRepository;
import dev.decagon.godday.payoneerapp.repository.PaymentOptionServiceRepositoryImpl;
import dev.decagon.godday.payoneerapp.repository.RepositoryVariant;
import dev.decagon.godday.payoneerapp.repository.TestPaymentOptionRepository;

public class PaymentOptionViewModelFactory implements ViewModelProvider.Factory {
    private PaymentOptionServiceRepository repository;

    public PaymentOptionViewModelFactory(RepositoryVariant variant) {
        switch (variant) {
            case TEST:
                repository = new TestPaymentOptionRepository();
                break;
            case PRODUCTION:
                repository = new PaymentOptionServiceRepositoryImpl();
        }
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PaymentOptionViewModel(repository);
    }
}
