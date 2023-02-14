package com.hari.functionalinterface;

import com.hari.model.EmployeeDTO;
import com.hari.model.EmployeeEntity;

import java.util.function.Function;

/**
 * A Function is a functional interface whose sole purpose is to return any result by working on a single input argument.
 * It represents a function that accepts one argument and returns a result.
 * The function interface contains exactly one abstract method apply(T t).
 * Note that it also contains default, static methods.
 *
 * @author Hari Chandra Prasad. Nimmagadda
 */
public class FunctionExample {

    public static void main(String[] args) {

        // Without lamda expressions, Convert String upper case to lower case
        Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String str) {
                return str.toLowerCase();
            }
        };
        System.out.println(function.apply("HARI"));

        // With lamda expressions, Convert String upper case to lower case
        Function<String, String> withLamda = (str) -> str.toLowerCase();
        System.out.println(withLamda.apply("CHANDRA"));

        // EmployeeDTO - the dto type of the input to the function
        // EmployeeEntity - the entity type of the output of the function
        //
        Function<EmployeeDTO, EmployeeEntity> dtoFunction = employeeDTO -> {
            return new EmployeeEntity(employeeDTO.getName(), employeeDTO.getAge());
        };

        EmployeeEntity employeeEntity = dtoFunction.apply(new EmployeeDTO("Hari", 35));
        System.out.println(employeeEntity.getName());
        System.out.println(employeeEntity.getAge());
    }
}
