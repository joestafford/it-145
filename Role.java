import java.io.FileNotFoundException;
import java.io.FileOutputStream;

class Role
{
  FileOutputStream roleStatement = null;
  
  public void printRole(String role) throws FileNotFoundException {
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
