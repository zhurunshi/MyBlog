$(function(){
	$('#confTable').bootstrapTable({
      url: '/conf/conf/find',
      method: 'get',
      striped: true,
      toolbar: "#toolbar",
      sidePagination: "true",
      striped: true, // 是否显示行间隔色
      //search : "true",
      uniqueId: "ID",
      pageSize: "5",
      pagination: true, // 是否分页
      sortable: true, // 是否启用排序
      search:true,
      showColumns: true,
      showRefresh: true,
      columns: [
        {
          //field: 'Number',//可不加
          title: '序号',//标题  可不加
          align: "center",
          width: 40,
          formatter: function (value, row, index) {
            return index+1;
          }
        },
        {
          field: 'id',
          title: 'id'
        },
        {
          field: 'firstName',
          title: 'firstName'
        },
        {
          field: 'lastName',
          title: 'lastName'
        },
        {
          field: 'price',
          title: '操作',
          width: 120,
          align: 'center',
          valign: 'middle',
          formatter: actionFormatter,
        },
      ]
    });
});