package com.codepuran.d2h.dto;

public class SubscriptionPackOutput {
  private String packageName;
  private double monthlyPrice;
  private double subscriptionAmount;
  private int noOfMonths;
  private double finalPriceAfterDiscount;
  private double discountApplied;
  private double accountBalance;

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public double getMonthlyPrice() {
    return monthlyPrice;
  }

  public void setMonthlyPrice(double monthlyPrice) {
    this.monthlyPrice = monthlyPrice;
  }

  public double getSubscriptionAmount() {
    return subscriptionAmount;
  }

  public void setSubscriptionAmount(double subscriptionAmount) {
    this.subscriptionAmount = subscriptionAmount;
  }

  public int getNoOfMonths() {
    return noOfMonths;
  }

  public void setNoOfMonths(int noOfMonths) {
    this.noOfMonths = noOfMonths;
  }

  public double getFinalPriceAfterDiscount() {
    return finalPriceAfterDiscount;
  }

  public void setFinalPriceAfterDiscount(double finalPriceAfterDiscount) {
    this.finalPriceAfterDiscount = finalPriceAfterDiscount;
  }

  public double getDiscountApplied() {
    return discountApplied;
  }

  public void setDiscountApplied(double discountApplied) {
    this.discountApplied = discountApplied;
  }

  public double getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(double accountBalance) {
    this.accountBalance = accountBalance;
  }
}
