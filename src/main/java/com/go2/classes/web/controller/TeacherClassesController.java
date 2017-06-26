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
import com.go2.classes.models.TeacherClasses;
import com.go2.classes.models.Classes;
import com.go2.classes.models.Teacher;

//--- Services 
import com.go2.classes.business.service.TeacherClassesService;
import com.go2.classes.business.service.ClassesService;
import com.go2.classes.business.service.TeacherService;

//--- List Items 
import com.go2.classes.web.listitem.ClassesListItem;
import com.go2.classes.web.listitem.TeacherListItem;

/**
 * Spring MVC controller for 'TeacherClasses' management.
 */
@Controller
@RequestMapping("/teacherClasses")
public class TeacherClassesController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "teacherClasses";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "teacherClasses/form";
	private static final String JSP_LIST   = "teacherClasses/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/teacherClasses/create";
	private static final String SAVE_ACTION_UPDATE   = "/teacherClasses/update";

	//--- Main entity service
	@Resource
    private TeacherClassesService teacherClassesService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private ClassesService classesService; // Injected by Spring
	@Resource
    private TeacherService teacherService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public TeacherClassesController() {
		super(TeacherClassesController.class, MAIN_ENTITY_NAME );
		log("TeacherClassesController created.");
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
	 * Populates the combo-box "items" for the referenced entity "Teacher"
	 * @param model
	 */
	private void populateListOfTeacherItems(Model model) {
		List<Teacher> list = teacherService.findAll();
		List<TeacherListItem> items = new LinkedList<TeacherListItem>();
		for ( Teacher teacher : list ) {
			items.add(new TeacherListItem( teacher ) );
		}
		model.addAttribute("listOfTeacherItems", items ) ;
	}


	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param teacherClasses
	 */
	private void populateModel(Model model, TeacherClasses teacherClasses, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, teacherClasses);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
			populateListOfClassesItems(model);
			populateListOfTeacherItems(model);
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
			populateListOfTeacherItems(model);
			populateListOfClassesItems(model);
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of TeacherClasses found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<TeacherClasses> list = teacherClassesService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new TeacherClasses
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		TeacherClasses teacherClasses = new TeacherClasses();	
		populateModel( model, teacherClasses, FormMode.CREATE);
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing TeacherClasses
	 * @param model Spring MVC model
	 * @param id  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{id}")
	public String formForUpdate(Model model, @PathVariable("id") Integer id ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		TeacherClasses teacherClasses = teacherClassesService.findById(id);
		populateModel( model, teacherClasses, FormMode.UPDATE);		
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param teacherClasses  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid TeacherClasses teacherClasses, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				TeacherClasses teacherClassesCreated = teacherClassesService.create(teacherClasses); 
				model.addAttribute(MAIN_ENTITY_NAME, teacherClassesCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, teacherClasses.getId() );
			} else {
				populateModel( model, teacherClasses, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "teacherClasses.error.create", e);
			populateModel( model, teacherClasses, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param teacherClasses  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid TeacherClasses teacherClasses, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				TeacherClasses teacherClassesSaved = teacherClassesService.update(teacherClasses);
				model.addAttribute(MAIN_ENTITY_NAME, teacherClassesSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, teacherClasses.getId());
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, teacherClasses, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "teacherClasses.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, teacherClasses, FormMode.UPDATE);
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
			teacherClassesService.delete( id );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "teacherClasses.error.delete", e);
		}
		return redirectToList();
	}

}
