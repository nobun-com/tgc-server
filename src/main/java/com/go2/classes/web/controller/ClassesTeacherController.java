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
import com.go2.classes.models.ClassesTeacher;
import com.go2.classes.models.Teacher;
import com.go2.classes.models.Classes;

//--- Services 
import com.go2.classes.business.service.ClassesTeacherService;
import com.go2.classes.business.service.TeacherService;
import com.go2.classes.business.service.ClassesService;

//--- List Items 
import com.go2.classes.web.listitem.TeacherListItem;
import com.go2.classes.web.listitem.ClassesListItem;

/**
 * Spring MVC controller for 'ClassesTeacher' management.
 */
@Controller
@RequestMapping("/classesTeacher")
public class ClassesTeacherController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "classesTeacher";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "classesTeacher/form";
	private static final String JSP_LIST   = "classesTeacher/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/classesTeacher/create";
	private static final String SAVE_ACTION_UPDATE   = "/classesTeacher/update";

	//--- Main entity service
	@Resource
    private ClassesTeacherService classesTeacherService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private TeacherService teacherService; // Injected by Spring
	@Resource
    private ClassesService classesService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public ClassesTeacherController() {
		super(ClassesTeacherController.class, MAIN_ENTITY_NAME );
		log("ClassesTeacherController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------
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
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param classesTeacher
	 */
	private void populateModel(Model model, ClassesTeacher classesTeacher, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, classesTeacher);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
			populateListOfTeacherItems(model);
			populateListOfClassesItems(model);
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
			populateListOfClassesItems(model);
			populateListOfTeacherItems(model);
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of ClassesTeacher found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<ClassesTeacher> list = classesTeacherService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new ClassesTeacher
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		ClassesTeacher classesTeacher = new ClassesTeacher();	
		populateModel( model, classesTeacher, FormMode.CREATE);
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing ClassesTeacher
	 * @param model Spring MVC model
	 * @param id  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{id}")
	public String formForUpdate(Model model, @PathVariable("id") Integer id ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		ClassesTeacher classesTeacher = classesTeacherService.findById(id);
		populateModel( model, classesTeacher, FormMode.UPDATE);		
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param classesTeacher  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid ClassesTeacher classesTeacher, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				ClassesTeacher classesTeacherCreated = classesTeacherService.create(classesTeacher); 
				model.addAttribute(MAIN_ENTITY_NAME, classesTeacherCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, classesTeacher.getId() );
			} else {
				populateModel( model, classesTeacher, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "classesTeacher.error.create", e);
			populateModel( model, classesTeacher, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param classesTeacher  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid ClassesTeacher classesTeacher, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				ClassesTeacher classesTeacherSaved = classesTeacherService.update(classesTeacher);
				model.addAttribute(MAIN_ENTITY_NAME, classesTeacherSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, classesTeacher.getId());
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, classesTeacher, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "classesTeacher.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, classesTeacher, FormMode.UPDATE);
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
			classesTeacherService.delete( id );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "classesTeacher.error.delete", e);
		}
		return redirectToList();
	}

}
