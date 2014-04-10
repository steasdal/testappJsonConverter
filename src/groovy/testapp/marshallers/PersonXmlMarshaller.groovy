package testapp.marshallers

import grails.converters.XML
import org.codehaus.groovy.grails.web.converters.exceptions.ConverterException
import org.codehaus.groovy.grails.web.converters.marshaller.NameAwareMarshaller
import org.codehaus.groovy.grails.web.converters.marshaller.ObjectMarshaller
import org.codehaus.groovy.grails.web.xml.XMLStreamWriter
import testapp.LinkService
import testapp.Person

/**
 * Created by steasdal on 2/25/14.
 */
class PersonXmlMarshaller implements ObjectMarshaller<XML>, NameAwareMarshaller{
    LinkService linkService

    @Override
    public boolean supports(Object object) {
        return object instanceof Person
    }

    @Override
    void marshalObject(Object object, XML converter) throws ConverterException {
        Person person = (Person) object
        XMLStreamWriter writer = converter.writer

        writer
            .startNode('firstName').characters(person.firstName).end()
            .startNode('lastName').characters(person.lastName).end()
            .startNode('selflink').characters(linkService.createPersonLink(person)).end()
    }

    @Override
    String getElementName(Object o) {
        return 'Person'
    }
}
