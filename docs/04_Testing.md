# Software Testing 

Descrizione dei casi di test implementati per le classi:
- WashineCoreWashing
- WashineCoreCommunity
- WashineCoreAuth

Abbiamo verificato il corretto funzionamento delle operazioni principali del sistema in modo da garantire una corretto utilizzo dei dati e gestione degli errori.

I casi di test sono stati effettuati nei seguenti file:
- WashineCoreWashingTest in cui abbiamo verificato la corretta creazione e gestione dei lavaggi della classe WashineCoreWashing
- WashineCoreCommunityTest per verififcare il corretto funzionamento delle funzionalità di invito e partecipazione della community della classe WashineCorecommunity
- WashineCoreAuthTest per verificare la correttezza autenticazioni degli utenti e i vari metodi di autenticazione del profilo utente, modifica password, Email, e le varie funzionalità del profilo Admin. 

## 1. Casi di Test  

### 1.1. WashineCoreWashingTest

![alt text](https://github.com/UniGiu/Washine/blob/main/docs/Immagini/WashineCoreWashingTest.png)  
*Risultato casi di test WashineCoreWashingTest*

#### createWashingTest

![alt text](https://github.com/UniGiu/Washine/blob/main/docs/Immagini/createWashingTest.png) 

Verifica che la creazione di un lavaggio avvenga correttamente, estituendo True quando il processo è completato con successo.  

#### getLaundryPersonWashingIdsTest  

![alt text](https://github.com/UniGiu/Washine/blob/main/docs/Immagini/ListWashingTest.png) 

Verifica che si possa risalire alla lista di lavaggi di uno specifico utente. Dato l'id utente (testUserId) il metodo deve restituire una lista non vuota contentente i vari Id dei lavaggi a cui un'utente ha partecipato.  

#### getParticipatedWashingIdsTest  
Verifica che si possa risalire alla lista di lavaggi di uno specifico utente. 

#### getLaunderWashingsTest  
Verifica che venga restituita correttamente la lsita di lavaggi attivi di un lavandaio specifico.  

#### getWashingTest  
Verifica che un lavaggio venga creato con le opzioni di lavaggio impostate correttamente. 

### 1.2. WashineCorecommunityTest  

![alt text](https://github.com/UniGiu/Washine/blob/main/docs/Immagini/WashineCoreCommunityTest.png)  
*Risultato casi di test WashineCoreCommunityTest*

#### addInviteTest1
Verifica che un particolare utente sia nella lista degli inviti di partecipazione della community.
#### addInviteTest
Verifica che venga restituito un messaggio di errore se si tenta di invitare un utente già membro della community.  
#### nameInInvitationsTest
Verifica che un nome non esistente non sia presente tra gli inviti. 
#### nameInCommunityTest2  
Verifica che un nome non esista nella comunità specificata.   
#### getIdThroughCodeTest1
Verifica che il recupero dell'Id di una comunità tramite un codice inesistente restituisca null. 
#### updateCodeTest2
Verifica che un nome non esista nella comunità specificata.   
#### joinCommunityTest1  
Verifica che un partecipante non possa unirsi con un codice errato.
#### joinCommunityTest2
Verifica un intero flusso di adesione a una comunità.
#### nameInJoinedCommunitiesTest
Verifica che un nome inesistente non sia presente nelle comunità a cui l'utente ha aderito.
#### userInCommunityTest
Verifica che un utente non possa far parte di una community che non esiste.
#### removeUserTest1
Verifica che dati due parametri errati, non venga effettuata la rimozione dell'utente dalla community. 

### 1.3. WashineCoreAuthTest

![alt text](https://github.com/UniGiu/Washine/blob/main/docs/Immagini/WashineCoreAuthTest.png)
*Risultato casi di test WashineCoreAuthTest*

#### updateUserPassword  

![alt text](https://github.com/UniGiu/Washine/blob/main/docs/Immagini/UpdatePw.png)  

Verifica che un utente possa modificare la propria password con successo.
Dato l'id dell'utente e la nuova password, il metodo dovrà restituire un valore non nullo per verificare che l'aggiornamento sia andato a buon fine. 

#### authenticateUserTest1

![alt text](https://github.com/UniGiu/Washine/blob/main/docs/Immagini/AuthUser.png)

Verifica che un utente possa autenticarsi con le sue credenziali e che esse siano valide.
Data la mail e la password, l'autenticazione dovrebbe restituire un oggetto non nullo.

#### authenticateUserTest2  
Verifica che data una mail e una password errate l'utente non venga autentificato.
Ci si aspetta quindi che venga restituito un oggetto non nullo.  
#### addUserTest1  
Verifica che l'inserimento di un utente avvenga in modo corretto.  

#### addUserTest2  
Verifica che l'inserimento di un utente già registrato fallisca.  

#### updateUserEmail  
Verifica che l'aggiornamento dell'Email di un'utente avvenga in modo corretto. 

#### authenticateAdminTest1  
Verifica la corretta autenticazione del profilo Admin (identificato con id = 0).  

#### authenticateAdminTest2  
Verifica che l'autenticazione di un iutente Admin fallisca se viene fornito un id non valido.  

#### blockUserTest1  
Verifica l'impossibilità di bloccare un profilo Admin.  

#### blockUserTest2  
Verifica la correttezza del blocco di un utente da parte di Admin.  

#### unblockUserTest1  
Verifica l'impossibilità di bloccare un profilo Admin da parte di un qualsiasi User.  

#### unblockUserTest2
Verifica la correttezza dello sblocco di un utente da parte di Admin.  


## 2. Copertura del codice 

![alt text](https://github.com/UniGiu/Washine/blob/main/docs/Immagini/CoverageCore.png)

Poco più di metà del codice è stato testato (54,5 %) . I test sono stati effettuati principalmente su funzionalità centrali quali lavaggi, gestione delle comunità e gestione degli utenti.
