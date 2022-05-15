package dev.decagon.godday.payoneerapp.repository;


import dev.decagon.godday.payoneerapp.network.PaymentServiceCallback;

public interface PaymentOptionServiceRepository {
    void getPaymentOptions(PaymentServiceCallback callback);
}
