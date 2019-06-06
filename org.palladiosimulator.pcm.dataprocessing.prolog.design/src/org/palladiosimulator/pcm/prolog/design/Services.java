package org.palladiosimulator.pcm.prolog.design;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.Caller;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.Operation;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.OperationCall;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.PropertyDefinition;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.SystemUsage;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.Variable;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.VariableAssignment;

/**
 * The services class used by VSM.
 */
public class Services {

	public String getOperationName(Operation op) {
		return getText(op);
	}

	public String getPreCallAssignment(OperationCall opCall) {
		String returnValue = "";

		for (VariableAssignment preCallStateVar : opCall.getPreCallStateDefinitions()) {
			returnValue += preCallStateVar.getVariable().getName() + "\n";
		}
		return returnValue;
	}

	public String operationCallNumber(OperationCall opCall) {
		Operation callee = opCall.getCallee();
		Caller caller = opCall.getCaller();
		for (int i = 0; i < caller.getCalls().size(); i++) {
			if (caller.getCalls().get(i).getCallee().equals(callee)) {
				return Integer.toString(i + 1);
			}
		}
		return "0";
	}

	public String returnValueAssignment(OperationCall opCall) {
		String returnValue = "";
		for (VariableAssignment var : opCall.getCallee().getReturnValueAssignments()) {
			returnValue += getText(var) + "\n";
		}
		return returnValue;
	}

	public String getPropertyDefinitionText(PropertyDefinition property) {
		return getText(property);
	}

	public String getSystemUsagesText(SystemUsage usage) {
		return getText(usage);
	}

	public String getReturnValueText(Variable var) {
		return getText(var);
	}

	private String getText(EObject eobject) {
		return getItemProvider(eobject).getText(eobject);
	}

	private ILabelProvider getItemProvider(EObject eobject) {
		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(composedAdapterFactory);
		return labelProvider;
	}
}
