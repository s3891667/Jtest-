package org.example;

import com.inflectra.spiratest.addons.junitextension.*;
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

@SpiraTestConfiguration(url = "https://rmit.spiraservice.net", login = "s3891667", rssToken = "{66F1CCF0-2795-4A83-9297-821EDF755C88}", projectId = 51)
public class a2test {
	@Test
	@SpiraTestCase(testCaseId = 1338)
	public void testInput() {
		Event event = new Event();
		Integer admin = event.getAdmin().size();
		Integer student = event.getStudent().size();
		Assertions.assertTrue(admin > 0);
		Assertions.assertTrue(student > 0);
	}

	@Test
	@SpiraTestCase(testCaseId = 1561)
	public void testAdminLogin() {
		Event event = new Event();
		String input = "owen\npassword\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Boolean result = event.AdminLogin();
		Assertions.assertFalse(result);

		System.setIn(System.in);
	}

	@Test
	@SpiraTestCase(testCaseId = 1562)
	public void testStudentLogin() {
		Event event = new Event();
		String input = "7654324\np7654324#\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Boolean result = event.StudentLogin();
		Assertions.assertTrue(result);

		System.setIn(System.in);

	}

	@Test
	@SpiraTestCase(testCaseId = 1563)
	public void testShowStudentEvents() {
		Event event = new Event();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream originalOut = System.out; // Store original System.out

		System.setOut(new PrintStream(outputStream));

		event.showStudentEvents();

		System.setOut(originalOut);

		String capturedOutput = outputStream.toString();

		Assertions.assertNotEquals("", capturedOutput);

	}

	@Test
	@SpiraTestCase(testCaseId = 1564)
	public void testviewStudentDetails() {
		Event event = new Event();
		Assertions.assertTrue(event.viewStudentDetails());
	}

	@Test
	@SpiraTestCase(testCaseId = 1565)
	public void testSearchStudentDetails() {
		Integer id = 1234;
		Event event = new Event();
		Assertions.assertTrue(event.searchStudentDetails(id));
	}

	@Test
	@SpiraTestCase(testCaseId = 1566)
	public void testRemoveStudent() {
		Event event = new Event();
		Integer id = 7654324;
		Assertions.assertAll(() -> assertTrue(event.removeStudent(id)),
				() -> assertFalse(event.searchStudentDetails(id)));
	}

}
