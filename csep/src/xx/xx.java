package xx;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.parser.IParseResult;


public class xx {
	
	public static List<String> offsets(Object object) {
		if (object instanceof Resource) {
			return resourceOffsets((Resource)object);
		}
		if (object instanceof IParseResult) {
			return parseResultOffsets((IParseResult)object);
		}
		return Arrays.asList(new String[]{"oops " + object.getClass().getCanonicalName()});
	}
	
	public static List<String> resourceOffsets(Resource resource) {
		List<String> result = new ArrayList<String>();
		TreeIterator<Object> iterator = EcoreUtil.getAllContents(resource, true);
		while (iterator.hasNext()) {
			InternalEObject child = (InternalEObject)iterator.next();
			INode node = (INode)child.eAdapters().get(0);
			result.add("" + node.getOffset() + ", " + node.getSemanticElement());
		}
		return result;
	}
	
	public static List<String> parseResultOffsets(IParseResult parseResult) {
		List<String> result = new ArrayList<String>();
		Iterator<INode> iter = parseResult.getRootNode().getAsTreeIterable().iterator();
		while (iter.hasNext()) {
			INode node = iter.next();
			result.add("" + node.getOffset() + ", " + node.getSemanticElement());
		}
		return result;
	}
}
