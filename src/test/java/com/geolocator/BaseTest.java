package com.geolocator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geolocator.service.GeolocatorApiService;
import com.geolocator.util.ShopAddressStore;


@ContextConfiguration({"/test-spring-context.xml"})
public class BaseTest {

    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected GeolocatorApiService geolocatorApiService;
    @Autowired
    protected ShopAddressStore shopAddressStore;
    
}
