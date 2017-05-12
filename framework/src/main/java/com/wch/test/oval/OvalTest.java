package com.wch.test.oval;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.constraint.AssertValid;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.exception.ConstraintsViolatedException;
import net.sf.oval.exception.ValidationFailedException;

public class OvalTest {

	public static void main(String[] args) {
		Validator validator = new Validator();
		
		School school = new School();
		school.setId(123);
//		school.setName("history");
		List<Person> persons = new ArrayList<Person>();
		Person e = new Person();
		e.setAge(24);
//		e.setId("你好");
		e.setName("张三");
		persons.add(e);
		school.setPersons(persons );
		
		try {
			validator.assertValid(school);
		} catch (ConstraintsViolatedException e2) {
			System.out.println(e2.getMessage());
		}
		
		List<ConstraintViolation> validate = validator.validate(school);
		for (ConstraintViolation constraintViolation : validate) {
			ConstraintViolation[] causes = constraintViolation.getCauses();
			System.out.println(Arrays.toString(causes));
		}
//		assertEquals(0, validate.size());
	}
	
}

class School{
	
	@NotNull
	private Integer id;
	
	@NotNull
	private String name;
	
	@NotNull
	@AssertValid
	private List<Person> persons;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class Person {
	@NotNull
	private String name ;
	
	@NotNull
	private String id ;
	
	@NotNull
	private Integer age ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	
}