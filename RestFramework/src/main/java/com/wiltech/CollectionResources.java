/*
 * (c) Midland Software Limited 2019
 * Name     : CollectionResources.java
 * Author   : ferraciolliw
 * Date     : 23 Oct 2019
 */
package com.wiltech;

import java.util.Map;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The type Collection resources.
 * @param <T> the type parameter
 */
@Data
public class CollectionResources<T> extends CollectionModel {

    @JsonProperty("_meta")
    private Map.Entry[] meta;

    /**
     * Instantiates a new Collection resources.
     * @param content the content
     * @param links the links
     * @param meta the meta
     */
    public CollectionResources(final Iterable<T> content, final Iterable<Link> links, final Meta meta) {
        super(content, links);
        this.meta = meta.getValues().entrySet().toArray(new Map.Entry[] {});
    }
}
