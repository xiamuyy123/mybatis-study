package com.yq.service.impl;

import com.yq.dao.AccountDao;

import com.yq.excepetions.MoneyNotEnoughException;
import com.yq.excepetions.TransferException;
import com.yq.pojo.Account;
import com.yq.service.AccountService;
import com.yq.utils.GenerateDaoByJavassist;
import com.yq.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao = SqlSessionUtil.openSession().getMapper(AccountDao.class);

    @Override
    public void transfer(Long fromActno, Long toActno, Double balance) throws MoneyNotEnoughException, TransferException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //1.判断A余额是否足够
        Account fromAccount = accountDao.selectAccountByActno(fromActno);
        if(fromAccount==null||fromAccount.getBalance()<balance){
            throw new MoneyNotEnoughException("账户不存在或余额不足");
        }
        //2.转账
        Account toAccount = accountDao.selectAccountByActno(toActno);
        if(toAccount==null){
            throw new MoneyNotEnoughException("账户不存在或余额不足");
        }
        fromAccount.setBalance(fromAccount.getBalance()-balance);
        toAccount.setBalance(toAccount.getBalance()+balance);
        int count = accountDao.updateAccount(fromAccount);
//        int i =10/0;
        count += accountDao.updateAccount(toAccount);
        if(count!=2){
            throw new TransferException("转账失败");
        }
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }
}
