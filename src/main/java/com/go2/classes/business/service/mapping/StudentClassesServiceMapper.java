package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.StudentClasses;
import com.go2.classes.models.jpa.StudentClassesEntity;
import com.go2.classes.models.jpa.ClassesEntity;
import com.go2.classes.models.jpa.StudentEntity;

@Component
public class StudentClassesServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public StudentClassesServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public StudentClasses mapStudentClassesEntityToStudentClasses(StudentClassesEntity studentClassesEntity) {
		if(studentClassesEntity == null) {
			return null;
		}

		//--- Generic mapping 
		StudentClasses studentClasses = map(studentClassesEntity, StudentClasses.class);

		//--- Link mapping ( link to Classes )
		if(studentClassesEntity.getClasses() != null) {
			studentClasses.setClassesId(studentClassesEntity.getClasses().getId());
		}
		//--- Link mapping ( link to Student )
		if(studentClassesEntity.getStudent() != null) {
			studentClasses.setStudentId(studentClassesEntity.getStudent().getId());
		}
		return studentClasses;
	}
	
	public void mapStudentClassesToStudentClassesEntity(StudentClasses studentClasses, StudentClassesEntity studentClassesEntity) {
		if(studentClasses == null) {
			return;
		}

		//--- Generic mapping 
		map(studentClasses, studentClassesEntity);

		//--- Link mapping ( link : studentClasses )
		if( hasLinkToClasses(studentClasses) ) {
			ClassesEntity classes1 = new ClassesEntity();
			classes1.setId( studentClasses.getClassesId() );
			studentClassesEntity.setClasses( classes1 );
		} else {
			studentClassesEntity.setClasses( null );
		}

		//--- Link mapping ( link : studentClasses )
		if( hasLinkToStudent(studentClasses) ) {
			StudentEntity student2 = new StudentEntity();
			student2.setId( studentClasses.getStudentId() );
			studentClassesEntity.setStudent( student2 );
		} else {
			studentClassesEntity.setStudent( null );
		}

	}
	
	private boolean hasLinkToClasses(StudentClasses studentClasses) {
		if(studentClasses.getClassesId() != null) {
			return true;
		}
		return false;
	}

	private boolean hasLinkToStudent(StudentClasses studentClasses) {
		if(studentClasses.getStudentId() != null) {
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