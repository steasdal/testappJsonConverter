package testapp

import spock.lang.Specification

abstract class ConstraintUnitSpec extends Specification {

    /**
     * It should be noted here that other errors may exist on obj if, for
     * example, other fields on obj are not nullable and have been assigned
     * a null value. Those other errors don't matter as the method checks
     * for the existence of a specific error on a specific field.
     *
     * @param obj the instance of the domain class whose constraints we're going to check.
     * @param field the specific field we want to check for constraint violations.
     * @param error if we're sending in an invalid value for the field then this is the name of
     *              the constraint that should be violated. If we're sending in a valid value for
     *              the field then this parameter should have a value of 'valid' so that the
     *              absence of errors will be verified.
     */
    void validateConstraints(obj, field, error) {
        def validated = obj.validate()
        if(error && error != 'valid') {
            assert !validated
            assert obj.errors[field]
            assert error == obj.errors[field]
        } else {
            assert !obj.errors[field]
        }
    }
}
