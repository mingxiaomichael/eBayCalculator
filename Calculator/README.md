# Flexible Calculator

## Features
- Basic arithmetic operations [ADD, SUBTRACT, MULTIPLY, DIVIDE]
- Chaining calculation for multiple operations
- Allow new operations to be added without requiring changes to its existing code
- Error handling for invalid operations and division by zero

## Quick install and start

Environment:
- JDK: Java 17
- Spring Boot: 3+

Install repository:
- Run `git clone git@github.com:mingxiaomichael/eBayCalculator.git`. Or install the zip file: https://github.com/mingxiaomichael/eBayCalculator/archive/refs/heads/main.zip
- Build project with maven: `mvn clean install`

Then, Run `CalculatorApplication.java`!

## API documentation

This Calculator application is on `http://localhost:8080`

### **Basic calculation**: Performs a basic arithmetic operation between two numbers.

End point: `/api/v1/calculator/basic`

Method: `POST`

Example:

`POST` `http://localhost:8080/api/v1/calculator/basic`

Request Body:
```
{
"operation": "DIVIDE",
"num1": 10,
"num2": 2
}
```
Response:
         
```5```


### **Chaining calculation**: Performs a sequence of operations starting from an initial value.

End point: `/api/v1/calculator/chaining`

Method: `POST`

Example:

`POST` `http://localhost:8080/api/v1/calculator/chaining`

Request Body:

```
{
    "initial": 2,
    "calculations": [
        {
            "operation": "ADD",
            "num": 100
        }, 
        {
            "operation": "SUBTRACT",
            "num": 10
        }, 
        {
            "operation": "MULTIPLY",
            "num": 10
        }, 
        {
            "operation": "DIVIDE",
            "num": 5
        }
    ]
}
```

Response:

```184```


### Adding calculation operation

End point: `/api/v1/calculator/addOperation`

Method: `GET`

Directly call this API, then create a calculation operation, no need to change code.

Example:

`GET` `/api/v1/calculator/addOperation`

```Java
public void addOperation() {
    operationFactory.addOperation("MOD", new Operation() {
        @Override
        public BigDecimal calculate(BigDecimal a, BigDecimal b) {
            if (b.compareTo(BigDecimal.ZERO) == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            return a.remainder(b);
        }
    });
}
```

In this code, we add a MOD operation.

`POST` `http://localhost:8080/api/v1/calculator/basic`

Request Body:
```
{
    "operation": "MOD",
    "num1": 99,
    "num2": 10
}
```

Response:

```9```

## Some details for designing a flexible calculator

### Why use the Factory Pattern for a Calculator?

- Separate the creation of operations: The calculator can perform multiple operations (e.g., addition, subtraction, multiplication, division). The factory pattern helps separate the creation of these operation objects from the rest of the code, making the calculator logic cleaner.
- Extensibility: The Factory Pattern allows new operations to be easily added without modifying the existing code. This promotes the Open/Closed Principle, which states that the system should be open for extension but closed for modification.
- Centralized Object Creation: The Factory Pattern centralizes the creation logic in one place, making the system more maintainable and flexible.

### Using `BigDecimal`

In Java, BigDecimal is used when you need to perform precise mathematical calculations with very high precision, especially for financial, scientific, and other high-accuracy applications.

Request Body:

```
{
    "operation": "DIVIDE",
    "num1": 10000000000,
    "num2": 3
}
```

Response:

```3333333333.33333```

### Exception Handling

- Invalid operations: Operations outside the existing operation will be considered as a valid operation, and throw `IllegalArgumentException`.
  
  Request Body:  
  ```
    {
        "operation": "QQQ",
        "num1": 99,
        "num2": 10
    }
  ```
  Response:
  ```
  {
      "error": "Invalid request",
      "message": "Invalid operation type"
  }
  ```

- Division by zero: It is an illegal mathematical operation, throws `ArithmeticException`.
  ```
    {
        "operation": "DIVIDE",
        "num1": 99,
        "num2": 0
    }
  ```
  Response:
  ```
  {
      "error": "Math error",
      "message": "Cannot divide by zero"
  }
  ```

