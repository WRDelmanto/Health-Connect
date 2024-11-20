package com.example.healthconnect.utils;

import android.content.Context;
import android.widget.Toast;

public class FastToast {
    private FastToast() {
    }

    public static void show(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
