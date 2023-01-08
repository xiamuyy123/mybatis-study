package com.yq.web;

import com.yq.excepetions.MoneyNotEnoughException;
import com.yq.excepetions.TransferException;
import com.yq.service.AccountService;
import com.yq.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AccountServlet extends HttpServlet {

    private AccountService accountService = new AccountServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fromActno = Integer.parseInt(req.getParameter("fromActno"));
        int toActno = Integer.parseInt(req.getParameter("toActno"));
        Double money = Double.parseDouble(req.getParameter("money"));
        try {
            accountService.transfer((long) fromActno, (long) toActno,money);
            resp.sendRedirect("success.html");
        } catch (MoneyNotEnoughException e) {
            resp.sendRedirect("error1.html");
        } catch (TransferException e) {
            resp.sendRedirect("error2.html");
        } catch (ArithmeticException e){
            resp.sendRedirect("error2.html");
        }


    }
}
