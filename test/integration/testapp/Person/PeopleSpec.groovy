package testapp.Person

import grails.test.spock.IntegrationSpec
import groovy.json.JsonSlurper
import testapp.Person
import testapp.rest.PeopleRestController

class PeopleSpec extends IntegrationSpec {
    final def controller = new PeopleRestController()

    Person crosby
    Person stills
    Person nash
    Person young

    def setup() {
        // Setup a handful of fake Person records
        crosby = new Person(firstName: 'david', lastName: 'crosby').save(flush: true, failOnError: true)
        stills = new Person(firstName: 'stephen', lastName: 'stills').save(flush: true, failOnError: true)
        nash = new Person(firstName: 'graham', lastName: 'nash').save(flush: true, failOnError: true)
        young = new Person(firstName: 'neil', lastName: 'young').save(flush: true, failOnError: true)
    }

    def setupGet(String acceptHeader) {
        controller.request.method = "GET"
        controller.request.addHeader("Accept", acceptHeader)
    }

    def "Get XML list of all people, look for 200 status, parse results"() {
        setup: "fire up a fresh controller"
        setupGet('application/xml')
        XmlSlurper xmlSlurper = new XmlSlurper()

        when: "we call the list method on the controller"
        controller.list()

        then: "we get a 200 status and some XML"
        controller.response.status == 200

        def xml = xmlSlurper.parseText(controller.response.contentAsString)
        xml.Person.size() == 4
    }

    def "Get JSON list of all people, look for 200 status, parse results"() {
        setup: "fire up a fresh controller"
        setupGet('application/json')
        JsonSlurper jsonSlurper = new JsonSlurper()

        when: "we call the list method on the controller"
        controller.list()

        then: "we get a 200 status and some XML"
        controller.response.status == 200

        def json = jsonSlurper.parseText(controller.response.contentAsString)
        json.People.Person.size == 4
    }

    def "Verify that OPTIONS returns proper list of verbs"() {
        setup: "fresh controller coming up"
        controller.request.method = "OPTIONS"

        when: "We call the options method on the controller"
        controller.options()

        then: "We get the expected list of allowed verbs in the Allow header"
        controller.response.getHeader('Allow').tokenize(', ') == ["GET", "POST", "OPTIONS"]
    }

}
