# TDD_Athentication_and_Authorization_project
A school project.

## Tester
### Utan någon uppvärmning:
(uträkning och verifiering)

#### Iterationer = 65 536
* HMAC-SHA512 = 387 ms
* HMAC-SHA384 = 309 ms
* HMAC-SHA265 = 283 ms

#### Iterationer = 655 360
* HMAC-SHA512 = 2s 123ms
* HMAC-SHA384 = 1s 833 ms
* HMAC-SHA265 = 1s 444 ms

Jag kommer inte på något sätt att optimera... så här kommer en förklaring hur jag hade använt mig av detta till ett login-system för en server.

## Hur man använder i ett login-system:
(rätta mig gärna om jag har fel)

* HMAC är algoritmen som ska användas.
* 256 -> 512 är hur många symboler hashen ska innehålla. Större tal -> svårare att räkna ut och tar längre tid. Påverkar inte tiden lika mycket som Iterationer gör.
* Iterationer: man använder X antal iterationer. Ju fler iterationer ju längre tid tar det. Detta är bra ifall en hacker försöker lista ut innehållet. Samtidigt vill man inte ha för många iterationer för det tar längre tid för servern att räkna ut också.
* Salt: Man lägger till salt i sin key och saltet är unikt/randomiserat sedan användararen skapades. Saltet är synligt i databasen.
* Peppar: Man kan även lägga till peppar i sin key. Peppar finns antingen i som en variabel i operativsystemet eller i ett program som körs på servern. Pepparn ska inte vara synlig för någon annan.
