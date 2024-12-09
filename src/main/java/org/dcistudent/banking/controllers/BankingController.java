package org.dcistudent.banking.controllers;

import org.dcistudent.banking.interfaces.models.CustomerInterface;
import org.dcistudent.banking.renderers.ScannerRenderer;
import org.dcistudent.banking.services.BankingService;

import java.util.Scanner;

public final class BankingController {
    private final Scanner scanner;
    private Boolean loggedIn = false;
    private final BankingService bankingService;

    public static void main(String[] args) {
        new BankingController();
    }

    public BankingController() {
        this.scanner = new Scanner(System.in).useDelimiter("\n");
        this.bankingService = new BankingService(this.scanner);

        if (this.loggedIn == false) {
            this.sessionMenu();
            return;
        }

        this.customerMenu();
    }

    private void sessionMenu() {
        System.out.println("1. Signup");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        try {
            this.processSessionMenu();
        } catch (Exception e) {
            ScannerRenderer.renderSeparated(e.getMessage());
            this.processSessionMenu();
        }
    }

    private void processSessionMenu() {
        Integer option;
        CustomerInterface customer;

        ScannerRenderer.renderInputChoice();
        option = this.scanner.nextInt();

        switch (option) {
            case 1:
                try {
                    customer = this.bankingService.signup();
                    ScannerRenderer.renderSeparated("Welcome, " + customer + "! Please login now.");
                    this.sessionMenu();
                } catch (Exception e) {
                    ScannerRenderer.renderSeparated(e.getMessage());
                    this.sessionMenu();
                }
                break;
            case 2:
                try {
                    customer = this.bankingService.login();
                    ScannerRenderer.renderSeparated("Welcome back, " + customer + "!");
                    this.loggedIn = true;
                    this.customerMenu();
                } catch (Exception e) {
                    ScannerRenderer.renderSeparated(e.getMessage());
                    this.sessionMenu();
                }
                break;
            case 3:
                ScannerRenderer.renderSeparated("Bye, bye.");
                return;
            default:
                ScannerRenderer.renderSeparated("Invalid option. Please try again.");
        }

        this.sessionMenu();
    }

    private void customerMenu() {
        System.out.println("1. Show Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Reset Password");
        System.out.println("5. Logout");

        try {
            this.processCustomerMenu();
        } catch (Exception e) {
            ScannerRenderer.renderSeparated(e.getMessage());
            this.processCustomerMenu();
        }
    }

    private void processCustomerMenu() {
        Integer option;

        ScannerRenderer.renderInputChoice();
        option = this.scanner.nextInt();

        switch (option) {
            case 1:
                ScannerRenderer.renderSeparated("Your balance is: " + this.bankingService.checkBalance());
                break;
            case 2:
                this.bankingService.deposit();
                break;
//            case 3:
//                this.withdraw();
//                break;
//            case 4:
//                this.transfer();
//                break;
//            case 5:
//                this.logout();
//                break;
            default:
                ScannerRenderer.renderSeparated("Invalid option. Please Try again.");
        }

        this.customerMenu();
    }
}
