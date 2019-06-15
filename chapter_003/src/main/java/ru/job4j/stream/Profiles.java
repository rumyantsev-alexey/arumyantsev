package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {

    List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
    }

    List<Address> collectUniqOrder(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).distinct().sorted(Comparator.comparing(Address::getCity)).collect(Collectors.toList());
    }

}
