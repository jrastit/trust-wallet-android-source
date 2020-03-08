package com.aitivity.enterprise.wallet.ui.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.aitivity.enterprise.wallet.entity.WawetCommand;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.BaseActivity;

import dagger.android.AndroidInjection;

import static com.wallet.crypto.trustapp.C.Key.WAWETCOMMAND;

public class WawetCommandDetailActivity extends BaseActivity implements View.OnClickListener {

    //@Inject
    //WawetCommandDetailViewModelFactory wawetCommandDetailViewModelFactory;
    //private WawetCommandDetailViewModel viewModel;

    private WawetCommand wawetCommand;
    //private TextView amount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        setContentView(R.layout.activity_wawetcommand_detail);

        wawetCommand = getIntent().getParcelableExtra(WAWETCOMMAND);
        if (wawetCommand == null) {
            finish();
            return;
        }
        toolbar();

        //BigInteger gasFee = new BigInteger(wawetCommand.gasUsed).multiply(new BigInteger(wawetCommand.gasPrice));
        //amount = findViewById(R.id.amount);
        ((TextView) findViewById(R.id.id)).setText(new Long(wawetCommand.id).toString());
        ((TextView) findViewById(R.id.command)).setText(wawetCommand.command);
        //((TextView) findViewById(R.id.from)).setText(new Long(wawetCommand.id).toString());
        /*
        ((TextView) findViewById(R.id.to)).setText(wawetCommand.to);
        ((TextView) findViewById(R.id.gas_fee)).setText(BalanceUtils.weiToEth(gasFee).toPlainString());
        ((TextView) findViewById(R.id.txn_hash)).setText(wawetCommand.hash);
        ((TextView) findViewById(R.id.txn_time)).setText(getDate(wawetCommand.timeStamp));
        ((TextView) findViewById(R.id.block_number)).setText(wawetCommand.blockNumber);
        */
        findViewById(R.id.more_detail).setOnClickListener(this);
         
        /*
        viewModel = ViewModelProviders.of(this, wawetCommandDetailViewModelFactory)
                .get(WawetCommandDetailViewModel.class);
        viewModel.defaultNetwork().observe(this, this::onDefaultNetwork);
        viewModel.defaultWallet().observe(this, this::onDefaultWallet);
        
         */
    }

    /*
    private void onDefaultWallet(Wallet wallet) {
        boolean isSent = wawetCommand.from.toLowerCase().equals(wallet.address);
        String rawValue;
        String symbol;
        long decimals = 18;
        NetworkInfo networkInfo = viewModel.defaultNetwork().getValue();
        if (wawetCommand.operations == null || wawetCommand.operations.length == 0) {
            rawValue = wawetCommand.value;
            symbol = networkInfo == null ? "" : networkInfo.symbol;
        } else {
            rawValue = wawetCommand.operations[0].value;
            decimals = wawetCommand.operations[0].contract.decimals;
            symbol = wawetCommand.operations[0].contract.symbol;
        }

        amount.setTextColor(ContextCompat.getColor(this, isSent ? R.color.red : R.color.green));
        if (rawValue.equals("0")) {
            rawValue = "0 " + symbol;
        } else {
            rawValue = (isSent ? "-" : "+") + getScaledValue(rawValue, decimals) + " " + symbol;
        }
        amount.setText(rawValue);
    }

    private String getScaledValue(String valueStr, long decimals) {
        // Perform decimal conversion
        BigDecimal value = new BigDecimal(valueStr);
        value = value.divide(new BigDecimal(Math.pow(10, decimals)));
        int scale = 3 - value.precision() + value.scale();
        return value.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    private String getDate(long timeStampInSec) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timeStampInSec * 1000);
        return DateFormat.getLongDateFormat(this).format(cal.getTime());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            viewModel.shareWawetCommandDetail(this, wawetCommand);
        }
        return super.onOptionsItemSelected(item);
    }

    private void onDefaultNetwork(NetworkInfo networkInfo) {
        findViewById(R.id.more_detail).setVisibility(
                TextUtils.isEmpty(networkInfo.etherscanUrl) ? View.GONE : View.VISIBLE);
    }
    */
    @Override
    public void onClick(View v) {
        //viewModel.showMoreDetails(v.getContext(), wawetCommand);
    }
    
     
}
