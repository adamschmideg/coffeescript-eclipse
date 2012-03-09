package csep.tests;

import csep.CoffeeScriptRuntimeModule;
import csep.CoffeeScriptInjectorProvider;
import csep.CoffeeScriptStandaloneSetup;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectorProviderCustom extends CoffeeScriptInjectorProvider {
	
		private Injector injector = new CoffeeScriptStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(new CoffeeScriptRuntimeModule() {
				});
			}
		}.createInjectorAndDoEMFRegistration();;

		public Injector getInjector() {
			return injector;
		}
	
}
