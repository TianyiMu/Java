public class Rec2Example {
private int myA;
private int myB;
 public Rec2Example(int a, int b) {
myA = a;
myB = b;
}
public void setA(int a) {
myA = a;
}
public static void main(String[] args) {
Rec2Example ex = new Rec2Example(33, 7);
Rec2Example ex2 = ex;
ex.setA(44);
System.out.println(ex2.myA);
}
}