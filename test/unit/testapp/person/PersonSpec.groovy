package testapp.person

import grails.test.mixin.TestFor
import spock.lang.Unroll
import testapp.ConstraintUnitSpec
import testapp.Person

@TestFor(Person)
class PersonSpec extends ConstraintUnitSpec {

    def setup() {
        mockForConstraintsTests(Person, [new Person(firstName: "john", lastName: "doe")])
    }

    @Unroll("test Person all constraints #field is #error")
    void "test Person all constraints"() {
        when:
        def person = new Person("$field": testValue)

        then:
        validateConstraints(person, field, error)

        where:
        error      | field       | testValue
        'nullable' | 'firstName' | null
        'nullable' | 'firstName' | ''
        'valid'    | 'firstName' | 'psw'
        'nullable' | 'lastName'  | null
        'nullable' | 'lastName'  | ''
        'valid'    | 'lastName'  | 'Employee'
    }
}
