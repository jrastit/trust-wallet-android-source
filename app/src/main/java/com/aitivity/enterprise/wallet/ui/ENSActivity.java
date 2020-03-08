package com.aitivity.enterprise.wallet.ui;

import android.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.router.TransactionsRouter;
import com.wallet.crypto.trustapp.ui.BaseActivity;
import com.wallet.crypto.trustapp.viewmodel.TransactionsViewModel;
import com.wallet.crypto.trustapp.viewmodel.TransactionsViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public class ENSActivity extends BaseActivity implements HasFragmentInjector {

    @Inject
    TransactionsViewModelFactory transactionsViewModelFactory;
    private TransactionsViewModel viewModel;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    private static final long GET_BALANCE_INTERVAL = 8;

    private TextView ensName;
    private TextView ensNameResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ens);
        toolbar();

        findViewById(R.id.ensNameBtn).setOnClickListener(view -> onENSNameBtn());
        ensName = findViewById(R.id.ensNameEdit);
        ensNameResult = findViewById(R.id.ensNameResult);

        viewModel = ViewModelProviders.of(this, transactionsViewModelFactory)
                .get(TransactionsViewModel.class);


        viewModel.defaultNetwork().observe(this, this::onDefaultNetwork);

        viewModel.ensRegister().observe(this, this::onENSRegister);

    }

    private void onENSRegister(String result){
        ensNameResult.setText(result);
    }



    private void onENSNameBtn() {
        String name = ensName.getText().toString();
        viewModel.ensRegister(name);
        ensNameResult.setText("Registering... " + name);
    }



    @Override
    protected void onResume() {
        super.onResume();
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
