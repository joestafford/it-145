package zoo;

import java.util.ArrayList;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Authentication
{
  static ArrayList<String> username = new ArrayList<>();
  static ArrayList<String> hash = new ArrayList<>();
  static ArrayList<String> role = new ArrayList<>();
  
  public static String sessionRole = "";
  
  public static void credentialReader() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"));
    String line = null;

    while ((line = reader.readLine()) != null) {
      if (line.length() > 0) {
        String[] array = line.split("\\t");
        username.add(array[0]);
        hash.add(array[1]);
        role.add(array[3]);
      }
    }
  }
    
  public void authenticator() throws IOException, NoSuchAlgorithmException {
    Scanner scnr = new Scanner(System.in);
    credentialReader();
    String inputUsername = "";
    String inputPassword = "";
    String encryptPassword = "";
    int attempt = 0;
    boolean authenticating = true;
    
    System.out.println("Zoo User Console");
    System.out.println("----------------");
    System.out.println("Please login below.  You have 3 attempts to login successfully.  To exit from login prompt please leave username and password empty.");
    System.out.println("----------------");
    
    while (authenticating) {   //allow 3 login attempts
      System.out.println("Attempt: " + ++attempt);
      System.out.print("username: ");  // username prompt
      inputUsername = scnr.next();
      System.out.print("password: ");  // password prompt
      inputPassword = scnr.next();
      
      if (inputUsername==null && inputPassword==null) {
        System.out.println("Console exiting...");
        System.exit(0);
      }
      
      MessageDigest md = MessageDigest.getInstance("MD5");  // encrypt password string
      md.update(inputPassword.getBytes());
      byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
      for (byte b : digest) {
        sb.append(String.format("%02x", b & 0xff));
      }
      encryptPassword = sb.toString();
      System.out.println(Authentication.username.toString());
      
      if (Authentication.hash.contains(encryptPassword) && Authentication.username.contains(inputUsername)) {
    	  System.out.println("You are logged in");
          sessionRole = Authentication.role.toString();
          authenticating = false;
      }
      
      if (attempt > 2 && authenticating) { // Login failed
          System.out.println("Access Denied. Console exiting...");
          System.exit(0);
        }
      
    } 
    
  }
}
