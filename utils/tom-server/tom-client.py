#!/usr/bin/env python

import xmlrpclib
import sys
import os

PORT = 8000

TOM_HOME = os.getenv("TOM_HOME")
CONFIG = TOM_HOME + "/Tom.xml"
INC = TOM_HOME + "/share/tom/java"

def absolute(s):
    if s.endswith(".t"): return os.path.abspath(s)
    else               : return s

userargs = map(absolute, sys.argv[1:])
args = ['-X', CONFIG, '-I', INC, '-I', os.getcwd(), '-d', os.getcwd()]

s = xmlrpclib.ServerProxy('http://localhost:%d' % PORT)
t = s.compile(args + userargs)
(out,err) = eval(t)

sys.stdout.write(out)
sys.stderr.write(err)

