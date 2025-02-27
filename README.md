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

1) Explain what principles you apply to your project!

2) Explain the advantages of applying SOLID principles to your project with examples.
3) Explain the disadvantages of not applying SOLID principles to your project with examples.
