package com.codepuran.sattv.actions.impl;

import com.codepuran.sattv.actions.Action;
import com.codepuran.sattv.dao.UserDao;

import java.util.Scanner;

public class RechargeAccountAction implements Action {

  private final Scanner scanner;

  private final UserDao userDao;

  public RechargeAccountAction(Scanner scanner, UserDao userDao) {
    this.scanner = scanner;
    this.userDao = userDao;
  }

  @Override
  public void perform(long userId) {
    try {
      double balanceTobeUpdate = input();
      double updatedBalance = userDao.rechargeAccountBalance(userId, balanceTobeUpdate);
      showResult(updatedBalance);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private double input() {
    System.out.println("Enter the amount to recharge: ");
    double input = scanner.nextDouble();
    if (input < 1) {
      System.out.println("Balance Can not be in minus.");
      //TODO handle this
      input = input();
    }
    return input;
  }

  private void showResult(double updatedBalance) {
    System.out.println("Recharge completed successfully. Current balance is: " + updatedBalance + " Rs.");
  }

}
