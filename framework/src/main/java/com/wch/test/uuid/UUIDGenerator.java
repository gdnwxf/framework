package com.wch.test.uuid;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UUIDGenerator {

		public static void main(String[] args) {
			System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		}
}
