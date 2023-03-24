Бэкенд-разработчики сказали, что они всё уже сделали, это фронтендщики тормозят. Поэтому функцию перевода денег с карты на карту мы протестировать через веб-интерфейс не можем.

Зато они выдали нам описание REST API, которое позволяет это сделать, использовать нужно тот же app-deadline.jar.

Вот описание API:

Логин
POST http://localhost:9999/api/auth
Content-Type: application/json

{
  "login": "vasya",
  "password": "qwerty123"
}
Верификация
POST http://localhost:9999/api/auth/verification
Content-Type: application/json

{
  "login": "vasya",
  "code": "599640"
}
В ответе, в поле «token» придёт токен аутентификации, который нужно использовать в последующих запросах.

Подсказка по REST-assured
Просмотр карт
GET http://localhost:9999/api/cards
Content-Type: application/json
Authorization: Bearer {{token}}
Где {{token}} — это значение «token» с предыдущего шага. Фигурные скобки писать не нужно.

Перевод с карты на карту (любую)
POST http://localhost:9999/api/transfer
Content-Type: application/json
Authorization: Bearer {{token}}

{
  "from": "5559 0000 0000 0002",
  "to": "5559 0000 0000 0008",
  "amount": 5000
}
Внимательно изучите запросы и ответы и, используя любой инструмент, который вам нравится, реализуйте тесты API.

В результате выполнения этой задачи вы должны положить в репозиторий следующие файлы:

docker-compose.yml*,
app-deadline.jar,
schema.sql,
код ваших автотестов.
