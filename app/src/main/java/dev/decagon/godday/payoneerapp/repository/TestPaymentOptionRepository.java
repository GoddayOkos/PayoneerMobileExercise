package dev.decagon.godday.payoneerapp.repository;

import dev.decagon.godday.payoneerapp.network.PaymentServiceCallback;

/**
 * This class will be for testing purpose only so we can avoid network calls in tests
 */
public class TestPaymentOptionRepository implements PaymentOptionServiceRepository {
    @Override
    public void getPaymentOptions(PaymentServiceCallback callback) {

    }
}
