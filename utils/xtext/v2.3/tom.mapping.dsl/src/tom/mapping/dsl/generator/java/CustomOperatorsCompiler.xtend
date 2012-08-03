package tom.mapping.dsl.generator.java

import org.eclipse.xtext.generator.IFileSystemAccess
import tom.mapping.dsl.generator.TomMappingExtensions
import model.Accessor
import model.Mapping
import model.Operator
import model.UserOperator
import com.google.inject.Inject
import tom.mapping.dsl.generator.tom.ParametersCompiler

class CustomOperatorsCompiler {
	
	extension TomMappingExtensions = new TomMappingExtensions()
	
	@Inject ParametersCompiler injpa
	
	String prefix = ""
	
	
	def compile(Mapping m, IFileSystemAccess fsa) {
		if(m.operators.filter(e | e instanceof UserOperator).size() > 0) {
		fsa.generateFile(prefix+"/"+m.getCustomOperatorsClass()+".java", m.main())
		}
	}
	
	def main(Mapping map) {
		'''	
		public class �map.getCustomOperatorsClass()� {
			�FOR op: map.operators�
				�map.operator(op)�
			�ENDFOR�
			}
		'''
	}
	

	def dispatch operator(Mapping map, Operator op){}
	
	def dispatch operator(Mapping map, UserOperator usop){
		for(a: usop.accessors) {
			usop.accessor(a) 
			}
			usop.test()
			usop.make()
	}
	
	
	def accessor(UserOperator op, Accessor acc) {
		'''
		public static �injpa.javaTerminalType(acc.slot.type)��acc.getCustomOperatorSlotAccessorName()�(�injpa.javaTerminalType(op.type)� t) {
			return �acc.java�;
		}
		'''
	}


	def test(UserOperator usop) {
		'''
		public static boolean is�usop.name.toFirstUpper()�(�injpa.javaTerminalType(usop.type)� t) {
			return �usop.test�;
		}
		'''
	}


	def make(UserOperator usop) {
		'''
		public static �injpa.javaTerminalType(usop.type)� make �usop.name.toFirstUpper()�(�FOR acc: usop.accessors SEPARATOR ","��injpa.javaParameter(acc.slot)��ENDFOR�) {
			return �usop.make�
			}
		'''
	}

}