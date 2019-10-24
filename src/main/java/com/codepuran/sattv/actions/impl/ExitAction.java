package com.codepuran.sattv.actions.impl;

import com.codepuran.sattv.actions.Action;

public class ExitAction implements Action {
  @Override
  public void perform(long userId) {
    System.exit(0);
  }
}
