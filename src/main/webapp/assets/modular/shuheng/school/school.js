layui.use(['table', 'ax','admin'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
    * 基础字典管理
    */
    var School = {
        tableId: "schoolTable"
    };

    /**
    * 初始化表格的列
    */
    School.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'schoolId', hide: true, title: 'ID'},
            {field: 'schoolName', sort: false, title: '学校名称'},
            {field: 'schoolTel', sort: false, title: '电话'},
            {field: 'schoolAddres', sort: false, title: '地址'},
            {field: 'schoolDesc', sort: false, title: '学习简介'},
            {field: 'updateTime', sort: false, title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };



    /**
    * 渲染表格
    */
    // 渲染表格
    var tableResult = table.render({
        elem: '#' + School.tableId,
        url: Feng.ctxPath + '/school/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: School.initColumn()
    });
    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        School.openAdd();
    });
    //搜索
    $('#btnSearch').click(function () {
        School.search()
    })
    //添加
    School.openAdd = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加',
            content: Feng.ctxPath + '/school/school_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(School.tableId);
            }
        });
    }
    //修改
    School.onEdit = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑',
            content: Feng.ctxPath + '/school/school_edit?schoolId=' + data.schoolId,
            end: function () {
                admin.getTempData('formOk') && table.reload(School.tableId);
            }
        });
    }
    //查询
    School.search = function () {
        var queryData = {};
        queryData['schoolName'] = $("#schoolName").val();
        table.reload(School.tableId, {where: queryData});
    }

    //删除
    School.onDelete = function (data) {
        var operation = function () {
        var ajax = new $ax(Feng.ctxPath + "/school/delete", function () {
            Feng.success("删除成功!");
            School.search()
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("schoolId", data.schoolId);
        ajax.start();
        };
        Feng.confirm("是否删除菜单" + data.schoolName + "?", operation);
    }

    // 工具条点击事件
    table.on('tool(' + School.tableId + ')', function (obj) {
        var data = obj.data;
            var layEvent = obj.event;
        if (layEvent === 'edit') {
            School.onEdit(data);
        } else if (layEvent === 'delete') {
            School.onDelete(data);
        }
    });


});
