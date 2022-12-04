package org.model.annotations.manager;

import java.lang.reflect.Field;

public class ModelHelperFieldInfo {

    private String id;
    private String name;
    private int index;
    private int size;
    private Field field;

    public String getId() {
		return id;
	}
    
    public String getName() {
		return name;
	}
	public int getIndex() {
		return index;
	}

	public int getSize() {
		return size;
	}

	public Field getField() {
		return field;
	}

	public ModelHelperFieldInfo(String id, String name, int index, int size, Field field)
    {
        this.id = id;
        this.name = name;
        this.index = index;
        this.size = size;
        this.field = field;
    }
}
