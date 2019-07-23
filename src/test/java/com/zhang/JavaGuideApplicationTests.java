package com.zhang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaGuideApplicationTests {

	@Test
	public void contextLoads() {
		Person person = new Person("张三","湖北");
		test(person);
		System.out.println(person.toString());
	}

	protected void test(Person person) {
		person.setName("李四");
	}

}

class Person{
	private String name;
	private String addres;

	public Person(String name, String addres) {
		this.name = name;
		this.addres = addres;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", addres='" + addres + '\'' +
				'}';
	}
}

