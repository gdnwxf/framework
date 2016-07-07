/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.javamapper;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedDeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedInsertMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedSelectAllMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedSelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedUpdateByPrimaryKeyWithoutBLOBsMethodGenerator;
import org.mybatis.generator.internal.rules.Rules;

public class SimpleAnnotatedClientGenerator extends SimpleJavaClientGenerator
{
  public SimpleAnnotatedClientGenerator()
  {
    super(false);
  }

  protected void addDeleteByPrimaryKeyMethod(Interface interfaze)
  {
    if (this.introspectedTable.getRules().generateDeleteByPrimaryKey()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedDeleteByPrimaryKeyMethodGenerator(true);
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addInsertMethod(Interface interfaze)
  {
    if (this.introspectedTable.getRules().generateInsert()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedInsertMethodGenerator(true);
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addSelectByPrimaryKeyMethod(Interface interfaze)
  {
    if (this.introspectedTable.getRules().generateSelectByPrimaryKey()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedSelectByPrimaryKeyMethodGenerator(false, true);
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addSelectAllMethod(Interface interfaze)
  {
    AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedSelectAllMethodGenerator();
    initializeAndExecuteGenerator(methodGenerator, interfaze);
  }

  protected void addUpdateByPrimaryKeyMethod(Interface interfaze)
  {
    if (this.introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedUpdateByPrimaryKeyWithoutBLOBsMethodGenerator(true);
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  public AbstractXmlGenerator getMatchedXMLGenerator()
  {
    return null;
  }
}