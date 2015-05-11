package de.qabel.core.config;

import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class DeserializationValidatorTest {
	
	@Test(expected=JsonParseException.class)
	public void requiredItemMissingTest() throws IOException, JsonParseException, IllegalArgumentException, IllegalAccessException {
		// Test string with missing item "auth":
		String testString = "{\"provider\":\"provider\",\"user\":\"user\"}";
		Gson gson = new Gson();
		Account testAccount = gson.fromJson(testString, Account.class);
		DeserializedValidator.validate(testAccount);
	}

	@Test
	public void notRequiredItemMissingTest() {
		// Test string with missing item "user":
		String testString = "{\"provider\":\"provider\",\"auth\":\"auth\"}";
		Gson gson = new Gson();
		Account testAccount = gson.fromJson(testString, Account.class);
		DeserializedValidator.validate(testAccount);
	}
}
