/*
 * generated by Xtext
 */
package tom.mapping.dsl.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class TomMappingExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return tom.mapping.dsl.ui.internal.TomMappingActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return tom.mapping.dsl.ui.internal.TomMappingActivator.getInstance().getInjector("tom.mapping.dsl.TomMapping");
	}
	
}