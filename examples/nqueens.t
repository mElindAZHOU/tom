/*
  
    TOM - To One Matching Compiler

    Copyright (C) 2000-2001  LORIA (CNRS, INPL, INRIA, UHP, U-Nancy 2)
			     Nancy, France.

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA

    Pierre-Etienne Moreau	e-mail: Pierre-Etienne.Moreau@loria.fr
    Christophe Ringeissen	e-mail: Christophe.Ringeissen@loria.fr
    Marian Vittek		e-mail: vittek@guma.ii.fmph.uniba.sk

*/

#include<stdio.h>
#include<stdlib.h>

#include <aterm1.h>
#include <aterm2.h>
#include <choice.h>

static AFun f_zero;
static AFun f_suc;
static AFun f_nqueens;
static AFun f_empty;
static AFun f_cons;

static ATermInt tzero;
static ATermInt tone;

%typeterm Nat {
  implement           { ATermInt }
  get_fun_sym(t)      { ((t==tzero)?f_zero:f_suc) }
  cmp_fun_sym(s1,s2)  { s1 == s2 }
  get_subterm(t, n)   { ATmakeInt(ATgetInt(t)-1) }
}

%op Nat zero {
  fsym { f_zero }
}
  
%op Nat suc(Nat) {
  fsym { f_suc }
}

%typeterm Term {
  implement           { ATerm }
  get_fun_sym(t)      { ATgetAFun(t) }
  cmp_fun_sym(s1,s2)  { s1 == s2 }
  get_subterm(t, n)   { ATgetArgument(t,n) }
}

%op Term nqueens(Nat,Term) {
  fsym { f_nqueens }
}

%op Term empty {
  fsym { f_empty }
}

%op Term cons(Nat,Term) {
  fsym { f_cons }
}

#define empty()             ((ATerm)ATmakeAppl0(f_empty))
#define cons(e,l)           ((ATerm)ATmakeAppl2(f_cons,e,l))
#define nqueens(n,s)        ((ATerm)ATmakeAppl2(f_nqueens,(ATerm)n,s))

#define TRUE                (0==0)
#define FALSE               (!TRUE)
#define i2t(n)              ((ATerm)ATmakeInt(n))
#define t2i(t)              (ATgetInt((ATermInt)t))
#define plus(t1,t2)         i2t(t2i(t1)+t2i(t2))
#define minus(t1,t2)        i2t(t2i(t1)-t2i(t2))
#define neq(t1,t2)          ((ATerm)t1 != (ATerm)t2)


ATerm range_rule(ATerm arg0);
ATerm range_strat(ATerm arg0);
ATerm nqueens_rule(ATerm arg0);
ATerm nqueens_strat(ATerm arg0);
int   noattack(ATerm arg0, ATerm arg1, ATerm arg2);

//------------------------------------------------------------

int noattack(ATerm arg0, ATerm arg1, ATerm arg2) {
  int res = FALSE;
  
  %match(Term arg0, Term arg1, Term arg2) {
    N1,N2,empty -> {
      res = TRUE;
      goto end;
    }
    
    N1,N2,cons(X,LX) -> {
      res =
        neq(N2,X) &&
        neq(minus(N2,X),N1) &&
        neq(minus(X,N2),N1) &&
        noattack(plus(N1,tone),N2,LX);
      goto end;
    }
  }
  end:
  return res;
}


ATerm range_rule(ATerm arg0) {
  ATerm res = arg0;
  
  %match(Nat res) {
    N -> {
      if(t2i(N) > 1) {
        res = minus(N,tone);
        goto end;
      }
    }
  }
  fail();
  end:
  return res;
}

ATerm nqueens_rule(ATerm arg0) {
  ATerm res = arg0;
  ATerm X;
  ATerm LX;

  %match(Term res) {
    nqueens(zero,size) -> {
      res = empty();
      goto end;
    }

    nqueens(N,size) -> {
      if(localSetChoicePoint()) {
          /* local evaluations failed, try next rule */
        goto myend;
      }

      if(t2i(N) > 0) {
        LX = nqueens_strat(nqueens(minus(N,tone),size));
        X  = (ATerm)range_strat((ATerm)size);
      
        if(noattack((ATerm)tone,X,LX)) {
          res = cons(X,LX);
          goto end;
        }
      }
      myend:
    }
  }
  fail();
  end:
  return res;
} 

ATerm range_strat(ATerm arg0) {
  ATerm res = arg0;
  
  /* iterate(dk(range-rule)) */
  while(1) {
    if(!setChoicePoint()) {
      break;
    }
    res = range_rule(res);
  }
  end:
  return res;
}

ATerm nqueens_strat(ATerm arg0) {
  ATerm res = arg0;

    /* dk(nqueens-rule-0,nqueens-rule-N) */
  res = nqueens_rule(res);
  end:
  return res;
} 

#define start_nqueens(N) nqueens_strat(nqueens(i2t(N),i2t(N)))

//------------------------------------------------------------

void *at_malloc_protect(int size) {  
  void *start = malloc(size);  
  ATprotectMemory(start,size);
  return start;
}

void *at_realloc_protect(char *old,int size) {
  void *start = realloc(old,size);
  ATunprotectMemory(old);
  ATprotectMemory(start,size);
  return start;
}


int main(int argc, char **argv) {
  ATerm     bottomOfStack;
  int n;
  ATerm res;
  
  ATinit(argc, argv, &bottomOfStack);

  CPL_init_malloc_protect(at_malloc_protect);
  CPL_init_malloc(malloc);
  CPL_init_realloc_protect(at_realloc_protect);
  CPL_init_realloc(realloc);
  choice_init((long*)&bottomOfStack);
 
  f_zero    = ATmakeAFun("zero", 0, ATfalse);
  f_suc     = ATmakeAFun("suc", 1, ATfalse);
  f_nqueens = ATmakeAFun("nqueens", 2, ATfalse);
  f_empty   = ATmakeAFun("empty", 0, ATfalse);
  f_cons    = ATmakeAFun("cons", 2, ATfalse);
  
  ATprotectAFun(f_zero);
  ATprotectAFun(f_suc);
  ATprotectAFun(f_nqueens);
  ATprotectAFun(f_empty);
  ATprotectAFun(f_cons);

  tzero = ATmakeInt(0);
  tone  = ATmakeInt(1);
  ATprotect((ATerm*)&tzero);
  ATprotect((ATerm*)&tone);

  if(!setChoicePoint()) {

      //res = range_strat((ATerm)ATmakeInt(4));
    res = start_nqueens(12);
      //ATprintf("res = %t\n", res);

    fail();
  }

  printf("The End\n");
  
}
