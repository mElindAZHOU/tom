/**
 *
 * The TomSyntaxChecker plugin.
 *
 */

package jtom.checker;

import aterm.*;
import jtom.*;
import jtom.adt.tomsignature.types.*;

public class TomSyntaxChecker extends TomChecker implements TomPlugin
{
    %include { ../adt/TomSignature.tom }

    private TomTerm term;
    private OptionList myOptions;

    public TomSyntaxChecker()
    {
	myOptions = `concOption(OptionBoolean("checkSyntax","","",True()) // activationFlag
				);
    }

    public void setInput(ATerm term)
    {
	if (term instanceof TomTerm)
	    this.term = (TomTerm)term;
	else
	    environment().messageError(TomMessage.getString("TomTermExpected"),
				       "TomSyntaxChecker", TomMessage.DEFAULT_ERROR_LINE_NUMBER);
    }

    public ATerm getOutput()
    {
	return term;
    }

    public void run()
    {
	try
	    {	
		long startChrono = System.currentTimeMillis();
		boolean verbose = ((Boolean)getServer().getOptionValue("verbose")).booleanValue();
		
		reinit();

		checkSyntax(term);

		if(verbose)
		    System.out.println("TOM syntax checking phase (" +(System.currentTimeMillis()-startChrono)+ " ms)");

		environment().printAlertMessage("TomSyntaxChecker");
		if(!environment().isEclipseMode()) {
		    // remove all warning (in command line only)
		    environment().clearWarnings();
		}
	    }
	catch (Exception e)
	    {
		environment().messageError("Exception occurs in TomSyntaxChecker: "+e.getMessage(), 
					   environment().getInputFile().getName(), TomMessage.DEFAULT_ERROR_LINE_NUMBER);
		e.printStackTrace();
	    }
    }

    public OptionList declareOptions()
    {
// 	int i = 0;
// 	OptionList list = `concOption(myOptions*);
// 	while(!(list.isEmpty()))
// 	    {
// 		i++;
// 		list = list.getTail();
// 	    }

// 	System.out.println("1.3. The syntax checker declares " +i+ " options.");
	return myOptions;
    }

    public OptionList requiredOptions()
    {
	return `emptyOptionList();
    }

    public void setOption(String optionName, String optionValue)
    {
 	%match(OptionList myOptions)
 	    {
		concOption(av*, OptionBoolean(n, alt, desc, val), ap*)
		    -> { if(n.equals(optionName)||alt.equals(optionName))
			{
			    %match(String optionValue)
				{
				    ('true') ->
					{ myOptions = `concOption(av*, ap*, OptionBoolean(n, alt, desc, True())); }
				    ('false') ->
					{ myOptions = `concOption(av*, ap*, OptionBoolean(n, alt, desc, False())); }
				}
			}
		}
		concOption(av*, OptionInteger(n, alt, desc, val, attr), ap*)
		    -> { if(n.equals(optionName)||alt.equals(optionName))
			myOptions = `concOption(av*, ap*, OptionInteger(n, alt, desc, Integer.parseInt(optionValue), attr));
		}
		concOption(av*, OptionString(n, alt, desc, val, attr), ap*)
		    -> { if(n.equals(optionName)||alt.equals(optionName))
			myOptions = `concOption(av*, ap*, OptionString(n, alt, desc, optionValue, attr));
		}
	    }
    }
}
