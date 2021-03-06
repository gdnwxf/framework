/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wch.generator.mybaits.mybaitsx.core;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.internal.PluginAggregator;

import com.wch.generator.mybaits.mybaitsx.bean.IntrospectedColumn;
import com.wch.generator.mybaits.mybaitsx.bean.IntrospectedTable;
import com.wch.generator.mybaits.mybaitsx.bean.ModelType;
import com.wch.generator.mybaits.mybaitsx.bean.PluginConfiguration;
import com.wch.generator.mybaits.mybaitsx.bean.TableConfiguration;
import com.wch.generator.mybaits.mybaitsx.beanutils.JavaFormatter;
import com.wch.generator.mybaits.mybaitsx.beanutils.JavaTypeResolver;
import com.wch.generator.mybaits.mybaitsx.beanutils.PropertyHolder;
import com.wch.generator.mybaits.mybaitsx.beanutils.SqlMapGeneratorConfiguration;
import com.wch.generator.mybaits.mybaitsx.dom.Attribute;
import com.wch.generator.mybaits.mybaitsx.dom.Element;
import com.wch.generator.mybaits.mybaitsx.dom.XmlElement;
import com.wch.generator.mybaits.mybaitsx.dom.XmlFormatter;
import com.wch.generator.mybaits.mybaitsx.domain.GeneratedJavaFile;
import com.wch.generator.mybaits.mybaitsx.domain.GeneratedXmlFile;
import com.wch.generator.mybaits.mybaitsx.utils.Messages;
import com.wch.generator.mybaits.mybaitsx.utils.ObjectFactory;
import com.wch.generator.mybaits.mybaitsx.utils.StringUtility;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class MyContext extends PropertyHolder {
	private static final String ENCODEING = "UTF-8";
	private String id;
	private JDBCConnectionConfiguration jdbcConnectionConfiguration;
	private SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration;
	private JavaTypeResolverConfiguration javaTypeResolverConfiguration;
	private JavaModelGeneratorConfiguration javaModelGeneratorConfiguration;
	private JavaClientGeneratorConfiguration javaClientGeneratorConfiguration;
	private ArrayList<TableConfiguration> tableConfigurations;
	private ModelType defaultModelType;
	private String beginningDelimiter = "\"";
	private String endingDelimiter = "\"";
	private CommentGeneratorConfiguration commentGeneratorConfiguration;
	private CommentGenerator commentGenerator;
	private PluginAggregator pluginAggregator;
	private List<PluginConfiguration> pluginConfigurations;
	private String targetRuntime;
	private String introspectedColumnImpl;
	private Boolean autoDelimitKeywords;
	private JavaFormatter javaFormatter;
	private XmlFormatter xmlFormatter;

	private List<IntrospectedTable> introspectedTables;
	public MyContext(ModelType defaultModelType) {
		this.defaultModelType = defaultModelType == null ? ModelType.CONDITIONAL : defaultModelType;
		this.tableConfigurations = new ArrayList();
		this.pluginConfigurations = new ArrayList<PluginConfiguration>();
	}

	public void addTableConfiguration(TableConfiguration tc) {
		this.tableConfigurations.add(tc);
	}

	public JDBCConnectionConfiguration getJdbcConnectionConfiguration() {
		return this.jdbcConnectionConfiguration;
	}

	public JavaClientGeneratorConfiguration getJavaClientGeneratorConfiguration() {
		return this.javaClientGeneratorConfiguration;
	}

	public JavaModelGeneratorConfiguration getJavaModelGeneratorConfiguration() {
		return this.javaModelGeneratorConfiguration;
	}

	public JavaTypeResolverConfiguration getJavaTypeResolverConfiguration() {
		return this.javaTypeResolverConfiguration;
	}

	public SqlMapGeneratorConfiguration getSqlMapGeneratorConfiguration() {
		return this.sqlMapGeneratorConfiguration;
	}

	public void addPluginConfiguration(PluginConfiguration pluginConfiguration) {
		this.pluginConfigurations.add(pluginConfiguration);
	}

	public void validate(List<String> errors) {
		if (!StringUtility.stringHasValue((String) this.id)) {
			errors.add(Messages.getString((String) "ValidationError.16"));
		}
		if (this.jdbcConnectionConfiguration == null) {
			errors.add(Messages.getString((String) "ValidationError.10", (String) this.id));
		} else {
			this.jdbcConnectionConfiguration.validate(errors);
		}
		if (this.javaModelGeneratorConfiguration == null) {
			errors.add(Messages.getString((String) "ValidationError.8", (String) this.id));
		} else {
			this.javaModelGeneratorConfiguration.validate(errors, this.id);
		}
		if (this.javaClientGeneratorConfiguration != null) {
			this.javaClientGeneratorConfiguration.validate(errors, this.id);
		}
		IntrospectedTable it = null;
		try {
			it = ObjectFactory.createIntrospectedTableForValidation((MyContext) this);
		} catch (Exception e) {
			errors.add(Messages.getString((String) "ValidationError.25", (String) this.id));
		}
		if (it != null && it.requiresXMLGenerator()) {
			if (this.sqlMapGeneratorConfiguration == null) {
				errors.add(Messages.getString((String) "ValidationError.9", (String) this.id));
			} else {
				this.sqlMapGeneratorConfiguration.validate(errors, this.id);
			}
		}
		if (this.tableConfigurations.size() == 0) {
			errors.add(Messages.getString((String) "ValidationError.3", (String) this.id));
		} else {
			for (int i = 0; i < this.tableConfigurations.size(); ++i) {
				TableConfiguration tc = this.tableConfigurations.get(i);
				tc.validate(errors, i);
			}
		}
		for (PluginConfiguration pluginConfiguration : this.pluginConfigurations) {
			pluginConfiguration.validate(errors, this.id);
		}
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setJavaClientGeneratorConfiguration(JavaClientGeneratorConfiguration javaClientGeneratorConfiguration) {
		this.javaClientGeneratorConfiguration = javaClientGeneratorConfiguration;
	}

	public void setJavaModelGeneratorConfiguration(JavaModelGeneratorConfiguration javaModelGeneratorConfiguration) {
		this.javaModelGeneratorConfiguration = javaModelGeneratorConfiguration;
	}

	public void setJavaTypeResolverConfiguration(JavaTypeResolverConfiguration javaTypeResolverConfiguration) {
		this.javaTypeResolverConfiguration = javaTypeResolverConfiguration;
	}

	public void setJdbcConnectionConfiguration(JDBCConnectionConfiguration jdbcConnectionConfiguration) {
		this.jdbcConnectionConfiguration = jdbcConnectionConfiguration;
	}

	public void setSqlMapGeneratorConfiguration(SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration) {
		this.sqlMapGeneratorConfiguration = sqlMapGeneratorConfiguration;
	}

	public ModelType getDefaultModelType() {
		return this.defaultModelType;
	}

	public XmlElement toXmlElement() {
		XmlElement xmlElement = new XmlElement("context");
		xmlElement.addAttribute(new Attribute("id", this.id));
		if (this.defaultModelType != ModelType.CONDITIONAL) {
			xmlElement.addAttribute(new Attribute("defaultModelType", this.defaultModelType.getModelType()));
		}
		if (StringUtility.stringHasValue((String) this.introspectedColumnImpl)) {
			xmlElement.addAttribute(new Attribute("introspectedColumnImpl", this.introspectedColumnImpl));
		}
		if (StringUtility.stringHasValue((String) this.targetRuntime)) {
			xmlElement.addAttribute(new Attribute("targetRuntime", this.targetRuntime));
		}
		this.addPropertyXmlElements(xmlElement);
		for (PluginConfiguration pluginConfiguration : this.pluginConfigurations) {
			xmlElement.addElement((Element) pluginConfiguration.toXmlElement());
		}
	 
		for (TableConfiguration tableConfiguration : this.tableConfigurations) {
			xmlElement.addElement((Element) tableConfiguration.toXmlElement());
		}
		return xmlElement;
	}

	public List<TableConfiguration> getTableConfigurations() {
		return this.tableConfigurations;
	}

	public String getBeginningDelimiter() {
		return this.beginningDelimiter;
	}

	public String getEndingDelimiter() {
		return this.endingDelimiter;
	}

	public void addProperty(String name, String value) {
		super.addProperty(name, value);
		if ("beginningDelimiter".equals(name)) {
			this.beginningDelimiter = value;
		} else if ("endingDelimiter".equals(name)) {
			this.endingDelimiter = value;
		} else if ("autoDelimitKeywords".equals(name) && StringUtility.stringHasValue((String) value)) {
			this.autoDelimitKeywords = new Boolean(StringUtility.isTrue((String) value));
		}
	}

 
	public JavaFormatter getJavaFormatter() {
		if (this.javaFormatter == null) {
			this.javaFormatter = ObjectFactory.createJavaFormatter((MyContext) this);
		}
		return this.javaFormatter;
	}
 

	public CommentGeneratorConfiguration getCommentGeneratorConfiguration() {
		return this.commentGeneratorConfiguration;
	}

	public void setCommentGeneratorConfiguration(CommentGeneratorConfiguration commentGeneratorConfiguration) {
		this.commentGeneratorConfiguration = commentGeneratorConfiguration;
	}

	public Plugin getPlugins() {
		return this.pluginAggregator;
	}

	public String getTargetRuntime() {
		return this.targetRuntime;
	}

	public void setTargetRuntime(String targetRuntime) {
		this.targetRuntime = targetRuntime;
	}

	public String getIntrospectedColumnImpl() {
		return this.introspectedColumnImpl;
	}

	public void setIntrospectedColumnImpl(String introspectedColumnImpl) {
		this.introspectedColumnImpl = introspectedColumnImpl;
	}

	public int getIntrospectionSteps() {
		int steps = 0;
		++steps;
		return steps += this.tableConfigurations.size() * 1;
	}

	public void introspectTables( List<String> warnings, Set<String> fullyQualifiedTableNames) throws SQLException, InterruptedException {
		this.introspectedTables = new ArrayList<IntrospectedTable>();
		JavaTypeResolver javaTypeResolver = ObjectFactory.createJavaTypeResolver((MyContext) this, warnings);
		Connection connection = null;
		try {
			connection = this.getConnection();
			DatabaseIntrospector databaseIntrospector = new DatabaseIntrospector(this, connection.getMetaData(), javaTypeResolver, warnings);
			for (TableConfiguration tc : this.tableConfigurations) {
				String tableName = StringUtility.composeFullyQualifiedTableName((String) tc.getCatalog(), (String) tc.getSchema(), (String) tc.getTableName(), (char) '.');
				if (fullyQualifiedTableNames != null && fullyQualifiedTableNames.size() > 0 && !fullyQualifiedTableNames.contains(tableName))
					continue;
				if (!tc.areAnyStatementsEnabled()) {
					warnings.add(Messages.getString((String) "Warning.0", (String) tableName));
					continue;
				}
				List tables = databaseIntrospector.introspectTables(tc);
				if (tables != null) {
					this.introspectedTables.addAll(tables);
				}
			}
		} finally {
			this.closeConnection(connection);
		}
	}

	public int getGenerationSteps() {
		int steps = 0;
		if (this.introspectedTables != null) {
			for (IntrospectedTable introspectedTable : this.introspectedTables) {
				steps += introspectedTable.getGenerationSteps();
			}
		}
		return steps;
	}

	public void generateFiles(List<GeneratedJavaFile> generatedJavaFiles, List<GeneratedXmlFile> generatedXmlFiles, List<String> warnings) throws InterruptedException {
		this.pluginAggregator = new PluginAggregator();
//		for (PluginConfiguration pluginConfiguration : this.pluginConfigurations) {
//			Plugin plugin = ObjectFactory.createPlugin((MyContext) this, (PluginConfiguration) pluginConfiguration);
//			if (plugin.validate(warnings)) {
//				this.pluginAggregator.addPlugin(plugin);
//				continue;
//			}
//			warnings.add(Messages.getString((String) "Warning.24", (String) pluginConfiguration.getConfigurationType(), (String) this.id));
//		}
		if (this.introspectedTables != null) {
			for (IntrospectedTable introspectedTable : this.introspectedTables) {
			/*	callback.checkCancel();
				introspectedTable.initialize();
				introspectedTable.calculateGenerators(warnings, callback);
				generatedJavaFiles.addAll(introspectedTable.getGeneratedJavaFiles());
				generatedXmlFiles.addAll(introspectedTable.getGeneratedXmlFiles());
				generatedJavaFiles.addAll(this.pluginAggregator.contextGenerateAdditionalJavaFiles(introspectedTable));
				generatedXmlFiles.addAll(this.pluginAggregator.contextGenerateAdditionalXmlFiles(introspectedTable));*/
//				XmlElemenetGenerator.getXml(introspectedTable, true);
//				XmlElemenetGenerator.getXmlUpdateList(introspectedTable, true);
//				XmlElemenetGenerator.getDeleteList(introspectedTable, true);
				
				try {
					List<IntrospectedColumn>  primaryKeyColumns= introspectedTable.getPrimaryKeyColumns();
					List<IntrospectedColumn> baseColumns = introspectedTable.getBaseColumns();
  
					List<IntrospectedColumn> allColumns = new ArrayList<IntrospectedColumn>(primaryKeyColumns);
					allColumns .addAll(baseColumns);
					//具体的生成file
					
					Map<String ,Object> data = new HashMap<String ,Object>();
					data.put("introspectedTable", introspectedTable) ;
					data.put("allColumns", allColumns) ;
					data.put("primaryKeyColumns", primaryKeyColumns) ;
					data.put("baseColumns", baseColumns) ;
					PrintWriter writer = new PrintWriter(System.out);
					Configuration configuration = new Configuration(Configuration.getVersion());
					configuration.setDirectoryForTemplateLoading(new File( Thread.currentThread().getContextClassLoader().getResource("").getPath() + "com/wch/generator/mybaits/mybaitsx/ftllib"));
					Template template = configuration.getTemplate("Mapper.xml.ftl", ENCODEING);
					template.process(data, writer);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TemplateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			}
		}
	}

	private Connection getConnection() throws SQLException {
		Connection connection = JdbcManager.getConnection();
		return connection;
	}

	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// empty catch block
			}
		}
	}

	public boolean autoDelimitKeywords() {
		return this.autoDelimitKeywords != null && this.autoDelimitKeywords != false;
	}
}
