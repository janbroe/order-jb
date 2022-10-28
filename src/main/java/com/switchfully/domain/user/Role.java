package com.switchfully.domain.user;

import com.google.common.collect.Lists;
import java.util.List;

import static com.switchfully.domain.user.Feature.*;

public enum Role {
    CUSTOMER(Lists.newArrayList(LOGIN, ORDER_ITEM)),
    ADMIN(Lists.newArrayList(LOGIN, ADD_ITEM, ORDER_ITEM));

    private final List<Feature> features;

    Role(List<Feature> features) {
        this.features = features;
    }

    public boolean containsFeature(Feature feature) {
        return features.contains(feature);
    }
}
