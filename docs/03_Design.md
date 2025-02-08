
# Architettura e Design
## Indice

## 1. Software Architecture
### Architettura

Abbiamo sviluppato il progetto seguendo un'architettua a tre livelli, in cui ogni livello utilizza le funzionalità del livello sottostante, garantendo modularità e separazione.  Questo dovrebbe rispondere ai requisiti non funzionali di adattabilità, manutenibilità e favorire analizzabilità. 
I tre livelli che costituiscono il nostro sistema sono:
- **Database:**  
Livello inferiore dell'architettura, responsabile della gestione e memorizzazione dei dati. È stato realizzato con il supporto di JOOQ.
- **Logico (Core):**  
Livello intermedio, responsabile di gestire la logica applicativa del sistema e rappresenta il punto di collegamento tra il database e l'interfaccia grafica.
- **UI/GUI:**  
Livello superiore dell'architettura che rappresenta il punto di interazione con l'utente. Realizzato utilizzando il framework Vaadin.

Abbiamo anche deciso di seguire il pattern architetturale MVC nello sviluppo livelli e abbiamo quindi individuato 4 componenti principali:

- **Un componente di presentazione:**
 Questo componente appartiene al livello UI e abbiamo deciso che trattandosi di applicazione web interagisca con gli utenti attraverso il protocollo HTTP e con i livelli sottostanti esclusivamente attraverso interfacce astratte in modo da essere il più possibile modulare e disaccoppiato dal livello inferiore. 
Nell'implementazione attuale abbiamo utilizzato il framework Vaadin (Vaadin Flow) in modo da poter implementare un'interfaccia grafica che risponda ai principali requisiti requisiti di usabilità e di interfaccia utente previsti.
In virtù della sua modularità questo componente in alternativa potrebbe anche essere un sito web sviluppato con un altro framework, magari più accessibile per  categorie di utenti, o un'API a cui potrebbero connettersi appplicazioni di terze parti o dispositivi intelligenti, ad esempio un cesto della biancheria IOT. La natura open source del progetto incoraggia l'apertura a nuove configurazioni. Bisogna inoltre considerare che in sé anche il componente Vaadin che per qui brevità consideriamo un'unico componente ha in sè diversi componenti e una sua propria architettura.

![alt text](https://raw.githubusercontent.com/UniGiu/Washine/refs/heads/main/docs/Immagini/vaadin-application-architecture.png)

 _architettura di un'appliczione vaadin flow. Fonte immagine: https://vaadin.com/docs/latest/flow/application_

- **Un componente controller**
Lo stile architetturale interno di questo componente, che appartiene al livello Logico (Core), è quello "tipo di dati astratto" e prevede la modularità sia per rispondere ai requisiti i di adattabilità che di manutenibilità. Oltre ad essere fortemente disaccoppiato dai livelli superiore e inferiore anche al suo interno è composto da componenti modulari cui è affidata la responsabilità di una parte del servizio tramite interfacce. Abbiamo previsto che per la stessa responsabilità sia possibile avere componenti diversi che in diverse combinazioni possano fornire gli stessi servizi al livello superiore in base al tipo di componente di presentazione utilizzato. Ciò oltre a rispondere al requisito di adattabilità rende possibile fornire transitoriamente dei servizi fittizi al componente di presentazione a supporto della fase di sviluppo, quando non tutti i pezzi da cui la GUI dipende sono pronti e funzionanti.
Questi componenti interni sono di tipo computazionale, senza memoria, nel senso che la responsabilità della coerenza di stato del sistema gestito dal software è delegata al livello Database, non esiste uno stato intermedio: dato un particolare input dal livello UI questi componenti ne controlleranno la validità, ne cureranno l'elaborazione, genereranno un nuovo stato da portare al livello inferiore e si prenderanno carico di fornire una risposta appropriata al livello UI per rispecchiare l'attuale stato del sistema. Dopo questo nulla viene tenuto e il componente è pronto per rispondere a un nuovo input come se fosse il primo. 
Il componente controller comunica anche con il livello Database tramite un'interfaccia astratta.


- **Un componente modello logico**
Questo componente del livello Logico (Core) è quello che permette di rappresentare gli oggetti della business logic che vengono utilizzati dal componente controller. Viene poi esposto dal controller al livello Ui esclusivamente tramite le interfacce dei suoi oggetti. Si tratta di un componente passivo che supporta modellazione e stuttura dell'informazione del dominio dell'applicazione per permetterne la manipolazione.


- **Un componente modello fisico**
Anche questo è un componente che può essere sostituito, grazie a dei servizi di interrogazione modulari. Rispetto al componente di presentazione in questo caso il sistema è meno complesso perché più stabile e meno potenzialmente esposto alla varietà dell'ambiente esterno. I requisiti di manutenibilità adattabiltià e di portabilità hanno comunque pesato verso scelta della modularità. I componenti che lo compongono sono componenti di memoria e di di gestione della memoria. Il database che abbiamo utilizzato è SQLLite e il servizio di interrogazione dell'applicazione vi accede tramite la libreria di mapping JOOQ. Se si desiderasse utilizzare altri DBMS e altre librerie basterà aggiungere un gestore che implementi la setessa interfaccia senza dover cambiare nulla a nei livelli superiori. 

Nel diagramma dei componenti è possibile vedere i tre livelli e i componenti che li realizzano, le loro interazioni e gerarchie.

![alt text](https://raw.githubusercontent.com/UniGiu/Washine/refs/heads/main/docs/Immagini/ComponentiWashineBig.SVG)

 ### Decisioni progettuali particolari:
 
**Implementazione autenticazione**

|Elemento   |Descrizione   |
|---|---|
| Problema  | Per fornire il servizio è necessario identificare l'utente che compie le operazioni  |
| Decisione  | Implementare un sistema di autenticazione minimale e indipendente dalla scelta di componente di presentazione, lo stato dell'autenticazione è memorizzato a livello di sessione |
| Stato  | Approvato |
| Presupposti  | Il sistema è accessibile da web e utilizza HTTP/HTTPS |
| Alternative  |  Vaadin ha un sistema di autenticazione utente raffinato che permette di gestire aspetti come il routing e privilegi |
| Motivazione  | Un sistema implementato utilizzando le variabili di sessione del componente e che salvi le credenziali è indipendente dal framework utilizzato per la presentazione. Inoltre è più semplice integrare la gestione degli utenti anche a livello di business logic in quanto le relazioni tra gli utenti sono parte del dominio dell'applicazione e facilitare e organizzare la loro interazione è tra i requisiti |
| Implicazioni  | Vanno implementate funzionalità già fornite da Vaadin  |
| Appunti  | /  ||


|Elemento   |Descrizione   |
|---|---|
| Problema  | Fornire un livello di presentazione che possa essere internazionalizzato  |
| Decisione  | Utilizzare la lingua inglese e dare al livello presentazione la responsabilità su unità di misura, convenzioni e standard locali  |
| Stato  | Approvato  |
| Presupposti  | Altre versioni locali possono essere derivate da quella originale senza dover modificare i livelli sottostanti  |
| Alternative  |  Vaadin supporta il sistema i18n |
| Motivazione  | Era necessario un compromesso tra i requisiti di budget, e il requisito di comprensibilità e installabilità cioè limitare lo sforzo degli utenti nel comprendere l'interfaccia e quello dell'utente installatore per riadattare il software in base al contesto di utilizzo  |
| Implicazioni  | Sarà più complicato, lungo e dispendioso tradurre l'interfaccia Vaadin  |
| Appunti  | Le date sono gestite logicamente come date gregoriane anche nei livelli core e database anche se potrebbero usare senza particolari accorgimenti un numero intero |


### Viste architettoniche


- **Vista utente installatore/admin**
Nel file readme.md vengono date le istruzioni per eseguire l'applicazione in modalità sviluppo e per preparare il bundle eseguibile. Vengono date anche indicazioni sui requisiti minimi dell'ambiente in cui verrà installato il software. 
I diagrammi dei componenti e dei package inoltre danno una vista di insieme dell'architettura del sistema come descritta prima e delle relazioni e gerarchie tra i componenti in modo da avere una prima idea di dove mettere le mani in caso di malfunzionamenti o manutenzione perfettiva/adattiva.
Una volta capito l'insieme questo utente prima di mettere lavorare sul codice potrà consultare, se disponibili, gli altri diagrammi più specifici come i diagrammi delle classi, di comunicazione e di sequenza. 
Tutti questi aspetti rispondono ai requisiti di installabilità e adattabilità.

![alt text](https://raw.githubusercontent.com/UniGiu/Washine/refs/heads/main/docs/Immagini/package.SVG)

- **Vista utente**
Gli utenti veri e propri dell'applicazione per orientarsi nell'interfaccia grafica avranno a disposizione il menu di navigazione, le cui voci sono corredate di icone.   Inoltre è prevista una pagina di Help che illustri i casi d'uso dei vari requisiti funzionali, dove ogni descrizione è in forma testuale e illustrata da un video screen capture. Abbiamo immaginato un'utenza piuttosto variegata, gli utenti più esperti e curiosi possono consultare nel repository la documentazione e i diagrammi. Questo risponde ai requisiti di comprensibilità e apprendibilità.



## 2. Software Design

### Descrizione Design

Il principi che hanno ispirato la progettazione dell'applicazione nei livelli Core e Database sono quelli di coesione interna e disaccoppiamento e information hiding. La nostra conoscenza poco profonda del linguaggio Java ha dato al risultato una forma un po'rozza (basti vedere l'utilizzo quasi esclusivo di public e private come indicatori di visibilità) ma l'orientamento ideale di questa esperienza è stato quello.

Inizialmente abbiamo individuato gli oggetti principali del dominio dell'applicazione e le loro relazioni e ne abbiamo fatto il diagramma delle classi. Successivamente abbiamo preso in considerazione i casi d'uso e i requisiti funzionali e abbiamo progettato le classi e le interfacce che utilizzano questi oggetti suddividendoli principalmente per coesione logica e comunicativa individuando le tre funzioni di servizio principali: proposta e partecipazione ai lavaggi (che inizialmente pensavamo di tenere separati), messa in relazione tra utenti, e autenticazione.

Questa suddivisione si è mantenuta a livello di interfaccia e componenti su tutti e due i livelli considerati.
Laddove abbiamo visto la possibilità e l'utilità di usare interfacce invece che oggetti concreti lo abbiamo fatto, soprattutto quando si trattava di passare dati e comandi tra uno strato e l'altro, privilegiando forme di accoppiamento minimo (francobolli e dati) Il disaccoppiamento, oltre che una buon principio del design ci ha anche aiutato a non intralciarci nella fase di implementazione.

Riguardo all'interfaccia Vaadin la progettazione è consistita nell'utilizzare lo strumento di editing visuale che abbiamo trovato online per implementare le pagine web cercando di utilizzare i componenti "COT" disponibili in base ai requisiti di interfaccia.
Ottenuto lo scheletro dell'applicazione abbiamo cercato di seguire gli stessi principi utilizzati per gli strati inferiori nel contesto di una struttura già impostata e un ambiente pressoché ignoto che ha richiesto una buona dose di di lerning by doing per capire bene il lifecyle delle pagine e il funzionamento generale del framework. Molti dei componenti inseriti non sono sono stati utilizzati in favore di soluzioni custom perché si sono rivelati troppo rigidi o complicati per le nostre necessità. Questo in parte spiega la presenza nel package "utils" di alcuni componenti che sono cresciuti di numero e meriterebbero un package a parte. 

![alt text](https://raw.githubusercontent.com/UniGiu/Washine/refs/heads/main/docs/Immagini/ClassDiagram.SVG)

### Calcolo Complessità

Per il calcolo della complessità facciamo riferimento alle metriche fornite dal tool STAN e in particolare alla complessità ciclomatica media, abbiamo:
+ washine_db/src/main/java 
  + complessità ciclomatica media 1.16
+ washineCore/src/main/java
  + complessità ciclomatica media 1.08, particolare complessità si ha dove c'è da creare e modificare i lavaggi per via dell'elevato numerodi opzioni. Questo è un aspetto per il quale andrebbe valutata un'attività di refactoring.
+ washine-vaadin/src/main/java
  + complessità ciclomatica 1.91. Notevole complessità nelle classi che gestiscono il componente con il modulo di inserimento dei lavaggi (sempre perché ci sono parecchie opzioni di lavaggio) e il corrispondente elemento della lista lavaggi. Non è una sorpresa, nelle interfacce abbiamo delle opzioni principali e altre che si possono mostrare a espansione. 

### Misurazione Codice

Sempre tramite STAN abbiamo avuto modo di valutare il codice secondo diverse metriche. Lo strumento permette di individuare in maniera puntuale le parti di codice che ottengono una valutazione negativa. Per avere un'idea generale è interessante guardare i Pollution Graph:

![alt text](https://raw.githubusercontent.com/UniGiu/Washine/refs/heads/main/docs/Immagini/pollution.png)

### Design Pattern 

Abbiamo utilizzato dei design pattern sia in fase di progettazione che durante lo sviluppo quando ci siamo accorti che erano la soluzione migliore. Di notevole c' l'utilizzo dell'abstract factory pattern che fornisce al componente di presentazione delle interfacce per i servizi che possono variare in base alla richiesta. Questi servizi sono dei singleton pattern.
Altri pattern che abbiamo utilizzato più o meno consapevolmente e in maniera più o meno stretta possono essere:
Chain of reponsability pattern ad esempio nel percorso che fanno i dati inseriti dall'utente dal browser al database, dove ogni passaggio verifica un aspetto: il livello presentazione valida l'input e produce messaggi di errore e cambia lo stato di singoli input della pagina, il livello core fa un'ulteriore validazione dell'input anche in relazione allla busines logic.
Strategy pattern nella UI, in particolare per il form di partecipazione ai lavaggi, a seconda del contesto il componente della pulsantiera mostra i bottoni per partecipare a un lavaggio, oppure per modificare il carico o rinunciare alla partecipazione. 
Façade quando si tratta di mostrare le opzioni di lavaggio nelle liste dei lavaggi disponibili viene usata un'interfaccia dell'oggetto con i dati del lavaggio che è di sola lettura.
Un pattern che vorremmo utilizzare è il builder pattern per costruire gli elementi delle liste dei lavaggi che per ora utilizzano l'ereditarietà, ci siamo riservati di farlo come refactoring una volta stabilizzato anche il form di partecipazioen ai lavaggi.

![alt text](https://raw.githubusercontent.com/UniGiu/Washine/refs/heads/main/docs/Immagini/AbstractFatory.png)
