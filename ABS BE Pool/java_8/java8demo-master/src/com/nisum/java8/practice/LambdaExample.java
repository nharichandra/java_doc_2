package com.nisum.java8.practice;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.nisum.java8.practice.model.Person;

/**
 * Lambda expression examples.
 * 
 * @author Rjosula
 *
 */
public class LambdaExample {

	public static void main(String[] args) {

		Greetings greetingOld = new Greetings() {

			@Override
			public void sayHello(String name) {
				System.out.println("Hello " + name);

			}
		};
		greet(greetingOld, "Old Passion");

		System.out.println("------------------- using lambda ---------------");

		Greetings greetingsInstance = name -> System.out.println("Hello " + name);
		greet(greetingsInstance, "Tom");

		Person person = new Person(17, "person1", "male");

		System.out.println("Person age test :: " + personFilter.test(person));

		System.out.println(sum.apply(10));

		System.out.println(sumOf2.apply(20, 90));

		new LambdaExample().testConsumer();
	}

	/**
	 * Tests consumer to update the person salary incremented. 
	 */
	private void testConsumer() {
		appendString.accept("String has been ");

		System.out.println("\n ----- Using consumer updating person salary -------");
		Person personToUpdate = new Person(22, "person name", "male", 2000);
		personConsumer.accept(personToUpdate);
		System.out.println(personToUpdate.getSalary());
	}

	/**
	 * Static method to call interface method by passing input name.
	 * 
	 * @param greetingsInstance greeting instance
	 * @param name the name input
	 */
	private static void greet(Greetings greetingsInstance, String name) {
		greetingsInstance.sayHello(name);
	}

	public static Predicate<Person> personFilter = i -> (i.getAge() < 18);

	public static Predicate<Person> contactFilter = person -> (person.getPhoneNumber() > 0);

	public static Function<Integer, Integer> sum = a -> a + 10;

	public static BiFunction<Integer, Integer, Integer> sumOf2 =  (a, b) -> a + b;

	public Consumer<String> appendString = (str) -> System.out.println(str.concat("appended"));

	public Consumer<Person> personConsumer = (person) -> person.setSalary(person.getSalary() + Long.valueOf(1000));

}
