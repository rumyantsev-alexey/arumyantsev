package ru.job4j.stream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProfilesTest {
    private List<Profile> pfiles = new ArrayList<>();
    private Profiles pfs = new Profiles();

    @Before
    public void initProfiles() {
        pfiles = new ArrayList<>();
        pfiles.add(new Profile(new Address("York", "Street8", 5, 5)));
        pfiles.add(new Profile(new Address("Ark", "Street9", 7, 66)));
        pfiles.add(new Profile(new Address("York", "Street8", 5, 5)));
        pfiles.add(new Profile(new Address("Moscow", "Street12", 88, 54)));
    }

    @Test
    public void getListAddressTest() {
        List<Address> result = pfs.collect(pfiles);
        assertEquals("York", result.get(0).getCity());
    }

    @Test
    public void getListAddressUniqOrderByCityTest() {
        List<Address> result = pfs.collectUniqOrder(pfiles);
        assertEquals("Ark", result.get(0).getCity());
        assertEquals(3, result.size());
    }
}