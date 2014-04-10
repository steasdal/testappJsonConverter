package testapp.rest

import testapp.BaseController
import testapp.Person

class PeopleRestController extends BaseController {
    static allowedMethods = [list: 'GET', save: 'POST', options: 'OPTIONS']
    static responseFormats = ['json', 'xml']

    def outputConverter
    def linkService

    def list() {
        outputConverter.personConverter()
        respond Person.getAll()
    }

    def save() {
        def person = new Person(params)

        if(!person.validate() || !person.save(flush: true)) {
            render status: 400, text: 'error creating new Person'
        } else {
            response.setHeader("location",linkService.createPersonLink(person))
            render status: 200, text: "new Person object created successfully"
        }
    }
}
