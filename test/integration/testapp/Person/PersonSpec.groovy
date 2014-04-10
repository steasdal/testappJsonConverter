package testapp.Person

import grails.test.spock.IntegrationSpec
import groovy.json.JsonSlurper
import testapp.Person
import testapp.rest.PersonRestController

class PersonSpec extends IntegrationSpec {
    final def controller = new PersonRestController()

    Person person_01
    Person person_02

    def setup() {
        // Setup a handful of fake Person records
        person_01 = new Person(firstName: 'billy', lastName: 'jean').save(flush: true, failOnError: true)
        person_02 = new Person(firstName: 'bobby', lastName: 'sue').save(flush: true, failOnError: true)
    }

    def setupGet(String acceptHeader) {
        controller.request.method = "GET"
        controller.request.addHeader("Accept", acceptHeader)
    }

    def "Generate a 404 by searching for a nonexistent Person"() {
        setup: "setup the controller"
        setupGet('application/xml')
        controller.params.personId = 12341234

        when: "we call the list() method on the controller"
        controller.list()

        then: "we verify that the resulting status is a 404"
        controller.response.status == 404
    }

    def "Get Person XML, check for 200 status, parse results"() {
        setup: "prepare the controller"
        setupGet('application/xml')
        controller.params.personId = person_01.id
        XmlSlurper xmlSlurper = new XmlSlurper()

        when: "we call the list() method on the controller"
        controller.list()

        then: "we get back a particular Person record and a 200 status"
        controller.response.status == 200

        def xml = xmlSlurper.parseText(controller.response.contentAsString)
        xml.firstName.text() == 'billy'
        xml.lastName.text() == 'jean'
        xml.selflink.text() ==~ "http://.*/rest/people/${person_01.id}"
    }

    def "Get Person JSON, check for 200 status, parse results"() {
        setup: "prepare the controller"
        setupGet('application/json')
        controller.params.personId = person_02.id
        JsonSlurper jsonSlurper = new JsonSlurper()

        when: "we call the list() method on the controller"
        controller.list()

        then: "we get back a particular Person record and a 200 status"
        controller.response.status == 200

        def json = jsonSlurper.parseText(controller.response.contentAsString)
        json.firstName == 'bobby'
        json.lastName == 'sue'
        json.selflink ==~ "http://.*/rest/people/${person_02.id}"
    }

    def "Verify that OPTIONS returns proper list of verbs"() {
        setup: "setup the controller"
        controller.request.method = "OPTIONS"

        when: "We call the options method on the controller"
        controller.options()

        then: "We get the expected list of allowed verbs in the Allow header"
        controller.response.getHeader('Allow').tokenize(', ') == ["GET", "OPTIONS"]
    }
}
