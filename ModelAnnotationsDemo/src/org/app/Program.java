package org.app;

import org.model.annotations.manager.ModelHelper;
import org.model.annotations.manager.ModelHelperFieldInfo;

public class Program {

	public static void main(String[] args) throws Exception {
		 
		ModelHelper<ModelExample> modelHelper = new ModelHelper<>();
		modelHelper.identify(ModelExample.class);
		
		modelHelper.inspectModel();
		
		ModelExample modelExample1 = modelHelper.getNewModelInstance();
		ModelExample modelExample2 = modelHelper.getNewModelInstance();
		
        ///////////////////////////
        //5-Set Values In Fields //
        ///////////////////////////
		ModelExample newModel = modelHelper.getNewModelInstance();
		                                               
        modelHelper.setModelValueByFieldId(newModel, "Field1", "abcd");
        modelHelper.setModelValueByFieldName(newModel, "bravo", "zulus");

        modelHelper.setModelValueByFieldId(newModel, "Field4", 30);
        modelHelper.setModelValueByFieldName(newModel, "echo", 25);
		
        ModelHelperFieldInfo modelHelperFieldInfo1 = modelHelper.getFieldAnnotationById("Field5");
        ModelHelperFieldInfo modelHelperFieldInfo2 = modelHelper.getFieldAnnotationByName("echo");
        
        System.out.println("Fim");
	}

}
