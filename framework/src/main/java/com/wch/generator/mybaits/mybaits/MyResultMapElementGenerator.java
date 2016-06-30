package com.wch.generator.mybaits.mybaits;

import java.util.Iterator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

public class MyResultMapElementGenerator  extends AbstractXmlElementGenerator {
		private boolean isSimple;

		public MyResultMapElementGenerator(boolean isSimple) {
			this.isSimple = isSimple;
		}

		public void addElements( XmlElement parentElement) {
		/*	XmlElement answer = new XmlElement("update");
			
			answer.addAttribute(new Attribute("id", this.introspectedTable.getUpdateByPrimaryKeySelectiveStatementId()));
			String parameterType;
			if (this.introspectedTable.getRules().generateRecordWithBLOBsClass()) {
				parameterType = this.introspectedTable.getRecordWithBLOBsType();
			} else {
				parameterType = this.introspectedTable.getBaseRecordType();
			}

			answer.addAttribute(new Attribute("parameterType", parameterType));
			this.context.getCommentGenerator().addComment(answer);
			StringBuilder sb = new StringBuilder();
			sb.append("update ");
			sb.append(this.introspectedTable.getFullyQualifiedTableNameAtRuntime());
			answer.addElement(new TextElement(sb.toString()));
			XmlElement dynamicElement = new XmlElement("set");
			answer.addElement(dynamicElement);
			Iterator and = this.introspectedTable.getNonPrimaryKeyColumns().iterator();
			*/
			XmlElement answer = new XmlElement("update");
			answer.addAttribute(new Attribute("id",  "updateByIdList"));
			answer.addAttribute(new Attribute("parameterType", "map"));
			this.context.getCommentGenerator().addComment(answer);
			
			
			
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("update ");
			sb.append(this.introspectedTable.getFullyQualifiedTableNameAtRuntime());
			answer.addElement(new TextElement(sb.toString()));
			sb.setLength(0);
			XmlElement dynamicElement = new XmlElement("set");
			answer.addElement(dynamicElement);
			
			Iterator iter;
			if (this.isSimple) {
				iter = this.introspectedTable.getNonPrimaryKeyColumns().iterator();
			} else {
				iter = this.introspectedTable.getBaseColumns().iterator();
			}

			while (iter.hasNext()) {
				
				
			/*	IntrospectedColumn i$ = (IntrospectedColumn) and.next();
				XmlElement introspectedColumn = new XmlElement("if");
				sb.setLength(0);
				sb.append(i$.getJavaProperty());
				sb.append(" != null");
				introspectedColumn.addAttribute(new Attribute("test", sb.toString()));
				dynamicElement.addElement(introspectedColumn);
				sb.setLength(0);
				sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(i$));
				sb.append(" = ");
				sb.append(MyBatis3FormattingUtilities.getParameterClause(i$));
				sb.append(',');
				introspectedColumn.addElement(new TextElement(sb.toString()));*/
				
				IntrospectedColumn i$ = (IntrospectedColumn) iter.next();
				XmlElement introspectedColumn = new XmlElement("if");
				sb.setLength(0);
				sb.append(i$.getJavaProperty());
				sb.append(" != null");
				introspectedColumn.addAttribute(new Attribute("test", sb.toString()));
				dynamicElement.addElement(introspectedColumn);
				sb.setLength(0);
				sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(i$));
				sb.append(" = ");
				sb.append(MyBatis3FormattingUtilities.getParameterClause(i$));
				sb.append(',');
				introspectedColumn.addElement(new TextElement(sb.toString()));
				
				
/*				IntrospectedColumn and = (IntrospectedColumn) iter.next();
				sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(and));
				sb.append(" = ");
				sb.append(MyBatis3FormattingUtilities.getParameterClause(and));
				if (iter.hasNext()) {
					sb.append(',');
				}*/

				answer.addElement(new TextElement(sb.toString()));
				if (iter.hasNext()) {
					sb.setLength(0);
					OutputUtilities.xmlIndent(sb, 1);
				}
			}

			boolean and1 = false;
			Iterator i$ = this.introspectedTable.getPrimaryKeyColumns().iterator();

			while (i$.hasNext()) {
				IntrospectedColumn introspectedColumn = (IntrospectedColumn) i$.next();
				sb.setLength(0);
				if (and1) {
					sb.append("  and ");
				} else {
					sb.append("where ");
					and1 = true;
				}

				sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
				sb.append(" = ");
				sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
				answer.addElement(new TextElement(sb.toString()));
			}

		/*	if (this.context.getPlugins().sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(answer, this.introspectedTable)) {
				parentElement.addElement(answer);
			}*/

		}
}
