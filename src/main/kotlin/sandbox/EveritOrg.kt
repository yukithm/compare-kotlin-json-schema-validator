package kotlinsandbox.jsonvalidate

import org.everit.json.schema.ValidationException
import org.everit.json.schema.loader.SchemaLoader
import org.json.JSONObject
import org.json.JSONTokener

class EveritOrg {
    fun run() {
        val schemaJson = loadJson("/schema.json")
        val validJson = loadJson("/valid.json")
        val invalidJson = loadJson("/invalid.json")

        val schema = SchemaLoader.load(schemaJson)
        schema.validate(validJson)
        try {
            schema.validate(invalidJson)
        } catch (e: ValidationException) {
            println(e)
            println(e.toJSON().toString(2))
        }
    }

    fun loadJson(path: String): JSONObject {
        return javaClass.getResourceAsStream(path).use {
            JSONObject(JSONTokener(it))
        }
    }
}
