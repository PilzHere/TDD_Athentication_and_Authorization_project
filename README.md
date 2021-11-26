# TDD_Athentication_and_Authorization_project
## Tester
  
### Utan någon uppvärmning:
(uträkning och verifiering)

##### Iterationer: 65 536
| algoritm | tid | ökning i procent |
| ------------- | ------------- | ------------- |
| HMAC-SHA**512** | 387 ms | **+36.75%** |
| HMAC-SHA**384** | 309 ms | **+9.2%** |
| HMAC-SHA**265** | 283 ms | |

##### Iterationer: 655 360
| algoritm | tid | ökning i procent |
| ------------- | ------------- | ------------- |
| HMAC-SHA**512** | 2s 123 ms | **+47%** |
| HMAC-SHA**384** | 1s 833 ms | **+27%** |
| HMAC-SHA**265** | 1 444 ms | |
  
&nbsp;&nbsp;
## Hur jag hade använt hashing i ett login-system:
Jag kommer inte på något sätt att optimera... så här kommer en förklaring hur jag skulle ha använt mig av detta till en login-system applikation.
  
*(rätta mig gärna om jag har fel)*

* **HMAC**: är algoritmen som ska användas. HMAC använder sig utav SHA.
* **256 -> 512**: är hur många symboler hashen ska innehålla. Större tal -> svårare att räkna ut och tar längre tid. Påverkar inte tiden lika mycket som Iterationer gör.
* **Iterationer**: man använder X antal iterationer. Ju fler iterationer ju längre tid tar det. Detta är bra ifall en hacker försöker lista ut innehållet. Samtidigt vill man inte ha för många iterationer för det tar längre tid för servern att räkna ut också.
* **Salt**: Man lägger till salt i sin key och saltet är unikt/randomiserat sedan användararen skapades. Saltet är synligt i databasen.
* **Peppar**: Man kan även lägga till peppar i sin key. Peppar finns antingen i som en variabel i operativsystemet eller i ett program som körs på servern. Pepparn ska inte vara synlig för någon annan.
  
  
