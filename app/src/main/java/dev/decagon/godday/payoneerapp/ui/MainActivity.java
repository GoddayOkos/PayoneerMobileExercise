package dev.decagon.godday.payoneerapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
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
    }

    private void initViewModelAndObservers() {
        ViewModelProvider.Factory factory = new PaymentOptionViewModelFactory(RepositoryVariant.PRODUCTION);
        viewModel = new ViewModelProvider(this, factory).get(PaymentOptionViewModel.class);

        viewModel.getPaymentOptions().observe(this, paymentOptions -> {
            progressBar.setVisibility(View.GONE);
            adapter.updatePaymentOptions(paymentOptions);
        });

        viewModel.getErrors().observe(this, error -> {
            progressBar.setVisibility(View.GONE);
            ErrorUtils.handleException(this, error, "");
        });
    }
}