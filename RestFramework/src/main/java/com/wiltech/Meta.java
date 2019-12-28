/*
 * (c) Midland Software Limited 2019
 * Name     : Meta.java
 * Author   : ferraciolliw
 * Date     : 10 Sep 2019
 */
package com.wiltech;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Meta.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meta {

    /**
     * The constant MANDATORY.
     */
    public static String MANDATORY = "mandatory";
    /**
     * The constant READ_ONLY.
     */
    public static String READ_ONLY = "readOnly";
    /**
     * The constant HIDDEN.
     */
    public static String HIDDEN = "hidden";

    /**
     * The constant TRUE.
     */
    public static String TRUE = "true";
    /**
     * The constant VALUES.
     */
    public static String VALUES = "values";

    /**
     * The constant HIDDEN_AND_READ_ONLY_MAP.
     */
    public static final Map<String, Object> HIDDEN_AND_READ_ONLY_MAP = Map.of(Meta.HIDDEN, Meta.TRUE, Meta.READ_ONLY, Meta.TRUE);
    /**
     * The constant MANDATORY_MAP.
     */
    public static final Map<String, Object> MANDATORY_MAP = Map.of(Meta.MANDATORY, Meta.TRUE);

    private Map<String, Map<String, Object>> values = new HashMap<>();

    /**
     * Generate embedded value map.
     * @param embeddedMetadata the embedded metadata
     * @return the embedded values passed in on a map of values.
     */
    public static final Map<String, Object> generateEmbeddedValues(final List<EmbeddedMetadata> embeddedMetadata) {
        return Map.of(VALUES, embeddedMetadata);
    }

    public static final Map<String, Object> generateEmbeddedValues(final Map<String, String> keyValuePairs,
            final List<EmbeddedMetadata> embeddedMetadata) {
        final Map<String, Object> values = new HashMap<>();

        keyValuePairs.entrySet().forEach(entry ->
                values.put(entry.getKey(), entry.getValue()));

        values.put(VALUES, embeddedMetadata);

        return values;
    }

}
