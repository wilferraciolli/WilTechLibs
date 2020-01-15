package com.wiltech;

import java.io.Serializable;
import java.util.Map;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Base dto.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO extends RepresentationModel implements Serializable {

    @JsonProperty("_meta")
    private Map.Entry[] meta;

    public Map.Entry[] getMeta() {
        return meta;
    }

    public void setMeta(final Meta meta) {
        this.meta = meta.getValues().entrySet().toArray(new Map.Entry[] {});
    }
}
