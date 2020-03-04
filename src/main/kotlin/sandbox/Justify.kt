package kotlinsandbox.jsonvalidate

import org.leadpony.justify.api.JsonValidationService
import java.nio.file.Path
import java.nio.file.Paths

class Justify {
    val jsonValidationService = JsonValidationService.newInstance()

    fun run() {
        val schemaJson = jsonPath("/schema.json")
        val validJson = jsonPath("/valid.json")
        val invalidJson = jsonPath("/invalid.json")

        val schema = jsonValidationService.readSchema(schemaJson)
        val handler = jsonValidationService.createProblemPrinter(::println)

        jsonValidationService.createParser(validJson, schema, handler).use {
            while (it.hasNext()) {
                val event = it.next()
                println(event)
            }
        }

        jsonValidationService.createParser(invalidJson, schema, handler).use {
            while (it.hasNext()) {
                val event = it.next()
                println(event)
            }
        }
    }

    fun jsonPath(path: String): Path {
        return Paths.get(javaClass.getResource(path).toURI())
    }
}
