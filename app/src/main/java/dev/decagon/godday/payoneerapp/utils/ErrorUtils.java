package dev.decagon.godday.payoneerapp.utils;

import android.app.AlertDialog;
import android.content.Context;

import com.bumptech.glide.load.engine.GlideException;

import java.io.IOException;

import retrofit2.HttpException;

public class ErrorUtils {
    private static void showAlert(Context context, String message) {
        new AlertDialog.Builder(context).setTitle("An Error Occurred")
                .setMessage(message).setPositiveButton("Ok", (dialogInterface, i) -> {})
                .show();
    }

    private static void showAlert(Context context, String message, String title) {
        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(message).setPositiveButton("Ok", (dialogInterface, i) -> {})
                .show();
    }

    public static void handleException(Context context, Throwable error, String extras) {
        if (error instanceof GlideException) {
            Throwable cause = ((GlideException) error).getRootCauses().get(0);
            if (cause instanceof com.bumptech.glide.load.HttpException) {
                switch (((com.bumptech.glide.load.HttpException) cause).getStatusCode()) {
                    case 400:
                        showAlert(context, "Oops! Something isn't right here. Please try again",
                                "Error occurred while trying to download logo for " + extras);
                        break;
                    case 404:
                        showAlert(context, "Cause: The requested resource could not be found",
                                "Error occurred while trying to download logo for " + extras);
                        break;
                    case 500:
                        showAlert(context, "Oops! Please try again later while we contact our server guys",
                                "Error occurred while trying to download logo for " + extras);
                        break;
                    default:
                        showAlert(context, error.getMessage(), "Error occurred while trying to download logo for " + extras);
                }
            } else {
                showAlert(context, error.getMessage(), "Error occurred while trying to download logo for " + extras);
            }
            return;
        }

        if (error instanceof HttpException) {
            switch (((HttpException) error).code()) {
                case 400:
                    showAlert(context, "Oops! Something isn't right here. Please try again");
                    break;
                case 404:
                    showAlert(context, "Oops! The requested resources could not be found");
                    break;
                case 500:
                    showAlert(context, "Oops! Please try again later while we contact our server guys");
                    break;
                default:
                    showAlert(context, "Unknown error occurred. Please try again");
            }
            return;
        }

        if (error instanceof IOException) {
            showAlert(context, error.getMessage(), "IO Exception occurred");
            return;
        }

        showAlert(context, error.getMessage());
    }
}
