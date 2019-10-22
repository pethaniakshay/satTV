package com.codepuran.d2h.models;

import java.util.Set;

public class Package {

  String name;

  Set<Channel> channels;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Channel> getChannels() {
    return channels;
  }

  public void setChannels(Set<Channel> channels) {
    this.channels = channels;
  }
}
