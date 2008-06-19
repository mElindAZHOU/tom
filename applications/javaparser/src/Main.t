import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ANTLRInputStream;

import java.io.*;

import parser.*;
import parser.ast.*;

public class Main {

   // %include { parser/ast/Ast.tom }

  public static void main(String args[]) {
    try {
      InputStream input;

      if(args.length!=0) {
        File inputFile = new File(args[0]);
        input=new FileInputStream(inputFile);
      } else {
        input=System.in;
      }

      // parse tree
      JavaLexer lexer = new JavaLexer(new ANTLRInputStream(input));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      JavaParser parser = new JavaParser(tokens);
      parser.setTreeAdaptor(new AstAdaptor());

      AstTree ast = (AstTree) parser.compilationUnit().getTree();
      System.out.println(ast);

    } catch (Exception e) {
      System.err.println("exception: " + e);
      e.printStackTrace();
    }
  }
}
