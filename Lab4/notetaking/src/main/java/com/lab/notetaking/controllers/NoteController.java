package com.lab.notetaking.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.lab.notetaking.dao.NoteDao;
import com.lab.notetaking.models.Note;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {
    @Autowired
    NoteDao noteDao;

    private Parser parser = Parser.builder().build();
    
    private HtmlRenderer renderer = HtmlRenderer.builder().build();
    
    private void getAllNotes(Model model) {
        List<Note> notes = noteDao.findAll();
        Collections.reverse(notes);
        model.addAttribute("notes", notes);
    }

    private void saveNote(String description, Model model) {

        if (description != null && !description.trim().isEmpty()) {
            Note note = new Note();
            Node document = parser.parse(description.trim());
            String html = renderer.render(document);
            note.setDescription(html);
            noteDao.save(note);

          //After publish you need to clean up the textarea
          model.addAttribute("description", "");
        }
      }

      @GetMapping("/")
      public String index(Model model) {
          getAllNotes(model);
          
          return "index";
      }
      
      @PostMapping("/note")
      public String saveNotes(@RequestParam String description, Model model) throws IOException {
          saveNote(description, model);
          getAllNotes(model);

          return "redirect:/";
      }
}
