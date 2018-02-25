package zoo;

import java.util.Scanner;
import java.security.MessageDigest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class Main {
 
  public static void main(String[] args) {
    Authentication userSession = new Authentication();
    Role userRole = new Role();
    
    userSession();
    userRole.printRole(userSession.sessionRole);
    
  }
  
}