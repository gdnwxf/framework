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
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.CountByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeySelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class JavaMapperGenerator extends AbstractJavaClientGenerator
{
  public JavaMapperGenerator()
  {
    super(true);
  }

  public JavaMapperGenerator(boolean requiresMatchedXMLGenerator) {
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

    addCountByExampleMethod(interfaze);
    addDeleteByExampleMethod(interfaze);
    addDeleteByPrimaryKeyMethod(interfaze);
    addInsertMethod(interfaze);
    addInsertSelectiveMethod(interfaze);
    addSelectByExampleWithBLOBsMethod(interfaze);
    addSelectByExampleWithoutBLOBsMethod(interfaze);
    addSelectByPrimaryKeyMethod(interfaze);
    addUpdateByExampleSelectiveMethod(interfaze);
    addUpdateByExampleWithBLOBsMethod(interfaze);
    addUpdateByExampleWithoutBLOBsMethod(interfaze);
    addUpdateByPrimaryKeySelectiveMethod(interfaze);
    addUpdateByPrimaryKeyWithBLOBsMethod(interfaze);
    addUpdateByPrimaryKeyWithoutBLOBsMethod(interfaze);

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

  protected void addCountByExampleMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateCountByExample()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new CountByExampleMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addDeleteByExampleMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateDeleteByExample()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByExampleMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateDeleteByPrimaryKey()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByPrimaryKeyMethodGenerator(false);
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addInsertMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateInsert()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new InsertMethodGenerator(false);
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addInsertSelectiveMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateInsertSelective()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new InsertSelectiveMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addSelectByExampleWithBLOBsMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateSelectByExampleWithBLOBs()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new SelectByExampleWithBLOBsMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addSelectByExampleWithoutBLOBsMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateSelectByExampleWithoutBLOBs()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new SelectByExampleWithoutBLOBsMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateSelectByPrimaryKey()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new SelectByPrimaryKeyMethodGenerator(false);
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addUpdateByExampleSelectiveMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateUpdateByExampleSelective()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleSelectiveMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addUpdateByExampleWithBLOBsMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateUpdateByExampleWithBLOBs()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleWithBLOBsMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addUpdateByExampleWithoutBLOBsMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateUpdateByExampleWithoutBLOBs()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleWithoutBLOBsMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addUpdateByPrimaryKeySelectiveMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeySelectiveMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addUpdateByPrimaryKeyWithBLOBsMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithBLOBsMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addUpdateByPrimaryKeyWithoutBLOBsMethod(Interface interfaze) {
    if (!(this.introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs()))
      return;
    AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithoutBLOBsMethodGenerator();
    initializeAndExecuteGenerator(methodGenerator, interfaze);
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
    return new XMLMapperGenerator();
  }
}