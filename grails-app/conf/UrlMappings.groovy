class UrlMappings {

	static mappings = {
        name peopleEndpoint: "/rest/people(.$format)?" (controller: 'peopleRest') {
            action = [GET: "list", POST: "save", OPTIONS: "options"]
        }

        name personEndpoint: "/rest/people/$personId(.$format)?" (controller: 'personRest') {
            action = [GET: "list", OPTIONS: "options"]
        }

        name jsonDataEndpoint: "/rest/jsondata"(controller: 'jsonData') {
            action = [GET: "list", OPTIONS: "options"]
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
