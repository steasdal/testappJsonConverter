package testapp

class LinkService {

    def grailsLinkGenerator

    def createPersonLink(Person person) {
        grailsLinkGenerator.serverBaseURL +
                grailsLinkGenerator.link(mapping: 'personEndpoint',
                        params: [personId:person.id]
                )
    }

    def createJsonDataLink() {
        grailsLinkGenerator.serverBaseURL +
                grailsLinkGenerator.link(mapping: 'jsonDataEndpoint')
    }
}
