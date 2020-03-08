package com.aitivity.enterprise.wallet.ui;

import android.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wallet.crypto.trustapp.C;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.router.TransactionsRouter;
import com.aitivity.enterprise.wallet.viewmodel.AaveViewModel;
import com.aitivity.enterprise.wallet.viewmodel.AaveViewModelFactory;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.viewmodel.TransactionsViewModel;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public class AaveActivity extends BaseActivity implements HasFragmentInjector {

    @Inject
    AaveViewModelFactory aaveViewModelFactory;


    private AaveViewModel viewModel;
    private TransactionsViewModel viewModel2;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    private static final long GET_BALANCE_INTERVAL = 8;

    private TextView test;
    private TextView balance;
    private TextView depositAmount;
    private TextView deposit;
    private TextView redeemAmount;
    private TextView redeem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aave);
        toolbar();
        //test = findViewById(R.id.test);
        balance = findViewById(R.id.defiBalance);
        deposit = findViewById(R.id.deposit);
        redeem = findViewById(R.id.redeem);

        depositAmount = findViewById(R.id.deposit_amount);
        redeemAmount = findViewById(R.id.redeem_amount);

        viewModel = ViewModelProviders.of(this, aaveViewModelFactory)
                .get(AaveViewModel.class);


        viewModel.test().observe(this, this::onTestChanged);
        viewModel.defaultWalletBalance().observe(this, this::onBalanceChanged);
        viewModel.deposit().observe(this, this::onDepositChanged);
        viewModel.redeem().observe(this, this::onRedeemChanged);
        viewModel.aaveBalance().observe(this, this::onAAVEBalanceChanged);

        //findViewById(R.id.testBtn).setOnClickListener(view -> onTest());
        //findViewById(R.id.getBalanceBtn).setOnClickListener(view -> onGetBalance());
        findViewById(R.id.depositBtn).setOnClickListener(view -> onDeposit());
        findViewById(R.id.redeemBtn).setOnClickListener(view -> onRedeem());

        viewModel.defaultNetwork().observe(this, this::onDefaultNetwork);
    }

    public void onTestChanged(String test) {
        this.test.setText(test);
    }

    private void onDepositChanged(String deposit) {
        depositAmount.setText("");
        this.deposit.setText(deposit);
        findViewById(R.id.depositBtn).setVisibility(View.VISIBLE);
    }

    private void onRedeemChanged(String redeem) {
        redeemAmount.setText("");
        this.redeem.setText(redeem);
        findViewById(R.id.redeemBtn).setVisibility(View.VISIBLE);
    }

    private void onDeposit() {
        viewModel.deposit(new Double(depositAmount.getText().toString()));
        depositAmount.setText("Pending...");
        findViewById(R.id.depositBtn).setVisibility(View.INVISIBLE);
    }
    private void onRedeem() {
        viewModel.redeem(new Double(redeemAmount.getText().toString()));
        redeemAmount.setText("Pending...");
        findViewById(R.id.redeemBtn).setVisibility(View.INVISIBLE);
    }

    private void onBalanceChanged(Map<String, String> balance) {
        ActionBar actionBar = getSupportActionBar();
        NetworkInfo networkInfo = viewModel.defaultNetwork().getValue();
        Wallet wallet = viewModel.defaultWallet().getValue();
        if (actionBar == null || networkInfo == null || wallet == null) {
            return;
        }
        if (TextUtils.isEmpty(balance.get(C.USD_SYMBOL))) {
            actionBar.setTitle(balance.get(networkInfo.symbol) + " " + networkInfo.symbol);
            actionBar.setSubtitle("");
        } else {
            actionBar.setTitle("$" + balance.get(C.USD_SYMBOL));
            actionBar.setSubtitle(balance.get(networkInfo.symbol) + " " + networkInfo.symbol);
        }
    }

    private void onAAVEBalanceChanged(Map<String, String> balances) {
        NetworkInfo networkInfo = viewModel.defaultNetwork().getValue();
        Wallet wallet = viewModel.defaultWallet().getValue();
        if (balance == null || networkInfo == null || wallet == null) {
            return;
        }
        if (TextUtils.isEmpty(balances.get(C.USD_SYMBOL))) {
            balance.setText("AAVE " + balances.get(networkInfo.symbol) + " " + networkInfo.symbol);
        } else {
            balance.setText("AAVE $" + balances.get(C.USD_SYMBOL));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //test.setText("--");
        balance.setText("AAVE -- ETH");
        deposit.setText("");
        redeem.setText("");
        //setTitle(getString(R.string.unknown_balance_without_symbol));
        //setSubtitle("");
        viewModel.prepare();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                new TransactionsRouter().open(this, true);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new TransactionsRouter().open(this, true);
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }

    private void onDefaultNetwork(NetworkInfo networkInfo) {

    }
}
