package com.aitivity.enterprise.wallet.ui.widget.holder;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aitivity.enterprise.wallet.entity.WawetCommand;
import com.aitivity.enterprise.wallet.entity.WawetCommandOperation;
import com.aitivity.enterprise.wallet.ui.widget.OnWawetCommandClickListener;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.widget.holder.BinderViewHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WawetCommandHolder extends BinderViewHolder<WawetCommand> implements View.OnClickListener {

    public static final int VIEW_TYPE = 1003;

    private static final int SIGNIFICANT_FIGURES = 3;

    public static final String DEFAULT_ADDRESS_ADDITIONAL = "default_address";
    public static final String DEFAULT_SYMBOL_ADDITIONAL = "network_symbol";

    private final TextView type;
    private final TextView address;
    private final TextView value;
    private final ImageView typeIcon;

    private WawetCommand wawetCommand;
    private String defaultAddress;
    private OnWawetCommandClickListener onWawetCommandClickListener;

    public WawetCommandHolder(int resId, ViewGroup parent) {
        super(resId, parent);

        typeIcon = findViewById(R.id.type_icon);
        address = findViewById(R.id.address);
        type = findViewById(R.id.type);
        value = findViewById(R.id.value);

        typeIcon.setColorFilter(
                ContextCompat.getColor(getContext(), R.color.item_icon_tint),
                PorterDuff.Mode.SRC_ATOP);

        itemView.setOnClickListener(this);
    }

    @Override
    public void bind(@Nullable WawetCommand data, @NonNull Bundle addition) {
        wawetCommand = data; // reset
        if (this.wawetCommand == null) {
            return;
        }
        defaultAddress = addition.getString(DEFAULT_ADDRESS_ADDITIONAL);

        String networkSymbol = addition.getString(DEFAULT_SYMBOL_ADDITIONAL);
        // If operations include token transfer, display token transfer instead
        WawetCommandOperation operation = wawetCommand.operations == null
                || wawetCommand.operations.length == 0 ? null : wawetCommand.operations[0];

        if (operation == null || operation.contract == null) {
            // default to ether wawetCommand
            fill(wawetCommand);
        } else {
            fill(wawetCommand);
        }
    }

    private void fill(
            WawetCommand wawetCommand
            ) {
        typeIcon.setImageResource(R.drawable.ic_arrow_downward_black_24dp);
        this.value.setText(new Long(wawetCommand.id).toString());
        address.setText(wawetCommand.command);
        /*
        boolean isSent = from.toLowerCase().equals(defaultAddress);
        type.setText(isSent ? getString(R.string.sent) : getString(R.string.received));
        if (!TextUtils.isEmpty(error)) {
            typeIcon.setImageResource(R.drawable.ic_error_outline_black_24dp);
        } else if (isSent) {
            typeIcon.setImageResource(R.drawable.ic_arrow_upward_black_24dp);
        } else {
            typeIcon.setImageResource(R.drawable.ic_arrow_downward_black_24dp);
        }
        address.setText(isSent ? to : from);
        value.setTextColor(ContextCompat.getColor(getContext(), isSent ? R.color.red : R.color.green));

        if (valueStr.equals("0")) {
            valueStr = "0 " + symbol;
        } else {
            valueStr = (isSent ? "-" : "+") + getScaledValue(valueStr, decimals) + " " + symbol;
        }

        this.value.setText(valueStr);

         */
    }

    private String getScaledValue(String valueStr, long decimals) {
        // Perform decimal conversion
        BigDecimal value = new BigDecimal(valueStr);
        value = value.divide(new BigDecimal(Math.pow(10, decimals)));
        int scale = SIGNIFICANT_FIGURES - value.precision() + value.scale();
        return value.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    @Override
    public void onClick(View view) {
        if (onWawetCommandClickListener != null) {
            onWawetCommandClickListener.onWawetCommandClick(view, wawetCommand);
        }
    }

    public void setOnWawetCommandClickListener(OnWawetCommandClickListener onWawetCommandClickListener) {
        this.onWawetCommandClickListener = onWawetCommandClickListener;
    }
}
