/* Generated By:JavaCC: Do not edit this line. Parser.java */
package com.sas.sling.resource.parser;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.sas.sling.resource.parser.node.*;
import com.sas.sling.resource.parser.predicates.*;

public final class Parser implements ParserConstants {
  private NodesFactory factory = new NodesFactory();

  final public Node Input() throws ParseException {
  final Node node;
    node = Or();
    jj_consume_token(0);
    {if (true) return node;}
    throw new Error("Missing return statement in function");
  }

  final public Node Or() throws ParseException {
  final List < Node > nodes = new ArrayList < Node > (3);
  Node node;
    node = And();
    nodes.add(node);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(OR);
      node = And();
      nodes.add(node);
    }
    {if (true) return nodes.size() != 1 ? factory.createOrNode(nodes) : nodes.get(0);}
    throw new Error("Missing return statement in function");
  }

  final public Node And() throws ParseException {
  final List < Node > nodes = new ArrayList < Node > (3);
  Node node;
    node = Constraint();
    nodes.add(node);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(AND);
      node = Constraint();
      nodes.add(node);
    }
    {if (true) return nodes.size() != 1 ? factory.createAndNode(nodes) : nodes.get(0);}
    throw new Error("Missing return statement in function");
  }

  final public Node Constraint() throws ParseException {
  final Node node;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
      node = Group();
      break;
    case UNRESERVED_STR:
    case SINGLE_QUOTED_STR:
    case DOUBLE_QUOTED_STR:
    case NULL:
    case TRUE:
    case FALSE:
    case LBRACKET:
    case NUMBER:
      node = Comparison();
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return node;}
    throw new Error("Missing return statement in function");
  }

  final public Node Group() throws ParseException {
  final Node node;
    jj_consume_token(LPAREN);
    node = Or();
    jj_consume_token(RPAREN);
    {if (true) return node;}
    throw new Error("Missing return statement in function");
  }

  final public Node Comparison() throws ParseException {
  Node sel;
  String op = null;
  List < Node > args;
    sel = Argument();
    op = Operator();
    args = Arguments();
    {if (true) return factory.createComparisonNode(op, sel, args);}
    throw new Error("Missing return statement in function");
  }

  final public String Operator() throws ParseException {
    jj_consume_token(COMP_ALT);
    {if (true) return token.image;}
    throw new Error("Missing return statement in function");
  }

  final public List < Node > Arguments() throws ParseException {
  Object value = new ArrayList();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
      jj_consume_token(LPAREN);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case UNRESERVED_STR:
      case SINGLE_QUOTED_STR:
      case DOUBLE_QUOTED_STR:
      case NULL:
      case TRUE:
      case FALSE:
      case LBRACKET:
      case NUMBER:
        value = CommaSepArguments();
        break;
      default:
        jj_la1[3] = jj_gen;
        ;
      }
      jj_consume_token(RPAREN);
    {if (true) return (List) value;}
      break;
    case UNRESERVED_STR:
    case SINGLE_QUOTED_STR:
    case DOUBLE_QUOTED_STR:
    case NULL:
    case TRUE:
    case FALSE:
    case LBRACKET:
    case NUMBER:
      value = Argument();
    {if (true) return Arrays.asList((Node) value);}
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public List < Node > CommaSepArguments() throws ParseException {
  final List < Node > list = new ArrayList < Node > (3);
  Node arg;
    arg = Argument();
    list.add(arg);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      jj_consume_token(COMMA);
      arg = Argument();
      list.add(arg);
    }
    {if (true) return list;}
    throw new Error("Missing return statement in function");
  }

  final public Node Argument() throws ParseException {
  Node selector = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SINGLE_QUOTED_STR:
    case DOUBLE_QUOTED_STR:
    case NULL:
    case TRUE:
    case FALSE:
    case NUMBER:
      selector = Literal();
      break;
    case LBRACKET:
      selector = Property();
      break;
    case UNRESERVED_STR:
      selector = Function();
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return selector;}
    throw new Error("Missing return statement in function");
  }

  final public Node Function() throws ParseException {
  Node selector = null;
  List < Node > children = null;
    jj_consume_token(UNRESERVED_STR);
    selector = factory.createArgument(token.kind, token.image);
    children = Arguments();
    {if (true) return factory.createFunction(selector, children);}
    throw new Error("Missing return statement in function");
  }

  final public Node Literal() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DOUBLE_QUOTED_STR:
      jj_consume_token(DOUBLE_QUOTED_STR);
      break;
    case SINGLE_QUOTED_STR:
      jj_consume_token(SINGLE_QUOTED_STR);
      break;
    case NUMBER:
      jj_consume_token(NUMBER);
      break;
    case NULL:
      jj_consume_token(NULL);
      break;
    case TRUE:
      jj_consume_token(TRUE);
      break;
    case FALSE:
      jj_consume_token(FALSE);
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return factory.createArgument(token.kind, token.image);}
    throw new Error("Missing return statement in function");
  }

  final public Node Property() throws ParseException {
  Token string = null;
    jj_consume_token(LBRACKET);
    string = jj_consume_token(UNRESERVED_STR);
    jj_consume_token(RBRACKET);
    {if (true) return factory.createPropertySelector(string.image);}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public ParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[8];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x4000,0x2000,0x1179c00,0x1139c00,0x1179c00,0x400000,0x1139c00,0x1039800,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[29];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 8; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 29; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
