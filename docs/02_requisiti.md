# Specifica dei requisiti
## Indice

## Definizioni

| Termine | Definizione |
| ----- | ----- |
| adesione | Ingresso di un utente in una comunità |
| amministratore | Utente che ha installato il sistema e lo gestisce |
| calcolatore di carico | Tabella interattiva per il calcolo del carico con valori medi per capo  |
| caratteristiche di lavaggio | Caratteristiche necessarie alla creazione di un lavaggio  |
| carico | Quantità di vestiti in kg portata da ogni utente in un lavaggio |
| comunità |  Insieme di utente accomunati da un lavandaio |
| disconnessione | Disconnessione da una comunità |
| GUI | Componente software grafica per l’interazione con l’applicazione |
| indirizzo | Luogo dove ci ritrova per la consegna o per il ritiri del proprio carico |
| lavaggio | Insieme di informazioni relative a un lavaggio programmato da un lavandaio con carichi degli utenti  |
| lavandaio | Utente che crea un lavaggio |
| opzioni di lavaggio | Caratteristiche opzionali di un lavaggio  |
| partecipante | Utente che inserisce un carico nel sistema  |
| partecipare a un lavaggio | Aggiungere il proprio carico al lavaggio  |

## Elicitazione dei requisiti

L’elicitazione dei requisiti è avvenuta attraverso un’analisi dello scenario. Durante una sessione di brainstorming ci siamo concentrati sull’identificazione dei potenziali profili di utenti tipo della nostra applicazione. Il nostro obiettivo era di individuare i bisogni principali per ciascuno, comprendendo le loro necessità e traducendole in requisiti.

Abbiamo definito 6 potenziali profili utente:  
 

| Utente | Profilo/contesto | Bisogni/requisiti degli attori |
| :---- | :---- | :---- |
| Nonna Pina | Si ritrova con le amiche e passano del tempo insieme durante il lavaggio che offrono a turno amichevolmente senza dividere ogni volta le spese. Spesso, se non ha impegni, il ritrovo conviviale continua il giorno successivo perché anche per non portare pesi eccessivi stende tutti i panni a casa sua. Al figlio e ai nipoti lava e stira anche le camicie. Esperta del dominio dell’applicazione. | <ul><li>Granularità alta delle informazioni sul lavaggio</li> <li>Interfaccia utente semplice</li> <li>Segnalare disponibilità asciugatura</li> <li>Differenziare i partecipanti per lavaggio</li> <li>Pesare i vestiti e specificare il peso massimo</li></ul>  |
| I Colleghi | Il loro team fa spesso lavori lontano da casa o affittano una stanza o semplicemente non hanno una lavatrice e ne sanno il minimo indispensabile. Hanno un guardaroba ridotto che va lavato frequentemente o dei vestiti che vanno lavati in maniera specifica. I lavaggi vengono organizzati da uno di loro, o a turno, in lavanderie a gettone dove si ritrovano | <ul><li>Dividere il carico in parti uguali</li><li>Bassa granularità delle informazioni sul lavaggio (ma appropriata)</li><li>Impostazione entità massima dei carichi </li><li>Esplicitare indirizzo, e ora di ritrovo</li><li>Esplicitare i costi e sistema di ripartizione</li><li>Asciugatrice</li><li>Interpretare correttamente le indicazioni di lavaggio sulle etichette vestiario</li><li>Pesare i vestiti e specificare il peso massimo</li></ul> |
| I Condomini | Si conoscono da tempo e spesso si scambiano favori. Alcune famiglie sono numerose e capitano delle eccedenze di vestiti da lavare, soprattutto con figli piccoli. Solitamente i partecipanti ritirano a orari diversi i capi dal lavandaio e li fanno asciugare a casa qualcuno si trattiene durante il lavaggio. In qualche condominio c’è una lavanderia comune. | <ul><li>Nessun indirizzo</li><li>Intervalli orari di disponibilità per il ritiro</li><li>Durata del lavaggio</li><li>Pesare i vestiti e specificare il peso massimo</li></ul>  |
| L’Utente Base | Non ha/vuole una lavatrice e non vuole usare una lavatrice a gettoni, forse perché non sa usarla. Si appoggia a uno o più conoscenti lavandai ai quali su appuntamento porta i vestiti a casa o a un punto di incontro . | <ul><li>Intervalli orari di disponibilità per il ritiro</li><li>Indirizzo di raccolta o consegna</li><li>Esplicitare i costi e sistema di ripartizione</li></ul> |
| Il Perditempo | Spesso in pensione, molto attivo, offre il servizio ad amici, parenti e conoscenti spesso non così autosufficienti raccogliendo i panni da ciascuno al domicilio (o al bar) e lavandoli a casa propria o in lavanderia a gettoni. Si occupa anche della riconsegna. Dai partecipanti riceve riconoscenza e generalmente un rimborso delle spese. | <ul><li>Durata del lavaggio</li><li>Pesare i vestiti e specificare il peso massimo</li><li>Interpretare correttamente le indicazioni di lavaggio sulle etichette vestiario</li><li>Esplicitare i costi e sistema di ripartizione</li><li>Indicare indirizzi di ritiro e consegna</li></ul> |
| I Parrocchiani | I parrocchiani hanno raccolto fondi per una lavatrice di comunità per i bisognosi che in giorni e orari prefissati si possono presentare con i loro indumenti che verranno accettati e lavati fino a esaurimento disponibilità. Parte del carico può essere riservata per chi non ha accesso a Washine e si presenta direttamente in parrocchia.  | <ul><li>Programmare un calendario lavaggi</li><li>Gestire l’accessibilità ai lavaggi e ai carichi</li><li>Inserimento carichi di utenti non registrati</li><li>Risalire ai proprietari degli indumenti</li><li>Granularità minima delle informazioni di lavaggio</li><li>Impostazione entità massima dei carichi</li><li>Pesare i vestiti e specificare il peso massimo</li></ul>  |

Abbiamo inoltre individuato 3 ruoli: 

| Ruolo | Profilo/contesto | Bisogni/requisiti |
| :---- | :---- | :---- |
| Lavandaio | Offre ai partecipanti un servizio di lavaggio e fornisce le informazioni necessarie per decidere se aderire. Può annullare un lavaggio. Propone l’adesione degli utenti alla sua comunità e può disporne la sconnessione. Può essere a sua volta partecipante.  | Fornire un lavaggio e gestirne le informazioni e l’accesso  Gestire la composizione della comunità Coordinare il processo relativo al lavaggio |
| Partecipante |  Aderisce alla comunità di lavaggio di un lavandaio, partecipa ai lavaggi che gli vengono offerti indicando l’entità del suo carico. Può essere a sua volta lavandaio. | Fornire informazioni sul carico Ricevere informazioni sui lavaggi  |
| Admin | Per qualche motivo ha deciso di installare Washine su un server e offrire il servizio, ma non è necessariamente esperto di informatica e pur non essendo un richiestissimo ingegnere informatico non ha molto tempo da dedicare alla gestione dell’applicazione. La sua attività principalmente si limita  a saltuari aggiornamenti e a considerare eventuali problematiche tecniche segnalate dagli utenti. Quando strettamente necessario può intervenire sulle attività degli utenti rimuovendo lavaggi, eliminando utenti. Può limitare l’accesso a un insieme ristretto di utenti, o impedirlo ad altri. Può essere | Monitorare e tracciare l’utilizzo dell’applicazione Installare e configurare l’applicazione con competenze basse Accedere al codice dell’applicazione Eliminare lavaggi Eliminare o resettare utenti Regolare l’accesso tramite una lista nera/bianca di utenti |

Una volta terminato questo passaggio, abbiamo identificato i requisiti comuni tra tutti gli utenti e quelli tra loro contrastanti in modo da considerare i più rilevanti (e se necessario trovare dei compromessi) per soddisfare le necessità principali di ogni utente.

##  Requisiti

### Convenzioni di numerazione dei requisiti

La specifica è suddivisa in requisiti il cui formato è:  
XXX.n.y  
Dove:  
XXX \= codice di tre lettere che indica il tipo di requisito  
n \= numero del requisito 1-9999  
y \= numero del sottorequisito 1-99 se presente

| Codice | Tipo di requisito |
| :---- | :---- |
| FUN | Requisiti funzionali |
| GUI | Requisiti GUI |
| PRF | Requisiti di performance |
| QLT | Requisiti di qualità |

### Requisiti funzionali

| ID | Requisito/motivazione | Riferimento |
| :---- | :---- | :---- |
| FUN.1 | Il sistema deve permettere al lavandaio di offrire ai partecipanti l’adesione alla sua comunità e la loro disconnessione. Motivazione: Il lavandaio è colui che gestisce l’accesso alla sua comunità. |  |
| FUN 1.1 | Il sistema deve permettere agli utenti di accedere con la propria identità. |  |
| FUN.2 | Il sistema deve permettere al lavandaio di identificare i partecipanti della sua comunità. |  |
| FUN.3 | Il sistema deve permettere al lavandaio di selezionare i membri della comunità che possono vedere un lavaggio e accedervi. |  |
| FUN.3.1 | Il sistema deve permettere al lavandaio di creare dei gruppi di utenti “scorciatoia” per assegnare  a un lavaggio |  |
| FUN.4 | Il sistema deve permettere al lavandaio di proporre dei lavaggi. I lavaggi rimarranno modificabili fino a che non ci sarà un partecipante. |  |
| FUN.4.1 | Il sistema deve permettere al lavandaio di inserire le informazioni sul lavaggio necessarie al partecipante per decidere se parteciparvi.  Informazioni necessarie: <ul>  <li>temperatura del lavaggio</li> <li>tipologia dei vestiti</li> <li>colore dei capi</li> <li>data, ora e durata del lavaggio</li> <li>detersivi usati per il lavaggio</li> <li>il lavaggio prevede intimo</li> <li>modalità ed entità del rimborso spese</li> <li>capacità di carico</li> <li>carico iniziale</li> <li>tempo di visibilità del lavaggio</li></ul>|  |
| FUN.4.2 | Il sistema deve permettere al lavandaio di decidere la complessità delle informazioni sul lavaggio.  Informazioni opzionali: <ul>  <li>indirizzi di ritiro o di consegna del carico</li> <li>orari e intervalli di disponibilità per il ritiro o la consegna</li> <li>asciugatura e tipologia asciugatura</li> <li>servizio di stiratura carico massimo per partecipante</li> <li>data di apertura e chiusura di accesso al lavaggio</li></ul>  Motivazione: Con le informazioni opzionali il Lavandaio può modulare la tipologia di lavaggio offerto. |  |
| FUN.5 | Il sistema deve permettere di stimare il peso di un carico dati la tipologia e il numero dei capi. Motivazione: Il peso dei capi è un’informazione che potrebbe essere difficile da ottenere con gli strumenti di misura a disposizione degli utenti e una stima soggettiva potrebbe essere molto errata. |  |
| FUN.6 | Il sistema deve permettere al lavandaio di aggiungere carichi per partecipante non formalmente registrato. Motivazione: Utilizzo Parrocchiani |  |
| FUN.7 | Il sistema deve dare al partecipante uno strumento per visualizzare e identificare le comunità di appartenenza. |  |
| FUN.7.1 | Il sistema deve permettere a un partecipante di uscire da una comunità. |  |
| FUN.7.2 | Il sistema deve permettere a un partecipante di partecipare a un lavaggio e di ritirare la propria partecipazione. |  |
| FUN.8 | Il sistema deve dare informazioni per interpretare le etichette dei vestiti. Motivazione: Molti tipi di lavaggi non sono adatti per alcuni tipi di vestiti e viceversa. |  |
| FUN.9 | Il sistema deve permettere al lavandaio di risalire ai proprietari degli indumenti. Motivazione: Utilizzo parrocchiani |  |
| FUN.10  | Il sistema deve permettere all’amministratore di visualizzare la lista dei lavaggi attivi. |  |
| FUN.10.1 | Il sistema deve permettere all’amministratore di eliminare un lavaggio attivo. |  |
| FUN.11 | Il sistema deve permettere all’amministratore di visualizzare la lista degli utenti. |  |
| FUN.11.1 | Il sistema deve permettere all’amministratore di bloccare e rimuovere un utente. |  |
| FUN.12  | Il sistema deve permettere all’amministratore di visualizzare la lista degli utenti bloccati. |  |
| FUN.12.1 | Il sistema deve permettere all’amministratore di riabilitare un utente bloccato. |  |
| FUN.13 | Il sistema deve permettere all’amministratore di tracciare l’attività del sistema. |  |
| FUN.14 | Il sistema deve fornire informazioni sul suo corretto utilizzo |  |
| FUN.15 | Il sistema deve fornire un’interfaccia a cui connetterlo con diversi altri componenti software che ne consentano l’utilizzo (GUI web, app, agente AI). Motivazione: aumentare la portabilità |  |
|  FUN.16 | Il sistema deve permettere al lavandaio di visualizzare le informazioni relative ai lavaggi che ha in programma e ai partecipanti di ciascun lavaggio. |  |
| FUN.17 | Il sistema deve permettere al lavandaio di programmare un calendario lavaggi Motivazione: Utilizzo parrocchiani |  |
| FUN.17.1 | Il sistema deve permettere al lavandaio di aggiungere lavaggi al calendario |  |
| FUN.17.2 | Il sistema deve permettere al lavandaio di rimuovere lavaggi dal calendario |  |
| FUN.17.3 | Il sistema deve permettere al lavandaio di programmare le finestre temporali di accesso ai lavaggi programmati |  |


### Requisiti di interfaccia

| ID | Requisito/Motivazione | Riferimento |
| :---- | :---- | :---- |
| GUI.1 | La GUI deve implementare le seguenti viste: |  |
| GUI.1.1 | Login:permette all’utente di accedere al sistema. | FUN 1.1 |
| GUI.1.2 | Informazioni sulle etichette: permette all’utente di visualizzare i simboli delle etichette e la loro spiegazione. | FUN 8 |
| GUI.1.3 | Calcolatore peso carichi: permette all’utente di calcolare il peso di un insieme di vestiti. | FUN 5 |
| GUI.1.4 | Help: fornisce istruzioni su come utilizzare l’applicazione. | FUN 14 |
| GUI.1.5 | Home: fornisce un riepilogo delle attività dell’utente e delle comunità a cui ha aderito. | FUN.16FUN.7 |
| GUI.1.6 | Profilo personale: fornisce la possibilità all’utente di cambiare le proprie credenziali di accesso(e-mail e password). | FUN 1 |
| GUI.1.7 | Lavaggi disponibili: fornisce l’elenco dei lavaggi a cui è possibile partecipare divisi per comunità. | FUN.7 |
| GUI.1.8 | Il mio lavaggio: fornisce l’elenco dei lavaggi programmati dall’utente. | FUN.17 FUN.16 |
| GUI.1.9 | Amministratore: fornisce l’accesso alle attività di amministrazione. | FUN.10 |
| GUI.1.10 | Amministrazioni utenti: permette all’amministratore di cercare, visualizzare e modificare le informazioni e lo stato degli utenti. | FUN 11 |
| GUI.1.11 | Amministrazione lavaggi: permette all’amministratore di cercare, visualizzare e modificare le informazioni e lo stato dei lavaggi attivi. | FUN 10 |
| GUI.1.12 | Log:permette all’amministratore di accedere al log. | FUN.13 |
| GUI.1.13 | Gestione lavaggio: permette al lavandaio di creare e modificare un lavaggio. | FUN.2FUN.3 FUN.4 FUN.6 |
| GUI.1.14 | Partecipazione lavaggio: permette al partecipante di partecipare a un lavaggio specificando il peso del proprio carico  | FUN.7 |
| GUI.1.15 | Calendario: permette agli utenti di visualizzare un calendario con indicati i lavaggi programmati e quelli a cui si partecipa o si può partecipare, con la possibilità di entrare nel dettaglio. | FUN.17 |
| GUI.1.16 | Adesioni: permette l’adesione di un partecipante a una comunità o la creazione di un codice monouso per la partecipazione | FUN.1 |
| GUI.1.17 | Registrazione: permette agli utenti di registrarsi tramite email e password | FUN.1 |
| GUI.1.18 | Password smarrita: permette agli utenti di recuperare la password persa tramite email | FUN.1 |

### Requisiti di qualità

| ID | Requisito/Spiegazione | Riferimento |
| :---- | :---- | :---- |
| QLT.1 | Manutenibilità | ISO 9126 |
| QLT.1.1 | Analizzabilità Spiegazione: Qualsiasi carenza o guasto nel sistema deve essere facilmente diagnosticabile, pertanto, per aumentare la analizzabilità del software, il codice deve essere di facile lettura. A tal fine la gran parte del codice deve essere commentata in lingua inglese, di modo che tutti possano interpretarlo rapidamente. | ISO 9126 |
| QLT.1.2 | Stabilità Spiegazione: Per evitare effetti imprevisti dati dalle modifiche il sistema deve sempre avere una versione funzionante. E’ per questo che una modifica, prima di essere introdotta nel branch main deve essere testata nel proprio branch secondario. | ISO 9126 |
| QLT.1.3 | Conformità alla manutenibilità Spiegazione: Il sistema aderisce allo standard di codifica di Google per la scrittura di codice java. | ISO 9126  |
| QLT.2 | Usabilità | ISO 9126 |
| QLT.2.1 | Comprensibilità Spiegazione: Il sistema deve essere di facile comprensione: fin dal primo utilizzo si propone di fornire all’utente tutte le informazione e gli strumenti per usufruire al meglio delle sue funzionalità (grazie anche alla sua interfaccia grafica intuitiva). Il sistema utilizza componenti COTS (Vaadin) quando questo oltre a rendere più rapido lo sviluppo può aiutare l’utente a interpretare la loro funzionalità e finalità dell’interfaccia tramite i paradigmi di interazione standard che questi realizzano. | ISO 9126 GUI 1 |
| QLT.2.2 | Apprendibilità Spiegazione: Il sistema deve fornire una serie di istruzioni sia come pagina di Help sia contestuali all’interfaccia. L’apprendimento esplorativo delle funzionalità pertinenti al proprio caso di utilizzo deve essere rapido.  | ISO 9126 GUI 1 |
| QLT.2.3 | Operabilità: Spiegazione:L’interfaccia utente deve permettere di compiere le operazioni desiderate in maniera efficiente e riducendo le possibilità di errore e le situazioni che possono generare attriti e frustrazione nell’interazione. | ISO 9126 GUI 1 |
|  |  |  |
| QLT.3 | Portabilità |  |
| QLT.3.1 | Installabilità: Spiegazione:Il sistema deve poter essere installato nella maggior parte degli ambienti disponibili in poco tempo e poco sforzo. Questo nella scelta del linguaggio di programmazione da usare significa che questo deve essere supportato dalla maggior parte dei server web, anche quelli a basso costo, e che sia conosciuto dai potenziali installatori. Guardando le statistiche ([https://w3techs.com/technologies/history\_overview/programming\_language/ms/y](https://w3techs.com/technologies/history_overview/programming_language/ms/y)) la scelta più adatta potrebbe essere PHP, ma per motivi esterni utilizzeremo Java e il framework Vaadin. Il software verrà distribuito con una configurazione preimpostata che richiede poche modifiche. | ISO 9126 |
| QLT.3.2 | Adattabilità: Spiegazione: Il sistema dovrà essere modulare nelle sue componenti gui, core e database, che saranno isolati e interagiranno con la parte core tramite interfacce in modo che, data anche la sua natura opensource, sia possibile riadattare ciascuna di queste senza dover intervenire sulle altre. | ISO 9126 |

 

### Requisiti di performance

| ID | Requisito/Spiegazione | Riferimento |
| :---- | :---- | :---- |
| PRF.1 | Efficienza temporale Spiegazione: Il sistema deve rispondere velocemente alle richieste dell’utente. Poiché gli attori sono tutti umani il tempo di risposta non è un aspetto altamente critico per le transizioni tra le viste, ma è comunque importante che sia dimensionato all’essere umano e in generale dovrebbe essere inferiore al secondo per le operazioni di configurazione dei lavaggi e del carico. | ISO 9126 |
| PRF.2 | Stabilità Spiegazione: Il sistema mantenere l’integrità anche con un elevato numero di utenti.  | ISO 9126 |

## Priorità dei requisiti

Abbiamo poi adottato il modello MoSCow per classificare i requisiti, garantendo una corretta implementazione e gestione delle priorità.  
   
 

| Must Have | Should Have | Could Have | Won’t Have |
| :---- | :---- | :---- | :---- |
| FUN.1, FUN.1.1 |  |  |  |
| FUN.2 |  |  |  |
|  | FUN.3 | FUN.3.1 |  |
| FUN.4, FUN.4.1 FUN.4.2 |  |  |  |
|  | FUN.5 |  |  |
|  |  | FUN.6 |  |
| FUN.7 FUN.7.1 FUN.7.2 |  |  |  |
|  | FUN.8 |  |  |
|  |  | FUN.9 |  |
| FUN.10  | FUN.10.1 |  |  |
| FUN.11 FUN.11.1 |  |  |  |
| FUN.12.1 | FUN.12 |  |  |
|  |  | FUN.13 |  |
|  | FUN.14 |  |  |
|  | FUN.15 |  |  |
| FUN.16 |  |  |  |
|  |  |  | FUN.17 FUN.17.1 FUN.17.2 FUN.17.3 |

   
Le priorità dei requisiti funzionali determinano a cascata quelle dei requisiti dell’interfaccia. 
