package testapp.rest

import grails.converters.JSON
import testapp.BaseController

class JsonDataController extends BaseController {
    static allowedMethods = [list: 'GET', options: 'OPTIONS']
    static responseFormats = ['json']

    def linkService

    def list() {
        String rel = "self"
        String href = linkService.createJsonDataLink()

        Map json = [
                "links":
                        [
                                [
                                        "rel": rel,
                                        "href" : href
                                ]
                        ]
        ]

        render json as JSON
    }
}
