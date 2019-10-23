package com.codepuran.d2h;

import com.codepuran.d2h.models.Channel;
import com.codepuran.d2h.models.SpecialService;
import com.codepuran.d2h.models.User;
import com.codepuran.d2h.models.UserSubscription;

import java.util.List;

public class DataSource {

  public List<Channel> channels;

  public List<Package> packages;

  public List<SpecialService> specialServices;

  public List<User> users;

  public List<UserSubscription> userSubscriptions;


  public DataSource(){

  }

  private void init(){

  }

}
