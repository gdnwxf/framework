<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.dfire.retail.mapper.account.AccountInfoMapper" >
  <resultMap id="BaseResultMap" type="com.dfire.retail.bo.account.AccountInfo" >
	<#list primaryKeyColumns as  p > 
    <id column="${p.actualColumnName!}" property="${p.javaProperty!}" jdbcType="${p.jdbcTypeName!}" />
	</#list>
	<#list baseColumns as  p > 
	    <result column="${p.actualColumnName!}" property="${p.javaProperty!}" jdbcType="${p.jdbcTypeName!}" />
	</#list>
  </resultMap>
  <sql id="Base_Column_List" >
     <#assign x = allColumns?size> 
      <#list 2..x-1 as v > 
      	<#if v%3 == 2 >  
		${allColumns[v-2].actualColumnName!}, ${allColumns[v-1].actualColumnName!},${allColumns[v].actualColumnName!}<#if v < x && x%3 != 0 >,</#if>
      	</#if>
      </#list>
	 <#list (x%3) .. 1 as c > 
<#if c_index == 0 >		</#if><#rt>${allColumns[x-c].actualColumnName!}<#rt><#if c!=1 >,</#if>
	 </#list> 
	 
  </sql>
  <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="${primaryKeyColumns[0].fullyQualifiedJavaType}" >
    select 
    <include refid="Base_Column_List" />
    from ${introspectedTable.fullyQualifiedTable!}
    where 
    <#list primaryKeyColumns as  p > 
       <#if p_index gt 0 >and</#if> ${p.actualColumnName!} = ${"#{"}${p.javaProperty!},jdbcType=${p.jdbcTypeName!}} 
	</#list>
  </select>
  
  <delete id="deleteByPrimaryKey" parameterType="${primaryKeyColumns[0].fullyQualifiedJavaType}" >
    delete from ${introspectedTable.fullyQualifiedTable!}
    where 
    <#list primaryKeyColumns as  p > 
       <#if p_index gt 0 >and</#if> ${p.actualColumnName!} = ${"#{"}${p.javaProperty!},jdbcType=${p.jdbcTypeName!}} 
	</#list>
  </delete>
  
  
  <update id="updateByPrimaryKeySelective" parameterType="com.dfire.retail.bo.account.AccountInfo" >
    update ${introspectedTable.fullyQualifiedTable!}
    <set >
    <#list baseColumns as  p > 
       <if test="${p.javaProperty!} != null" >
        ${p.actualColumnName!} = ${"#{"}${p.javaProperty!},jdbcType=${p.jdbcTypeName!}},
       </if>
	</#list>
    </set>
    where 
    <#list primaryKeyColumns as  p > 
       <#if p_index gt 0 >and</#if> ${p.actualColumnName!} = ${"#{"}${p.javaProperty!},jdbcType=${p.jdbcTypeName!}} 
	</#list>
   <update>
   
   
    <insert id="insertSelective" parameterType="com.dfire.retail.bo.account.AccountInfo" >
    insert into ${introspectedTable.fullyQualifiedTable!}
    <trim prefix="(" suffix=")" suffixOverrides="," >
    <#list allColumns as a>
      <if test="${a.javaProperty!} != null" >
        ${a.actualColumnName!},
      </if>
     </#list>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides="," >
      <#list allColumns as a>
      <if test="${a.javaProperty!} != null" >
        ${a.actualColumnName!} = ${"#{"}${a.javaProperty!},jdbcType=${a.jdbcTypeName!}},
      </if>
     </#list>
    </trim>
  </insert>
  
  
  
</mapper>