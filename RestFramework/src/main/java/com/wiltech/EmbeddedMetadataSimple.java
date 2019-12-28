/*
 * (c) Midland Software Limited 2019
 * Name     : EmbeddedMetadata.java
 * Author   : ferraciolliw
 * Date     : 22 Oct 2019
 */
package com.wiltech;

import lombok.Value;

/**
 * The type Embedded metadata simple.
 */
@Value
public class EmbeddedMetadataSimple implements EmbeddedMetadata {

    private String id;
    private String value;
}
