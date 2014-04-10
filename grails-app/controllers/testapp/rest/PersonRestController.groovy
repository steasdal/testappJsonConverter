package testapp.rest

import testapp.BaseController
import testapp.Person

class PersonRestController extends BaseController {
    static allowedMethods = [list: 'GET', options: 'OPTIONS']
    static responseFormats = ['json', 'xml']

    def outputConverter

    def list() {
        outputConverter.personConverter()
        respond Person.findById(params.personId)
    }
}
