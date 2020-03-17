package com.codepuran.sattv.actions.impl;

import com.codepuran.sattv.actions.Action;
import com.codepuran.sattv.dao.PackageDao;
import com.codepuran.sattv.dao.UserDao;
import com.codepuran.sattv.dto.SubscribePackInputData;
import com.codepuran.sattv.dto.SubscriptionPackOutput;
import com.codepuran.sattv.miscellaneous.NotificationService;
import com.codepuran.sattv.models.Package;
import com.codepuran.sattv.models.Subscription;
import com.codepuran.sattv.models.User;

import java.util.Optional;
import java.util.Scanner;

public class SubscribePackageAction implements Action {

  private final Scanner scanner;
  private final PackageDao packageDao;
  private final UserDao userDao;

  public SubscribePackageAction(Scanner scanner, PackageDao packageDao, UserDao userDao) {
    this.scanner = scanner;
    this.packageDao = packageDao;
    this.userDao = userDao;
  }

  @Override
  public void perform(long userId) {

    try {
      Optional<User> userOptional = this.userDao.findById(userId);

      if (userOptional.isPresent()) {
        User user = userOptional.get();
        Subscription subscription = user.getSubscription();
        if (subscription == null || subscription.getPack() == null) {
          SubscribePackInputData input = input();
          SubscriptionPackOutput output = processSubscription(input, user);
          showResult(output);
          NotificationService.sendEmailNotification();
          NotificationService.sendSMSNotification();
        } else {
          new RuntimeException("You already subscribed to one package");
        }
      } else {
        throw new RuntimeException("User not exist");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private SubscribePackInputData input() {
    System.out.println("Enter the Pack you wish to subscribe: ");
    String packToBeSubscribe;
    do {
      scanner.nextLine();
      packToBeSubscribe = scanner.nextLine();
    } while (false); //TODO input validation intentionally avoid. Reason: Avoid writing too many lines of production code.

    System.out.println("Enter the months: ");
    int months;
    do {
      months = scanner.nextInt();
    } while (false); //TODO input validation intentionally avoid. Reason: Avoid writing too many lines of production code.

    SubscribePackInputData subscribePackInputData = new SubscribePackInputData(packToBeSubscribe, months);
    return subscribePackInputData;
  }

  public SubscriptionPackOutput processSubscription(SubscribePackInputData inputData, User user) {
    SubscriptionPackOutput output = new SubscriptionPackOutput();
    String packageSlug = inputData.getPack();
    Optional<Package> packageOptional = packageDao.findBySlug(packageSlug);
    if (packageOptional.isPresent()) {
      Package pack = packageOptional.get();
      int subscriptionMonths = inputData.getMonth();
      double currentUserAccountBalance = user.getAccountBalance();
      double packagePricePerMonth = pack.getPrice();
      double totalPrice = packagePricePerMonth * subscriptionMonths;
      double effectivePurchasePrice;
      double discount = 0;
      if (subscriptionMonths >= 3) {
        discount = (totalPrice * 10) / 100;
        effectivePurchasePrice = totalPrice - discount;
      } else {
        effectivePurchasePrice = totalPrice;
      }
      if (currentUserAccountBalance >= effectivePurchasePrice) {
        double updatedUserAccountBalance = currentUserAccountBalance - effectivePurchasePrice;
        Subscription subscription = user.getSubscription();
        if (subscription == null) {
          subscription = new Subscription();
        }
        subscription.setPack(pack);
        user.setAccountBalance(updatedUserAccountBalance);
        user.setSubscription(subscription);

        output.setPackageName(pack.getName());
        output.setNoOfMonths(subscriptionMonths);
        output.setAccountBalance(user.getAccountBalance());
        output.setDiscountApplied(discount);
        output.setFinalPriceAfterDiscount(effectivePurchasePrice);
        output.setMonthlyPrice(pack.getPrice());
        output.setSubscriptionAmount(totalPrice);
        return output;
      } else {
        throw new RuntimeException("insufficient account balance");
      }
    } else {
      throw new RuntimeException("package not available");
    }
  }

  private void showResult(SubscriptionPackOutput output) {
    System.out.println("You have successfully subscribed the following packs: " + output.getPackageName());
    System.out.println("Monthly price: " + output.getMonthlyPrice());
    System.out.println("No of months: " + output.getNoOfMonths());
    System.out.println("Subscription Amount: " + output.getSubscriptionAmount());
    System.out.println("Discount applied: " + output.getDiscountApplied());
    System.out.println("Final Price after discount: " + output.getFinalPriceAfterDiscount());
    System.out.println("Account balance: " + output.getAccountBalance());
  }
}
