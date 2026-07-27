# Проект по автоматизации тестирования сайта Caterpillar Careers

> Проект включает в себя UI-автотесты для сайта Caterpillar Careers, с использованием современного стека технологий, интеграцией в CI/CD процессы и подключением отчётности.

## 🔗 Ссылки на проект и инфраструктуру
- [Тестируемый сайт](https://careers.caterpillar.com/en/jobs/)
- [Сборка в Jenkins](http://localhost:8080/job/caterpillar-ui-tests/)
- [Отчёт в Allure Report](http://localhost:8080/job/caterpillar-ui-tests/lastBuild/allure/)

## 🛠 Технологический стек
<p align="left">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="40" height="40"/>
<img src="https://selenide.org/images/selenide-logo-big.png" width="40" height="40"/>
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/gradle/gradle-plain.svg" width="40" height="40"/>
<img src="https://junit.org/junit5/assets/img/junit5-logo.png" width="40" height="40"/>
<img src="https://avatars.githubusercontent.com/u/5879127" width="40" height="40"/>
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jenkins/jenkins-original.svg" width="40" height="40"/>
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" width="40" height="40"/>
</p>

Java | Selenide | Gradle | JUnit 5 | Allure | Jenkins | Docker + Selenoid

## ✅ Тест кейсы
- Страница открывается и показывает вакансии
- Поиск по слову "Engineer" возвращает результаты
- На странице отображается 20 карточек
- Первая вакансия имеет непустое название
- Счётчик вакансий больше 100

## 🚀 Запуск тестов

Локально:
```bash
./gradlew clean test
```

Через Selenoid:
```bash
./gradlew clean test -Dselenide.remote=http://localhost:4444/wd/hub
```

## 📊 Allure отчёт
![Allure](.github/images/allure.png)

## 🏗 Jenkins
![Jenkins](.github/images/jenkins.png)

## 📱 Telegram уведомления
![Telegram](.github/images/telegram.png)
