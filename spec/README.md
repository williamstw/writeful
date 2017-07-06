# Writeful - A Hypermedia Application Language (HAL) Profile

A profile for HAL supporting rich and complex writes in the RESTful style.

* Author: Tim Williams <williamstw@gmail.com>
* Created: 2017-07-03
* Updated: -

## Introduction

## Requirements

## Writeful Documents

## Resource Objects

### Reserved Properties

#### _forms

## Example Document
The following is an example document representing data entry for a Person:

    GET /people/template HTTP/1.1
    Host: example.org
    Accept: application/hal+json

    HTTP/1.1 200 OK
    Content-Type: application/hal+json; profile="https://github.com/williamstw/writeful"
 ```javascript   
    {
  "_forms" : {
    "default" : {
      "contentType" : "application/hal+json",
      "method" : "POST",
      "fields" : [ {
        "name" : "birthDate",
        "path" : "/birthDate",
        "value" : null,
        "displayText" : "Birth Date",
        "type" : "string"
      }, {
        "name" : "lastName",
        "path" : "/lastName",
        "value" : null,
        "displayText" : "Last Name",
        "type" : "string"
      }, {
        "name" : "firstName",
        "path" : "/firstName",
        "value" : null,
        "displayText" : "First Name",
        "type" : "string"
      }, {
        "name" : "middleName",
        "path" : "/middleName",
        "value" : null,
        "displayText" : "Middle Name",
        "type" : "string"
      }, {
        "name" : "fullName",
        "path" : "/fullName",
        "value" : null,
        "displayText" : "Full Name",
        "type" : "string"
      }, {
        "name" : "emailAddress",
        "path" : "/emailAddress",
        "value" : null,
        "displayText" : "Email Address",
        "type" : "string"
      } ],
      "_links" : {
        "target" : {
          "href" : "http://localhost:8080/people"
        }
      }
    }
  }
}
```
## IANA Considerations
TBD

## Normative References

## Appendix A. Acknowledgements
This work builds off of the work of Mike Amundsen and Dwolla, which provided the foundation upon which this builds.
