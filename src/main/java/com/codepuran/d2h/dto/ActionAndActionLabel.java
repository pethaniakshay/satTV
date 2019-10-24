package com.codepuran.d2h.dto;

import com.codepuran.d2h.actions.Action;

public class ActionAndActionLabel {

  private String label;
  private Action action;

  public ActionAndActionLabel(String label, Action action) {
    this.label = label;
    this.action = action;
  }

  public String getLabel() {
    return label;
  }

  public Action getAction() {
    return action;
  }
}
