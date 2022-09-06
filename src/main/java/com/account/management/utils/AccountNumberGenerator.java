package com.account.management.utils;


import java.time.Instant;
import java.util.*;

public class AccountNumberGenerator {

    public static final int SET_SIZE_REQUIRED = 10;
    public static final int NUMBER_RANGE = 100;

    public static String generate() {
        Random random = new Random();

        Set set = new HashSet<Integer>(SET_SIZE_REQUIRED);

        while(set.size()< SET_SIZE_REQUIRED) {
            while (set.add(random.nextInt(NUMBER_RANGE)));
        }
        assert set.size() == SET_SIZE_REQUIRED;
        return String.valueOf(set).replace("[","").replace("]","")
                .replace(",","").replace(" ","").substring(0,10);

    }

    public static void main(String[] args) {

        System.out.println(Instant.now());
        String a = "2022-05-26T16:40:38.217+00:00";
        String b = String.valueOf(Instant.now());
        System.out.println(Instant.parse(b).getEpochSecond());
    }


}
