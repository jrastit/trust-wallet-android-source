package com.wallet.crypto.trustapp.ui;

import android.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
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

    private TextView exchangeRate;

    private TextView balance;

    private Button getExchangeRateButton;

    private Button getBalanceButton;

    private TextView balanceDecoded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aave);
        toolbar();
        exchangeRate = findViewById(R.id.exchangeRate);
        balance = findViewById(R.id.balance);
        balanceDecoded = findViewById(R.id.balance_decoded);

        viewModel = ViewModelProviders.of(this, aaveViewModelFactory)
                .get(AaveViewModel.class);
        viewModel.getExchangeRate().observe(this, this::onExchangeRateChanged);
        viewModel.getBalance().observe(this, this::onBalanceChanged);
        viewModel.getBalanceDecoded().observe(this, this::onBalanceDecodedChanged);

        getExchangeRateButton = findViewById(R.id.getExchangeRateBtn);
        getBalanceButton = findViewById(R.id.getBalanceBtn);

        getExchangeRateButton.setOnClickListener(view -> onGetExchangeRate());
        getBalanceButton.setOnClickListener(view -> onGetBalance());

        viewModel.defaultNetwork().observe(this, this::onDefaultNetwork);


    }

    public void onExchangeRateChanged(String exchangeRate) {
        this.exchangeRate.setText(exchangeRate);
    }

    public void onBalanceChanged(String balance) {
        this.balance.setText(balance);
    }

    public void onBalanceDecodedChanged(String balanceDecoded) {
        this.balanceDecoded.setText(balanceDecoded);
    }

    private void onGetExchangeRate() {
        viewModel.getExchangeRate(null);
    }

    private void onGetBalance() {
        viewModel.getBalance(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        exchangeRate.setText(getString(R.string.unknown_balance_without_symbol));
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
