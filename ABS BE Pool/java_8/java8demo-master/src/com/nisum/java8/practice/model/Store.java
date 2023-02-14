package com.nisum.java8.practice.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Store class.
 * 
 * @author Rjosula
 *
 */
public class Store {

    private Optional<String> id;
    
    private Optional<String> name;
    
    public Store(String id, String name) {
        this.id = Optional.ofNullable(id);
        this.name = Optional.ofNullable(name);
    }

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) {
        this.id = id;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }
    
    /**
     * Prepares the list of stores.
     * 
     * @return list of stores
     */
    public static List<Store> fetchAllStores() {
        
        Store s1 = new Store("1212", "genStores");
        Store s2 = new Store("121", "medStores");
        Store s3 = new Store("1111", "medStores2");
        Store s4 = new Store("3333", "medStores3");
        Store s5 = new Store("345", "medStores4");
        Store s6 = new Store("33", "medStores5");
        Store s7 = new Store("3345", "medStores6");
        Store s8 = new Store("3222", "medStores7");
        return Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8);
    }
    
}
