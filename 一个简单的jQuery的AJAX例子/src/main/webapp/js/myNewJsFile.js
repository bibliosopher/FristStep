//================================================工具函数================================================================
//这个数组存储的是每一行的name,这个name定义了从服务器传输过来的标准,以及从页面发送给武器的标准
var stuVar = ["stuId", "stuName", "gradeId", "bornDate", "stuGender", "stuPhone", "stuEmail", "stuAddress", "stuPassword"];
//这个数组存储的是一个每一行的具体名字
var placeholder = ["学号", "姓名", "班号", "出生日期", "性别", "电话", "邮箱", "地址", "密码"];

/**
 * 这个函数是为了创建一个 修改按钮 这个 已经在外围包裹了td,并且,这个按钮已经绑定了修改事件
 */
function creatRevice() {
    return $("<td><input class='btn btn-default' type='button' value='修改' onclick='reviseStu(this)'></td>");
}

function creatReviceNotTd() {
    return $("<input class='btn btn-default' type='button' value='修改' onclick='reviseStu(this)'>");
}

/**
 * 这个函数是为了创建一个 修改按钮 这个 已经在外围包裹了td,并且,这个按钮已经绑定了删除事件
 */
function creatDelete() {
    return $("<td><input class='btn btn-default' type='button' value='删除' onclick='deleteStu(this)'></td>");
}

function creatDeleteNotTd() {
    return $("<input class='btn btn-default' type='button' value='删除' onclick='deleteStu(this)'>");
}

/**
 * 这个函数是为了创建一个 保存按钮 这个按钮已经绑定了 这个保存是为了实现修改操作的
 */
function createPreForRevise() {
    return $("<input class='btn btn-default' type='button' value='保存' onclick='preStuForRevise(this)'>");
}

/**
 * 这个函数是为了创建一个 保存按钮 这个按钮已经绑定了 这个保存是为了实现添加操作的
 */
function createPreForAdd() {
    return $("<td><input class='btn btn-default' type='button' value='保存' onclick='preStuForAdd(this)'></td>");
}

/**
 * 这个函数用来创建一个input对象,参数有 placeholder,value,type
 */
function creatInput(placeholder, value, type) {
    var input = $("<input class='form-control'>")
    input.attr("type", type);
    input.attr("placeholder", placeholder);
    input.attr("value", value);
    return input;
}

//================================================工具函数================================================================


//================================================页面加载事件开始=========================================================
/**
 * 这个匿名函数是为了个body绑定一个onload事件
 * 因为加载学生数据这个操作应该在页面加载完毕的时候就执行的
 * 这个函数规定了函数的请求方式,json接收和转换,但不执行具体操作,具体操作位于loadOpe()函数中
 */
$(function () {
    $.get("/student?option=load", function (jsonStu) {
        var students = jQuery.parseJSON(jsonStu);
        loadOpe(students);//这是将学生信息加载在domTree上面的具体操作
    });
})

/**
 * 这个函数将学生数据加载到domTree的具体操作,
 * 学生数据传递的是一个对象数组,是多个的,因此这个函数执行的是将每个tr加载进去
 * 具体每个tr怎么创建保存在creatTr()函数中
 */
function loadOpe(students) {
    var tbody = $("#content tbody");
//这里获取到的是一个 student
    for (var i = 0; i < students.length; i++) {
        var tr = creatTr(students[i]);
        tbody.append(tr);//这是对每个tr进行创建的具体操作,也就是对每个学生对象进行创建的具体流程
    }
}

/**
 * 这个函数是每一个学生对象进行tr内部domTree的创建
 * 因为学生传递的是一个对象数组,因此需要之后对每一个学生对象进行domTree创建
 */
function creatTr(student) {
    var tr = $("<tr></tr>");//创建一个tr

//0-9是因为我规定中有九个数据,我试过有student.length来操作,但是无法实现,于是写死了
    for (var i = 0; i < 9; i++) {
        if (stuVar[i] == "bornDate") {
            var date = jutils.formatDate(new Date(student[stuVar[i]]), "YYYY-MM-DD");
            var td = $("<td></td>").text(date);
        } else {
            var td = $("<td></td>>").text(student[stuVar[i]]);
        }
        tr.append(td);
    }
//第十行创建的是修改操作,同时这个操作在通过creatRevice()创建的时候已经绑定了点击事件reviseStu(this)
    tr.append(creatRevice());
//第11行创建的是删除操作,同时这个操作在通过creatDelete()创建的时候已经绑定了点击事件deleteStu(this)
    tr.append(creatDelete());
    return tr;
}

//================================================页面加载事件结束=================================================


//==========================================删除事件开始==========================================================
/**
 * 这个函数是删除按钮的绑定事件,
 * 这个事件要求我们通过post请求传入一个stuId的数据,首先需要得到这个stuId
 * 并将这个数据返回给服务器,服务器在数据库删除之后返回给前端界面一个处理结果
 * 处理结果为true的时候,删除domTree结构
 *
 * 获取stuId具体操作在getStuId()函数中
 * 删除domTree具体操作位于deleteDom()函数中
 *
 */
function deleteStu(btn) {
    var stuId = getStuId(btn);
    $.post("/student?option=delete", {
        stuId: stuId
    }, function (decide) {
        if (decide) {
            deleteDom(btn);
        } else {
            alert("发生了预料之外的错误,删除数据出错")
        }
    });
}

/**
 * 这个函数是获得删除这个操作所在这一行的stuId
 * 因为我知道第一个是stuId,所以直接取了第一个,但是我觉得这个地方应该有更多更复杂的操作
 */
function getStuId(btn) {
    return btn.parentNode.parentNode.firstChild.innerText;
// var a = $(btn).parent().first().text();  实在搞不明白了
}

/**
 * 这个函数是将这一行的学生数据从domTree上删除,只有当返回结果为true时执行
 */
function deleteDom(btn) {
    var tr = btn.parentNode.parentNode;
    tr.remove();
}

//==========================================删除事件结束==========================================================

//==========================================修改事件以及他对应的保存事件开始==========================================

/**
 * 这个函数执行的是一个修改操作,但是这个操作不存在想服务器发送请求,只是单纯的将domTree改为可修改状态
 * 注意:
 * 1.由于删除的时候瞎写的是获取td内的内容,因此为了防止更多更复杂的操作,我们需要在修改状态的时候将删除按钮去掉
 * 2.我们将增加时候的可修改状态和修改时候的可输入状态定义为一种状态,这种修改状态是否需要判定只能存在一种(保留意见,先不做处理)
 * 因为上次是查domTree,这次是对节点进行操作
 */
function reviseStu(btn) {
//这个时候我们发现,添加一行和修改状态的操作是一样的,抽成函数

    var childNodes = btn.parentNode.parentNode.childNodes;//找到所有的td节点

    tdToInput(childNodes, createPreForRevise);//执行具体操作
}

/**
 * 这个函数是为了将td转变成有初始值的input
 * 由于我们知道他有11个所有我就直接写了,但是这是一种不可取的做法,这样会使下次变更出现问题
 *
 * 我们这里做了一个限定,就是学号不可修改,因为我们需要一个数据来操作底层的 update(建表问题)
 */
function tdToInput(childNodes, createPreForRevise) {
//将前9列替换为input输入框
    for (i = 1; i < 9; i++) {
        var input;
        if (i == 3) {
            input = creatInput(placeholder[i], $(childNodes[i]).text(), "date");//创建date
        } else {
            input = creatInput(placeholder[i], $(childNodes[i]).text(), "text");//创建text
        }
        $(childNodes[i]).empty();//将原先td内的数据清空
        $(childNodes[i]).append(input);//将input替换进去
    }
    $(childNodes[9]).empty();
    $(childNodes[10]).empty();
    $(childNodes[9]).append(createPreForRevise());
}

/**
 * 这是修改事件对应的保存操作,因为不同的保存返回的操作不同,一个是更改,一个是插入
 * 所以需要有不同的操作
 * 第一步,获取td里面的数据,我们需要包装成一个对象吗(先不包装直接使用) TODO 打算
 *
 * 这个虽然可以修改,但是没有做验证,所以需要谨慎修改
 *
 *
 */
function preStuForRevise(btn) {
    var tds = btn.parentNode.parentNode.childNodes;

    $.post("/student?option=revise", {
        stuId: $(tds[0]).text(),
        stuName: $(tds[1].firstChild).val(),
        gradeId: $(tds[2].firstChild).val(),
        bornDate: $(tds[3].firstChild).val(),
        stuGender: $(tds[4].firstChild).val(),
        stuPhone: $(tds[5].firstChild).val(),
        stuEmail: $(tds[6].firstChild).val(),
        stuAddress: $(tds[7].firstChild).val(),
        stuPassword: $(tds[8].firstChild).val()
    }, function (result) {
//于是这里需要去修改domTree了
        if (result) {
            reviseDom(btn);
        } else {
            alert("数据输入有误,修改失败");
        }
    })
}

/**
 *这个函数是用来修改domTree的,将input全部转换为td里面的内容
 */
function reviseDom(btn) {
    var tds = btn.parentNode.parentNode.childNodes;//获取所有的td
    for (var i = 1; i < 9; i++) {
        var value = $(tds[i].firstChild).val()

        $(tds[i]).empty();
        $(tds[i]).text(value);
    }
    $(tds[9]).empty()
//第十行创建的是修改操作,同时这个操作在通过creatRevice()创建的时候已经绑定了点击事件reviseStu(this)
    $(tds[9]).append(creatReviceNotTd());
//第11行创建的是删除操作,同时这个操作在通过creatDelete()创建的时候已经绑定了点击事件deleteStu(this)
    $(tds[10]).append(creatDeleteNotTd());
}

//==========================================修改事件以及他对应的保存事件结束==========================================
//==========================================添加一行以及他对应的保存事件开始==========================================
/**
 * 这是为添加事件设置的操作,在添加这个点击事件中不应该有ajax的请求,只是domTree的操作
 * 添加一行我们需要做什么,我们需要在开始的位置创建 11个td,,并按照需求在这11个td里面添加 input
 *
 * todo 由于传入数据没有做数据验证,所以尽可能保证传入数据的准确性 TODO
 */
$("#add").click(function () {
    var tr = createTrWithAdd();
    $("#content tbody").prepend(tr);//因为可能存在数据量过大的问题,所以直接放在开头,保存在末尾
});

/**
 *  这个函数是为了创建一行input的数据框(具体时间add操作里面的一行) 当然可以适当的缩减代码,因为代码重复,但是此处先不缩减
 *
 *  这一行的绑定事件绑定的是添加一行相关的保存事件preStuForAdd(this)
 *
 */
function createTrWithAdd() {
    var tr = $("<tr></tr>");
    for (var i = 0; i < 9; i++) {
        var td = $("<td></td>");
        var input;
        if (i == 3) {
            input = creatInput(placeholder[i], "", "date");
        } else {
            input = creatInput(placeholder[i], "", "text");
        }
        td.append(input);
        tr.append(td);
    }
    tr.append(createPreForAdd());
    tr.append($("<td></td>"));
    return tr;
}

function preStuForAdd(btn) {
    var tds = btn.parentNode.parentNode.childNodes;

    $.post("/student?option=add", {
        stuId: $(tds[0].firstChild).val(),
        stuName: $(tds[1].firstChild).val(),
        gradeId: $(tds[2].firstChild).val(),
        bornDate: $(tds[3].firstChild).val(),
        stuGender: $(tds[4].firstChild).val(),
        stuPhone: $(tds[5].firstChild).val(),
        stuEmail: $(tds[6].firstChild).val(),
        stuAddress: $(tds[7].firstChild).val(),
        stuPassword: $(tds[8].firstChild).val()
    }, function (result) {
//于是这里需要去修改domTree了
        if (result) {
            addDom(btn);
        } else {
            alert("数据输入有误,修改失败");
        }
    })
}

/**
 *这个函数是用来操作添加一行这个操作的domTree结构的,需要将input转换为td,并添加两个按钮
 *
 * 于是我在想保存之后的数据是不是要放到末尾
 */
function addDom(btn) {
    var tds = btn.parentNode.parentNode.childNodes;//获取所有的td

    for (var i = 0; i < 9; i++) {
        var value = $(tds[i].firstChild).val()

        $(tds[i]).empty();
        $(tds[i]).text(value);
    }
    $(tds[9]).empty()
//第十行创建的是修改操作,同时这个操作在通过creatRevice()创建的时候已经绑定了点击事件reviseStu(this)
    $(tds[9]).append(creatReviceNotTd());
//第11行创建的是删除操作,同时这个操作在通过creatDelete()创建的时候已经绑定了点击事件deleteStu(this)
    $(tds[10]).append(creatDeleteNotTd());
}


//==========================================添加一行以及他对应的保存事件结束==========================================
