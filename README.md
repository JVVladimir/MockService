# MockService

The lightweight service for creating a standalone mock, written in pure Kotlin with Netty container.

The service allows getting config file dynamically for runtime generating controllers to handle outside requests.

Default endpoint for uploading file is ***localhost:8079/config***.
The path could be changed in application.yml file.

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
      delay: 2s
  - request:
      uri: "/hello/{}/cat"
      method: POST
    response:
      errorCode: 403
      errorMessage: Forbidden
      delay: 100ms
```
The service also supports similar config file in json format.

With ***{}*** you can specify path variables in URI. Moreover, you can specify return body, headers or status code.

Body is presented as json format or as simple string.
If body won't be specified, so server return 200 OK answer.

The service supplies to return custom success codes (by default is 200) or error code with description message.

You have opportunity to set delay in seconds or milliseconds before response will send back.
