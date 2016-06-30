package com.wch.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class MethodInfoTest {
	private MethodInfoTest() {
	}

	private static boolean sameType(Type[] types, Class<?>[] clazzes) {
		if (types.length != clazzes.length) {
			return false;
		}
		for (int i = 0; i < types.length; i++) {
			if (!Type.getType(clazzes[i]).equals(types[i])) {
				return false;
			}
		}
		return true;
	}

	public static String[] getMethodParamNames(final Method m) {
		final String[] paramNames = new String[m.getParameterTypes().length];
		final String n = m.getDeclaringClass().getName();
		ClassReader cr = null;
		try {
			cr = new ClassReader(n);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		cr.accept(new ClassVisitor(Opcodes.ASM5) {
			public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
				final Type[] args = Type.getArgumentTypes(desc);
				if (!name.equals(m.getName()) || !sameType(args, m.getParameterTypes())) {
					return super.visitMethod(access, name, desc, signature, exceptions);
				}
				MethodVisitor v = super.visitMethod(access, name, desc, signature, exceptions);
				return new MethodVisitor(Opcodes.ASM5, v) {
					public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
						int i = index - 1;
						if (Modifier.isStatic(m.getModifiers())) {
							i = index;
						}
						if (i >= 0 && i < paramNames.length) {
							paramNames[i] = name;
						}
						super.visitLocalVariable(name, desc, signature, start, end, index);
					}
				};
			}
		}, 0);
		return paramNames;
	}

	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		String[] s = getMethodParamNames(MethodInfoTest.class.getMethod("getMethodParamNames", Method.class));
		System.out.println(Arrays.toString(s));
		s = getMethodParamNames(MethodInfoTest.class.getDeclaredMethod("sameType", Type[].class, Class[].class));
		System.out.println(Arrays.toString(s));
		s = getMethodParamNames(MethodVisitor.class.getDeclaredMethod("visitAnnotation", String.class, Boolean.TYPE));
		System.out.println(Arrays.toString(s));
	}

}
