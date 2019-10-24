package com.codepuran.sattv.actions.impl;

import com.codepuran.sattv.actions.Action;
import com.codepuran.sattv.dao.UserDao;

public class ViewCurrentBalanceAction implements Action {
  private final UserDao userDao;

  public ViewCurrentBalanceAction(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void perform(long userId) {
    double currentAccountBalance = balance(userId);
    showResult(currentAccountBalance);
  }

  private double balance(long userId) {
    return userDao.getUserBalance(userId);
  }

  private void showResult(double currentAccountBalance) {
    System.out.println("Current Balance is " + currentAccountBalance + " Rs.");
  }
}
