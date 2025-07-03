Правила пользования заглушкой
1)Команда для запуска jar-файла без явного указания профиля настроек:
java -Xms2048m -Xmx4096m -jar /Users/davidgvaliya/Desktop/Мак/Java/HomeworkStub/target/HomeworkStub-0.0.1-SNAPSHOT.jar
2)Команда для запуска jar-файла с профилем test1:
java -Dspring.profiles.active=test1 -Xms2048m -Xmx4096m -jar /Users/davidgvaliya/Desktop/Мак/Java/HomeworkStub/target/HomeworkStub-0.0.1-SNAPSHOT.jar
3)Команда для запуска jar-файла с профилем test2:
java -Dspring.profiles.active=test2 -Xms2048m -Xmx4096m -jar /Users/davidgvaliya/Desktop/Мак/Java/HomeworkStub/target/HomeworkStub-0.0.1-SNAPSHOT.jar
