package com.aitivity.enterprise.wallet.service;

import com.aitivity.enterprise.wallet.entity.WawetCommand;

import io.reactivex.Observable;

public interface WawetCommandService {

    Observable<WawetCommand[]> fetchCommand(String command);
}
