<!DOCTYPE html>
<html>
<head>
    <title>ReBrain</title>
</head>
<body>
<h1>ReBrain</h1><hr>
<h4>Подробная информация о карточке</h4>
<b>Название карточки: </b>${card.title}<br>
<b>Описание карточки: </b>${card.descr}<br>
<hr>
<form action="/card/delete/${card.id}" method="post">
    <input type="submit" value="Удалить карточку"/>
</form>
</body>
</html>