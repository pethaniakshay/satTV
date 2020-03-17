package com.codepuran.sattv.models;

import java.util.Set;

public class Package {

  private String name;

  private String slug;

  private Set<Channel> channels;

  private double price;

  public Package(String name, String slug, Set<Channel> channels, double price) {
    this.name = name;
    this.slug = slug;
    this.channels = channels;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public Set<Channel> getChannels() {
    return channels;
  }

  public void setChannels(Set<Channel> channels) {
    this.channels = channels;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
