package com.switchfully.service.security;

import com.google.common.collect.Lists;

import java.util.List;

import static com.switchfully.service.security.Feature.*;

public enum Role {
    CUSTOMER("customer", LOGIN, ORDER_ITEM),
    ADMIN("admin", LOGIN, ADD_ITEM, ORDER_ITEM, UPDATE_ITEM, GET_ALL_ITEMS, GET_ALL_ORDERS);

    private final String label;
    private final List<Feature> featureList;

    Role(String label, Feature... featureList) {
        this.label = label;
        this.featureList = Lists.newArrayList(featureList);
    }

    public List<Feature> getFeatures() {
        return featureList;
    }
    public String getLabel() {
        return label;
    }
}
