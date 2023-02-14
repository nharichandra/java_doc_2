import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    static void countFreq(int arr[], int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() == 1) {
                //System.out.println(e.getKey());
            }
        }
    }

    public static void main(String[] args) {



        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Optional<String> optional = Optional.of("");
       // System.out.println(optional.isPresent());
        Optional<String> optionalN = Optional.ofNullable("null");
       // System.out.println(optionalN.isPresent());
        optional.ifPresent(a -> System.out.println(a));
       // optionalN.ifPresentOrElse((value) -> System.out.println(value), () -> System.out.println("Value is not there"));

        List<Employee> employeeList = new ArrayList<>(
                List.of(new Employee(1,"Bhanu","chander", Arrays.asList(1231312l,31231313l),20, "dev", 1000.0),
                        new Employee(2,"Ranjith", "kumar",Arrays.asList(1231312l,31231313l),20, "dev", 2000.0),
                        new Employee(3,"praveen", "kumar",Arrays.asList(1231312l,31231313l),30, "Qa", 3000.0),
                        new Employee(4,"suresh", "reddy",Arrays.asList(1231312l,31231313l),40, "Qa", 4000.0),
                        new Employee(5,"venkatesh", "yadav",Arrays.asList(1231312l,31231313l),40, "Admin", 5000.0),
                        new Employee(6,"srinivas", "yadav",Arrays.asList(1231312l,31231313l),40, "Admin", 6000.0))
        );

        List<Employee> employeeList2 = new ArrayList<>(
                List.of(new Employee(12,"Bhanu","chander",Arrays.asList(1231312l,31231313l), 20, "dev", 1000.0),
                        new Employee(22,"Ranjith", "kumar",Arrays.asList(1231312l,31231313l),20, "dev", 2000.0),
                        new Employee(32,"praveen", "kumar",Arrays.asList(1231312l,31231313l),30, "Qa", 3000.0),
                        new Employee(42,"suresh", "reddy",Arrays.asList(1231312l,31231313l),40, "Qa", 4000.0),
                        new Employee(5,"venkatesh", "yadav",Arrays.asList(1231312l,31231313l),40, "Admin", 5000.0),
                        new Employee(6,"srinivas", "yadav",Arrays.asList(1231312l,31231313l),40, "Admin", 6000.0))
        );

        System.out.println("streams: "+employeeList.stream().flatMap(employee -> employee.getPhoneNumbers().stream().map(s ->s.toString())));

        System.out.println("findAny():---"+employeeList.stream().findAny());
        System.out.println("findFirst():---"+employeeList.stream().findFirst());
       // System.out.println(employeeList.stream().anyMatch());

        //6. Consider a list of employees, sort the employees by their firstName and return the sorted list of employees. 
             employeeList.stream().sorted(Comparator.comparing(Employee::getName).reversed()).forEach(a -> System.out.println("Sorting by names desc:----"+a.toString()));
           //System.out.println("Sorting by names desc:"+sortBYNames);
        // employeeList.stream().map(x ->x.getId()).filter( a ->a == employeeList2.stream().map( g->g.getId())).
        //1. From the list of employees, get the first employee from the list and return his fullName.
        employeeList.stream().sorted(Comparator.comparingDouble(Employee::getId)).limit(1).map(a -> a.getName()+" "+a.getLastName()).forEach(System.out::println);
        //2. Given a list of employees. Can you create a map with the count of employees each department has ? with key as department name and count of employees as value.
        Map<String,Long> stringLongMap=employeeList.stream().collect(Collectors.groupingBy(Employee::getDep,Collectors.counting()) );
        System.out.println("Map with Dep as keys and count as values:-------------"+stringLongMap);

        //12. Get all the employees in ascending order based on the salary.
       List<Employee> ascOrderbySal=   employeeList.stream().sorted(Comparator.comparingDouble(Employee::getSal)).collect(Collectors.toList());
       System.out.println("List oof employee by salary asc"+ascOrderbySal);
        //7. Consider a list of employees, return the employee whose empId is highest.
        System.out.println("maximum Empoyee Id::-----"+ employeeList.stream().collect(Collectors.maxBy(Comparator.comparingInt(Employee::getId))).get().toString());
        //8)Consider a list of employees, concat the fullName of all the employees with pipe (|) and return the concatenated string.
        //where fullName = firstname + lastname.
        //ex: niranjankumar|viswaraj
        String concatinatedString =employeeList.stream().map(a ->a.getName()).collect(Collectors.joining("|"));
        System.out.println(concatinatedString);
        //9. Consider a list of 10 employees, get the 8th employee and print his full name and department name.
        employeeList.stream().sorted(Comparator.comparingInt(Employee::getId).reversed()).limit(2).sorted(Comparator.comparingInt(Employee::getId)).limit(1).forEach(d -> System.out.println("Revered"+d.toString()));


        //Sort the Employee by sal in asc
        employeeList.stream().filter(a -> a.getSal() > 20000).sorted(Comparator.comparing(Employee::getSal)).forEach(df -> System.out.println(df.toString()));
        Map<String, Employee> maxByDep = employeeList.stream().collect(Collectors.groupingBy(e -> e.getDep(), Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Employee::getSal)), Optional::get)));
        //System.out.println("May salary By each department: "+maxByDep);
        //Sort the Employee by sal in desc
        employeeList.stream().filter(a -> a.getSal() > 20000).sorted(Comparator.comparing(Employee::getSal).reversed()).forEach(df -> System.out.println(df.toString()));
        //System.out.println(employeeList.stream().collect(Collectors.minBy(Comparator.comparing(Employee::getAge))));

        /***************************HashMap Sorting**********************************************************/
        Map<String, Integer> map = new HashMap<>();

        map.put("Abhishek", 90);
        map.put("Anushka", 80);
        map.put("Jayant", 80);
        map.put("Amit", 75);
        map.put("Danish", 40);

        List<String> keysList = new ArrayList<>(map.keySet());

        Collections.sort(keysList);
        Map<String, Integer> mapa = new LinkedHashMap<>();
        for(String s :keysList){
           mapa.put(s,map.get(s));
            //System.out.print( s +" = " +map.get(s)+" ");
        }
        System.out.println("sorted Keys using Array List: "+mapa);

        //sort hashmap by values
        Map<String, Integer> sss = map.entrySet().stream().sorted((q1, q2) -> q1.getValue().compareTo(q2.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (y1, y2) -> y1, LinkedHashMap<String, Integer>::new));
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        for (Map.Entry<String, Integer> entry :
                sss.entrySet()) {
           /* System.out.println("Key = " + entry.getKey()
                    + ", Value = "
                    + entry.getValue());*/
        }
        //sort the Hapsmap Values by Desc
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(x -> linkedHashMap.put(x.getKey(), x.getValue()));
        //sort the Hapsmap Values by asc
        // System.out.println("linkedhashmap: "+linkedHashMap);
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(x -> treeMap.put(x.getKey(), x.getValue()));
        //sort the Hapsmap Values by asc
        //System.out.println("tree hmap: "+treeMap);
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(x -> linkedHashMap.put(x.getKey(), x.getValue()));
        //System.out.println(linkedHashMap);
        //sort the Hapsmap keys by Desc
        map.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).forEachOrdered(x -> linkedHashMap.put(x.getKey(), x.getValue()));
        //sort the Hapsmap keys by asc
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> linkedHashMap.put(x.getKey(), x.getValue()));
        // System.out.println(linkedHashMap);

        //sort hashmap by keys using treemap
        TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
        tm.putAll(map);
        //System.out.println("TreeMap Sort natural sorting order only by Keys: "+tm);
        //sort hashmap by keys using ArrayList
        ArrayList<String> sortedKeys = new ArrayList<String>(map.keySet());
        Collections.sort(sortedKeys);
        for (String s : sortedKeys) {
            //System.out.print( s +" = " +map.get(s)+" ");
        }
        // //sort hashmap by Keys
        HashMap<String, Integer> temp
                = map.entrySet()
                .stream()
                .sorted((i1, i2)
                        -> i1.getKey().compareTo(
                        i2.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap<String, Integer>::new));

        for (Map.Entry<String, Integer> entry :
                temp.entrySet()) {
            // System.out.println("Key = " + entry.getKey()
            //                       + ", Value = "
            //                      + entry.getValue());
        }


        Integer[] a = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> list = Arrays.stream(a).sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
        // System.out.println(list);
        int[] primitiveArray = {1, 2, 3, 4, 5};
        Integer[] in = Arrays.stream(primitiveArray).boxed().toArray(Integer[]::new);


        List<Integer> intList = Arrays.asList(2, 3, 4, 5, 2, 5, 1, 67, 43);
        List<Integer> duplicate = intList.stream().distinct().collect(Collectors.toList());
        //filter(s -> Collections.frequency(intList, s)>1).collect(Collectors.toSet());
        // System.out.println(duplicate);
        String s = "bhanuchander pathuri is attending ABS interview for the fist time am i currently working in Nisum  pvt ltd company currently am working from Office today";
        String[] d = s.split(" ");
        Map<String, Integer> m = Arrays.stream(d).collect(Collectors.toMap(Function.identity(), w -> 1, Math::addExact));
        // System.out.println(m);

        //occurances of number from the list
        List<Integer> integers = Arrays.asList(1, 2, 3, 2, 4, 5, 6, 5);
        Map<Integer, Long> occu = integers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        //occurances of character from the list
        List<String> strings = Arrays.asList("A", "A", "B", "c", "c");
        Map<String, Long> stringOcc = strings.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // System.out.println(stringOcc);


        Integer[] arr = new Integer[]{10, 40, 50, 20, 10, 20, 30, 10};
        Map<Integer, Integer> x = Arrays.stream(arr).collect(Collectors.toMap(Function.identity(), t -> 1, Math::addExact));
        int n = arr.length;
        // countFreq(arr, n);

        List<String> l = Arrays.asList("B", "A", "A", "C", "B", "A");

        Map<Integer, Long> frequencyMap = intList.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));

        for (Map.Entry<Integer, Long> entry : frequencyMap.entrySet()) {
            // System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        Optional<List<String>> stringList = Optional.of(Arrays.asList("a","b"));
            stringList.orElseGet(Collections::emptyList).forEach(System.out::println);

    }
}