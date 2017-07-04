package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.Student;
import com.go2.classes.models.jpa.StudentEntity;

@Component
public class StudentServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public StudentServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public Student mapStudentEntityToStudent(StudentEntity studentEntity) {
		if(studentEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Student student = map(studentEntity, Student.class);

		return student;
	}
	
	public void mapStudentToStudentEntity(Student student, StudentEntity studentEntity) {
		if(student == null) {
			return;
		}

		//--- Generic mapping 
		map(student, studentEntity);

	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}