#!/bin/sh

APIGEN_ARGS=
JAVA_ARGS=
while [ $# -gt 0 ]; do
  case $1 in
    -D*)
		JAVA_ARGS=$JAVA_ARGS" "$1
		;;
		*)
		APIGEN_ARGS=$APIGEN_ARGS" "$1
		;;			
  esac
  shift
done

# OS specific support.  $var _must_ be set to either true or false.
cygwin=false;
darwin=false;
case "`uname`" in
  CYGWIN*) cygwin=true ;;
  Darwin*) darwin=true ;;
esac

if [ -z "$TOM_HOME" -o ! -d "$TOM_HOME" ] ; then
  ## resolve links - $0 may be a link to tom's home
  PRG="$0"
  progname=`basename "$0"`

  # need this for relative symlinks
  while [ -h "$PRG" ] ; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
    else
    PRG=`dirname "$PRG"`"/$link"
    fi
  done

  TOM_HOME=`dirname "$PRG"`/..

  # make it fully qualified
  TOM_HOME=`cd "$TOM_HOME" && pwd`
fi

# For Cygwin, ensure paths are in UNIX format before anything is touched
if $cygwin ; then
  [ -n "$TOM_HOME" ] &&
    TOM_HOME=`cygpath --unix "$TOM_HOME"`
fi

# set TOM_LIB location
TOM_LIB="${TOM_HOME}/lib"
TOM_JAR=`echo $TOM_LIB/*.jar | tr ' ' ':'`
EXTERNAL_JAR=
JAVACMD=java$JAVA_ARGS


if [ -z "$LOCALCLASSPATH" ] ; then
    LOCALCLASSPATH=$TOM_JAR:$EXTERNAL_JAR
else
    LOCALCLASSPATH=$TOM_JAR:$EXTERNAL_JAR:$LOCALCLASSPATH
fi

# For Cygwin, switch paths to Windows format before running java
if $cygwin; then
  TOM_HOME=`cygpath --windows "$TOM_HOME"`
  TOM_INC=`cygpath --windows "$TOM_INC"`
  LOCALCLASSPATH=`cygpath --path --windows "$LOCALCLASSPATH"`
  TOM_CONFIG_FILE=`cygpath --windows "$TOM_CONFIG_FILE"`
fi

#echo $JAVACMD -cp $LOCALCLASSPATH apigen.gen.tom.java.Main $APIGEN_ARGS

exec $JAVACMD -cp $LOCALCLASSPATH apigen.gen.tom.java.Main $APIGEN_ARGS
