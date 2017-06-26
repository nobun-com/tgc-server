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
import com.go2.classes.models.CenterAssetEntity;
import com.go2.classes.models.Center;
import com.go2.classes.models.Asset;

//--- Services 
import com.go2.classes.business.service.CenterAssetEntityService;
import com.go2.classes.business.service.CenterService;
import com.go2.classes.business.service.AssetService;

//--- List Items 
import com.go2.classes.web.listitem.CenterListItem;
import com.go2.classes.web.listitem.AssetListItem;

/**
 * Spring MVC controller for 'CenterAssetEntity' management.
 */
@Controller
@RequestMapping("/centerAssetEntity")
public class CenterAssetEntityController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "centerAssetEntity";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "centerAssetEntity/form";
	private static final String JSP_LIST   = "centerAssetEntity/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/centerAssetEntity/create";
	private static final String SAVE_ACTION_UPDATE   = "/centerAssetEntity/update";

	//--- Main entity service
	@Resource
    private CenterAssetEntityService centerAssetEntityService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private CenterService centerService; // Injected by Spring
	@Resource
    private AssetService assetService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public CenterAssetEntityController() {
		super(CenterAssetEntityController.class, MAIN_ENTITY_NAME );
		log("CenterAssetEntityController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------
	/**
	 * Populates the combo-box "items" for the referenced entity "Center"
	 * @param model
	 */
	private void populateListOfCenterItems(Model model) {
		List<Center> list = centerService.findAll();
		List<CenterListItem> items = new LinkedList<CenterListItem>();
		for ( Center center : list ) {
			items.add(new CenterListItem( center ) );
		}
		model.addAttribute("listOfCenterItems", items ) ;
	}

	/**
	 * Populates the combo-box "items" for the referenced entity "Asset"
	 * @param model
	 */
	private void populateListOfAssetItems(Model model) {
		List<Asset> list = assetService.findAll();
		List<AssetListItem> items = new LinkedList<AssetListItem>();
		for ( Asset asset : list ) {
			items.add(new AssetListItem( asset ) );
		}
		model.addAttribute("listOfAssetItems", items ) ;
	}


	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param centerAssetEntity
	 */
	private void populateModel(Model model, CenterAssetEntity centerAssetEntity, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, centerAssetEntity);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
			populateListOfCenterItems(model);
			populateListOfAssetItems(model);
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
			populateListOfCenterItems(model);
			populateListOfAssetItems(model);
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of CenterAssetEntity found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<CenterAssetEntity> list = centerAssetEntityService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new CenterAssetEntity
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		CenterAssetEntity centerAssetEntity = new CenterAssetEntity();	
		populateModel( model, centerAssetEntity, FormMode.CREATE);
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing CenterAssetEntity
	 * @param model Spring MVC model
	 * @param id  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{id}")
	public String formForUpdate(Model model, @PathVariable("id") Long id ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		CenterAssetEntity centerAssetEntity = centerAssetEntityService.findById(id);
		populateModel( model, centerAssetEntity, FormMode.UPDATE);		
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param centerAssetEntity  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid CenterAssetEntity centerAssetEntity, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				CenterAssetEntity centerAssetEntityCreated = centerAssetEntityService.create(centerAssetEntity); 
				model.addAttribute(MAIN_ENTITY_NAME, centerAssetEntityCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, centerAssetEntity.getId() );
			} else {
				populateModel( model, centerAssetEntity, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "centerAssetEntity.error.create", e);
			populateModel( model, centerAssetEntity, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param centerAssetEntity  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid CenterAssetEntity centerAssetEntity, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				CenterAssetEntity centerAssetEntitySaved = centerAssetEntityService.update(centerAssetEntity);
				model.addAttribute(MAIN_ENTITY_NAME, centerAssetEntitySaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, centerAssetEntity.getId());
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, centerAssetEntity, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "centerAssetEntity.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, centerAssetEntity, FormMode.UPDATE);
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
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {
		log("Action 'delete'" );
		try {
			centerAssetEntityService.delete( id );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "centerAssetEntity.error.delete", e);
		}
		return redirectToList();
	}

}
