package org.notapache.writeful.forms;

import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.notapache.writeful.Main;
import org.notapache.writeful.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Main.class})
public class FormAdapterTest {

    @Autowired
    private FormAdapter adapter;

    @Before
    public void setup() {
        HttpServletRequest mockRequest = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }

    @After
    public void teardown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullCreatesException() throws Exception {
        adapter.createDefault(null);
    }

    @Test
    public void theFirstFormShouldBeTheDefault() throws Exception {
        Forms newForm = adapter.createDefault(new Person());

        assertTrue(newForm.getForms().containsKey("default"));
    }

    @Test
    public void defaultToHalMediaType() throws Exception {
        Form form = getNewDefaultForm();
        assertThat("Default mediatype is hal.", form.getContentType(), equalTo(MediaTypes.HAL_JSON_VALUE));
    }

    @Test
    public void formShouldHaveATargetLinkRel() throws Exception {
        Form form = getNewDefaultForm();

        assertTrue("We get a link back.", form.hasLink(Form.REL_TARGET));
    }

    @Test
    public void defaultToPOSTMethod() throws Exception {
        Form form = getNewDefaultForm();
        assertThat("The default method is POST", form.getMethod(), equalTo(RequestMethod.POST));
    }

    @Test
    public void supportCustomFieldTypes() throws Exception {
        Forms newForms = adapter.createDefault(new Person());
        Form form = newForms.getForms().get(Forms.DEFAULT);
        List<Field> fields = form.getFields().stream().filter(f -> f.getName().equals("emailAddress")).collect(Collectors.toList());

        assertThat("We get back the expected fields.", fields.get(0).getType(), equalTo(FieldType.EMAIL));
    }

    @Test
    public void defaultFieldTypeIsString() throws Exception {
        Forms newForms = adapter.createDefault(new Person());
        Form form = newForms.getForms().get(Forms.DEFAULT);
        List<Field> fields = form.getFields().stream().filter(f -> f.getName().equals("firstName")).collect(Collectors.toList());

        assertThat("We get back the expected fields.", fields.get(0).getType(), equalTo(FieldType.STRING));
    }

    @Test
    public void defaultDisplayFieldToUncamelize() throws Exception {
        Forms newForms = adapter.createDefault(new Person());
        Form form = newForms.getForms().get(Forms.DEFAULT);
        List<Field> fields = form.getFields().stream().filter(f -> f.getName().equals("firstName")).collect(Collectors.toList());

        assertThat("We get back the expected fields.", fields.get(0).getDisplayText(), equalTo("First Name"));
    }

    @Test
    public void supportCustomDisplayFields() throws Exception {
        Forms newForms = adapter.createDefault(new Person());
        Form form = newForms.getForms().get(Forms.DEFAULT);
        List<Field> fields = form.getFields().stream().filter(f -> f.getName().equals("lastName")).collect(Collectors.toList());

        assertThat("We get back the expected fields.", fields.get(0).getDisplayText(), equalTo("Surname"));
    }

    @Test
    public void fieldsShouldBeNamedByTheirGetters() throws Exception {
        Forms newForms = adapter.createDefault(new Person());
        Form form = newForms.getForms().get(Forms.DEFAULT);
        List<String> fields = form.getFields().stream().map(f -> f.getName()).collect(Collectors.toList());
        List<String> expected = Lists.newArrayList("firstName", "middleName", "lastName", "fullName", "emailAddress", "birthDate");

        assertThat("We get back the expected fields.", fields, containsInAnyOrder(expected.toArray()));
    }

    private Form getNewDefaultForm() throws Exception {
        Form form;
        Forms newForm = adapter.createDefault(new Person());
        return newForm.getForms().get(Forms.DEFAULT);
    }
}