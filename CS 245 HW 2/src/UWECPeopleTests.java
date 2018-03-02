import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class UWECPeopleTests {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUWECPerson() {
		try {
			Class uwecPersonClass = Class.forName("UWECPerson");
			assertEquals("UWECPerson class does not have the correct modifiers", uwecPersonClass.getModifiers(), 1025);
			assertEquals("UWECPerson uwecId does not have the correct modifiers", uwecPersonClass.getDeclaredField("uwecId").getModifiers(), 2);
			assertEquals("UWECPerson firstName does not have the correct modifiers", uwecPersonClass.getDeclaredField("firstName").getModifiers(), 2);
			assertEquals("UWECPerson lastName does not have the correct modifiers", uwecPersonClass.getDeclaredField("lastName").getModifiers(), 2);
			assertEquals("UWECPerson title does not have the correct modifiers", uwecPersonClass.getDeclaredField("title").getModifiers(), 2);
			assertEquals("UWECPerson constructor does not have the correct modifiers and/or signature", uwecPersonClass.getConstructor(int.class, String.class, String.class).getModifiers(), 1);
			assertEquals("UWECPerson setTitle method does not have the correct modifiers and/or signature", uwecPersonClass.getDeclaredMethod("setTitle", String.class).getModifiers(), 1);
			assertEquals("UWECPerson getUwecId method does not have the correct modifiers and/or signature", uwecPersonClass.getDeclaredMethod("getUwecId").getModifiers(), 17);
			assertEquals("UWECPerson getFirstName method does not have the correct modifiers and/or signature", uwecPersonClass.getDeclaredMethod("getFirstName").getModifiers(), 17);
			assertEquals("UWECPerson getLastName method does not have the correct modifiers and/or signature", uwecPersonClass.getDeclaredMethod("getLastName").getModifiers(), 17);
			assertEquals("UWECPerson getTitle method does not have the correct modifiers and/or signature", uwecPersonClass.getDeclaredMethod("getTitle").getModifiers(), 17);
			assertEquals("UWECPerson toString method does not have the correct modifiers and/or signature", uwecPersonClass.getDeclaredMethod("toString").getModifiers(), 1025);
			assertEquals("UWECPerson equals method does not have the correct modifiers and/or signature", uwecPersonClass.getDeclaredMethod("equals", Object.class).getModifiers(), 1);
		} catch (ClassNotFoundException e) {
			fail("Cannot find UWECPerson class");
		} catch (NoSuchFieldException e) {
			fail("At least one UWECPerson field name not found.");
		} catch (NoSuchMethodException e) {
			fail("At least one UWECPerson method name not found.");
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUWECAcademic() {
		try {
			Class uwecAcademicClass = Class.forName("UWECAcademic");
			assertEquals("UWECAcademic class does not have the correct modifiers", uwecAcademicClass.getModifiers(), 1025);
			assertEquals("UWECAcademic numTotalCredits does not have the correct modifiers", uwecAcademicClass.getDeclaredField("numTotalCredits").getModifiers(), 2);
			assertEquals("UWECAcademic constructor does not have the correct modifiers and/or signature", uwecAcademicClass.getConstructor(int.class, String.class, String.class).getModifiers(), 1);
			assertEquals("UWECAcademic getNumTotalCredits method does not have the correct modifiers and/or signature", uwecAcademicClass.getDeclaredMethod("getNumTotalCredits").getModifiers(), 17);
			assertEquals("UWECAcademic setNumTotalCredits method does not have the correct modifiers and/or signature", uwecAcademicClass.getDeclaredMethod("setNumTotalCredits", int.class).getModifiers(), 1);
			assertEquals("UWECAcademic toString method does not have the correct modifiers and/or signature", uwecAcademicClass.getDeclaredMethod("toString").getModifiers(), 1025);
			assertEquals("UWECAcademic equals method does not have the correct modifiers and/or signature", uwecAcademicClass.getDeclaredMethod("equals", Object.class).getModifiers(), 1);
		} catch (ClassNotFoundException e) {
			fail("Cannot find UWECAcademic class");
		} catch (NoSuchFieldException e) {
			fail("At least one UWECAcademic field name not found.");
		} catch (NoSuchMethodException e) {
			fail("At least one UWECAcademic method name not found.");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUWECEmployee() {
		try {
			Class uwecEmployeeInterface = Class.forName("UWECEmployee");
			assertEquals("UWECEmployee computePaycheck method does not have the correct modifiers and/or signature", uwecEmployeeInterface.getDeclaredMethod("computePaycheck").getModifiers(), 1025);
		} catch (ClassNotFoundException e) {
			fail("Cannot find UWECEmployee class");
		} catch (NoSuchMethodException e) {
			fail("At least one UWECEmployee method name not found.");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUWECStudent() {
		UWECStudent s, ss, sss, ssss, sssss, ssssss;
		s = new UWECStudent(2, "first2", "last2", 3.9);
		ss = new UWECStudent(22, "first2", "last2", 3.9);
		sss = new UWECStudent(2, "first22", "last2", 3.9);
		ssss = new UWECStudent(2, "first2", "last22", 3.9);
		sssss = new UWECStudent(2, "first2", "last2", 3.99);
		ssssss = new UWECStudent(2, "first2", "last2", 3.9);
		
		assertTrue("UWECStudent should inherit from UWECAcademic", s instanceof UWECAcademic);
		assertTrue("UWECStudent should be a descendant of UWECPerson", s instanceof UWECPerson);
		
		try {
			Class uwecStudentClass = Class.forName("UWECStudent");
			assertEquals("UWECStudent class does not have the correct modifiers", uwecStudentClass.getModifiers(), 1);
			assertEquals("UWECStudent gpa does not have the correct modifiers", uwecStudentClass.getDeclaredField("gpa").getModifiers(), 2);
			assertEquals("UWECStudent constructor does not have the correct modifiers and/or signature", uwecStudentClass.getConstructor(int.class, String.class, String.class, double.class).getModifiers(), 1);
			assertEquals("UWECStudent setNumTotalCredits method does not have the correct modifiers and/or signature", uwecStudentClass.getDeclaredMethod("setNumTotalCredits", int.class).getModifiers(), 1);
			assertEquals("UWECStudent getGpa method does not have the correct modifiers and/or signature", uwecStudentClass.getDeclaredMethod("getGpa").getModifiers(), 17);
			assertEquals("UWECStudent toString method does not have the correct modifiers and/or signature", uwecStudentClass.getDeclaredMethod("toString").getModifiers(), 1);
			assertEquals("UWECStudent equals method does not have the correct modifiers and/or signature", uwecStudentClass.getDeclaredMethod("equals", Object.class).getModifiers(), 1);
		} catch (ClassNotFoundException e) {
			fail("Cannot find UWECStudent class");
		} catch (NoSuchFieldException e) {
			fail("At least one UWECStudent field name not found.");
		} catch (NoSuchMethodException e) {
			fail("At least one UWECStudent method name not found.");
		}
		
		assertEquals("UWECStudent not assigning or getting id appropriately", s.getUwecId(), 2);
		assertEquals("UWECStudent not assigning or getting first name appropriately", s.getFirstName(), "first2");
		assertEquals("UWECStudent not assigning or getting last name appropriately", s.getLastName(), "last2");
		assertEquals("UWECStudent not assigning or getting gpa appropriately", new Double(s.getGpa()), new Double(3.9));
		assertEquals("UWECStudent not assigning or getting number of credits appropriately", s.getNumTotalCredits(), 0);
		assertNotEquals("UWECStudent not testing for equality appropriately", s, ss);
		assertNotEquals("UWECStudent not testing for equality appropriately", s, sss);
		assertNotEquals("UWECStudent not testing for equality appropriately", s, ssss);
		assertNotEquals("UWECStudent not testing for equality appropriately", s, sssss);
		assertEquals("UWECStudent not testing for equality appropriately", s, ssssss);
		ssssss.setNumTotalCredits(50);
		assertNotEquals("UWECStudent not testing for equality appropriately", s, ssssss);
		assertEquals("UWECStudent not assigning or getting title appropriately", s.getTitle(), "Freshman");
		s.setNumTotalCredits(23);
		assertEquals("UWECStudent not assigning or getting number of credits appropriately", s.getNumTotalCredits(), 23);
		assertEquals("UWECStudent not assigning or getting title appropriately", s.getTitle(), "Freshman");
		s.setNumTotalCredits(24);
		assertEquals("UWECStudent not assigning or getting title appropriately", s.getTitle(), "Sophomore");
		s.setNumTotalCredits(57);
		assertEquals("UWECStudent not assigning or getting title appropriately", s.getTitle(), "Sophomore");
		s.setNumTotalCredits(58);
		assertEquals("UWECStudent not assigning or getting title appropriately", s.getTitle(), "Junior");
		s.setNumTotalCredits(85);
		assertEquals("UWECStudent not assigning or getting title appropriately", s.getTitle(), "Junior");
		s.setNumTotalCredits(86);
		assertEquals("UWECStudent not assigning or getting title appropriately", s.getTitle(), "Senior");
		assertEquals("UWECStudent not assigning or getting number of credits appropriately", ss.getNumTotalCredits(), 0);
		assertEquals("UWECStudent not assigning or getting title appropriately", ss.getTitle(), "Freshman");
		assertEquals("UWECStudent's toString not implemented appropriately", s.toString(), "UWECStudent = uwecId: 2, name: first2 last2, title: Senior, credits: 86, gpa: 3.9");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUWECStaff() {
		UWECStaff f, ff, fff, ffff, fffff, ffffff, fffffff;
		f = new UWECStaff(3, "first3", "last3", "staff1");
		ff = new UWECStaff(33, "first3", "last3", "staff2");
		fff = new UWECStaff(3, "first33", "last3", "staff3");
		ffff = new UWECStaff(3, "first3", "last33", "staff4");
		fffff = new UWECStaff(3, "first3", "last3", "staff5");
		ffffff = new UWECStaff(3, "first3", "last3", "staff6");
		fffffff = new UWECStaff(3, "first3", "last3", "staff7");
		
		assertTrue("UWECStaff should inherit from UWECPerson", f instanceof UWECPerson);
		assertTrue("UWECStaff should implement UWECEmployee", f instanceof UWECEmployee);
		
		try {
			Class uwecStaffClass = Class.forName("UWECStaff");
			assertEquals("UWECStaff class does not have the correct modifiers", uwecStaffClass.getModifiers(), 1);
			assertEquals("UWECStaff hourlyPay does not have the correct modifiers", uwecStaffClass.getDeclaredField("hourlyPay").getModifiers(), 2);
			assertEquals("UWECStaff hoursPerWeek does not have the correct modifiers", uwecStaffClass.getDeclaredField("hoursPerWeek").getModifiers(), 2);
			assertEquals("UWECStaff constructor does not have the correct modifiers and/or signature", uwecStaffClass.getConstructor(int.class, String.class, String.class, String.class).getModifiers(), 1);
			assertEquals("UWECStaff getHourlyPay method does not have the correct modifiers and/or signature", uwecStaffClass.getDeclaredMethod("getHourlyPay").getModifiers(), 17);
			assertEquals("UWECStaff setHourlyPay method does not have the correct modifiers and/or signature", uwecStaffClass.getDeclaredMethod("setHourlyPay", double.class).getModifiers(), 1);
			assertEquals("UWECStaff getHoursPerWeek method does not have the correct modifiers and/or signature", uwecStaffClass.getDeclaredMethod("getHoursPerWeek").getModifiers(), 17);
			assertEquals("UWECStaff setHoursPerWeek method does not have the correct modifiers and/or signature", uwecStaffClass.getDeclaredMethod("setHoursPerWeek", double.class).getModifiers(), 1);
			assertEquals("UWECStaff computePaycheck method does not have the correct modifiers and/or signature", uwecStaffClass.getDeclaredMethod("computePaycheck").getModifiers(), 1);
			assertEquals("UWECStaff toString method does not have the correct modifiers and/or signature", uwecStaffClass.getDeclaredMethod("toString").getModifiers(), 1);
			assertEquals("UWECStaff equals method does not have the correct modifiers and/or signature", uwecStaffClass.getDeclaredMethod("equals", Object.class).getModifiers(), 1);
		} catch (ClassNotFoundException e) {
			fail("Cannot find UWECStaff class");
		} catch (NoSuchFieldException e) {
			fail("At least one UWECStaff field name not found.");
		} catch (NoSuchMethodException e) {
			fail("At least one UWECStaff method name not found.");
		}
		
		assertEquals("UWECStaff not assigning or getting id appropriately", f.getUwecId(), 3);
		assertEquals("UWECStaff not assigning or getting first name appropriately", f.getFirstName(), "first3");
		assertEquals("UWECStaff not assigning or getting last name appropriately", f.getLastName(), "last3");
		f.setTitle("title1");
		assertEquals("UWECStaff not assigning or getting title appropriately", f.getTitle(), "title1");
		f.setHourlyPay(10.5);
		assertEquals("UWECStaff not assigning or getting hourly pay appropriately", new Double(f.getHourlyPay()), new Double(10.5));
		f.setHoursPerWeek(35);
		assertEquals("UWECStaff not assigning or getting last name appropriately", new Double(f.getHoursPerWeek()), new Double(35));
		assertNotEquals("UWECStaff not testing for equality appropriately", f, ff);
		assertNotEquals("UWECStaff not testing for equality appropriately", f, fff);
		assertNotEquals("UWECStaff not testing for equality appropriately", f, ffff);
		fffff.setTitle("title2");
		assertNotEquals("UWECStaff not testing for equality appropriately", f, fffff);
		ffffff.setHourlyPay(10);
		assertNotEquals("UWECStaff not testing for equality appropriately", f, ffffff);
		fffffff.setHoursPerWeek(40);
		assertNotEquals("UWECStaff not testing for equality appropriately", f, fffffff);
		fffff.setHoursPerWeek(35);
		fffff.setHourlyPay(10.5);
		fffff.setTitle("title1");
		assertEquals("UWECStaff not testing for equality appropriately", f, fffff);
		assertEquals("UWECStaff not computing paycheck correctly", new Double(f.computePaycheck()), new Double(735.0));
		ffffff.setHoursPerWeek(25);
		assertEquals("UWECStaff not computing paycheck correctly", new Double(ffffff.computePaycheck()), new Double(500.0));
		assertEquals("UWECStaff's toString not implemented appropriately", f.toString(), "UWECStaff = uwecId: 3, name: first3 last3, title: title1, hourly pay: 10.5, hours/week: 35.0");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUWECFaculty() {
		UWECFaculty t, tt, ttt, tttt, ttttt, tttttt, ttttttt;
		t = new UWECFaculty(5, "first5", "last5", 47);
		tt = new UWECFaculty(55, "first5", "last5", 48);
		ttt = new UWECFaculty(5, "first55", "last5", 119);
		tttt = new UWECFaculty(5, "first5", "last55", 120);
		ttttt = new UWECFaculty(5, "first5", "last5", 239);
		tttttt = new UWECFaculty(5, "first5", "last5", 240);
		ttttttt = new UWECFaculty(5, "first5", "last5", 47);
		
		assertTrue("UWECFaculty should inherit from UWECAcademic", t instanceof UWECAcademic);
		assertTrue("UWECFaculty should be a descendant of UWECPerson", t instanceof UWECPerson);
		assertTrue("UWECFaculty should implement UWECEmployee", t instanceof UWECEmployee);
		
		try {
			Class uwecFacultyClass = Class.forName("UWECFaculty");
			assertEquals("UWECFaculty class does not have the correct modifiers", uwecFacultyClass.getModifiers(), 1);
			assertEquals("UWECFaculty yearlySalary does not have the correct modifiers", uwecFacultyClass.getDeclaredField("yearlySalary").getModifiers(), 2);
			assertEquals("UWECFaculty constructor does not have the correct modifiers and/or signature", uwecFacultyClass.getConstructor(int.class, String.class, String.class, int.class).getModifiers(), 1);
			assertEquals("UWECFaculty setNumTotalCredits method does not have the correct modifiers and/or signature", uwecFacultyClass.getDeclaredMethod("setNumTotalCredits", int.class).getModifiers(), 1);
			assertEquals("UWECFaculty getYearlySalary method does not have the correct modifiers and/or signature", uwecFacultyClass.getDeclaredMethod("getYearlySalary").getModifiers(), 17);
			assertEquals("UWECFaculty setYearlySalary method does not have the correct modifiers and/or signature", uwecFacultyClass.getDeclaredMethod("setYearlySalary", double.class).getModifiers(), 1);
			assertEquals("UWECFaculty computePaycheck method does not have the correct modifiers and/or signature", uwecFacultyClass.getDeclaredMethod("computePaycheck").getModifiers(), 1);
			assertEquals("UWECFaculty toString method does not have the correct modifiers and/or signature", uwecFacultyClass.getDeclaredMethod("toString").getModifiers(), 1);
			assertEquals("UWECFaculty equals method does not have the correct modifiers and/or signature", uwecFacultyClass.getDeclaredMethod("equals", Object.class).getModifiers(), 1);
		} catch (ClassNotFoundException e) {
			fail("Cannot find UWECFaculty class");
		} catch (NoSuchFieldException e) {
			fail("At least one UWECFaculty field name not found.");
		} catch (NoSuchMethodException e) {
			fail("At least one UWECFaculty method name not found.");
		}
		
		assertEquals("UWECFaculty not assigning or getting id appropriately", t.getUwecId(), 5);
		assertEquals("UWECFaculty not assigning or getting first name appropriately", t.getFirstName(), "first5");
		assertEquals("UWECFaculty not assigning or getting last name appropriately", t.getLastName(), "last5");
		t.setYearlySalary(70000);
		assertEquals("UWECFaculty not assigning or getting yearly salary appropriately", new Double(t.getYearlySalary()), new Double(70000));
		t.setYearlySalary(0);	
		assertEquals("UWECFaculty not keeping track of or reporting number of credits appropriately", t.getNumTotalCredits(), 47);
		assertNotEquals("UWECFaculty not testing for equality appropriately", t, tt);
		assertNotEquals("UWECFaculty not testing for equality appropriately", t, ttt);
		assertNotEquals("UWECFaculty not testing for equality appropriately", t, tttt);
		assertNotEquals("UWECFaculty not testing for equality appropriately", t, ttttt);
		assertNotEquals("UWECFaculty not testing for equality appropriately", t, tttttt);
		assertEquals("UWECFaculty not testing for equality appropriately", t, ttttttt);
		t.setYearlySalary(70000);
		assertNotEquals("UWECFaculty not testing for equality appropriately", t, ttttttt);
		assertEquals("UWECFaculty not assigning or getting title appropriately", t.getTitle(), "Adjunct Professor");
		assertEquals("UWECFaculty not assigning or getting title appropriately", tt.getTitle(), "Assistant Professor");
		assertEquals("UWECFaculty not assigning or getting title appropriately", ttt.getTitle(), "Assistant Professor");
		assertEquals("UWECFaculty not assigning or getting title appropriately", tttt.getTitle(), "Associate Professor");
		assertEquals("UWECFaculty not assigning or getting title appropriately", ttttt.getTitle(), "Associate Professor");
		assertEquals("UWECFaculty not assigning or getting title appropriately", tttttt.getTitle(), "Professor");
		assertEquals("UWECFaculty not computing paycheck correctly", new Double(t.computePaycheck()), new Double(70000.0/26));
		assertEquals("UWECFaculty not computing paycheck correctly", new Double(tttttt.computePaycheck()), new Double(0));
		assertEquals("UWECFaculty not assigning or getting title appropriately", t.toString(), "UWECFaculty = uwecId: 5, name: first5 last5, title: Adjunct Professor, credits: 47, yearlySalary: 70000.0");
	}
}
