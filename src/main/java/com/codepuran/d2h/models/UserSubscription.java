package com.codepuran.d2h.models;

import java.util.Set;

public class UserSubscription {

  long id;

  long userId;

  Set<Package> packages;

  Set<SpecialService> specialServices;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public Set<Package> getPackages() {
    return packages;
  }

  public void setPackages(Set<Package> packages) {
    this.packages = packages;
  }

  public Set<SpecialService> getSpecialServices() {
    return specialServices;
  }

  public void setSpecialServices(Set<SpecialService> specialServices) {
    this.specialServices = specialServices;
  }
}
