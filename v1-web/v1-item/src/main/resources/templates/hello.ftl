<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
        id:${student.id}
            name:${student.name}
            date:${student.date?date}
            date:${student.date?time}dd
            date:${student.date?datetime}
            date:${student.date?string('yyyy年MM月dd日 HH时mm分ss秒')}
            <h1>展示集合的信息</h1>

            ${null!'是空的哦~'}
            <#if (money>8000)>
            有钱
            <#elseif (money>7000)>
            有点钱
            <#else>
            没有钱
            </#if>
        <#if null??>
            该值不为null
            <#else >
                该值为null
        </#if>

</body>
</html>