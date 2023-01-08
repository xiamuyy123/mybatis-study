package com.yq.dao;

import com.yq.pojo.Account;

public interface AccountDao {

    Account selectAccountByActno(Long actno);

    int updateAccount(Account account);
}
