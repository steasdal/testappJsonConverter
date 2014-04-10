testapp
=======

Sample app created to demonstrate a potential issue with Grails's `grails.converters.JSON` converter.

This project contains a very simple Domain Class called `Person`:

```java
class Person {
    String firstName
    String lastName


    static constraints = {
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
    }
}
```

A couple of controllers and some URL mappings have been created to provide a REST interface to `Person`.
Custom marshallers marshall `People` and `Person` data into XML and JSON.

A very simple controller called `JsonDataController` serves up some JSON by formatting a map using
`grails.converters.JSON`.  Here's the entirety of the `list()` method from that controller:

```java
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
```

The `grails.converters.JSON` converter seems to flake out rather quickly and reliably when switching
back and forth between it and the custom marshallers.









