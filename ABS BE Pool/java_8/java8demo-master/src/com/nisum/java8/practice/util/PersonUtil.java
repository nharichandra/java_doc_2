package com.nisum.java8.practice.util;

import java.util.ArrayList;
import java.util.List;

import com.nisum.java8.practice.model.Person;

/**
 * Person utils to generate the person related collections.
 * 
 * @author Rjosula
 *
 */
public class PersonUtil {

	/**
	 * Person object builder to provide the list of persons.
	 * @return personsList
	 */
	public static List<Person> personBuilder() {
		List<Person> personsList = new ArrayList<>();
		personsList.add(new Person(22, "person", "male"));
		personsList.add(new Person(13, "person1", "female"));
		personsList.add(new Person(45, "person2", "male"));
		personsList.add(new Person(12, "person3", "female"));
		personsList.add(new Person(55, "person4", "female"));
		personsList.add(new Person(16, "person5", "male"));
		return personsList;
	}

	/**
	 * Prepares persons list more than 80 and return.
	 * 
	 * @return persons list
	 */
	public static List<Person> getPersonsAgeBetween8090() {
		List<Person> persons = new ArrayList<>();

		persons.add(new Person(80, "name 80", "male"));
		persons.add(new Person(86, "name 86", "female"));
		persons.add(new Person(88, "name 88", "female"));
		persons.add(new Person(96, "name 96", "male"));

		return persons;
	}

	/**
	 * Prepares persons list between 60 and 70 return.
	 * 
	 * @return persons list
	 */
	public static List<Person> getPersonsBetween6070() {
		List<Person> persons = new ArrayList<>();

		persons.add(new Person(61, "name 61", "male"));
		persons.add(new Person(66, "name 66", "female"));
		persons.add(new Person(62, "name 62", "female"));
		persons.add(new Person(69, "name 69", "male"));

		return persons;
	}
	
	/**
	 * Prepares persons list between 60 and 70 return.
	 * 
	 * @return persons list
	 */
	public static List<Person> getPesonsList() {
		List<Person> persons = new ArrayList<>();

		persons.add(new Person(61, "name 61", "male", 1000));
		persons.add(new Person(66, "name 66", "female", 2000));
		persons.add(new Person(62, "name 62", "female", 10000));
		persons.add(new Person(69, "name 69", "male", 7000));

		return persons;
	}

	/**
	 * Displays the fruits stream with parallel stream.
	 */
	public static void displayParallelStream() {
		List<String> fruitList = getListOfFruits();
		//forEach - the output would be in any order
		System.out.println("\n Print stream parallel");
		fruitList.stream() 
		.filter(f->f.startsWith("M"))
		.parallel() 
		.forEach(n->System.out.println(n));
	}

	/**
	 * Displays the fruits stream with sequential stream.
	 */
	public static void displayFruitsStream() {
		List<String> fruitList = getListOfFruits();
		//forEach - the output would be in any order
		System.out.println("\n Print stream sequential");
		fruitList.stream() 
		.forEach(n->System.out.println(n));
	}

	/**
	 * Displays the fruits stream with ordered elements.
	 */
	public static void displayFruitsForEachOrdered() {
		List<String> fruitList = getListOfFruits();
		//forEach - the output would be in any order
		fruitList.stream().filter(f->f.startsWith("M"))  
		.parallel()  
		.forEachOrdered(n->System.out.println(n));
	}

	/**
	 * Gets the fruits list.
	 * 
	 * @return return fruit list
	 */
	public static List<String> getListOfFruits() {
		List<String> fruitList = new ArrayList<String>();
		fruitList.add("Maggie"); 
		fruitList.add("Michonne");
		fruitList.add("Rick");
		fruitList.add("Merle");
		fruitList.add("Governor"); 
		return fruitList;
	}
	
	/**
	 * Fetching person collections from various collection of persons.
	 * Example for flatMap to apply transformation on list of lists 
	 * to flatten to a single stream of collection.
	 * 
	 * @return lists of lists of persons
	 */
	public static List<List<Person>> fetchPersonCollections() {
		List<List<Person>> personCollections = new ArrayList<>();
		personCollections.add(getPersonsAgeBetween8090());
		personCollections.add(getPersonsBetween6070());
		return personCollections;
	}
}
