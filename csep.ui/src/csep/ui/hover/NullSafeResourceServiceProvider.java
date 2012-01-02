package csep.ui.hover;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IGlobalServiceProvider.ResourceServiceProviderImpl;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.IResourceServiceProvider.Registry;

import com.google.inject.Inject;

public class NullSafeResourceServiceProvider extends
		ResourceServiceProviderImpl {

	@Inject
	public NullSafeResourceServiceProvider(Registry arg0,
			IResourceServiceProvider arg1) {
		super(arg0, arg1);
	}

	@Override
	public <T> T findService(EObject eObject, Class<T> serviceClazz) {
		if (eObject.eIsProxy()) {
			return findService(((InternalEObject)eObject).eProxyURI(),serviceClazz);
		} 
		else {
			Resource resource = eObject.eResource();
			if (resource == null) {
				return null;
			}
			else {
				return findService(resource.getURI(),serviceClazz);
			}
		}
	}
}
