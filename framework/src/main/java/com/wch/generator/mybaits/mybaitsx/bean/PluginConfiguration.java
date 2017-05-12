package com.wch.generator.mybaits.mybaitsx.bean;

import java.util.List;

import com.wch.generator.mybaits.mybaitsx.dom.Attribute;
import com.wch.generator.mybaits.mybaitsx.dom.XmlElement;
import com.wch.generator.mybaits.mybaitsx.utils.Messages;
import com.wch.generator.mybaits.mybaitsx.utils.StringUtility;


public class PluginConfiguration extends TypedPropertyHolder {
	public XmlElement toXmlElement() {
		XmlElement answer = new XmlElement("plugin");
		if (this.getConfigurationType() != null) {
			answer.addAttribute(new Attribute("type", this.getConfigurationType()));
		}

		this.addPropertyXmlElements(answer);
		return answer;
	}

	public void validate(List<String> errors, String contextId) {
		if (!StringUtility.stringHasValue(this.getConfigurationType())) {
			errors.add(Messages.getString("ValidationError.17", contextId));
		}

	}
}