package org.palladiosimulator.pcm.prolog.design;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.Caller;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.Operation;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.OperationCall;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.PropertyDefinition;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.SystemUsage;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.Variable;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.VariableAssignment;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.provider.extension.*;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.provider.OperationItemProvider;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.provider.PrologmodelItemProviderAdapterFactory;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.provider.SystemUsageItemProvider;
//import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.provider.PropertyDefinitionItemProvider;
//import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.provider.VariableAssignmentItemProvider;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.util.PrologmodelAdapterFactory;

/**
 * The services class used by VSM.
 */
public class Services {
    
    /**
    * See http://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.sirius.doc%2Fdoc%2Findex.html&cp=24 for documentation on how to write service methods.
    */
    public EObject myService(EObject self, String arg) {
       // TODO Auto-generated code
      return self;
    }
    
    public String getOperationName(Operation op) {
    	OperationItemProvider  itemProvider = new OperationItemProvider(new PrologmodelItemProviderAdapterFactory());
    	return itemProvider.getText(op);
    }
    
    public String getPreCallAssignment(OperationCall opCall) {
    	String returnValue="";
    	
    	for(VariableAssignment preCallStateVar : opCall.getPreCallStateDefinitions()) {
    		returnValue += preCallStateVar.getVariable().getName() + "\n";
    	}    	
    	return returnValue;
    }
    
    public String operationCallNumber(OperationCall opCall) {
    	Operation callee = opCall.getCallee();
    	Caller caller = opCall.getCaller();
    	for(int i =0; i<caller.getCalls().size(); i++) {
    		if(caller.getCalls().get(i).getCallee().equals(callee)) {
    			return Integer.toString(i+1);
    		}
    	}
    	return "0";
    }

    
    public String returnValueAssignment(OperationCall opCall) {
    	String returnValue="";
    		VariableAssignmentItemProvider itemProvider = new VariableAssignmentItemProvider(new PrologmodelAdapterFactory());
    		for(VariableAssignment var : opCall.getCallee().getReturnValueAssignments()) {
				returnValue += itemProvider.getText(var) + "\n";
    		}
    	return returnValue;
    }

    public String getPropertyDefinitionText(PropertyDefinition property) {
    	PropertyDefinitionItemProvider definitionItemProvider = new PropertyDefinitionItemProvider(new PrologmodelAdapterFactory());
    	return definitionItemProvider.getText(property);
    }
    
    public String getSystemUsagesText(SystemUsage usage) {
    	SystemUsageItemProvider itemProvider = new SystemUsageItemProvider(new PrologmodelAdapterFactory());
    	return itemProvider.getText(usage);
    }
    
    public String getReturnValueText(Variable var) {
    	VariableItemProvider varItemProvider = new VariableItemProvider(new PrologmodelAdapterFactory());
    	return varItemProvider.getText(var);
    }
}
