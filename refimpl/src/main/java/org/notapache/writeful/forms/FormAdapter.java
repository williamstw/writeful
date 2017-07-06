package org.notapache.writeful.forms;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.Map;

@Component
public class FormAdapter {
    private static final String PATH_ROOT = "/";

    @Autowired
    private EntityLinks entityLinks;

    public Forms createDefault(Object object) throws Exception {
        if (object == null) {
            throw new IllegalArgumentException("Bean cannot be null.");
        }
        Map<String, Object> props = PropertyUtils.describe(object);

        Forms forms = new Forms();
        Form defaultForm = new Form();
        defaultForm.add(entityLinks.linkToCollectionResource(object.getClass()).withRel(Form.REL_TARGET));
        Class<?> clazz = object.getClass();

        for (Map.Entry<String, Object> e : props.entrySet()) {
            if (!"class".equals(e.getKey()))
                defaultForm.getFields().add(createField(e.getKey(), PropertyUtils.getPropertyDescriptor(object, e.getKey())));
        }

        forms.getForms().put(Forms.DEFAULT, defaultForm);
        return forms;
    }

    private Field createField(String key, PropertyDescriptor descriptor) {
        Field f = new Field();
        f.setName(key);
        f.setPath(PATH_ROOT + key);

        String display = String.join(" ", StringUtils.splitByCharacterTypeCamelCase(key));
        f.setDisplayText(StringUtils.capitalize(display));

        if (descriptor.getReadMethod().isAnnotationPresent(Writeful.class)) {
            Writeful annotation = descriptor.getReadMethod().getAnnotation(Writeful.class);

            if (StringUtils.isNotEmpty(annotation.display())) {
                f.setDisplayText(annotation.display());
            }
            f.setType(annotation.type());
        }


        return f;
    }


}
