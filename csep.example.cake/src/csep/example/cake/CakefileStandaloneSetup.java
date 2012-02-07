
package csep.example.cake;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class CakefileStandaloneSetup extends CakefileStandaloneSetupGenerated{

	public static void doSetup() {
		new CakefileStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

