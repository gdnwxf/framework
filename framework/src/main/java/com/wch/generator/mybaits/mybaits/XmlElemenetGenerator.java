package com.wch.generator.mybaits.mybaits;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.ibatis.javassist.bytecode.annotation.BooleanMemberValue;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

public class XmlElemenetGenerator {
	
	public static void getXml( IntrospectedTable introspectedTable ,boolean isSimple) {
		
		XmlElement answer = new XmlElement("update");
		answer.addAttribute(new Attribute("id",  "updateByIdList"));
		answer.addAttribute(new Attribute("parameterType", "map"));
//		context.getCommentGenerator().addComment(answer);
		
		
		
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("update ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		answer.addElement(new TextElement(sb.toString()));
		sb.setLength(0);
		XmlElement dynamicElement = new XmlElement("set");
		answer.addElement(dynamicElement);
		
		Iterator iter;
		if (isSimple) {
			iter = introspectedTable.getNonPrimaryKeyColumns().iterator();
		} else {
			iter = introspectedTable.getBaseColumns().iterator();
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
			sb.append("bean."+i$.getJavaProperty());
			sb.append(" != null");
			introspectedColumn.addAttribute(new Attribute("test", sb.toString()));
			dynamicElement.addElement(introspectedColumn);
			sb.setLength(0);
			sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(i$));
			sb.append(" = ");
			sb.append(MyBatis3FormattingUtilities.getParameterClause(i$,"bean."));
			sb.append(',');
			introspectedColumn.addElement(new TextElement(sb.toString()));
			
			
/*				IntrospectedColumn and = (IntrospectedColumn) iter.next();
			sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(and));
			sb.append(" = ");
			sb.append(MyBatis3FormattingUtilities.getParameterClause(and));
			if (iter.hasNext()) {
				sb.append(',');
			}*/

			if(MyBatis3FormattingUtilities.getEscapedColumnName(i$).equalsIgnoreCase("optime") 
					|| MyBatis3FormattingUtilities.getEscapedColumnName(i$).equalsIgnoreCase("opuserid") )
			answer.addElement(new TextElement(sb.toString()));
			if (iter.hasNext()) {
				sb.setLength(0);
				OutputUtilities.xmlIndent(sb, 1);
			}
		}
		answer.addElement(new TextElement("lastVer = lastVer + 1"));
		
		
		boolean and1 = false;
		Document document = null;
		String formartXml =  null;
		
		
		Iterator i$ = introspectedTable.getPrimaryKeyColumns().iterator();

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
			 answer.addElement(new TextElement(sb.toString()));
			 sb.setLength(0);
			 OutputUtilities.xmlIndent(sb, 1);
			 
			 
			 
		       XmlElement outerForEachElement = new XmlElement("foreach");
			/*    <foreach collection="idList" item="id" open="(" close=")" separator=",">
		        #{id,jdbcType=VARCHAR}
		    </foreach>*/
			    outerForEachElement.addAttribute(new Attribute("collection", "idList"));
			    outerForEachElement.addAttribute(new Attribute("item", "id"));
			    outerForEachElement.addAttribute(new Attribute("open", " in ("));
			    outerForEachElement.addAttribute(new Attribute("close", ")"));
			    outerForEachElement.addAttribute(new Attribute("separator", ","));
			    sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
			    outerForEachElement.addElement( new TextElement(sb.toString()));
			    answer.addElement(outerForEachElement);
//			    answer.addElement(new TextElement(sb.toString()));
		}
		

	    XmlElement outerForEachElement = new XmlElement("foreach");
	/*    <foreach collection="idList" item="id" open="(" close=")" separator=",">
        #{id,jdbcType=VARCHAR}
    </foreach>*/
	    outerForEachElement.addAttribute(new Attribute("collection", "idList"));
	    outerForEachElement.addAttribute(new Attribute("item", "item"));
	    outerForEachElement.addAttribute(new Attribute("open", "("));
	    outerForEachElement.addAttribute(new Attribute("close", ")"));
	    outerForEachElement.addAttribute(new Attribute("separator", ","));
		
		  document = new Document();
		document.setRootElement(answer);
	    formartXml = document.getFormattedContent();
		System.out.println(formartXml);
		
	}
	
	
	public static void getXmlUpdateList( IntrospectedTable introspectedTable ,boolean isSimple) {
		XmlElement answer = new XmlElement("insert");
		answer.addAttribute(new Attribute("id", "updateList"));
		answer.addAttribute(new Attribute("parameterType", "java.util.List"));
	/*	FullyQualifiedJavaType  parameterType = null ;
		if (isSimple) {
			parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		} else {
			parameterType = introspectedTable.getRules().calculateAllFieldsClass();
		}
*/

		StringBuilder temp = new StringBuilder();

		StringBuilder insertClause1 = new StringBuilder();
		StringBuilder textClause1 = new StringBuilder();
		StringBuilder valuesClause = new StringBuilder();
		insertClause1.append("insert into ");
		insertClause1.append(introspectedTable.getFullyQualifiedTable());
		insertClause1.append(" (");


	
		ArrayList valuesClauses = new ArrayList();
		Iterator iter = introspectedTable.getAllColumns().iterator();
		Iterator expectPrimaryKey = introspectedTable.getNonPrimaryKeyColumns().iterator();
		
		textClause1.append("    ON DUPLICATE KEY UPDATE \n");
		textClause1.append("\t");
		while (expectPrimaryKey.hasNext()) {
			IntrospectedColumn i$ = (IntrospectedColumn) expectPrimaryKey.next();
			if (!i$.isIdentity()) {
				String tempStr = MyBatis3FormattingUtilities.getEscapedColumnName(i$);
				if("lastver".equalsIgnoreCase(i$.getJavaProperty())) {
					textClause1.append(tempStr).append("=").append(tempStr).append("+1");
					temp.append(tempStr).append("=").append(tempStr).append("+1");
				}else {
					textClause1.append(tempStr)
					.append("=COALESCE(VALUES(").append(tempStr)
					.append("),").append(tempStr).append(")");
					temp.append(tempStr)
					.append("=COALESCE(VALUES(").append(tempStr)
					.append("),").append(tempStr).append(")");
				}
			
				if (expectPrimaryKey.hasNext()) {
					textClause1.append(", ");
					temp.append(", ");
				}

				if (temp.length() > 100) {
					textClause1.append("\n\t");
					temp.setLength(0);
					OutputUtilities.xmlIndent(textClause1, 1);
				}
			}
		}

		textClause1.append(";");
		
		valuesClause.append("(");
		while (iter.hasNext()) {
			IntrospectedColumn i$ = (IntrospectedColumn) iter.next();
			if (!i$.isIdentity()) {
				String tempStr = MyBatis3FormattingUtilities.getEscapedColumnName(i$);
				insertClause1.append(tempStr);
				if(i$.getJavaProperty().equalsIgnoreCase("lastver") || i$.getJavaProperty().equalsIgnoreCase("isvalid"))
				{
					valuesClause.append( "1" );
					
				}else {
					valuesClause.append(  MyBatis3FormattingUtilities.getParameterClause(i$, "item."));
				}
				if (iter.hasNext()) {
					insertClause1.append(", ");
					valuesClause.append(", ");
				}

				if (valuesClause.length() > 80) {
					answer.addElement(new TextElement(insertClause1.toString()));
					insertClause1.setLength(0);
					OutputUtilities.xmlIndent(insertClause1, 1);
					valuesClauses.add(valuesClause.toString());
					valuesClause.setLength(0);
					OutputUtilities.xmlIndent(valuesClause, 1);
				}
			}
		}
		insertClause1.append(')');
		if(insertClause1.length() > 0) 
		answer.addElement(new TextElement(insertClause1.toString()));
		answer.addElement(new TextElement(" values "));
		
	    XmlElement outerForEachElement = new XmlElement("foreach");
		/*    <foreach collection="idList" item="id" open="(" close=")" separator=",">
	        #{id,jdbcType=VARCHAR}
	    </foreach>*/
		    outerForEachElement.addAttribute(new Attribute("collection", "list"));
		    outerForEachElement.addAttribute(new Attribute("item", "item"));
		    outerForEachElement.addAttribute(new Attribute("index", "index"));
		    outerForEachElement.addAttribute(new Attribute("separator", ","));
			
		
		valuesClause.append(')');
		valuesClauses.add(valuesClause.toString());
		Iterator i$1 = valuesClauses.iterator();

		while (i$1.hasNext()) {
			String clause = (String) i$1.next();
			outerForEachElement.addElement(new TextElement(clause));
		}
		
		answer.addElement(outerForEachElement);
		answer.addElement(new TextElement(textClause1.toString()));
		Document document = null;
		String formartXml =  null;
	    document = new Document();
		document.setRootElement(answer);
	    formartXml = document.getFormattedContent();
		System.out.println(formartXml);

	}
	
	
	

	
	public static void getDeleteList( IntrospectedTable introspectedTable ,boolean isSimple) {
		
		XmlElement answer = new XmlElement("update");
		answer.addAttribute(new Attribute("id",  "deleteList"));
		answer.addAttribute(new Attribute("parameterType", "map"));
//		context.getCommentGenerator().addComment(answer);
		
		
		
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("update ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		answer.addElement(new TextElement(sb.toString()));
		sb.setLength(0);
		XmlElement dynamicElement = new XmlElement("set");
		answer.addElement(dynamicElement);
		
		Iterator iter;
		if (isSimple) {
			iter = introspectedTable.getNonPrimaryKeyColumns().iterator();
		} else {
			iter = introspectedTable.getBaseColumns().iterator();
		}
		
		StringBuilder lastString = new StringBuilder();
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
			String tempStr = MyBatis3FormattingUtilities.getEscapedColumnName(i$);
			if(i$.getJavaProperty().equalsIgnoreCase("lastver")) {
				sb.append(tempStr).append(" = ").append(tempStr + " + 1");
			}else if(i$.getJavaProperty().equalsIgnoreCase("isvalid")) {
				OutputUtilities.xmlIndent(lastString,1);
				lastString.append(tempStr).append(" = 0 ");
			}else {
				sb.append(tempStr);
				sb.append(" = ");
				sb.append(MyBatis3FormattingUtilities.getParameterClause(i$));
			}
		
			 
			
			
/*				IntrospectedColumn and = (IntrospectedColumn) iter.next();
			sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(and));
			sb.append(" = ");
			sb.append(MyBatis3FormattingUtilities.getParameterClause(and));
			if (iter.hasNext()) {
				sb.append(',');
			}*/

		    sb.append(",");
			
			if(i$.getJavaProperty().equalsIgnoreCase("optime") 
					|| i$.getJavaProperty().equalsIgnoreCase("opuserid") 
					) {
				dynamicElement.addElement(new TextElement(sb.toString()));
			}
			if( i$.getJavaProperty().equalsIgnoreCase("lastver") ) {
				dynamicElement.addElement(new TextElement(sb.toString()));
			}

			if (iter.hasNext()) {
				sb.setLength(0);
				OutputUtilities.xmlIndent(sb, 1);
			}
		 
		}
		
		dynamicElement.addElement(new TextElement(lastString.toString()));
		boolean and1 = false;
		Document document = null;
		String formartXml =  null;
		
		
		Iterator i$ = introspectedTable.getPrimaryKeyColumns().iterator();

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
			 answer.addElement(new TextElement(sb.toString()));
			 sb.setLength(0);
			 OutputUtilities.xmlIndent(sb, 1);
			 
			 
			 
		       XmlElement outerForEachElement = new XmlElement("foreach");
			/*    <foreach collection="idList" item="id" open="(" close=")" separator=",">
		        #{id,jdbcType=VARCHAR}
		    </foreach>*/
			    outerForEachElement.addAttribute(new Attribute("collection", "idList"));
			    outerForEachElement.addAttribute(new Attribute("item", "id"));
			    outerForEachElement.addAttribute(new Attribute("open", " in ("));
			    outerForEachElement.addAttribute(new Attribute("close", ")"));
			    outerForEachElement.addAttribute(new Attribute("separator", ","));
			    sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
			    outerForEachElement.addElement( new TextElement(sb.toString()));
			    answer.addElement(outerForEachElement);
//			    answer.addElement(new TextElement(sb.toString()));
		}
		

	    XmlElement outerForEachElement = new XmlElement("foreach");
	/*    <foreach collection="idList" item="id" open="(" close=")" separator=",">
        #{id,jdbcType=VARCHAR}
    </foreach>*/
	    outerForEachElement.addAttribute(new Attribute("collection", "idList"));
	    outerForEachElement.addAttribute(new Attribute("item", "item"));
	    outerForEachElement.addAttribute(new Attribute("open", "("));
	    outerForEachElement.addAttribute(new Attribute("close", ")"));
	    outerForEachElement.addAttribute(new Attribute("separator", ","));
		
		  document = new Document();
		document.setRootElement(answer);
	    formartXml = document.getFormattedContent();
		System.out.println(formartXml);
		
	}

}
