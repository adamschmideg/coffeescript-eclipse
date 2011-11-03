
package csep;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class CoffeeScriptStandaloneSetup extends CoffeeScriptStandaloneSetupGenerated{

	public static void doSetup() {
		new CoffeeScriptStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

