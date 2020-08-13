$(function(){
	$('#searchButton').click(function(e) {
		initTable();
	});
});

function initTable() {
	$('#confTable').bootstrapTable({
      url: '/conf/conf/find',
      method: 'post',
      striped: true,
      toolbar: "#toolbar",
      sidePagination: "true",
      striped: true, // 是否显示行间隔色
      pageSize: "5",
      pagination: true, // 是否分页
      sortable: true, // 是否启用排序
      search:true,
      showColumns: true,
      showRefresh: true,
	  sidePagination: "client",
      columns: [
        {
          field: 'checkBox',//可不加
		  checkbox: true,
          align: "center"
        },
        {
          field: 'confKey',
          title: '参数代码'
        },
        {
          field: 'confName',
          title: '参数名称'
        },
        {
          field: 'type',
          title: '参数类型'
        },
		{
          field: 'value',
          title: '参数值'
        },
		{
          field: 'dtal',
          title: '备注'
        }
      ]
    });
}