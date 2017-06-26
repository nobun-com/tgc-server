package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.ClassesTimetable;
import com.go2.classes.models.jpa.ClassesTimetableEntity;
import com.go2.classes.models.jpa.ClassesEntity;
import com.go2.classes.models.jpa.TimeTableEntity;

@Component
public class ClassesTimetableServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public ClassesTimetableServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public ClassesTimetable mapClassesTimetableEntityToClassesTimetable(ClassesTimetableEntity classesTimetableEntity) {
		if(classesTimetableEntity == null) {
			return null;
		}

		//--- Generic mapping 
		ClassesTimetable classesTimetable = map(classesTimetableEntity, ClassesTimetable.class);

		//--- Link mapping ( link to Classes )
		if(classesTimetableEntity.getClasses() != null) {
			classesTimetable.setClassesId(classesTimetableEntity.getClasses().getId());
		}
		//--- Link mapping ( link to TimeTable )
		if(classesTimetableEntity.getTimeTable() != null) {
			classesTimetable.setTimetableId(classesTimetableEntity.getTimeTable().getId());
		}
		return classesTimetable;
	}
	
	public void mapClassesTimetableToClassesTimetableEntity(ClassesTimetable classesTimetable, ClassesTimetableEntity classesTimetableEntity) {
		if(classesTimetable == null) {
			return;
		}

		//--- Generic mapping 
		map(classesTimetable, classesTimetableEntity);

		//--- Link mapping ( link : classesTimetable )
		if( hasLinkToClasses(classesTimetable) ) {
			ClassesEntity classes1 = new ClassesEntity();
			classes1.setId( classesTimetable.getClassesId() );
			classesTimetableEntity.setClasses( classes1 );
		} else {
			classesTimetableEntity.setClasses( null );
		}

		//--- Link mapping ( link : classesTimetable )
		if( hasLinkToTimeTable(classesTimetable) ) {
			TimeTableEntity timeTable2 = new TimeTableEntity();
			timeTable2.setId( classesTimetable.getTimetableId() );
			classesTimetableEntity.setTimeTable( timeTable2 );
		} else {
			classesTimetableEntity.setTimeTable( null );
		}

	}
	
	private boolean hasLinkToClasses(ClassesTimetable classesTimetable) {
		if(classesTimetable.getClassesId() != null) {
			return true;
		}
		return false;
	}

	private boolean hasLinkToTimeTable(ClassesTimetable classesTimetable) {
		if(classesTimetable.getTimetableId() != null) {
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