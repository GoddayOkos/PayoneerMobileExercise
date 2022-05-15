package dev.decagon.godday.payoneerapp.ui.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Objects;

import dev.decagon.godday.payoneerapp.R;
import dev.decagon.godday.payoneerapp.model.ApplicableNetwork;
import dev.decagon.godday.payoneerapp.utils.ErrorUtils;

public class PaymentViewHolder extends RecyclerView.ViewHolder {
    private final ImageView paymentLogo;
    private final TextView paymentLabel;

    public PaymentViewHolder(@NonNull View itemView) {
        super(itemView);
        paymentLogo = itemView.findViewById(R.id.payment_logo);
        paymentLabel = itemView.findViewById(R.id.payment_label);
    }

    void onBind(ApplicableNetwork applicableNetwork) {
        Glide.with(paymentLogo.getContext())
                .load(Objects.requireNonNull(applicableNetwork.getLinks()
                        .get("logo")).toString()).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                ErrorUtils.handleException(itemView.getContext(), e, applicableNetwork.getLabel());
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(paymentLogo);

        paymentLabel.setText(applicableNetwork.getLabel());
    }
}
