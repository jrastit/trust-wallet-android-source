package com.aitivity.enterprise.wallet.router;

import android.content.Context;
import android.content.Intent;

import com.wallet.crypto.trustapp.ui.AaveActivity;

public class AaveRouter {

    public void open(Context context) {
        Intent intent = new Intent(context, AaveActivity.class);
        context.startActivity(intent);
    }
}
