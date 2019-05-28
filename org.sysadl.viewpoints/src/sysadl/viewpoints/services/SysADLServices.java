package sysadl.viewpoints.services;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.sysadl.AbstractComponentDef;
import org.sysadl.AbstractConnectorDef;
import org.sysadl.AbstractDef;
import org.sysadl.AbstractPortUse;
import org.sysadl.ActivityFlowable;
import org.sysadl.ActivitySwitch;
import org.sysadl.ActivitySwitchCase;
import org.sysadl.ComponentDef;
import org.sysadl.CompositePortDef;
import org.sysadl.ConnectorBinding;
import org.sysadl.ConnectorDef;
import org.sysadl.ConnectorUse;
import org.sysadl.ConstraintUse;
import org.sysadl.Expression;
import org.sysadl.Model;
import org.sysadl.PortUse;
import org.sysadl.Requirement;
import org.sysadl.Style;
import org.sysadl.SysADLFactory;
import org.sysadl.util.SysADLCreationTools;
import org.sysadl.util.SysADLUtil;

public class SysADLServices {

//	public EObject openTextEditor(EObject any) {
//		if (any != null && any.eResource() instanceof XtextResource
//				&& any.eResource().getURI() != null) {
//
//			String fileURI = any.eResource().getURI().toPlatformString(true);
//			IFile workspaceFile = ResourcesPlugin.getWorkspace().getRoot()
//					.getFile(new Path(fileURI));
//			if (workspaceFile != null) {
//				IWorkbenchPage page = PlatformUI.getWorkbench()
//						.getActiveWorkbenchWindow().getActivePage();
//				try {
//					IEditorPart openEditor = IDE.openEditor(page,
//							workspaceFile,
//							"br.consiste.SysADL",
//							true);
//					if (openEditor instanceof AbstractTextEditor) {
//						ICompositeNode node = NodeModelUtils
//								.findActualNodeFor(any);
//						if (node != null) {
//							int offset = node.getOffset();
//							int length = node.getTotalEndOffset() - offset;
//							((AbstractTextEditor) openEditor).selectAndReveal(
//									offset, length);
//							System.out
//									.println("SysADLServices.openTextEditor()");
//						}
//					}
//					// editorInput.
//				} catch (PartInitException e) {
//					// Put your exception handler here if you wish to.
//				}
//			}
//		}
//		System.out.println(any);
//		return any;
//	}
	
	public EObject inferConnectorType(EObject port1, EObject port2) {
		return null; // TODO
	}
	
	public String nodeText(EObject exp) {
		if (exp==null) return "";
		INode expNode = NodeModelUtils.getNode(exp);
		String value = NodeModelUtils.getTokenText(expNode);
		return  value;
	}
	
	public String constraintUseText(ConstraintUse c) {
		return "<<Constraint>>\n:"+c.getDefinition().getName()+"\n"+nodeText(c.getDefinition().getEquation());
	}
	
	public EList allElements(Model m) {
		EList e = new BasicEList();
		e.addAll(m.getInvolvedElements());
		for (Object _r : m.getRequirements()) {
			Requirement rs = (Requirement) _r;
			
			e.addAll(reqSatisfy(rs));
		}
		return e;
	}
	
	private EList reqSatisfy(Requirement r) {
		EList l = new BasicEList();
		l.addAll(r.getSatisfiedBy());
		for (Object _r : r.getComposition()) {
			Requirement rs = (Requirement) _r;
			
			l.addAll(reqSatisfy(rs));
		}
		return l;
	}
	
	public Boolean isReqSatisfied(Requirement r) {
		if (r.getSatisfiedBy().isEmpty()) {
			if (!r.getComposition().isEmpty() || !r.getDerive().isEmpty()) {
				for (Object sub : r.getComposition()) {
					if (!isReqSatisfied((Requirement)sub))
						return false;
				}
				for (Object sub : r.getDerive()) {
					if (!isReqSatisfied((Requirement)sub))
						return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}
	
	public Expression createNullExpression() {
		return SysADLCreationTools.createNullExpression();
	}
	
	public void setupCase(ActivitySwitchCase a, ActivityFlowable tar) {
		a.setTarget(tar);
		a.setCondition(createNullExpression());
	}
	
	public EObject createCase(ActivitySwitch s, ActivityFlowable tar) {
		ActivitySwitchCase c = SysADLFactory.eINSTANCE.createActivitySwitchCase();
		setupCase(c, tar);
		s.getCases().add(c);
		return c;
	}
	
	public Boolean connectorIsComposite(ConnectorUse c) {
		Boolean b = (c.getDefinition().getComposite() != null);
		PortUse first = ((ConnectorBinding) c.getBindings().get(0)).getSource();
		PortUse second = ((ConnectorBinding)c.getBindings().get(0)).getDestination();
		b = b && (first.getDefinition() instanceof CompositePortDef) && (second.getDefinition() instanceof CompositePortDef);
		return b;
	}
	
	public Collection<EObject> possibleStyle(EObject entry) {
		
		ArrayList<EObject> result = new ArrayList<EObject>();
		org.sysadl.Package p = SysADLUtil.upToPackage(entry);
		Style s = p.getAppliedStyle();
		Class<?> filterClass = null;
		
		if (entry instanceof ComponentDef)  filterClass = AbstractComponentDef.class;
		else if (entry instanceof ConnectorDef) filterClass = AbstractConnectorDef.class;
		else if (entry instanceof PortUse) filterClass = AbstractPortUse.class;
		
		for (AbstractDef d : s.getDefinitions()) {
			if (d.getClass().equals(filterClass)) {
				result.add(d);
			}
		}
		return result;
	}
}
