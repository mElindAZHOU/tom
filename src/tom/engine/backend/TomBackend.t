package jtom.backend;

import java.io.*;

import aterm.*;
import jtom.*;
import jtom.adt.tomsignature.types.*;
import jtom.adt.options.types.*;
import jtom.tools.*;

/**
 * The TomBackend plugin.
 */
public class TomBackend extends TomGenericPlugin //Base implements TomPlugin
{
    %include { ../adt/TomSignature.tom }
    %include{ ../adt/Options.tom }

//     private TomTerm term;
//     private TomOptionList myOptions;

    private final static int defaultDeep = 2;
    private TomAbstractGenerator generator;
    private Writer writer;


    public TomBackend()
    {
	myOptions = `concTomOption(OptionBoolean("noOutput", "", "Do not generate code", False()), // desactivation flag
				OptionBoolean("jCode", "j", "Generate Java code", True()),
				OptionBoolean("cCode", "c", "Generate C code", False()),
				OptionBoolean("eCode", "e", "Generate Eiffel code", False()),
				OptionBoolean("camlCode", "", "Generate Caml code", False()));
    }

//     public void setInput(ATerm term)
//     {
// 	if (term instanceof TomTerm)
// 	    this.term = (TomTerm)term;
// 	else
// 	    environment().messageError(TomMessage.getString("TomTermExpected"),
// 				       "TomParserPlugin", TomMessage.DEFAULT_ERROR_LINE_NUMBER);
//     }

//     public ATerm getOutput()
//     {
// 	return term;
//     }

    public void run()
    {
      if(isActivated() == true)
	    {
		try
		    {
			long startChrono = System.currentTimeMillis();
			//TomOptionList list = `concTomOption(myOptions*);
			boolean verbose = getServer().getOptionBooleanValue("verbose");
			
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(environment().getOutputFile())));
			
			OutputCode output = new OutputCode(writer, defaultDeep);
			
			if( getServer().getOptionBooleanValue("jCode") )
			    generator = new TomJavaGenerator(output);
			else if( getServer().getOptionBooleanValue("cCode") )
			    generator = new TomCGenerator(output);
			else if( getServer().getOptionBooleanValue("eCode") )
			    generator = new TomEiffelGenerator(output);
			else if( getServer().getOptionBooleanValue("camlCode") )
			    generator = new TomCamlGenerator(output);

// 			while(!(list.isEmpty()))
// 			    {
// 				TomOption h = list.getHead();
// 				%match(TomOption h)
// 				    {
// 					OptionBoolean[name="jCode", valueB=True()] -> 
// 					    { 
// 						generator = new TomJavaGenerator(output);
// 						break;
// 					    }
// 					OptionBoolean[name="cCode", valueB=True()] -> 
// 					    { 
// 						generator = new TomCGenerator(output);
// 						break;
// 					    }
// 					OptionBoolean[name="eCode", valueB=True()] -> 
// 					    { 
// 						generator = new TomEiffelGenerator(output);
// 						break;
// 					    }
// 					OptionBoolean[name="camlCode", valueB=True()] -> 
// 					    { 
// 						generator = new TomCamlGenerator(output);
// 						break;
// 					    }
// 				    }
// 				list = list.getTail();
// 			    }
			
			generator.generate(defaultDeep, term);
			
			if(verbose)
			    System.out.println("TOM generation phase (" +(System.currentTimeMillis()-startChrono)+ " ms)");

			writer.close();

			environment().printAlertMessage("TomBackend");
			if(!environment().isEclipseMode()) {
			    // remove all warning (in command line only)
			    environment().clearWarnings();
			}
		    }
		catch (Exception e)
		    {
			environment().messageError("Exception occurs in TomBackend Init: "+e.getMessage(), 
						   environment().getInputFile().getName(), TomMessage.DEFAULT_ERROR_LINE_NUMBER);
			e.printStackTrace();
		    }
	    }
	else // backend desactivated
	    {
		boolean verbose = getServer().getOptionBooleanValue("verbose");
		
		if(verbose)
		    {
			System.out.println("The backend is not activated and thus WILL NOT RUN.");
			System.out.println("No output !");
		    }
	    }
    }

//     public TomOptionList declareOptions()
//     {
// 	return myOptions;
//     }

//     public TomOptionList requiredOptions()
//     {
// 	return `emptyTomOptionList();
//     }

  public void setOption(String optionName, String optionValue) {
    String type = getServer().getOptionsType(optionName);
    if( type == "boolean")
      putOptionValue(optionName, new Boolean(optionValue));
    else if( type == "integer")
      putOptionValue(optionName, new Integer(optionValue));
    else if( type == "string")
      putOptionValue(optionName, optionValue);

    if(optionValue.equals("true")) // no more than 1 type of code can be activated at a time
	{
	    if( optionName.equals("jCode") || optionName.equals("j") )
		{ 
		    //System.out.println("Java code activated, other codes desactivated");
		    putOptionValue("cCode", Boolean.FALSE);
		    putOptionValue("eCode", Boolean.FALSE);
		    putOptionValue("camlCode", Boolean.FALSE); 
		}
	    else if( optionName.equals("cCode") || optionName.equals("c") )
		{ 
		    //System.out.println("C code activated, other codes desactivated");
		    putOptionValue("jCode", Boolean.FALSE);
		    putOptionValue("eCode", Boolean.FALSE);
		    putOptionValue("camlCode", Boolean.FALSE); 
		}
	    else if( optionName.equals("eCode") || optionName.equals("e") )
		{ 
		    //System.out.println("Eiffel code activated, other codes desactivated");
		    putOptionValue("jCode", Boolean.FALSE);
		    putOptionValue("cCode", Boolean.FALSE);
		    putOptionValue("camlCode", Boolean.FALSE); 
		}
	    else if( optionName.equals("camlCode") )
		{ 
		    //System.out.println("Caml code activated, other codes desactivated");
		    putOptionValue("jCode", Boolean.FALSE);
		    putOptionValue("cCode", Boolean.FALSE);
		    putOptionValue("eCode", Boolean.FALSE); 
		}
	}
    }


  private boolean isActivated() {
    return !getServer().getOptionBooleanValue("noOutput");
  }
}
