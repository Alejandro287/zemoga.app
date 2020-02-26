# Portfolio Java Web App

This is a tech test for the position of Java Developer

## Getting Started

1. Open Git Bash.Change the current working directory to the location where you want the cloned directory to be made.
2. Type: 
```sh
$ git clone https://github.com/Alejandro287/zemoga.app.git
```
3. Change the current working directory to each derectory and run each spring boot project typing the following command:
```sh
$ mvn spring-boot:run
```

## Prerequisites

* JDK 1.8
* Git
* IDE (IntelliJ was used in this case)
* Maven
* MySQL
* Tomcat
* Postman

## Tech Stack 

* JDK 1.8
* Java 8
* Spring 5
* Spring Boot 2
* Spring Data
* Lombok: avoid boilerplate code.
* MySQL: SQL database and its connector with Spring.
* Spring HATEOAS: Implement a Hypermedia Driven RESTful API
* Twitter4j: Connector with Twitter API.
* Project Spring Reactor Webflux: Implement rective programming and recative oriented API.
* Spring Cloud Config: Externalize configuration of the services.
* Spring Cloud Netflix Eureka: A server registry and service discovery.
* Spring Cloud Netflix Zuul: The Gateway and router to the services.
* Spring Actuator: Expose operational information about the running application
* Ajax/jQuery
* Junit 5
* Fourth level of API RESTful
* Microservices Architecture
* MVC Architecture

## Using of the Frontend side of the application

After running every component of the app, go to portfolio_front and open in your browser the userList.html

![Image upload screen](userList.JPG)

You can now navigate to any user profile page. 

![Image upload screen](user.JPG)

From this page you can be redirecto to Twitter posts. 

![Image Tweet](Tweet.JPG)

If you click on the button you will go to another screen to upload the user information.

![Image upload screen](updateUser.JPG)

## Using of the Backend side of the application

An example of the use of the API and its differents endpoints will be shown following.   

### GET  -  http://localhost:8081/api/users

This endpoint returns the user list.

* Parameters: N/A

* Response: (JSON)

```sh
{
    "_embedded": {
        "users": [
            {
                "id": 1,
                "description": "Lord Commander of the Night's Watch and King of the Free Folk",
                "imageUrl": "https://pbs.twimg.com/profile_images/901947348699545601/hqRMHITj_400x400.jpg",
                "username": "LordSnow",
                "title": "Jon Snow",
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/api/users/1"
                    },
                    "tweets": {
                        "href": "http://localhost:8081/api/users/1/tweets"
                    }
                }
            },
            {
                "id": 2,
                "description": "The Mother of Dragons",
                "imageUrl": "https://pbs.twimg.com/profile_images/1117967801652617216/i8PWXebo_400x400.jpg",
                "username": "Daenerys",
                "title": "Daenerys Targaryen",
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/api/users/2"
                    },
                    "tweets": {
                        "href": "http://localhost:8081/api/users/2/tweets"
                    }
                }
            },
            {
                "id": 3,
                "description": "Tyrion of House Lannister. Imp, Halfman. Never forget what you are, for surely the world will not",
                "imageUrl": "https://pbs.twimg.com/profile_images/668279339838935040/8sUE9d4C_400x400.jpg",
                "username": "GoT_Tyrion",
                "title": "Tyrion Lannister",
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/api/users/3"
                    },
                    "tweets": {
                        "href": "http://localhost:8081/api/users/3/tweets"
                    }
                }
            },
            {
                "id": 4,
                "description": "Sansa like true knights, songs and dogs. I hate babysitting. Currently being polite and trying not to die",
                "imageUrl": "https://pbs.twimg.com/profile_images/1812114126/newtwitter_400x400.jpg",
                "username": "Lady_Sansa",
                "title": "Sansa Stark",
                "_links": {
                    "self": {
                        "href": "http://localhost:8081/api/users/4"
                    },
                    "tweets": {
                        "href": "http://localhost:8081/api/users/4/tweets"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8081/api/users"
        }
    }
}
```

### GET  -  http://localhost:8081/api/users/1

This endpoint returns a specific user data.

* Parameters: N/A

* Response: (JSON)

```sh
{
    "id": 1,
    "description": "Lord Commander of the Night's Watch and King of the Free Folk",
    "imageUrl": "https://pbs.twimg.com/profile_images/901947348699545601/hqRMHITj_400x400.jpg",
    "username": "LordSnow",
    "title": "Jon Snow",
    "_links": {
        "self": {
            "href": "/api/users/1"
        },
        "users": {
            "href": "/api/users"
        },
        "tweets": {
            "href": "/api/users/1/tweets"
        }
    }
}
```

### GET  -  http://localhost:8081/api/users/1/tweets

This endpoint returns the five last Tweets posted for the user in their twitter account.

* Parameters: N/A

* Response: (JSON)

```sh
{
    "_embedded": {
        "tweets": [
            {
                "name": "Jon Snow",
                "text": "Get the popular Dragon Rings for FREE now!🔥 Just cover shipping✈️\n\nLink: https://t.co/CdRFLwCzyj https://t.co/hsYwuwIIVM",
                "_links": {
                    "self": {
                        "href": "https://twitter.com/LordSnow/status/1230949186738302982"
                    }
                }
            },
            {
                "name": "Jon Snow",
                "text": "https://t.co/reotGSUszQ",
                "_links": {
                    "self": {
                        "href": "https://twitter.com/LordSnow/status/1230466489884516352"
                    }
                }
            },
            {
                "name": "Jon Snow",
                "text": "That reaction 😂 https://t.co/yK7vBsPuhy",
                "_links": {
                    "self": {
                        "href": "https://twitter.com/LordSnow/status/1229355758992732162"
                    }
                }
            },
            {
                "name": "Stranger Things",
                "text": "First official teaser for Stranger Things season 4! https://t.co/HgV5c0JHra",
                "_links": {
                    "self": {
                        "href": "https://twitter.com/stnetflixtv/status/1228332795984039936"
                    }
                }
            },
            {
                "name": "Jon Snow",
                "text": "Happy Valentine's Day 🌹🗡️ https://t.co/eeRW7zi42P",
                "_links": {
                    "self": {
                        "href": "https://twitter.com/LordSnow/status/1228264673914576896"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8081/api/users/1/tweets"
        },
        "user": {
            "href": "http://localhost:8081/api/users/1"
        },
        "users": {
            "href": "http://localhost:8081/api/users"
        }
    }
}
```

### PUT  -  http://localhost:8081/api/users/1/tweets

This endpoint is used to update a user, providing in the body the new data in JSON format.

* Parameters:

Body: (JSON)

```sh
{
    "description": "Lord Commander of the Night's Watch and King of the Free Folk",
    "imageUrl": "https://pbs.twimg.com/profile_images/901947348699545601/hqRMHITj_400x400.jpg",
    "username": "LordSnow",
    "title": "Juan Nieve"
}
```

* Response: (JSON)

```sh
{
    "id": 1,
    "description": "Lord Commander of the Night's Watch and King of the Free Folk",
    "imageUrl": "https://pbs.twimg.com/profile_images/901947348699545601/hqRMHITj_400x400.jpg",
    "username": "LordSnow",
    "title": "Juan Nieve",
    "_links": {
        "self": {
            "href": "/api/users/1"
        },
        "users": {
            "href": "/api/users"
        },
        "tweets": {
            "href": "/api/users/1/tweets"
        }
    }
}
```

## API Documentation

The Swagger documentation autogenerated from the API is shown in following link. 

https://app.swaggerhub.com/apis-docs/Alejandro287/PORTFOLIO_JAVA_WEB_APP/1.0.0

## Diagram of the architecture 

## Built

## Deployment

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Alejandro Cano Rico** - *3134752698* - *alejocano287@gmail.com* 

