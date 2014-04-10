package testapp.marshallers

import grails.converters.JSON
import org.codehaus.groovy.grails.web.converters.exceptions.ConverterException
import org.codehaus.groovy.grails.web.converters.marshaller.ObjectMarshaller
import org.codehaus.groovy.grails.web.json.JSONWriter
import testapp.LinkService
import testapp.Person

class PersonsJsonMarshaller implements ObjectMarshaller<JSON> {
    LinkService linkService

    @Override
    boolean supports(Object object) {
        return object instanceof Collection<Person>
    }

    @Override
    void marshalObject(Object object, JSON json) throws ConverterException {
        Collection<Person> people = (Collection<Person>) object
        JSONWriter writer = json.getWriter()
        .object()
            .key('People')
            .array()

            people.each { person ->
                writer.object()
                    .key('Person')
                    .array()
                        .object()
                            .key('firstName').value(person.firstName)
                            .key('lastName').value(person.lastName)
                            .key('link').value(linkService.createPersonLink(person))
                        .endObject()
                    .endArray()
                .endObject()
            }

            writer.endArray()
        writer.endObject()
    }
}
