var sy = sy || {};

/**
 * 去字符串空格
 * 
 * @author 
 */
sy.trim = function(str) {
	return str.replace(/(^\s*)|(\s*$)/g, '');
};
sy.ltrim = function(str) {
	return str.replace(/(^\s*)/g, '');
};
sy.rtrim = function(str) {
	return str.replace(/(\s*$)/g, '');
};
sy.isEmpty = function(str){
	if(str==undefined||str==null) return true;
	if(sy.trim(str).length==0)  return true;
	return false;
};

/**
 * 判断开始字符是否是XX
 * 
 * @author 孙宇
 */
sy.startWith = function(source, str) {
	var reg = new RegExp("^" + str);
	return reg.test(source);
};
/**
 * 判断结束字符是否是XX
 * 
 * @author 孙宇
 */
sy.endWith = function(source, str) {
	var reg = new RegExp(str + "$");
	return reg.test(source);
};

/**
 * iframe自适应高度
 * 
 * @author 孙宇
 * 
 * @param iframe
 */
sy.autoIframeHeight = function(iframe) {
	iframe.style.height = iframe.contentWindow.document.body.scrollHeight + "px";
};

/**
 * 设置iframe高度
 * 
 * @author 孙宇
 * 
 * @param iframe
 */
sy.setIframeHeight = function(iframe, height) {
	iframe.height = height;
};

/**
 * js日期的格式化
 * @param fmt
 * @returns
 * 调用： 
	var time1 = new Date().Format("yyyy-MM-dd");
	var time2 = new Date().Format("yyyy-MM-dd HH:mm:ss");  
 */
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
