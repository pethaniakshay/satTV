package com.codepuran.sattv.models;

import java.util.Set;

public class Subscription {

  private Package pack;

  private Set<Channel> channels;

  private Set<SpecialService> specialServices;

  public Package getPack() {
    return pack;
  }

  public void setPack(Package pack) {
    this.pack = pack;
  }

  public Set<Channel> getChannels() {
    return channels;
  }

  public void setChannels(Set<Channel> channels) {
    this.channels = channels;
  }

  public Set<SpecialService> getSpecialServices() {
    return specialServices;
  }

  public void setSpecialServices(Set<SpecialService> specialServices) {
    this.specialServices = specialServices;
  }
}
