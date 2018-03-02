import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UWECPeopleTests {
	
	private UWECPerson p;
	private UWECStudent s;
	private UWECStaff f;
	private int r;
	private int r2;
	private double gpa;

	@BeforeEach
	public void setUp() throws Exception {
		r = (int)(Math.random() * 100);
		r2 = (int)(Math.random() * 10);
		gpa = Math.random() * 4;
		p = new UWECPerson(r, ""+(r+r2), ""+(r*r2));
		s = new UWECStudent(r+1, ""+(r+r2+1), ""+(r*r2+1), gpa);
		f = new UWECStaff(r+2, ""+(r+r2+2), ""+(r*r2+2));
		f.setTitle(""+r*r);
		f.setHourlyPay(gpa*3);
		f.setHoursPerWeek(gpa*10);
	}

	@Test
	public void testUWECPerson() {
		assertEquals("UWECPerson not assigning or getting id appropriately", p.getUwecId(), r);
		assertEquals("UWECPerson not assigning or getting first name appropriately", p.getFirstName(), ""+(r+r2));
		assertEquals("UWECPerson not assigning or getting last name appropriately", p.getLastName(), ""+(r*r2));
		assertEquals("UWECPerson toString method not working correctly", p.toString(), "UWECPerson = uwecId: " + r + ", name: " + (r+r2) + " " + (r*r2));
		assertTrue("UWECPerson equals method not working correctly", p.equals(new UWECPerson(r, ""+(r+r2), ""+(r*r2))));
		assertFalse("UWECPerson equals method not working correctly", p.equals(new UWECPerson(r+1, ""+(r+r2), ""+(r*r2))));
		assertFalse("UWECPerson equals method not working correctly", p.equals(new UWECPerson(r, ""+(r+r2)+1, ""+(r*r2))));
		assertFalse("UWECPerson equals method not working correctly", p.equals(new UWECPerson(r, ""+(r+r2), ""+(r*r2)+1)));
	}
	
	@Test
	public void testUWECStudent() {
		assertTrue("UWECStudent should inherit from UWECPerson", s instanceof UWECPerson);
		assertEquals("UWECStudent not assigning or getting id appropriately", s.getUwecId(), r+1);
		assertEquals("UWECStudent not assigning or getting first name appropriately", s.getFirstName(), ""+(r+r2+1));
		assertEquals("UWECStudent not assigning or getting last name appropriately", s.getLastName(), ""+(r*r2+1));
		assertEquals("UWECStudent not assigning or getting gpa appropriately", new Double(s.getGpa()), new Double(gpa));
		assertEquals("UWECStudent toString method not working correctly", s.toString(), "UWECStudent = uwecId: " + (r+1) + ", name: " + (r+r2+1) + " " + (r*r2+1) + ", gpa: " + gpa);
		assertTrue("UWECStudent equals method not working correctly", s.equals( new UWECStudent(r+1, ""+(r+r2+1), ""+(r*r2+1), gpa)));
		assertFalse("UWECStudent equals method not working correctly", s.equals( new UWECStudent(r+2, ""+(r+r2+1), ""+(r*r2+1), gpa)));
		assertFalse("UWECStudent equals method not working correctly", s.equals( new UWECStudent(r+1, ""+(r+r2+2), ""+(r*r2+1), gpa)));
		assertFalse("UWECStudent equals method not working correctly", s.equals( new UWECStudent(r+1, ""+(r+r2+1), ""+(r*r2+2), gpa)));
		assertFalse("UWECStudent equals method not working correctly", s.equals( new UWECStudent(r+1, ""+(r+r2+1), ""+(r*r2+1), gpa+0.1)));
	}
	
	@Test
	public void testUWECStaff() {
		assertTrue("UWECStaff should inherit from UWECPerson", f instanceof UWECPerson);
		assertEquals("UWECStaff not assigning or getting id appropriately", f.getUwecId(), r+2);
		assertEquals("UWECStaff not assigning or getting first name appropriately", f.getFirstName(), ""+(r+r2+2));
		assertEquals("UWECStaff not assigning or getting last name appropriately", f.getLastName(), ""+(r*r2+2));
		assertEquals("UWECStaff not assigning or getting title appropriately", f.getTitle(), ""+(r*r));
		assertEquals("UWECStaff not assigning or getting hourly pay appropriately", new Double(f.getHourlyPay()), new Double(gpa*3));
		assertEquals("UWECStaff not assigning or getting hours per week appropriately", new Double(f.getHoursPerWeek()), new Double(gpa*10));
		assertEquals("UWECStaff toString method not working correctly", f.toString(), "UWECStaff = uwecId: " + (r+2) + ", name: " + (r+r2+2) + " " + (r*r2+2) + ", title: " + (r*r) + ", hourly pay: " + (gpa*3) + ", hours/week: " + (gpa*10));
		
		UWECStaff tempStaff = new UWECStaff(r+2, ""+(r+r2+2), ""+(r*r2+2));
		tempStaff.setTitle(""+r*r);
		tempStaff.setHourlyPay(gpa*3);
		tempStaff.setHoursPerWeek(gpa*10);
		assertTrue("UWECStaff equals method not working correctly", f.equals(tempStaff));
		tempStaff.setUwecId(r+3);
		assertFalse("UWECStaff equals method not working correctly", f.equals(tempStaff));
		tempStaff.setUwecId(r+2);
		tempStaff.setFirstName(""+(r+r2+3));
		assertFalse("UWECStaff equals method not working correctly", f.equals(tempStaff));
		tempStaff.setFirstName(""+(r+r2+2));
		tempStaff.setLastName(""+(r*r2+3));
		assertFalse("UWECStaff equals method not working correctly", f.equals(tempStaff));
		tempStaff.setLastName(""+(r*r2+2));
		tempStaff.setTitle(""+r*r+1);
		assertFalse("UWECStaff equals method not working correctly", f.equals(tempStaff));
		tempStaff.setTitle(""+r*r);
		tempStaff.setHourlyPay(gpa*3+1);
		assertFalse("UWECStaff equals method not working correctly", f.equals(tempStaff));
		tempStaff.setHourlyPay(gpa*3);
		tempStaff.setHoursPerWeek(gpa*10+1);
		assertFalse("UWECStaff equals method not working correctly", f.equals(tempStaff));
	}

}
