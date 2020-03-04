package kotlinsandbox.jsonvalidate

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.networknt.schema.JsonSchemaFactory
import com.networknt.schema.SpecVersion

class Networknt {
    val objectMapper = jacksonObjectMapper()
    val schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7)

    fun run() {
        val schemaJson = loadJson("/schema.json")
        val validJson = loadJson("/valid.json")
        val invalidJson = loadJson("/invalid.json")

        val schema = schemaFactory.getSchema(schemaJson)
        schema.validate(validJson)
        val errors = schema.validate(invalidJson)
        errors.forEach {
            println(it.message)
        }
    }

    fun loadJson(path: String): JsonNode {
        return objectMapper.readTree(javaClass.getResource(path))
    }
}
