package com.yq.dao.impl;

import com.yq.dao.AccountDao;
import com.yq.pojo.Account;
import com.yq.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountDaoImpl1 implements AccountDao {
    @Override
    public Account selectAccountByActno(Long actno) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Account account = (Account) sqlSession.selectOne("account.selectAccountByActno", actno);

        return account;
    }

    @Override
    public int updateAccount(Account account) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.update("account.updateAccount",account);

        return count;
    }
}
