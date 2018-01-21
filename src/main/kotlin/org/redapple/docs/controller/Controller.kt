package org.redapple.docs.controller

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
class Controller @Autowired constructor(val docDao: DocumentDao){
    @GetMapping("/")
    fun indexAction(model: Model): String {
        model.addAttribute("documents", docDao.findAllRoot())
        return "index"
    }

    @PostMapping("add")
    fun add(@ModelAttribute document: Document): String {
        docDao.insert(document)
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
//    @GetMapping("/add")
//    fun add(model: Model, @RequestParam(value = "root", required = false, defaultValue = "-1") root: Long): String {
////        docDao.insert(Document(parent = id))
//        return "redirect:/"
//    }
}