package com.epsi.mediatheque.domain;

import lombok.Data;

@Data
public class UserPreference {
    private String id;
    private String key;
    private String value;
}
