package com.codepuran.d2h.actions.impl;

import com.codepuran.d2h.actions.Action;
import com.codepuran.d2h.dao.UserDao;
import com.codepuran.d2h.dto.EmailPhone;
import com.codepuran.d2h.models.User;

import java.util.Optional;
import java.util.Scanner;

public class UpdateContactInfoAction implements Action {

  private final Scanner scanner;

  private final UserDao userDao;

  public UpdateContactInfoAction(Scanner scanner, UserDao userDao) {
    this.scanner = scanner;
    this.userDao = userDao;
  }

  @Override
  public void perform(long userId) {
    EmailPhone input = input();
    processAction(userId, input);
    showResult();
  }

  private EmailPhone input() {
    EmailPhone input = new EmailPhone();
    System.out.println("Enter the email: ");
    scanner.nextLine();
    input.setEmail(scanner.nextLine());
    System.out.println("Enter phone: ");
    scanner.nextLine();
    input.setPhone(scanner.nextLine());
    return input;
  }

  private void processAction(long userId, EmailPhone input) {
    Optional<User> userOptional = userDao.findById(userId);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      user.setEmail(input.getEmail());
      user.setPhone(input.getPhone());
    } else {
      throw new RuntimeException("User Does not exist");
    }
  }

  private void showResult() {
    System.out.println("Email and Phone updated successfully");
  }
}
