package com.go2.classes.web.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//--- Common classes
import com.go2.classes.web.common.AbstractController;
import com.go2.classes.web.common.FormMode;
import com.go2.classes.web.common.Message;
import com.go2.classes.web.common.MessageType;

//--- Entities
import com.go2.classes.models.ClassesTimetable;
import com.go2.classes.models.Classes;
import com.go2.classes.models.TimeTable;

//--- Services 
import com.go2.classes.business.service.ClassesTimetableService;
import com.go2.classes.business.service.ClassesService;
import com.go2.classes.business.service.TimeTableService;

//--- List Items 
import com.go2.classes.web.listitem.ClassesListItem;
import com.go2.classes.web.listitem.TimeTableListItem;

/**
 * Spring MVC controller for 'ClassesTimetable' management.
 */
@Controller
@RequestMapping("/classesTimetable")
public class ClassesTimetableController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "classesTimetable";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "classesTimetable/form";
	private static final String JSP_LIST   = "classesTimetable/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/classesTimetable/create";
	private static final String SAVE_ACTION_UPDATE   = "/classesTimetable/update";

	//--- Main entity service
	@Resource
    private ClassesTimetableService classesTimetableService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private ClassesService classesService; // Injected by Spring
	@Resource
    private TimeTableService timeTableService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public ClassesTimetableController() {
		super(ClassesTimetableController.class, MAIN_ENTITY_NAME );
		log("ClassesTimetableController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------
	/**
	 * Populates the combo-box "items" for the referenced entity "Classes"
	 * @param model
	 */
	private void populateListOfClassesItems(Model model) {
		List<Classes> list = classesService.findAll();
		List<ClassesListItem> items = new LinkedList<ClassesListItem>();
		for ( Classes classes : list ) {
			items.add(new ClassesListItem( classes ) );
		}
		model.addAttribute("listOfClassesItems", items ) ;
	}

	/**
	 * Populates the combo-box "items" for the referenced entity "TimeTable"
	 * @param model
	 */
	private void populateListOfTimeTableItems(Model model) {
		List<TimeTable> list = timeTableService.findAll();
		List<TimeTableListItem> items = new LinkedList<TimeTableListItem>();
		for ( TimeTable timeTable : list ) {
			items.add(new TimeTableListItem( timeTable ) );
		}
		model.addAttribute("listOfTimeTableItems", items ) ;
	}


	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param classesTimetable
	 */
	private void populateModel(Model model, ClassesTimetable classesTimetable, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, classesTimetable);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
			populateListOfClassesItems(model);
			populateListOfTimeTableItems(model);
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
			populateListOfClassesItems(model);
			populateListOfTimeTableItems(model);
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of ClassesTimetable found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<ClassesTimetable> list = classesTimetableService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new ClassesTimetable
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		ClassesTimetable classesTimetable = new ClassesTimetable();	
		populateModel( model, classesTimetable, FormMode.CREATE);
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing ClassesTimetable
	 * @param model Spring MVC model
	 * @param id  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{id}")
	public String formForUpdate(Model model, @PathVariable("id") Integer id ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		ClassesTimetable classesTimetable = classesTimetableService.findById(id);
		populateModel( model, classesTimetable, FormMode.UPDATE);		
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param classesTimetable  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid ClassesTimetable classesTimetable, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				ClassesTimetable classesTimetableCreated = classesTimetableService.create(classesTimetable); 
				model.addAttribute(MAIN_ENTITY_NAME, classesTimetableCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, classesTimetable.getId() );
			} else {
				populateModel( model, classesTimetable, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "classesTimetable.error.create", e);
			populateModel( model, classesTimetable, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param classesTimetable  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid ClassesTimetable classesTimetable, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				ClassesTimetable classesTimetableSaved = classesTimetableService.update(classesTimetable);
				model.addAttribute(MAIN_ENTITY_NAME, classesTimetableSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, classesTimetable.getId());
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, classesTimetable, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "classesTimetable.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, classesTimetable, FormMode.UPDATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'DELETE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param redirectAttributes
	 * @param id  primary key element
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}") // GET or POST
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("id") Integer id) {
		log("Action 'delete'" );
		try {
			classesTimetableService.delete( id );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "classesTimetable.error.delete", e);
		}
		return redirectToList();
	}

}
