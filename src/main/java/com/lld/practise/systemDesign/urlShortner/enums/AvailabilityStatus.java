package com.lld.practise.systemDesign.urlShortner.enums;

import lombok.Getter;

@Getter
public enum AvailabilityStatus {
    USED("given id is already used by url", "USED"),
    UNUSED("given id is not used by any url", "UNUSED"),
    LOADED("given id is loaded into redis", "LOADED");

    private final String description;
    private final String value;

    AvailabilityStatus(String description, String value) {
        this.description = description;
        this.value = value;
    }


}