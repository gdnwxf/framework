package com.wch.generator.mybaits.mybaitsx.beanutils;

import java.util.List;

import com.wch.generator.mybaits.mybaitsx.dom.Attribute;
import com.wch.generator.mybaits.mybaitsx.dom.XmlElement;
import com.wch.generator.mybaits.mybaitsx.utils.Messages;
import com.wch.generator.mybaits.mybaitsx.utils.StringUtility;

public class SqlMapGeneratorConfiguration extends PropertyHolder {
	private String targetPackage;
	private String targetProject;

	public String getTargetProject() {
		return this.targetProject;
	}

	public void setTargetProject(String targetProject) {
		this.targetProject = targetProject;
	}

	public String getTargetPackage() {
		return this.targetPackage;
	}

	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}

	public XmlElement toXmlElement() {
		XmlElement answer = new XmlElement("sqlMapGenerator");
		if (this.targetPackage != null) {
			answer.addAttribute(new Attribute("targetPackage", this.targetPackage));
		}

		if (this.targetProject != null) {
			answer.addAttribute(new Attribute("targetProject", this.targetProject));
		}

		this.addPropertyXmlElements(answer);
		return answer;
	}

	public void validate(List<String> errors, String contextId) {
		if (!StringUtility.stringHasValue(this.targetProject)) {
			errors.add(Messages.getString("ValidationError.1", contextId));
		}

		if (!StringUtility.stringHasValue(this.targetPackage)) {
			errors.add(Messages.getString("ValidationError.12", "SQLMapGenerator", contextId));
		}

	}
}