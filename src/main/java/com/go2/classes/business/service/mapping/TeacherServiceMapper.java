package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.Teacher;
import com.go2.classes.models.jpa.TeacherEntity;

@Component
public class TeacherServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public TeacherServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public Teacher mapTeacherEntityToTeacher(TeacherEntity teacherEntity) {
		if(teacherEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Teacher teacher = map(teacherEntity, Teacher.class);

		return teacher;
	}
	
	public void mapTeacherToTeacherEntity(Teacher teacher, TeacherEntity teacherEntity) {
		if(teacher == null) {
			return;
		}

		//--- Generic mapping 
		map(teacher, teacherEntity);

	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}