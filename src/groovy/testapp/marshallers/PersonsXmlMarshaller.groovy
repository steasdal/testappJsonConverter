package testapp.marshallers

import org.codehaus.groovy.grails.web.converters.Converter
import org.codehaus.groovy.grails.web.converters.exceptions.ConverterException
import org.codehaus.groovy.grails.web.converters.marshaller.NameAwareMarshaller
import org.codehaus.groovy.grails.web.converters.marshaller.ObjectMarshaller
import org.codehaus.groovy.grails.web.xml.XMLStreamWriter
import testapp.LinkService
import testapp.Person

class PersonsXmlMarshaller implements ObjectMarshaller, NameAwareMarshaller {
    LinkService linkService

    @Override
    boolean supports(Object object) {
        return object instanceof Collection<Person>
    }

    @Override
    void marshalObject(Object object, Converter converter) throws ConverterException {
        Collection<Person> people = (Collection<Person>) object
        XMLStreamWriter writer = converter.writer

        people.each{ person ->
            writer
            .startNode('Person')
                .startNode('firstName').characters(person.firstName).end()
                .startNode('lastName').characters(person.lastName).end()
                .startNode('link').characters(linkService.createPersonLink(person)).end()
            .end()
        }
    }

    @Override
    String getElementName(Object o) {
        return 'People'
    }
}
