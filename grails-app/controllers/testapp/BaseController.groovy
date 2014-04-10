package testapp

import org.apache.commons.lang.StringUtils

abstract class BaseController {

    /*
     * This method responds to the OPTIONS request.
     * The response is a 200 status with the Allow header set to the comma delimited
     * list of allowedMethods values.
     *
     * NOTE: sub-classes MUST include options: in allowedMethods for this to work
     * properly. E.g.:
     * static allowedMethods = [list: 'GET', save: 'POST', options: 'OPTIONS']
     */
    final options() {
        response.setHeader("Allow", StringUtils.join(allowedMethods.values(), ", "))
        render status:200
    }

    /*
     * Whenever grails receives a request using an unsupported HTTP-Method (a.k.a. REST verb),
     * the receiving controller's index() method is called. The default response is a 200 status
     * with the Allow header set to the comma delimited list of default allowedMethods values.
     * Instead, we will respond with a 405 (Method Not Allowed) with the Allow header set to the
     * comma delimited list of controller specified allowedMethods values.
     */
    final index() {
        response.setHeader("Allow", StringUtils.join(allowedMethods.values(), ", "))
        render status:405
    }
}