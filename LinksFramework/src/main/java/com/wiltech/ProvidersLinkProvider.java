package com.wiltech;

import org.springframework.hateoas.Link;

public class ProvidersLinkProvider {

    public static Link buildProvidersLink() {
        return new Link("http://localhost:5002/api/providers", "providers");
    }
}
