import java.io.*;
import java.math.BigInteger;

public class Addition { // as the class name that contains the main method is "Addition", you have to save this file as "Addition.java", and submit "Addition.java" to Codecrunch
  public static void main(String[] args) {
    IntegerScanner sc = new IntegerScanner(System.in);
    while (true) {
               
        BigInteger a = sc.nextInt();
        BigInteger b = sc.nextInt();
        if( a.equals( new BigInteger("-1")) && b.equals( new BigInteger("-1")))
            break;
        BigInteger c = a.add(b);
        String out = "" + c;
        System.out.println(out);      
    }
  }
}

class IntegerScanner { // coded by Ian Leow, we will use this quite often in CS2010 PSes
  BufferedInputStream bis;
  IntegerScanner(InputStream is) {
    bis = new BufferedInputStream(is,1000000);
  }

  public BigInteger nextInt() {
    BigInteger result = new BigInteger("0");
    try {
      int cur = bis.read();
      if (cur == -1)
        return  new BigInteger("-1");

      while ((cur < 48 || cur > 57) && cur != 45) {
        cur = bis.read();
      }

      boolean negate = false;
      if (cur == 45) {
        negate = true;
        cur = bis.read();
      }

      while (cur >= 48 && cur <= 57) {
        result = result.multiply(new BigInteger("10")).add(new BigInteger("" + (cur-48)));
        cur = bis.read();
      }

      if (negate) {
        return new BigInteger("" + result).negate();
      }
      return new BigInteger("" + result);
    }
    catch (IOException ioe) {
      return new BigInteger("-1");
    }
  }
}