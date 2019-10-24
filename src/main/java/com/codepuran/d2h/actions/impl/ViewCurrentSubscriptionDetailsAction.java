package com.codepuran.d2h.actions.impl;

import com.codepuran.d2h.actions.Action;
import com.codepuran.d2h.dao.UserDao;
import com.codepuran.d2h.models.Channel;
import com.codepuran.d2h.models.SpecialService;
import com.codepuran.d2h.models.Subscription;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ViewCurrentSubscriptionDetailsAction implements Action {

  private UserDao userDao;

  public ViewCurrentSubscriptionDetailsAction(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void perform(long userId) {
    Optional<Subscription> subscriptionOptional = userDao.getSubscriptionByUserId(userId);
    if (subscriptionOptional.isPresent()) {
      showResult(subscriptionOptional.get());
    } else {
      System.out.println("You do not have any subscription going on");
    }
  }

  private void showResult(Subscription subscription) {
    System.out.println("Currently subscribed packs and channels: ");
    if (subscription.getPack() != null) {
      System.out.println("Packs: ");
      System.out.print(subscription.getPack().getName());
    }
    if (subscription.getChannels() != null) {
      System.out.println();
      System.out.println("Channels: ");
      Set<Channel> channels = subscription.getChannels();
      String commaSeparatedChannel = channels.stream().map(Channel::getName).collect(Collectors.toList()).stream().collect(Collectors.joining(","));
      System.out.print(commaSeparatedChannel);
    }
    System.out.println();
    System.out.println("Currently subscribed services: ");
    Set<SpecialService> specialServices = subscription.getSpecialServices();
    if (specialServices != null) {
      String commaSeparatedServices = specialServices.stream().map(SpecialService::getName).collect(Collectors.toList()).stream().collect(Collectors.joining(","));
      System.out.print(commaSeparatedServices);
    }
  }
}
