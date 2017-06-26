package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.ClassesTeacher;
import com.go2.classes.models.jpa.ClassesTeacherEntity;
import com.go2.classes.models.jpa.TeacherEntity;
import com.go2.classes.models.jpa.ClassesEntity;

@Component
public class ClassesTeacherServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public ClassesTeacherServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public ClassesTeacher mapClassesTeacherEntityToClassesTeacher(ClassesTeacherEntity classesTeacherEntity) {
		if(classesTeacherEntity == null) {
			return null;
		}

		//--- Generic mapping 
		ClassesTeacher classesTeacher = map(classesTeacherEntity, ClassesTeacher.class);

		//--- Link mapping ( link to Teacher )
		if(classesTeacherEntity.getTeacher() != null) {
			classesTeacher.setTeacherId(classesTeacherEntity.getTeacher().getId());
		}
		//--- Link mapping ( link to Classes )
		if(classesTeacherEntity.getClasses() != null) {
			classesTeacher.setClassesId(classesTeacherEntity.getClasses().getId());
		}
		return classesTeacher;
	}
	
	public void mapClassesTeacherToClassesTeacherEntity(ClassesTeacher classesTeacher, ClassesTeacherEntity classesTeacherEntity) {
		if(classesTeacher == null) {
			return;
		}

		map(classesTeacher, classesTeacherEntity);

		if( hasLinkToTeacher(classesTeacher) ) {
			TeacherEntity teacher1 = new TeacherEntity();
			teacher1.setId( classesTeacher.getTeacherId() );
			classesTeacherEntity.setTeacher( teacher1 );
		} else {
			classesTeacherEntity.setTeacher( null );
		}

		//--- Link mapping ( link : classesTeacher )
		if( hasLinkToClasses(classesTeacher) ) {
			ClassesEntity classes2 = new ClassesEntity();
			classes2.setId( classesTeacher.getClassesId() );
			classesTeacherEntity.setClasses( classes2 );
		} else {
			classesTeacherEntity.setClasses( null );
		}

	}
	
	private boolean hasLinkToTeacher(ClassesTeacher classesTeacher) {
		if(classesTeacher.getTeacherId() != null) {
			return true;
		}
		return false;
	}

	private boolean hasLinkToClasses(ClassesTeacher classesTeacher) {
		if(classesTeacher.getClassesId() != null) {
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