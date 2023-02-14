package com.nisum.java8.practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringSepExample {

    public static void main(String[] args) {
        
        List<String> filePathParts = Arrays.asList("Raghu", "Thoya", "Suraj");
        String filePath = filePathParts.stream()
                  .collect(Collectors.joining("|", "", ""));
        System.out.println(filePath);
    }
}
