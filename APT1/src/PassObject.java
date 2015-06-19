class Letter {
  char c;
}

public class PassObject {
  static void f(Letter y, int z) {
    y.c = 'z';
    z = 3;
  }
  public static void main(String[] args) {
    Letter x = new Letter();
    x.c = 'a';
    int d = 1;
    f(x, d);
    System.out.println(x.c);
    System.out.println(d);
    int [] list = new int(100);
    
  }
}