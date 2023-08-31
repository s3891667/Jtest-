package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class a2test {
	@Test
	public void testInput() {
		Event event = new Event();
		Integer admin = event.getAdmin().size();
		Integer student = event.getStudent().size();
		Assertions.assertTrue(admin > 0);
		Assertions.assertTrue(student > 0);
	}

	@Tag("testSet1")
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	class TestingInputFeature {

		@Test
		public void testAdminLogin() {
			Event event = new Event();
			String input = "owen\npassword\n";
			System.setIn(new ByteArrayInputStream(input.getBytes()));
			Boolean result = event.AdminLogin();
			Assertions.assertFalse(result);

			System.setIn(System.in);
		}

		@Test
		public void testStudentLogin() {
			Event event = new Event();
			String input = "7654324\np7654324#\n";
			System.setIn(new ByteArrayInputStream(input.getBytes()));
			Boolean result = event.StudentLogin();
			Assertions.assertTrue(result);

			System.setIn(System.in);

		}
	}

	@Tag("testSet2")
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	class CRUDTest {
		@Test
		public void testShowStudentEvents() {
			Event event = new Event();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			PrintStream originalOut = System.out; // Store original System.out

			System.setOut(new PrintStream(outputStream));

			event.showStudentEvents();

			System.setOut(originalOut);

			String capturedOutput = outputStream.toString();

			Assertions.assertEquals("", capturedOutput);

		}

		@Test
		public void testviewStudentDetails() {
			Event event = new Event();
			Assertions.assertTrue(event.viewStudentDetails());
		}

		@Test
		public void testSearchStudentDetails() {
			Integer id = 1234;
			Event event = new Event();
			Assertions.assertTrue(event.searchStudentDetails(id));
		}

		@Test
		public void testRemoveStudent() {
			Event event = new Event();
			Integer id = 7654324;
			Assertions.assertAll(() -> assertTrue(event.removeStudent(id)),
					() -> assertFalse(event.searchStudentDetails(id)));
		}

	}

}
