package dev.decagon.godday.payoneerapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import dev.decagon.godday.payoneerapp.R;
import dev.decagon.godday.payoneerapp.repository.RepositoryVariant;
import dev.decagon.godday.payoneerapp.ui.adapter.PaymentAdapter;
import dev.decagon.godday.payoneerapp.utils.ErrorUtils;
import dev.decagon.godday.payoneerapp.viewmodel.PaymentOptionViewModel;
import dev.decagon.godday.payoneerapp.viewmodel.PaymentOptionViewModelFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    Button retryButton;
    PaymentOptionViewModel viewModel;
    PaymentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Payment methods");

        initUI();
        initViewModelAndObservers();
    }

    private void initUI() {
        adapter = new PaymentAdapter(paymentOption -> {
            Toast.makeText(this, paymentOption.getLabel() + " selected", Toast.LENGTH_SHORT).show();
        });

        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);

        retryButton = findViewById(R.id.retry_btn);
        retryButton.setOnClickListener(view -> {
            view.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            viewModel.loadPaymentOptions();
        });
    }

    private void initViewModelAndObservers() {
        ViewModelProvider.Factory factory = new PaymentOptionViewModelFactory(RepositoryVariant.PRODUCTION);
        viewModel = new ViewModelProvider(this, factory).get(PaymentOptionViewModel.class);

        viewModel.getPaymentOptions().observe(this, paymentOptions -> {
            progressBar.setVisibility(View.GONE);
            retryButton.setVisibility(View.GONE);
            adapter.updatePaymentOptions(paymentOptions);
        });

        viewModel.getErrors().observe(this, error -> {
            progressBar.setVisibility(View.GONE);
            retryButton.setVisibility(View.VISIBLE);
            ErrorUtils.handleException(this, error, "");
        });
    }
}