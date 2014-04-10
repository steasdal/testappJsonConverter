import testapp.MasterConverter
import testapp.marshallers.*


// Place your Spring DSL code here
beans = {
    personJsonMarshaller(PersonJsonMarshaller) {
        linkService = ref('linkService')
    }

    personsJsonMarshaller(PersonsJsonMarshaller) {
        linkService = ref('linkService')
    }

    personXmlMarshaller(PersonXmlMarshaller) {
        linkService = ref('linkService')
    }

    personsXmlMarshaller(PersonsXmlMarshaller) {
        linkService = ref('linkService')
    }

    customMarshallerRegistrar( CustomMarshallerRegistrar ) {
        personJsonMarshaller = ref('personJsonMarshaller')
        personsJsonMarshaller = ref('personsJsonMarshaller')
        personXmlMarshaller = ref('personXmlMarshaller')
        personsXmlMarshaller = ref('personsXmlMarshaller')
    }

    outputConverter(MasterConverter) {
    }
}
