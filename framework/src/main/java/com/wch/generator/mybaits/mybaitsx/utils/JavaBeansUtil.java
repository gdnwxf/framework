package com.wch.generator.mybaits.mybaitsx.utils;

import java.util.Locale;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public class JavaBeansUtil {
	public static String getGetterMethodName(String property, FullyQualifiedJavaType fullyQualifiedJavaType) {
		StringBuilder sb = new StringBuilder();
		sb.append(property);
		if (Character.isLowerCase(sb.charAt(0)) && (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1)))) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}

		if (fullyQualifiedJavaType.equals(FullyQualifiedJavaType.getBooleanPrimitiveInstance())) {
			sb.insert(0, "is");
		} else {
			sb.insert(0, "get");
		}

		return sb.toString();
	}

	public static String getSetterMethodName(String property) {
		StringBuilder sb = new StringBuilder();
		sb.append(property);
		if (Character.isLowerCase(sb.charAt(0)) && (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1)))) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}

		sb.insert(0, "set");
		return sb.toString();
	}

	public static String getCamelCaseString(String inputString, boolean firstCharacterUppercase) {
		StringBuilder sb = new StringBuilder();
		boolean nextUpperCase = false;

		for (int i = 0; i < inputString.length(); ++i) {
			char c = inputString.charAt(i);
			switch (c) {
			case ' ':
			case '#':
			case '$':
			case '&':
			case '-':
			case '/':
			case '@':
			case '_':
				if (sb.length() > 0) {
					nextUpperCase = true;
				}
				break;
			default:
				if (nextUpperCase) {
					sb.append(Character.toUpperCase(c));
					nextUpperCase = false;
				} else {
					sb.append(Character.toLowerCase(c));
				}
			}
		}

		if (firstCharacterUppercase) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}

		return sb.toString();
	}

	public static String getValidPropertyName(String inputString) {
		String answer;
		if (inputString == null) {
			answer = null;
		} else if (inputString.length() < 2) {
			answer = inputString.toLowerCase(Locale.US);
		} else if (Character.isUpperCase(inputString.charAt(0)) && !Character.isUpperCase(inputString.charAt(1))) {
			answer = inputString.substring(0, 1).toLowerCase(Locale.US) + inputString.substring(1);
		} else {
			answer = inputString;
		}

		return answer;
	}
}