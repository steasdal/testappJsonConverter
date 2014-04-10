package testapp.marshallers

import grails.converters.XML
import grails.converters.JSON

import javax.annotation.PostConstruct

class CustomMarshallerRegistrar {
    def personJsonMarshaller
    def personsJsonMarshaller
    def personXmlMarshaller
    def personsXmlMarshaller

    @PostConstruct
    void registerMarshallers() {
        JSON.createNamedConfig("JsonPersonConfig") {
            it.registerObjectMarshaller(personJsonMarshaller)
            it.registerObjectMarshaller(personsJsonMarshaller)
        }

        XML.createNamedConfig("XmlPersonConfig") {
            it.registerObjectMarshaller(personXmlMarshaller)
            it.registerObjectMarshaller(personsXmlMarshaller)
        }
    }
}
