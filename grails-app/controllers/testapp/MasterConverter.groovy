package testapp

import grails.converters.JSON
import grails.converters.XML

class MasterConverter {
    def personConverter = {
        JSON.use('JsonPersonConfig')
        XML.use('XmlPersonConfig')
    }
}
