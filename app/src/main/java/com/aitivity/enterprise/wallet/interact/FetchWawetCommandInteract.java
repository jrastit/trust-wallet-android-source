package com.aitivity.enterprise.wallet.interact;

import com.aitivity.enterprise.wallet.entity.WawetCommand;
import com.wallet.crypto.trustapp.repository.WalletRepositoryType;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FetchWawetCommandInteract {

	private final WalletRepositoryType walletRepository;

	public FetchWawetCommandInteract(WalletRepositoryType walletRepository) {
		this.walletRepository = walletRepository;
	}

	public Single<WawetCommand[]> fetch() {
		return walletRepository
				.getWawetCommand()
				.observeOn(AndroidSchedulers.mainThread());
	}
}
