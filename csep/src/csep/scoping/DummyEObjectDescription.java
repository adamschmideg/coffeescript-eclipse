package csep.scoping;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;

public class DummyEObjectDescription implements IEObjectDescription {
	private QualifiedName qname;
	private EObject obj;
	
	public DummyEObjectDescription(QualifiedName qname) {
		this.qname = qname;
		this.obj = new DummyIdImpl(qname.toString());
	}
	
	@Override
	public QualifiedName getName() {
		return qname;
	}

	@Override
	public QualifiedName getQualifiedName() {
		return qname;
	}

	@Override
	public EObject getEObjectOrProxy() {
		return obj;
	}

	@Override
	public URI getEObjectURI() {
		return null;
	}

	@Override
	public EClass getEClass() {
		return obj.eClass();
	}

	@Override
	public String getUserData(String key) {
		return null;
	}

	@Override
	public String[] getUserDataKeys() {
		return null;
	}

}
