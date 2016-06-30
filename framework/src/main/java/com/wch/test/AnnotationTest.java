package com.wch.test;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("*")
public class AnnotationTest extends AbstractProcessor {
	
	private Filer filer;
	private Messager messager;

	@Override
	public void init(ProcessingEnvironment env) {
		filer = env.getFiler();
		messager = env.getMessager();
	}

	@Override
	public boolean process(Set elements, RoundEnvironment env) {

		for (Element element : env.getRootElements()) {

			System.out.println(element);
			
			/*if (element.getSimpleName().toString().startsWith("Silly")) {
				// 不要循环为已经生成的类生成新的类
				continue;
			}

			if (element.getSimpleName().toString().startsWith("T")) {
				messager.printMessage(Kind.WARNING,
					"This class name starts with a T!",
					element);
			}

			String sillyClassName = "Silly" + element.getSimpleName();
			String sillyClassContent =
					"package silly;\n"
				+	"public class " + sillyClassName + " {\n"
				+	"	public String foobar;\n"
				+	"}";

			JavaFileObject file = null;

			try {
				file = filer.createSourceFile(
						"silly/" + sillyClassName,
						element);
				file.openWriter()
					.append(sillyClassContent)
					.close();
			} catch (IOException e) {
				e.printStackTrace();
			}*/

		}

		return true;
	}
	
	public static void main(String[] args) {
		AnnotationTest at = new AnnotationTest();
		
	}
}  
