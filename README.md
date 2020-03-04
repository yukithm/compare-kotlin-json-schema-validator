# Comparison of Kotlin/Java JSON Schema validators

## Run

```sh
./gradlew run
```

```console
> Task :compileKotlin UP-TO-DATE
> Task :compileJava NO-SOURCE
> Task :processResources UP-TO-DATE
> Task :classes UP-TO-DATE

> Task :run
------------------------------------------------------------
everit-org:
org.everit.json.schema.ValidationException: #: 2 schema violations found
{
  "schemaLocation": "#",
  "pointerToViolation": "#",
  "causingExceptions": [
    {
      "schemaLocation": "#/properties/firstName",
      "pointerToViolation": "#/firstName",
      "causingExceptions": [],
      "keyword": "minLength",
      "message": "expected minLength: 1, actual: 0"
    },
    {
      "schemaLocation": "#/properties/age",
      "pointerToViolation": "#/age",
      "causingExceptions": [],
      "keyword": "minimum",
      "message": "-21 is not greater or equal to 0"
    }
  ],
  "message": "2 schema violations found"
}
------------------------------------------------------------
networknt:
12:17:36.730 [main] DEBUG com.networknt.schema.TypeValidator - validate( {"firstName":"John","lastName":"Doe","age":21}, {"firstName":"John","lastName":"Doe","age":21}, $)
12:17:36.732 [main] DEBUG com.networknt.schema.PropertiesValidator - validate( {"firstName":"John","lastName":"Doe","age":21}, {"firstName":"John","lastName":"Doe","age":21}, $)
12:17:36.733 [main] DEBUG com.networknt.schema.TypeValidator - validate( "John", {"firstName":"John","lastName":"Doe","age":21}, $.firstName)
12:17:36.733 [main] DEBUG com.networknt.schema.MinLengthValidator - validate( "John", {"firstName":"John","lastName":"Doe","age":21}, $.firstName)
12:17:36.733 [main] DEBUG com.networknt.schema.TypeValidator - validate( "Doe", {"firstName":"John","lastName":"Doe","age":21}, $.lastName)
12:17:36.734 [main] DEBUG com.networknt.schema.MinLengthValidator - validate( "Doe", {"firstName":"John","lastName":"Doe","age":21}, $.lastName)
12:17:36.734 [main] DEBUG com.networknt.schema.MinimumValidator - validate( 21, {"firstName":"John","lastName":"Doe","age":21}, $.age)
12:17:36.734 [main] DEBUG com.networknt.schema.TypeValidator - validate( 21, {"firstName":"John","lastName":"Doe","age":21}, $.age)
12:17:36.734 [main] DEBUG com.networknt.schema.TypeValidator - validate( {"firstName":"","age":-21}, {"firstName":"","age":-21}, $)
12:17:36.734 [main] DEBUG com.networknt.schema.PropertiesValidator - validate( {"firstName":"","age":-21}, {"firstName":"","age":-21}, $)
12:17:36.734 [main] DEBUG com.networknt.schema.TypeValidator - validate( "", {"firstName":"","age":-21}, $.firstName)
12:17:36.734 [main] DEBUG com.networknt.schema.MinLengthValidator - validate( "", {"firstName":"","age":-21}, $.firstName)
12:17:36.735 [main] DEBUG com.networknt.schema.MinimumValidator - validate( -21, {"firstName":"","age":-21}, $.age)
12:17:36.735 [main] DEBUG com.networknt.schema.TypeValidator - validate( -21, {"firstName":"","age":-21}, $.age)
$.firstName: must be at least 1 characters long
$.age: must have a minimum value of 0
------------------------------------------------------------
justify:
START_OBJECT
KEY_NAME
VALUE_STRING
KEY_NAME
VALUE_STRING
KEY_NAME
VALUE_NUMBER
END_OBJECT
START_OBJECT
KEY_NAME
[2,17][/firstName] 文字列は1文字以上でなければいけませんが、実際の長さは0文字です。
VALUE_STRING
KEY_NAME
[3,12][/age] 数値は0以上でなければいけません。
VALUE_NUMBER
END_OBJECT
------------------------------------------------------------
medeia:
[Validation Failure
------------------
Rule:     properties
Property: firstName
Message:  Property validation failed
Location: at 2:18 in file:/Users/yukithm/projects/compare-kotlin-json-schema-validator/build/resources/main/invalid.json
Details:
    Rule:     minLength
    Message:  String length 0 is smaller than maxLength 1
    Location: at 2:18 in file:/Users/yukithm/projects/compare-kotlin-json-schema-validator/build/resources/main/invalid.json
    -----
]

BUILD SUCCESSFUL in 1s
3 actionable tasks: 1 executed, 2 up-to-date
```
