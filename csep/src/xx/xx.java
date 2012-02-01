package xx;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.nodemodel.INode;


public class xx {
	public static List<String> offsets(Resource resource) {
		List<String> result = new ArrayList<String>();
		TreeIterator<Object> iterator = EcoreUtil.getAllContents(resource, true);
		while (iterator.hasNext()) {
			InternalEObject child = (InternalEObject)iterator.next();
			INode node = (INode)child.eAdapters().get(0);
			result.add("" + node.getOffset() + ", " + node.getSemanticElement());
		}
		return result;
	}
	
}
