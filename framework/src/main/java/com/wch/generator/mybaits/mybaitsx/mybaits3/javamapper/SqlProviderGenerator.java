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
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.AbstractJavaProviderMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderApplyWhereMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderCountByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderDeleteByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderInsertSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderSelectByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderSelectByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByExampleSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByPrimaryKeySelectiveMethodGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.messages.Messages;

public class SqlProviderGenerator extends AbstractJavaGenerator
{
  public List<CompilationUnit> getCompilationUnits()
  {
    this.progressCallback.startTask(Messages.getString("Progress.18", this.introspectedTable.getFullyQualifiedTable().toString()));

    CommentGenerator commentGenerator = this.context.getCommentGenerator();

    FullyQualifiedJavaType type = new FullyQualifiedJavaType(this.introspectedTable.getMyBatis3SqlProviderType());

    TopLevelClass topLevelClass = new TopLevelClass(type);
    topLevelClass.setVisibility(JavaVisibility.PUBLIC);
    commentGenerator.addJavaFileComment(topLevelClass);

    boolean addApplyWhereMethod = false;
    addApplyWhereMethod |= addCountByExampleMethod(topLevelClass);
    addApplyWhereMethod |= addDeleteByExampleMethod(topLevelClass);
    addInsertSelectiveMethod(topLevelClass);
    addApplyWhereMethod |= addSelectByExampleWithBLOBsMethod(topLevelClass);
    addApplyWhereMethod |= addSelectByExampleWithoutBLOBsMethod(topLevelClass);
    addApplyWhereMethod |= addUpdateByExampleSelectiveMethod(topLevelClass);
    addApplyWhereMethod |= addUpdateByExampleWithBLOBsMethod(topLevelClass);
    addApplyWhereMethod |= addUpdateByExampleWithoutBLOBsMethod(topLevelClass);
    addUpdateByPrimaryKeySelectiveMethod(topLevelClass);

    if (addApplyWhereMethod) {
      addApplyWhereMethod(topLevelClass);
    }

    List answer = new ArrayList();

    if ((topLevelClass.getMethods().size() > 0) && 
      (this.context.getPlugins().providerGenerated(topLevelClass, this.introspectedTable)))
    {
      answer.add(topLevelClass);
    }

    return answer;
  }

  protected boolean addCountByExampleMethod(TopLevelClass topLevelClass) {
    boolean rc = false;
    if (this.introspectedTable.getRules().generateCountByExample()) {
      AbstractJavaProviderMethodGenerator methodGenerator = new ProviderCountByExampleMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, topLevelClass);
      rc = true;
    }

    return rc;
  }

  protected boolean addDeleteByExampleMethod(TopLevelClass topLevelClass) {
    boolean rc = false;
    if (this.introspectedTable.getRules().generateDeleteByExample()) {
      AbstractJavaProviderMethodGenerator methodGenerator = new ProviderDeleteByExampleMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, topLevelClass);
      rc = true;
    }

    return rc;
  }

  protected void addInsertSelectiveMethod(TopLevelClass topLevelClass) {
    if (this.introspectedTable.getRules().generateInsertSelective()) {
      AbstractJavaProviderMethodGenerator methodGenerator = new ProviderInsertSelectiveMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, topLevelClass);
    }
  }

  protected boolean addSelectByExampleWithBLOBsMethod(TopLevelClass topLevelClass)
  {
    boolean rc = false;
    if (this.introspectedTable.getRules().generateSelectByExampleWithBLOBs()) {
      AbstractJavaProviderMethodGenerator methodGenerator = new ProviderSelectByExampleWithBLOBsMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, topLevelClass);
      rc = true;
    }

    return rc;
  }

  protected boolean addSelectByExampleWithoutBLOBsMethod(TopLevelClass topLevelClass)
  {
    boolean rc = false;
    if (this.introspectedTable.getRules().generateSelectByExampleWithoutBLOBs()) {
      AbstractJavaProviderMethodGenerator methodGenerator = new ProviderSelectByExampleWithoutBLOBsMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, topLevelClass);
      rc = true;
    }

    return rc;
  }

  protected boolean addUpdateByExampleSelectiveMethod(TopLevelClass topLevelClass)
  {
    boolean rc = false;
    if (this.introspectedTable.getRules().generateUpdateByExampleSelective()) {
      AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByExampleSelectiveMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, topLevelClass);
      rc = true;
    }

    return rc;
  }

  protected boolean addUpdateByExampleWithBLOBsMethod(TopLevelClass topLevelClass)
  {
    boolean rc = false;
    if (this.introspectedTable.getRules().generateUpdateByExampleWithBLOBs()) {
      AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByExampleWithBLOBsMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, topLevelClass);
      rc = true;
    }

    return rc;
  }

  protected boolean addUpdateByExampleWithoutBLOBsMethod(TopLevelClass topLevelClass)
  {
    boolean rc = false;
    if (this.introspectedTable.getRules().generateUpdateByExampleWithoutBLOBs()) {
      AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByExampleWithoutBLOBsMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, topLevelClass);
      rc = true;
    }

    return rc;
  }

  protected void addUpdateByPrimaryKeySelectiveMethod(TopLevelClass topLevelClass)
  {
    if (this.introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
      AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByPrimaryKeySelectiveMethodGenerator();
      initializeAndExecuteGenerator(methodGenerator, topLevelClass);
    }
  }

  protected void addApplyWhereMethod(TopLevelClass topLevelClass) {
    AbstractJavaProviderMethodGenerator methodGenerator = new ProviderApplyWhereMethodGenerator();
    initializeAndExecuteGenerator(methodGenerator, topLevelClass);
  }

  protected void initializeAndExecuteGenerator(AbstractJavaProviderMethodGenerator methodGenerator, TopLevelClass topLevelClass)
  {
    methodGenerator.setContext(this.context);
    methodGenerator.setIntrospectedTable(this.introspectedTable);
    methodGenerator.setProgressCallback(this.progressCallback);
    methodGenerator.setWarnings(this.warnings);
    methodGenerator.addClassElements(topLevelClass);
  }
}