package com.wch.generator.mybaits.mybaits;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.NullProgressCallback;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.XmlFileMergerJaxp;
import org.mybatis.generator.internal.util.ClassloaderUtility;
import org.mybatis.generator.internal.util.messages.Messages;

import cn.org.rapid_framework.generator.util.sqlparse.SqlParseHelper.NameWithAlias;

public class MyBatisGenerator2 {
	private Configuration configuration;
	private ShellCallback shellCallback;
	private List<GeneratedJavaFile> generatedJavaFiles;
	private List<GeneratedXmlFile> generatedXmlFiles;
	private List<String> warnings;
	private Set<String> projects;

	public MyBatisGenerator2(Configuration configuration, ShellCallback shellCallback, List<String> warnings) throws InvalidConfigurationException {
		if (configuration == null) {
			throw new IllegalArgumentException(Messages.getString("RuntimeError.2"));
		} else {
			this.configuration = configuration;
			if (shellCallback == null) {
				this.shellCallback = new DefaultShellCallback(false);
			} else {
				this.shellCallback = shellCallback;
			}

			if (warnings == null) {
				this.warnings = new ArrayList();
			} else {
				this.warnings = warnings;
			}

			this.generatedJavaFiles = new ArrayList();
			this.generatedXmlFiles = new ArrayList();
			this.projects = new HashSet();
			this.configuration.validate();
		}
	}

	public void generate(ProgressCallback callback) throws SQLException, IOException, InterruptedException {
		this.generate(callback, (Set) null, (Set) null);
	}

	public void generate(ProgressCallback callback, Set<String> contextIds) throws SQLException, IOException, InterruptedException {
		this.generate(callback, contextIds, (Set) null);
	}

	public void generate(ProgressCallback callback, Set<String> contextIds, Set<String> fullyQualifiedTableNames) throws SQLException, IOException, InterruptedException {
		if (callback == null) {
			callback = new NullProgressCallback();
		}

		this.generatedJavaFiles.clear();
		this.generatedXmlFiles.clear();
		Object contextsToRun;
		if (contextIds != null && contextIds.size() != 0) {
			contextsToRun = new ArrayList();
			Iterator totalSteps = this.configuration.getContexts().iterator();

			while (totalSteps.hasNext()) {
				Context i$ = (Context) totalSteps.next();
				if (contextIds.contains(i$.getId())) {
					((List) contextsToRun).add(i$);
				}
			}
		} else {
			contextsToRun = this.configuration.getContexts();
		}

		if (this.configuration.getClassPathEntries().size() > 0) {
			ClassLoader totalSteps1 = ClassloaderUtility.getCustomClassloader(this.configuration.getClassPathEntries());
			ObjectFactory.addExternalClassLoader(totalSteps1);
		}

		int totalSteps2 = 0;

		Context project;
		Iterator i$1;
		for (i$1 = ((List) contextsToRun).iterator(); i$1.hasNext(); totalSteps2 += project.getIntrospectionSteps()) {
			project = (Context) i$1.next();
		}

		((ProgressCallback) callback).introspectionStarted(totalSteps2);
		i$1 = ((List) contextsToRun).iterator();

		while (i$1.hasNext()) {
			project = (Context) i$1.next();
			project.introspectTables((ProgressCallback) callback, this.warnings, fullyQualifiedTableNames);
		}

		totalSteps2 = 0;

		for (i$1 = ((List) contextsToRun).iterator(); i$1.hasNext(); totalSteps2 += project.getGenerationSteps()) {
			project = (Context) i$1.next();
		}

		((ProgressCallback) callback).generationStarted(totalSteps2);
		i$1 = ((List) contextsToRun).iterator();

		while (i$1.hasNext()) {
			project = (Context) i$1.next();
			project.generateFiles((ProgressCallback) callback, this.generatedJavaFiles, this.generatedXmlFiles, this.warnings);
		}

		((ProgressCallback) callback).saveStarted(this.generatedXmlFiles.size() + this.generatedJavaFiles.size());
		i$1 = this.generatedXmlFiles.iterator();

		File targetFile;
		String source;
		File e;
		while (i$1.hasNext()) {
			GeneratedXmlFile project1 = (GeneratedXmlFile) i$1.next();
			this.projects.add(project1.getTargetProject());

			try {
				e = this.shellCallback.getDirectory(project1.getTargetProject(), project1.getTargetPackage());
				targetFile = new File(e, project1.getFileName());
				if (targetFile.exists()) {
					if (project1.isMergeable()) {
						source = XmlFileMergerJaxp.getMergedSource(project1, targetFile);
					} else if (this.shellCallback.isOverwriteEnabled()) {
						source = project1.getFormattedContent();
						this.warnings.add(Messages.getString("Warning.11", targetFile.getAbsolutePath()));
					} else {
						source = project1.getFormattedContent();
						targetFile = this.getUniqueFileName(e, project1.getFileName());
						this.warnings.add(Messages.getString("Warning.2", targetFile.getAbsolutePath()));
					}
				} else {
					source = project1.getFormattedContent();
				}
			} catch (ShellException arg11) {
				this.warnings.add(arg11.getMessage());
				continue;
			}

			((ProgressCallback) callback).checkCancel();
			((ProgressCallback) callback).startTask(Messages.getString("Progress.15", targetFile.getName()));
			PrintWriter printWriter = new PrintWriter(System.out);
			printWriter.println(source);
		}

/*	    i$1 = this.generatedJavaFiles.iterator();*/
/*
		while (i$1.hasNext()) {
			GeneratedJavaFile project2 = (GeneratedJavaFile) i$1.next();
			this.projects.add(project2.getTargetProject());

			try {
				e = this.shellCallback.getDirectory(project2.getTargetProject(), project2.getTargetPackage());
				targetFile = new File(e, project2.getFileName());
				if (targetFile.exists()) {
					if (this.shellCallback.isMergeSupported()) {
						source = this.shellCallback.mergeJavaFile(project2.getFormattedContent(), targetFile.getAbsolutePath(), MergeConstants.OLD_ELEMENT_TAGS, project2.getFileEncoding());
					} else if (this.shellCallback.isOverwriteEnabled()) {
						source = project2.getFormattedContent();
						this.warnings.add(Messages.getString("Warning.11", targetFile.getAbsolutePath()));
					} else {
						source = project2.getFormattedContent();
						targetFile = this.getUniqueFileName(e, project2.getFileName());
						this.warnings.add(Messages.getString("Warning.2", targetFile.getAbsolutePath()));
					}
				} else {
					source = project2.getFormattedContent();
				}

				((ProgressCallback) callback).checkCancel();
				((ProgressCallback) callback).startTask(Messages.getString("Progress.15", targetFile.getName()));
				this.writeFile(targetFile, source, project2.getFileEncoding());
			} catch (ShellException arg10) {
				this.warnings.add(arg10.getMessage());
			}
		}

		i$1 = this.projects.iterator();

		while (i$1.hasNext()) {
			String project3 = (String) i$1.next();
			this.shellCallback.refreshProject(project3);
		}*/

		((ProgressCallback) callback).done();
	}

	private void writeFile(File file, String content, String fileEncoding) throws IOException {
		FileOutputStream fos = new FileOutputStream(file, false);
		OutputStreamWriter osw;
		if (fileEncoding == null) {
			osw = new OutputStreamWriter(fos);
		} else {
			osw = new OutputStreamWriter(fos, fileEncoding);
		}

		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(content);
		bw.close();
	}

	private File getUniqueFileName(File directory, String fileName) {
		File answer = null;
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < 1000; ++i) {
			sb.setLength(0);
			sb.append(fileName);
			sb.append('.');
			sb.append(i);
			File testFile = new File(directory, sb.toString());
			if (!testFile.exists()) {
				answer = testFile;
				break;
			}
		}

		if (answer == null) {
			throw new RuntimeException(Messages.getString("RuntimeError.3", directory.getAbsolutePath()));
		} else {
			return answer;
		}
	}
}
