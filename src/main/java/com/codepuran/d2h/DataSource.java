package com.codepuran.d2h;

import com.codepuran.d2h.models.Channel;
import com.codepuran.d2h.models.Package;
import com.codepuran.d2h.models.SpecialService;
import com.codepuran.d2h.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DataSource {

  public Channel channel1 = new Channel("Zee", 10);
  public Channel channel2 = new Channel("Sony", 15);
  public Channel channel3 = new Channel("Star Plus", 20);
  public Channel channel4 = new Channel("Discovery", 10);
  public Channel channel5 = new Channel("Nat Geo", 20);

  //DATA
  public Package silverPack = new Package("Silver Pack", "S", new HashSet<>(Arrays.asList(channel1, channel2, channel3)), 50);
  public Package goldPack = new Package("Gold Pack", "G", new HashSet<>(Arrays.asList(channel1, channel2, channel3, channel4, channel5)), 100);
  public User user = new User(1, "akshay@gmail.com", "5468548545", 100);
  public SpecialService service1 = new SpecialService("LearnEnglish", 200);
  public SpecialService service2 = new SpecialService("LearnCooking", 100);
  private List<Channel> channels;
  private List<Package> packages;
  private List<SpecialService> specialServices;
  private List<User> users;
  public DataSource() {
    init();
  }

  private void init() {
    channels = new ArrayList<>(Arrays.asList(channel1, channel2, channel3, channel4, channel5));
    packages = new ArrayList<>(Arrays.asList(silverPack, goldPack));
    specialServices = new ArrayList<>(Arrays.asList(service1, service2));
    users = new ArrayList<>(Arrays.asList(user));
  }

  public List<Channel> getChannels() {
    return channels;
  }

  public void setChannels(List<Channel> channels) {
    this.channels = channels;
  }

  public List<Package> getPackages() {
    return packages;
  }

  public void setPackages(List<Package> packages) {
    this.packages = packages;
  }

  public List<SpecialService> getSpecialServices() {
    return specialServices;
  }

  public void setSpecialServices(List<SpecialService> specialServices) {
    this.specialServices = specialServices;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}
