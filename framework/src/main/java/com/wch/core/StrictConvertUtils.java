package com.wch.core;

import org.apache.commons.beanutils.ConvertUtilsBean;

/**     
 * 项目名称：retail-core  
 * 类名称：StrictConvertUtils  
 * 类描述：   严格类型转换工具类
 * 创建时间：2014年10月10日 上午10:53:04  
 * @author guanhuangbai  
 * @version 1.0
 */
public class StrictConvertUtils extends ConvertUtilsBean {
    private static StrictConvertUtils Instance = new StrictConvertUtils();
    
    private StrictConvertUtils() {
        //当无法转换时，默认会抛出转换异常，并设置默认值为null
        register(true, false, 0);
    }
    
    public static StrictConvertUtils getInstance() {
        return Instance;
    }
    
}
