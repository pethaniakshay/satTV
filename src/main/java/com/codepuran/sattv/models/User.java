package com.codepuran.sattv.models;

public class User {

  private long id;

  private String email;

  private String phone;

  private double accountBalance;

  private Subscription subscription;

  public User(long id, String email, String phone, double accountBalance) {
    this.id = id;
    this.email = email;
    this.phone = phone;
    this.accountBalance = accountBalance;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public double getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(double accountBalance) {
    this.accountBalance = accountBalance;
  }

  public Subscription getSubscription() {
    return subscription;
  }

  public void setSubscription(Subscription subscription) {
    this.subscription = subscription;
  }
}
