для запуска otusPageObjectTest введите свои параметры -Dusername, -Dpassword, -Dname, -Dsurname, -DnameLatin, -DsurnameLatin, -Dbirthday, -DidBlogName,
браузер -Dbrowser принимает значния CHROME, FIREFOX, OPERA, по умолчанию будет chrome

Пример:
mvn clean -Dtest=OtusPageObjectTest test -Dusername=login -Dpassword=pwd -Dname=name -Dsurname=surname -DnameLatin=nameLatin -DsurnameLatin=surnameLatin -Dbirthday=birthday -DidBlogName=idBlogName