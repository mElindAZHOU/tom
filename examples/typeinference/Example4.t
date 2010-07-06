import example4.example.types.*;
public class Example4 {
  %gom {
    module Example
      abstract syntax
      B = b()
        | f(B*)
  }

  public static void main(String[] args) {
    B tt = `f(f(b()));
    %match{
      b() << tt -> { System.out.println(`tt); }
      f(x) << tt -> { System.out.println(`x); }
    }
  }
}