import java.io.*;
import java.util.*;

class TermColumn {
  public static void main(String[] args) {
      if(args.length!=0) paintTermColumns(getTermColumns(),args[0]); // can be executed in mode random and mode normal
      else paintTermColumns(getTermColumns(),"normal");
  }

  public static int getTermColumns(){ // return number of columns
    String[] cmd = {"/bin/bash", "-c", "tput cols 2< /dev/tty"};
    //String[] cmd = {"/bin/bash", "-c", "stty size < /dev/tty | cut -d ' ' -f 2"};
    int cols = 0;
    try {
        InputStream stdInStream = Runtime.getRuntime().exec(cmd).getInputStream();
        BufferedReader stdOutReader =  new BufferedReader(new InputStreamReader(stdInStream));
        cols = Integer.parseInt(stdOutReader.readLine());
    } catch (IOException ex) {
    }
    return cols;
  }

  public static void paintTermColumns(int columns, String mode){ // paint each column
    Random rgen = new Random();
    for(int i = 0; i < columns; i++){
      if (mode.equals("random")) System.out.print("\033["+(40+rgen.nextInt(8))+"m");
      else System.out.print("\033[47m");
      System.out.print(" ");
      try{
        Thread.sleep(5); // painting speed
      }
      catch (InterruptedException ex) {
      }

    }
    System.out.print("\n");
    System.out.print("\033[49m");
  }
}
