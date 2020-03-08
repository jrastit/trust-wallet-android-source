package com.aitivity.enterprise.wallet.router;

import android.content.Context;
import android.content.Intent;

import com.aitivity.enterprise.wallet.ui.AaveActivity;

public class ENSTestRouter {

    public void open(Context context) {
        Intent intent = new Intent(context, AaveActivity.class);
        context.startActivity(intent);
    }
}
