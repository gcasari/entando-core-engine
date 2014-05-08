/*
*
* Copyright 2013 Entando S.r.l. (http://www.entando.com) All rights reserved.
*
* This file is part of Entando Enterprise Edition software.
* You can redistribute it and/or modify it
* under the terms of the Entando's EULA
* 
* See the file License for the specific language governing permissions   
* and limitations under the License
* 
* 
* 
* Copyright 2013 Entando S.r.l. (http://www.entando.com) All rights reserved.
*
*/
package org.entando.entando.apsadmin.portal.guifragment;

import com.agiletec.apsadmin.system.ApsAdminSystemConstants;
import com.agiletec.apsadmin.system.BaseAction;

import org.entando.entando.aps.system.services.guifragment.GuiFragment;
import org.entando.entando.aps.system.services.guifragment.IGuiFragmentManager;
import org.entando.entando.aps.system.services.widgettype.IWidgetTypeManager;
import org.entando.entando.aps.system.services.widgettype.WidgetType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author E.Santoboni
 */
public class GuiFragmentAction extends BaseAction {
	
	private static final Logger _logger =  LoggerFactory.getLogger(GuiFragmentAction.class);
	
	public String newGuiFragment() {
		try {
			this.setStrutsAction(ApsAdminSystemConstants.ADD);
		} catch (Throwable t) {
			_logger.error("error in newGuiFragment", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	public String edit() {
		try {
			GuiFragment guiFragment = this.getGuiFragmentManager().getGuiFragment(this.getCode());
			if (null == guiFragment) {
				this.addActionError(this.getText("error.guiFragment.null"));
				return INPUT;
			}
			this.populateForm(guiFragment);
			this.setStrutsAction(ApsAdminSystemConstants.EDIT);
		} catch (Throwable t) {
			_logger.error("error in edit", t);
			return FAILURE;
		}
		return SUCCESS;
	}

	public String save() {
		try {
			int strutsAction = this.getStrutsAction();
			GuiFragment guiFragment = this.createGuiFragment();
			if (ApsAdminSystemConstants.ADD == strutsAction) {
				this.getGuiFragmentManager().addGuiFragment(guiFragment);
			} else if (ApsAdminSystemConstants.EDIT == strutsAction) {
				this.getGuiFragmentManager().updateGuiFragment(guiFragment);
			}
		} catch (Throwable t) {
			_logger.error("error in save", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	public String trash() {
		try {
			GuiFragment guiFragment = this.getGuiFragmentManager().getGuiFragment(this.getCode());
			if (null == guiFragment) {
				this.addActionError(this.getText("error.guiFragment.null"));
				return INPUT;
			}
			this.populateForm(guiFragment);
			this.setStrutsAction(ApsAdminSystemConstants.DELETE);
		} catch (Throwable t) {
			_logger.error("error in trash", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	public String delete() {
		try {
			if (this.getStrutsAction() == ApsAdminSystemConstants.DELETE) {
				this.getGuiFragmentManager().deleteGuiFragment(this.getCode());
			}
		} catch (Throwable t) {
			_logger.error("error in delete", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	public String view() {
		try {
			GuiFragment guiFragment = this.getGuiFragmentManager().getGuiFragment(this.getCode());
			if (null == guiFragment) {
				this.addActionError(this.getText("error.guiFragment.null"));
				return INPUT;
			}
			this.populateForm(guiFragment);
		} catch (Throwable t) {
			_logger.error("error in view", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	private void populateForm(GuiFragment guiFragment) throws Throwable {
		this.setCode(guiFragment.getCode());
		this.setWidgetTypeCode(guiFragment.getWidgetTypeCode());
		this.setPluginCode(guiFragment.getPluginCode());
		this.setGui(guiFragment.getGui());
		this.setDefaultGui(guiFragment.getDefaultGui());
	}
	
	private GuiFragment createGuiFragment() throws Throwable {
		GuiFragment guiFragment = null;
		int strutsAction = this.getStrutsAction();
		if (ApsAdminSystemConstants.ADD == strutsAction) {
			guiFragment = new GuiFragment();
			guiFragment.setCode(this.getCode());
		} else {
			guiFragment = this.getGuiFragmentManager().getGuiFragment(this.getCode());
		}
		//GuiFragment guiFragment = new GuiFragment();
		//guiFragment.setWidgetTypeCode(this.getWidgetTypeCode());
		//guiFragment.setPluginCode(this.getPluginCode());
		guiFragment.setGui(this.getGui());
		return guiFragment;
	}
	
	public WidgetType getWidgetType(String widgetTypeCode) {
		return this.getWidgetTypeManager().getWidgetType(widgetTypeCode);
	}
	
	public int getStrutsAction() {
		return _strutsAction;
	}
	public void setStrutsAction(int strutsAction) {
		this._strutsAction = strutsAction;
	}
	
	public String getCode() {
		return _code;
	}
	public void setCode(String code) {
		this._code = code;
	}
	
	@Deprecated
	public String getWidgetCode() {
		return this.getWidgetTypeCode();
	}
	@Deprecated
	public void setWidgetCode(String widgetCode) {
		this.setWidgetTypeCode(widgetCode);
	}
	
	public String getWidgetTypeCode() {
		return _widgetTypeCode;
	}
	public void setWidgetTypeCode(String widgetTypeCode) {
		this._widgetTypeCode = widgetTypeCode;
	}
	
	public String getPluginCode() {
		return _pluginCode;
	}
	public void setPluginCode(String pluginCode) {
		this._pluginCode = pluginCode;
	}
	
	public String getGui() {
		return _gui;
	}
	public void setGui(String gui) {
		this._gui = gui;
	}
	
	public String getDefaultGui() {
		return _defaultGui;
	}
	public void setDefaultGui(String defaultGui) {
		this._defaultGui = defaultGui;
	}
	
	protected IGuiFragmentManager getGuiFragmentManager() {
		return _guiFragmentManager;
	}
	public void setGuiFragmentManager(IGuiFragmentManager guiFragmentManager) {
		this._guiFragmentManager = guiFragmentManager;
	}
	
	protected IWidgetTypeManager getWidgetTypeManager() {
		return _widgetTypeManager;
	}
	public void setWidgetTypeManager(IWidgetTypeManager widgetTypeManager) {
		this._widgetTypeManager = widgetTypeManager;
	}
	
	private int _strutsAction;
	private String _code;
	private String _widgetTypeCode;
	private String _pluginCode;
	private String _gui;
	private String _defaultGui;
	
	private IGuiFragmentManager _guiFragmentManager;
	private IWidgetTypeManager _widgetTypeManager;
	
}