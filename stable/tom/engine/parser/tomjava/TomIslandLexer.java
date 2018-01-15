// Generated from /Users/pem/github/tom/src/tom/engine/parser/tomjava/TomIslandLexer.g4 by ANTLR 4.5.3
package tom.engine.parser.tomjava;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TomIslandLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MATCH=1, STRATEGY=2, EXTENDS=3, VISIT=4, INCLUDE=5, GOM=6, OP=7, OPARRAY=8, 
		OPLIST=9, TYPETERM=10, IS_FSYM=11, IS_SORT=12, MAKE=13, MAKE_EMPTY=14, 
		MAKE_APPEND=15, MAKE_INSERT=16, GET_SLOT=17, GET_DEFAULT=18, GET_ELEMENT=19, 
		GET_HEAD=20, GET_TAIL=21, GET_SIZE=22, IS_EMPTY=23, IMPLEMENT=24, EQUALS=25, 
		MATCH_SYMBOL=26, EQUAL=27, LBRACE=28, RBRACE=29, LPAREN=30, RPAREN=31, 
		LSQUAREBR=32, RSQUAREBR=33, ARROW=34, COMMA=35, COLON=36, STAR=37, UNDERSCORE=38, 
		ANTI=39, AT=40, METAQUOTE=41, GREATEROREQ=42, LOWEROREQ=43, GREATERTHAN=44, 
		LOWERTHAN=45, DOUBLEEQ=46, DIFFERENT=47, AND=48, OR=49, PIPE=50, QMARK=51, 
		DQMARK=52, SLASH=53, BACKSLASH=54, DOT=55, BQUOTE=56, ID=57, DMINUSID=58, 
		INTEGER=59, DOUBLE=60, LONG=61, STRING=62, CHAR=63, ACTION_ESCAPE=64, 
		ACTION_STRING_LITERAL=65, MLCOMMENT=66, SLCOMMENT=67, WS=68, NL=69, ANY=70;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"MATCH", "STRATEGY", "EXTENDS", "VISIT", "INCLUDE", "GOM", "OP", "OPARRAY", 
		"OPLIST", "TYPETERM", "IS_FSYM", "IS_SORT", "MAKE", "MAKE_EMPTY", "MAKE_APPEND", 
		"MAKE_INSERT", "GET_SLOT", "GET_DEFAULT", "GET_ELEMENT", "GET_HEAD", "GET_TAIL", 
		"GET_SIZE", "IS_EMPTY", "IMPLEMENT", "EQUALS", "MATCH_SYMBOL", "EQUAL", 
		"LBRACE", "RBRACE", "LPAREN", "RPAREN", "LSQUAREBR", "RSQUAREBR", "ARROW", 
		"COMMA", "COLON", "STAR", "UNDERSCORE", "ANTI", "AT", "METAQUOTE", "GREATEROREQ", 
		"LOWEROREQ", "GREATERTHAN", "LOWERTHAN", "DOUBLEEQ", "DIFFERENT", "AND", 
		"OR", "PIPE", "QMARK", "DQMARK", "SLASH", "BACKSLASH", "DOT", "BQUOTE", 
		"ID", "DMINUSID", "INTEGER", "DOUBLE", "LONG", "STRING", "CHAR", "UNSIGNED_DOUBLE", 
		"LONG_SUFFIX", "LETTER", "DIGIT", "HEX_DIGIT", "ESC", "ACTION_ESCAPE", 
		"ACTION_STRING_LITERAL", "MLCOMMENT", "SLCOMMENT", "WS", "NL", "ANY"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'%match'", "'%strategy'", "'extends'", "'visit'", "'%include'", 
		"'%gom'", "'%op'", "'%oparray'", "'%oplist'", "'%typeterm'", "'is_fsym'", 
		"'is_sort'", "'make'", "'make_empty'", "'make_append'", "'make_insert'", 
		"'get_slot'", "'get_default'", "'get_element'", "'get_head'", "'get_tail'", 
		"'get_size'", "'is_empty'", "'implement'", "'equals'", "'<<'", "'='", 
		"'{'", "'}'", "'('", "')'", "'['", "']'", "'->'", "','", "':'", "'*'", 
		"'_'", "'!'", "'@'", null, "'>='", "'<='", "'>'", "'<'", "'=='", "'!='", 
		"'&&'", "'||'", "'|'", "'?'", "'??'", "'/'", "'\\'", "'.'", "'`'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "MATCH", "STRATEGY", "EXTENDS", "VISIT", "INCLUDE", "GOM", "OP", 
		"OPARRAY", "OPLIST", "TYPETERM", "IS_FSYM", "IS_SORT", "MAKE", "MAKE_EMPTY", 
		"MAKE_APPEND", "MAKE_INSERT", "GET_SLOT", "GET_DEFAULT", "GET_ELEMENT", 
		"GET_HEAD", "GET_TAIL", "GET_SIZE", "IS_EMPTY", "IMPLEMENT", "EQUALS", 
		"MATCH_SYMBOL", "EQUAL", "LBRACE", "RBRACE", "LPAREN", "RPAREN", "LSQUAREBR", 
		"RSQUAREBR", "ARROW", "COMMA", "COLON", "STAR", "UNDERSCORE", "ANTI", 
		"AT", "METAQUOTE", "GREATEROREQ", "LOWEROREQ", "GREATERTHAN", "LOWERTHAN", 
		"DOUBLEEQ", "DIFFERENT", "AND", "OR", "PIPE", "QMARK", "DQMARK", "SLASH", 
		"BACKSLASH", "DOT", "BQUOTE", "ID", "DMINUSID", "INTEGER", "DOUBLE", "LONG", 
		"STRING", "CHAR", "ACTION_ESCAPE", "ACTION_STRING_LITERAL", "MLCOMMENT", 
		"SLCOMMENT", "WS", "NL", "ANY"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public static final int WHITESPACE = 1; 
	public static final int COMMENTS = 2;


	public TomIslandLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TomIslandLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2H\u0270\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33"+
		"\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3#"+
		"\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3*\3*\7*\u019a\n*\f*\16*"+
		"\u019d\13*\3*\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3.\3.\3/\3/\3/\3\60\3\60\3"+
		"\60\3\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\65\3"+
		"\66\3\66\3\67\3\67\38\38\39\39\3:\5:\u01c8\n:\3:\3:\3:\3:\7:\u01ce\n:"+
		"\f:\16:\u01d1\13:\3;\3;\3;\3;\7;\u01d7\n;\f;\16;\u01da\13;\3<\5<\u01dd"+
		"\n<\3<\6<\u01e0\n<\r<\16<\u01e1\3=\5=\u01e5\n=\3=\3=\3>\5>\u01ea\n>\3"+
		">\6>\u01ed\n>\r>\16>\u01ee\3>\3>\3?\3?\3?\7?\u01f6\n?\f?\16?\u01f9\13"+
		"?\3?\3?\3@\3@\3@\6@\u0200\n@\r@\16@\u0201\3@\3@\3A\6A\u0207\nA\rA\16A"+
		"\u0208\3A\3A\7A\u020d\nA\fA\16A\u0210\13A\3A\3A\6A\u0214\nA\rA\16A\u0215"+
		"\5A\u0218\nA\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3F\6F\u0225\nF\rF\16F\u0226"+
		"\3F\3F\3F\3F\3F\3F\3F\3F\5F\u0231\nF\5F\u0233\nF\3F\3F\5F\u0237\nF\5F"+
		"\u0239\nF\3G\3G\3G\3H\3H\3H\7H\u0241\nH\fH\16H\u0244\13H\3H\3H\3I\3I\3"+
		"I\3I\7I\u024c\nI\fI\16I\u024f\13I\3I\3I\3I\3I\3I\3J\3J\3J\3J\7J\u025a"+
		"\nJ\fJ\16J\u025d\13J\3J\3J\3K\6K\u0262\nK\rK\16K\u0263\3K\3K\3L\6L\u0269"+
		"\nL\rL\16L\u026a\3L\3L\3M\3M\4\u019b\u024d\2N\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S"+
		"+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081"+
		"\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b\2\u008dB\u008fC\u0091D\u0093"+
		"E\u0095F\u0097G\u0099H\3\2\f\6\2\f\f\17\17$$^^\6\2\f\f\17\17))^^\4\2N"+
		"Nnn\4\2C\\c|\5\2\62;CHch\n\2$$))^^ddhhppttvv\4\2$$^^\4\2\f\f\17\17\5\2"+
		"\13\13\17\17\"\"\3\2\f\f\u0289\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2"+
		"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3"+
		"\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2"+
		"\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2"+
		"i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3"+
		"\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u008d"+
		"\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2"+
		"\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\3\u009b\3\2\2\2\5\u00a2\3\2\2\2\7\u00ac"+
		"\3\2\2\2\t\u00b4\3\2\2\2\13\u00ba\3\2\2\2\r\u00c3\3\2\2\2\17\u00c8\3\2"+
		"\2\2\21\u00cc\3\2\2\2\23\u00d5\3\2\2\2\25\u00dd\3\2\2\2\27\u00e7\3\2\2"+
		"\2\31\u00ef\3\2\2\2\33\u00f7\3\2\2\2\35\u00fc\3\2\2\2\37\u0107\3\2\2\2"+
		"!\u0113\3\2\2\2#\u011f\3\2\2\2%\u0128\3\2\2\2\'\u0134\3\2\2\2)\u0140\3"+
		"\2\2\2+\u0149\3\2\2\2-\u0152\3\2\2\2/\u015b\3\2\2\2\61\u0164\3\2\2\2\63"+
		"\u016e\3\2\2\2\65\u0175\3\2\2\2\67\u0178\3\2\2\29\u017a\3\2\2\2;\u017c"+
		"\3\2\2\2=\u017e\3\2\2\2?\u0180\3\2\2\2A\u0182\3\2\2\2C\u0184\3\2\2\2E"+
		"\u0186\3\2\2\2G\u0189\3\2\2\2I\u018b\3\2\2\2K\u018d\3\2\2\2M\u018f\3\2"+
		"\2\2O\u0191\3\2\2\2Q\u0193\3\2\2\2S\u0195\3\2\2\2U\u01a1\3\2\2\2W\u01a4"+
		"\3\2\2\2Y\u01a7\3\2\2\2[\u01a9\3\2\2\2]\u01ab\3\2\2\2_\u01ae\3\2\2\2a"+
		"\u01b1\3\2\2\2c\u01b4\3\2\2\2e\u01b7\3\2\2\2g\u01b9\3\2\2\2i\u01bb\3\2"+
		"\2\2k\u01be\3\2\2\2m\u01c0\3\2\2\2o\u01c2\3\2\2\2q\u01c4\3\2\2\2s\u01c7"+
		"\3\2\2\2u\u01d2\3\2\2\2w\u01dc\3\2\2\2y\u01e4\3\2\2\2{\u01e9\3\2\2\2}"+
		"\u01f2\3\2\2\2\177\u01fc\3\2\2\2\u0081\u0217\3\2\2\2\u0083\u0219\3\2\2"+
		"\2\u0085\u021b\3\2\2\2\u0087\u021d\3\2\2\2\u0089\u021f\3\2\2\2\u008b\u0221"+
		"\3\2\2\2\u008d\u023a\3\2\2\2\u008f\u023d\3\2\2\2\u0091\u0247\3\2\2\2\u0093"+
		"\u0255\3\2\2\2\u0095\u0261\3\2\2\2\u0097\u0268\3\2\2\2\u0099\u026e\3\2"+
		"\2\2\u009b\u009c\7\'\2\2\u009c\u009d\7o\2\2\u009d\u009e\7c\2\2\u009e\u009f"+
		"\7v\2\2\u009f\u00a0\7e\2\2\u00a0\u00a1\7j\2\2\u00a1\4\3\2\2\2\u00a2\u00a3"+
		"\7\'\2\2\u00a3\u00a4\7u\2\2\u00a4\u00a5\7v\2\2\u00a5\u00a6\7t\2\2\u00a6"+
		"\u00a7\7c\2\2\u00a7\u00a8\7v\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7i\2\2"+
		"\u00aa\u00ab\7{\2\2\u00ab\6\3\2\2\2\u00ac\u00ad\7g\2\2\u00ad\u00ae\7z"+
		"\2\2\u00ae\u00af\7v\2\2\u00af\u00b0\7g\2\2\u00b0\u00b1\7p\2\2\u00b1\u00b2"+
		"\7f\2\2\u00b2\u00b3\7u\2\2\u00b3\b\3\2\2\2\u00b4\u00b5\7x\2\2\u00b5\u00b6"+
		"\7k\2\2\u00b6\u00b7\7u\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7v\2\2\u00b9"+
		"\n\3\2\2\2\u00ba\u00bb\7\'\2\2\u00bb\u00bc\7k\2\2\u00bc\u00bd\7p\2\2\u00bd"+
		"\u00be\7e\2\2\u00be\u00bf\7n\2\2\u00bf\u00c0\7w\2\2\u00c0\u00c1\7f\2\2"+
		"\u00c1\u00c2\7g\2\2\u00c2\f\3\2\2\2\u00c3\u00c4\7\'\2\2\u00c4\u00c5\7"+
		"i\2\2\u00c5\u00c6\7q\2\2\u00c6\u00c7\7o\2\2\u00c7\16\3\2\2\2\u00c8\u00c9"+
		"\7\'\2\2\u00c9\u00ca\7q\2\2\u00ca\u00cb\7r\2\2\u00cb\20\3\2\2\2\u00cc"+
		"\u00cd\7\'\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf\7r\2\2\u00cf\u00d0\7c\2"+
		"\2\u00d0\u00d1\7t\2\2\u00d1\u00d2\7t\2\2\u00d2\u00d3\7c\2\2\u00d3\u00d4"+
		"\7{\2\2\u00d4\22\3\2\2\2\u00d5\u00d6\7\'\2\2\u00d6\u00d7\7q\2\2\u00d7"+
		"\u00d8\7r\2\2\u00d8\u00d9\7n\2\2\u00d9\u00da\7k\2\2\u00da\u00db\7u\2\2"+
		"\u00db\u00dc\7v\2\2\u00dc\24\3\2\2\2\u00dd\u00de\7\'\2\2\u00de\u00df\7"+
		"v\2\2\u00df\u00e0\7{\2\2\u00e0\u00e1\7r\2\2\u00e1\u00e2\7g\2\2\u00e2\u00e3"+
		"\7v\2\2\u00e3\u00e4\7g\2\2\u00e4\u00e5\7t\2\2\u00e5\u00e6\7o\2\2\u00e6"+
		"\26\3\2\2\2\u00e7\u00e8\7k\2\2\u00e8\u00e9\7u\2\2\u00e9\u00ea\7a\2\2\u00ea"+
		"\u00eb\7h\2\2\u00eb\u00ec\7u\2\2\u00ec\u00ed\7{\2\2\u00ed\u00ee\7o\2\2"+
		"\u00ee\30\3\2\2\2\u00ef\u00f0\7k\2\2\u00f0\u00f1\7u\2\2\u00f1\u00f2\7"+
		"a\2\2\u00f2\u00f3\7u\2\2\u00f3\u00f4\7q\2\2\u00f4\u00f5\7t\2\2\u00f5\u00f6"+
		"\7v\2\2\u00f6\32\3\2\2\2\u00f7\u00f8\7o\2\2\u00f8\u00f9\7c\2\2\u00f9\u00fa"+
		"\7m\2\2\u00fa\u00fb\7g\2\2\u00fb\34\3\2\2\2\u00fc\u00fd\7o\2\2\u00fd\u00fe"+
		"\7c\2\2\u00fe\u00ff\7m\2\2\u00ff\u0100\7g\2\2\u0100\u0101\7a\2\2\u0101"+
		"\u0102\7g\2\2\u0102\u0103\7o\2\2\u0103\u0104\7r\2\2\u0104\u0105\7v\2\2"+
		"\u0105\u0106\7{\2\2\u0106\36\3\2\2\2\u0107\u0108\7o\2\2\u0108\u0109\7"+
		"c\2\2\u0109\u010a\7m\2\2\u010a\u010b\7g\2\2\u010b\u010c\7a\2\2\u010c\u010d"+
		"\7c\2\2\u010d\u010e\7r\2\2\u010e\u010f\7r\2\2\u010f\u0110\7g\2\2\u0110"+
		"\u0111\7p\2\2\u0111\u0112\7f\2\2\u0112 \3\2\2\2\u0113\u0114\7o\2\2\u0114"+
		"\u0115\7c\2\2\u0115\u0116\7m\2\2\u0116\u0117\7g\2\2\u0117\u0118\7a\2\2"+
		"\u0118\u0119\7k\2\2\u0119\u011a\7p\2\2\u011a\u011b\7u\2\2\u011b\u011c"+
		"\7g\2\2\u011c\u011d\7t\2\2\u011d\u011e\7v\2\2\u011e\"\3\2\2\2\u011f\u0120"+
		"\7i\2\2\u0120\u0121\7g\2\2\u0121\u0122\7v\2\2\u0122\u0123\7a\2\2\u0123"+
		"\u0124\7u\2\2\u0124\u0125\7n\2\2\u0125\u0126\7q\2\2\u0126\u0127\7v\2\2"+
		"\u0127$\3\2\2\2\u0128\u0129\7i\2\2\u0129\u012a\7g\2\2\u012a\u012b\7v\2"+
		"\2\u012b\u012c\7a\2\2\u012c\u012d\7f\2\2\u012d\u012e\7g\2\2\u012e\u012f"+
		"\7h\2\2\u012f\u0130\7c\2\2\u0130\u0131\7w\2\2\u0131\u0132\7n\2\2\u0132"+
		"\u0133\7v\2\2\u0133&\3\2\2\2\u0134\u0135\7i\2\2\u0135\u0136\7g\2\2\u0136"+
		"\u0137\7v\2\2\u0137\u0138\7a\2\2\u0138\u0139\7g\2\2\u0139\u013a\7n\2\2"+
		"\u013a\u013b\7g\2\2\u013b\u013c\7o\2\2\u013c\u013d\7g\2\2\u013d\u013e"+
		"\7p\2\2\u013e\u013f\7v\2\2\u013f(\3\2\2\2\u0140\u0141\7i\2\2\u0141\u0142"+
		"\7g\2\2\u0142\u0143\7v\2\2\u0143\u0144\7a\2\2\u0144\u0145\7j\2\2\u0145"+
		"\u0146\7g\2\2\u0146\u0147\7c\2\2\u0147\u0148\7f\2\2\u0148*\3\2\2\2\u0149"+
		"\u014a\7i\2\2\u014a\u014b\7g\2\2\u014b\u014c\7v\2\2\u014c\u014d\7a\2\2"+
		"\u014d\u014e\7v\2\2\u014e\u014f\7c\2\2\u014f\u0150\7k\2\2\u0150\u0151"+
		"\7n\2\2\u0151,\3\2\2\2\u0152\u0153\7i\2\2\u0153\u0154\7g\2\2\u0154\u0155"+
		"\7v\2\2\u0155\u0156\7a\2\2\u0156\u0157\7u\2\2\u0157\u0158\7k\2\2\u0158"+
		"\u0159\7|\2\2\u0159\u015a\7g\2\2\u015a.\3\2\2\2\u015b\u015c\7k\2\2\u015c"+
		"\u015d\7u\2\2\u015d\u015e\7a\2\2\u015e\u015f\7g\2\2\u015f\u0160\7o\2\2"+
		"\u0160\u0161\7r\2\2\u0161\u0162\7v\2\2\u0162\u0163\7{\2\2\u0163\60\3\2"+
		"\2\2\u0164\u0165\7k\2\2\u0165\u0166\7o\2\2\u0166\u0167\7r\2\2\u0167\u0168"+
		"\7n\2\2\u0168\u0169\7g\2\2\u0169\u016a\7o\2\2\u016a\u016b\7g\2\2\u016b"+
		"\u016c\7p\2\2\u016c\u016d\7v\2\2\u016d\62\3\2\2\2\u016e\u016f\7g\2\2\u016f"+
		"\u0170\7s\2\2\u0170\u0171\7w\2\2\u0171\u0172\7c\2\2\u0172\u0173\7n\2\2"+
		"\u0173\u0174\7u\2\2\u0174\64\3\2\2\2\u0175\u0176\7>\2\2\u0176\u0177\7"+
		">\2\2\u0177\66\3\2\2\2\u0178\u0179\7?\2\2\u01798\3\2\2\2\u017a\u017b\7"+
		"}\2\2\u017b:\3\2\2\2\u017c\u017d\7\177\2\2\u017d<\3\2\2\2\u017e\u017f"+
		"\7*\2\2\u017f>\3\2\2\2\u0180\u0181\7+\2\2\u0181@\3\2\2\2\u0182\u0183\7"+
		"]\2\2\u0183B\3\2\2\2\u0184\u0185\7_\2\2\u0185D\3\2\2\2\u0186\u0187\7/"+
		"\2\2\u0187\u0188\7@\2\2\u0188F\3\2\2\2\u0189\u018a\7.\2\2\u018aH\3\2\2"+
		"\2\u018b\u018c\7<\2\2\u018cJ\3\2\2\2\u018d\u018e\7,\2\2\u018eL\3\2\2\2"+
		"\u018f\u0190\7a\2\2\u0190N\3\2\2\2\u0191\u0192\7#\2\2\u0192P\3\2\2\2\u0193"+
		"\u0194\7B\2\2\u0194R\3\2\2\2\u0195\u0196\7\'\2\2\u0196\u0197\7]\2\2\u0197"+
		"\u019b\3\2\2\2\u0198\u019a\13\2\2\2\u0199\u0198\3\2\2\2\u019a\u019d\3"+
		"\2\2\2\u019b\u019c\3\2\2\2\u019b\u0199\3\2\2\2\u019c\u019e\3\2\2\2\u019d"+
		"\u019b\3\2\2\2\u019e\u019f\7_\2\2\u019f\u01a0\7\'\2\2\u01a0T\3\2\2\2\u01a1"+
		"\u01a2\7@\2\2\u01a2\u01a3\7?\2\2\u01a3V\3\2\2\2\u01a4\u01a5\7>\2\2\u01a5"+
		"\u01a6\7?\2\2\u01a6X\3\2\2\2\u01a7\u01a8\7@\2\2\u01a8Z\3\2\2\2\u01a9\u01aa"+
		"\7>\2\2\u01aa\\\3\2\2\2\u01ab\u01ac\7?\2\2\u01ac\u01ad\7?\2\2\u01ad^\3"+
		"\2\2\2\u01ae\u01af\7#\2\2\u01af\u01b0\7?\2\2\u01b0`\3\2\2\2\u01b1\u01b2"+
		"\7(\2\2\u01b2\u01b3\7(\2\2\u01b3b\3\2\2\2\u01b4\u01b5\7~\2\2\u01b5\u01b6"+
		"\7~\2\2\u01b6d\3\2\2\2\u01b7\u01b8\7~\2\2\u01b8f\3\2\2\2\u01b9\u01ba\7"+
		"A\2\2\u01bah\3\2\2\2\u01bb\u01bc\7A\2\2\u01bc\u01bd\7A\2\2\u01bdj\3\2"+
		"\2\2\u01be\u01bf\7\61\2\2\u01bfl\3\2\2\2\u01c0\u01c1\7^\2\2\u01c1n\3\2"+
		"\2\2\u01c2\u01c3\7\60\2\2\u01c3p\3\2\2\2\u01c4\u01c5\7b\2\2\u01c5r\3\2"+
		"\2\2\u01c6\u01c8\7a\2\2\u01c7\u01c6\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8"+
		"\u01c9\3\2\2\2\u01c9\u01cf\5\u0085C\2\u01ca\u01ce\5\u0085C\2\u01cb\u01ce"+
		"\5\u0087D\2\u01cc\u01ce\7a\2\2\u01cd\u01ca\3\2\2\2\u01cd\u01cb\3\2\2\2"+
		"\u01cd\u01cc\3\2\2\2\u01ce\u01d1\3\2\2\2\u01cf\u01cd\3\2\2\2\u01cf\u01d0"+
		"\3\2\2\2\u01d0t\3\2\2\2\u01d1\u01cf\3\2\2\2\u01d2\u01d3\7/\2\2\u01d3\u01d4"+
		"\7/\2\2\u01d4\u01d8\3\2\2\2\u01d5\u01d7\5\u0085C\2\u01d6\u01d5\3\2\2\2"+
		"\u01d7\u01da\3\2\2\2\u01d8\u01d6\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9v\3"+
		"\2\2\2\u01da\u01d8\3\2\2\2\u01db\u01dd\7/\2\2\u01dc\u01db\3\2\2\2\u01dc"+
		"\u01dd\3\2\2\2\u01dd\u01df\3\2\2\2\u01de\u01e0\5\u0087D\2\u01df\u01de"+
		"\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1\u01df\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2"+
		"x\3\2\2\2\u01e3\u01e5\7/\2\2\u01e4\u01e3\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5"+
		"\u01e6\3\2\2\2\u01e6\u01e7\5\u0081A\2\u01e7z\3\2\2\2\u01e8\u01ea\7/\2"+
		"\2\u01e9\u01e8\3\2\2\2\u01e9\u01ea\3\2\2\2\u01ea\u01ec\3\2\2\2\u01eb\u01ed"+
		"\5\u0087D\2\u01ec\u01eb\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01ec\3\2\2"+
		"\2\u01ee\u01ef\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0\u01f1\5\u0083B\2\u01f1"+
		"|\3\2\2\2\u01f2\u01f7\7$\2\2\u01f3\u01f6\5\u008bF\2\u01f4\u01f6\n\2\2"+
		"\2\u01f5\u01f3\3\2\2\2\u01f5\u01f4\3\2\2\2\u01f6\u01f9\3\2\2\2\u01f7\u01f5"+
		"\3\2\2\2\u01f7\u01f8\3\2\2\2\u01f8\u01fa\3\2\2\2\u01f9\u01f7\3\2\2\2\u01fa"+
		"\u01fb\7$\2\2\u01fb~\3\2\2\2\u01fc\u01ff\7)\2\2\u01fd\u0200\5\u008bF\2"+
		"\u01fe\u0200\n\3\2\2\u01ff\u01fd\3\2\2\2\u01ff\u01fe\3\2\2\2\u0200\u0201"+
		"\3\2\2\2\u0201\u01ff\3\2\2\2\u0201\u0202\3\2\2\2\u0202\u0203\3\2\2\2\u0203"+
		"\u0204\7)\2\2\u0204\u0080\3\2\2\2\u0205\u0207\5\u0087D\2\u0206\u0205\3"+
		"\2\2\2\u0207\u0208\3\2\2\2\u0208\u0206\3\2\2\2\u0208\u0209\3\2\2\2\u0209"+
		"\u020a\3\2\2\2\u020a\u020e\7\60\2\2\u020b\u020d\5\u0087D\2\u020c\u020b"+
		"\3\2\2\2\u020d\u0210\3\2\2\2\u020e\u020c\3\2\2\2\u020e\u020f\3\2\2\2\u020f"+
		"\u0218\3\2\2\2\u0210\u020e\3\2\2\2\u0211\u0213\7\60\2\2\u0212\u0214\5"+
		"\u0087D\2\u0213\u0212\3\2\2\2\u0214\u0215\3\2\2\2\u0215\u0213\3\2\2\2"+
		"\u0215\u0216\3\2\2\2\u0216\u0218\3\2\2\2\u0217\u0206\3\2\2\2\u0217\u0211"+
		"\3\2\2\2\u0218\u0082\3\2\2\2\u0219\u021a\t\4\2\2\u021a\u0084\3\2\2\2\u021b"+
		"\u021c\t\5\2\2\u021c\u0086\3\2\2\2\u021d\u021e\4\62;\2\u021e\u0088\3\2"+
		"\2\2\u021f\u0220\t\6\2\2\u0220\u008a\3\2\2\2\u0221\u0238\7^\2\2\u0222"+
		"\u0239\t\7\2\2\u0223\u0225\7w\2\2\u0224\u0223\3\2\2\2\u0225\u0226\3\2"+
		"\2\2\u0226\u0224\3\2\2\2\u0226\u0227\3\2\2\2\u0227\u0228\3\2\2\2\u0228"+
		"\u0229\5\u0089E\2\u0229\u022a\5\u0089E\2\u022a\u022b\5\u0089E\2\u022b"+
		"\u022c\5\u0089E\2\u022c\u0239\3\2\2\2\u022d\u0232\4\62\65\2\u022e\u0230"+
		"\4\629\2\u022f\u0231\4\629\2\u0230\u022f\3\2\2\2\u0230\u0231\3\2\2\2\u0231"+
		"\u0233\3\2\2\2\u0232\u022e\3\2\2\2\u0232\u0233\3\2\2\2\u0233\u0239\3\2"+
		"\2\2\u0234\u0236\4\669\2\u0235\u0237\4\629\2\u0236\u0235\3\2\2\2\u0236"+
		"\u0237\3\2\2\2\u0237\u0239\3\2\2\2\u0238\u0222\3\2\2\2\u0238\u0224\3\2"+
		"\2\2\u0238\u022d\3\2\2\2\u0238\u0234\3\2\2\2\u0239\u008c\3\2\2\2\u023a"+
		"\u023b\7^\2\2\u023b\u023c\13\2\2\2\u023c\u008e\3\2\2\2\u023d\u0242\7$"+
		"\2\2\u023e\u0241\5\u008dG\2\u023f\u0241\n\b\2\2\u0240\u023e\3\2\2\2\u0240"+
		"\u023f\3\2\2\2\u0241\u0244\3\2\2\2\u0242\u0240\3\2\2\2\u0242\u0243\3\2"+
		"\2\2\u0243\u0245\3\2\2\2\u0244\u0242\3\2\2\2\u0245\u0246\7$\2\2\u0246"+
		"\u0090\3\2\2\2\u0247\u0248\7\61\2\2\u0248\u0249\7,\2\2\u0249\u024d\3\2"+
		"\2\2\u024a\u024c\13\2\2\2\u024b\u024a\3\2\2\2\u024c\u024f\3\2\2\2\u024d"+
		"\u024e\3\2\2\2\u024d\u024b\3\2\2\2\u024e\u0250\3\2\2\2\u024f\u024d\3\2"+
		"\2\2\u0250\u0251\7,\2\2\u0251\u0252\7\61\2\2\u0252\u0253\3\2\2\2\u0253"+
		"\u0254\bI\2\2\u0254\u0092\3\2\2\2\u0255\u0256\7\61\2\2\u0256\u0257\7\61"+
		"\2\2\u0257\u025b\3\2\2\2\u0258\u025a\n\t\2\2\u0259\u0258\3\2\2\2\u025a"+
		"\u025d\3\2\2\2\u025b\u0259\3\2\2\2\u025b\u025c\3\2\2\2\u025c\u025e\3\2"+
		"\2\2\u025d\u025b\3\2\2\2\u025e\u025f\bJ\2\2\u025f\u0094\3\2\2\2\u0260"+
		"\u0262\t\n\2\2\u0261\u0260\3\2\2\2\u0262\u0263\3\2\2\2\u0263\u0261\3\2"+
		"\2\2\u0263\u0264\3\2\2\2\u0264\u0265\3\2\2\2\u0265\u0266\bK\3\2\u0266"+
		"\u0096\3\2\2\2\u0267\u0269\t\13\2\2\u0268\u0267\3\2\2\2\u0269\u026a\3"+
		"\2\2\2\u026a\u0268\3\2\2\2\u026a\u026b\3\2\2\2\u026b\u026c\3\2\2\2\u026c"+
		"\u026d\bL\3\2\u026d\u0098\3\2\2\2\u026e\u026f\13\2\2\2\u026f\u009a\3\2"+
		"\2\2 \2\u019b\u01c7\u01cd\u01cf\u01d8\u01dc\u01e1\u01e4\u01e9\u01ee\u01f5"+
		"\u01f7\u01ff\u0201\u0208\u020e\u0215\u0217\u0226\u0230\u0232\u0236\u0238"+
		"\u0240\u0242\u024d\u025b\u0263\u026a\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}