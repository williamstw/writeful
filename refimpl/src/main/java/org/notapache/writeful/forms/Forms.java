package org.notapache.writeful.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import org.springframework.hateoas.ResourceSupport;

import java.util.Map;


public class Forms extends ResourceSupport {
    public static String DEFAULT = "default";

    @JsonProperty("_forms")
    private Map<String, Form> forms = Maps.newHashMap();
    private String foo;

    public Map<String, Form> getForms() {
        return forms;
    }

    public void setForms(Map<String, Form> forms) {
        this.forms = forms;
    }
}
