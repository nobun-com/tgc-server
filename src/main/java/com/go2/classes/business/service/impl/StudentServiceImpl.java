package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.go2.classes.models.Student;
import com.go2.classes.models.jpa.StudentEntity;
import com.go2.classes.business.service.StudentService;
import com.go2.classes.business.service.mapping.StudentServiceMapper;
import com.go2.classes.data.repository.jpa.StudentJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentJpaRepository studentJpaRepository;

    @Resource
    private StudentServiceMapper studentServiceMapper;

    @Override
    public Student findById(Long id) {
	StudentEntity studentEntity = studentJpaRepository.findOne(id);
	return studentServiceMapper.mapStudentEntityToStudent(studentEntity);
    }

    @Override
    public List<Student> findAll() {
	Iterable<StudentEntity> entities = studentJpaRepository.findAll();
	List<Student> beans = new ArrayList<Student>();
	for (StudentEntity studentEntity : entities) {
	    beans.add(studentServiceMapper.mapStudentEntityToStudent(studentEntity));
	}
	return beans;
    }

    @Override
    public Student save(Student student) {
	return update(student);
    }

    @Override
    public Student create(Student student) {
	StudentEntity studentEntity = null;
	if (!Objects.isNull(student.getEmail())) {
	    studentEntity = studentJpaRepository.findByEmail(student.getEmail());
	}
	if (!Objects.isNull(studentEntity)) {
	    throw new IllegalStateException("user already exists");
	}
	studentEntity = new StudentEntity();
	studentServiceMapper.mapStudentToStudentEntity(student, studentEntity);
	StudentEntity studentEntitySaved = studentJpaRepository.save(studentEntity);
	return studentServiceMapper.mapStudentEntityToStudent(studentEntitySaved);
    }

    @Override
    public Student update(Student student) {
	StudentEntity studentEntity = studentJpaRepository.findOne(student.getId());
	studentServiceMapper.mapStudentToStudentEntity(student, studentEntity);
	StudentEntity studentEntitySaved = studentJpaRepository.save(studentEntity);
	return studentServiceMapper.mapStudentEntityToStudent(studentEntitySaved);
    }

    @Override
    public void delete(Long id) {
	studentJpaRepository.delete(id);
    }

    public StudentJpaRepository getStudentJpaRepository() {
	return studentJpaRepository;
    }

    public void setStudentJpaRepository(StudentJpaRepository studentJpaRepository) {
	this.studentJpaRepository = studentJpaRepository;
    }

    public StudentServiceMapper getStudentServiceMapper() {
	return studentServiceMapper;
    }

    public void setStudentServiceMapper(StudentServiceMapper studentServiceMapper) {
	this.studentServiceMapper = studentServiceMapper;
    }

    @Override
    public Student findByEmail(String email) {
	StudentEntity studentEntity = studentJpaRepository.findByEmail(email);
	if (Objects.isNull(studentEntity)) {
	    return null;
	}
	return studentServiceMapper.mapStudentEntityToStudent(studentEntity);
    }

}
