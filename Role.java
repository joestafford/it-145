package zoo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

class Role
{
  FileOutputStream roleStatement = null;
  
  public void printRole(String role) {
    if (role.equals("admin")) {
      roleStatement = new FileOutputStream("admin.txt");
      System.out.print(roleStatement);
    } else if (role.equals("zookeeper")) {
      roleStatement = new FileOutputStream("zookeeper.txt");
      System.out.print(roleStatement);
    } else if (role.equals("veterinarian")) {
      roleStatement = new FileOutputStream("veterinarian.txt");
      System.out.print(roleStatement);
    }
  }
}