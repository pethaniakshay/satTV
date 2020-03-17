package com.codepuran.sattv;

import com.codepuran.sattv.actions.Action;
import com.codepuran.sattv.actions.impl.*;
import com.codepuran.sattv.dao.ChannelDao;
import com.codepuran.sattv.dao.PackageDao;
import com.codepuran.sattv.dao.SpecialServiceDao;
import com.codepuran.sattv.dao.UserDao;
import com.codepuran.sattv.dto.ActionAndActionLabel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SatTVApplication {

  public static void main(String[] args) {
    SatTVApplication.init();
  }

  public static void init() {
    DataSource dataSource = new DataSource();
    ChannelDao channelDao = new ChannelDao(dataSource);
    PackageDao packageDao = new PackageDao(dataSource);
    SpecialServiceDao specialServiceDao = new SpecialServiceDao(dataSource);
    UserDao userDao = new UserDao(dataSource);

    Scanner scanner = new Scanner(System.in);

    Action addChannelsExistingSubscriptionAction = new AddChannelsExistingSubscriptionAction(scanner, userDao, channelDao);
    Action rechargeAccountAction = new RechargeAccountAction(scanner, userDao);
    Action subscribePackageAction = new SubscribePackageAction(scanner, packageDao, userDao);
    Action subscribeSpecialPackAction = new SubscribeSpecialPackAction(scanner, userDao, specialServiceDao);
    Action updateContactInfoAction = new UpdateContactInfoAction(scanner, userDao);
    Action viewAvailablePacksChannelsServicesAction = new ViewAvailablePacksChannelsServicesAction(packageDao, channelDao, specialServiceDao);
    Action viewCurrentBalanceAction = new ViewCurrentBalanceAction(userDao);
    Action viewCurrentSubscriptionDetailsAction = new ViewCurrentSubscriptionDetailsAction(userDao);
    Action exitAction = new ExitAction();

    List<ActionAndActionLabel> actionAndActionLabels = new ArrayList<>();
    actionAndActionLabels.add(new ActionAndActionLabel("View current balance in the account", viewCurrentBalanceAction));
    actionAndActionLabels.add(new ActionAndActionLabel("Recharge Account", rechargeAccountAction));
    actionAndActionLabels.add(new ActionAndActionLabel("View available packs, channels and services", viewAvailablePacksChannelsServicesAction));
    actionAndActionLabels.add(new ActionAndActionLabel("Subscribe to base packs", subscribePackageAction));
    actionAndActionLabels.add(new ActionAndActionLabel("Add channels to an existing subscription", addChannelsExistingSubscriptionAction));
    actionAndActionLabels.add(new ActionAndActionLabel("Subscribe to special services", subscribeSpecialPackAction));
    actionAndActionLabels.add(new ActionAndActionLabel("View current subscription details", viewCurrentSubscriptionDetailsAction));
    actionAndActionLabels.add(new ActionAndActionLabel("Update email and phone number for notifications", updateContactInfoAction));
    actionAndActionLabels.add(new ActionAndActionLabel("Exit", exitAction));

    DashBoard dashBoard = new DashBoard(scanner, actionAndActionLabels);
    dashBoard.launch(1L);
  }
}
