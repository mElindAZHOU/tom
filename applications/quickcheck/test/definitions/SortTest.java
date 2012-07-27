/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package definitions;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import sort.types.Expr;
import sort.types.Surexpr;
import sort.types.expr.mult;
import sort.types.expr.plus;
import sort.types.expr.un;
import sort.types.expr.zero;
import sort.types.surexpr.rec;
import sort.types.surexpr.wrapper;

/**
 *
 * @author hubert
 */
public class SortTest {

  private Scope scope;
  private Sort nat;
  private Sort tree;
  private Sort tree2;
  private Sort forest;
  private Sort circ;
  private Sort typetest;
  private Sort a, b, c;
  private SortGom expr;
  private SortGom surexpr;
  private Sort list;
  private Sort list2;
  private Sort mix;
  private StrategyParameters paramDepth, paramNodes;

  public SortTest() {

    paramDepth = new StrategyParameters(
            StrategyParameters.DistStrategy.DEPTH,
            StrategyParameters.TerminationCriterion.POINT_OF_NO_RETURN);
    
    paramNodes = new StrategyParameters(
            StrategyParameters.DistStrategy.NODES,
            StrategyParameters.TerminationCriterion.POINT_OF_NO_RETURN);
    
    scope = new Scope();

    nat = new Sort(scope, "nat");
    nat.addConstructor("succ", nat);
    nat.addConstructor("zero");

    tree = new Sort(scope, "tree");
    tree.addConstructor("branch", tree, tree);
    tree.addConstructor("leaf");

    typetest = new Sort(scope, "typetest");
    typetest.addConstructor("tree", tree);
    typetest.addConstructor("nat", nat);


    forest = new Sort(scope, "forest");
    tree2 = new Sort(scope, "tree2");
    forest.addConstructor("tree", tree2);
    tree2.addConstructor("nat", nat);
    tree2.addConstructor("forest", forest);

    circ = new Sort(scope, "circ");
    circ.addConstructor("circ", circ);

    a = new Sort(scope, "a");
    b = new Sort(scope, "b");
    c = new Sort(scope, "c");
    a.addConstructor("nill");
    a.addConstructor("b", b);
    b.addConstructor("c", c);
    c.addConstructor("a", a);

    expr = new SortGom(scope, Expr.class);
    expr.addConstructor(mult.class);
    expr.addConstructor(plus.class);
    expr.addConstructor(un.class);
    expr.addConstructor(zero.class);

    surexpr = new SortGom(scope, Surexpr.class);
    surexpr.addConstructor(rec.class);
    surexpr.addConstructor(wrapper.class);

    list = new Sort(scope, "list");
    list.addConstructor(expr);
    list.addConstructor("consList", expr, list);

    list2 = new Sort(scope, "list2");
    list2.addConstructor("consList", expr, list2);

    mix = new Sort(scope, "mix");
    mix.addConstructor("consMix", list, surexpr);

    scope.setDependances();
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
    System.out.println("--------------------");
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of isRec method, of class Sort.
   */
  @Test
  public void testIsRecA() {
    System.out.println("isRec A");
    boolean expResult = true;
    boolean result = a.isRec();
    assertEquals(expResult, result);
  }

  @Test
  public void testIsRecB() {
    System.out.println("isRec B");
    boolean expResult = true;
    boolean result = b.isRec();
    assertEquals(expResult, result);
  }

  @Test
  public void testIsRecC() {
    System.out.println("isRec C");
    boolean expResult = true;
    boolean result = c.isRec();
    assertEquals(expResult, result);
  }

  @Test
  public void testIsRecCirc() {
    System.out.println("isRec Circ");
    boolean expResult = true;
    boolean result = circ.isRec();
    assertEquals(expResult, result);
  }

  @Test
  public void testIsRecForest() {
    System.out.println("isRec Forest");
    boolean expResult = true;
    boolean result = forest.isRec();
    assertEquals(expResult, result);
  }

  @Test
  public void testIsRecNat() {
    System.out.println("isRec Nat");
    boolean expResult = true;
    boolean result = nat.isRec();
    assertEquals(expResult, result);
  }

  @Test
  public void testIsRecTree() {
    System.out.println("isRec Tree");
    boolean expResult = true;
    boolean result = tree.isRec();
    assertEquals(expResult, result);
  }

  @Test
  public void testIsRecTree2() {
    System.out.println("isRec Tree2");
    boolean expResult = true;
    boolean result = tree2.isRec();
    assertEquals(expResult, result);
  }

  @Test
  public void testIsRecTypeTest() {
    System.out.println("isRec TypeTest");
    boolean expResult = true;
    boolean result = expr.isRec();
    assertEquals(expResult, result);
  }

  @Test
  public void testIsRecExpr() {
    System.out.println("isRec Expr");
    boolean expResult = true;
    boolean result = surexpr.isRec();
    assertEquals(expResult, result);
  }

  @Test
  public void testIsRecSurExpr() {
    System.out.println("isRec SurExpr");
    boolean expResult = false;
    boolean result = typetest.isRec();
    assertEquals(expResult, result);
  }

  @Test
  public void testIsRecSurList() {
    System.out.println("isRec List");
    boolean expResult = true;
    boolean result = list.isRec();
    assertEquals(expResult, result);
  }

  @Test
  public void testIsRecSurList2() {
    Sort test = list2;
    System.out.println("isRec " + test.getName());
    boolean expResult = true;
    boolean result = test.isRec();
    assertEquals(expResult, result);
  }

  @Test
  public void testIsRecSurMix() {
    Sort test = mix;
    System.out.println("isRec " + test.getName());
    boolean expResult = false;
    boolean result = test.isRec();
    assertEquals(expResult, result);
  }

  /**
   * Test of getDimension method, of class Sort.
   */
  @Test
  public void testGetDimentionNat() {
    System.out.println("getDimention Nat");
    int expResult = 1;
    int result = nat.getDimension();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetDimentionTree() {
    System.out.println("getDimention tree");
    int expResult = 1;
    int result = tree.getDimension();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetDimentionTypeTest() {
    System.out.println("getDimention TypeTest");
    int expResult = 1;
    int result = typetest.getDimension();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetDimentionTree2() {
    System.out.println("getDimention Tree2");
    int expResult = 2;
    int result = tree2.getDimension();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetDimentionForest() {
    System.out.println("getDimention Forest");
    int expResult = 2;
    int result = forest.getDimension();
    assertEquals(expResult, result);
    assertEquals(2, tree2.getDimension());
  }

  @Test
  public void testGetDimentionCirc() {
    System.out.println("getDimention Circ");
    int expResult = 1;
    int result = circ.getDimension();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetDimentionA() {
    System.out.println("getDimention A");
    int expResult = 1;
    int result = a.getDimension();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetDimentionB() {
    System.out.println("getDimention B");
    int expResult = 1;
    int result = b.getDimension();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetDimentionC() {
    System.out.println("getDimention C");
    int expResult = 1;
    int result = c.getDimension();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetDimentionExpr() {
    System.out.println("getDimention Expr");
    int expResult = 1;
    int result = expr.getDimension();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetDimentionSurExpr() {
    System.out.println("getDimention SurExpr");
    int expResult = 2;
    int result = surexpr.getDimension();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetDimentionlist() {
    System.out.println("getDimention list");
    int expResult = 2;
    int result = list.getDimension();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetDimentionlist2() {
    Sort test = list2;
    System.out.println("getDimention " + test.getName());
    int expResult = 2;
    int result = test.getDimension();
    assertEquals(expResult, result);
  }

  @Test
  public void testGetDimentionmix() {
    Sort test = mix;
    System.out.println("getDimention " + test.getName());
    int expResult = 2;
    int result = test.getDimension();
    assertEquals(expResult, result);
  }

  /**
   * Test of depthToLeaf method, of class Sort.
   */
  @Test
  public void testDstToLeafNat() {
    System.out.println("dstToLeaf Nat");
    int expResult = 0;
    int result = nat.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testDstToLeafTree() {
    System.out.println("dstToLeaf Tree");
    int expResult = 0;
    int result = tree.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testDstToLeafTypeTest() {
    System.out.println("dstToLeaf TypeTest");
    int expResult = 1;
    int result = typetest.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testDstToLeafForest() {
    System.out.println("dstToLeaf Forest");
    int expResult = 2;
    int result = forest.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testDstToLeafTree2() {
    System.out.println("dstToLeaf Tree2");
    int expResult = 1;
    int result = tree2.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testDstToLeafCirc() {
    System.out.println("dstToLeaf Circ");
    int expResult = Integer.MAX_VALUE;
    int result = circ.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testDstToLeafB() {
    System.out.println("dstToLeaf B");
    int expResult = 2;
    int result = b.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testDstToLeafA() {
    System.out.println("dstToLeaf A");
    int expResult = 0;
    int result = a.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testDstToLeafC() {
    System.out.println("dstToLeaf C");
    int expResult = 1;
    int result = c.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testDstToLeafExpre() {
    System.out.println("dstToLeaf Expr");
    int expResult = 0;
    int result = expr.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testDstToLeafSurExpre() {
    System.out.println("dstToLeaf SurExpr");
    int expResult = 1;
    int result = surexpr.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testDstToLeaflist() {
    System.out.println("dstToLeaf list");
    int expResult = 1;
    int result = list.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testDstToLeaflist2() {
    Sort test = list2;
    System.out.println("dstToLeaf " + test.getName());
    int expResult = Integer.MAX_VALUE;
    int result = test.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testDstToLeafmix() {
    Sort test = mix;
    System.out.println("dstToLeaf " + test.getName());
    int expResult = 2;
    int result = test.minimalSize(paramDepth.getDistStrategy());
    assertEquals(expResult, result);
  }

  /**
   * Test of stepsToLeaf method, of class Sort.
   */
  @Test
  public void testStepsToLeafNat() {
    System.out.println("stepsToLeaf " + nat.getName());
    int expResult = 0;
    int result = nat.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testStepsToLeafA() {
    System.out.println("stepsToLeaf " + a.getName());
    int expResult = 0;
    int result = a.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testStepsToLeafB() {
    System.out.println("stepsToLeaf " + b.getName());
    int expResult = 2;
    int result = b.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testStepsToLeafC() {
    System.out.println("stepsToLeaf " + c.getName());
    int expResult = 1;
    int result = c.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testStepsToLeafForest() {
    System.out.println("stepsToLeaf " + forest.getName());
    int expResult = 2;
    int result = forest.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testStepsToLeaftree() {
    System.out.println("stepsToLeaf " + tree.getName());
    int expResult = 0;
    int result = tree.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testStepsToLeaftypetest() {
    System.out.println("stepsToLeaf " + typetest.getName());
    int expResult = 1;
    int result = typetest.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testStepsToLeaftree2() {
    System.out.println("stepsToLeaf " + tree2.getName());
    int expResult = 1;
    int result = tree2.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testStepsToLeafcirc() {
    System.out.println("stepsToLeaf " + circ.getName());
    int expResult = Integer.MAX_VALUE;
    int result = circ.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testStepsToLeafexpr() {
    System.out.println("stepsToLeaf " + expr.getName());
    int expResult = 0;
    int result = expr.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testStepsToLeafsurexpr() {
    System.out.println("stepsToLeaf " + surexpr.getName());
    int expResult = 1;
    int result = surexpr.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testStepsToLeaflist() {
    System.out.println("stepsToLeaf " + list.getName());
    int expResult = 1;
    int result = list.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testStepsToLeaflist2() {
    Sort test = list2;
    System.out.println("stepsToLeaf " + test.getName());
    int expResult = Integer.MAX_VALUE;
    int result = test.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  @Test
  public void testStepsToLeafmix() {
    Sort test = mix;
    System.out.println("stepsToLeaf " + test.getName());
    int expResult = 3;
    int result = test.minimalSize(paramNodes.getDistStrategy());
    assertEquals(expResult, result);
  }

  /**
   * Test of testAddConstructor with class method, of class Sort.
   */
  @Test
  public void testAddConstructorClassExpr() {
    System.out.println("testAddConstructorClass SurExpr");
    System.out.println(expr);
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }

  @Test
  public void testAddConstructorClassSurExpr() {
    System.out.println("testAddConstructorClass SurExpr");
    System.out.println(surexpr);
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }

  /**
   * Test of generate method, of class Sort.
   */
  @Test
  public void testGenerateExpr() {
    System.out.println("Generate Expr");
    Slot slot = expr.generateSlot(100, paramDepth);
    System.out.println(slot);
    slot.toDot2("Expr.dot");
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }

  @Test
  public void testGenerateSurExpr() {
    System.out.println("Generate SurExpr");
    Slot slot = surexpr.generateSlot(100, paramDepth);
    System.out.println(slot);
    slot.toDot2("SurExpr.dot");
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }

  @Test
  public void testGenerateA() {
    System.out.println("Generate A");
    Slot slot = a.generateSlot(100, paramDepth);
    System.out.println(slot);
    slot.toDot2("A.dot");
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }

  @Test
  public void testGenerateB() {
    System.out.println("Generate B");
    Slot slot = b.generateSlot(100, paramDepth);
    System.out.println(slot);
    slot.toDot2("B.dot");
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }

  @Test
  public void testGenerateC() {
    System.out.println("Generate C");
    Slot slot = c.generateSlot(100, paramDepth);
    System.out.println(slot);
    slot.toDot2("C.dot");
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }

  @Test
  public void testGenerateCirc() {
    System.out.println("Generate Circ");
    Slot slot;
    String res = "not this";
    try {
      slot = circ.generateSlot(100, paramDepth);
      System.out.println(slot);
      slot.toDot2("Circ.dot");
    } catch (UnsupportedOperationException e) {
      res = e.getMessage();
    }

    String expResult = "Type circ does not terminate.";
    assertEquals(expResult, res);
  }

  @Test
  public void testGenerateforest() {
    System.out.println("Generate forest");
    Slot slot = forest.generateSlot(100, paramDepth);
    System.out.println(slot);
    slot.toDot2("forest.dot");
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }

  @Test
  public void testGeneratenat() {
    System.out.println("Generate nat");
    Slot slot = nat.generateSlot(100, paramDepth);
    System.out.println(slot);
    slot.toDot2("nat.dot");
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }

  @Test
  public void testGenerateTree() {
    System.out.println("Generate tree");
    Slot slot = tree.generateSlot(100, paramDepth);
    System.out.println(slot);
    slot.toDot2("tree.dot");
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }

  @Test
  public void testGenerateTree2() {
    System.out.println("Generate tree2");
    Slot slot = tree2.generateSlot(100, paramDepth);
    System.out.println(slot);
    slot.toDot2("tree2.dot");
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }

  @Test
  public void testGeneratetypetest() {
    System.out.println("Generate typetest");
    Slot slot = typetest.generateSlot(100, paramDepth);
    System.out.println(slot);
    slot.toDot2("typetest.dot");
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }

  @Test
  public void testGeneratelist() {
    System.out.println("Generate list");
    Slot slot = list.generateSlot(100, paramDepth);
    System.out.println(slot);
    slot.toDot2("list.dot");
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }

  @Test
  public void testGeneratelist2() {
    Sort test = list2;
    System.out.println("Generate " + test.getName());
    Slot slot = null;
    String res = "not this";
    try {
      slot = test.generateSlot(100, paramDepth);
      System.out.println(slot);
      slot.toDot2("list2.dot");
    } catch (UnsupportedOperationException e) {
      System.out.println("fail");
      res = e.getMessage();
    }
    String expResult = "Type list2 does not terminate.";
    assertEquals(expResult, res);
  }

  @Test
  public void testGeneratemix() {
    Sort test = mix;
    System.out.println("Generate " + test.getName());
    Slot slot = mix.generateSlot(100, paramDepth);
    System.out.println(slot);
    slot.toDot2("mix.dot");
    int expResult = 1;
    int result = 1;
    assertEquals(expResult, result);
  }
}