package com.mo1ty.medcenterapp.entity.prototype;

import com.mo1ty.medcenterapp.entity.Address;
import com.mo1ty.medcenterapp.entity.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientPrototype { // Requires Address. Must be implemented after AddressPrototype.

    public static Client makeCzechClient(Address address){
        return new Client("Honza", "Novak",
                "honza.novak@email.cz", "{plain}batman", address);
    }

    public static Client makeGermanClient(Address address){
        return new Client("Thomas", "Beckenbauer",
                "thomas.beckenbauer@email.de", "{plain}batman", address);
    }

    public static Client makeFrenchClient(Address address){
        return new Client("Jean", "Renault",
                "jean.renault@email.fr", "{plain}batman", address);
    }

    public static List<Client> makeAllClients(List<Address> addresses){
        ArrayList<Client> arrayList = new ArrayList<>();
        arrayList.add(makeCzechClient(addresses.get(0)));
        arrayList.add(makeGermanClient(addresses.get(1)));
        arrayList.add(makeFrenchClient(addresses.get(2)));
        return arrayList;
    }
}
