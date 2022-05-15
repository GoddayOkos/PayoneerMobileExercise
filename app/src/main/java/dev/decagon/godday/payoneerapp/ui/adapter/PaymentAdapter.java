package dev.decagon.godday.payoneerapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.decagon.godday.payoneerapp.R;
import dev.decagon.godday.payoneerapp.model.ApplicableNetwork;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentViewHolder> {

    private List<ApplicableNetwork> paymentOptions = new ArrayList<>();

    private final PaymentOptionClickListener clickListener;

    public PaymentAdapter(PaymentOptionClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        ApplicableNetwork paymentOption = paymentOptions.get(position);
        holder.itemView.setOnClickListener(v -> {
            clickListener.onPaymentOptionClicked(paymentOption);
        });
        holder.onBind(paymentOption);
    }

    @Override
    public int getItemCount() {
        return paymentOptions.size();
    }

    public void updatePaymentOptions(List<ApplicableNetwork> paymentOptions) {
        this.paymentOptions = paymentOptions;
        notifyDataSetChanged();
    }

    public interface PaymentOptionClickListener {
        void onPaymentOptionClicked(ApplicableNetwork paymentOption);
    }
}
