package com.codepuran.d2h.actions.impl;

import com.codepuran.d2h.actions.Action;
import com.codepuran.d2h.dao.ChannelDao;
import com.codepuran.d2h.dao.UserDao;
import com.codepuran.d2h.models.Channel;
import com.codepuran.d2h.models.Subscription;
import com.codepuran.d2h.models.User;

import java.util.*;

public class AddChannelsExistingSubscriptionAction implements Action {

  private final Scanner scanner;
  private final UserDao userDao;
  private final ChannelDao channelDao;

  public AddChannelsExistingSubscriptionAction(Scanner scanner, UserDao userDao, ChannelDao channelDao) {
    this.scanner = scanner;
    this.userDao = userDao;
    this.channelDao = channelDao;
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
      System.out.println("Enter channel names to add (separated by commas): ");
      scanner.nextLine();
      input = scanner.nextLine();
    } while (false);//TODO input validation intentionally avoid. Reason: Avoid writing too many lines of production code.
    return input;
  }

  public double processSubscription(String input, User user) {
    String[] inputArray = input.split(",");
    Set<String> channelsNameToBeSubscribed = new HashSet<String>(Arrays.asList(inputArray));
    List<Channel> channelsToSubscribe = new ArrayList<>();
    double subscriptionAmount = 0;
    for (String channelName : channelsNameToBeSubscribed) {
      Optional<Channel> channelOptional = channelDao.findByName(channelName);
      if (channelOptional.isPresent()) {
        Channel channel = channelOptional.get();
        channelsToSubscribe.add(channel);
        subscriptionAmount = subscriptionAmount + channel.getPrice();
      }
    }
    Subscription currentSubscription = user.getSubscription();
    if (currentSubscription == null) {
      currentSubscription = new Subscription();
    }
    if (channelsToSubscribe == null || channelsToSubscribe.isEmpty()) {
      throw new RuntimeException("Select Valid Channels");
    }
    Set<Channel> currentSubscribedChannel = currentSubscription.getChannels();
    if (currentSubscribedChannel == null) {
      currentSubscribedChannel = new HashSet<>();
    }
    double userAccountBalance = user.getAccountBalance();
    if (userAccountBalance >= subscriptionAmount) {
      currentSubscribedChannel.addAll(channelsToSubscribe);
      currentSubscription.setChannels(currentSubscribedChannel);
      user.setSubscription(currentSubscription);
      double updateAccountBalance = userAccountBalance - subscriptionAmount;
      user.setAccountBalance(updateAccountBalance);
    } else {
      throw new RuntimeException("You do not have sufficient balance to subscribe selected channels");
    }
    return user.getAccountBalance();
  }

  private void displayOutput(double updatedAccountBalance) {
    System.out.println("Channels added successfully.");
    System.out.println("Account balance: " + updatedAccountBalance + "Rs.");
  }
}
