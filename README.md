# MockService

Lightweight service for creating standalone mock, written in pure Kotlin with netty container.

Service can dynamically get config file for runtime generating controllers for handling outside requests.

Default endpoint for uploading file is ***localhost:8079/config***.
Path could be changed in application.yml file.

#### Example of yaml config file:

```yml
endpoints:
  - request:
      uri: "/hello/{}"
      method: POST
      response:
      successCode: 201
      headers:
        Content-Type: "application/json;charset=utf-8"
        Connection: "keep-alive"
        Custom-Header: "custom-header"
      body: >
        [{
          "id": 1,
          "type": "cat",
          "age": 23,
          "color": "red",
          "name": "Barsic"
        }]
      delay: 2s #(s, ms)
  - request:
      uri: "/hello/{}/cat"
      method: POST
      response:
      headers:
      Content-Type: "application/json;charset=utf-8"
      Connection: "keep-alive"
      body: >
        [{
          "id": 1,
          "type": "cat",
          "age": 23,
          "color": "red",
          "name": "Barsic"
        }]
      delay: 2s
```
Service also supports similar config file in json.