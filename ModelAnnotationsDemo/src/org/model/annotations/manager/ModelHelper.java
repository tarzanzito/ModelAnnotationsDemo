package org.model.annotations.manager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ModelHelper<T> {

	//Fields
	
	private String modelId;
	private int modelVersion;
	private  ModelClassAnnotationType modelType;
	
	private ModelHelperFieldInfo[] modelHelperFieldInfoArray;
	private Class<T> tClazz;

	//Getters
	
	public String getModelId() {
		return modelId;
	}

	public int getModelVersion() {
		return modelVersion;
	}

	public ModelClassAnnotationType getModelType() {
		return modelType;
	}

	public ModelHelperFieldInfo[] getModelHelperFieldInfoArray() {
      return modelHelperFieldInfoArray;
    }
    
	//Constructors
	
	public ModelHelper() throws Exception {

		//inspecAllModel(); //cannot because 'tClazz' is null yet, use identify method
	}

	//Public Methods

	public void identify(Class<T> clazz) throws Exception {

		Class<T> xpto = (Class<T>) this.getClass();
		
		this.tClazz = clazz;
		
		inspecAllModel();
	}
	
	public ModelHelper<T> inspectModel() throws Exception {
				
		inspecAllModel();

		return this;
	}

	public T getNewModelInstance() {

		Class<T> classT = getT();
		
		T newInsrance = null;

		try {
			if (classT == null)
				throw new Exception("Execute 'identify' method before.");

			newInsrance = classT.newInstance();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return newInsrance;
	}

	public T getNewModelInstanceWithoutIndentiry() { //try to solve T.gwtName() 

		// tries ----------------------
		// C#
		// 1 - return = new T();
		// 2 - return (T)Activator.CreateInstance(typeof(T));

		// Problem :
		// 1- from T knowing the class name ?????????

		// String className = "T.getName()";
		
		String className = "org.app.ModelExample"; // como obter esta info vindo de T !!!!!

		T newIns = null;

		try {

			Class<?> clazz = Class.forName(className);

			Object obj = clazz.newInstance();
			newIns = (T) obj;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return newIns;
	}

	public void setModelValueByFieldId(T model, String id, Object value) throws Exception {

		for (ModelHelperFieldInfo item : modelHelperFieldInfoArray) {
			
			if (item.getId().equals(id)) {
				setFieldValue(model, item.getField(), value);
				return;
			}
		}
		
		throw new Exception("Field Annotation 'ID':'" + id + "' not found in '" + model.getClass().getName() + "'.");
	}
	            
	public void setModelValueByFieldName(T model, String name, Object value) throws Exception {
		
		for (ModelHelperFieldInfo item : modelHelperFieldInfoArray) {
			
			if (item.getField().getName().equals(name)) {
				setFieldValue(model, item.getField(), value);
				return;
			}
		}
		
		throw new Exception("Field:'" + name + "' not found in '" + model.getClass().getName() + "'.");
	}
	
	public ModelHelperFieldInfo getFieldAnnotationByName(String name) throws Exception {

		for (ModelHelperFieldInfo item : modelHelperFieldInfoArray) {
			
			if (item.getField().getName().equals(name))
				return item;
		}
		
		throw new Exception("Field Name:'" + name + "' not found.");
	}

	public ModelHelperFieldInfo getFieldAnnotationById(String id) throws Exception {

		for (ModelHelperFieldInfo item : modelHelperFieldInfoArray) {
			
			if (item.getId().equals(id))
				return item;
		}
		
		throw new Exception("Field Annotation Id:'" + id + "' not found.");
	}

	
	//Private Methods
	
	private void inspecAllModel() throws Exception {

		getModelClassInfo();
		getAllModelFieldInfo();
	}

	private Class<T> getT() {

		return this.tClazz;

		// the BIG problem : (from T get className)
//		String className = "org.app.ModelExample";
//
//		Class<?> classT = null;
//		try {
//
//			classT = Class.forName(className); // TODO
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}

	}
	
	private void getModelClassInfo() throws Exception {

		Class<T> clazz = getT();

		if (!clazz.isAnnotationPresent(ModelClassAnnotation.class))
			throw new Exception("Class '" + clazz.getSimpleName() + "' does not contains '" + ModelClassAnnotation.class
					+ "' Annotation.");;
		
		ModelClassAnnotation modelClassAnnotation = clazz.getAnnotation(ModelClassAnnotation.class);
		
//		Annotation[] annotationArray = clazz.getAnnotations();
//
//		ModelClassAnnotation modelClassAnnotation = null;
//
//		for (Annotation item : annotationArray) {
//			
//			if (item.annotationType().equals(ModelClassAnnotation.class)) {
//				modelClassAnnotation = (ModelClassAnnotation) item;
//				break;
//			}
//		}
//
//		if (modelClassAnnotation == null)
//			throw new Exception("Class '" + clazz.getSimpleName() + "' does not contains '" + ModelClassAnnotation.class
//					+ "' Annotation.");

		this.modelId = modelClassAnnotation.id();
		this.modelVersion = modelClassAnnotation.version();
		this.modelType = modelClassAnnotation.type();
	}

	private void getAllModelFieldInfo() throws Exception {

		Class<T> classT = this.getT();

		List<ModelHelperFieldInfo> modelHelperFieldInfoList = new ArrayList<>();

		Field[] fieldArray = classT.getDeclaredFields();

		for (Field item : fieldArray) {
			
			ModelHelperFieldInfo fieldInfo = getMoldelHelperFieldInfo(item);
			if (fieldInfo != null)
				modelHelperFieldInfoList.add(fieldInfo);
		}

		if (modelHelperFieldInfoList.isEmpty())
			throw new Exception(
					"Class '{typeof(T).Name}' does not contains any field with'{nameof(ModelFieldAttribute)}'.");

		this.modelHelperFieldInfoArray = (ModelHelperFieldInfo[]) modelHelperFieldInfoList.toArray();
	}

	private ModelHelperFieldInfo getMoldelHelperFieldInfo(Field field) {

		if (!field.isAnnotationPresent(ModelFieldAnnotation.class))
			return null;
		
		ModelFieldAnnotation modelFieldAnnotation = field.getAnnotation(ModelFieldAnnotation.class);
		
//		Annotation[] annotationArray = field.getAnnotations();
//
//		ModelFieldAnnotation modelFieldAnnotation = null;
//
//		for (Annotation item : annotationArray) {
//			
//			if (item.annotationType().equals(ModelFieldAnnotation.class)) {
//				modelFieldAnnotation = (ModelFieldAnnotation) item;
//				break;
//			}
//		}
//
		ModelHelperFieldInfo modelHelperFieldInfo = null;
		
		if (modelFieldAnnotation != null) {
			modelHelperFieldInfo = new ModelHelperFieldInfo(modelFieldAnnotation.id(),
					field.getName(),
					modelFieldAnnotation.index(),
					modelFieldAnnotation.size(),
					field);
		}

		return modelHelperFieldInfo;
	}

	private void setFieldValue(T model, Field field, Object value) throws Exception {
		
		field.set(model, value);
		//try {
		//	field.set(model, value);
		//} catch (IllegalArgumentException | IllegalAccessException e) {
			//Class<?> classValue = value.getClass();
			//Class<?> typeField = field.getType();
		//}
	}

//	private void runMethodsExample() {
//		
//		Method[] methods = this.getClass().getDeclaredMethods();
//		for(Method item : methods) {
//			item.invoke(obj, args)
//		}
//			
//	}
}
