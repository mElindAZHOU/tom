.SUFFIXES: .t .java

.t.java:
	cd ../..;tom aterm2antlrgrammar/exceptions/$<

all: AntlrWrongActionException.java AntlrWrongActionsException.java AntlrWrongArgsException.java AntlrWrongCommentException.java AntlrWrongElementException.java AntlrWrongExceptionException.java AntlrWrongGrammarException.java AntlrWrongIdException.java AntlrWrongIntException.java AntlrWrongOptionException.java AntlrWrongOptionsException.java AntlrWrongRetException.java AntlrWrongRuleException.java AntlrWrongTokensException.java AntlrWrongScopeException.java RecognitionException.java

classclean:
	rm -f *.class

clean:
	make classclean
	rm -f $(foreach v,$(wildcard *.t),$(shell echo $(basename $(v)).java))
	rm -f *~
