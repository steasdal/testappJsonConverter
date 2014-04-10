package testapp

class Person {
    String firstName
    String lastName


    static constraints = {
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
    }
}
