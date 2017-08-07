package com.go2.classes.business.service;

import java.util.List;
import java.util.Map;

import com.go2.classes.models.Classes;
import com.go2.classes.models.jpa.ClassesEntity;

public interface ClassesService {

    Classes findById(Long id);

    List<Classes> findAll();

    Classes save(Classes entity);

    Classes update(Classes entity);

    Classes create(Classes entity);

    void delete(Long id);

    List<Classes> getAllClassesByCenter(Long centerId);

    List<Classes> getAllClassesByTeacher(Long teacherId);

    List<Map<String, Object>> findAllClassesCategory();

    void bookClass(ClassesEntity classes);
    
    Integer getActiveClassesCount();
    
    Integer getActiveClassesCountByEducator(Long teacherId);
}
