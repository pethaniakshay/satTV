package com.codepuran.sattv.actions.impl;

import com.codepuran.sattv.DataSource;
import com.codepuran.sattv.dao.PackageDao;
import com.codepuran.sattv.dao.UserDao;
import com.codepuran.sattv.dto.SubscribePackInputData;
import com.codepuran.sattv.dto.SubscriptionPackOutput;
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

public class SubscribePackageActionTest {

  private final Scanner scanner = new Scanner(System.in);
  private final UserDao userDao = mock(UserDao.class);
  private final PackageDao packageDao = mock(PackageDao.class);

  SubscribePackageAction action = new SubscribePackageAction(scanner,packageDao,userDao);

  DataSource dataSource;
  @BeforeEach
  public void setUp(){
    dataSource = new DataSource();
  }

  @Test
  public void processSubscription_Test(){
    SubscribePackInputData inputData = new SubscribePackInputData("S",2);
    User user = dataSource.user;
    when(packageDao.findBySlug(inputData.getPack())).thenReturn(Optional.of(dataSource.silverPack));
    SubscriptionPackOutput output = action.processSubscription(inputData,user);
    assertThat(output.getNoOfMonths(),equalTo(inputData.getMonth()));
    assertThat(output.getAccountBalance(),equalTo(0D));
    assertThat(output.getPackageName(),equalTo(dataSource.silverPack.getName()));
    assertThat(output.getDiscountApplied(),equalTo(0D));
  }

  @Test
  public void processSubscription_WithDiscount_Test(){
    SubscribePackInputData inputData = new SubscribePackInputData("S",4);
    User user = dataSource.user;
    user.setAccountBalance(600);
    when(packageDao.findBySlug(inputData.getPack())).thenReturn(Optional.of(dataSource.silverPack));
    SubscriptionPackOutput output = action.processSubscription(inputData,user);
    assertThat(output.getNoOfMonths(),equalTo(inputData.getMonth()));
    assertThat(output.getAccountBalance(),equalTo(420D));
    assertThat(output.getPackageName(),equalTo(dataSource.silverPack.getName()));
    assertThat(output.getDiscountApplied(),equalTo(20D));
  }

  @Test
  public void processSubscription_InSufficientBalance_Test(){
    SubscribePackInputData inputData = new SubscribePackInputData("S",4);
    User user = dataSource.user;
    when(packageDao.findBySlug(inputData.getPack())).thenReturn(Optional.of(dataSource.silverPack));
    Assertions.assertThrows(RuntimeException.class, () -> {
      action.processSubscription(inputData,user);
    });
  }

  @Test
  public void processSubscription_PackageNotAvailable_Test(){
    SubscribePackInputData inputData = new SubscribePackInputData("R",4);
    User user = dataSource.user;
    Assertions.assertThrows(RuntimeException.class, () -> {
      action.processSubscription(inputData,user);
    });
  }
}
