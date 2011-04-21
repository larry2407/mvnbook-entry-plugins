package com.mgreau.mvnbook.persistence.model;
import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class IssueManagementTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void systemIsBlank() {
        IssueManagement im = new IssueManagement(" ", "http://jira.codehaus.org/browse/MDEPLOY");

        Set<ConstraintViolation<IssueManagement>> constraintViolations =
            validator.validate(im);
        
        assertEquals(1, constraintViolations.size());
        assertEquals("ne peut pas être vide", constraintViolations.iterator().next().getMessage());
    }
    
    @Test
    public void urlIsNull() {
        IssueManagement im = new IssueManagement("JIRA", null);

        Set<ConstraintViolation<IssueManagement>> constraintViolations =
            validator.validate(im);

        assertEquals(1, constraintViolations.size());
        assertEquals("ne peut pas être nul", constraintViolations.iterator().next().getMessage());
    }
    
    @Test
    public void urlIsBad() {
    	IssueManagement im = new IssueManagement("JIRA", "http:://jira.codehaus.org/browse/MDEPLOY");

    	Set<ConstraintViolation<IssueManagement>> constraintViolations =
            validator.validate(im);

    	assertEquals(1, constraintViolations.size());
        assertEquals("L'URL est erronée.", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void issueManagementIsValid() {
    	IssueManagement im = new IssueManagement("JIRA", "http://jira.codehaus.org/browse/MDEPLOY");

    	Set<ConstraintViolation<IssueManagement>> constraintViolations =
            validator.validate(im);

        assertEquals(0, constraintViolations.size());
    }
}