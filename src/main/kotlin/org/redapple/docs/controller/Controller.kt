package org.redapple.docs.controller

import org.redapple.docs.model.Attribute
import org.redapple.docs.model.AttributeDao
import org.redapple.docs.model.Document
import org.redapple.docs.model.DocumentDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@EnableAutoConfiguration
class Controller @Autowired constructor(val docDao: DocumentDao, val aDao: AttributeDao) {
    @GetMapping("/")
    fun indexAction(model: Model): String {
        model.addAttribute("documents", docDao.findAllRoot())
        return "index"
    }

    @PostMapping("add")
    fun add(@ModelAttribute document: Document): String {
        docDao.save(document)
        if (document.parent != null)
            return "redirect:/enter?id=" + document.parent
        return "redirect:/"
    }

    @GetMapping("/enter")
    fun enter(model: Model, @RequestParam(value = "id", required = true) id: Long): String {
        model.addAttribute("documents", docDao.getChildren(id))
        model.addAttribute("root", id)
        return "index"
    }

    @GetMapping("/delete")
    fun delete(model: Model, @RequestParam(value = "id", required = true) id: Long): String {
        docDao.delete(id)
        return ""
    }

    @GetMapping("/edit")
    fun docEdit(model: Model, @RequestParam(value = "root", required = false, defaultValue = "") root: String,
                @RequestParam(value = "id", required = false, defaultValue = "") id: String): String {
        if (root.isNotEmpty()) model.addAttribute("root", root)
        if (id.isNotEmpty()) {
            val doc = docDao.getDocument(id.toLong())
            if (doc != null)
                model.addAttribute("document", doc)
        }
        return "doc_edit"
    }

    @GetMapping("/attributes")
    fun attributes(model: Model, @RequestParam(value = "id", required = true) docId: Long): String {
        model.addAttribute("attributes", aDao.findAttributes(docId))
        model.addAttribute("docId", docId)
        return "attributes"
    }

    @GetMapping("/attributes/add")
    fun addAttribute(model: Model, @RequestParam(value = "docId", required = true) docId: Long): String {
        model.addAttribute("docId", docId)
        return "attr_edit"
    }

    @GetMapping("/attribute/delete")
    fun deleteAttribute(@RequestParam(value = "id", required = true) id: Long,
                        @RequestParam(value = "docId", required = true) docId: Long): String {
        aDao.delete(id)
        return "redirect:/attributes?id=" + docId
    }

    @GetMapping("/attribute/edit")
    fun editAttribute(model: Model, @RequestParam(value = "id", required = true) id: Long): String {
        model.addAttribute("attribute", aDao.find(id))
        return "attr_edit"
    }

    @PostMapping("/attribute/save")
    fun saveAttribute(@ModelAttribute attribute: Attribute): String {
        aDao.save(attribute)
        return "redirect:/attributes?id=" + attribute.docId
    }
}