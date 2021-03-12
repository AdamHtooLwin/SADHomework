package com.lab.notetaking.dao;

import com.lab.notetaking.models.Note;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteDao extends JpaRepository<Note, Integer>{
    
}
