package com.wch.generator.mybaits.algorithm;

import java.util.List;

/**
 * 
 * @author wch
 * @date 2017年5月6日 上午10:59:05
 * @param <T>
 */
public interface PartialHandler<T> {
	Integer handle(List<T> list) ;
}
