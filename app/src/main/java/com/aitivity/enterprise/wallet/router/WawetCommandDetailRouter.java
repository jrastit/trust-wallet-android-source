package com.aitivity.enterprise.wallet.router;

import android.content.Context;
import android.content.Intent;

import com.aitivity.enterprise.wallet.entity.WawetCommand;
import com.aitivity.enterprise.wallet.ui.widget.WawetCommandDetailActivity;

import static com.wallet.crypto.trustapp.C.Key.WAWETCOMMAND;

public class WawetCommandDetailRouter {

    public void open(Context context, WawetCommand wawetCommand) {
        Intent intent = new Intent(context, WawetCommandDetailActivity.class);
        intent.putExtra(WAWETCOMMAND, wawetCommand);
        context.startActivity(intent);
    }
}
