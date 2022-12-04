package org.app;

import org.model.annotations.manager.AbstractModel;
import org.model.annotations.manager.ModelClassAnnotation;
import org.model.annotations.manager.ModelFieldAnnotation;
import org.model.annotations.manager.ModelClassAnnotationType;

@ModelClassAnnotation(id = "Test1", type = ModelClassAnnotationType.WALK, version = 1)
public class ModelExample  extends AbstractModel {

	@ModelFieldAnnotation(id = "Field1", index = 1, size = 20)
	public String alfa;

	@ModelFieldAnnotation(id = "Field2", index = 2, size = 50)
	public String bravo;

	@ModelFieldAnnotation(id = "Field3", index = 3, size = 100)
	public String charley;

	public String delta;

	@ModelFieldAnnotation(id = "Field4", index = 4)
	public int echo;

	@ModelFieldAnnotation(id = "Field5", index = 5)
	public String foxtrot;

	private String juliette; //private Field do not make sense in an Model context
	
}
