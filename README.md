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
back and forth between it and the custom marshallers.  To reproduce this behavior, simply fire up
POSTman (or your favorite REST URL exerciser) and iterate back 'n forth between the following two endpoints:

[http://localhost:8080/testappJsonConverter/rest/people]
[http://localhost:8080/testappJsonConverter/rest/jsondata]

You should, within a few iterations, encounter the following error:

```
Unconvertable Object of class: java.util.LinkedHashMap. Stacktrace follows:
Message: Unconvertable Object of class: java.util.LinkedHashMap
    Line | Method
->>  199 | value     in grails.converters.JSON
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
|    133 | render    in     &#39;&#39;
|    149 | render .  in     &#39;&#39;
|     26 | list      in JsonDataController.groovy
|    200 | doFilter  in PageFragmentCachingFilter.java
|     63 | doFilter  in AbstractFilter.java
|   1145 | runWorker in java.util.concurrent.ThreadPoolExecutor
|    615 | run       in java.util.concurrent.ThreadPoolExecutor$Worker
^    724 | run . . . in java.lang.Thread
```

Here's a short video demonstration of this anomalous behavior:

<a href="http://www.youtube.com/watch?feature=player_embedded&v=oMXyhNCqyn4"
   target="_blank"><img src="http://img.youtube.com/vi/oMXyhNCqyn4/0.jpg"
   alt="testappJsonConverter demo video" width="480" height="360" border="10" /></a>
