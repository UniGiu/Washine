# Software Testing 

Descrizione dei casi di test implementati per le classi:
- WashineCoreWashing
- WashineCoreCommunity
- WashineCoreAuth

Abbiamo verificato il corretto funzionamento delle operazioni principali del sistema in modo da garantire una corretto utilizzo dei dati e gestione degli errori.

I casi di test sono stati effettuati nei seguenti file:
- WashineCoreWashingTest in cui abbiamo verificato la corretta creazione e gestione dei lavaggi della classe WashineCoreWashing
- WashineCoreCommunityTest per verififcare il corretto funzionamento delle funzionalità di invito e partecipazione della community della classe WashineCorecommunity
- WashineCoreAuthTest per verificare la correttezza autenticazioni degli utenti e i vari metodi di modifica password, Email, ...  

## Casi di Test  

### WashineCoreWashingTest

![alt text](https://github.com/UniGiu/Washine/blob/testing/docs/Immagini/WashineCoreWashingTest.png)  

#### createWashingTest

![alt text](https://github.com/UniGiu/Washine/blob/testing/docs/Immagini/createWashingTest.png) 

Verifica che la creazione di un lavaggio abbia successo in modo corretto.Dato l'id dell'utente e opzioni di lavaggio, se il lavaggio viene correttamente creato, ci si aspetta che venga ritornato il valore True.

#### getLaundryPersonWashingIdsTest  

![alt text](https://github.com/UniGiu/Washine/blob/testing/docs/Immagini/ListWashingTest.png) 

Verifica che si possa risalire alla lista di lavaggi si uno specifico utente. Dato l'id utente (testUserId) il metodo deve restituire una lista non vuota contentente i vari Id dei lavaggi a cui un'utente ha partecipato.

### WashineCorecommunityTest  

![alt text](https://github.com/UniGiu/Washine/blob/testing/docs/Immagini/WashineCoreCommunityTest.png)

### WashineCoreAuthTest

![alt text](https://github.com/UniGiu/Washine/blob/testing/docs/Immagini/WashineCoreAuthTest.png)

#### updateUserPassword  

![alt text](https://github.com/UniGiu/Washine/blob/testing/docs/Immagini/UpdatePw.png)  

Verifica che un utente possa modificare la propria password con successo.
Dato l'id dell'utente e la nuova password, il metodo dovrà restituire un valore non nullo per verificare che l'aggiornamento sia andato a buon fine. 

#### authenticateUserTest1

![alt text](https://github.com/UniGiu/Washine/blob/testing/docs/Immagini/AuthUser.png)

Verifica che un utente possa autenticarsi con le sue credenziali e che esse siano valide.
Data la mail e la password, l'autenticazione dovrebbe restituire un oggetto non nullo.

## Copertura del codice 

![alt text](https://github.com/UniGiu/Washine/blob/testing/docs/Immagini/CoverageCore.png)



