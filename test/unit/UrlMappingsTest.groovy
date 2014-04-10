import testapp.rest.PeopleRestController
import testapp.rest.PersonRestController

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(UrlMappings)
//all controller endpoints must be mocked.
@Mock([PersonRestController, PeopleRestController])
class UrlMappingsTest {

    /***** DataModel *****/

    void test_peopleEndpoint_mappings() {
        assertForwardUrlMapping("/rest/people", controller: 'peopleRest')
    }

    void test_personEndpoint_mapping_1() {
        assertForwardUrlMapping("/rest/people/1", controller: 'personRest')
                {
                    personId = '1'
                }
    }

    void test_personEndpoing_mapping_13() {
        assertForwardUrlMapping("/rest/people/13", controller: 'personRest')
                {
                    personId = '13'
                }
    }
}
