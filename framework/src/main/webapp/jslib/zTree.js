
/**
 * 构造一个zt的对象
 */
var zt = zt || {};

/**
 * 传递的参数如下
 * $.fn.zTree.init($("#treeDemo"), setting, zNodes);
 * opt{
 *     async:false, //默认是true
 *     url:url, 
 *     idField:"resId", //默认是id
 *     autoParam:[{"id","pid","name"}]
 *     parentField:"preRes" //默认是pid
 *     selectedMulti:true 默认是false;
 *     dblClickExpand:true  默认是false;
 *     callback:callback
 *     view:view ztree 提供的view
 *     otherParam:otherParam ztree提供的otherParam
 *     }
 */
zt.init = function(id,opt){
	//id属性
	var idField = opt.idField||"id";
	var parentField = opt.parentField||"pid";
	var setting = {
  			async: {
  				enable: opt.async || true,
  				url: opt.url,
  				autoParam: opt.autoParam || [idField, parentField,"name"],
  				dataType: "text",
  				type:opt.type||'get',
  				otherParam:opt.otherParam
  			},
  			callback:opt.callback,	
  			data: {
  				simpleData: {
  					enable: true,
  					idKey: idField,
  					pIdKey: parentField,
  					rootPId: opt.root || 0,
  				}
  			},
  			view: opt.view || {
				selectedMulti: opt.selectedMulti||false,
				dblClickExpand: opt.dblClickExpand||false
  			}
  			 
  	};
	return $.fn.zTree.init($("#"+id), setting);
}

 
 