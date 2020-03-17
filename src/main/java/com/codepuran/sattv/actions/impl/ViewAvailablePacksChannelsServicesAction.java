package com.codepuran.sattv.actions.impl;

import com.codepuran.sattv.actions.Action;
import com.codepuran.sattv.dao.ChannelDao;
import com.codepuran.sattv.dao.PackageDao;
import com.codepuran.sattv.dao.SpecialServiceDao;
import com.codepuran.sattv.models.Channel;
import com.codepuran.sattv.models.Package;
import com.codepuran.sattv.models.SpecialService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ViewAvailablePacksChannelsServicesAction implements Action {

  private final PackageDao packageDao;
  private final ChannelDao channelDao;
  private final SpecialServiceDao specialServiceDao;

  public ViewAvailablePacksChannelsServicesAction(PackageDao packageDao, ChannelDao channelDao, SpecialServiceDao specialServiceDao) {
    this.packageDao = packageDao;
    this.channelDao = channelDao;
    this.specialServiceDao = specialServiceDao;
  }

  @Override
  public void perform(long userId) {
    List<Package> availablePackages = availablePackages();
    List<Channel> availableChannels = availableChannels();
    List<SpecialService> specialServices = availableSerices();
    output(availablePackages, availableChannels, specialServices);
  }

  private List<Package> availablePackages() {
    return packageDao.findAll();
  }

  private List<Channel> availableChannels() {
    return channelDao.findAll();
  }

  private List<SpecialService> availableSerices() {
    return specialServiceDao.findAll();
  }

  private List<SpecialService> AvailableSpecialServices() {
    return specialServiceDao.findAll();
  }

  private void output(List<Package> packages, List<Channel> channels, List<SpecialService> specialServices) {
    System.out.print("Available packs for subscription: ");
    System.out.println();
    packages.forEach(pack -> {
      System.out.print(pack.getName());
      System.out.print(" || ");
      Set<Channel> channelsIncludedInPack = pack.getChannels();
      String commaSeparatedChannelOfPacks = channelsIncludedInPack.stream().map(Channel::getName).collect(Collectors.toList()).stream().collect(Collectors.joining(","));
      System.out.print(commaSeparatedChannelOfPacks);
      System.out.print(" || ");
      System.out.print(pack.getPrice() + " Rs.");
      System.out.println();
    });

    System.out.println("Available channels for subscription: ");
    String commaSeparatedChannel = channels.stream().map(Channel::getName).collect(Collectors.toList()).stream().collect(Collectors.joining(","));
    System.out.println(commaSeparatedChannel);

    System.out.println("Available services for subscription: ");
    String commaSeparatedServices = specialServices.stream().map(SpecialService::getName).collect(Collectors.toList()).stream().collect(Collectors.joining(","));
    System.out.println(commaSeparatedServices);

  }
}
