<!DOCTYPE html>
<html>
<head>
    <title>ReBrain</title>
</head>
<body>
<h1>ReBrain</h1><hr>
<h4>Карточки</h4>
<form action="/" method="get">
    Поиск по названию карточки: <input type="text" name="title"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list cards as card>
    <div>
        <p><b>${card.title}</b> | <a href="/card/${card.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>Карточек нет</h3>
</#list>
<hr>
<h3>Создать новую карточку</h3>
<form action="/card/create" method="post">
    Название объявления: <input type="text" name="title"/><br><br>
    Описание объявления: <input type="text" name="descr"/><br><br>
    <input type="submit" value="Добавить карточку"/>
</form>
</body>
</html>