package kotlinsandbox.jsonvalidate

import com.fasterxml.jackson.core.JsonFactory
import com.worldturner.medeia.api.UrlSchemaSource
import com.worldturner.medeia.schema.validation.SchemaValidator
import com.worldturner.medeia.api.*
import com.worldturner.medeia.api.jackson.MedeiaJacksonApi
import java.net.URL

class Medeia {
    val api = MedeiaJacksonApi()
    val jsonFactory = JsonFactory()

    fun run() {
        val validJson = jsonUrl("/valid.json")
        val invalidJson = jsonUrl("/invalid.json")

        val validator = getSchemaValidator()

        val validJsonParser = api.decorateJsonParser(validator, jsonFactory.createParser(validJson))
        api.parseAll(validJsonParser)

        val invalidJsonParser = api.decorateJsonParser(validator, jsonFactory.createParser(invalidJson))
        try {
            api.parseAll(invalidJsonParser)
        } catch (e: ValidationFailedException) {
            println(e.message)
        }
    }

    fun getSchemaValidator(): SchemaValidator {
        val schemaJson = jsonUrl("/schema.json")
        val schemaSource = UrlSchemaSource(schemaJson)
        return api.loadSchema(schemaSource)
    }

    fun jsonUrl(path: String): URL {
        return javaClass.getResource(path)
    }
}
