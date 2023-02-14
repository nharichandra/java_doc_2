package com.nisum.java8.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.nisum.java8.practice.model.Person;
import com.nisum.java8.practice.util.PersonUtil;

/**
 * StreamsExample class demonstrates the Stream API and its methods.
 * 
 * @author Rjosula
 *
 */
public class StreamsExample {

	public static void main(String[] args) {
		
		StreamsExample streamsExample = new StreamsExample();

		List<String> stringList = Arrays.asList("one", "two", "three", "four", 
				"five", "six", "seven", "eight", "nine", "ten");

		// displaying each element from array list.
		stringList.stream().forEach(System.out :: println);
		System.out.println("-------------------");
		stringList.stream().filter( str -> str.length() > 3).forEach(System.out :: println);

		System.out.println("-------- Displaying person filter whose age is greater than 20-----------");
		List<Person> personList = PersonUtil.personBuilder();

		// Applying age filter whose age is greater than 20
		List<Person> ageFilter = personList.stream().filter(person -> person.getAge() > 20).collect(Collectors.toList());
		ageFilter.forEach(person -> System.out.println(person.displayPerson()));
		System.out.println("Total persons available after filter applied is " + ageFilter.stream().count());

		System.out.println("\n-------- Displaying person list with country code appended to person names -----------");
		//Modifying the original persons list with name appended with country code and displaying the same.
		// Using map static method to update person objects
		personList.stream().map( person -> person.getName() + " Country code :: +91- India").forEach(System.out :: println);

		System.out.println(" \n -------- Concatenating dual persons collection stream-----------");

		Stream<Person> concateStream = Stream.concat(PersonUtil.getPersonsBetween6070().stream(),
				PersonUtil.getPersonsAgeBetween8090().stream());
		concateStream.forEach(person -> System.out.println(person.displayPerson()));

		System.out.println("\n ----------   Applying parallel stream ---------------------");
		personList.parallelStream().filter(person -> person.getAge() > 15 && person.getAge() < 40)
		.forEach(person -> System.out.println(person.displayPerson()));

		// displaying string list with insertion order
		PersonUtil.displayFruitsStream();

		// display parallel stream of strings without order
		PersonUtil.displayParallelStream();

		System.out.println("\n ----------   Displaying elements in an ordered ---------------------");
		// displaying elements in an order
		PersonUtil.displayFruitsForEachOrdered();
		
		System.out.println("\n ---------   applying flatmap and see the flatten results------");
		List<List<Person>> personCollections = PersonUtil.fetchPersonCollections();
		List<Person> transformedList = personCollections.stream()
		        .flatMap(element -> element.stream()).collect(Collectors.toList());
		
		transformedList.forEach(person -> System.out.println(person.displayPerson()));
		
		System.out.println("\n --------  Instream range --------------");
		IntStream.rangeClosed(0, 10).filter( i -> i > 3).forEach(System.out :: print);
		
		
		System.out.println("\n --------  Usage of distinct --------------");
		Arrays.asList(1, 2, 1, 3, 3, 3, 4, 5, 6, 7).stream().distinct().forEach(System.out :: print);
		
		System.out.println("\n --------  Manipulating persons salary by adding 1000 ore --------------");
		streamsExample.manipulatePersonSalary();
		
	}
	
	/**
	 * Manipulates persons salary using method reference.
	 * 
	 */
	private void manipulatePersonSalary() {
		
		List<Person> manipulatedList = PersonUtil.getPesonsList().stream()
				.map(this::salFunction2)
				.collect(Collectors.toList()); 
		
		manipulatedList.forEach(person -> System.out.println("\n" + person.getSalary()));
	}
	
	/**
	 * Function to increment the salaries using lambda.
	 */
	private static Function<Person, Person> salFunction = person -> {
		person.setSalary(person.getSalary() + 1000);
		return person;
	};
	
	/**
	 * Function to increment the salaries using normal method.
	 */
	private Person salFunction2 (Person person) {
		person.setSalary(person.getSalary() + 10000);
		return person;
	}
}
