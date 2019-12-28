package com.wiltech;

import java.net.URI;
import java.util.Arrays;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * The type Base rest service.
 */
public class BaseRestService {

    /**
     * Build location header uri.
     * @param id the id
     * @return the uri
     */
    public URI buildLocationHeader(final Long id) {

        return MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(id).toUri();
    }

    /**
     * Build self link link.
     * @return the link
     */
    public Link buildSelfLink() {
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();

        return new Link(uriString, "self");
    }

    /**
     * Gets dto root name.
     * @param clazz the clazz
     * @return the dto root name
     */
    public String getDTORootName(final Class<? extends BaseDTO> clazz) {
        if (clazz.isAnnotationPresent(JsonRootName.class)) {
            return clazz.getAnnotation(JsonRootName.class).value();
        }

        return clazz.getSimpleName();
    }

    /**
     * Empty resources resources. Use this resource to force the serializer to add am empty array.
     * @return the resources
     */
    public Resources<Object> emptyResources() {
        final EmbeddedWrappers wrappers = new EmbeddedWrappers(false);
        final EmbeddedWrapper wrapper = wrappers.emptyCollectionOf(ProviderResourceResponse.class);

        return new Resources<>(Arrays.asList(wrapper));
    }
}
