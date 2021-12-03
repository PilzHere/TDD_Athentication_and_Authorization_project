# TDD_Athentication_and_Authorization_project
## Tester
### Utan någon uppvärmning:
##### Iterationer: 65 536
| algoritm | tid | ökning i procent |
| ------------- | ------------- | ------------- |
| HMAC-SHA**512** | 44s 46ms | **+22.08%** |
| HMAC-SHA**384** | 41s 982ms | **+15.12%** |
| HMAC-SHA**265** | 36s 42ms | |

##### Iterationer: 65 536 * Pi
| algoritm | tid | ökning i procent |
| ------------- | ------------- | ------------- |
| HMAC-SHA**512** | 2m 6s | **+16.67%** |
| HMAC-SHA**384** | 2m 10s | **+20.37%** |
| HMAC-SHA**265** | 1m 48s | |
  
&nbsp;&nbsp;
## Hur jag hade använt hashing i ett login-system:
Här kommer en förklaring hur jag skulle ha använt mig av detta till en login-system applikation.
  
*(rätta mig gärna om jag har fel)*

* **HMAC**: är algoritmen som ska användas. HMAC använder sig utav SHA.
* **256 -> 512**: är hur många symboler hashen ska innehålla. Större tal -> svårare att räkna ut och tar längre tid. Påverkar inte tiden lika mycket som Iterationer gör.
* **Iterationer**: man använder X antal iterationer. Ju fler iterationer ju längre tid tar det. Detta är bra ifall en hacker försöker lista ut innehållet. Samtidigt vill man inte ha för många iterationer för det tar längre tid för servern att räkna ut också.
* **Salt**: Man lägger till salt i sin key och saltet är unikt/randomiserat sedan användararen skapades. Saltet är synligt i databasen.
* **Peppar**: Man kan även lägga till peppar i sin key. Peppar finns antingen i som en variabel i operativsystemet eller i ett program som körs på servern. Pepparn ska inte vara synlig för någon annan.
  
  
