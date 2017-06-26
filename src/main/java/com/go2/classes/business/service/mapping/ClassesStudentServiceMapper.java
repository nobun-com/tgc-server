package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.ClassesStudent;
import com.go2.classes.models.jpa.ClassesStudentEntity;
import com.go2.classes.models.jpa.StudentEntity;
import com.go2.classes.models.jpa.ClassesEntity;

@Component
public class ClassesStudentServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public ClassesStudentServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public ClassesStudent mapClassesStudentEntityToClassesStudent(ClassesStudentEntity classesStudentEntity) {
		if(classesStudentEntity == null) {
			return null;
		}

		ClassesStudent classesStudent = map(classesStudentEntity, ClassesStudent.class);

		if(classesStudentEntity.getStudent() != null) {
			classesStudent.setStudentId(classesStudentEntity.getStudent().getId());
		}
		if(classesStudentEntity.getClasses() != null) {
			classesStudent.setClassesId(classesStudentEntity.getClasses().getId());
		}
		return classesStudent;
	}
	
	public void mapClassesStudentToClassesStudentEntity(ClassesStudent classesStudent, ClassesStudentEntity classesStudentEntity) {
		if(classesStudent == null) {
			return;
		}
		map(classesStudent, classesStudentEntity);
		if( hasLinkToStudent(classesStudent) ) {
			StudentEntity student1 = new StudentEntity();
			student1.setId( classesStudent.getStudentId() );
			classesStudentEntity.setStudent( student1 );
		} else {
			classesStudentEntity.setStudent( null );
		}

		if( hasLinkToClasses(classesStudent) ) {
			ClassesEntity classes2 = new ClassesEntity();
			classes2.setId( classesStudent.getClassesId() );
			classesStudentEntity.setClasses( classes2 );
		} else {
			classesStudentEntity.setClasses( null );
		}

	}
	
	private boolean hasLinkToStudent(ClassesStudent classesStudent) {
		if(classesStudent.getStudentId() != null) {
			return true;
		}
		return false;
	}

	private boolean hasLinkToClasses(ClassesStudent classesStudent) {
		if(classesStudent.getClassesId() != null) {
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