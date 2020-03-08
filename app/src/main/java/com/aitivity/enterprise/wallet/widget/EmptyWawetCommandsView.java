package com.aitivity.enterprise.wallet.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.NetworkInfo;

public class EmptyWawetCommandsView extends FrameLayout {

    public EmptyWawetCommandsView(@NonNull Context context, OnClickListener onClickListener) {
        super(context);

        LayoutInflater.from(getContext())
                .inflate(R.layout.layout_empty_wawetcommands, this, true);

        findViewById(R.id.action_buy).setOnClickListener(onClickListener);
    }

    public void setNetworkInfo(NetworkInfo networkInfo) {
        if (networkInfo.isMainNetwork) {
            findViewById(R.id.action_buy).setVisibility(VISIBLE);
        } else {
            findViewById(R.id.action_buy).setVisibility(GONE);
        }
    }
}
