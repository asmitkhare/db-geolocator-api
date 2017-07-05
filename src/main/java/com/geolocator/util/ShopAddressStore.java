package com.geolocator.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.geolocator.model.Shop;

public class ShopAddressStore {
	
	@NotNull
    private List<Shop> shopsList = Collections.synchronizedList(new ArrayList<Shop>());

    public Shop get(int index) {
        return shopsList.get(index);
    }

    public void add(Shop Shop) {
    	shopsList.add(Shop);
    }

    public void remove(Shop Shop) {
    	shopsList.remove(Shop);
    }

    public void remove(int index) {
    	shopsList.remove(index);
    }

    public List<Shop> getAllShops() {
        return Collections.unmodifiableList(shopsList);
    }

}
