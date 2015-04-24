package de.qabel.core.config;

import java.lang.reflect.Field;

import com.google.gson.JsonParseException;

class DeserializedValidator {
	static void validate(Object obj) throws JsonParseException{
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field : fields) {
			if (!field.isAnnotationPresent(RequiredInJson.class))
				continue;
			
			field.setAccessible(true);
			try {
				if (field.get(obj) == null) {
					throw new JsonParseException("In JSON object \"" + obj.getClass()
							+ "\" the field \"" + field.getName() + "\" is missing.");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
