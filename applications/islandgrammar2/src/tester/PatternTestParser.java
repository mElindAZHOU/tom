package tester;
import newparser.miniTomLexer;
import newparser.miniTomParser;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;


public class PatternTestParser extends TestParser{

  public PatternTestParser() {;}
  
  @Override
  public void parse(String stringInput) {
    
 
    CharStream input = new ANTLRStringStream(stringInput);
    miniTomLexer lexer = new miniTomLexer(input);
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
    miniTomParser parser = new miniTomParser(tokenStream);

    Tree tree;
    try {
      tree = (Tree) parser.csPattern().getTree();
    } catch (RecognitionException e) {
      this.setSuccess(false);
      return;
    }
    
    this.setSuccess(true);
    this.setTree(tree);
  }

}
