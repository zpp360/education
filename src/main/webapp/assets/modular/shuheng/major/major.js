layui.use(['table', 'ax','admin'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
    * 基础字典管理
    */
    var Major = {
        tableId: "majorTable"
    };

    /**
    * 初始化表格的列
    */
    Major.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'majorId', hide: true, title: '专业id'},
            {field: 'majorName', sort: false, title: '专业名称'},
            {field: 'schoolName', sort: false, title: '学校'},
            {field: 'collegeName', sort: false, title: '学院'},
            {field: 'majorDesc', sort: false, title: '描述'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };



    /**
    * 渲染表格
    */
    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Major.tableId,
        url: Feng.ctxPath + '/major/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: Major.initColumn()
    });
    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Major.openAdd();
    });
    //搜索
    $('#btnSearch').click(function () {
        Major.search()
    })
    //添加
    Major.openAdd = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加',
            content: Feng.ctxPath + '/major/major_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Major.tableId);
            }
        });
    }
    //修改
    Major.onEdit = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑',
            content: Feng.ctxPath + '/major/major_edit?majorId=' + data.majorId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Major.tableId);
            }
        });
    }
    //查询
    Major.search = function () {
        var queryData = {};
        queryData['majorName'] = $("#majorName").val();
        table.reload(Major.tableId, {where: queryData});
    }

    //删除
    Major.onDelete = function (data) {
        var operation = function () {
        var ajax = new $ax(Feng.ctxPath + "/major/delete", function () {
            Feng.success("删除成功!");
            Major.search()
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("majorId", data.majorId);
        ajax.start();
        };
        Feng.confirm("是否删除菜单" + data.majorName + "?", operation);
    }

    // 工具条点击事件
    table.on('tool(' + Major.tableId + ')', function (obj) {
        var data = obj.data;
            var layEvent = obj.event;
        if (layEvent === 'edit') {
            Major.onEdit(data);
        } else if (layEvent === 'delete') {
            Major.onDelete(data);
        }
    });


});
