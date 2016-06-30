package com.wch.generator.mybaits.mybaits;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class GeneratorFile {
	private static final String CONFIG_FILE = "-configfile";
	private static final String OVERWRITE = "-overwrite";
	private static final String CONTEXT_IDS = "-contextids";
	private static final String TABLES = "-tables";
	private static final String VERBOSE = "-verbose";
	private static final String FORCE_JAVA_LOGGING = "-forceJavaLogging";
	private static final String HELP_1 = "-?";
	private static final String HELP_2 = "-h";

	public static void main(String[] args) throws InvalidConfigurationException, IOException, XMLParserException, SQLException, InterruptedException {
		  Set<String> fullyqualifiedTables = new HashSet();
		
		MyConfiguration config = new MyConfiguration();
		
		MyContext context = new MyContext(null);
		
		context.setId("1");
  
		JDBCConnectionConfiguration jdbcInfo = new JDBCConnectionConfiguration();
		jdbcInfo.setUserId("retail");
		jdbcInfo.setPassword("retail552208");
		jdbcInfo.setConnectionURL("jdbc:mysql://10.1.1.20:3306/retail");
		jdbcInfo.setDriverClass("com.mysql.jdbc.Driver");
		context.setJdbcConnectionConfiguration(jdbcInfo);
		
		CommentGeneratorConfiguration comment = new CommentGeneratorConfiguration();
		comment.addProperty("suppressAllComments", "true");
		comment.addProperty("suppressDate", "true");
		context.setCommentGeneratorConfiguration(comment);
		
		SqlMapGeneratorConfiguration sqlMapXml = new SqlMapGeneratorConfiguration();
		sqlMapXml.setTargetPackage("mapping.system");
		sqlMapXml.setTargetProject("resources");
		sqlMapXml.addProperty("enableSubPackages", "true");
		context.setSqlMapGeneratorConfiguration(sqlMapXml);
		
		
		JavaTypeResolverConfiguration javaTypeResolver = new JavaTypeResolverConfiguration();
		javaTypeResolver.addProperty("forceBigDecimals", "false");
		context.setJavaTypeResolverConfiguration(javaTypeResolver);
		
		TableConfiguration tc = getTableConfig(context, "discountgoods", "discountgoods");
	    context.addTableConfiguration(tc);

	    JavaModelGeneratorConfiguration javaModel = new JavaModelGeneratorConfiguration();
	    javaModel.setTargetPackage("com.dfire.retail.bo.system");
	    javaModel.setTargetProject("java");
	    javaModel.addProperty("enableSubPackages", "true");
	    javaModel.addProperty("trimStrings", "true");
		context.setJavaModelGeneratorConfiguration(javaModel);
		
	    
	    
		config.addContext(context);
		Map<String, String> arguments = parseCommandLine(args);
		MyBatisGenerator2  myBatisGenerator = new MyBatisGenerator2(config, null, null);
		ProgressCallback progressCallback = arguments.containsKey("-verbose") ? new VerboseProgressCallback() : null;
		Set<String> contexts = new HashSet<String>();
		myBatisGenerator.generate(progressCallback, contexts , fullyqualifiedTables);

	}

	private static TableConfiguration getTableConfig(Context context, String tableName, String domainName) {
		TableConfiguration tc = new TableConfiguration(context);
		tc.setTableName(tableName);
		tc.setDomainObjectName(domainName);
		tc.addProperty("enableCountByExample", "false");
		tc.addProperty("enableUpdateByExample", "false");
		tc.addProperty("enableDeleteByExample", "false");
		tc.addProperty("enableSelectByExample", "false");
		tc.addProperty("selectByExampleQueryId", "false");
		return tc;
	}

	private static Map<String, String> parseCommandLine(String[] args) {
		List<String> errors = new ArrayList();
		Map<String, String> arguments = new HashMap();
		for (int i = 0; i < args.length; i++) {
			if ("-configfile".equalsIgnoreCase(args[i])) {
				if (i + 1 < args.length) {
					arguments.put("-configfile", args[(i + 1)]);
				} else {
					errors.add(Messages.getString("RuntimeError.19", "-configfile"));
				}
				i++;
			} else if ("-overwrite".equalsIgnoreCase(args[i])) {
				arguments.put("-overwrite", "Y");
			} else if ("-verbose".equalsIgnoreCase(args[i])) {
				arguments.put("-verbose", "Y");
			} else if ("-?".equalsIgnoreCase(args[i])) {
				arguments.put("-?", "Y");
			} else if ("-h".equalsIgnoreCase(args[i])) {
				arguments.put("-?", "Y");
			} else if ("-forceJavaLogging".equalsIgnoreCase(args[i])) {
				// LogFactory.forceJavaLogging();
			} else if ("-contextids".equalsIgnoreCase(args[i])) {
				if (i + 1 < args.length) {
					arguments.put("-contextids", args[(i + 1)]);
				} else {
					errors.add(Messages.getString("RuntimeError.19", "-contextids"));
				}
				i++;
			} else if ("-tables".equalsIgnoreCase(args[i])) {
				if (i + 1 < args.length) {
					arguments.put("-tables", args[(i + 1)]);
				} else {
					errors.add(Messages.getString("RuntimeError.19", "-tables"));
				}
				i++;
			} else {
				errors.add(Messages.getString("RuntimeError.20", args[i]));
			}
		}
		if (!errors.isEmpty()) {
			for (String error : errors) {
				writeLine(error);
			}
			System.exit(-1);
		}
		return arguments;
	}

	private static void writeLine() {
		System.out.println();
	}

	private static void writeLine(String message) {
		System.out.println(message);
	}
}
