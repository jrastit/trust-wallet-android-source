package com.wallet.crypto.trustapp.ui;

import android.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.router.TransactionsRouter;
import com.aitivity.enterprise.wallet.viewmodel.AaveViewModel;
import com.aitivity.enterprise.wallet.viewmodel.AaveViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public class AaveActivity extends BaseActivity implements HasFragmentInjector {

    @Inject
    AaveViewModelFactory aaveViewModelFactory;
    private AaveViewModel viewModel;


    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

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
        test = findViewById(R.id.test);
        balance = findViewById(R.id.balance);
        deposit = findViewById(R.id.deposit);
        redeem = findViewById(R.id.redeem);
        
        depositAmount = findViewById(R.id.deposit_amount);
        redeemAmount = findViewById(R.id.redeem_amount);

        viewModel = ViewModelProviders.of(this, aaveViewModelFactory)
                .get(AaveViewModel.class);

        viewModel.test().observe(this, this::onTestChanged);
        viewModel.getBalance().observe(this, this::onBalanceChanged);
        viewModel.deposit().observe(this, this::onDepositChanged);
        viewModel.redeem().observe(this, this::onRedeemChanged);

        findViewById(R.id.testBtn).setOnClickListener(view -> onTest());
        findViewById(R.id.getBalanceBtn).setOnClickListener(view -> onGetBalance());
        findViewById(R.id.depositBtn).setOnClickListener(view -> onDeposit());
        findViewById(R.id.redeemBtn).setOnClickListener(view -> onRedeem());

        viewModel.defaultNetwork().observe(this, this::onDefaultNetwork);
    }

    public void onTestChanged(String test) {
        this.test.setText(test);
    }

    public void onBalanceChanged(String balance) {
        this.balance.setText(balance);
    }

    private void onDepositChanged(String deposit) {
        this.deposit.setText(deposit);
    }

    private void onRedeemChanged(String redeem) {
        this.redeem.setText(redeem);
    }

    private void onTest() {
        viewModel.test(null);
    }

    private void onGetBalance() {
        viewModel.getBalance(null);
    }

    private void onDeposit() {
        viewModel.deposit(new Double(depositAmount.getText().toString()));
    }

    private void onRedeem() {
        viewModel.redeem(new Double(redeemAmount.getText().toString()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        test.setText("--");
        balance.setText("--");
        deposit.setText("--");
        redeem.setText("--");
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
