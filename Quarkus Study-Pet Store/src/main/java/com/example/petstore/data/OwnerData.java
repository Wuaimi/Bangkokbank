package com.example.petstore.data;

import java.util.*;
import com.example.petstore.template.Owner;

public class OwnerData {
    private List<Owner> ownerList = new ArrayList<>();
    private static OwnerData owners = new OwnerData();

    private OwnerData() {
        this.addOwner(new Owner(1, "Mhee", 25, "male", Arrays.asList(1, 2, 3)));
        this.addOwner(new Owner(2, "Kwan", 20, "female", Arrays.asList(3)));
        this.addOwner(new Owner(3, "Khing", 20, "female", Arrays.asList(4, 5)));
        this.addOwner(new Owner(4, "Kwan", 45, "male", Arrays.asList(5)));
        this.addOwner(new Owner(5, "Pop", 35, "male", Arrays.asList(3, 5)));
    }

    public static OwnerData getAllOwners() {
        return owners;
    }

    public List<Owner> getOwnerList() {
        return ownerList;
    }

    public Owner addOwner(Owner owner) {
        ownerList.add(owner);
        return owner;
    }

    public Owner updateOwner(Integer ownerId, Owner updatedOwner) {
        for (Owner o : ownerList) {
            if (o.getOwnerId().equals(ownerId)) {
                o.setOwnerAge(updatedOwner.getOwnerAge());
                o.setOwnerName(updatedOwner.getOwnerName());
                o.setOwnerGender(updatedOwner.getOwnerGender());
                o.setOwnerPets(updatedOwner.getOwnerPets());
                return o;
            }
        }
        return null;
    }

    public boolean deleteOwner (Integer ownerId){
        Iterator<Owner> iterator = ownerList.iterator();
        while (iterator.hasNext()) {
            Owner o = iterator.next();
            if (o.getOwnerId().equals(ownerId)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }


    public Owner getOwnerById(Integer ownerId) {
        return ownerList.stream()
                .filter(o -> o.getOwnerId().equals(ownerId))
                .findFirst()
                .orElse(null);
    }

    public List<Owner> getOwnerByName (String ownerName) {
        List<Owner> searchList = new ArrayList<Owner>();
        for (Owner o : ownerList) {
            if (o.getOwnerName().equals(ownerName)) { // <<< แก้ตรงนี้!!
                searchList.add(o);
            }
        }
        return searchList;
    }


    public List<Owner> getOwnerByGender (String ownerGender) {
        List<Owner> searchList = new ArrayList<Owner>();
        for (Owner o : ownerList) {
            if (o.getOwnerGender().equalsIgnoreCase(ownerGender)) { // <<< เพิ่ม ignore case
                searchList.add(o);
            }
        }
        return searchList;
    }


    public List<Owner> getOwnerByAge(Integer ownerAge) {
        List<Owner> searchList = new ArrayList<>();
        for (Owner o : ownerList) {
            if (o.getOwnerAge().equals(ownerAge)) {
                searchList.add(o);
            }
        }
        return searchList;
    }

    public List<Owner> getOwnerByPetId(Integer petId) {
        List<Owner> searchList = new ArrayList<>();
        for (Owner o : ownerList) {
            if (o.getOwnerPets().contains(petId)) {
                searchList.add(o);
            }
        }
        return searchList;
    }
}
