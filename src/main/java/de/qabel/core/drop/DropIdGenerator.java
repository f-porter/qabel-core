package de.qabel.core.drop;

import java.util.Arrays;

import org.bouncycastle.util.encoders.UrlBase64;

public abstract class DropIdGenerator {
	public static final int DROP_ID_LENGTH = 43;
	public static final int DROP_ID_LENGTH_BYTE = 32;

	public static DropIdGenerator getDefaultDropIdGenerator() {
		return new AdjustableDropIdGenerator();
	}

	abstract byte[] generateDropIdBytes(); 

	/**
	 * Generates identifier for a drop encoded in Base64url.
	 * @return the identifier
	 */
	public String generateDropId() {
		byte[] id = Arrays.copyOf(this.generateDropIdBytes(), DROP_ID_LENGTH_BYTE); 
		return new String(UrlBase64.encode(id)).substring(0, DROP_ID_LENGTH); // cut off terminating dot
	}
}
