import java.util.ArrayList;
import java.util.Scanner;
import java.security.MessageDigest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

class Authentication
{
  static ArrayList<String> username = new ArrayList<>();
  static ArrayList<String> hash = new ArrayList<>();
  static ArrayList<String> role = new ArrayList<>();
  
  public static void credentialReader() {

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
    
  public static void authenticator(String[] args) {
    Scanner scnr = new Scanner(System.in);
    credentialReader();
    String inputUsername = "";
    String inputPassword = "";
    String encryptPassword = "";
    String sessionRole = "";
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
      
      if (encryptPassword.equals(Authentication.hash) && inputUsername.equals(Authentication.username)) { // login successful.  set role.
        System.out.println("You are logged in");
        sessionRole = Authentication.role;
        authenticating = false;
      }
    }
    
    if (attempt > 2 && authenticating) { // Login failed
      System.out.println("Access Denied.  Console exiting...");
      System.exit(0);
    }
  }
}