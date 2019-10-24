package com.codepuran.d2h.actions.impl;

import com.codepuran.d2h.actions.Action;

public class ExitAction implements Action {
  @Override
  public void perform(long userId) {
    System.exit(0);
  }
}
