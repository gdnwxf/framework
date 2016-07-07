/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.mybaits3.javamapper.elements.sqlprovider;

import java.util.List;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;

public class ProviderSelectByExampleWithBLOBsMethodGenerator extends ProviderSelectByExampleWithoutBLOBsMethodGenerator
{
  public List<IntrospectedColumn> getColumns()
  {
    return this.introspectedTable.getAllColumns();
  }

  public String getMethodName()
  {
    return this.introspectedTable.getSelectByExampleWithBLOBsStatementId();
  }

  public boolean callPlugins(Method method, TopLevelClass topLevelClass)
  {
    return this.context.getPlugins().providerSelectByExampleWithBLOBsMethodGenerated(method, topLevelClass, this.introspectedTable);
  }
}