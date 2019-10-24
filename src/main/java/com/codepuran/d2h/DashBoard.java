package com.codepuran.d2h;

import com.codepuran.d2h.actions.Action;
import com.codepuran.d2h.dto.ActionAndActionLabel;

import java.util.List;
import java.util.Scanner;

public class DashBoard {

  private final List<ActionAndActionLabel> actionAndActionLabels;
  private final Scanner scanner;

  public DashBoard(Scanner scanner, List<ActionAndActionLabel> actionAndActionLabels) {
    this.scanner = scanner;
    this.actionAndActionLabels = actionAndActionLabels;
  }

  public void launch(long userId) {
    while (true) {
      System.out.println("-------------------------------------------------------");
      int i = 0;
      for (ActionAndActionLabel actionAndActionLabel : actionAndActionLabels) {
        i++;
        System.out.println(i + ". " + actionAndActionLabel.getLabel());
      }
      System.out.println("=======================================================");
      System.out.println("Select Action");
      int actionNumber = scanner.nextInt();
      if (actionNumber > -1 && actionNumber < actionAndActionLabels.size() + 1) {
        Action selectedAction = actionAndActionLabels.get(actionNumber - 1).getAction();
        selectedAction.perform(userId);
      } else {
        System.out.println("Please select valid option");
      }
      System.out.println("-------------------------------------------------------");
    }
  }
}
