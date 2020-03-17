package com.codepuran.sattv.actions.impl;


import com.codepuran.sattv.DataSource;
import com.codepuran.sattv.dao.ChannelDao;
import com.codepuran.sattv.dao.UserDao;
import com.codepuran.sattv.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddChannelsExistingSubscriptionActionTest {


  private final Scanner scanner = new Scanner(System.in);
  private final UserDao userDao = mock(UserDao.class);
  private final ChannelDao channelDao = mock(ChannelDao.class);

  AddChannelsExistingSubscriptionAction action = new AddChannelsExistingSubscriptionAction(scanner,userDao,channelDao);

  DataSource dataSource;
  @BeforeEach
  public void setUp(){
    dataSource = new DataSource();
  }

  @Test
  public void processSubscription_Test(){
    String input = "Nat Geo,Discovery";
    User user = dataSource.user;
    when(channelDao.findByName("Nat Geo")).thenReturn(Optional.of(dataSource.channel5));
    when(channelDao.findByName("Discovery")).thenReturn(Optional.of(dataSource.channel4));
    double result = action.processSubscription(input,user);
    assertThat(result,equalTo(70D));
  }

  @Test
  public void processSubscription_InSufficientBalance_Test(){
    String input = "Nat Geo,Discovery";
    User user = dataSource.user;
    user.setAccountBalance(5);
    when(channelDao.findByName("Nat Geo")).thenReturn(Optional.of(dataSource.channel5));
    when(channelDao.findByName("Discovery")).thenReturn(Optional.of(dataSource.channel4));

    Assertions.assertThrows(RuntimeException.class, () -> {
      action.processSubscription(input,user);
    });
  }

  @Test
  public void processSubscriptionTest_InvalidChannelSelection(){
    String input = "Pogo";
    User user = dataSource.user;
    Assertions.assertThrows(RuntimeException.class, () -> {
      action.processSubscription(input,user);
    });
  }
}
