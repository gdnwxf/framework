/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.javamapper;

import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectAllMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.SimpleXMLMapperGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class SimpleJavaClientGenerator extends AbstractJavaClientGenerator
{
  public SimpleJavaClientGenerator()
  {
    super(true);
  }

  public SimpleJavaClientGenerator(boolean requiresMatchedXMLGenerator) {
    super(requiresMatchedXMLGenerator);
  }

  public List<CompilationUnit> getCompilationUnits()
  {
    this.progressCallback.startTask(Messages.getString("Progress.17", this.introspectedTable.getFullyQualifiedTable().toString()));

    CommentGenerator commentGenerator = this.context.getCommentGenerator();

    FullyQualifiedJavaType type = new FullyQualifiedJavaType(this.introspectedTable.getMyBatis3JavaMapperType());

    Interface interfaze = new Interface(type);
    interfaze.setVisibility(JavaVisibility.PUBLIC);
    commentGenerator.addJavaFileComment(interfaze);

    String rootInterface = this.introspectedTable.getTableConfigurationProperty("rootInterface");

    if (!(StringUtility.stringHasValue(rootInterface))) {
      rootInterface = this.context.getJavaClientGeneratorConfiguration().getProperty("rootInterface");
    }

    if (StringUtility.stringHasValue(rootInterface)) {
      FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootInterface);

      interfaze.addSuperInterface(fqjt);
      interfaze.addImportedType(fqjt);
    }

    addDeleteByPrimaryKeyMethod(interfaze);
    addInsertMethod(interfaze);
    addSelectByPrimaryKeyMethod(interfaze);
    addSelectAllMethod(interfaze);
    addUpdateByPrimaryKeyMethod(interfaze);

    List answer = new ArrayList();
    if (this.context.getPlugins().clientGenerated(interfaze, null, this.introspectedTable))
    {
      answer.add(interfaze);
    }

    List extraCompilationUnits = getExtraCompilationUnits();
    if (extraCompilationUnits != null) {
      answer.addAll(extraCompilationUnits);
    }

    return answer;
  }

  protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateDeleteByPrimaryKey()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByPrimaryKeyMethodGenerator(true);
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addInsertMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateInsert()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new InsertMethodGenerator(true);
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateSelectByPrimaryKey()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new SelectByPrimaryKeyMethodGenerator(true);
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addSelectAllMethod(Interface interfaze) {
    AbstractJavaMapperMethodGenerator methodGenerator = new SelectAllMethodGenerator();
    initializeAndExecuteGenerator(methodGenerator, interfaze);
  }

  protected void addUpdateByPrimaryKeyMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithoutBLOBsMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void initializeAndExecuteGenerator(AbstractJavaMapperMethodGenerator methodGenerator, Interface interfaze)
  {
    methodGenerator.setContext(this.context);
    methodGenerator.setIntrospectedTable(this.introspectedTable);
    methodGenerator.setProgressCallback(this.progressCallback);
    methodGenerator.setWarnings(this.warnings);
    methodGenerator.addInterfaceElements(interfaze);
  }

  public List<CompilationUnit> getExtraCompilationUnits() {
    return null;
  }

  public AbstractXmlGenerator getMatchedXMLGenerator()
  {
    return new SimpleXMLMapperGenerator();
  }
}