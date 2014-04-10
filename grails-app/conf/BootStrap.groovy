import testapp.Person

class BootStrap {

    def init = { servletContext ->
        environments {
            development {
                new Person(firstName: 'Joe', lastName: 'Schmo').save(flush: true)
                new Person(firstName: 'John', lastName: 'Thomas').save(flush: true)
                new Person(firstName: 'Peter', lastName: 'Farthington').save(flush: true)
            }
        }
    }

    def destroy = {
    }
}
