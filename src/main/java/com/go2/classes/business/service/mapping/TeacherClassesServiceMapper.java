package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.TeacherClasses;
import com.go2.classes.models.jpa.TeacherClassesEntity;
import com.go2.classes.models.jpa.ClassesEntity;
import com.go2.classes.models.jpa.TeacherEntity;

@Component
public class TeacherClassesServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public TeacherClassesServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public TeacherClasses mapTeacherClassesEntityToTeacherClasses(TeacherClassesEntity teacherClassesEntity) {
		if(teacherClassesEntity == null) {
			return null;
		}

		//--- Generic mapping 
		TeacherClasses teacherClasses = map(teacherClassesEntity, TeacherClasses.class);

		//--- Link mapping ( link to Classes )
		if(teacherClassesEntity.getClasses() != null) {
			teacherClasses.setClassesId(teacherClassesEntity.getClasses().getId());
		}
		//--- Link mapping ( link to Teacher )
		if(teacherClassesEntity.getTeacher() != null) {
			teacherClasses.setTeacherId(teacherClassesEntity.getTeacher().getId());
		}
		return teacherClasses;
	}
	
	public void mapTeacherClassesToTeacherClassesEntity(TeacherClasses teacherClasses, TeacherClassesEntity teacherClassesEntity) {
		if(teacherClasses == null) {
			return;
		}

		//--- Generic mapping 
		map(teacherClasses, teacherClassesEntity);

		//--- Link mapping ( link : teacherClasses )
		if( hasLinkToClasses(teacherClasses) ) {
			ClassesEntity classes1 = new ClassesEntity();
			classes1.setId( teacherClasses.getClassesId() );
			teacherClassesEntity.setClasses( classes1 );
		} else {
			teacherClassesEntity.setClasses( null );
		}

		//--- Link mapping ( link : teacherClasses )
		if( hasLinkToTeacher(teacherClasses) ) {
			TeacherEntity teacher2 = new TeacherEntity();
			teacher2.setId( teacherClasses.getTeacherId() );
			teacherClassesEntity.setTeacher( teacher2 );
		} else {
			teacherClassesEntity.setTeacher( null );
		}

	}
	
	private boolean hasLinkToClasses(TeacherClasses teacherClasses) {
		if(teacherClasses.getClassesId() != null) {
			return true;
		}
		return false;
	}

	private boolean hasLinkToTeacher(TeacherClasses teacherClasses) {
		if(teacherClasses.getTeacherId() != null) {
			return true;
		}
		return false;
	}

	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}