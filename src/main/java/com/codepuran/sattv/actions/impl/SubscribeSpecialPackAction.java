package com.codepuran.sattv.actions.impl;

import com.codepuran.sattv.actions.Action;
import com.codepuran.sattv.dao.SpecialServiceDao;
import com.codepuran.sattv.dao.UserDao;
import com.codepuran.sattv.miscellaneous.NotificationService;
import com.codepuran.sattv.models.SpecialService;
import com.codepuran.sattv.models.Subscription;
import com.codepuran.sattv.models.User;

import java.util.*;

public class SubscribeSpecialPackAction implements Action {

  private final Scanner scanner;
  private final UserDao userDao;
  private final SpecialServiceDao specialServiceDao;

  public SubscribeSpecialPackAction(Scanner scanner, UserDao userDao, SpecialServiceDao specialServiceDao) {
    this.scanner = scanner;
    this.userDao = userDao;
    this.specialServiceDao = specialServiceDao;
  }

  @Override
  public void perform(long userId) {
    try {
      Optional<User> userOptional = this.userDao.findById(userId);
      if (userOptional.isPresent()) {
        User user = userOptional.get();
        String input = input();
        double updatedUserAccountBalance = processSubscription(input, user);
        displayOutput(updatedUserAccountBalance);
        NotificationService.sendEmailNotification();
        NotificationService.sendSMSNotification();
      } else {
        throw new RuntimeException("User Not Exist");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private String input() {
    String input;
    do {
      System.out.println("Enter the service name:");
      scanner.nextLine();
      input = scanner.nextLine();
    } while (false);//TODO input validation intentionally avoid. Reason: Avoid writing too many lines of production code.
    return input;
  }

  private double processSubscription(String input, User user) {
    String[] inputArray = input.split(",");
    Set<String> serviceNameToBeSubscribed = new HashSet<String>(Arrays.asList(inputArray));
    List<SpecialService> specialServicesTOBeSubscribe = new ArrayList<>();
    double subscriptionAmount = 0;
    for (String channelName : serviceNameToBeSubscribed) {
      Optional<SpecialService> specialServiceOptional = specialServiceDao.findByName(channelName);
      if (specialServiceOptional.isPresent()) {
        SpecialService specialService = specialServiceOptional.get();
        specialServicesTOBeSubscribe.add(specialService);
        subscriptionAmount = subscriptionAmount + specialService.getPrice();
      }
    }
    double userAccountBalance = user.getAccountBalance();
    Subscription currentSubscription = user.getSubscription();
    if (currentSubscription == null) {
      currentSubscription = new Subscription();
    }
    if (specialServicesTOBeSubscribe == null || specialServicesTOBeSubscribe.isEmpty()) {
      System.out.println("Please selete valid special pack");
    }
    Set<SpecialService> currentSubscribedServices = currentSubscription.getSpecialServices();
    if (currentSubscribedServices == null) {
      currentSubscribedServices = new HashSet<>();
    }
    if (userAccountBalance >= subscriptionAmount) {
      currentSubscribedServices.addAll(specialServicesTOBeSubscribe);
      currentSubscription.setSpecialServices(currentSubscribedServices);
      user.setSubscription(currentSubscription);
      double updateAccountBalance = userAccountBalance - subscriptionAmount;
      user.setAccountBalance(updateAccountBalance);
    } else {
      throw new RuntimeException("You do not have sufficient balance to subscribe selected special services");
    }
    return user.getAccountBalance();
  }

  private void displayOutput(double updatedAccountBalance) {
    System.out.println("Service subscribed successfully");
    System.out.println("Account balance: " + updatedAccountBalance + "Rs.");
  }
}
