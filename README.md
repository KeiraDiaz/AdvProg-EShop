# Advanced Programming - Eshop Application

__Koyeb Deploy Link__: [Eshop Application](https://excess-penelopa-keiradiaz-83ffea60.koyeb.app/)

[![Continuous Integration (CI)](https://github.com/KeiraDiaz/AdvProg-EShop/actions/workflows/ci.yml/badge.svg)](https://github.com/KeiraDiaz/AdvProg-EShop/actions/workflows/ci.yml) [![SonarCloud Code](https://github.com/KeiraDiaz/AdvProg-EShop/actions/workflows/sonarcloud.yml/badge.svg)](https://github.com/KeiraDiaz/AdvProg-EShop/actions/workflows/sonarcloud.yml) [![SonarCloud Code](https://github.com/KeiraDiaz/AdvProg-EShop/actions/workflows/scorecard.yml/badge.svg)](https://github.com/KeiraDiaz/AdvProg-EShop/actions/workflows/scorecard.yml)




## Contents

1. **MODULE 01**
   - [Reflection 1](#reflection-1-module-01)
   - [Reflection 2](#reflection-2-module-01)
2. **MODULE 2**
   - [Reflection](#reflection-module-02)
3. **MODULE 3**
   - [Reflection](#reflection-module-03)


# Module 01

## Reflection 1 Module 01
  Through this process, there were multiple times I showcased proper coding standards. When creating new variables and functions, I was always warry to name them something that would make sense to me in the future. In addition to that, I try my best to create functions that best reflect their logic, seperating functions if needed but essentially making sure that they would serve one specific purpose. All the function names like `findById` or `update`. In addition to coding practices, during the stage when I was creating comments, at first I added comments that would help me learn better. 
  
  As time went on, I no longer needed them, so I cleaned them up with minimal comments and only include important comments like explanation of intent. In addition to comments, I made sure leave plenty of blank lines between code for readability. For this repository, I used feature branching as my workflow. 
  
  One thing I would like to improve is maybe change the `ProductData` to a hash map for more efficient reasons. All in all, I think my code is satisfactory.


## Reflection 2 Module 01

Writing unit tests is an essential practice to ensure that individual components of a program function as expected. However, determining the right number of unit tests in a class depends on the complexity of the functionality being tested. 

A well-structured test suite should cover all possible scenarios, including edge cases, to ensure robustness. One way to measure the effectiveness of tests is through code coverage, which indicates the percentage of code that is executed during testing. While achieving 100% code coverage is beneficial, it does not necessarily guarantee the absence of bugs, as it does not account for logical errors, unexpected input handling, or integration issues. 

Therefore, while high code coverage is a useful metric, it should be complemented with thorough test case design and real-world scenario testing.

When expanding a test suite, code cleanliness becomes a crucial consideration. If a new functional test suite, such as verifying the number of items in the product list, is implemented with redundant setup procedures and instance variables, it may lead to code duplication and reduced maintainability. 

Writing similar test classes without reusing existing code can make future updates and debugging more challenging. To improve code quality, common functionalities should be extracted into reusable methods or base classes to follow the DRY (Don't Repeat Yourself) principle.

 Additionally, organizing tests logically and ensuring a clear separation of concerns enhances readability and maintainability, making the test suite more efficient and scalable. By focusing on these principles, we can create a well-structured test suite that is both comprehensive and easy to manage.

# Module 02

## Reflection Module 02

One of the main code quality issues I tackled was the lack of assertions in some test. I noticed when reviewing sonar cloud that the code quality issues that were in the danger zone of maintainability were to do with lack of assertions in my `EshopApplicationTests` and `CreateProductFunctional`. 

When initially creating the tests, I didn't think they needed assertions because `EshopApplicationTests` was just to run main, but I realized that it had to make sure that main ran without any issues. On the other hand for the functional was to simply test if the product created showed on the page. 


Yes! The CI workflow ensures the project is built correctly and automated tests run on every push or pull request, helping catch issues early. By definition of CI, this would be the optimum implementation of it. In terms of CD, I decided to use koyeb's built in auto deploy which would continously deploy on every push to my repository on GitHub.

# Module 03

## Reflection Module 03

Single Responsibility Principle

The way I have implemented this principle in my project is by creating separate classes for different functionalities, such as the `Product` class for product-related operations and the `Car` class for car-related  operations. This can be viewed through seperating the controllers for `Product` and `Car`. This way, the controllers are only responsible for handling HTTP requests and delegating the actual business logic to the corresponding service classes. This separation of concerns helps to keep the codebase organized and makes it easier to maintain and extend in the future. 


Open / Closed Principle

The Open/Closed Principle (OCP) is another key principle in object-oriented design that states that software entities should be open for extension but closed for modification. This principle encourages developers to design systems that can be easily extended with new functionality without modifying existing code.


Liskov Substitution Principle

The Liskov Substitution Principle (LSP) is a principle that states that objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program. This principle ensures that subclasses adhere to the same contract as their superclass, allowing them to be used interchangeably.


Interface Segregation Principle

The Interface Segregation Principle (ISP) is a principle that states that clients should not be forced to depend on interfaces they do not use. This principle encourages developers to create small, cohesive interfaces that are tailored to the specific needs of clients, rather than large, monolithic interfaces that contain unnecessary methods.

Dependency Inversion Principle

The Dependency Inversion Principle (DIP) is a principle that states that high-level modules should not depend on low-level modules. Instead, both should depend on abstractions, this can be viewed in `CarController.java` where the controller is dependent on the service interface. This principle helps to decouple modules and reduce dependencies, making the codebase more flexible and easier to maintain.



2) Explain the advantages of applying SOLID principles to your project with examples.

The advantages of applying SOLID principles to my project are as follows:


1. Improved code quality: By following SOLID principles, I was able to create a well-structured and maintainable codebase that is easy to understand and modify. This has helped me to write cleaner, more readable code that is less prone to bugs and errors.
2. Longevity: By adhering to SOLID principles, I have created a codebase that is more flexible and extensible, making it easier to add new features and make changes in the future. This has helped me to future-proof my project and ensure that it can adapt to changing requirements over time.
3. Reusability: By following SOLID principles, I have created code that is more modular and reusable, allowing me to easily reuse components across different parts of my project. This has helped me to reduce code duplication and improve the overall efficiency of my codebase.
4. Testability: By following SOLID principles, I have created code that is easier to test and debug. This has helped me to write more comprehensive unit tests and ensure that my code behaves as expected in different scenarios.
5. Scalability: By following SOLID principles, I have created a codebase that is more scalable and easier to maintain. This has helped me to build a project that can grow and evolve over time without becoming overly complex or difficult to manage.

3) Explain the disadvantages of not applying SOLID principles to your project with examples.

The disadvantages of not applying SOLID principles to my project are as follows:

1. Decreased code quality: Without following SOLID principles, my codebase would likely be less organized and harder to maintain. This could lead to code duplication, tight coupling, and other code smells that make it difficult to understand and modify the code.
2. Reduced flexibility: Without following SOLID principles, my codebase would likely be less flexible and extensible. This could make it harder to add new features or make changes to the existing code without introducing bugs or breaking existing functionality.
3. Lower reusability: Without following SOLID principles, my codebase would likely be less modular and reusable. This could lead to code duplication and inefficiencies that make it harder to reuse components across different parts of the project.
4. Poor testability: Without following SOLID principles, my codebase would likely be harder to test and debug. This could make it more difficult to write comprehensive unit tests and ensure that the code behaves as expected in different scenarios.
5. Limited scalability: Without following SOLID principles, my codebase would likely be less scalable and harder to maintain. This could make it harder to build a project that can grow and evolve over time without becoming overly complex or difficult to manage.

