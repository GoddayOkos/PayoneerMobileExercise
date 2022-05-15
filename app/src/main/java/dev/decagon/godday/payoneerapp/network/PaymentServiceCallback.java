package dev.decagon.godday.payoneerapp.network;

import dev.decagon.godday.payoneerapp.model.Networks;

public interface PaymentServiceCallback {
    void onSuccess(Networks networks);
    void onError(Throwable error);
}