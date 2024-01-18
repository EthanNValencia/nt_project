package com.nephew.stripe;

import java.util.HashMap;
import java.util.Map;

public class Model {
    private final Map<String, Object> modelAttributes = new HashMap<>();

    public void addAttribute(String attributeName, Object attributeValue) {
        modelAttributes.put(attributeName, attributeValue);
    }

    public Map<String, Object> getModelAttributes() {
        return modelAttributes;
    }

    @Override
    public String toString() {
        return "Model{" +
                "modelAttributes=" + modelAttributes +
                '}';
    }
}
