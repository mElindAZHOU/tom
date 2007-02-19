package tom.engine.compiler;

import tom.engine.TomBase;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomconstraint.types.*;

import tom.engine.compiler.propagator.*;

/**
 * This class is in charge with launching all the propagators,
 * until no more propagations can be made 
 */
public class TomPropagationManager extends TomBase {
	
	%include { adt/tomsignature/TomSignature.tom }	
	
	private static final String propagatorsPackage = "tom.engine.compiler.propagator";
	
	private static final String[] propagatorsNames = {"TomSyntacticPropagator"};
	
	public static Constraint performPropagations(Constraint constraintToCompile) 
			throws ClassNotFoundException,InstantiationException,IllegalAccessException{
		
		// counts the propagators that didn't change the expression
		short propCounter = 0;
		Constraint result = null;		
		
		// iterate until all propagators are applied and nothing was changed 
		mainLoop: while(true){		
			for(String i:propagatorsNames){
				
				TomIBasePropagator prop = (TomIBasePropagator)Class.forName(propagatorsPackage + i).newInstance();
				result = prop.propagate(constraintToCompile);
				// if nothing was done, start counting 
				if (result == constraintToCompile){
					propCounter++;
				}else{
					// reset counter
					propCounter = 0;
				}
				
				// if we applied all the propagators and nothing changed,
				// it's time to stop
				if (propCounter == propagatorsNames.length) { break mainLoop; }
				// reinitialize
				constraintToCompile = result;
			}
		} // end while
		return result;
	}	
}