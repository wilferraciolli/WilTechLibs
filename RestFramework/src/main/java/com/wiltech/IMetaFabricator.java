/*
 * (c) Midland Software Limited 2019
 * Name     : IPersonMetaFabricator.java
 * Author   : ferraciolliw
 * Date     : 12 Sep 2019
 */
package com.wiltech;

/**
 * The interface meta fabricator.
 */
public interface IMetaFabricator {

    /**
     * Create meta for template meta.
     * @return the meta
     */
    Meta createMetaForTemplate();

    /**
     * Create meta for single resource meta.
     * @return the meta
     */
    Meta createMetaForSingleResource();

    /**
     * Create meta for collection resource meta.
     * @return the meta
     */
    Meta createMetaForCollectionResource();
}
