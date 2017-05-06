package com.wch.generator.mybaits.algorithm;

import java.util.List;

/**
 * 
 * @author wch
 * @date 2017年5月6日 上午10:58:57
 */
public class PartialList {

	public static <T extends Object> int cyclicHandle(List<T> list ,Integer step , PartialHandler<T> partialHandler){
		int totalSize = list.size();
		step = (step == null || step < 0 ) ? 30 : step;
		int totalRefectRow = 0 ;
		int resultRow = 0 ;
		int fromIndex = 0;
		int toIndex = step;
		
		if(totalSize <= step) {
			  resultRow = partialHandler.handle(list);
			  totalRefectRow += resultRow;
		}else {
			//当当前的fromIndex　<＝ 总size的时候结束循环
			while ( fromIndex < totalSize ) {
				List<T> subList = list.subList(fromIndex, toIndex);
				resultRow = partialHandler.handle(subList);
				totalRefectRow += resultRow;
				//每次添加相同的步长
				fromIndex = fromIndex + step;
				toIndex = toIndex + step;
				if(toIndex > totalSize) { toIndex = totalSize; }
			}
		}
		
		return totalRefectRow;
	}
}

