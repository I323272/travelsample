Prerequisites

1.Create Heroku account
2.Make sure you have Java 1.7 and Maven installed.  Also, install the [Heroku Toolbelt](https://toolbelt.heroku.com/).
3.Set up enviornment variable for Java/bin and maven/bin path
4.Install Git

Set up

Open Git Bash
```sh
$ git clone https://github.com/nehaknp/travelsample
$ cd C:\Users\<User>\travelsample
$ mvn clean install
$ foreman start web -f Procfile.windows
```

Your app should now be running on [localhost:8080](http://localhost:8080/).

## Commiting your code
$ heroku login
Heroku Username:
Password:
$ heroku create
$ git push heroku master
$ git add .
$ git commit -m "commit message"
$ git push heroku master
$ heroku open --app 