package csep.scoping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

public class DummyScope implements IScope {
	private IScope parent;
	
	public DummyScope(IScope parent) {
		this.parent = parent;
	}
	
	@Override
	public IEObjectDescription getSingleElement(QualifiedName name) {
		IEObjectDescription description = parent.getSingleElement(name);
		if (description == null)
			description = new DummyEObjectDescription(name);
		return description;
	}

	@Override
	public Iterable<IEObjectDescription> getElements(QualifiedName name) {
		return parent.getElements(name);
	}

	@Override
	public IEObjectDescription getSingleElement(EObject object) {
		return parent.getSingleElement(object);
	}

	@Override
	public Iterable<IEObjectDescription> getElements(EObject object) {
		return parent.getElements(object);
	}

	@Override
	public Iterable<IEObjectDescription> getAllElements() {
		return parent.getAllElements();
	}

}
