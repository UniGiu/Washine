# Software Testing 

Descrizione dei casi di test implementati per le classi WashineCoreWashing e WashineCoreCommunity.
Lo scopo è verificare il corretto funzionamento delle operazioni principali del sistema garantendo una corretto utilizzo dei dati e gestione degli errori.

Con WashineCoreWashingTest abbiamo verificato la corretta creazione e gestione dei lavaggi della classe WashineCoreWashing.
Invece con washineCoreCommunityTest il corretto funzionamento delle funzionalità di invito e partecipazione della community della classe WashineCorecommunity.  

## Esempio di alcuni casi di Test  

### WashineCoreWashingTest

#### createWashingTest

Verifica che la creazione di un lavaggio abbia successo in modo corretto.

Dato l'id dell'utente e opzioni di lavaggio, se il lavaggio viene correttamente creato, ci si aspetta che venga ritornato il valore True.

#### getLaundryPersonWashingIdsTest

Verifica che si possa risalire alla lista di lavaggi si uno specifico utente.

Dato l'id utente (testUserId) il metodo deve restituire una lista non vuota contentente i vari Id dei lavaggi a cui un'utente ha partecipato.



### WashineCorecommunityTest

#### 



