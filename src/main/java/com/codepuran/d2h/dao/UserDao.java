package com.codepuran.d2h.dao;

import com.codepuran.d2h.DataSource;
import com.codepuran.d2h.models.Subscription;
import com.codepuran.d2h.models.User;

import java.util.List;
import java.util.Optional;

public class UserDao {

  private final DataSource dataSource;

  public UserDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public double getUserBalance(long userId) {
    List<User> users = dataSource.getUsers();
    double currentBalance;
    //TODO scope to improvement

    Optional<User> userOptional = users.stream().filter(u -> u.getId() == userId).findFirst();
    if (userOptional.isPresent()) {
      currentBalance = userOptional.get().getAccountBalance();
    } else {
      throw new RuntimeException("User Not Exist");
    }
    return currentBalance;
  }

  public double rechargeAccountBalance(long userId, double balance) {
    List<User> users = dataSource.getUsers();
    double currentBalance;
    double updatedBalance;
    Optional<User> userOptional = users.stream().filter(u -> u.getId() == userId).findFirst();
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      currentBalance = user.getAccountBalance();
      updatedBalance = currentBalance + balance;
      user.setAccountBalance(updatedBalance);
      updatedBalance = user.getAccountBalance();
    } else {
      throw new RuntimeException("User Not Exist");
    }
    return updatedBalance;
  }

  public Optional<Subscription> getSubscriptionByUserId(long userId) {
    List<User> users = this.dataSource.getUsers();
    Optional<User> userOptional = users.stream().filter(u -> u.getId() == userId).findFirst();
    Optional<Subscription> subscriptionOptional = Optional.empty();
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      if (user.getSubscription() != null) {
        subscriptionOptional = Optional.of(user.getSubscription());
      }
    }
    return subscriptionOptional;
  }

  public Optional<User> findById(long userId) {
    List<User> users = dataSource.getUsers();
    Optional<User> userOptional = users.stream().filter(u -> u.getId() == userId).findFirst();
    return userOptional;
  }
}
