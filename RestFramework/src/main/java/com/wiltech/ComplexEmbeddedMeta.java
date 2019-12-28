/*
 * (c) Midland Software Limited 2019
 * Name     : EmbeddedMeta.java
 * Author   : ferraciolliw
 * Date     : 12 Sep 2019
 */
package com.wiltech;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Embedded meta.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplexEmbeddedMeta {

    /**
     * The Id.
     */
    String id;
    /**
     * The Value.
     */
    String value;
}
