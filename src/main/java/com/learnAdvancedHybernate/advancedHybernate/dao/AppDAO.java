package com.learnAdvancedHybernate.advancedHybernate.dao;

import com.learnAdvancedHybernate.advancedHybernate.entity.Instructor;
import com.learnAdvancedHybernate.advancedHybernate.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);
}
