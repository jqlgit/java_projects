package assn07;


import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();
        System.out.println("Enter Master Password");
        String scan = scanner.nextLine();
        while (!passwordManager.checkMasterPassword(scan)) {
            System.out.println("Enter Master Password");
            scan = scanner.nextLine();
        }
        scan = scanner.nextLine();
        while (!scan.equals("Exit")) {
            if (scan.equals("New password")) {
                String scan1 = scanner.nextLine();
                String scan2 = scanner.nextLine();
                passwordManager.put(scan1, scan2);
                System.out.println("New password added");
            } else if (scan.equals("Get password")) {
                String scan1 = scanner.nextLine();
                if (passwordManager.get(scan1) == null) {
                    System.out.println("Account does not exist");
                } else {
                    System.out.println(passwordManager.get(scan1));
                }
            } else if (scan.equals("Delete account")) {
                String scan1 = scanner.nextLine();
                if (passwordManager.remove(scan1) == null) {
                    System.out.println("Account does not exist");
                }
                else {
                    System.out.println("Account deleted");
                }
            } else if (scan.equals("Check duplicate password")) {
                String scan1 = scanner.nextLine();
                List<String> array = passwordManager.checkDuplicate(scan1);
                if (array.size() != 0) {
                    System.out.println("Websites using that password:");
                    for (int i = 0; i < array.size(); i++) {
                        System.out.println(array.get(i));
                    }
                }
                else {
                    System.out.println("No account uses that password");
                }
            } else if (scan.equals("Get accounts")) {
                System.out.println("Your accounts:");
                for (String accountName : passwordManager.keySet()) {
                    System.out.println(accountName);
                }
            } else if (scan.equals("Generate random password")) {
                String len = scanner.nextLine();
                int lengthInt = Integer.parseInt(len);
                String answer = passwordManager.generateRandomPassword(lengthInt);
                System.out.println(answer);
            }else{
                System.out.println("Command not found");
            }
            scan = scanner.nextLine();
            }
        }
    }