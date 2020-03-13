layui.use(['table', 'ax','admin'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
    * 基础字典管理
    */
    var College = {
        tableId: "collegeTable"
    };

    /**
    * 初始化表格的列
    */
    College.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'collegeId', hide: true, title: 'id'},
            {field: 'collegeName', sort: false, title: '学院名称'},
            {field: 'schoolName', sort: false, title: '学校'},
            {field: 'collegeDesc', sort: false, title: '学院简介'},
            {field: 'sort', sort: false, title: '排序号',width:30},
            {field: 'updateTime', sort: false, title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };



    /**
    * 渲染表格
    */
    // 渲染表格
    var tableResult = table.render({
        elem: '#' + College.tableId,
        url: Feng.ctxPath + '/college/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: College.initColumn()
    });
    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        College.openAdd();
    });
    //搜索
    $('#btnSearch').click(function () {
        College.search()
    })
    //添加
    College.openAdd = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加',
            content: Feng.ctxPath + '/college/college_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(College.tableId);
            }
        });
    }
    //修改
    College.onEdit = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑',
            content: Feng.ctxPath + '/college/college_edit?collegeId=' + data.collegeId,
            end: function () {
                admin.getTempData('formOk') && table.reload(College.tableId);
            }
        });
    }
    //查询
    College.search = function () {
        var queryData = {};
        queryData['collegeName'] = $("#collegeName").val();
        table.reload(College.tableId, {where: queryData});
    }

    //删除
    College.onDelete = function (data) {
        var operation = function () {
        var ajax = new $ax(Feng.ctxPath + "/college/delete", function () {
            Feng.success("删除成功!");
            College.search()
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("collegeId", data.collegeId);
        ajax.start();
        };
        Feng.confirm("是否删除菜单" + data.collegeName + "?", operation);
    }

    // 工具条点击事件
    table.on('tool(' + College.tableId + ')', function (obj) {
        var data = obj.data;
            var layEvent = obj.event;
        if (layEvent === 'edit') {
            College.onEdit(data);
        } else if (layEvent === 'delete') {
            College.onDelete(data);
        }
    });


});
