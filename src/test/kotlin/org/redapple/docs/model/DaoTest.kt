package org.redapple.docs.model

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test
import kotlin.test.assertEquals
import org.jetbrains.exposed.sql.SchemaUtils.create

class DaoTest {

    @Test
    fun daoTest() {
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
        transaction {
            val daoDoc =  DocumentDao()
            val daoAttr = AttributeDao()
            val trueDoc = Document(null, "name", "type", "index",
                    "content", null)
            create(Documents, Attributes)
//            save test and get by id
            val newDoc = daoDoc.save(trueDoc)
            val doc = daoDoc.getDocument(newDoc.id!!)
            assertEquals(newDoc, doc)

            // attr test

            val attr = Attribute(null, "name", "type", "value", 123, newDoc.id)
            daoAttr.save(attr)

            assertEquals(daoAttr.findAttributes(newDoc.id!!).first(), attr)

            daoAttr.delete(attr.id!!)

            assert(daoAttr.findAttributes(newDoc.id!!).isEmpty())

            daoAttr.save(Attribute(null, "name", "type", "value", 123, newDoc.id))
            val a = daoAttr.findAttributes(newDoc.id!!).first()
            a.name = "test123"

            daoAttr.save(a)
            val b = daoAttr.findAttributes(newDoc.id!!).first()
            assertEquals(a, b)

            val child = Document(name="child", parent = doc?.id)
            daoDoc.save(child)

            val childs = daoDoc.getChildren(doc?.id!!)
            assert(childs.isNotEmpty() && childs.size == 1)

//          delete test
            daoDoc.delete(childs.first().id!!)  // delete child
            assert(daoDoc.findAll().size == 1)

            daoDoc.save(child)

            daoDoc.delete(doc.id!!) // delete root
            assert(daoDoc.findAll().isEmpty())
            assert(daoAttr.findAll().isEmpty())
        }


    }
}