package com.wch.generator.mybaits.mybaits;

import java.util.ArrayList;
import java.util.Iterator;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class MyConfiguration extends Configuration {
	
	public void validate() throws InvalidConfigurationException {
		ArrayList errors = new ArrayList();
		Iterator i$ = this.getClassPathEntries().iterator();

		while (i$.hasNext()) {
			String context = (String) i$.next();
			if (!StringUtility.stringHasValue(context)) {
				errors.add(Messages.getString("ValidationError.19"));
				break;
			}
		}

		if (this.getContexts().size() == 0) {
			errors.add(Messages.getString("ValidationError.11"));
		} else {
			i$ = this.getContexts().iterator();

			while (i$.hasNext()) {
				Context context1 = (Context) i$.next();
				context1.validate(errors);
			}
		}

		if (errors.size() > 0) {
			throw new InvalidConfigurationException(errors);
		}
	}
	
	public Document toDocument() {
		Document document = new Document("-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN", "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd");
		XmlElement rootElement = new XmlElement("generatorConfiguration");
		document.setRootElement(rootElement);
		/*Iterator i$ = this.classPathEntries.iterator();

		while (i$.hasNext()) {
			String context = (String) i$.next();
			XmlElement cpeElement = new XmlElement("classPathEntry");
			cpeElement.addAttribute(new Attribute("location", context));
			rootElement.addElement(cpeElement);
		}*/

		Iterator i$ = this.getContexts().iterator();

		while (i$.hasNext()) {
			Context context1 = (Context) i$.next();
			rootElement.addElement(context1.toXmlElement());
		}

		return document;
	}


}
