package testEnum;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Builder;

public class BuilderTest {

	@Test
	public void testValueOf() {
		assertEquals(Builder.FENDER, Builder.fromString("Fender"));
	
	}

}
