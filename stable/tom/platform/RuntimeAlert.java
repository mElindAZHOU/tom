/* Generated by TOM (version 2.6): Do not edit this file *//*
 * 
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2008, INRIA
 * Nancy, France.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
 * 
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 *
 **/

package tom.platform;

import aterm.pure.*;
import java.util.logging.*;

import tom.platform.adt.platformalert.*;
import tom.platform.adt.platformalert.types.*;

public class RuntimeAlert {
  
  /* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file */  /* Generated by TOM (version 2.6): Do not edit this file */    public static   tom.platform.adt.platformalert.types.AlertList  tom_append_list_concAlert( tom.platform.adt.platformalert.types.AlertList l1,  tom.platform.adt.platformalert.types.AlertList  l2) {     if( l1.isEmptyconcAlert() ) {       return l2;     } else if( l2.isEmptyconcAlert() ) {       return l1;     } else if(  l1.getTailconcAlert() .isEmptyconcAlert() ) {       return  tom.platform.adt.platformalert.types.alertlist.ConsconcAlert.make( l1.getHeadconcAlert() ,l2) ;     } else {       return  tom.platform.adt.platformalert.types.alertlist.ConsconcAlert.make( l1.getHeadconcAlert() ,tom_append_list_concAlert( l1.getTailconcAlert() ,l2)) ;     }   }   public static   tom.platform.adt.platformalert.types.AlertList  tom_get_slice_concAlert( tom.platform.adt.platformalert.types.AlertList  begin,  tom.platform.adt.platformalert.types.AlertList  end, tom.platform.adt.platformalert.types.AlertList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcAlert()  ||  (end== tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.platform.adt.platformalert.types.alertlist.ConsconcAlert.make( begin.getHeadconcAlert() ,( tom.platform.adt.platformalert.types.AlertList )tom_get_slice_concAlert( begin.getTailconcAlert() ,end,tail)) ;   }    

  private AlertList errors;
  private AlertList warnings;
  private int nbErrors;
  private int nbWarnings;

  public RuntimeAlert() {
    errors =  tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() ;
    warnings =  tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() ;
    nbErrors = 0;
    nbWarnings = 0;
  }

  /**
   * Add the warning only if it is not already in the list 
   */
  public void addWarning(String message, String file, int line) {
    Alert entry =  tom.platform.adt.platformalert.types.alert.Warning.make(message, file, line) ;
    {{ Object tomMatch628NameNumber_freshVar_0=entry;if ( (tomMatch628NameNumber_freshVar_0 instanceof tom.platform.adt.platformalert.types.Alert) ) {{  tom.platform.adt.platformalert.types.Alert  tomMatch628NameNumber_freshSubject_1=(( tom.platform.adt.platformalert.types.Alert )tomMatch628NameNumber_freshVar_0);{ Object tomMatch628NameNumber_freshVar_1=warnings;if ( (tomMatch628NameNumber_freshVar_1 instanceof tom.platform.adt.platformalert.types.AlertList) ) {{  tom.platform.adt.platformalert.types.AlertList  tomMatch628NameNumber_freshSubject_2=(( tom.platform.adt.platformalert.types.AlertList )tomMatch628NameNumber_freshVar_1);{ boolean tomMatch628NameNumber_freshVar_9= false ;{  tom.platform.adt.platformalert.types.AlertList  tomMatch628NameNumber_freshVar_2=tomMatch628NameNumber_freshSubject_2;if ( ((tomMatch628NameNumber_freshVar_2 instanceof tom.platform.adt.platformalert.types.alertlist.ConsconcAlert) || (tomMatch628NameNumber_freshVar_2 instanceof tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert)) ) {{  tom.platform.adt.platformalert.types.AlertList  tomMatch628NameNumber_begin_4=tomMatch628NameNumber_freshVar_2;{  tom.platform.adt.platformalert.types.AlertList  tomMatch628NameNumber_end_5=tomMatch628NameNumber_freshVar_2;do {{{  tom.platform.adt.platformalert.types.AlertList  tomMatch628NameNumber_freshVar_3=tomMatch628NameNumber_end_5;if (!( tomMatch628NameNumber_freshVar_3.isEmptyconcAlert() )) {{  tom.platform.adt.platformalert.types.Alert  tomMatch628NameNumber_freshVar_8= tomMatch628NameNumber_freshVar_3.getHeadconcAlert() ;if ( (tomMatch628NameNumber_freshVar_8==tomMatch628NameNumber_freshSubject_1) ) {{  tom.platform.adt.platformalert.types.AlertList  tomMatch628NameNumber_freshVar_6= tomMatch628NameNumber_freshVar_3.getTailconcAlert() ;tomMatch628NameNumber_freshVar_9= true ;}}}}}if ( tomMatch628NameNumber_end_5.isEmptyconcAlert() ) {tomMatch628NameNumber_end_5=tomMatch628NameNumber_begin_4;} else {tomMatch628NameNumber_end_5= tomMatch628NameNumber_end_5.getTailconcAlert() ;}}} while(!( (tomMatch628NameNumber_end_5==tomMatch628NameNumber_begin_4) ));}}}}if ((tomMatch628NameNumber_freshVar_9 ==  false )) {

        warnings =  tom.platform.adt.platformalert.types.alertlist.ConsconcAlert.make(entry,tom_append_list_concAlert(warnings, tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() )) ;
        nbWarnings++;   
      }}}}}}}}}
    
  }
  
  /**
   * Add the error only if it is not already in the list 
   */
  public void addError(String message, String file, int line) {
    Alert entry =  tom.platform.adt.platformalert.types.alert.Error.make(message, file, line) ;
    {{ Object tomMatch629NameNumber_freshVar_0=entry;if ( (tomMatch629NameNumber_freshVar_0 instanceof tom.platform.adt.platformalert.types.Alert) ) {{  tom.platform.adt.platformalert.types.Alert  tomMatch629NameNumber_freshSubject_1=(( tom.platform.adt.platformalert.types.Alert )tomMatch629NameNumber_freshVar_0);{ Object tomMatch629NameNumber_freshVar_1=errors;if ( (tomMatch629NameNumber_freshVar_1 instanceof tom.platform.adt.platformalert.types.AlertList) ) {{  tom.platform.adt.platformalert.types.AlertList  tomMatch629NameNumber_freshSubject_2=(( tom.platform.adt.platformalert.types.AlertList )tomMatch629NameNumber_freshVar_1);{ boolean tomMatch629NameNumber_freshVar_9= false ;{  tom.platform.adt.platformalert.types.AlertList  tomMatch629NameNumber_freshVar_2=tomMatch629NameNumber_freshSubject_2;if ( ((tomMatch629NameNumber_freshVar_2 instanceof tom.platform.adt.platformalert.types.alertlist.ConsconcAlert) || (tomMatch629NameNumber_freshVar_2 instanceof tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert)) ) {{  tom.platform.adt.platformalert.types.AlertList  tomMatch629NameNumber_begin_4=tomMatch629NameNumber_freshVar_2;{  tom.platform.adt.platformalert.types.AlertList  tomMatch629NameNumber_end_5=tomMatch629NameNumber_freshVar_2;do {{{  tom.platform.adt.platformalert.types.AlertList  tomMatch629NameNumber_freshVar_3=tomMatch629NameNumber_end_5;if (!( tomMatch629NameNumber_freshVar_3.isEmptyconcAlert() )) {{  tom.platform.adt.platformalert.types.Alert  tomMatch629NameNumber_freshVar_8= tomMatch629NameNumber_freshVar_3.getHeadconcAlert() ;if ( (tomMatch629NameNumber_freshVar_8==tomMatch629NameNumber_freshSubject_1) ) {{  tom.platform.adt.platformalert.types.AlertList  tomMatch629NameNumber_freshVar_6= tomMatch629NameNumber_freshVar_3.getTailconcAlert() ;tomMatch629NameNumber_freshVar_9= true ;}}}}}if ( tomMatch629NameNumber_end_5.isEmptyconcAlert() ) {tomMatch629NameNumber_end_5=tomMatch629NameNumber_begin_4;} else {tomMatch629NameNumber_end_5= tomMatch629NameNumber_end_5.getTailconcAlert() ;}}} while(!( (tomMatch629NameNumber_end_5==tomMatch629NameNumber_begin_4) ));}}}}if ((tomMatch629NameNumber_freshVar_9 ==  false )) {

        errors =  tom.platform.adt.platformalert.types.alertlist.ConsconcAlert.make(entry,tom_append_list_concAlert(errors, tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() )) ;
        nbErrors++;    
      }}}}}}}}}
    
  }
  
  public int getNbErrors() {
    return nbErrors;
  }

  public int getNbWarnings() {
    return nbWarnings;
  }

  public boolean hasErrors() {
    return (nbErrors>0);
  }

  public boolean hasWarnings() {
    return (nbWarnings>0);
  }

  public AlertList getErrors() {
    return errors;
  }

  public AlertList getWarnings() {
    return warnings;
  }
  
  public void concat(RuntimeAlert newErrors) {
    if(newErrors.getErrors() !=  tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() ) {
      AlertList newAlerts = newErrors.getErrors();
      errors = tom_append_list_concAlert(newAlerts,tom_append_list_concAlert(errors, tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() ));
      nbErrors += newErrors.getNbErrors();      
    }
    if(newErrors.getWarnings() !=  tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() ) {
      AlertList newAlerts = newErrors.getWarnings();
      warnings = tom_append_list_concAlert(newAlerts,tom_append_list_concAlert(warnings, tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() ));
      nbWarnings += newErrors.getNbWarnings();
    }
  }

  /**
   * @param record
   */
  public void add(PlatformLogRecord record) {
    
	PlatformFormatter formatter = new PlatformFormatter();   
	  
	if(record.getLevel() == Level.SEVERE) {
      addError(formatter.formatMessage(record), record.getFilePath(), record.getLine());
    } else if(record.getLevel() == Level.WARNING) {
      addWarning(formatter.formatMessage(record), record.getFilePath(), record.getLine());
    }
  }

} //class RuntimeAlert
