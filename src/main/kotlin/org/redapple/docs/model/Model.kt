package org.redapple.docs.model

import org.jetbrains.exposed.sql.*
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

object Documents : Table("documents") {
    val id = long("id").autoIncrement().primaryKey()
    val index = varchar("index", 115).nullable()
    val name = varchar("name", 115).nullable()
    val type = varchar("type", 115).nullable()
    val content = text("content").nullable()
    val parent = long("parent").references(Documents.id, ReferenceOption.CASCADE).nullable()
}

data class Document(
        var id: Long? = null, var name: String? = "", var type: String? = "",
        var index: String? = "", var content: String? = "", var parent: Long? = null)


@Repository
@Transactional
open class DocumentDao {
    open fun findAll(): List<Document> = list(Documents.selectAll())

    private fun list(query: Query): List<Document> = query
            .map {
                Document(it[Documents.id], it[Documents.name], it[Documents.type], it[Documents.index],
                        it[Documents.content], it[Documents.parent])
            }

    open fun findAllRoot(): List<Document> = list(Documents.select({ Documents.parent.isNull() })
            .orderBy(Documents.index to true))

    open fun save(document: Document): Document {
        if (document.id != null) {
            Documents.update({ Documents.id eq document.id }) {
                it[name] = document.name.orEmpty()
                it[type] = document.type.orEmpty()
                it[content] = document.content.orEmpty()
                it[index] = document.index.orEmpty()
                if (document.parent != null) it[parent] = document.parent!!
            }
        } else {
            document.id = Documents.insert({
                it[name] = document.name.orEmpty()
                it[type] = document.type.orEmpty()
                it[content] = document.content.orEmpty()
                it[index] = document.index.orEmpty()
                if (document.parent != null) it[parent] = document.parent!!
            })[Documents.id]
        }
        return document
    }

    open fun getChildren(docId: Long): List<Document> = list(Documents.select({ Documents.parent eq docId }))

    open fun getDocument(id: Long): Document? {
        return list(Documents.select({ Documents.id eq id })).firstOrNull()
    }

    open fun delete(id: Long) {
        Documents.deleteWhere { Documents.id eq id }
    }

}