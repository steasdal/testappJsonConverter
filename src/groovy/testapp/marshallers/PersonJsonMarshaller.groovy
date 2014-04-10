package testapp.marshallers

import grails.converters.JSON
import org.codehaus.groovy.grails.web.converters.exceptions.ConverterException
import org.codehaus.groovy.grails.web.converters.marshaller.ObjectMarshaller
import org.codehaus.groovy.grails.web.json.JSONWriter
import testapp.LinkService
import testapp.Person

/**
 * Created by steasdal on 2/25/14.
 */
class PersonJsonMarshaller implements ObjectMarshaller<JSON> {
    LinkService linkService

    @Override
    public boolean supports(Object object) {
        return object instanceof Person
    }

    @Override
    void marshalObject(Object object, JSON json) throws ConverterException {
        Person person = (Person) object

        JSONWriter writer = json.getWriter()
            .object()
                .key('firstName').value(person.firstName)
                .key('lastName').value(person.lastName)
                .key('selflink').value(linkService.createPersonLink(person))
            .endObject()
    }
}
