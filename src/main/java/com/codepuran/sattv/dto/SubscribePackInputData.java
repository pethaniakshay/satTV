package com.codepuran.sattv.dto;

public class SubscribePackInputData {

  private String pack;

  private int month;

  private SubscribePackInputData() {
  }

  public SubscribePackInputData(String pack, int month) {
    this.pack = pack;
    this.month = month;
  }

  public String getPack() {
    return pack;
  }

  public int getMonth() {
    return month;
  }
}