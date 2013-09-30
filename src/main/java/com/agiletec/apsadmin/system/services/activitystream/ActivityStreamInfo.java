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
package com.agiletec.apsadmin.system.services.activitystream;

import java.util.Properties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author E.Santoboni
 */
@XmlRootElement(name = "activityStreamInfo")
@XmlType(propOrder = {"objectTitles", "objectTitleLabel", "actionType", 
	"linkNamespace", "linkActionName", "linkParameters", "linkAuthPermission", "linkAuthGroup"})
public class ActivityStreamInfo {
	
	public ActivityStreamInfo() {}
	
	@XmlElement(name = "objectTitles", required = false)
	public Properties getObjectTitles() {
		return _objectTitles;
	}
	public void setObjectTitles(Properties objectTitles) {
		this._objectTitles = objectTitles;
	}
	
	@XmlElement(name = "objectTitleLabel", required = false)
	public String getObjectTitleLabel() {
		return _objectTitleLabel;
	}
	public void setObjectTitleLabel(String objectTitleLabel) {
		this._objectTitleLabel = objectTitleLabel;
	}
	
	@XmlElement(name = "actionType", required = false)
	public int getActionType() {
		return _actionType;
	}
	public void setActionType(int actionType) {
		this._actionType = actionType;
	}
	
	@XmlElement(name = "linkNamespace", required = false)
	public String getLinkNamespace() {
		return _linkNamespace;
	}
	public void setLinkNamespace(String linkNamespace) {
		this._linkNamespace = linkNamespace;
	}
	
	@XmlElement(name = "linkActionName", required = false)
	public String getLinkActionName() {
		return _linkActionName;
	}
	public void setLinkActionName(String linkActionName) {
		this._linkActionName = linkActionName;
	}
	
	@XmlElement(name = "linkParameters", required = false)
	public Properties getLinkParameters() {
		return _linkParameters;
	}
	public void setLinkParameters(Properties linkParameters) {
		this._linkParameters = linkParameters;
	}
	public void addLinkParameter(String key, String value) {
		if (null == key || null == value) {
			return;
		}
		if (null == this.getLinkParameters()) {
			this.setLinkParameters(new Properties());
		}
		this.getLinkParameters().setProperty(key, value);
	}
	
	@XmlElement(name = "linkAuthPermission", required = false)
	public String getLinkAuthPermission() {
		return _linkAuthPermission;
	}
	public void setLinkAuthPermission(String linkAuthPermission) {
		this._linkAuthPermission = linkAuthPermission;
	}
	
	@XmlElement(name = "linkAuthGroup", required = false)
	public String getLinkAuthGroup() {
		return _linkAuthGroup;
	}
	public void setLinkAuthGroup(String linkAuthGroup) {
		this._linkAuthGroup = linkAuthGroup;
	}
	
	private Properties _objectTitles;
	private String _objectTitleLabel;
	private int _actionType;
	private String _linkNamespace;
	private String _linkActionName;
	private Properties _linkParameters;
	private String _linkAuthPermission;
	private String _linkAuthGroup;
	
}
