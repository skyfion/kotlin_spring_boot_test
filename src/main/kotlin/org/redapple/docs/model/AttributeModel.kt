package org.redapple.docs.model

import org.jetbrains.exposed.sql.*
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

object Attributes : Table("attributes") {
    val id = long("id").autoIncrement().primaryKey()
    val name = varchar("name", 115).nullable()
    val type = varchar("type", 115).nullable()
    val value = varchar("value", 115).nullable()
    val valueInt = integer("value_int").nullable()
    val docId = long("doc_id").references(Documents.id, ReferenceOption.CASCADE)
}

data class Attribute(
        var id: Long? = null, var name: String? = "", var type: String? = "",
        var value: String? = "", var valueInt: Int? = null, var docId: Long? = null)

@Repository
@Transactional
open class AttributeDao {

    private fun list(query: Query): List<Attribute> = query
            .map {
                Attribute(it[Attributes.id], it[Attributes.name], it[Attributes.type],
                        it[Attributes.value], it[Attributes.valueInt], it[Attributes.docId])
            }

    open fun save(a: Attribute): Attribute {
        if (a.id != null) {
            Attributes.update({Attributes.id eq a.id}) {
                it[name] = a.name.orEmpty()
                it[type] = a.type.orEmpty()
                it[value] = a.value.orEmpty()
                it[valueInt] = a.valueInt
                it[docId] = a.docId!!
            }
        } else {
            a.id = Attributes.insert({
                it[name] = a.name.orEmpty()
                it[type] = a.type.orEmpty()
                it[value] = a.value.orEmpty()
                it[valueInt] = a.valueInt
                it[docId] = a.docId!!
            })[Attributes.id]
        }
        return a
    }

    open fun findAttributes(docId: Long): List<Attribute> {
       return list(Attributes.select({Attributes.docId eq docId}))
    }

    open fun delete(id: Long) {
        Attributes.deleteWhere { Attributes.id eq id }
    }

    open fun findAll(): List<Attribute> = list(Attributes.selectAll())

    open fun find(id: Long): Attribute? {
        return list(Attributes.select({Attributes.id eq id})).firstOrNull()
    }
}