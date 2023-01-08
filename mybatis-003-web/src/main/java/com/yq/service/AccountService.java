package com.yq.service;

import com.yq.excepetions.MoneyNotEnoughException;
import com.yq.excepetions.TransferException;

public interface AccountService {
    void transfer(Long fromActno,Long toActno,Double balance ) throws MoneyNotEnoughException, TransferException;
}
