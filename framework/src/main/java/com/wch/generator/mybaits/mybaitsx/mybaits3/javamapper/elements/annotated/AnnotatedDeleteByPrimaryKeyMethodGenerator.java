/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.javamapper.elements.annotated;

import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.internal.util.StringUtility;

public class AnnotatedDeleteByPrimaryKeyMethodGenerator extends DeleteByPrimaryKeyMethodGenerator
{
  public AnnotatedDeleteByPrimaryKeyMethodGenerator(boolean isSimple)
  {
    super(isSimple);
  }

  public void addMapperAnnotations(Interface interfaze, Method method)
  {
    interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Delete"));

    method.addAnnotation("@Delete({");

    StringBuilder sb = new StringBuilder();
    OutputUtilities.javaIndent(sb, 1);
    sb.append("\"delete from ");
    sb.append(StringUtility.escapeStringForJava(this.introspectedTable.getFullyQualifiedTableNameAtRuntime()));

    sb.append("\",");
    method.addAnnotation(sb.toString());

    boolean and = false;
    Iterator iter = this.introspectedTable.getPrimaryKeyColumns().iterator();
    while (iter.hasNext()) {
      IntrospectedColumn introspectedColumn = (IntrospectedColumn)iter.next();
      sb.setLength(0);
      OutputUtilities.javaIndent(sb, 1);
      if (and) {
        sb.append("  \"and ");
      } else {
        sb.append("\"where ");
        and = true;
      }

      sb.append(StringUtility.escapeStringForJava(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn)));

      sb.append(" = ");
      sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
      sb.append('"');
      if (iter.hasNext()) {
        sb.append(',');
      }

      method.addAnnotation(sb.toString());
    }

    method.addAnnotation("})");
  }
}