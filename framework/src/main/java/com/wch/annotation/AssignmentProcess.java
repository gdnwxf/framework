package com.wch.annotation;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

public class AssignmentProcess extends AbstractProcessor {
	
	 private TypeElement assignmentElement; 
	    public synchronized void init(ProcessingEnvironment processingEnv) {
	        super.init(processingEnv);
	        Elements elementUtils = processingEnv.getElementUtils();
	        assignmentElement = elementUtils.getTypeElement("annotation.Assignment");
	 } 


	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		
		 Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(assignmentElement);
	        for (Element element : elements) {
	            processAssignment(element);
	        }
		
		return false;
	}
	
	
	public void processAssignment(Element element) {
        List<? extends AnnotationMirror> annotations = element.getAnnotationMirrors();
        for (AnnotationMirror mirror : annotations) {
            if (mirror.getAnnotationType().asElement().equals(assignmentElement)) {
                Map<? extends ExecutableElement, ? extends AnnotationValue> values = mirror.getElementValues();
                System.out.println(values);
                String assignee = (String) getAnnotationValue(values, "assignee");
                System.out.println(assignee);
            }
        }
    } 
	
	
	private Object getAnnotationValue(Map<? extends ExecutableElement, ? extends AnnotationValue> values, String annotationFiledName) {
		for (Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : values.entrySet()) {
			if (entry.getKey().getSimpleName().contentEquals(annotationFiledName)) {
				return entry.getValue().getValue();
			}
		}
		return null;
	}

}
